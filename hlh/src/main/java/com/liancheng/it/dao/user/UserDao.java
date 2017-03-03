package com.liancheng.it.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.liancheng.it.entity.user.ShoppingAddress;
import com.liancheng.it.entity.user.User;

public interface UserDao {
	
	User queryUserById(String user_id);
	User queryUserByPhone(String phoneNum);//根据email或phoneNum
	void saveUser(User user);//保存注册的用户
	int updateUser(Map<String, Object> params);//修改用户
	int saveShoppingAddress(Map<String, Object> params);
	
	@Select("select * from shopping_address where user_id=#{user_id} limit #{start},#{end}")
	List<ShoppingAddress> queryPaginAddr(Map<String, Object> params);
	@Select("select count(0) from shopping_address where user_id=#{user_id}")
	int totalPaginAddr(String user_id);
	
	
	
}
