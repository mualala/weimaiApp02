package com.liancheng.it.dao.user;

import java.util.Map;

import com.liancheng.it.entity.user.User;

public interface UserDao {
	
	public User queryUserById(String user_id);
	public void saveUser(User user);//保存注册的用户
	public User queryUserByPhone(String phoneNum);//根据email或phoneNum
	
	
}
