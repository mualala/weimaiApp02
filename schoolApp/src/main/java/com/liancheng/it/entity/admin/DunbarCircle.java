package com.liancheng.it.entity.admin;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 用于顿巴数同心圆的实体类
 */
public class DunbarCircle implements Serializable {

	private static final long serialVersionUID = -2696819834624825743L;

	private int id;
	private String content;
	private Timestamp last_modify_time;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the last_modify_time
	 */
	public Timestamp getLast_modify_time() {
		return last_modify_time;
	}
	/**
	 * @param last_modify_time the last_modify_time to set
	 */
	public void setLast_modify_time(Timestamp last_modify_time) {
		this.last_modify_time = last_modify_time;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DunbarCircle [id=" + id + ", content=" + content
				+ ", last_modify_time=" + last_modify_time + "]";
	}
	
	
	
}
