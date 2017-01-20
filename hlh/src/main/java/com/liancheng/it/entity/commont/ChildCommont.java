package com.liancheng.it.entity.commont;

import java.io.Serializable;
import java.sql.Timestamp;

import com.liancheng.it.entity.user.User;

/**
 * 子评论的实体类
 */
public class ChildCommont implements Serializable {

	private static final long serialVersionUID = 3227056107594055503L;
	
	private int child_id;
	private String child_user_id;
	private int com_id;
	private String parent_user_id;
	private String type;
	private String child_content;
	private Timestamp child_creatime;
	
	private String profile;//用户的头像
	private String user_nickname;//用户的昵称
	private int isChildLaud;//子评论是否点赞的标识
	private int totalChildLaud;//某条自说说的点赞数量
	
	/**
	 * @return the child_id
	 */
	public int getChild_id() {
		return child_id;
	}
	/**
	 * @param child_id the child_id to set
	 */
	public void setChild_id(int child_id) {
		this.child_id = child_id;
	}
	/**
	 * @return the child_user_id
	 */
	public String getChild_user_id() {
		return child_user_id;
	}
	/**
	 * @param child_user_id the child_user_id to set
	 */
	public void setChild_user_id(String child_user_id) {
		this.child_user_id = child_user_id;
	}
	/**
	 * @return the com_id
	 */
	public int getCom_id() {
		return com_id;
	}
	/**
	 * @param com_id the com_id to set
	 */
	public void setCom_id(int com_id) {
		this.com_id = com_id;
	}
	/**
	 * @return the parent_user_id
	 */
	public String getParent_user_id() {
		return parent_user_id;
	}
	/**
	 * @param parent_user_id the parent_user_id to set
	 */
	public void setParent_user_id(String parent_user_id) {
		this.parent_user_id = parent_user_id;
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
	 * @return the child_content
	 */
	public String getChild_content() {
		return child_content;
	}
	/**
	 * @param child_content the child_content to set
	 */
	public void setChild_content(String child_content) {
		this.child_content = child_content;
	}
	/**
	 * @return the child_creatime
	 */
	public Timestamp getChild_creatime() {
		return child_creatime;
	}
	/**
	 * @param child_creatime the child_creatime to set
	 */
	public void setChild_creatime(Timestamp child_creatime) {
		this.child_creatime = child_creatime;
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
	 * @return the isChildLaud
	 */
	public int getIsChildLaud() {
		return isChildLaud;
	}
	/**
	 * @param isChildLaud the isChildLaud to set
	 */
	public void setIsChildLaud(int isChildLaud) {
		this.isChildLaud = isChildLaud;
	}
	/**
	 * @return the totalChildLaud
	 */
	public int getTotalChildLaud() {
		return totalChildLaud;
	}
	/**
	 * @param totalChildLaud the totalChildLaud to set
	 */
	public void setTotalChildLaud(int totalChildLaud) {
		this.totalChildLaud = totalChildLaud;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ChildCommont [child_id=" + child_id + ", child_user_id="
				+ child_user_id + ", com_id=" + com_id + ", parent_user_id="
				+ parent_user_id + ", type=" + type + ", child_content="
				+ child_content + ", child_creatime=" + child_creatime
				+ ", profile=" + profile + ", user_nickname=" + user_nickname
				+ ", isChildLaud=" + isChildLaud + ", totalChildLaud="
				+ totalChildLaud + "]";
	}
	
}
