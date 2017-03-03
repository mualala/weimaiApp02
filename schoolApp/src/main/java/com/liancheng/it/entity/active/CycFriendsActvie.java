package com.liancheng.it.entity.active;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 朋友圈动态查询的实体类
 */
public class CycFriendsActvie implements Serializable {
	
	private static final long serialVersionUID = -5774363105107125295L;
	
	private int active_user_id;
	private String user_id;
	private String user_nickname;
	private String profile;//用户的头像
	private int level;
	private String type_a;
	private String type_b;
	private String saysay;
	private String position;
	private String active_pic;
	private String docum;
	private String state;
	private String fans_see_state;
	private int see;
	private Timestamp active_creatime;
	
	private List<String> pics;//无关，返回图片的的集合
	private List<String> docums;
	private int totalCommont;//某条说说的评论数量总计
	private int totalLaud;//某条说说的点赞数量
	private int isLaud;//用户对某条说说的点赞状态
	private int isVerify;//某用户是否通过了验证;0=不是认证用户；1是认证用户
	/**
	 * @return the active_user_id
	 */
	public int getActive_user_id() {
		return active_user_id;
	}
	/**
	 * @param active_user_id the active_user_id to set
	 */
	public void setActive_user_id(int active_user_id) {
		this.active_user_id = active_user_id;
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
	 * @return the type_a
	 */
	public String getType_a() {
		return type_a;
	}
	/**
	 * @param type_a the type_a to set
	 */
	public void setType_a(String type_a) {
		this.type_a = type_a;
	}
	/**
	 * @return the type_b
	 */
	public String getType_b() {
		return type_b;
	}
	/**
	 * @param type_b the type_b to set
	 */
	public void setType_b(String type_b) {
		this.type_b = type_b;
	}
	/**
	 * @return the saysay
	 */
	public String getSaysay() {
		return saysay;
	}
	/**
	 * @param saysay the saysay to set
	 */
	public void setSaysay(String saysay) {
		this.saysay = saysay;
	}
	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * @return the active_pic
	 */
	public String getActive_pic() {
		return active_pic;
	}
	/**
	 * @param active_pic the active_pic to set
	 */
	public void setActive_pic(String active_pic) {
		this.active_pic = active_pic;
	}
	/**
	 * @return the docum
	 */
	public String getDocum() {
		return docum;
	}
	/**
	 * @param docum the docum to set
	 */
	public void setDocum(String docum) {
		this.docum = docum;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the fans_see_state
	 */
	public String getFans_see_state() {
		return fans_see_state;
	}
	/**
	 * @param fans_see_state the fans_see_state to set
	 */
	public void setFans_see_state(String fans_see_state) {
		this.fans_see_state = fans_see_state;
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
	 * @return the active_creatime
	 */
	public Timestamp getActive_creatime() {
		return active_creatime;
	}
	/**
	 * @param active_creatime the active_creatime to set
	 */
	public void setActive_creatime(Timestamp active_creatime) {
		this.active_creatime = active_creatime;
	}
	/**
	 * @return the pics
	 */
	public List<String> getPics() {
		return pics;
	}
	/**
	 * @param pics the pics to set
	 */
	public void setPics(List<String> pics) {
		this.pics = pics;
	}
	/**
	 * @return the docums
	 */
	public List<String> getDocums() {
		return docums;
	}
	/**
	 * @param docums the docums to set
	 */
	public void setDocums(List<String> docums) {
		this.docums = docums;
	}
	/**
	 * @return the totalCommont
	 */
	public int getTotalCommont() {
		return totalCommont;
	}
	/**
	 * @param totalCommont the totalCommont to set
	 */
	public void setTotalCommont(int totalCommont) {
		this.totalCommont = totalCommont;
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
	/**
	 * @return the isVerify
	 */
	public int getIsVerify() {
		return isVerify;
	}
	/**
	 * @param isVerify the isVerify to set
	 */
	public void setIsVerify(int isVerify) {
		this.isVerify = isVerify;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CycFriendsActvie [active_user_id=" + active_user_id
				+ ", user_id=" + user_id + ", user_nickname=" + user_nickname
				+ ", profile=" + profile + ", level=" + level + ", type_a="
				+ type_a + ", type_b=" + type_b + ", saysay=" + saysay
				+ ", position=" + position + ", active_pic=" + active_pic
				+ ", docum=" + docum + ", state=" + state + ", fans_see_state="
				+ fans_see_state + ", see=" + see + ", active_creatime="
				+ active_creatime + ", pics=" + pics + ", docums=" + docums
				+ ", totalCommont=" + totalCommont + ", totalLaud=" + totalLaud
				+ ", isLaud=" + isLaud + ", isVerify=" + isVerify + "]";
	}
	
}
