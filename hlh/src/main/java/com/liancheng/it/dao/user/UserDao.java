package com.liancheng.it.dao.user;

import java.util.Map;

import com.liancheng.it.entity.user.User;

public interface UserDao {
	
	public User queryUserById(String user_id);
	public void saveUser(User user);//����ע����û�
	public User queryUserByPhone(String phoneNum);//����email��phoneNum
	
	
}
