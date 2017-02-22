package com.liancheng.it.entity.admin;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * bannerͼ��ʵ����
 */
public class BannerPicReport implements Serializable {

	private static final long serialVersionUID = -3333711204693769826L;
	
	private int ban_id;
	private String title;//����
	private String ban_pic;//ͼƬ������
	private String in_jump;//�ڲ���ת����
	private String ban_url;//bannerͼ�ⲿ����ת·��
	private int total_see;//bannerͼ�����ת������
	private Timestamp ban_creatime;
	private Timestamp ban_lastmodify_time;//bannerͼ������޸�ʱ��
	
	private String str_ban_creatime;//���ں�̨����
	private String str_ban_lastmodify_time;//���ں�̨����
	
	/**
	 * @return the ban_id
	 */
	public int getBan_id() {
		return ban_id;
	}
	/**
	 * @param ban_id the ban_id to set
	 */
	public void setBan_id(int ban_id) {
		this.ban_id = ban_id;
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
	 * @return the ban_pic
	 */
	public String getBan_pic() {
		return ban_pic;
	}
	/**
	 * @param ban_pic the ban_pic to set
	 */
	public void setBan_pic(String ban_pic) {
		this.ban_pic = ban_pic;
	}
	/**
	 * @return the in_jump
	 */
	public String getIn_jump() {
		return in_jump;
	}
	/**
	 * @param in_jump the in_jump to set
	 */
	public void setIn_jump(String in_jump) {
		this.in_jump = in_jump;
	}
	/**
	 * @return the ban_url
	 */
	public String getBan_url() {
		return ban_url;
	}
	/**
	 * @param ban_url the ban_url to set
	 */
	public void setBan_url(String ban_url) {
		this.ban_url = ban_url;
	}
	/**
	 * @return the total_see
	 */
	public int getTotal_see() {
		return total_see;
	}
	/**
	 * @param total_see the total_see to set
	 */
	public void setTotal_see(int total_see) {
		this.total_see = total_see;
	}
	/**
	 * @return the ban_creatime
	 */
	public Timestamp getBan_creatime() {
		return ban_creatime;
	}
	/**
	 * @param ban_creatime the ban_creatime to set
	 */
	public void setBan_creatime(Timestamp ban_creatime) {
		this.ban_creatime = ban_creatime;
	}
	/**
	 * @return the ban_lastmodify_time
	 */
	public Timestamp getBan_lastmodify_time() {
		return ban_lastmodify_time;
	}
	/**
	 * @param ban_lastmodify_time the ban_lastmodify_time to set
	 */
	public void setBan_lastmodify_time(Timestamp ban_lastmodify_time) {
		this.ban_lastmodify_time = ban_lastmodify_time;
	}
	/**
	 * @return the str_ban_creatime
	 */
	public String getStr_ban_creatime() {
		return str_ban_creatime;
	}
	/**
	 * @param str_ban_creatime the str_ban_creatime to set
	 */
	public void setStr_ban_creatime(String str_ban_creatime) {
		this.str_ban_creatime = str_ban_creatime;
	}
	/**
	 * @return the str_ban_lastmodify_time
	 */
	public String getStr_ban_lastmodify_time() {
		return str_ban_lastmodify_time;
	}
	/**
	 * @param str_ban_lastmodify_time the str_ban_lastmodify_time to set
	 */
	public void setStr_ban_lastmodify_time(String str_ban_lastmodify_time) {
		this.str_ban_lastmodify_time = str_ban_lastmodify_time;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BannerPicReport [ban_id=" + ban_id + ", title=" + title
				+ ", ban_pic=" + ban_pic + ", in_jump=" + in_jump
				+ ", ban_url=" + ban_url + ", total_see=" + total_see
				+ ", ban_creatime=" + ban_creatime + ", ban_lastmodify_time="
				+ ban_lastmodify_time + ", str_ban_creatime="
				+ str_ban_creatime + ", str_ban_lastmodify_time="
				+ str_ban_lastmodify_time + "]";
	}
	
}
