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
 * 用户登录业务
 */

@Service("userService")//扫描service
@Aspect
@Transactional
public class UserServiceImpl implements UserService {
	@Resource(name="userDao")//注入userDao
	private UserDao userDao;
	@Resource(name="checkCodeDao")//注入checkCodeDao
	private CheckCodeDao checkCodeDao;
	@Resource(name="friendsDao")
	private FriendsDao friendsDao;
	@Resource(name="activeUserDao")
	private ActiveUserDao activeUserDao;

	public JSONObject checkLogin(String phoneNum, String password, HttpServletRequest request, String hostPath01) {
		JSONObject resultJSON = new JSONObject();
		User hasuser = new User();
		if(phoneNum==null || "".equals(phoneNum)){
			resultJSON.put("msg", "账号为空，请输入正确的账号!");
			resultJSON.put("satuts", false);
			return resultJSON;
		}else if (password==null || "".equals(password)) {
			resultJSON.put("msg", "密码为空，请输入密码!");
			resultJSON.put("status", false);
			return resultJSON;
		}else {//电话号码不为空的情况下
			System.out.println("电话号码部位空="+phoneNum);
			int reqParamLen = phoneNum.length();
			if(reqParamLen==11){//如果有11位通过电话号码查询
				hasuser = userDao.findByPhoneNum(phoneNum);
			}
			if(reqParamLen != 11 && hasuser != null){//如果没有11位或11位的查询不出来再通过校园号再查一次
				hasuser.setSchoolId(Integer.parseInt(phoneNum));
				hasuser = userDao.findBySchoolId(hasuser);
			}
			try {
				if(hasuser!=null && UUIDUtil.md5(password).equals(hasuser.getPassword())){//登录条件判断
					
					//生成短信的token
					Map<String , Object> payload=new HashMap<String, Object>();
					Date date=new Date();
					payload.put("uid", phoneNum);//用户ID
					payload.put("iat", date.getTime());//生成时间
					payload.put("ext",date.getTime()+1000*60*60);//过期时间1小时
					Jwt.createToken(payload);
					
//					request.getServletContext().setAttribute("aa", hasuser.getUser_id());
					
					if(hasuser.getProfile()==null){
						hasuser.setProfile(hostPath01+"avatar_def.png");//设置头像的url
					}else {
						hasuser.setProfile(hostPath01+hasuser.getProfile());//设置头像的url
					}
					resultJSON.put("status", true);
					resultJSON.put("msg", "登陆成功");
					resultJSON.put("data",hasuser);
					return resultJSON;
				}else{
					resultJSON.put("status", false);
					resultJSON.put("msg", "用户名或密码不对");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return resultJSON;//返回json字符串
		}		
	}
	
	
	/**
	 * 生成验证码并发送验证码业务
	 */
	public JSONObject checkUserRegist(String phoneNum, int codeType) {
		
		Coder coder =  new Coder();
		Coder checkCode = checkCodeDao.findCheckCodeByPhoneNum(phoneNum);
		JSONObject resultJSON=new JSONObject();
		User user = userDao.findByPhoneNum(phoneNum);
		
		if(codeType==1){//是注册的验证码请求
			if(user != null){//该手机号已注册
				resultJSON.put("status", false);
				resultJSON.put("msg", "该手机号码已注册");
				return resultJSON;
			}else {//手机号码没注册过
				if(checkCode != null){//判断该手机是否获取过验证码
//					Date date=new Date();
					long currentime = System.currentTimeMillis();
					long creatime = checkCodeDao.findCheckCodeByPhoneNum(phoneNum).getCreatime().getTime();
					
					if(currentime<creatime+5*60*1000){//判断验证码是否过期，5分钟内不能再获取验证码
						resultJSON.put("status", false);
						resultJSON.put("msg", "5分钟内已获取验证码，不能再获取!");
						return resultJSON;
					}else{
						checkCodeDao.deleteCoderByPhoneNum(phoneNum);//5分钟后删除该验证码
					}
				}
				String code = RegistCheckCode.getCheckCode();
				String codemsg = "您好！您的验证码为："+code+"有效时间5分钟，请及时完成注册！";
				
				coder.setCode(code);
				coder.setCreatime(new Timestamp(System.currentTimeMillis()));
				coder.setId(phoneNum);
				checkCodeDao.saveCoder(coder);//保存一次验证码
				try {
					HttpSenderUtil.batchSend("http://sapi.253.com/msg/HttpBatchSendSM",
							"qishidianzi", "Qsdz757980", phoneNum, codemsg, true, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				resultJSON.put("status", true);
				resultJSON.put("msg", "发送验证码成功");
				resultJSON.put("data", code);
				return resultJSON;
			}
		}else {//是重置密码的验证码请求
			System.out.println("进了重置密码的验证请求");
			if(user==null){//该手机没有注册过
				resultJSON.put("status", false);
				resultJSON.put("msg", "该号码不是注册过的用户");
				return resultJSON;
			}else {
				if(checkCode != null){//判断该手机是否获取过验证码
					long currentime = System.currentTimeMillis();
					long creatime = checkCodeDao.findCheckCodeByPhoneNum(phoneNum).getCreatime().getTime();
					
					if(currentime<creatime+5*60*1000){//判断验证码是否过期，5分钟内不能再获取验证码
						resultJSON.put("status", false);
						resultJSON.put("msg", "5分钟内已获取验证码，不能再获取!");
						return resultJSON;
					}else{
						checkCodeDao.deleteCoderByPhoneNum(phoneNum);//5分钟后删除该验证码
					}
				}
				String code = RegistCheckCode.getCheckCode();
				String codemsg = "您好！您的验证码为："+code+"有效时间5分钟，请及时完成注册！";
				
				coder.setCode(code);
				coder.setCreatime(new Timestamp(System.currentTimeMillis()));
				coder.setId(phoneNum);
				checkCodeDao.saveCoder(coder);//保存一次验证码
				System.out.println("保存了验证码："+ coder.toString());
				try {
					HttpSenderUtil.batchSend("http://sapi.253.com/msg/HttpBatchSendSM",
							"qishidianzi", "Qsdz757980", phoneNum, codemsg, true, null);
				} catch (Exception e) {
					e.printStackTrace();
				}
				resultJSON.put("status", true);
				resultJSON.put("msg", "发送验证码成功");
				resultJSON.put("data", code);
				return resultJSON;
			}
		}
	}
	
	/**
	 * 注册业务
	 */
	public JSONObject addUser(String phoneNum, String password, String code, String username) {
		JSONObject resultJSON=new JSONObject();
		User user = new User();
		Coder checkCode = new Coder();
		
		if(password==null || "".equals(password)){//密码如果为空
			resultJSON.put("status", false);
			resultJSON.put("msg", "密码为空,请输入密码!");
			return resultJSON;
		}
		if(code==null || "".equals(code)){//如果验证码为空
			resultJSON.put("status", false);
			resultJSON.put("msg", "验证码为空,请输入验证码!");
			return resultJSON;
		}else {
			checkCode = checkCodeDao.findCheckCodeByPhoneNum(phoneNum);//根据验证码获取用户验证信息
			if(checkCode==null){//如果没有获取验证码
				resultJSON.put("status", false);
				resultJSON.put("msg", "该号码未获取验证码");
				return resultJSON;
			}else if(code.equals(checkCode.getCode()) && phoneNum.equals(checkCode.getId())) {//验证码和电话号码正确的情况下
				long currentime = System.currentTimeMillis();
				long creatime = checkCode.getCreatime().getTime();
				
				if(currentime<creatime+5*60*1000){//判断验证码是否过期，5分钟为过期时间
					try {
						Timestamp u_reatime = new Timestamp(System.currentTimeMillis());
						
						String userUUID = UUIDUtil.creatId();
						user.setToken(IMManager.getIMUserId(userUUID,username));//给用户添加云信token
						user.setUser_id(userUUID);//可以先生成uuid
						user.setUser_nickname(username);//可以先设置用户名
						user.setPhoneNum(phoneNum);//添加用户电话号码
						user.setCreatime(u_reatime);
						user.setPassword(UUIDUtil.md5(password));//设置密码 
						
						userDao.save(user);//保存注册用户
						
						Friends friends = new Friends();
						friends.setUser_id(userUUID);
						friends.setF_user_id(userUUID);
						friends.setF_creatime(u_reatime);
						friendsDao.addFriend(friends);//需要在好友表里面注册一个自己是自己的好友，用于朋友圈动态的展示
						
						checkCodeDao.deleteCoderByPhoneNum(phoneNum);//注册成功就删除验证码
					} catch (Exception e) {
						e.printStackTrace();
					}
					Date date = new Date();
					Map<String , Object> payload=new HashMap<String, Object>();
					payload.put("uid", phoneNum);//用户ID
					payload.put("iat", date.getTime());//生成时间
					payload.put("ext",date.getTime()+1000*60*60);//过期时间1小时
					String token=Jwt.createToken(payload);
					
					//查询刚刚注册的用户并返回给app
					User registUser = userDao.findByPhoneNum(phoneNum);
					resultJSON.put("status", true);
					resultJSON.put("msg", "注册成功");
//					resultJSON.put("msgtoken", token);
//					resultJSON.put("data", registUser);
					return resultJSON;
				}else {//验证码过期
					checkCodeDao.deleteCoderByPhoneNum(phoneNum);//验证码过期就删除该条验证码记录
					resultJSON.put("status", false);
					resultJSON.put("msg", "验证码失效，请重新获取验证码");
				}
			}else {
				resultJSON.put("msg", "注册失败");
				return resultJSON;
			}
		}
		return resultJSON;
	}
	
	/**
	 * 用户上传头像
	 */
	public void saveProfile(MultipartFile pic, String user_id, 
			String localBasePath, String picName, String deletLocalPath, String suffix){
		if(!pic.isEmpty()){
			try {
				//先删除用户原有的头像
				//
				//
				User hasUser = userDao.findByUserId(user_id);
				//
				//
				String hasPicName = hasUser.getProfile();
				File file = new File(deletLocalPath+hasPicName+suffix);
				if(file.isFile() && file.exists()){//如果图片存在
					file.delete();
				}
				//先将图片保存到本地
				pic.transferTo(new File(localBasePath));
				
				User user = new User();
				user.setProfile(picName+suffix);
				user.setUser_id(user_id);
				
				userDao.saveProfile(user);//并将图片的地址放到user表中
			} catch (Exception e) {
				System.out.println("上传头像后本地保存失败!");
			}
		}
	}
	
	/**
	 * 通过id查找用户
	 */
	public JSONObject findUserById(String user_id) {
		JSONObject resultJSON=new JSONObject();
		User user = userDao.findById(user_id);
		if(user!=null){
			resultJSON.put("success", true);
			resultJSON.put("msg", "查找成功");
			resultJSON.put("data", user);
			return resultJSON;
		}
		resultJSON.put("success", true);
		resultJSON.put("msg", "无该用户");
		return resultJSON;
	}
	
	/**
	 * 通过昵称查找用户
	 */
	public JSONObject findUsersByNick(String user_nickname) {
		JSONObject resultJSON=new JSONObject();
		List<User> users = userDao.findByNickName(user_nickname);
		if(users!=null){
				resultJSON.put("success", true);
				resultJSON.put("msg", "查寻用户成功");
				resultJSON.put("data", users);
				return resultJSON;
		}
		resultJSON.put("success", true);
		resultJSON.put("msg", "无此类型用户");
		return resultJSON;
	}
	
	
	/**
	 * 修改用户基本信息
	 */
	public JSONObject editUser(String user_id, String username, String gender, 
			String birthday, String star, String e_state, String grade, String profession, 
			String school, String major, String highschool, String province, String city, 
			String county, String lable, String skill) {
		
		User user = userDao.findByUserId(user_id);
		User resultUser = new User();
		JSONObject resultJSON=new JSONObject();
		
		resultUser.setUser_id(user_id);
		
		//避免用户信息被覆盖为空
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
			System.out.println("走了major为空");
			resultUser.setMajor(user.getMajor());
		}else {
			System.out.println("走了major不为空="+major);
			resultUser.setMajor(major);
			System.out.println("resultMajor="+resultUser.getMajor());
		}
		
		if("".equals(school) || school==null){
			System.out.println("走了school为空");
			resultUser.setSchool(user.getSchool());
		}else {
			System.out.println("走了school不为空="+school);
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
		//添加用户的技能信息
		
		System.out.println("保存的用户="+resultUser);
		userDao.editUser(resultUser);
		
		resultJSON.put("status", true);
		resultJSON.put("data", resultUser);
		resultJSON.put("msg", "用户信息更新成功！");
		return resultJSON;
	}

	/**
	 * 单独更新用户的技能信息
	 */
	public JSONObject editSkills(String user_id, String skills){
		
		Map<String, Object> skillsParam = new HashMap<String, Object>();
		skillsParam.put("user_id", user_id);
		
		return null;
	}
	
	/**
	 * 用户上传身份验证图片
	 */
	public JSONObject userVerify(MultipartFile verify, String type, 
			String user_id, String localBasePath, String picName, String suffix){
		JSONObject resultJSON = new JSONObject();
		System.out.println("用户上传的类型type="+type);
		try {
			verify.transferTo(new File(localBasePath+picName+suffix));//保存到服务器本地
			
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("user_id", user_id);
			if("stu".equals(type)){//如果是学生证验证信息
				params.put("stu_verify", picName+suffix);
				params.put("lastmodifytime", new Timestamp(System.currentTimeMillis()));
				userDao.saveStuVerify(params);
				System.out.println("进了stu图片上传，本地路径="+localBasePath+picName+suffix);
			}else if ("certi".equals(type)) {//如果是毕业证验证信息
				params.put("certi_verify", picName+suffix);
				params.put("lastmodifytime", new Timestamp(System.currentTimeMillis()));
				userDao.saveCertiVerify(params);
				System.out.println("进了certi图片上传，本地路径="+localBasePath+picName+suffix);
			}
			resultJSON.put("msg", "验证信息保存成功");
			resultJSON.put("status", true);
		} catch (Exception e) {
			resultJSON.put("msg", "验证信息保存服务器本地失败");
			resultJSON.put("status", false);
		}
		System.out.println(resultJSON);
		return resultJSON;
	}
	
	/**
	 * 后台用户验证的报表
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
					System.out.println("后台用户认证审核报表的时间转换错误！！");
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
			resultJSON.put("msg", "学生证审核成功!");
		}else if ("certi_state".equals(verify)) {
			params.put("certi_state", state);
			params.put("otherState", otherState);
			System.out.println(params);
			userDao.modifyCertiAdmin(params);
			resultJSON.put("msg", "毕业证审核成功!");
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
		resultJSON.put("msg", "人海随机用户列表成功!");
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
		
		if("".equals(type)){//为空进入人海用户的筛选条件
			System.out.println("为空进入人海用户的筛选条件");
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
			resultJSON.put("msg", "人海用户列表筛选成功!");
			resultJSON.put("status", true);
			resultJSON.put("data", users);
		}else {//不为空进入脉脉圈的用户筛选条件
			System.out.println("不为空进入脉脉圈的用户筛选条件");
			User user = userDao.findByUserId(user_id);
			if("同大学".equals(type)){
				params.put("typeInsert", user.getSchool());
			}
			if ("老乡".equals(type)) {
				params.put("typeInsert", user.getHome());
			}
			if ("同高中".equals(type)) {
				params.put("typeInsert", user.getHighschool());
			}
			System.out.println(params);
			if(params.get("typeInsert") == null){//只要用户没相关信息
				resultJSON.put("msg", "您没有添加完自身的信息!");
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
				resultJSON.put("msg", "脉脉圈用户筛选成功!");
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
		if("同大学".equals(type)){
			params.put("typeInsert", user.getSchool());
		}
		if ("老乡".equals(type)) {
			params.put("typeInsert", user.getHome());
		}
		if ("同高中".equals(type)) {
			params.put("typeInsert", user.getHighschool());
		}
		if(params.get("typeInsert") == null){//只要用户没相关信息
			resultJSON.put("msg", "您没有添加完自身的信息!");
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
			resultJSON.put("msg", "脉脉圈用户加载成功!");
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
			resultJSON.put("msg", "朋友查找成功");
			return resultJSON;
		} catch (Exception e) {
			System.out.println("人海的朋友搜索条件不是校园号，通过一场进入昵称查找。。。。");
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
				resultJSON.put("msg", "朋友查找成功");
			}else {
				resultJSON.put("status", false);
				resultJSON.put("msg", "没有找到朋友");
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
			resultJSON.put("msg", "朋友查找成功");
		}else {
			resultJSON.put("status", false);
			resultJSON.put("msg", "没有找到朋友");
		}
		return resultJSON;
	}
	
	public JSONObject userInfo(String user_id, String other_user_id, String hostPath01, String hostPath02){
		JSONObject resultJSON = new JSONObject();
		User user = userDao.findByUserId(other_user_id);
		//设置url头像
		if(user.getProfile()==null || "".equals(user.getProfile())){
			user.setProfile(hostPath01+"avatar_def.png");
		}else {
			user.setProfile(hostPath01+user.getProfile());
		}
		//添加最近4条动态
		List<Active> actives = activeUserDao.queryActForUserInfo(other_user_id);
		if(actives!=null || actives.size()>0){
			List<String> aa = new ArrayList<String>();
			for(int i=0;i<actives.size();i++){
				String pics = actives.get(i).getActive_pic();
				if(pics!=null && !"".equals(pics)){
					String[] picsName = pics.split(",");
					String resultPicName = "";//url地址的图片
					for(String pic:picsName){
						if(aa.size()<4){//只放4张图片
							aa.add(resultPicName+hostPath02+pic);
						}
					}
				}
			}
			user.setPics(aa);
		}
		//解析用户的技能，并存集合用于返回前端
		String skill = user.getSkill();
		if(null!=skill && !"".equals(skill)){
			String[] strSkill = skill.split(",");
			List<String> ss = new ArrayList<String>();
			for(String s:strSkill){
				ss.add(s);
			}
			user.setSkills(ss);
		}
		//添加访客数量
		user.setVisit(userDao.totalVisitors(other_user_id));
		//添加总的fans数量
		user.setFans(friendsDao.queryTotalFans(other_user_id));
		//添加个人动态总数量
		user.setOneActCount(activeUserDao.queryActiveCounts(other_user_id));
		//判断是否已经是朋友
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", user_id);
		params.put("other_user_id", other_user_id);
		if(friendsDao.queryIsFriend(params)==null){
			resultJSON.put("isFriend", false);//没加过朋友
		}else {
			resultJSON.put("isFriend", true);
		}
		//是否可以
		resultJSON.put("status", true);
		resultJSON.put("data", user);
		System.out.println("个人信息展示="+user);
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
			resultJSON.put("msg", "没有访客数据");
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
			resultJSON.put("msg", "访客用户列表查询成功");
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
					System.out.println("后台用户报表时间转换错误");
					resultJSON.put("status", false);
					resultJSON.put("msg", "后台用户报表时间转换错误");
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
		if("1".equals(user.getVerify_state())){//如果是认证用户返回code为1
			resultJSON.put("status", true);
			resultJSON.put("code", 1);
			resultJSON.put("msg", "已经是认证用户");
			return resultJSON;
		}else if ("0".equals(user.getVerify_state()) && 
				(user.getStu_verify()!=null || user.getCerti_verify()!=null)) {
			resultJSON.put("status", true);
			resultJSON.put("code", 2);
			resultJSON.put("msg", "资料审核中");
			return resultJSON;
		}else if(user.getStu_verify()==null && user.getCerti_verify()==null) {
			resultJSON.put("status", true);
			resultJSON.put("code", 3);
			resultJSON.put("msg", "未提交任何资料");
			return resultJSON;
		}
		return resultJSON;
	}
	
	public JSONObject editPassword(String phoneNum, String code, String password){
		JSONObject resultJSON = new JSONObject();
		Coder checkCode = new Coder();
		if(password==null || "".equals(password)){//密码如果为空
			resultJSON.put("status", false);
			resultJSON.put("msg", "密码为空,请输入密码!");
			return resultJSON;
		}
		if(code==null || "".equals(code)){//如果验证码为空
			resultJSON.put("status", false);
			resultJSON.put("msg", "验证码为空,请输入验证码!");
			return resultJSON;
		}else {
			checkCode = checkCodeDao.findCheckCodeByPhoneNum(phoneNum);//根据验证码获取用户验证信息
			
			System.out.println("查询用户的验证码信息coder："+checkCode+"\n请求的电话="+phoneNum+
					"\n请求的验证码="+code);
			if(checkCode==null){//如果没有获取验证码
				resultJSON.put("status", false);
				resultJSON.put("msg", "该号码未获取验证码");
				return resultJSON;
			}else if(code.equals(checkCode.getCode()) && phoneNum.equals(checkCode.getId())) {//验证码和电话号码正确的情况下
				
				long currentime = System.currentTimeMillis();
				long creatime = checkCode.getCreatime().getTime();
				
				if(currentime<creatime+1*60*1000){//判断验证码是否过期，5分钟为过期时间
					try {
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("phoneNum", phoneNum);
						params.put("password", UUIDUtil.md5(password));
						
						userDao.ModifyPassword(params);//保存注册用户
						checkCodeDao.deleteCoderByPhoneNum(phoneNum);//注册成功就删除验证码
					} catch (Exception e) {
						System.out.println("重置密码时md5加密失败");
					}
					resultJSON.put("status", true);
					resultJSON.put("msg", "重置密码成功");
					return resultJSON;
				}else {//验证码过期
					checkCodeDao.deleteCoderByPhoneNum(phoneNum);//验证码过期就删除该条验证码记录
					resultJSON.put("status", false);
					resultJSON.put("msg", "验证码失效，请重新获取验证码");
				}
			}else {
				resultJSON.put("status", false);
				resultJSON.put("msg", "重置密码失败");
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
				//补全的日期,值为0
				//转换日期
				int maxDay = DateUtil.maxOfMonth(selectDate+"-01");
				for(int i=1;i<maxDay+1;i++){
					if(i<10){
						map.put(String.valueOf(DateUtil.parse((selectDate+"-0"+i)+" 00:00:00").getTime()), 0);
					}else {
						map.put(String.valueOf(DateUtil.parse((selectDate+"-"+i)+" 00:00:00").getTime()), 0);
					}
				}
				//有的日期,并覆盖补为0的日期
				for(UserChart uc:chartData){
					map.put(String.valueOf(DateUtil.parse(uc.getYmd()+" 00:00:00").getTime()), uc.getCountAddUser());
				}
				//结果推送到list
				map.entrySet();
				for(Map.Entry<String, Object> entry:map.entrySet()){
					Integer value = (Integer)entry.getValue();
					series.add(value);
				}
			}
		} catch (Exception e) {
			System.out.println("日期转换异常");
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
			resultJSON.put("msg", "允许发送消息");
			resultJSON.put("status", true);
			return resultJSON;
		}else {
			userDao.modifySendMsg(params);
			resultJSON.put("msg", "不允许发送消息");
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
			resultJSON.put("msg", "加我为好友时需要验证");
			return resultJSON;
		}else {
			userDao.modifyAddSwitch(params);
			resultJSON.put("status", true);
			resultJSON.put("msg", "加我为好友时不需要验证");
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
			resultJSON.put("msg", "让TA看我的动态");
			return resultJSON;
		}else if ("1".equals(type) && state==1) {
			userDao.saveTaNoSeeOwnActive(params);
			resultJSON.put("status", true);
			resultJSON.put("msg", "不让TA看我的动态");
			return resultJSON;
		}else if ("2".equals(type) && state==0) {
			userDao.saveTaNoSeeOwnActive(params);
			resultJSON.put("status", true);
			resultJSON.put("msg", "不看TA的动态");
			return resultJSON;
		}else if ("2".equals(type) && state==1) {
			userDao.saveTaNoSeeOwnActive(params);
			resultJSON.put("status", true);
			resultJSON.put("msg", "看TA的动态");
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
			resultJSON.put("msg", "生活圈动态粉丝不可见");
			return resultJSON;
		}else {
			userDao.modifyLifeSee(params);
			resultJSON.put("status", true);
			resultJSON.put("msg", "生活圈动态粉丝可见");
			return resultJSON;
		}
	}
	
	
	
}
