package com.liancheng.it.service.user;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import net.minidev.json.JSONObject;

public interface UserService {
	
	public JSONObject checkLogin(String phoneNum, String password, HttpServletRequest request);//登录
	public JSONObject addUser(String phoneNum,String password,String code,String enterCode);//注册
	public JSONObject checkUserRegist(String phoneNum);//验证码
	
	public void saveProfile(MultipartFile pic, String phoneNum, 
			String localBasePath, String picName, String deletLocalPath, String suffix);//用户上传头像
	public JSONObject findUserById(String user_id);//通过id查找用户
	public JSONObject findUsersByNick(String user_nickname);//通过昵称查找用户
	public JSONObject editUser(String phoneNum, String username, String gender, 
			String birthday, String star, String e_state, String grade, String profession, String school,
			String major, String highschool, String province, String city, String county, 
			String lable, String skill);//修改密码
	public JSONObject editSkills(String user_id, String skills);//更新用户技能
	public JSONObject userVerify(MultipartFile verify, String type, 
			String user_id, String localBasePath, String picName, String suffix);//用户验证图片
	public JSONObject verifyReport(int pageSize, int pageNumber, String hostPath);//用户验证信息后台报表
	public JSONObject oneUserVerify(int schoolId, String verify, String state);//后台独项验证学生证或毕业证
	public JSONObject randPeoples(String user_id, int pageSize, int pageNumber, 
			String hostPath01);//人海的用户列表
	public JSONObject filterPeoples(String user_id, int pageSize, int pageNumber, String gender, 
			String grade, int age01, int age02, String major, String type, String hostPath01);//人海的条件筛选
	public JSONObject maimaiCateg(String user_id, int pageSize, int pageNumber, 
			String type, String hostPath01);//脉脉圈的分类用户
	public JSONObject peoplesSearch(String param, String hostPath01);//人海用户的朋友搜索
	public JSONObject maimaiSearch(String param, String hostPath01);//脉脉圈的朋友搜索
	public JSONObject userInfo(String user_id, String hostPath01, String hostPath02);//展示用户的个人信息
	public JSONObject visitorList(String user_id, int pageSize, int pageNumber, 
			String hostPath01);//访客的用户列表
	
}
