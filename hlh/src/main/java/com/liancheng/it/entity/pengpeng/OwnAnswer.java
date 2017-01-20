package com.liancheng.it.entity.pengpeng;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 当前已登录用户对展示的已回答
 */
public class OwnAnswer implements Serializable {

	private static final long serialVersionUID = 1679051581111420202L;

	private int ques_id;
	private int ans_id;
	private String user_id;
	private String profile;
	private String user_nickname;
	private String gender;
	private String birthday;
	private String province;
	private String city;
	private String name_type;
	private String content;
	private int see;
	private Timestamp ans_creatime;
	
	private List<ChildAnswer> child_answers;
	private int totalLaud;//点赞数量
	private int totalCom;//评论量
	
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
	 * @return the see
	 */
	public int getSee() {
		return see;
	}
	/**
	 * @param see the see to set
	 */
	public void setSee(int see) {
		this.see = see;
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
	 * @return the child_answers
	 */
	public List<ChildAnswer> getChild_answers() {
		return child_answers;
	}
	/**
	 * @param child_answers the child_answers to set
	 */
	public void setChild_answers(List<ChildAnswer> child_answers) {
		this.child_answers = child_answers;
	}
	/**
	 * @return the totalLaud
	 */
	public int getTotalLaud() {
		return totalLaud;
	}
	/**
	 * @param totalLaud the totalLaud to set
	 */
	public void setTotalLaud(int totalLaud) {
		this.totalLaud = totalLaud;
	}
	/**
	 * @return the totalCom
	 */
	public int getTotalCom() {
		return totalCom;
	}
	/**
	 * @param totalCom the totalCom to set
	 */
	public void setTotalCom(int totalCom) {
		this.totalCom = totalCom;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OwnAnswer [ques_id=" + ques_id + ", ans_id=" + ans_id
				+ ", user_id=" + user_id + ", profile=" + profile
				+ ", user_nickname=" + user_nickname + ", gender=" + gender
				+ ", birthday=" + birthday + ", province=" + province
				+ ", city=" + city + ", name_type=" + name_type + ", content="
				+ content + ", see=" + see + ", ans_creatime=" + ans_creatime
				+ ", child_answers=" + child_answers + ", totalLaud="
				+ totalLaud + ", totalCom=" + totalCom + "]";
	}
	
}
