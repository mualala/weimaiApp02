package com.liancheng.it.service.user;


import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.minidev.json.JSONObject;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liancheng.it.dao.user.CheckCodeDao;
import com.liancheng.it.dao.user.UserDao;
import com.liancheng.it.entity.friends.Friends;
import com.liancheng.it.entity.user.Coder;
import com.liancheng.it.entity.user.ShoppingAddress;
import com.liancheng.it.entity.user.User;
import com.liancheng.it.util.DateUtil;
import com.liancheng.it.util.HttpSenderUtil;
import com.liancheng.it.util.Jwt;
import com.liancheng.it.util.RegistCheckCode;
import com.liancheng.it.util.UUIDUtil;
/**
 * 用户登录业务
 */

@Service("userService")//扫描service
@Aspect
@Transactional
public class UserServiceImpl implements UserService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource(name="userDao")//注入userDao
	private UserDao userDao;
	@Resource(name="checkCodeDao")//注入checkCodeDao
	private CheckCodeDao checkCodeDao;

	/**
	 * 注册业务
	 */
	public JSONObject addUser(String phoneNum,String password,String code,String nickname){
		JSONObject resultJSON=new JSONObject();
		User user = new User();
		Coder checkCode = new Coder();
		
		if(password==null || "".equals(password)){//密码如果为空
			resultJSON.put("msg", "密码为空,请输入密码!");
			return resultJSON;
		}
		if(code==null || "".equals(code)){//如果验证码为空
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
				
				System.out.println("进了。。。");
				
				long currentime = System.currentTimeMillis();
				long creatime = checkCode.getCreatime().getTime();
				
				if(currentime<creatime+1*60*1000){//判断验证码是否过期，5分钟为过期时间
					try {
						user.setUser_id(UUIDUtil.creatId());//可以先生成uuid
						user.setPhoneNum(phoneNum);
						user.setNickname(nickname);//可以先设置用户名
						user.setPhoneNum(phoneNum);//添加用户电话号码
						user.setCreatime(new Timestamp(System.currentTimeMillis()));
						user.setPassword(UUIDUtil.md5(password));//设置密码 
						
						userDao.saveUser(user);//保存注册用户
						checkCodeDao.deleteCoderByPhoneNum(phoneNum);//注册成功就删除验证码
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("注册用户的信息："+user);
					
					Date date = new Date();
					Map<String , Object> payload=new HashMap<String, Object>();
					payload.put("uid", phoneNum);//用户ID
					payload.put("iat", date.getTime());//生成时间
					payload.put("ext",date.getTime()+1000*60*60);//过期时间1小时
//					String token=Jwt.createToken(payload);
					
					resultJSON.put("status", true);
					resultJSON.put("msg", "注册成功");
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
	
	public JSONObject checkLoginUser(String phoneNum, String password){
		JSONObject jsonObject = new JSONObject();
		User user = userDao.queryUserByPhone(phoneNum);
		try {
			if(user!=null && (UUIDUtil.md5(password)).equals(user.getPassword())){
				logger.info("用户可以登录");
				jsonObject.put("status", true);
				jsonObject.put("token", user.getUser_id());
				jsonObject.put("url", "index.html");
				jsonObject.put("msg", "登录成功");
				return jsonObject;
			}else {
				jsonObject.put("status", false);
				jsonObject.put("msg", "账号或密码错误");
				return jsonObject;
			}
		} catch (Exception e) {
			logger.info("登录时md5加密失败");
		}
		return jsonObject;
	}
	
	/**
	 * 生成验证码并发送验证码业务
	 */
	public JSONObject createCode(String phoneNum){
		JSONObject resultJSON=new JSONObject();
		Coder coder =  new Coder();
		Coder checkCode = checkCodeDao.findCheckCodeByPhoneNum(phoneNum);
		if(userDao.queryUserByPhone(phoneNum)!=null){//手机号码已经注册
			resultJSON.put("status", false);
			resultJSON.put("msg", "该手机号码已注册");
			return resultJSON;
		}else {//手机号码没注册过
			if(checkCode != null){//判断该手机是否获取过验证码
//				Date date=new Date();
				long currentime = System.currentTimeMillis();
				long creatime = checkCodeDao.findCheckCodeByPhoneNum(phoneNum).getCreatime().getTime();
				
				if(currentime<creatime+1*60*1000){//判断验证码是否过期，1分钟内不能再获取验证码
					resultJSON.put("status", false);
					resultJSON.put("msg", "请于1分钟后获取");
					System.out.println("获取了验证码，1min不能获取");
					return resultJSON;
				}else{
					checkCodeDao.deleteCoderByPhoneNum(phoneNum);//5分钟后删除该验证码
				}
			}
			String code = RegistCheckCode.getCheckCode();
			String codemsg = "您好！您的验证码为："+code+"有效时间1分钟，请及时完成注册！";
			
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
				System.out.println("给手机号"+phoneNum+"发送验证码失败");
			}
			resultJSON.put("status", true);
			resultJSON.put("msg", "获取验证码成功");
			resultJSON.put("data", code);
			System.out.println("验证码返回的json："+resultJSON.toJSONString());
			return resultJSON;
		}
		
	}

	public JSONObject updateUser(String user_id,String phoneNum,String password,String nickname,
			String age,String gender,String mobile,String homeland,String job,String label) {
		JSONObject resultJSON = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", user_id);
		params.put("phoneNum", phoneNum);
		params.put("password", password);
		params.put("nickname", nickname);
		params.put("age", age);
		params.put("gendar", gender);
		params.put("mobile", mobile);
		params.put("homeland", homeland);
		params.put("job", job);
		params.put("label", label);
		params.put("lastmodifytime", new Timestamp(System.currentTimeMillis()));
		System.out.println(params);
		logger.info(params.toString());
		int rows = userDao.updateUser(params);
		if(rows == 1){
			resultJSON.put("status", true);
			resultJSON.put("msg", "修改个人信息成功");
			return resultJSON;
		}else{
			resultJSON.put("status", false);
			resultJSON.put("msg", "修改个人信息失败");
			return resultJSON;
		}
	}
	
	public JSONObject showUserInfo(String user_id, String hostPath01){
		JSONObject resultJSON = new JSONObject();
		User user = userDao.queryUserById(user_id);
		if(user != null){
			if(user.getProfile() != null){
				user.setProfile(hostPath01+user.getProfile());
			}
			resultJSON.put("status", true);
			resultJSON.put("data", user);
		}else {
			resultJSON.put("status", false);
		}
		return resultJSON;
	}
	
	public JSONObject attachShoppingAddress(String user_id, String name, String phone, 
			String area, String address){
		JSONObject resultJSON = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", user_id);
		params.put("name", name);
		params.put("phone", phone);
		params.put("area", area);
		params.put("address", address);
		params.put("addr_creatime", new Timestamp(System.currentTimeMillis()));
		int insertCount = userDao.saveShoppingAddress(params);
		if(insertCount==1){
			resultJSON.put("status", true);
			resultJSON.put("msg", "添加收货地址成功");
		}else {
			resultJSON.put("status", false);
			resultJSON.put("msg", "添加收货地址失败");
		}
		return resultJSON;
	}
	
	public JSONObject showAddrPagination(String user_id, int pageSize, int pageNumber){
		JSONObject resultJSON = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		params.put("start", start);
		params.put("end", end);
		params.put("user_id", user_id);
		List<ShoppingAddress> addrs = userDao.queryPaginAddr(params);
		resultJSON.put("rows", addrs);
		resultJSON.put("total", userDao.totalPaginAddr(user_id));
		return resultJSON;
	}
	
	
	
	
}
