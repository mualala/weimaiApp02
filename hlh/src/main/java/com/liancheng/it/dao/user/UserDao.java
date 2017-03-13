package com.liancheng.it.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.liancheng.it.entity.user.ShoppingAddress;
import com.liancheng.it.entity.user.User;

public interface UserDao {
	
	/**
	 * 根据id查询用户
	 * @param user_id
	 * @return
	 */
	User queryUserById(String user_id);
	
	/**
	 * 根据用户号码查询
	 * @param phoneNum
	 * @return
	 */
	User queryUserByPhone(String phoneNum);//根据email或phoneNum
	
	/**
	 * 保存注册的用户
	 * @param user
	 */
	void saveUser(User user);
	
	/**
	 * 更新用户信息
	 * @param params
	 * @return
	 */
	int updateUser(Map<String, Object> params);
	
	/**
	 * 保存用户的收货地址
	 * @param params
	 * @return
	 */
	int saveShoppingAddress(Map<String, Object> params);
	
	/**
	 * 分页查询用户的收货地址
	 * @param params
	 * @return
	 */
	@Select("select * from shopping_address where user_id=#{user_id} limit #{start},#{end}")
	List<ShoppingAddress> queryPaginAddr(Map<String, Object> params);
	
	/**
	 * 分页收货地址的总的记录数
	 * @param user_id
	 * @return
	 */
	@Select("select count(0) from shopping_address where user_id=#{user_id}")
	int totalPaginAddr(String user_id);
	
	
	
}
