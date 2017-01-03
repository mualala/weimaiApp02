package com.liancheng.it.entity.active;

import java.io.Serializable;

public class ActiveVerify implements Serializable {

	private static final long serialVersionUID = 4915800919646355743L;
	
	private int act_verify_id;
	private String class_active;//大类的分类
	private String stu_verify;//0=学生验证关闭，1=学生验证开启
	private String certi_verify;//0=毕业证验证关闭，1=毕业证验证开启
	
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "active_verify [act_verify_id=" + act_verify_id
				+ ", class_active=" + class_active + ", stu_verify="
				+ stu_verify + ", certi_verify=" + certi_verify + "]";
	}
	
}
