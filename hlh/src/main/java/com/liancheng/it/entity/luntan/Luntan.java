package com.liancheng.it.entity.luntan;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Luntan implements Serializable {

	private static final long serialVersionUID = -8137790796826958019L;
	
	private int lt_id;
	private String user_id;
	private String type;//分类
	private int lt_type;//1论坛  2 图文直播 0 默认值
	private String area;//发帖选择的地区
	private String lt_content;
	private String pic;
	private Timestamp lt_creatime;
	
	private String nickname;
	private String profile;
	private int level;
	private List<String> pics;
	/**
	 * @return the lt_id
	 */
	public int getLt_id() {
		return lt_id;
	}
	/**
	 * @param lt_id the lt_id to set
	 */
	public void setLt_id(int lt_id) {
		this.lt_id = lt_id;
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
	 * @return the lt_type
	 */
	public int getLt_type() {
		return lt_type;
	}
	/**
	 * @param lt_type the lt_type to set
	 */
	public void setLt_type(int lt_type) {
		this.lt_type = lt_type;
	}
	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}
	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * @return the lt_content
	 */
	public String getLt_content() {
		return lt_content;
	}
	/**
	 * @param lt_content the lt_content to set
	 */
	public void setLt_content(String lt_content) {
		this.lt_content = lt_content;
	}
	/**
	 * @return the pic
	 */
	public String getPic() {
		return pic;
	}
	/**
	 * @param pic the pic to set
	 */
	public void setPic(String pic) {
		this.pic = pic;
	}
	/**
	 * @return the lt_creatime
	 */
	public Timestamp getLt_creatime() {
		return lt_creatime;
	}
	/**
	 * @param lt_creatime the lt_creatime to set
	 */
	public void setLt_creatime(Timestamp lt_creatime) {
		this.lt_creatime = lt_creatime;
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
	
	
}
