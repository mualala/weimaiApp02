package com.liancheng.it.entity.user;

import java.io.Serializable;

public class StateSwitch implements Serializable {

	private static final long serialVersionUID = -2335161810650700772L;

	private int attention;//是否关注
	private int no_other_see;//不让TA看我的动态
	private int no_see_other;//不看TA的动态
	
	/**
	 * @return the attention
	 */
	public int getAttention() {
		return attention;
	}
	/**
	 * @param attention the attention to set
	 */
	public void setAttention(int attention) {
		this.attention = attention;
	}
	/**
	 * @return the no_other_see
	 */
	public int getNo_other_see() {
		return no_other_see;
	}
	/**
	 * @param no_other_see the no_other_see to set
	 */
	public void setNo_other_see(int no_other_see) {
		this.no_other_see = no_other_see;
	}
	/**
	 * @return the no_see_other
	 */
	public int getNo_see_other() {
		return no_see_other;
	}
	/**
	 * @param no_see_other the no_see_other to set
	 */
	public void setNo_see_other(int no_see_other) {
		this.no_see_other = no_see_other;
	}
	
}
