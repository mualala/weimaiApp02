package com.liancheng.it.entity.luntan;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 二级评论==多级评论
 * @author Administrator
 *
 */
public class TwoCommont implements Serializable {

	private static final long serialVersionUID = -3673730679333850181L;
	
	private int t_comm_id;
	private String user_id;
	private String other_user_id;
	private int comm_id;
	private String t_content;
	private Timestamp t_creatime;
	
	private String nickname;
	private String profile;
	private int level;
	
	/**
	 * @return the t_comm_id
	 */
	public int getT_comm_id() {
		return t_comm_id;
	}
	/**
	 * @param t_comm_id the t_comm_id to set
	 */
	public void setT_comm_id(int t_comm_id) {
		this.t_comm_id = t_comm_id;
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
	 * @return the comm_id
	 */
	public int getComm_id() {
		return comm_id;
	}
	/**
	 * @param comm_id the comm_id to set
	 */
	public void setComm_id(int comm_id) {
		this.comm_id = comm_id;
	}
	/**
	 * @return the t_content
	 */
	public String getT_content() {
		return t_content;
	}
	/**
	 * @param t_content the t_content to set
	 */
	public void setT_content(String t_content) {
		this.t_content = t_content;
	}
	/**
	 * @return the t_creatime
	 */
	public Timestamp getT_creatime() {
		return t_creatime;
	}
	/**
	 * @param t_creatime the t_creatime to set
	 */
	public void setT_creatime(Timestamp t_creatime) {
		this.t_creatime = t_creatime;
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
	 * @return the profile
	 */
	public String getProfile() {
		return profile;
	}
	/**
	 * @param profile the profile to set
	 */
	public void setProfile(String profile) {
		this.profile = profile;
	}
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	
	
}
