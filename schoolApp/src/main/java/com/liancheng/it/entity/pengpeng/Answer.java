package com.liancheng.it.entity.pengpeng;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户对问题直接评论的实体类
 */
public class Answer implements Serializable {

	private static final long serialVersionUID = -427152209366544592L;

	private int ans_id;//回答的id号
	private String user_id;//回答问题的用户uuid
	private int ques_id;//问题的id
	private String type;//1=评论；2=点赞；
	private String name_type;
	private String content;//回答的内容
	private int see;//浏览量
	private Timestamp ans_creatime;//回答的时间
	
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Answer [ans_id=" + ans_id + ", user_id=" + user_id
				+ ", ques_id=" + ques_id + ", type=" + type + ", name_type="
				+ name_type + ", content=" + content + ", see=" + see
				+ ", ans_creatime=" + ans_creatime + "]";
	}
	
}
