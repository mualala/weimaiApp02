package com.liancheng.it.entity.active;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 存储动态搜索关键词的实体类
 */
public class TextKey implements Serializable {

	private static final long serialVersionUID = -6137389330122810359L;
	
	private int text_id;
	private String text;
	private Timestamp text_creatime;
	
	/**
	 * @return the text_id
	 */
	public int getText_id() {
		return text_id;
	}
	/**
	 * @param text_id the text_id to set
	 */
	public void setText_id(int text_id) {
		this.text_id = text_id;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the text_creatime
	 */
	public Timestamp getText_creatime() {
		return text_creatime;
	}
	/**
	 * @param text_creatime the text_creatime to set
	 */
	public void setText_creatime(Timestamp text_creatime) {
		this.text_creatime = text_creatime;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TextKey [text_id=" + text_id + ", text=" + text
				+ ", text_creatime=" + text_creatime + "]";
	}

}
