package com.liancheng.it.entity.admin;

import java.io.Serializable;
import java.sql.Timestamp;

public class Admin implements Serializable{
	
	private static final long serialVersionUID = 8027018318695197846L;
	
	private String admin_id;
	private String admin_name;
	private String password;
	private String logintime;
	private Timestamp exitime;
	
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLogintime() {
		return logintime;
	}
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}
	public Timestamp getExitime() {
		return exitime;
	}
	public void setExitime(Timestamp exitime) {
		this.exitime = exitime;
	}
	
	@Override
	public String toString() {
		return "Admin [admin_id=" + admin_id + ", admin_name=" + admin_name
				+ ", password=" + password + ", logintime=" + logintime
				+ ", exitime=" + exitime + "]";
	}
	
}	
