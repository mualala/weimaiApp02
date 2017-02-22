package com.liancheng.it.controller.user;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.liancheng.it.service.user.UserService;
import com.liancheng.it.util.IMManager;
import com.liancheng.it.util.StringUtil;
import com.liancheng.it.util.StringUtil02;

import net.minidev.json.JSONObject;
/**
 * 实现登录，连接数据库验证帐号密码
 *
 */

@Controller//将controller注入
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	/**
	 * 登录
	 */
	@RequestMapping(value="/login.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public JSONObject doLogin(@RequestParam("phoneNum") String phoneNum, 
			@RequestParam("password") String password, 
			HttpServletRequest request){
//		String phoneNum = request.getParameter("phoneNum");
//		String password = request.getParameter("password");
//		String token =request.getParameter("schoolId");
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject result = new JSONObject();
		result = userService.checkLogin(phoneNum, password, request, hostPath01);//调用service方法实现业务逻辑
		return result;
	}
	
	/**
	 * 验证码请求
	 */
	@RequestMapping(value="/checkPhoneNum.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public JSONObject checkCode(@RequestParam("codeType") int codeType, 
			@RequestParam("phoneNum") String phoneNum){
		JSONObject result = new JSONObject();
		System.out.println("请求的电话号码："+phoneNum);
		if(phoneNum == null || "".equals(phoneNum)){
			result.put("status", false);
			result.put("msg", "手机号位空,请输入手机号码");
			return result;
		}else {
			result = userService.checkUserRegist(phoneNum, codeType);
			return result;
		}
	}
	
	/**
	 * 注册
	 */
	@RequestMapping(value="/regist.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public JSONObject regist(HttpServletRequest request){
		String phoneNum = request.getParameter("phoneNum");
		String code = request.getParameter("code");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		
		System.out.println("进入用户注册，phone："+phoneNum);
		System.out.println("注册用户的密码：" + password);
		System.out.println("请求的验证码：" + code);
		JSONObject result = userService.addUser(phoneNum, password, code,username);
		return result;
	}
	
	/**
	 * 上传用户头像
	 */
	@RequestMapping(value="/profile.do", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject profile(@RequestParam("profile") MultipartFile pic, 
			@RequestParam("user_id") String user_id, 
			HttpServletRequest request, HttpServletResponse response){
		
		//将HttpServletRequest转换成MultipartHttpServletRequest
		//(MultipartHttpServletRequest)request
		
		System.out.println("进了用户头像上传请求，用户id=" + user_id);
		
		//服务器上的地址，url
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		System.out.println("basePath="+basePath);
		
		//获取保存本地用户头像的路径
		String localBasePath = request.getSession().getServletContext().getRealPath("/")+"images/local_user_profile/";//获取本地磁盘路径
		
		String picName = String.valueOf(System.currentTimeMillis());//设置图片唯一的名称
		String suffix = pic.getOriginalFilename().substring(pic.getOriginalFilename().lastIndexOf("."));//获取文件的后缀名
		
		System.out.println("本地保存的路径="+localBasePath + picName + suffix);
		userService.saveProfile(pic, user_id, localBasePath + picName + suffix,picName,localBasePath,suffix);
		
		JSONObject resultJSON = new JSONObject();
		resultJSON.put("msg", "头像上传成功!");
		resultJSON.put("data", basePath+picName+suffix);
		System.out.println("上传头像返回的数据："+resultJSON.toJSONString());
		return resultJSON;
	}
	
	/**
	 * 用户个人信息展示
	 */
	@RequestMapping("/showUserInfo.do")
	@ResponseBody
	public JSONObject showUserInfo(@RequestParam("user_id") String user_id, 
			@RequestParam("other_user_id") String other_user_id, 
			HttpServletRequest request){
		System.out.println("进了用户信息展示.....");
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		String hostPath02 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_active/";
		
		JSONObject result = userService.userInfo(user_id, other_user_id, hostPath01, hostPath02);
		return result;
	}
	
	
	/**
	 * 更新用户基本信息
	 */
	@RequestMapping(value="/edit.do",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject eidtUser(@RequestParam("user_id") String user_id, 
			@RequestParam(value="username",required=false) String username, 
			@RequestParam(value="gender",required=false) String gender, 
			@RequestParam(value="birthday",required=false) String birthday, 
			@RequestParam(value="star",required=false) String star, 
			@RequestParam(value="e_state",required=false) String e_state, 
			@RequestParam(value="grade",required=false) String grade, 
			@RequestParam(value="profession",required=false) String profession, 
			@RequestParam(value="major",required=false) String major, 
			@RequestParam(value="school",required=false) String school, 
			@RequestParam(value="highschool",required=false) String highschool, 
			@RequestParam(value="province",required=false) String province, 
			@RequestParam(value="city",required=false) String city, 
			@RequestParam(value="county",required=false) String county, 
			@RequestParam(value="lable",required=false) String lable, 
			@RequestParam(value="skill",required=false) String skill){
		System.out.println("major="+major+",school="+school);
		System.out.println("进了用户信息更新！");
		JSONObject jsonObject = userService.editUser(user_id, username, gender, birthday, star, e_state, grade, 
				profession, school, major, highschool, province, city, county, lable, skill);
		System.out.println("major="+major+",school="+school);
		return jsonObject;
	}
	
	/**
	 * 上传用户验证信息
	 * @param type 验证信息的类型，学生证还是毕业证
	 */
	@RequestMapping(value="/verify.do")
	@ResponseBody
	public JSONObject uploadVerify(@RequestParam("verify") MultipartFile verify, 
			HttpServletRequest request){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		
		String type = multipartRequest.getParameter("type");
		String user_id = multipartRequest.getParameter("user_id");
		
		//保存文件的本地路径
		String localBasePath = request.getSession().getServletContext().getRealPath("/")+"images/local_verify/";
		String picName = String.valueOf(System.currentTimeMillis());//设置图片唯一的名称
		//获取文件的后缀名
		String suffix = verify.getOriginalFilename().substring(verify.getOriginalFilename().lastIndexOf("."));
		
		JSONObject jsonObject = userService.userVerify(verify, type, user_id, localBasePath, picName, suffix);
		
		return jsonObject;
	}
	
	/**
	 * 人海的随机用户列表
	 */
	@RequestMapping(value="/peoples.do",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject peoples(@RequestParam("user_id") String user_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		System.out.println("进了人海的随机用户列表！！！！");
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		
		JSONObject jsonObject = userService.randPeoples(user_id, pageSize, pageNumber, hostPath01);
		return jsonObject;
	}
	/**
	 * 人海和脉脉圈用户的筛选请求
	 * @param age01 查询的初始年龄
	 * @param age02 查询的结束年龄
	 */
	@RequestMapping(value="/filterPeoples.do")
	@ResponseBody
	public JSONObject filterPeoples(@RequestParam(value="user_id") String user_id, 
			@RequestParam(value="pageSize",required=false) int pageSize, 
			@RequestParam(value="pageNumber",required=false) int pageNumber, 
			@RequestParam(value="gender",required=false) String gender, 
			@RequestParam(value="grade",required=false) String grade, 
			@RequestParam(value="age01",required=false) int age01, 
			@RequestParam(value="age02",required=false) int age02, 
			@RequestParam(value="major",required=false) String major, 
			@RequestParam(value="type",required=false) String type, 
			HttpServletRequest request){
		System.out.println("进了人海用户的筛选请求。。。");
		System.out.println("user_id="+user_id+",pageNumber="+pageNumber+",pageSize="+pageSize+",gender="+gender+",grade="+grade+",age01="+age01+",age02="+age02+",major="+major+",type="+type);
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = userService.filterPeoples(user_id, pageSize, pageNumber, 
				gender, grade, age01, age02, major, type, hostPath01);
		return jsonObject;
	}
	
	/**
	 * 人海用户的搜索
	 */
	@RequestMapping("peoplesSearch.do")
	@ResponseBody
	public JSONObject peoplesSearch(@RequestParam("param") String param, 
			HttpServletRequest request){
		System.out.println("进了人海用户的搜索。。。");
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = userService.peoplesSearch(param, hostPath01);
		return jsonObject;
	}
	
	/**
	 * 脉脉圈的分类用户
	 */
	@RequestMapping("/maimaiCateg.do")
	@ResponseBody
	public JSONObject maimaiCateg(@RequestParam("user_id") String user_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			@RequestParam("type") String type, 
			HttpServletRequest request){
		System.out.println("进了脉脉圈用户加载列表。。。");
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		
		JSONObject jsonObject = userService.maimaiCateg(user_id, pageSize, pageNumber, type, hostPath01);
		return jsonObject;
	}
	
	/**
	 * 脉脉圈的用户搜索
	 */
	@RequestMapping("/maimaiSearch.do")
	@ResponseBody
	public JSONObject maimaiSearch(@RequestParam("param") String param, 
			HttpServletRequest request){
		System.out.println("进了脉脉圈的用户搜索。。。");
		System.out.println("param="+param);
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		
		JSONObject jsonObject = userService.maimaiSearch(param, hostPath01);
		return jsonObject;
	}
	
	/**
	 * 访客的用户列表
	 */
	@RequestMapping("/showVisitorList.do")
	@ResponseBody
	public JSONObject showVisitorList(@RequestParam("user_id") String user_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		System.out.println("访客的用户列表。。。");
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = userService.visitorList(user_id, pageSize, pageNumber, hostPath01);
		return jsonObject;
	}
	
	/**
	 * 返回学校api数据的接口
	 */
	@RequestMapping(value="/schoolAPI.do",produces="application/json;charset=utf-8")
	@ResponseBody
	public JSONObject schoolAPI(){
		JSONObject jsonObject = new JSONObject();
		Properties prop = new Properties();
		try {
			InputStreamReader in= new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("/json/json.properties"),"UTF-8");
			prop.load(in);
			jsonObject.put("status", true);
//			jsonObject.put("data", StringUtil02.compress("abcdefghijklmn"));
			jsonObject.put("data", StringUtil02.compress(prop.getProperty("university")));
//			jsonObject.put("length01=", prop.getProperty("university").length());
//			jsonObject.put("length02=", StringUtil02.compress(prop.getProperty("university")).length());
			jsonObject.put("msg", "学校数据读取成功");
		} catch (Exception e) {
			System.out.println("学校数据读取失败");
			jsonObject.put("status", false);
			jsonObject.put("msg", "学校数据读取失败");
			return jsonObject;
		}
		return jsonObject;
		/*
		net.sf.json.JSONObject j = new net.sf.json.JSONObject();
		Properties prop = new Properties();
		InputStream in= this.getClass().getClassLoader().getResourceAsStream("/json/json.properties");
		try {
			prop.load(in);
		} catch (Exception e) {
			System.out.println("学校的api数据读取失败");
			j.accumulate("status", false);
			j.accumulate("msg", "学校的api数据读取失败");
			return j;
		}
		j.accumulate("data", prop.getProperty("university"));
		j.accumulate("status", true);
		j.accumulate("msg", "学校的api数据读取成功");
		return j;*/
		
	}
	
	/**
	 * 用户的认证状态
	 */
	@RequestMapping("/checkUserVerify.do")
	@ResponseBody
	public JSONObject checkUserVerify(@RequestParam("user_id") String user_id){
		System.out.println("进了用户的认证状态");
		JSONObject jsonObject = userService.checkUserVrify(user_id);
		return jsonObject;
	}
	
	/**
	 * 重置密码
	 */
	@RequestMapping("/rePassword.do")
	@ResponseBody
	public JSONObject rePassword(@RequestParam("phoneNum") String phoneNum, 
			@RequestParam("code") String code, 
			@RequestParam("password") String password){
		System.out.println("进了重置密码");
		JSONObject jsonObject = userService.editPassword(phoneNum, code, password);
		return jsonObject;
	}
	
	/**
	 * 动态可不可见的开关
	 */
	@RequestMapping("/seeSwitch.do")
	@ResponseBody
	public JSONObject seeSwitch(@RequestParam("user_id") String user_id, 
			@RequestParam("f_user_id") String f_user_id){
		JSONObject jsonObject = userService.seeState(user_id, f_user_id);
		return jsonObject;
	}
	
	/**
	 * 控制是否可以发消息
	 * @param send_msg 开关：0关闭 1开启
	 * @return
	 */
	@RequestMapping("/controlSendMsg.do")
	@ResponseBody
	public JSONObject controlSendMsg(@RequestParam("user_id") String user_id, 
			@RequestParam("send_msg") int send_msg){
		JSONObject jsonObject = userService.controlSendMsg(user_id, send_msg);
		return jsonObject;
	}
	
	/**
	 * 拉黑用户
	 * @param user_id
	 * @param other_user_id
	 * @param value
	 * @return
	 */
	@RequestMapping("/specialRelation.do")
	@ResponseBody
	public JSONObject specialRelation(@RequestParam("user_id") String user_id, 
			@RequestParam("other_user_id") String other_user_id, 
			@RequestParam("value") String value){
		JSONObject jsonObject = new JSONObject();
		try {
			if(IMManager.setSpecialRelation(user_id, other_user_id, value)){
				jsonObject.put("status", true);
				jsonObject.put("msg", "加入黑名单成功");
				return jsonObject;
			}else {
				jsonObject.put("status", false);
				jsonObject.put("msg", "加入黑名单失败");
				return jsonObject;
			}
		} catch (Exception e) {
			jsonObject.put("status", false);
			jsonObject.put("msg", "加入黑名单失败");
			return jsonObject;
		}
	}
	
	/**
	 * 加好友时是否需要验证的开关
	 * @param user_id
	 * @param other_user_id
	 * @return
	 */
	@RequestMapping("/addFriendIsVerify.do")
	@ResponseBody
	public JSONObject addFriendIsVerify(@RequestParam("user_id") String user_id, 
			@RequestParam("switchVal") int switchVal){
		JSONObject jsonObject = userService.addFriendIsVerify(user_id, switchVal);
		return jsonObject;
	}
	
	/**
	 * 控制 --不让TA看我的动态-- 和 --不看TA的动态的开关--
	 * @param user_id
	 * @param other_user_id
	 * @param type
	 * @param state
	 * @return
	 */
	@RequestMapping("/taNoSeeOwnActive.do")
	@ResponseBody
	public JSONObject taNoSeeOwnActive(@RequestParam("user_id") String user_id, 
			@RequestParam("other_user_id") String other_user_id, 
			@RequestParam("type") String type, 
			@RequestParam("switchVal") int state){
		JSONObject jsonObject = userService.taNoSeeOwnActive(user_id, other_user_id, type, state);
		return jsonObject;
	}
	
	/**
	 * 生活圈动态粉丝可见否
	 * @param user_id
	 * @param switchVal
	 * @return
	 */
	@RequestMapping("/lifeSeeControl.do")
	@ResponseBody
	public JSONObject lifeSeeControl(@RequestParam("user_ui") String user_id, 
			@RequestParam("switchVal") int switchVal){
		JSONObject jsonObject = userService.lifeSeeControl(user_id, switchVal);
		return jsonObject;
	}
	
	
	
}
