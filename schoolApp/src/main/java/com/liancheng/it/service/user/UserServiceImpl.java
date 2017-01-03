package com.liancheng.it.service.user;


import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.liancheng.it.util.CreateIMId;
import com.liancheng.it.util.DateUtil;
import com.liancheng.it.util.HttpSenderUtil;
import com.liancheng.it.util.Jwt;
import com.liancheng.it.util.RegistCheckCode;
import com.liancheng.it.util.UUIDUtil;
import com.liancheng.it.dao.active.ActiveUserDao;
import com.liancheng.it.dao.friends.FriendsDao;
import com.liancheng.it.dao.user.CheckCodeDao;
import com.liancheng.it.dao.user.UserDao;
import com.liancheng.it.entity.active.Active;
import com.liancheng.it.entity.friends.Friends;
import com.liancheng.it.entity.user.Coder;
import com.liancheng.it.entity.user.User;
import com.liancheng.it.entity.user.Visitor;

import net.minidev.json.JSONObject;
/**
 * �û���¼ҵ��
 */

@Service("userService")//ɨ��service
@Aspect
@Transactional
public class UserServiceImpl implements UserService {
	@Resource(name="userDao")//ע��userDao
	private UserDao userDao;
	@Resource(name="checkCodeDao")//ע��checkCodeDao
	private CheckCodeDao checkCodeDao;
	@Resource(name="friendsDao")
	private FriendsDao friendsDao;
	@Resource(name="activeUserDao")
	private ActiveUserDao activeUserDao;

	public JSONObject checkLogin(String phoneNum, String password, HttpServletRequest request) {
		JSONObject resultJSON = new JSONObject();
		User hasuser = new User();
		System.out.println("phoneNum1="+phoneNum);
		if(phoneNum==null || "".equals(phoneNum)){
			resultJSON.put("msg", "�˺�Ϊ�գ���������ȷ���˺�!");
			resultJSON.put("satuts", false);
			return resultJSON;
		}else if (password==null || "".equals(password)) {
			resultJSON.put("msg", "����Ϊ�գ�����������!");
			resultJSON.put("status", false);
			return resultJSON;
		}else {//�绰���벻Ϊ�յ������
			System.out.println("�绰���벿λ��="+phoneNum);
			int reqParamLen = phoneNum.length();
			if(reqParamLen==11){//�����11λͨ���绰�����ѯ
				hasuser = userDao.findByPhoneNum(phoneNum);
				System.out.println("���ˣ���11λ�ĺ��룡������"+hasuser);
			}
			if(reqParamLen != 11 && hasuser != null){//���û��11λ��11λ�Ĳ�ѯ��������ͨ��У԰���ٲ�һ��
				hasuser.setSchoolId(Integer.parseInt(phoneNum));
				System.out.println("phoneNum2,����11λ�ĺ��룡="+phoneNum);
				hasuser = userDao.findBySchoolId(hasuser);
			}
			System.out.println("�û���ѯ��ϣ�����");
			try {
				if(hasuser!=null && UUIDUtil.md5(password).equals(hasuser.getPassword())){//��¼�����ж�
					
					//���ɶ��ŵ�token
					Map<String , Object> payload=new HashMap<String, Object>();
					Date date=new Date();
					payload.put("uid", phoneNum);//�û�ID
					payload.put("iat", date.getTime());//����ʱ��
					payload.put("ext",date.getTime()+1000*60*60);//����ʱ��1Сʱ
					Jwt.createToken(payload);
					
					request.getServletContext().setAttribute("aa", hasuser.getUser_id());
					
					resultJSON.put("status", true);
					resultJSON.put("msg", "��½�ɹ�");
					resultJSON.put("data",hasuser);
					System.out.println("�û��˶���ϣ�����"+resultJSON.toJSONString());
					return resultJSON;
				}else{
					resultJSON.put("status", false);
					resultJSON.put("msg", "�û��������벻��");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return resultJSON;//����json�ַ���
		}		
	}
	
	
	/**
	 * ������֤�벢������֤��ҵ��
	 */
	public JSONObject checkUserRegist(String phoneNum) {
		
		Coder coder =  new Coder();
		Coder checkCode = checkCodeDao.findCheckCodeByPhoneNum(phoneNum);
		JSONObject resultJSON=new JSONObject();
		
		System.out.println("������֤������");
		
		if(userDao.findByPhoneNum(phoneNum) != null){//���ֻ�����ע��
			resultJSON.put("status", false);
			resultJSON.put("msg", "���ֻ�������ע��");
			return resultJSON;
		}else {//�ֻ�����ûע���
			if(checkCode != null){//�жϸ��ֻ��Ƿ��ȡ����֤��
//				Date date=new Date();
				long currentime = System.currentTimeMillis();
				long creatime = checkCodeDao.findCheckCodeByPhoneNum(phoneNum).getCreatime().getTime();
				
				if(currentime<creatime+1*60*1000){//�ж���֤���Ƿ���ڣ�1�����ڲ����ٻ�ȡ��֤��
					resultJSON.put("status", false);
					resultJSON.put("msg", "1�������ѻ�ȡ��֤�룬�����ٻ�ȡ!");
					System.out.println("��ȡ����֤�룬1min���ܻ�ȡ");
					return resultJSON;
				}else{
					checkCodeDao.deleteCoderByPhoneNum(phoneNum);//5���Ӻ�ɾ������֤��
				}
			}
			String code = RegistCheckCode.getCheckCode();
			String codemsg = "���ã�������֤��Ϊ��"+code+"��Чʱ��1���ӣ��뼰ʱ���ע�ᣡ";
			
			Timestamp time = new Timestamp(System.currentTimeMillis());
			coder.setCode(code);
			coder.setCreatime(time);
			coder.setId(phoneNum);
			checkCodeDao.saveCoder(coder);//����һ����֤��
			System.out.println("��������֤�룺"+ coder.toString());
			try {
				HttpSenderUtil.batchSend("http://sapi.253.com/msg/HttpBatchSendSM",
						"qishidianzi", "Qsdz757980", phoneNum, codemsg, true, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			resultJSON.put("status", true);
			resultJSON.put("msg", "������֤��ɹ�");
			resultJSON.put("data", code);
			System.out.println("��֤�뷵�ص�json��"+resultJSON.toJSONString());
			return resultJSON;
		}
	}
	
	/**
	 * ע��ҵ��
	 */
	public JSONObject addUser(String phoneNum, String password, String code, String username) {
		JSONObject resultJSON=new JSONObject();
		User user = new User();
		Coder checkCode = new Coder();
		
		if(password==null || "".equals(password)){//�������Ϊ��
			resultJSON.put("msg", "����Ϊ��,����������!");
			return resultJSON;
		}
		if(code==null || "".equals(code)){//�����֤��Ϊ��
			resultJSON.put("msg", "��֤��Ϊ��,��������֤��!");
			return resultJSON;
		}else {
			checkCode = checkCodeDao.findCheckCodeByPhoneNum(phoneNum);//������֤���ȡ�û���֤��Ϣ
			
			System.out.println("��ѯ�û�����֤����Ϣcoder��"+checkCode+"\n����ĵ绰="+phoneNum+
					"\n�������֤��="+code);
			
			
			if(checkCode==null){//���û�л�ȡ��֤��
				resultJSON.put("status", false);
				resultJSON.put("msg", "�ú���δ��ȡ��֤��");
				return resultJSON;
			}else if(code.equals(checkCode.getCode()) && phoneNum.equals(checkCode.getId())) {//��֤��͵绰������ȷ�������
				
				System.out.println("���ˡ�����");
				
				long currentime = System.currentTimeMillis();
				long creatime = checkCode.getCreatime().getTime();
				
				if(currentime<creatime+1*60*1000){//�ж���֤���Ƿ���ڣ�5����Ϊ����ʱ��
					try {
						Timestamp u_reatime = new Timestamp(System.currentTimeMillis());
						
						String userUUID = UUIDUtil.creatId();
						user.setToken(CreateIMId.getIMId(userUUID,username));//���û��������token
						user.setUser_id(userUUID);//����������uuid
						user.setUser_nickname(username);//�����������û���
						user.setPhoneNum(phoneNum);//����û��绰����
						user.setCreatime(u_reatime);
						user.setPassword(UUIDUtil.md5(password));//�������� 
						
						userDao.save(user);//����ע���û�
						
						Friends friends = new Friends();
						friends.setUser_id(userUUID);
						friends.setF_user_id(userUUID);
						friends.setF_creatime(u_reatime);
						friendsDao.addFriend(friends);//��Ҫ�ں��ѱ�����ע��һ���Լ����Լ��ĺ��ѣ���������Ȧ��̬��չʾ
						
						checkCodeDao.deleteCoderByPhoneNum(phoneNum);//ע��ɹ���ɾ����֤��
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					System.out.println("ע���û�����Ϣ��"+user);
					
					
					Date date = new Date();
					Map<String , Object> payload=new HashMap<String, Object>();
					payload.put("uid", phoneNum);//�û�ID
					payload.put("iat", date.getTime());//����ʱ��
					payload.put("ext",date.getTime()+1000*60*60);//����ʱ��1Сʱ
					String token=Jwt.createToken(payload);
					
					//��ѯ�ո�ע����û������ظ�app
					User registUser = userDao.findByPhoneNum(phoneNum);
					resultJSON.put("status", true);
					resultJSON.put("msg", "ע��ɹ�");
//					resultJSON.put("msgtoken", token);
//					resultJSON.put("data", registUser);
					return resultJSON;
				}else {//��֤�����
					checkCodeDao.deleteCoderByPhoneNum(phoneNum);//��֤����ھ�ɾ��������֤���¼
					resultJSON.put("status", false);
					resultJSON.put("msg", "��֤��ʧЧ�������»�ȡ��֤��");
				}
			}else {
				resultJSON.put("msg", "ע��ʧ��");
				return resultJSON;
			}
		}
		return resultJSON;
	}
	
	/**
	 * �û��ϴ�ͷ��
	 */
	public void saveProfile(MultipartFile pic, String user_id, 
			String localBasePath, String picName, String deletLocalPath, String suffix){
		if(!pic.isEmpty()){
			try {
				//��ɾ���û�ԭ�е�ͷ��
				//
				//
				User hasUser = userDao.findByUserId(user_id);
				//
				//
				String hasPicName = hasUser.getProfile();
				System.out.println("ԭʼͼƬ��ַ������"+deletLocalPath+hasPicName+suffix);
				File file = new File(deletLocalPath+hasPicName+suffix);
				System.out.println("file="+file);
				if(file.isFile() && file.exists()){//���ͼƬ����
					file.delete();
					System.out.println(user_id+"�û�ԭʼͼƬɾ���ˣ�");
				}
				//�Ƚ�ͼƬ���浽����
				pic.transferTo(new File(localBasePath));
				System.out.println("����ͼƬ�ϴ�������·��="+localBasePath);
				
				User user = new User();
				user.setProfile(picName);
				user.setUser_id(user_id);
				System.out.println("Ҫ�����userͼƬ="+user.toString());
				
				userDao.saveProfile(user);//����ͼƬ�ĵ�ַ�ŵ�user����
			} catch (Exception e) {
				System.out.println("�ϴ�ͷ��󱾵ر���ʧ��!");
			}
		}
	}
	
	/**
	 * ͨ��id�����û�
	 */
	public JSONObject findUserById(String user_id) {
		JSONObject resultJSON=new JSONObject();
		User user = userDao.findById(user_id);
		if(user!=null){
			resultJSON.put("success", true);
			resultJSON.put("msg", "���ҳɹ�");
			resultJSON.put("data", user);
			return resultJSON;
		}
		resultJSON.put("success", true);
		resultJSON.put("msg", "�޸��û�");
		return resultJSON;
	}
	
	/**
	 * ͨ���ǳƲ����û�
	 */
	public JSONObject findUsersByNick(String user_nickname) {
		JSONObject resultJSON=new JSONObject();
		List<User> users = userDao.findByNickName(user_nickname);
		if(users!=null){
				resultJSON.put("success", true);
				resultJSON.put("msg", "��Ѱ�û��ɹ�");
				resultJSON.put("data", users);
				return resultJSON;
		}
		resultJSON.put("success", true);
		resultJSON.put("msg", "�޴������û�");
		return resultJSON;
	}
	
	
	/**
	 * �޸��û�������Ϣ
	 */
	public JSONObject editUser(String user_id, String username, String gender, 
			String birthday, String star, String e_state, String grade, String profession, 
			String school, String major, String highschool, String province, String city, 
			String county, String lable, String skill) {
		
		User user = userDao.findByUserId(user_id);
		User resultUser = new User();
		JSONObject resultJSON=new JSONObject();
		
		resultUser.setUser_id(user_id);
		
		//�����û���Ϣ������Ϊ��
		if ("".equals(username) || username==null) {
			resultUser.setUser_nickname(user.getUser_nickname());
		}else {
			resultUser.setUser_nickname(username);
		}
		
		if("".equals(gender) || gender==null){
			resultUser.setGender(user.getGender());
		}else {
			resultUser.setGender(gender);
		}
		
		if("".equals(birthday) || birthday==null){
			resultUser.setBirthday(user.getBirthday());
		}else {
			resultUser.setBirthday(birthday);
		}
		
		if("".equals(star) || star==null){
			resultUser.setStar(user.getStar());
		}else {
			resultUser.setStar(star);
		}
		
		if("".equals(e_state) || e_state==null){
			resultUser.setE_state(user.getE_state());
		}else {
			resultUser.setE_state(e_state);
		}
		
		if("".equals(grade) || grade==null){
			resultUser.setGrade(user.getGrade());
		}else {
			resultUser.setGrade(grade);
		}
		
		if("".equals(profession) || profession==null){
			resultUser.setProfession(user.getProfession());
		}else {
			resultUser.setProfession(profession);
		}
		
		if("".equals(major) || major==null){
			System.out.println("����majorΪ��");
			resultUser.setMajor(user.getMajor());
		}else {
			System.out.println("����major��Ϊ��="+major);
			resultUser.setMajor(major);
			System.out.println("resultMajor="+resultUser.getMajor());
		}
		
		if("".equals(school) || school==null){
			System.out.println("����schoolΪ��");
			resultUser.setSchool(user.getSchool());
		}else {
			System.out.println("����school��Ϊ��="+school);
			resultUser.setSchool(school);
			System.out.println("resultSchool="+resultUser.getSchool());
		}
		
		if("".equals(highschool) || highschool==null){
			resultUser.setHighschool(user.getHighschool());
		}else {
			resultUser.setHighschool(highschool);
		}
		
		if("".equals(province) || province==null){
			resultUser.setProvince(user.getProvince());
		}else {
			resultUser.setProvince(province);
		}
		
		if("".equals(city) || city==null){
			resultUser.setCity(user.getCity());
		}else {
			resultUser.setCity(city);
		}
		
		if("".equals(county) || county==null){
			resultUser.setCounty(user.getCounty());
		}else {
			resultUser.setCounty(county);
		}
		
		if("".equals(lable) || lable==null){
			resultUser.setLable(user.getLable());
		}else {
			resultUser.setLable(lable);
		}
		
		if("".equals(skill) || skill==null){
			resultUser.setSkill(user.getSkill());;
		}else {
			resultUser.setSkill(skill);
		}
		
		resultUser.setLastmodifytime(new Timestamp(System.currentTimeMillis()));
		//����û��ļ�����Ϣ
		
		System.out.println("������û�="+resultUser);
		userDao.editUser(resultUser);
		
		resultJSON.put("status", true);
		resultJSON.put("data", resultUser);
		resultJSON.put("msg", "�û���Ϣ���³ɹ���");
		return resultJSON;
	}

	/**
	 * ���������û��ļ�����Ϣ
	 */
	public JSONObject editSkills(String user_id, String skills){
		
		Map<String, Object> skillsParam = new HashMap<String, Object>();
		skillsParam.put("user_id", user_id);
		
		return null;
	}
	
	/**
	 * �û��ϴ������֤ͼƬ
	 */
	public JSONObject userVerify(MultipartFile verify, String type, 
			String user_id, String localBasePath, String picName, String suffix){
		JSONObject resultJSON = new JSONObject();
		try {
			verify.transferTo(new File(localBasePath+picName+suffix));//���浽����������
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("user_id", user_id);
			if("stu".equals(type)){//�����ѧ��֤��֤��Ϣ
				params.put("stu_verify", picName+suffix);
				params.put("lastmodifytime", new Timestamp(System.currentTimeMillis()));
				userDao.saveStuVerify(params);
				System.out.println("����stuͼƬ�ϴ�������·��="+localBasePath+picName+suffix);
			}else if ("certi".equals(type)) {//����Ǳ�ҵ֤��֤��Ϣ
				params.put("certi_verify", picName+suffix);
				params.put("lastmodifytime", new Timestamp(System.currentTimeMillis()));
				userDao.saveCertiVerify(params);
				System.out.println("����certiͼƬ�ϴ�������·��="+localBasePath+picName+suffix);
			}
			resultJSON.put("msg", "��֤��Ϣ����ɹ���");
			resultJSON.put("status", true);
		} catch (Exception e) {
			resultJSON.put("msg", "��֤��Ϣ�������������ʧ�ܣ�");
			resultJSON.put("status", false);
		}
		return resultJSON;
	}
	
	/**
	 * ��̨�û���֤�ı���
	 */
	public JSONObject verifyReport(int pageSize, int pageNumber, String hostPath){
		JSONObject resultJSON = new JSONObject();
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("end", end);
		List<User> userVerifies = userDao.queryAdminVerify(params);
		if(userVerifies.size()>0){
			for(User user:userVerifies){
				user.setStu_verify(hostPath+user.getStu_verify());
				user.setCerti_verify(hostPath+user.getCerti_verify());
				try {
					user.setReportLastTime(DateUtil.formatDate(user.getLastmodifytime()));
				} catch (Exception e) {
					System.out.println("��̨�û���֤��˱����ʱ��ת�����󣡣�");
				}
			}
		}
		resultJSON.put("total", userDao.queryCountVerifies());
		resultJSON.put("rows", userVerifies);
		System.out.println("�û���֤����="+userVerifies.toString());
		return resultJSON;
	}
	
	public JSONObject oneUserVerify(int schoolId, String verify, String state){
		JSONObject resultJSON = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("schoolId", schoolId);
		if("stu_state".equals(verify)){
			params.put("stu_state", state);
			userDao.modifyStuAdmin(params);
			resultJSON.put("msg", "�޸ĸ���ѧ��֤��֤�ɹ���");
		}else if ("certi_state".equals(verify)) {
			params.put("certi_state", state);
			userDao.modifyCertiAdmin(params);
			resultJSON.put("msg", "�޸ĸ��˱�ҵ֤֤��֤�ɹ���");
		}
		return resultJSON;
	}
	
	public JSONObject randPeoples(String user_id, int pageSize, int pageNumber, 
			String hostPath01){
		JSONObject resultJSON = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		params.put("user_id", user_id);
		params.put("start", start);
		params.put("end", end);
		List<User> users = userDao.randPeoples(params);
		if(users.size()>0){
			for(User user:users){
				user.setProfile(hostPath01+user.getProfile());
			}
		}
		resultJSON.put("msg", "�˺�����û��б�ɹ�!");
		resultJSON.put("status", true);
		resultJSON.put("data", users);
		return resultJSON;
	}
	
	public JSONObject filterPeoples(String user_id, int pageSize, int pageNumber, String gender, 
			String grade, int age01, int age02, String major, String type, String hostPath01){
		JSONObject resultJSON = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		params.put("user_id", user_id);
		params.put("start", start);
		params.put("end", end);
		params.put("gender", gender);
		params.put("grade", grade);
		params.put("age01", age01);
		params.put("age02", age02);
		params.put("major", major);
		params.put("type", type);
		
		if("".equals(type)){//Ϊ�ս����˺��û���ɸѡ����
			System.out.println("Ϊ�ս����˺��û���ɸѡ����");
			System.out.println(params);
			List<User> users = userDao.filterPeoples(params);
			if(users.size()>0){
				for(User u:users){
					u.setProfile(hostPath01+u.getProfile());
				}
			}
			resultJSON.put("msg", "�˺��û��б�ɸѡ�ɹ�!");
			resultJSON.put("status", true);
			resultJSON.put("data", users);
		}else {//��Ϊ�ս�������Ȧ���û�ɸѡ����
			System.out.println("��Ϊ�ս�������Ȧ���û�ɸѡ����");
			User user = userDao.findByUserId(user_id);
			if("ͬ��ѧ".equals(type)){
				params.put("typeInsert", user.getSchool());
			}
			if ("����".equals(type)) {
				params.put("typeInsert", user.getHome());
			}
			if ("ͬ����".equals(type)) {
				params.put("typeInsert", user.getHighschool());
			}
			System.out.println(params);
			if(params.get("typeInsert") == null){//ֻҪ�û�û�����Ϣ
				resultJSON.put("msg", "��û��������������Ϣ!");
				resultJSON.put("status", false);
			}else {
				List<User> categUser = userDao.queryTypeUser(params);
				if(categUser.size()>0){
					for(User u:categUser){
						u.setProfile(hostPath01+user.getProfile());
					}
				}
				resultJSON.put("msg", "����Ȧ�û�ɸѡ�ɹ�!");
				resultJSON.put("status", true);
				resultJSON.put("data", categUser);
			}
		}
		return resultJSON;
	}
	
	public JSONObject maimaiCateg(String user_id, int pageSize, int pageNumber, 
			String type, String hostPath01){
		JSONObject resultJSON = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		User user = userDao.findByUserId(user_id);
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		params.put("start", start);
		params.put("end", end);
		params.put("user_id", user_id);
		params.put("type", type);
		if("ͬ��ѧ".equals(type)){
			params.put("typeInsert", user.getSchool());
		}
		if ("����".equals(type)) {
			params.put("typeInsert", user.getHome());
		}
		if ("ͬ����".equals(type)) {
			params.put("typeInsert", user.getHighschool());
		}
		if(params.get("typeInsert") == null){//ֻҪ�û�û�����Ϣ
			resultJSON.put("msg", "��û��������������Ϣ!");
			resultJSON.put("status", false);
		}else {
			List<User> categUser = userDao.queryTypeUser(params);
			if(categUser.size()>0){
				for(User u:categUser){
					u.setProfile(hostPath01+user.getProfile());
				}
			}
			resultJSON.put("msg", "����Ȧ�û����سɹ�!");
			resultJSON.put("status", true);
			resultJSON.put("data", categUser);
		}
		return resultJSON;
	}
	
	public JSONObject peoplesSearch(String param, String hostPath01){
		JSONObject resultJSON = new JSONObject();
		try {
			int schoolId = Integer.valueOf(param);
			User user = new User();
			user.setSchoolId(schoolId);
			User user02 = userDao.findBySchoolId(user);
			user02.setProfile(hostPath01+user02.getProfile());
			resultJSON.put("status", true);
			resultJSON.put("data", user02);
			resultJSON.put("msg", "���Ѳ��ҳɹ�");
			return resultJSON;
		} catch (Exception e) {
			System.out.println("�˺�������������������У԰�ţ�ͨ��һ�������ǳƲ��ҡ�������");
			System.out.println(param);
			List<User> users = userDao.findByNickName(param);
			if(users != null){
				for(User u:users){
					u.setProfile(hostPath01+u.getProfile());
				}
				resultJSON.put("status", true);
				resultJSON.put("data", users);
				resultJSON.put("msg", "���Ѳ��ҳɹ�");
			}else {
				resultJSON.put("status", false);
				resultJSON.put("msg", "û���ҵ�����");
			}
			return resultJSON;
		}
	}
	
	public JSONObject maimaiSearch(String param, String hostPath01){
		JSONObject resultJSON = new JSONObject();
		String paramStr = "";
		if(!"".equals(param)){
			paramStr = "%"+param+"%";
		}
		List<User> users = userDao.queryCheckMaimai(paramStr);
		if(users != null){
			for(User u:users){
				u.setProfile(hostPath01+u.getProfile());
			}
			resultJSON.put("status", true);
			resultJSON.put("data", users);
			resultJSON.put("msg", "���Ѳ��ҳɹ�");
		}else {
			resultJSON.put("status", false);
			resultJSON.put("msg", "û���ҵ�����");
		}
		return resultJSON;
	}
	
	public JSONObject userInfo(String user_id, String hostPath01, String hostPath02){
		JSONObject resultJSON = new JSONObject();
		User user = userDao.findByUserId(user_id);
		//����urlͷ��
		user.setProfile(hostPath01+user.getProfile());
		//������4����̬
		List<Active> actives = activeUserDao.queryActForUserInfo(user_id);
		if(actives!=null || actives.size()>0){
			List<String> aa = new ArrayList<String>();
			for(int i=0;i<actives.size();i++){
				String pics = actives.get(i).getActive_pic();
				if(pics!=null && !"".equals(pics)){
					String[] picsName = pics.split(",");
					String resultPicName = "";//url��ַ��ͼƬ
					for(String pic:picsName){
						if(aa.size()<4){//ֻ��4��ͼƬ
							aa.add(resultPicName+hostPath02+pic);
						}
					}
				}
			}
			user.setPics(aa);
		}
		//�����û��ļ��ܣ����漯�����ڷ���ǰ��
		String skill = user.getSkill();
		if(null!=skill && !"".equals(skill)){
			String[] strSkill = skill.split(",");
			List<String> ss = new ArrayList<String>();
			for(String s:strSkill){
				ss.add(s);
			}
			user.setSkills(ss);
		}
		//��ӷÿ�����
		user.setVisit(userDao.totalVisitors(user_id));
		//����ܵ�fans����
		user.setFans(friendsDao.queryTotalFans(user_id));
		//��Ӹ��˶�̬������
		user.setOneActCount(activeUserDao.queryActiveCounts(user_id));
		resultJSON.put("status", true);
		resultJSON.put("data", user);
		return resultJSON;
	}

	public JSONObject visitorList(String user_id, int pageSize, int pageNumber, 
			String hostPath01){
		JSONObject resultJSON = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		params.put("v_user_id", user_id);
		params.put("start", start);
		params.put("end", end);
		List<Visitor> visitors = userDao.queryVisitorList(params);
		if(null==visitors || visitors.size()==0){
			resultJSON.put("status", false);
			resultJSON.put("msg", "û�зÿ�����");
			resultJSON.put("data", visitors);
			resultJSON.put("totalVisitors", 0);
			return resultJSON;
		}else {
			for(Visitor v:visitors){
				User user = userDao.findById(v.getUser_id());
				v.setPrifile(hostPath01+user.getProfile());
				v.setUser_nickname(user.getUser_nickname());
				v.setSchool(user.getSchool());
				v.setMajor(user.getMajor());
				v.setBirthday(user.getBirthday());
			}
			resultJSON.put("status", true);
			resultJSON.put("msg", "�ÿ��û��б��ѯ�ɹ�");
			resultJSON.put("data", visitors);
			resultJSON.put("totalVisitors", userDao.totalVisitors(user_id));
			return resultJSON;
		}
	}
	
	
}
