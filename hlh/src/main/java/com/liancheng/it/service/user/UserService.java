package com.liancheng.it.service.user;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import net.minidev.json.JSONObject;

public interface UserService {
	
	public JSONObject addUser(String phoneNum,String password,String code,String nickname);//ע��
	public boolean checkLoginUser(String phoneNum, String password);//����û���¼
	public JSONObject createCode(String phoneNum);//������֤��
	
}
