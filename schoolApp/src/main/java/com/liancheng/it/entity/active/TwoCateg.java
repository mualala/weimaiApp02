package com.liancheng.it.entity.active;

import java.io.Serializable;

/**
 * ����app��ҳ�Զ����صĶ��������ʵ����
 */
public class TwoCateg implements Serializable {
	
	private static final long serialVersionUID = -5092731310406366485L;

	private String twoCateg;
	private String twoCategUrl;
	
	/**
	 * @return the twoCateg
	 */
	public String getTwoCateg() {
		return twoCateg;
	}
	/**
	 * @param twoCateg the twoCateg to set
	 */
	public void setTwoCateg(String twoCateg) {
		this.twoCateg = twoCateg;
	}
	/**
	 * @return the twoCategUrl
	 */
	public String getTwoCategUrl() {
		return twoCategUrl;
	}
	/**
	 * @param twoCategUrl the twoCategUrl to set
	 */
	public void setTwoCategUrl(String twoCategUrl) {
		this.twoCategUrl = twoCategUrl;
	}
	
	
}
