package com.liancheng.it.controller.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.minidev.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liancheng.it.service.user.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	
	@RequestMapping(value="/regist.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public JSONObject regist(@RequestParam("phoneNum") String phoneNum,
			@RequestParam("verifyCode") String verifyCode, 
			@RequestParam("nickname") String nickname, 
			@RequestParam("password") String password){
		JSONObject result = userService.addUser(phoneNum, password, verifyCode, nickname);
		return result;
	}
	
	@RequestMapping("/login.do")
	@ResponseBody
	public JSONObject login(@RequestParam("loginUser") String phoneNum, 
			@RequestParam("loginPassword") String password){
		JSONObject jsonObject = userService.checkLoginUser(phoneNum, password);
		return jsonObject;
	}
	
	@RequestMapping("/sendCode.do")
	@ResponseBody
	public JSONObject sendCode(@RequestParam("phoneNum") String phoneNum){
		JSONObject jsonObject = userService.createCode(phoneNum);
		return jsonObject;
	}
	
	@RequestMapping(value="/updateUser.do",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject updateUser(@RequestParam("token") String user_id, 
			@RequestParam(value="phoneNum",required=false) String phoneNum, 
			@RequestParam(value="password",required=false) String password, 
			@RequestParam(value="nickname",required=false) String nickname, 
			@RequestParam(value="age",required=false) String age, 
			@RequestParam(value="gender",required=false) String gender, 
			@RequestParam(value="mobile",required=false) String mobile, 
			@RequestParam(value="homeland",required=false) String homeland, 
			@RequestParam(value="job",required=false) String job, 
			@RequestParam(value="label",required=false) String label){
		JSONObject jsonObject = userService.updateUser(user_id, phoneNum, password, nickname,
				age, gender, mobile, homeland, job, label);
		return jsonObject;
	}
	
	@RequestMapping("/showUserInfo.do")
	@ResponseBody
	public JSONObject showUserInfo(@RequestParam("token") String user_id, 
			HttpServletRequest req){
		//项目环境下的图片路径
		String path = req.getContextPath();
		String hostPath01 = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = userService.showUserInfo(user_id, hostPath01);
		return jsonObject;
	}
	
	@RequestMapping("/attachShoppingAddress.do")
	@ResponseBody
	public JSONObject attachShoppingAddress(@RequestParam("token") String user_id, 
			@RequestParam("name") String name, 
			@RequestParam("phone") String phone, 
			@RequestParam("area") String area, 
			@RequestParam("address") String address){
		JSONObject jsonObject = userService.attachShoppingAddress(user_id, name, phone, area, address);
		return jsonObject;
	}
	
	@RequestMapping("/showAddrPagination.do")
	@ResponseBody
	public JSONObject showAddrPagination(@RequestParam("token") String user_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber){
		JSONObject jsonObject = userService.showAddrPagination(user_id, pageSize, pageNumber);
		return jsonObject;
	}
	
	
	
	
	
}
