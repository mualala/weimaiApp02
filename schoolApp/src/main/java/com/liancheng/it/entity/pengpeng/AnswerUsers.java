package com.liancheng.it.entity.pengpeng;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户对问题的直接子回答
 */
public class AnswerUsers implements Serializable {

	private static final long serialVersionUID = -4524143625361111079L;

	private String user_id;
	private String name_type;
	private int ques_id;
	private String type;
	private String content;
	private Timestamp ans_creatime;
	private int ans_id;
	private String user_nickname;
	private String profile;
	private String gender;
	private String birthday;
	private String province;
	private String city;
	
	private int isLaud;//某用户对直接子评论是否点赞

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
	 * @return the name_type
	 */
	public String getName_type() {
		return name_type;
	}

	/**
	 * @param name_type the name_type to set
	 */
	public void setName_type(String name_type) {
		this.name_type = name_type;
	}

	/**
	 * @return the ques_id
	 */
	public int getQues_id() {
		return ques_id;
	}

	/**
	 * @param ques_id the ques_id to set
	 */
	public void setQues_id(int ques_id) {
		this.ques_id = ques_id;
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
	 * @return the ans_creatime
	 */
	public Timestamp getAns_creatime() {
		return ans_creatime;
	}

	/**
	 * @param ans_creatime the ans_creatime to set
	 */
	public void setAns_creatime(Timestamp ans_creatime) {
		this.ans_creatime = ans_creatime;
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

	/**
	 * @return the isLaud
	 */
	public int getIsLaud() {
		return isLaud;
	}

	/**
	 * @param isLaud the isLaud to set
	 */
	public void setIsLaud(int isLaud) {
		this.isLaud = isLaud;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AnswerUsers [user_id=" + user_id + ", name_type=" + name_type
				+ ", ques_id=" + ques_id + ", type=" + type + ", content="
				+ content + ", ans_creatime=" + ans_creatime + ", ans_id="
				+ ans_id + ", user_nickname=" + user_nickname + ", profile="
				+ profile + ", gender=" + gender + ", birthday=" + birthday
				+ ", province=" + province + ", city=" + city + ", isLaud="
				+ isLaud + "]";
	}

}
