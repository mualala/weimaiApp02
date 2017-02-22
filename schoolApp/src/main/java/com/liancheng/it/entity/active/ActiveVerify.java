package com.liancheng.it.entity.active;

import java.io.Serializable;
import java.sql.Timestamp;

public class ActiveVerify implements Serializable {

	private static final long serialVersionUID = 4915800919646355743L;
	
	private int act_verify_id;
	private String class_active;//大类的分类
	private String theme_pic;//主题的图标
	private String two_class;//二级分类
	private String two_pic;//二级分类
	private String stu_verify;//0=学生验证关闭，1=学生验证开启
	private String certi_verify;//0=毕业证验证关闭，1=毕业证验证开启
	private Timestamp act_creatime;
	private Timestamp act_lastmodifytime;
	
	/**
	 * @return the act_verify_id
	 */
	public int getAct_verify_id() {
		return act_verify_id;
	}
	/**
	 * @param act_verify_id the act_verify_id to set
	 */
	public void setAct_verify_id(int act_verify_id) {
		this.act_verify_id = act_verify_id;
	}
	/**
	 * @return the class_active
	 */
	public String getClass_active() {
		return class_active;
	}
	/**
	 * @param class_active the class_active to set
	 */
	public void setClass_active(String class_active) {
		this.class_active = class_active;
	}
	/**
	 * @return the two_class
	 */
	public String getTwo_class() {
		return two_class;
	}
	/**
	 * @param two_class the two_class to set
	 */
	public void setTwo_class(String two_class) {
		this.two_class = two_class;
	}
	/**
	 * @return the theme_pic
	 */
	public String getTheme_pic() {
		return theme_pic;
	}
	/**
	 * @param theme_pic the theme_pic to set
	 */
	public void setTheme_pic(String theme_pic) {
		this.theme_pic = theme_pic;
	}
	/**
	 * @return the two_pic
	 */
	public String getTwo_pic() {
		return two_pic;
	}
	/**
	 * @param two_pic the two_pic to set
	 */
	public void setTwo_pic(String two_pic) {
		this.two_pic = two_pic;
	}
	/**
	 * @return the stu_verify
	 */
	public String getStu_verify() {
		return stu_verify;
	}
	/**
	 * @param stu_verify the stu_verify to set
	 */
	public void setStu_verify(String stu_verify) {
		this.stu_verify = stu_verify;
	}
	/**
	 * @return the certi_verify
	 */
	public String getCerti_verify() {
		return certi_verify;
	}
	/**
	 * @param certi_verify the certi_verify to set
	 */
	public void setCerti_verify(String certi_verify) {
		this.certi_verify = certi_verify;
	}
	/**
	 * @return the act_creatime
	 */
	public Timestamp getAct_creatime() {
		return act_creatime;
	}
	/**
	 * @param act_creatime the act_creatime to set
	 */
	public void setAct_creatime(Timestamp act_creatime) {
		this.act_creatime = act_creatime;
	}
	/**
	 * @return the act_lastmodifytime
	 */
	public Timestamp getAct_lastmodifytime() {
		return act_lastmodifytime;
	}
	/**
	 * @param act_lastmodifytime the act_lastmodifytime to set
	 */
	public void setAct_lastmodifytime(Timestamp act_lastmodifytime) {
		this.act_lastmodifytime = act_lastmodifytime;
	}
	
}
