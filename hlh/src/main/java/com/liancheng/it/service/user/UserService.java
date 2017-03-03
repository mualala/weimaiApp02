package com.liancheng.it.service.user;

import net.minidev.json.JSONObject;

public interface UserService {
	
	public JSONObject addUser(String phoneNum,String password,String code,String nickname);//注册
	public JSONObject checkLoginUser(String phoneNum, String password);//检查用户登录
	public JSONObject createCode(String phoneNum);//生成验证码
	public JSONObject updateUser(String user_id,String phoneNum,String password,String nickname,
			String age,String gender,String mobile,String homeland,String job,String label);
	public JSONObject showUserInfo(String user_id, String hostPath01);
	public JSONObject attachShoppingAddress(String user_id, String name, String phone, 
			String area, String address);
	public JSONObject showAddrPagination(String user_id, int pageSize, int pageNumber);
	
	
	
	
}
