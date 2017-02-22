package com.liancheng.it.entity.admin;

import java.io.Serializable;
import java.sql.Timestamp;

public class AdminLoginInfo implements Serializable {

	private static final long serialVersionUID = -4774155884443972651L;

	private int id;
	private String admin_id;
	private int role_id;
	private String login_ip;
	private String source;
	private String browser_info;
	private Timestamp login_time;
	private Timestamp exit_time;
	
	private String strLoginTime;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

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
	 * @return the role_id
	 */
	public int getRole_id() {
		return role_id;
	}

	/**
	 * @param role_id the role_id to set
	 */
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	/**
	 * @return the login_ip
	 */
	public String getLogin_ip() {
		return login_ip;
	}

	/**
	 * @param login_ip the login_ip to set
	 */
	public void setLogin_ip(String login_ip) {
		this.login_ip = login_ip;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the browser_info
	 */
	public String getBrowser_info() {
		return browser_info;
	}

	/**
	 * @param browser_info the browser_info to set
	 */
	public void setBrowser_info(String browser_info) {
		this.browser_info = browser_info;
	}

	/**
	 * @return the login_time
	 */
	public Timestamp getLogin_time() {
		return login_time;
	}

	/**
	 * @param login_time the login_time to set
	 */
	public void setLogin_time(Timestamp login_time) {
		this.login_time = login_time;
	}

	/**
	 * @return the exit_time
	 */
	public Timestamp getExit_time() {
		return exit_time;
	}

	/**
	 * @param exit_time the exit_time to set
	 */
	public void setExit_time(Timestamp exit_time) {
		this.exit_time = exit_time;
	}

	/**
	 * @return the strLoginTime
	 */
	public String getStrLoginTime() {
		return strLoginTime;
	}

	/**
	 * @param strLoginTime the strLoginTime to set
	 */
	public void setStrLoginTime(String strLoginTime) {
		this.strLoginTime = strLoginTime;
	}
	
}
