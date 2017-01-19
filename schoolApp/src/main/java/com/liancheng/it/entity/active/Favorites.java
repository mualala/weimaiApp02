package com.liancheng.it.entity.active;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用户收藏的动态实体类
 */
public class Favorites implements Serializable {

	private static final long serialVersionUID = 5902751488872754565L;
	
	private int favor_id;
	private String user_id;//收藏的人的uuid
	private String type;//扩展分类
	private int active_user_id;//动态的id
	private Timestamp favor_creatime;
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
	 * @return the favor_creatime
	 */
	public Timestamp getFavor_creatime() {
		return favor_creatime;
	}
	/**
	 * @param favor_creatime the favor_creatime to set
	 */
	public void setFavor_creatime(Timestamp favor_creatime) {
		this.favor_creatime = favor_creatime;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Favorites [favor_id=" + favor_id + ", user_id=" + user_id
				+ ", type=" + type + ", active_user_id=" + active_user_id
				+ ", favor_creatime=" + favor_creatime + "]";
	}
	
}
