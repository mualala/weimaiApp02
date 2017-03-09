package com.liancheng.it.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.liancheng.it.entity.user.SeeControl;
import com.liancheng.it.entity.user.User;
import com.liancheng.it.entity.user.UserChart;
import com.liancheng.it.entity.user.Visitor;

public interface UserDao {
	
	/**
	 * 通过电话号码查询用户
	 * @param phoneNum
	 * @return
	 */
	@Select("select * from user	where phoneNum=#{phoneNum}")
	public User findByPhoneNum(@Param("phoneNum") String phoneNum);
	
	/**
	 * 根据user_id查询用户信息
	 * @param user_id
	 * @return
	 */
	public User findByUserId(String user_id);
	
	/**
	 * 根据校园号查询用户信息
	 * @param user
	 * @return
	 */
	public User findBySchoolId(User user);
	
	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	public int save(User user);
	
	/**
	 * 保存/更新用户头像
	 * @param user
	 */
	public void saveProfile(User user);
	
	/**
	 * 根据用户id查询用户
	 * @param user_id
	 * @return
	 */
	public User findById(String user_id);
	
	/**
	 * 根据昵称查询用户信息
	 * @param user_nickname
	 * @return
	 */
	public List<User> findByNickName(String user_nickname);
	
	/**
	 * 根据用户id删除用户
	 * @param user_id
	 */
	public void deleteById(String user_id);
	
	/**
	 * 更新用户信息 
	 * @param user
	 */
	public void editUser(User user);
	
	/**
	 * 查询用户头像
	 * @param user_id
	 * @return
	 */
	public String queryUserProfile(String user_id);
	
	/**
	 * 查询用户昵称
	 * @param user_id
	 * @return
	 */
	public String queryUserNickName(String user_id);
	
	/**
	 * 保存用户学生证验证信息
	 * @param params
	 */
	public void saveStuVerify(Map<String, Object> params);
	
	/**
	 * 保存用户毕业证验证信息
	 * @param params
	 */
	public void saveCertiVerify(Map<String, Object> params);
	
	/**
	 * 用于后台审核身份验证信息查询
	 * @param params
	 * @return
	 */
	public List<User> queryAdminVerify(Map<String, Object> params);
	
	/**
	 * 身份验证的条数
	 * @param params
	 * @return
	 */
	public int queryCountVerifies(Map<String, Object> params);
	
	/**
	 * 修改学生证验证状态
	 * @param params
	 */
	public void modifyStuAdmin(Map<String, Object> params);
	
	/**
	 * 修改毕业证验证状态
	 * @param params
	 */
	public void modifyCertiAdmin(Map<String, Object> params);
	
	/**
	 * 人海的随机用户
	 * @param params
	 * @return
	 */
	public List<User> randPeoples(Map<String, Object> params);
	
	/**
	 * 人海用户的筛选
	 * @param params
	 * @return
	 */
	public List<User> filterPeoples(Map<String, Object> params);
	
	/**
	 * 脉脉圈的分类用户列表
	 * @param params
	 * @return
	 */
	public List<User> queryTypeUser(Map<String, Object> params);
	
	/**
	 * 脉脉圈的朋友搜索
	 * @param param
	 * @return
	 */
	public List<User> queryCheckMaimai(String param);
	
	/**
	 * 根据访问者的uuid查询访客
	 * @param params
	 * @return
	 */
	public Visitor queryVisitorByUserId(Map<String, Object> params);
	
	/**
	 * 保存访客
	 * @param params
	 */
	public void saveVisitor(Map<String, Object> params);
	
	/**
	 * 查询访客数量
	 * @param user_id
	 * @return
	 */
	public int totalVisitors(String user_id);
	
	/**
	 * 查询访客的用户列表
	 * @param params
	 * @return
	 */
	public List<Visitor> queryVisitorList(Map<String, Object> params);
	
	/**
	 * 用于后台用户报表
	 * @param params
	 * @return
	 */
	public List<User> queryUserList(Map<String, Object> params);
	
	/**
	 * 查询有多少用户数
	 * @param params
	 * @return
	 */
	public int totalUserCount(Map<String, Object> params);
	
	/**
	 * 修改用户的密码
	 * @param params
	 */
	public void ModifyPassword(Map<String, Object> params);
	
	/**
	 * home.html页面chart的筛选框的值
	 * @return
	 */
	public List<UserChart> queryChartControlYM();
	
	/**
	 * home.html页面chart的数据
	 * @param selectDate
	 * @return
	 */
	public List<UserChart> queryChartData(String selectDate);
	
	/**
	 * 全网用户数
	 * @return
	 */
	public int queryAllNetPeople();
	
	/**
	 * 所有未审核的用户
	 * @return
	 */
	public int totalNoVerifyUser();
	
	/**
	 * 是否可以发消息
	 * @param params
	 */
	public void modifySendMsg(Map<String, Object> params);
	
	/**
	 * 用户自身属性的开关
	 * @param params
	 */
	public void modifyAddSwitch(Map<String, Object> params);
	
	/**
	 * 修改生活圈是否可看的开关
	 * @param params
	 */
	public void modifyLifeSee(Map<String, Object> params);
	
	/**
	 * 保存不让他看我动态的关系
	 * @param params
	 */
	public void saveTaNoSeeOwnActive(Map<String, Object> params);
	
	/**
	 * 查询不让他看我动态的关系
	 * @param params
	 * @return
	 */
	public SeeControl queryTaNoSeeOwnActive(Map<String, Object> params);
	
	/**
	 * 更新不让他看我动态的关系
	 * @param params
	 */
	public void updateTaNoSeeOwnActive(Map<String, Object> params);
	
	/**
	 * 查询可看不看个人空间的用户列表
	 * @param params
	 * @return
	 */
	public List<SeeControl> querySeeControlState(Map<String, Object> params);
	
	/**
	 * 禁用/解禁用户
	 * @param params
	 */
	public void blockUser(Map<String, Object> params);
	
	
}
