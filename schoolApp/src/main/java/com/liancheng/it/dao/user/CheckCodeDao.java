package com.liancheng.it.dao.user;

import com.liancheng.it.entity.user.Coder;

public interface CheckCodeDao {
	
	/**
	 * 保存验证码
	 * @param coder
	 */
	public void saveCoder(Coder coder);
	
	/**
	 * 根据用户的电话查询验证码
	 * @param phoneNum
	 * @return
	 */
	public Coder findCheckCodeByPhoneNum(String phoneNum);
	
	/**
	 * 根据用户的电话号码删除该用户的验证码记录
	 * @param phoneNum
	 */
	public void deleteCoderByPhoneNum(String phoneNum);
	
}
