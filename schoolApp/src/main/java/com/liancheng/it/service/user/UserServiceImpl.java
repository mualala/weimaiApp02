package com.liancheng.it.service.user;


import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.liancheng.it.util.DateUtil;
import com.liancheng.it.util.HttpSenderUtil;
import com.liancheng.it.util.IMManager;
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
import com.liancheng.it.entity.user.SeeControl;
import com.liancheng.it.entity.user.StateSwitch;
import com.liancheng.it.entity.user.User;
import com.liancheng.it.entity.user.UserChart;
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

	public JSONObject checkLogin(String phoneNum, String password, HttpServletRequest request, String hostPath01) {
		JSONObject resultJSON = new JSONObject();
		User hasuser = new User();
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
			}
			if(reqParamLen != 11 && hasuser != null){//���û��11λ��11λ�Ĳ�ѯ��������ͨ��У԰���ٲ�һ��
				hasuser.setSchoolId(Integer.parseInt(phoneNum));
				hasuser = userDao.findBySchoolId(hasuser);
			}
			try {
				if(hasuser!=null && UUIDUtil.md5(password).equals(hasuser.getPassword())){//��¼�����ж�
					
					//���ɶ��ŵ�token
					Map<String , Object> payload=new HashMap<String, Object>();
					Date date=new Date();
					payload.put("uid", phoneNum);//�û�ID
					payload.put("iat", date.getTime());//����ʱ��
					payload.put("ext",date.getTime()+1000*60*60);//����ʱ��1Сʱ
					Jwt.createToken(payload);
					
//					request.getServletContext().setAttribute("aa", hasuser.getUser_id());
					
					if(hasuser.getProfile()==null){
						hasuser.setProfile(hostPath01+"avatar_def.png");//����ͷ���url
					}else {
						hasuser.setProfile(hostPath01+hasuser.getProfile());//����ͷ���url
					}
					resultJSON.put("status", true);
					resultJSON.put("msg", "��½�ɹ�");
					resultJSON.put("data",hasuser);
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
	public JSONObject checkUserRegist(String phoneNum, int codeType) {
		
		Coder coder =  new Coder();
		Coder checkCode = checkCodeDao.findCheckCodeByPhoneNum(phoneNum);
		JSONObject resultJSON=new JSONObject();
		User user = userDao.findByPhoneNum(phoneNum);
		
		if(codeType==1){//��ע�����֤������
			if(user != null){//���ֻ�����ע��
				resultJSON.put("status", false);
				resultJSON.put("msg", "���ֻ�������ע��");
				return resultJSON;
			}else {//�ֻ�����ûע���
				if(checkCode != null){//�жϸ��ֻ��Ƿ��ȡ����֤��
//					Date date=new Date();
					long currentime = System.currentTimeMillis();
					long creatime = checkCodeDao.findCheckCodeByPhoneNum(phoneNum).getCreatime().getTime();
					
					if(currentime<creatime+5*60*1000){//�ж���֤���Ƿ���ڣ�5�����ڲ����ٻ�ȡ��֤��
						resultJSON.put("status", false);
						resultJSON.put("msg", "5�������ѻ�ȡ��֤�룬�����ٻ�ȡ!");
						return resultJSON;
					}else{
						checkCodeDao.deleteCoderByPhoneNum(phoneNum);//5���Ӻ�ɾ������֤��
					}
				}
				String code = RegistCheckCode.getCheckCode();
				String codemsg = "���ã�������֤��Ϊ��"+code+"��Чʱ��5���ӣ��뼰ʱ���ע�ᣡ";
				
				coder.setCode(code);
				coder.setCreatime(new Timestamp(System.currentTimeMillis()));
				coder.setId(phoneNum);
				checkCodeDao.saveCoder(coder);//����һ����֤��
				try {
					HttpSenderUtil.batchSend("http://sapi.253.com/msg/HttpBatchSendSM",
							"qishidianzi", "Qsdz757980", phoneNum, codemsg, true, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				resultJSON.put("status", true);
				resultJSON.put("msg", "������֤��ɹ�");
				resultJSON.put("data", code);
				return resultJSON;
			}
		}else {//�������������֤������
			System.out.println("���������������֤����");
			if(user==null){//���ֻ�û��ע���
				resultJSON.put("status", false);
				resultJSON.put("msg", "�ú��벻��ע������û�");
				return resultJSON;
			}else {
				if(checkCode != null){//�жϸ��ֻ��Ƿ��ȡ����֤��
					long currentime = System.currentTimeMillis();
					long creatime = checkCodeDao.findCheckCodeByPhoneNum(phoneNum).getCreatime().getTime();
					
					if(currentime<creatime+5*60*1000){//�ж���֤���Ƿ���ڣ�5�����ڲ����ٻ�ȡ��֤��
						resultJSON.put("status", false);
						resultJSON.put("msg", "5�������ѻ�ȡ��֤�룬�����ٻ�ȡ!");
						return resultJSON;
					}else{
						checkCodeDao.deleteCoderByPhoneNum(phoneNum);//5���Ӻ�ɾ������֤��
					}
				}
				String code = RegistCheckCode.getCheckCode();
				String codemsg = "���ã�������֤��Ϊ��"+code+"��Чʱ��5���ӣ��뼰ʱ���ע�ᣡ";
				
				coder.setCode(code);
				coder.setCreatime(new Timestamp(System.currentTimeMillis()));
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
				return resultJSON;
			}
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
			resultJSON.put("status", false);
			resultJSON.put("msg", "����Ϊ��,����������!");
			return resultJSON;
		}
		if(code==null || "".equals(code)){//�����֤��Ϊ��
			resultJSON.put("status", false);
			resultJSON.put("msg", "��֤��Ϊ��,��������֤��!");
			return resultJSON;
		}else {
			checkCode = checkCodeDao.findCheckCodeByPhoneNum(phoneNum);//������֤���ȡ�û���֤��Ϣ
			if(checkCode==null){//���û�л�ȡ��֤��
				resultJSON.put("status", false);
				resultJSON.put("msg", "�ú���δ��ȡ��֤��");
				return resultJSON;
			}else if(code.equals(checkCode.getCode()) && phoneNum.equals(checkCode.getId())) {//��֤��͵绰������ȷ�������
				long currentime = System.currentTimeMillis();
				long creatime = checkCode.getCreatime().getTime();
				
				if(currentime<creatime+5*60*1000){//�ж���֤���Ƿ���ڣ�5����Ϊ����ʱ��
					try {
						Timestamp u_reatime = new Timestamp(System.currentTimeMillis());
						
						String userUUID = UUIDUtil.creatId();
						user.setToken(IMManager.getIMUserId(userUUID,username));//���û��������token
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
				File file = new File(deletLocalPath+hasPicName+suffix);
				if(file.isFile() && file.exists()){//���ͼƬ����
					file.delete();
				}
				//�Ƚ�ͼƬ���浽����
				pic.transferTo(new File(localBasePath));
				
				User user = new User();
				user.setProfile(picName+suffix);
				user.setUser_id(user_id);
				
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
		System.out.println("�û��ϴ�������type="+type);
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
			resultJSON.put("msg", "��֤��Ϣ����ɹ�");
			resultJSON.put("status", true);
		} catch (Exception e) {
			resultJSON.put("msg", "��֤��Ϣ�������������ʧ��");
			resultJSON.put("status", false);
		}
		System.out.println(resultJSON);
		return resultJSON;
	}
	
	/**
	 * ��̨�û���֤�ı���
	 */
	public JSONObject verifyReport(int pageSize, int pageNumber, String searchText, String sortName,
			String sortOrder, String startDate, String endDate, String schoolID, String phoneNum, String hostPath){
		JSONObject resultJSON = new JSONObject();
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("end", end);
		if (searchText != null) {
			params.put("searchText", "%"+searchText+"%");
		}else {
			params.put("searchText", searchText);
		}
		params.put("sortName", sortName);
		params.put("sortOrder", sortOrder);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("schoolID", schoolID);
		params.put("phoneNum", phoneNum);
		List<User> userVerifies = userDao.queryAdminVerify(params);
		if(userVerifies.size()>0){
			for(User user:userVerifies){
				user.setStu_verify(hostPath+user.getStu_verify());
				user.setCerti_verify(hostPath+user.getCerti_verify());
				try {
					user.setReportCreaTime(DateUtil.formatDate(user.getCreatime()));
					user.setReportLastTime(DateUtil.formatDate(user.getLastmodifytime()));
				} catch (Exception e) {
					System.out.println("��̨�û���֤��˱����ʱ��ת�����󣡣�");
				}
			}
		}
		resultJSON.put("total", userDao.queryCountVerifies(params));
		resultJSON.put("rows", userVerifies);
		return resultJSON;
	}
	
	public JSONObject oneUserVerify(int schoolId, String verify, String state, String otherState){
		JSONObject resultJSON = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("schoolId", schoolId);
		if("stu_state".equals(verify)){
			params.put("stu_state", state);
			params.put("otherState", otherState);
			System.out.println(params);
			userDao.modifyStuAdmin(params);
			resultJSON.put("msg", "ѧ��֤��˳ɹ�!");
		}else if ("certi_state".equals(verify)) {
			params.put("certi_state", state);
			params.put("otherState", otherState);
			System.out.println(params);
			userDao.modifyCertiAdmin(params);
			resultJSON.put("msg", "��ҵ֤��˳ɹ�!");
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
				if(user.getProfile()==null){
					user.setProfile(hostPath01+"avatar_def.png");
				}else {
					user.setProfile(hostPath01+user.getProfile());
				}
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
					if(u.getProfile()==null){
						u.setProfile(hostPath01+"avatar_def.png");
					}else {
						u.setProfile(hostPath01+u.getProfile());
					}
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
						if(user.getProfile()==null){
							u.setProfile(hostPath01+"avatar_def.png");
						}else {
							u.setProfile(hostPath01+user.getProfile());
						}
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
					if(user.getProfile()==null){
						u.setProfile(hostPath01+"avatar_def.png");
					}else {
						u.setProfile(hostPath01+user.getProfile());
					}
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
			if(user02.getProfile()==null){
				user02.setProfile(hostPath01+"avatar_def.png");
			}else {
				user02.setProfile(hostPath01+user02.getProfile());
			}
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
					if(u.getProfile()==null){
						u.setProfile(hostPath01+"avatar_def.png");
					}else {
						u.setProfile(hostPath01+u.getProfile());
					}
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
				if(u.getProfile()==null){
					u.setProfile(hostPath01+"avatar_def.png");
				}else {
					u.setProfile(hostPath01+u.getProfile());
				}
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
	
	public JSONObject userInfo(String user_id, String other_user_id, String hostPath01, String hostPath02){
		JSONObject resultJSON = new JSONObject();
		User user = userDao.findByUserId(other_user_id);
		//����urlͷ��
		if(user.getProfile()==null || "".equals(user.getProfile())){
			user.setProfile(hostPath01+"avatar_def.png");
		}else {
			user.setProfile(hostPath01+user.getProfile());
		}
		//������4����̬
		List<Active> actives = activeUserDao.queryActForUserInfo(other_user_id);
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
		user.setVisit(userDao.totalVisitors(other_user_id));
		//����ܵ�fans����
		user.setFans(friendsDao.queryTotalFans(other_user_id));
		//��Ӹ��˶�̬������
		user.setOneActCount(activeUserDao.queryActiveCounts(other_user_id));
		//�ж��Ƿ��Ѿ�������
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", user_id);
		params.put("other_user_id", other_user_id);
		if(friendsDao.queryIsFriend(params)==null){
			resultJSON.put("isFriend", false);//û�ӹ�����
		}else {
			resultJSON.put("isFriend", true);
		}
		//�Ƿ����
		resultJSON.put("status", true);
		resultJSON.put("data", user);
		System.out.println("������Ϣչʾ="+user);
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
				if(user.getProfile()==null){
					v.setPrifile(hostPath01+"avatar_def.png");
				}else {
					v.setPrifile(hostPath01+user.getProfile());
				}
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
	
	public JSONObject userReport(int pageSize, int pageNumber, String searchText, String verifyState, 
			String school, String gender, String profession, String startDate, String endDate, 
			String sortName, String sortOrder, String schoolID, String hostPath01, String hostPath02){
		JSONObject resultJSON = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		params.put("start", start);
		params.put("end", end);
		if (searchText != null) {
			params.put("searchText", "%"+searchText+"%");
		}else {
			params.put("searchText", searchText);
		}
		if ("1".equals(verifyState) || "0".equals(verifyState)) {
			params.put("verifyState", verifyState);
		}else {
			params.put("verifyState", 2);
		}
		params.put("school", school);
		params.put("gender", gender);
		params.put("profession", profession);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("sortName", sortName);
		params.put("sortOrder", sortOrder);
		params.put("schoolID", schoolID);
		System.out.println(params);
		List<User> userList = userDao.queryUserList(params);
		if(userList!=null || userList.size()>0){
			for(User u:userList){
				if(u.getProfile()==null){
					u.setProfile(hostPath01+"avatar_def.png");
				}else {
					u.setProfile(hostPath01+u.getProfile());
				}
				u.setStu_verify(hostPath02+u.getStu_verify());
				u.setCerti_verify(hostPath02+u.getCerti_verify());
				try {
					if(u.getCreatime()!=null){
						u.setReportCreaTime(DateUtil.formatDate(u.getCreatime()));
					}
					if(u.getLastmodifytime()!=null){
						u.setReportLastTime(DateUtil.formatDate(u.getLastmodifytime()));
					}
				} catch (Exception e) {
					System.out.println("��̨�û�����ʱ��ת������");
					resultJSON.put("status", false);
					resultJSON.put("msg", "��̨�û�����ʱ��ת������");
				}
			}
		}
		resultJSON.put("status", true);
		resultJSON.put("rows", userList);
		resultJSON.put("total", userDao.totalUserCount(params));
		return resultJSON; 
	}
	
	public JSONObject checkUserVrify(String user_id){
		JSONObject resultJSON = new JSONObject();
		User user = userDao.findByUserId(user_id);
		if("1".equals(user.getVerify_state())){//�������֤�û�����codeΪ1
			resultJSON.put("status", true);
			resultJSON.put("code", 1);
			resultJSON.put("msg", "�Ѿ�����֤�û�");
			return resultJSON;
		}else if ("0".equals(user.getVerify_state()) && 
				(user.getStu_verify()!=null || user.getCerti_verify()!=null)) {
			resultJSON.put("status", true);
			resultJSON.put("code", 2);
			resultJSON.put("msg", "���������");
			return resultJSON;
		}else if(user.getStu_verify()==null && user.getCerti_verify()==null) {
			resultJSON.put("status", true);
			resultJSON.put("code", 3);
			resultJSON.put("msg", "δ�ύ�κ�����");
			return resultJSON;
		}
		return resultJSON;
	}
	
	public JSONObject editPassword(String phoneNum, String code, String password){
		JSONObject resultJSON = new JSONObject();
		Coder checkCode = new Coder();
		if(password==null || "".equals(password)){//�������Ϊ��
			resultJSON.put("status", false);
			resultJSON.put("msg", "����Ϊ��,����������!");
			return resultJSON;
		}
		if(code==null || "".equals(code)){//�����֤��Ϊ��
			resultJSON.put("status", false);
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
				
				long currentime = System.currentTimeMillis();
				long creatime = checkCode.getCreatime().getTime();
				
				if(currentime<creatime+1*60*1000){//�ж���֤���Ƿ���ڣ�5����Ϊ����ʱ��
					try {
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("phoneNum", phoneNum);
						params.put("password", UUIDUtil.md5(password));
						
						userDao.ModifyPassword(params);//����ע���û�
						checkCodeDao.deleteCoderByPhoneNum(phoneNum);//ע��ɹ���ɾ����֤��
					} catch (Exception e) {
						System.out.println("��������ʱmd5����ʧ��");
					}
					resultJSON.put("status", true);
					resultJSON.put("msg", "��������ɹ�");
					return resultJSON;
				}else {//��֤�����
					checkCodeDao.deleteCoderByPhoneNum(phoneNum);//��֤����ھ�ɾ��������֤���¼
					resultJSON.put("status", false);
					resultJSON.put("msg", "��֤��ʧЧ�������»�ȡ��֤��");
				}
			}else {
				resultJSON.put("status", false);
				resultJSON.put("msg", "��������ʧ��");
				return resultJSON;
			}
		}
		return resultJSON;
	}
	
	public JSONObject seeState(String user_id, String f_user_id){
		JSONObject resultJSON = new JSONObject();
		StateSwitch stateSwitch = new StateSwitch();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", user_id);
		params.put("other_user_id", f_user_id);
		Friends friends = friendsDao.queryIsAttention(params);
		if(friends==null){
			stateSwitch.setAttention(0);
		}else {
			stateSwitch.setAttention(1);
		}
		List<SeeControl> seeControl =userDao.querySeeControlState(params);
		if(seeControl.size()>0){
			for(SeeControl sc:seeControl){
				if("1".equals(sc.getType())){
					if(sc.getState()==0){
						stateSwitch.setNo_other_see(0);
					}else {
						stateSwitch.setNo_other_see(1);
					}
				}else {
					if(sc.getState()==0){
						stateSwitch.setNo_other_see(0);
					}else {
						stateSwitch.setNo_other_see(1);
					}
				}
			}
		}else {
			stateSwitch.setNo_other_see(0);
			stateSwitch.setNo_see_other(0);
		}
		resultJSON.put("status", true); 
		resultJSON.put("data", stateSwitch);
		return resultJSON;
	}
	
	public JSONObject getHomeSelectorVal(){
		JSONObject resultJSON = new JSONObject();
		List<UserChart> userChart = userDao.queryChartControlYM();
		List<String> chartYM = new ArrayList<String>();
		if(userChart != null){
			for(UserChart u:userChart){
				chartYM.add(u.getYm());
			}
		}
		resultJSON.put("data", chartYM);
		resultJSON.put("allNetPeople", userDao.queryAllNetPeople());
		resultJSON.put("allNetActive", activeUserDao.queryAllNetActive());
		return resultJSON;
	}
	
	public JSONObject getHomeChartData(String selectDate){
		JSONObject resultJSON = new JSONObject();
		List<UserChart> chartData = userDao.queryChartData(selectDate);
		List<Integer> series = new ArrayList<Integer>();
		
		Map<String, Object> map = new TreeMap<String, Object>(new Comparator<String>() {
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		try {
			if(chartData != null){
				//��ȫ������,ֵΪ0
				//ת������
				int maxDay = DateUtil.maxOfMonth(selectDate+"-01");
				for(int i=1;i<maxDay+1;i++){
					if(i<10){
						map.put(String.valueOf(DateUtil.parse((selectDate+"-0"+i)+" 00:00:00").getTime()), 0);
					}else {
						map.put(String.valueOf(DateUtil.parse((selectDate+"-"+i)+" 00:00:00").getTime()), 0);
					}
				}
				//�е�����,�����ǲ�Ϊ0������
				for(UserChart uc:chartData){
					map.put(String.valueOf(DateUtil.parse(uc.getYmd()+" 00:00:00").getTime()), uc.getCountAddUser());
				}
				//������͵�list
				map.entrySet();
				for(Map.Entry<String, Object> entry:map.entrySet()){
					Integer value = (Integer)entry.getValue();
					series.add(value);
				}
			}
		} catch (Exception e) {
			System.out.println("����ת���쳣");
		}
		resultJSON.put("series", series);
		return resultJSON;
	}
	
	public JSONObject controlSendMsg(String user_id, int send_msg){
		JSONObject resultJSON = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", user_id);
		params.put("send_msg", send_msg);
		if(send_msg==1){
			userDao.modifySendMsg(params);
			resultJSON.put("msg", "��������Ϣ");
			resultJSON.put("status", true);
			return resultJSON;
		}else {
			userDao.modifySendMsg(params);
			resultJSON.put("msg", "����������Ϣ");
			resultJSON.put("status", true);
			return resultJSON;
		}
	}
	
	public JSONObject addFriendIsVerify(String user_id, int switchVal){
		JSONObject resultJSON = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", user_id);
		params.put("add_switch", switchVal);
		if(switchVal==1){
			userDao.modifyAddSwitch(params);
			resultJSON.put("status", true);
			resultJSON.put("msg", "����Ϊ����ʱ��Ҫ��֤");
			return resultJSON;
		}else {
			userDao.modifyAddSwitch(params);
			resultJSON.put("status", true);
			resultJSON.put("msg", "����Ϊ����ʱ����Ҫ��֤");
			return resultJSON;
		}
	}
	
	public JSONObject taNoSeeOwnActive(String user_id, String other_user_id, String type, int state){
		JSONObject resultJSON = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", user_id);
		params.put("other_user_id", other_user_id);
		params.put("type", type);
		params.put("state", state);
		params.put("see_creatime", new Timestamp(System.currentTimeMillis()));
		if("1".equals(type) && state==0){
			userDao.saveTaNoSeeOwnActive(params);
			resultJSON.put("status", true);
			resultJSON.put("msg", "��TA���ҵĶ�̬");
			return resultJSON;
		}else if ("1".equals(type) && state==1) {
			userDao.saveTaNoSeeOwnActive(params);
			resultJSON.put("status", true);
			resultJSON.put("msg", "����TA���ҵĶ�̬");
			return resultJSON;
		}else if ("2".equals(type) && state==0) {
			userDao.saveTaNoSeeOwnActive(params);
			resultJSON.put("status", true);
			resultJSON.put("msg", "����TA�Ķ�̬");
			return resultJSON;
		}else if ("2".equals(type) && state==1) {
			userDao.saveTaNoSeeOwnActive(params);
			resultJSON.put("status", true);
			resultJSON.put("msg", "��TA�Ķ�̬");
			return resultJSON;
		}
		return resultJSON;
	}
	
	public JSONObject lifeSeeControl(String user_id, int switchVal){
		JSONObject resultJSON = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", user_id);
		params.put("life_see", switchVal);
		if(switchVal==0){
			userDao.modifyLifeSee(params);
			resultJSON.put("status", true);
			resultJSON.put("msg", "����Ȧ��̬��˿���ɼ�");
			return resultJSON;
		}else {
			userDao.modifyLifeSee(params);
			resultJSON.put("status", true);
			resultJSON.put("msg", "����Ȧ��̬��˿�ɼ�");
			return resultJSON;
		}
	}
	
	
	
}
