package com.liancheng.it.entity.active;

import java.io.Serializable;
import java.util.List;

/**
 * 用于app首页自动加载的主题分类的实体类
 */
public class ThemeCateg implements Serializable {

	private static final long serialVersionUID = 2045122939399100905L;

	private String themeCateg;
	private String themeCategUrl;
	
	private List<TwoCateg> twoCategs;

	/**
	 * @return the themeCateg
	 */
	public String getThemeCateg() {
		return themeCateg;
	}

	/**
	 * @param themeCateg the themeCateg to set
	 */
	public void setThemeCateg(String themeCateg) {
		this.themeCateg = themeCateg;
	}

	/**
	 * @return the themeCategUrl
	 */
	public String getThemeCategUrl() {
		return themeCategUrl;
	}

	/**
	 * @param themeCategUrl the themeCategUrl to set
	 */
	public void setThemeCategUrl(String themeCategUrl) {
		this.themeCategUrl = themeCategUrl;
	}

	/**
	 * @return the twoCategs
	 */
	public List<TwoCateg> getTwoCategs() {
		return twoCategs;
	}

	/**
	 * @param twoCategs the twoCategs to set
	 */
	public void setTwoCategs(List<TwoCateg> twoCategs) {
		this.twoCategs = twoCategs;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ThemeCateg [themeCateg=" + themeCateg + ", themeCategUrl="
				+ themeCategUrl + ", twoCategs=" + twoCategs + "]";
	}
	
}
