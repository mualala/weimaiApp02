package com.liancheng.it.service.user;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import net.minidev.json.JSONObject;

public interface UserService {
	
	/**
	 * 通过用户id查找用户
	 * @param user_id
	 * @return
	 */
	public JSONObject findUserById(String user_id);
	
	/**
	 * 通过昵称查找用户
	 * @param user_nickname
	 * @return
	 */
	public JSONObject findUsersByNick(String user_nickname);
	
	/**
	 * 登录
	 * @param phoneNum
	 * @param password
	 * @param request
	 * @param hostPath01
	 * @return
	 */
	public JSONObject checkLogin(String phoneNum, String password, HttpServletRequest request, String hostPath01);
	
	/**
	 * 注册
	 * @param phoneNum
	 * @param password
	 * @param code
	 * @param enterCode
	 * @return
	 */
	public JSONObject addUser(String phoneNum,String password,String code,String enterCode);
	
	/**
	 * 验证码
	 * @param phoneNum
	 * @param codeType
	 * @return
	 */
	public JSONObject checkUserRegist(String phoneNum ,int codeType);
	
	/**
	 * 上传用户头像
	 */
	public void saveProfile(MultipartFile pic, String phoneNum, 
			String localBasePath, String picName, String deletLocalPath, String suffix);
	
	/**
	 * 更新用户基本信息
	 * @return
	 */
	public JSONObject editUser(String phoneNum, String username, String gender, 
			String birthday, String star, String e_state, String grade, String profession, String school,
			String major, String highschool, String province, String city, String county, 
			String lable, String skill);
	
	/**
	 * 更新用户技能
	 * @param user_id
	 * @param skills
	 * @return
	 */
	public JSONObject editSkills(String user_id, String skills);
	
	/**
	 * 上传用户验证信息
	 * @return
	 */
	public JSONObject userVerify(MultipartFile verify, String type, 
			String user_id, String localBasePath, String picName, String suffix);
	
	/**
	 * 用户验证信息后台报表
	 * @return
	 */
	public JSONObject verifyReport(int pageSize, int pageNumber, String searchText, String sortName,
			String sortOrder, String startDate, String endDate, String schoolID, String phoneNum, String hostPath);
	
	/**
	 * 后台独项验证学生证或毕业证
	 * @param schoolId
	 * @param verify
	 * @param state
	 * @param otherState
	 * @return
	 */
	public JSONObject oneUserVerify(int schoolId, String verify, String state, String otherState);
	
	/**
	 * 人海的用户列表
	 * @param user_id
	 * @param pageSize
	 * @param pageNumber
	 * @param hostPath01
	 * @return
	 */
	public JSONObject randPeoples(String user_id, int pageSize, int pageNumber, 
			String hostPath01);
	
	/**
	 * 人海和脉脉圈用户的筛选
	 * @param user_id
	 * @param pageSize
	 * @param pageNumber
	 * @param gender
	 * @param grade
	 * @param age01  查询的初始年龄
	 * @param age02 查询的结束年龄
	 * @param major
	 * @param type
	 * @param hostPath01
	 * @return
	 */
	public JSONObject filterPeoples(String user_id, int pageSize, int pageNumber, String gender, 
			String grade, int age01, int age02, String major, String type, String hostPath01);
	
	/**
	 * 脉脉圈的分类用户
	 * @return
	 */
	public JSONObject maimaiCateg(String user_id, int pageSize, int pageNumber, 
			String type, String hostPath01);
	
	/**
	 * 人海用户的搜索
	 * @param param
	 * @param hostPath01
	 * @return
	 */
	public JSONObject peoplesSearch(String param, String hostPath01);
	
	/**
	 * 脉脉圈的用户搜索
	 * @param param
	 * @param hostPath01
	 * @return
	 */
	public JSONObject maimaiSearch(String param, String hostPath01);
	
	/**
	 * 用户个人信息展示
	 * @param user_id
	 * @param other_user_id
	 * @param hostPath01
	 * @param hostPath02
	 * @return
	 */
	public JSONObject userInfo(String user_id, String other_user_id, String hostPath01, String hostPath02);
	
	/**
	 * 访客的用户列表
	 * @param user_id
	 * @param pageSize
	 * @param pageNumber
	 * @param hostPath01
	 * @return
	 */
	public JSONObject visitorList(String user_id, int pageSize, int pageNumber, 
			String hostPath01);
	
	/**
	 * 后台的用户报表
	 * @return
	 */
	public JSONObject userReport(int pageSize, int pageNumber, String searchText, String verifyState, 
			String blockState, String school, String gender, String profession, String startDate, String endDate, 
			String sortName, String sortOrder, String schoolID, String hostPath01, String hostPath02);
	
	/**
	 * 用户的认证状态
	 * @param user_id
	 * @return
	 */
	public JSONObject checkUserVrify(String user_id);
	
	/**
	 * 重置密码
	 * @param phoneNum
	 * @param code
	 * @param password
	 * @return
	 */
	public JSONObject editPassword(String phoneNum, String code, String password);
	
	/**
	 * 动态可不可见的开关
	 * @param user_id
	 * @param f_user_id
	 * @return
	 */
	public JSONObject seeState(String user_id, String f_user_id);
	
	/**
	 * home.html页面chart的筛选框的值
	 * @return
	 */
	public JSONObject getHomeSelectorVal();
	
	/**
	 * home.html页面Echart的数据
	 * @param selectDate
	 * @return
	 */
	public JSONObject getHomeChartData(String selectDate);
	
	/**
	 * 控制是否可以发消息
	 * @param user_id
	 * @param send_msg 开关：0关闭 1开启
	 * @return
	 */
	public JSONObject controlSendMsg(String user_id, int send_msg);
	
	/**
	 * 加好友时是否需要验证的开关
	 * @param user_id
	 * @param switchVal
	 * @return
	 */
	public JSONObject addFriendIsVerify(String user_id, int switchVal);
	
	/**
	 * 控制 --不让TA看我的动态-- 和 --不看TA的动态的开关--
	 * @param user_id
	 * @param other_user_id
	 * @param type
	 * @param state
	 * @return
	 */
	public JSONObject taNoSeeOwnActive(String user_id, String other_user_id, String type, int state);
	
	/**
	 * 生活圈动态粉丝可见否
	 * @param user_id
	 * @param switchVal
	 * @return
	 */
	public JSONObject lifeSeeControl(String user_id, int switchVal);
	
	/**
	 * 禁用用户
	 * @param user_id
	 * @param lock
	 * @return
	 */
	public JSONObject blockUser(String user_id, int lock);
	
	
}
