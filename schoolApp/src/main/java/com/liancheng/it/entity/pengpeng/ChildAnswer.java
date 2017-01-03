package com.liancheng.it.entity.pengpeng;

import java.io.Serializable;
import java.sql.Timestamp;

public class ChildAnswer implements Serializable {

	private static final long serialVersionUID = 8605153469804670693L;
	
	private int child_ans_id;
	private String user_id;
	private int ans_id;
	private String child_type;
	private String content;
	private Timestamp child_ans_creatime;
	
	private String profile;
	private String user_nickname;
	private String gender;
	private String birthday;
	private String province;
	private String city;
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
	 * @return the ans_id
	 */
	public int getAns_id() {
		return ans_id;
	}
	/**
	 * @param ans_id the ans_id to set
	 */
	public void setAns_id(int ans_id) {
		this.ans_id = ans_id;
	}
	/**
	 * @return the child_type
	 */
	public String getChild_type() {
		return child_type;
	}
	/**
	 * @param child_type the child_type to set
	 */
	public void setChild_type(String child_type) {
		this.child_type = child_type;
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
	 * @return the child_ans_creatime
	 */
	public Timestamp getChild_ans_creatime() {
		return child_ans_creatime;
	}
	/**
	 * @param child_ans_creatime the child_ans_creatime to set
	 */
	public void setChild_ans_creatime(Timestamp child_ans_creatime) {
		this.child_ans_creatime = child_ans_creatime;
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
		return "ChildAnswer [child_ans_id=" + child_ans_id + ", user_id="
				+ user_id + ", ans_id=" + ans_id + ", child_type=" + child_type
				+ ", content=" + content + ", child_ans_creatime="
				+ child_ans_creatime + ", profile=" + profile
				+ ", user_nickname=" + user_nickname + ", gender=" + gender
				+ ", birthday=" + birthday + ", province=" + province
				+ ", city=" + city + "]";
	}
	
}
