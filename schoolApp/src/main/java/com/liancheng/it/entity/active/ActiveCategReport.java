package com.liancheng.it.entity.active;

import java.io.Serializable;

public class ActiveCategReport implements Serializable {

	private static final long serialVersionUID = 3506150587931328815L;

	private String themeType;//主题大类
	private String twoType;//二级分类
	private int activeCount;//分类的数量count
	/**
	 * @return the themeType
	 */
	public String getThemeType() {
		return themeType;
	}
	/**
	 * @param themeType the themeType to set
	 */
	public void setThemeType(String themeType) {
		this.themeType = themeType;
	}
	/**
	 * @return the twoType
	 */
	public String getTwoType() {
		return twoType;
	}
	/**
	 * @param twoType the twoType to set
	 */
	public void setTwoType(String twoType) {
		this.twoType = twoType;
	}
	/**
	 * @return the activeCount
	 */
	public int getActiveCount() {
		return activeCount;
	}
	/**
	 * @param activeCount the activeCount to set
	 */
	public void setActiveCount(int activeCount) {
		this.activeCount = activeCount;
	}
	
	
	
}
