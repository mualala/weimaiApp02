package com.liancheng.it.entity.luntan;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 一级评论的bean
 */
public class Commont implements Serializable {

	private static final long serialVersionUID = 5532466421100186552L;

	private int comm_id;
	private String user_id;
	private String other_user_id;
	private int lt_id;
	private String content;
	private Timestamp comm_creatime;
	
	private String nickname;
	private String profile;
	private int level;
	
	private List<TwoCommont> multiComms;

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
	 * @return the lt_id
	 */
	public int getLt_id() {
		return lt_id;
	}

	/**
	 * @param lt_id the lt_id to set
	 */
	public void setLt_id(int lt_id) {
		this.lt_id = lt_id;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the comm_creatime
	 */
	public Timestamp getComm_creatime() {
		return comm_creatime;
	}

	/**
	 * @param comm_creatime the comm_creatime to set
	 */
	public void setComm_creatime(Timestamp comm_creatime) {
		this.comm_creatime = comm_creatime;
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

	/**
	 * @return the multiComms
	 */
	public List<TwoCommont> getMultiComms() {
		return multiComms;
	}

	/**
	 * @param multiComms the multiComms to set
	 */
	public void setMultiComms(List<TwoCommont> multiComms) {
		this.multiComms = multiComms;
	}
	
}
