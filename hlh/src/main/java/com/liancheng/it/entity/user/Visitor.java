package com.liancheng.it.entity.user;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 访客的实体类
 */
public class Visitor implements Serializable {

	private static final long serialVersionUID = 6643928910846571859L;
	
	private int v_id;
	private String v_user_id;//访客的云信id
	private String user_id;//user_id
	private Timestamp v_creatime;//访客的访问时间
	
	private String prifile;//用户的头像
	private String user_nickname;//用户的昵称
	private String school;
	private String major;
	private String birthday;
	
	/**
	 * @return the v_id
	 */
	public int getV_id() {
		return v_id;
	}
	/**
	 * @param v_id the v_id to set
	 */
	public void setV_id(int v_id) {
		this.v_id = v_id;
	}
	/**
	 * @return the v_user_id
	 */
	public String getV_user_id() {
		return v_user_id;
	}
	/**
	 * @param v_user_id the v_user_id to set
	 */
	public void setV_user_id(String v_user_id) {
		this.v_user_id = v_user_id;
	}
	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the v_creatime
	 */
	public Timestamp getV_creatime() {
		return v_creatime;
	}
	/**
	 * @param v_creatime the v_creatime to set
	 */
	public void setV_creatime(Timestamp v_creatime) {
		this.v_creatime = v_creatime;
	}
	/**
	 * @return the prifile
	 */
	public String getPrifile() {
		return prifile;
	}
	/**
	 * @param prifile the prifile to set
	 */
	public void setPrifile(String prifile) {
		this.prifile = prifile;
	}
	/**
	 * @return the user_nickname
	 */
	public String getUser_nickname() {
		return user_nickname;
	}
	/**
	 * @param user_nickname the user_nickname to set
	 */
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	/**
	 * @return the school
	 */
	public String getSchool() {
		return school;
	}
	/**
	 * @param school the school to set
	 */
	public void setSchool(String school) {
		this.school = school;
	}
	/**
	 * @return the major
	 */
	public String getMajor() {
		return major;
	}
	/**
	 * @param major the major to set
	 */
	public void setMajor(String major) {
		this.major = major;
	}
	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Visitor [v_id=" + v_id + ", v_user_id=" + v_user_id
				+ ", user_id=" + user_id + ", v_creatime=" + v_creatime
				+ ", prifile=" + prifile + ", user_nickname=" + user_nickname
				+ ", school=" + school + ", major=" + major + ", birthday="
				+ birthday + "]";
	}
	
}
