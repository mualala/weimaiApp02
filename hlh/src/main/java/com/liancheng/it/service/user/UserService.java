package com.liancheng.it.service.user;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import net.minidev.json.JSONObject;

public interface UserService {
	
	public JSONObject addUser(String phoneNum,String password,String code,String nickname);//注册
	public boolean checkLoginUser(String phoneNum, String password);//检查用户登录
	public JSONObject createCode(String phoneNum);//生成验证码
	
}
