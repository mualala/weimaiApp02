package com.liancheng.it.controller.user;


import java.io.InputStream;
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

import net.minidev.json.JSONObject;
/**
 * ʵ�ֵ�¼���������ݿ���֤�ʺ�����
 *
 */

@Controller//��controllerע��
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	

	/**
	 * ע��
	 */
	@RequestMapping(value="/regist.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public JSONObject regist(@RequestParam("phoneNum") String phoneNum,
			@RequestParam("verifyCode") String verifyCode, 
			@RequestParam("nickname") String nickname, 
			@RequestParam("password") String password){
		JSONObject result = userService.addUser(phoneNum, password, verifyCode, nickname);
		return result;
	}
	
	/**
	 * ��¼
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public JSONObject login(@RequestParam("loginUser") String phoneNum, 
			@RequestParam("loginPassword") String password){
		JSONObject jsonObject = new JSONObject();
		if(userService.checkLoginUser(phoneNum, password)){
			jsonObject.put("status", true);
			jsonObject.put("url", "index.html");
			jsonObject.put("msg", "��¼�ɹ�");
			return jsonObject;
		}else {
			jsonObject.put("status", false);
			jsonObject.put("msg", "�˺Ż��������");
			return jsonObject;
		}
	}
	
	/**
	 * ������֤��
	 */
	@RequestMapping("/sendCode.do")
	@ResponseBody
	public JSONObject sendCode(@RequestParam("phoneNum") String phoneNum){
		System.out.println("���˷�����֤�롣����");
		JSONObject jsonObject = userService.createCode(phoneNum);
		return jsonObject;
	}
}
