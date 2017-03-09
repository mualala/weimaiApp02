package com.liancheng.it.dao.user;

import com.liancheng.it.entity.user.Coder;

public interface CheckCodeDao {
	
	/**
	 * ������֤��
	 * @param coder
	 */
	public void saveCoder(Coder coder);
	
	/**
	 * �����û��ĵ绰��ѯ��֤��
	 * @param phoneNum
	 * @return
	 */
	public Coder findCheckCodeByPhoneNum(String phoneNum);
	
	/**
	 * �����û��ĵ绰����ɾ�����û�����֤���¼
	 * @param phoneNum
	 */
	public void deleteCoderByPhoneNum(String phoneNum);
	
}
