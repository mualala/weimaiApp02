package com.liancheng.it.entity.admin;

import java.io.Serializable;
import java.sql.Timestamp;

public class Admin implements Serializable{
	
	private static final long serialVersionUID = 8027018318695197846L;
	
	private String admin_id;
	private int pic_id;//Í¼Æ¬µÄÎ»ÖÃ
	private String admin_name;
	private String password;
	private String logintime;
	private Timestamp exitime;
	
	/**
	 * @return the admin_id
	 */
	public String getAdmin_id() {
		return admin_id;
	}
	/**
	 * @param admin_id the admin_id to set
	 */
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	/**
	 * @return the pic_id
	 */
	public int getPic_id() {
		return pic_id;
	}
	/**
	 * @param pic_id the pic_id to set
	 */
	public void setPic_id(int pic_id) {
		this.pic_id = pic_id;
	}
	/**
	 * @return the admin_name
	 */
	public String getAdmin_name() {
		return admin_name;
	}
	/**
	 * @param admin_name the admin_name to set
	 */
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the logintime
	 */
	public String getLogintime() {
		return logintime;
	}
	/**
	 * @param logintime the logintime to set
	 */
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}
	/**
	 * @return the exitime
	 */
	public Timestamp getExitime() {
		return exitime;
	}
	/**
	 * @param exitime the exitime to set
	 */
	public void setExitime(Timestamp exitime) {
		this.exitime = exitime;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Admin [admin_id=" + admin_id + ", pic_id=" + pic_id
				+ ", admin_name=" + admin_name + ", password=" + password
				+ ", logintime=" + logintime + ", exitime=" + exitime + "]";
	}
	
}	
