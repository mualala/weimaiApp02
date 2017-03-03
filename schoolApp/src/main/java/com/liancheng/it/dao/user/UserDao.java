package com.liancheng.it.dao.user;

import java.util.List;
import java.util.Map;

import com.liancheng.it.entity.user.SeeControl;
import com.liancheng.it.entity.user.User;
import com.liancheng.it.entity.user.UserChart;
import com.liancheng.it.entity.user.Visitor;

public interface UserDao {
	
	public User findByPhoneNum(String phoneNum);
	public User findByUserId(String user_id);//根据user_id查询用户信息
	public User findBySchoolId(User user);//根据校园号查询用户信息
	
	public int save(User user);
	public void saveProfile(User user);
	public User findById(String user_id);
	public List<User> findByNickName(String user_nickname);//根据昵称查询用户信息
	
	public void deleteById(String user_id);
	
	public void editUser(User user);//更新用户信息 
	public void editSkills();//更新用户技能标签
	
	public String queryUserProfile(String user_id);//查询用户头像
	public String queryUserNickName(String user_id);//查询用户昵称
	public void saveStuVerify(Map<String, Object> params);//保存用户学生证验证信息
	public void saveCertiVerify(Map<String, Object> params);//保存用户毕业证验证信息
	public List<User> queryAdminVerify(Map<String, Object> params);//用于后台审核身份验证信息查询
	public int queryCountVerifies(Map<String, Object> params);//身份验证的条数
	public void modifyStuAdmin(Map<String, Object> params);//修改学生证验证状态
	public void modifyCertiAdmin(Map<String, Object> params);//修改毕业证验证状态
	
	public List<User> randPeoples(Map<String, Object> params);//人海的随机用户
	public List<User> filterPeoples(Map<String, Object> params);//人海用户的筛选
	public List<User> queryTypeUser(Map<String, Object> params);//脉脉圈的分类用户列表
	
	public List<User> queryCheckMaimai(String param);//脉脉圈的朋友搜索
	
	public Visitor queryVisitorByUserId(Map<String, Object> params);//根据访问者的uuid查询访客
	public void saveVisitor(Map<String, Object> params);//保存访客
	public int totalVisitors(String user_id);//查询访客数量
	public List<Visitor> queryVisitorList(Map<String, Object> params);//查询访客的用户列表
	public List<User> queryUserList(Map<String, Object> params);//用于后台用户报表
	public int totalUserCount(Map<String, Object> params);//查询有多少用户数
	public void ModifyPassword(Map<String, Object> params);//修改用户的密码
	public List<UserChart> queryChartControlYM();//home.html页面chart的筛选框的值
	public List<UserChart> queryChartData(String selectDate);//home.html页面chart的数据
	public int queryAllNetPeople();//全网用户数
	public int totalNoVerifyUser();//所有未审核的用户
	public void modifySendMsg(Map<String, Object> params);//是否可以发消息
	public void modifyAddSwitch(Map<String, Object> params);
	public void modifyLifeSee(Map<String, Object> params);
	
	public void saveTaNoSeeOwnActive(Map<String, Object> params);//保存不让他看我动态的关系
	public SeeControl queryTaNoSeeOwnActive(Map<String, Object> params);//查询不让他看我动态的关系
	public void updateTaNoSeeOwnActive(Map<String, Object> params);//更新不让他看我动态的关系
	
	public List<SeeControl> querySeeControlState(Map<String, Object> params);
	public void blockUser(Map<String, Object> params);//禁用/解禁用户
	
	
}
