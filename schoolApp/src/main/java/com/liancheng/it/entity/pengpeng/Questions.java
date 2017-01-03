package com.liancheng.it.entity.pengpeng;

import java.io.Serializable;
import java.sql.Timestamp;

public class Questions implements Serializable {

	private static final long serialVersionUID = -2152748641080622621L;
	
	private int ques_id;//发布问题的id
	private String question;//问题
	private Timestamp ques_creatime;//问题创建的时间
	private Timestamp ques_lastmodifytime;//问题的最后修改时间
	
	private String strQues_creatime;
	private int ans_id;//直接回答的id
	private String user_id;//直接回答用户的uuid
	private int count;//问题回答的次数
	private String type;//点赞还是评论
	private String name_type;//实名还是匿名
	
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
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * @return the ques_creatime
	 */
	public Timestamp getQues_creatime() {
		return ques_creatime;
	}
	/**
	 * @param ques_creatime the ques_creatime to set
	 */
	public void setQues_creatime(Timestamp ques_creatime) {
		this.ques_creatime = ques_creatime;
	}
	/**
	 * @return the ques_lastmodifytime
	 */
	public Timestamp getQues_lastmodifytime() {
		return ques_lastmodifytime;
	}
	/**
	 * @param ques_lastmodifytime the ques_lastmodifytime to set
	 */
	public void setQues_lastmodifytime(Timestamp ques_lastmodifytime) {
		this.ques_lastmodifytime = ques_lastmodifytime;
	}
	/**
	 * @return the strQues_creatime
	 */
	public String getStrQues_creatime() {
		return strQues_creatime;
	}
	/**
	 * @param strQues_creatime the strQues_creatime to set
	 */
	public void setStrQues_creatime(String strQues_creatime) {
		this.strQues_creatime = strQues_creatime;
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
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Questions [ques_id=" + ques_id + ", question=" + question
				+ ", ques_creatime=" + ques_creatime + ", ques_lastmodifytime="
				+ ques_lastmodifytime + ", strQues_creatime="
				+ strQues_creatime + ", ans_id=" + ans_id + ", user_id="
				+ user_id + ", count=" + count + ", type=" + type
				+ ", name_type=" + name_type + "]";
	}
	
}
