package com.liancheng.it.entity.user;

import java.io.Serializable;

public class UserChart implements Serializable {
	
	private static final long serialVersionUID = -1485580139104056416L;
	
	private String ym;//�����
	private String ymd;//�ꡢ�¡���
	private int countAddUser;//ÿ�������û���
	
	/**
	 * @return the ym
	 */
	public String getYm() {
		return ym;
	}
	/**
	 * @param ym the ym to set
	 */
	public void setYm(String ym) {
		this.ym = ym;
	}
	/**
	 * @return the ymd
	 */
	public String getYmd() {
		return ymd;
	}
	/**
	 * @param ymd the ymd to set
	 */
	public void setYmd(String ymd) {
		this.ymd = ymd;
	}
	/**
	 * @return the countAddUser
	 */
	public int getCountAddUser() {
		return countAddUser;
	}
	/**
	 * @param countAddUser the countAddUser to set
	 */
	public void setCountAddUser(int countAddUser) {
		this.countAddUser = countAddUser;
	}
	
}
