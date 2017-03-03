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
	private String phoneNum;//注册email或phoneNum
	private String nickname;//用户昵称
	private String password;//密码
	private String profile;//头像
	private int level;//等级
	private int age;//年龄
	private String gender;//性别
	private String mobile;//手机号码
	private String homeland;//家乡
	private String job;//职业
	private String label;//个人简介
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
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the homeland
	 */
	public String getHomeland() {
		return homeland;
	}
	/**
	 * @param homeland the homeland to set
	 */
	public void setHomeland(String homeland) {
		this.homeland = homeland;
	}
	/**
	 * @return the job
	 */
	public String getJob() {
		return job;
	}
	/**
	 * @param job the job to set
	 */
	public void setJob(String job) {
		this.job = job;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
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
				+ ", profile=" + profile + ", level=" + level + ", age=" + age
				+ ", gender=" + gender + ", mobile=" + mobile + ", homeland="
				+ homeland + ", job=" + job + ", label=" + label
				+ ", creatime=" + creatime + ", lastmodifytime="
				+ lastmodifytime + "]";
	}
	
}
