package com.liancheng.it.service.user;

import net.minidev.json.JSONObject;

public interface UserService {
	
	/**
	 * 注册
	 * @param phoneNum
	 * @param password
	 * @param code
	 * @param nickname
	 * @return
	 */
	public JSONObject addUser(String phoneNum,String password,String code,String nickname);//注册
	
	/**
	 * 登录
	 * @param phoneNum
	 * @param password
	 * @return
	 */
	public JSONObject checkLoginUser(String phoneNum, String password);
	
	/**
	 * 发送验证码
	 * @param phoneNum
	 * @return
	 */
	public JSONObject createCode(String phoneNum);//生成验证码
	
	/**
	 * 更新用户信息
	 * @param user_id
	 * @param phoneNum
	 * @param password
	 * @param nickname
	 * @param age
	 * @param gender
	 * @param mobile
	 * @param homeland
	 * @param job
	 * @param label
	 * @return
	 */
	public JSONObject updateUser(String user_id,String phoneNum,String password,String nickname,
			String age,String gender,String mobile,String homeland,String job,String label);
	
	/**
	 * 展示用户个人信息
	 * @param user_id
	 * @param hostPath01
	 * @return
	 */
	public JSONObject showUserInfo(String user_id, String hostPath01);
	
	/**
	 * 添加收货地址
	 * @param user_id
	 * @param name
	 * @param phone
	 * @param area
	 * @param address
	 * @return
	 */
	public JSONObject attachShoppingAddress(String user_id, String name, String phone, 
			String area, String address);
	
	/**
	 * 展示收货地址的分页数据
	 * @param user_id
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	public JSONObject showAddrPagination(String user_id, int pageSize, int pageNumber);
	
	
	
	
}
