package com.liancheng.it.entity.active;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 首页大类动态展示的实体类
 */
public class ClassActive implements Serializable {

	private static final long serialVersionUID = -6592865588297408506L;
	
	private int active_user_id;
	private String user_id;
	private int level;
	private String user_nickname;
	private String type_a;
	private String type_b;
	private String saysay;
	private String title;
	private String active_pic;
	private String docum;
	private String docum_size;
	private String state;
	private int see;
	private String doc_down_count;//文件下载次数
	private Timestamp active_creatime;
	
	private List<String> pics;//无关，返回图片的的集合
	private List<String> docums;//文件的集合
	private int totalCommont;//某条说说的评论数量总计
	private String profile;//用户的头像
	private int totalLaud;//某条说说的点赞数量
	private int isLaud;//用户对某条说说是否点赞
	private int isVerify;//某用户是否通过了验证;0=不是认证用户；1是认证用户
	private int isFavor;//用户是否收藏
	private int favor_id;//收藏的id
	
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the docum_size
	 */
	public String getDocum_size() {
		return docum_size;
	}
	/**
	 * @param docum_size the docum_size to set
	 */
	public void setDocum_size(String docum_size) {
		this.docum_size = docum_size;
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
	 * @return the doc_down_count
	 */
	public String getDoc_down_count() {
		return doc_down_count;
	}
	/**
	 * @param doc_down_count the doc_down_count to set
	 */
	public void setDoc_down_count(String doc_down_count) {
		this.doc_down_count = doc_down_count;
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
	/**
	 * @return the isFavor
	 */
	public int getIsFavor() {
		return isFavor;
	}
	/**
	 * @param isFavor the isFavor to set
	 */
	public void setIsFavor(int isFavor) {
		this.isFavor = isFavor;
	}
	/**
	 * @return the favor_id
	 */
	public int getFavor_id() {
		return favor_id;
	}
	/**
	 * @param favor_id the favor_id to set
	 */
	public void setFavor_id(int favor_id) {
		this.favor_id = favor_id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClassActive [active_user_id=" + active_user_id + ", user_id="
				+ user_id + ", level=" + level + ", user_nickname="
				+ user_nickname + ", type_a=" + type_a + ", type_b=" + type_b
				+ ", saysay=" + saysay + ", title=" + title + ", active_pic="
				+ active_pic + ", docum=" + docum + ", docum_size="
				+ docum_size + ", state=" + state + ", see=" + see
				+ ", doc_down_count=" + doc_down_count + ", active_creatime="
				+ active_creatime + ", pics=" + pics + ", docums=" + docums
				+ ", totalCommont=" + totalCommont + ", profile=" + profile
				+ ", totalLaud=" + totalLaud + ", isLaud=" + isLaud
				+ ", isVerify=" + isVerify + ", isFavor=" + isFavor
				+ ", favor_id=" + favor_id + "]";
	}
	
}
