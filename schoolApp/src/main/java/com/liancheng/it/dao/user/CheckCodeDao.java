package com.liancheng.it.dao.user;

import com.liancheng.it.entity.user.Coder;

public interface CheckCodeDao {
	
	public void saveCoder(Coder coder);
	public Coder findCheckCodeByPhoneNum(String phoneNum);
	public void deleteCoderByPhoneNum(String phoneNum);
}
