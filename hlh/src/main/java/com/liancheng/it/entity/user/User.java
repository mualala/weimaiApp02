package com.liancheng.it.entity.user;

/**
 * 用户表
 */
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.liancheng.it.entity.active.Active;

public class User implements Serializable {

	private static final long serialVersionUID = -2184344802610554732L;
	
	private String user_id;//用户的uuid
	private String phoneNum;//email或phoneNum
	private String nickname;//用户昵称
	private String password;//密码

	private Timestamp creatime;//注册时间
	private Timestamp lastmodifytime;//最后一次更新时间
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
	 * @return the phoneNum
	 */
	public String getPhoneNum() {
		return phoneNum;
	}
	/**
	 * @param phoneNum the phoneNum to set
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	 * @return the creatime
	 */
	public Timestamp getCreatime() {
		return creatime;
	}
	/**
	 * @param creatime the creatime to set
	 */
	public void setCreatime(Timestamp creatime) {
		this.creatime = creatime;
	}
	/**
	 * @return the lastmodifytime
	 */
	public Timestamp getLastmodifytime() {
		return lastmodifytime;
	}
	/**
	 * @param lastmodifytime the lastmodifytime to set
	 */
	public void setLastmodifytime(Timestamp lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", phoneNum=" + phoneNum
				+ ", nickname=" + nickname + ", password=" + password
				+ ", creatime=" + creatime + ", lastmodifytime="
				+ lastmodifytime + "]";
	}
	
}
