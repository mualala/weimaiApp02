package com.liancheng.it.entity.pengpeng;

import java.io.Serializable;
import java.sql.Timestamp;

public class TwoChildAnswer implements Serializable {

	private static final long serialVersionUID = 3489486989900694926L;
	
	private int two_child_id;
	private String user_id;
	private int child_ans_id;
	private String grand_user_id;
	private String content;
	private Timestamp two_child_ans_creatime;
	
	private String profile;
	private String user_nickname;
	private String gender;
	private String birthday;
	private String province;
	private String city;
	/**
	 * @return the two_child_id
	 */
	public int getTwo_child_id() {
		return two_child_id;
	}
	/**
	 * @param two_child_id the two_child_id to set
	 */
	public void setTwo_child_id(int two_child_id) {
		this.two_child_id = two_child_id;
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
	 * @return the child_ans_id
	 */
	public int getChild_ans_id() {
		return child_ans_id;
	}
	/**
	 * @param child_ans_id the child_ans_id to set
	 */
	public void setChild_ans_id(int child_ans_id) {
		this.child_ans_id = child_ans_id;
	}
	/**
	 * @return the grand_user_id
	 */
	public String getGrand_user_id() {
		return grand_user_id;
	}
	/**
	 * @param grand_user_id the grand_user_id to set
	 */
	public void setGrand_user_id(String grand_user_id) {
		this.grand_user_id = grand_user_id;
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
	 * @return the two_child_ans_creatime
	 */
	public Timestamp getTwo_child_ans_creatime() {
		return two_child_ans_creatime;
	}
	/**
	 * @param two_child_ans_creatime the two_child_ans_creatime to set
	 */
	public void setTwo_child_ans_creatime(Timestamp two_child_ans_creatime) {
		this.two_child_ans_creatime = two_child_ans_creatime;
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
	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TwoChildAnswer [two_child_id=" + two_child_id + ", user_id="
				+ user_id + ", child_ans_id=" + child_ans_id
				+ ", grand_user_id=" + grand_user_id + ", content=" + content
				+ ", two_child_ans_creatime=" + two_child_ans_creatime
				+ ", profile=" + profile + ", user_nickname=" + user_nickname
				+ ", gender=" + gender + ", birthday=" + birthday
				+ ", province=" + province + ", city=" + city + "]";
	}
	
}
