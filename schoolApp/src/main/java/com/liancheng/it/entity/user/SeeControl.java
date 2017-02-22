package com.liancheng.it.entity.user;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 动态可看、不可看的的关系表
 */
public class SeeControl implements Serializable {

	private static final long serialVersionUID = 2244756677143135201L;

	private int see_id;
	private String user_id;//
	private String other_user_id;
	private String type;//1 不让TA看我动态 2 不看他的动态
	private int state;//0 关闭 1开启
	private Timestamp see_creatime;
	
	/**
	 * @return the see_id
	 */
	public int getSee_id() {
		return see_id;
	}
	/**
	 * @param see_id the see_id to set
	 */
	public void setSee_id(int see_id) {
		this.see_id = see_id;
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
	 * @return the other_user_id
	 */
	public String getOther_user_id() {
		return other_user_id;
	}
	/**
	 * @param other_user_id the other_user_id to set
	 */
	public void setOther_user_id(String other_user_id) {
		this.other_user_id = other_user_id;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}
	/**
	 * @return the see_creatime
	 */
	public Timestamp getSee_creatime() {
		return see_creatime;
	}
	/**
	 * @param see_creatime the see_creatime to set
	 */
	public void setSee_creatime(Timestamp see_creatime) {
		this.see_creatime = see_creatime;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SeeControl [see_id=" + see_id + ", user_id=" + user_id
				+ ", other_user_id=" + other_user_id + ", type=" + type
				+ ", state=" + state + ", see_creatime=" + see_creatime + "]";
	}
	
}
