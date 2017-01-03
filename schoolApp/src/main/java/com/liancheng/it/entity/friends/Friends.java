package com.liancheng.it.entity.friends;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ���Ѻͷ�˿��ʵ����
 * @author Administrator
 */
public class Friends implements Serializable {
	
	private static final long serialVersionUID = -1774392339456776942L;
	
	private String user_id;//�ٷ���Ӻ��ѵ�uuid ��������ӹ�ע��uuid
	private String f_user_id;//�ٺ��ѵ�uuid �ڱ���ע���û�
	private String f_see_state;//�����Ƿ�ɲ鿴��̬��0=���ܿ���̬��1=�ܿ���̬
	private String fans_see_state;//fans�Ƿ�ɲ鿴��̬��0=���ܿ���̬��1=�ܿ���̬
	private String type;//0=���ѣ�1=��ע
	private Timestamp f_creatime;//������ѵ�ʱ��
	
	private String profile;
	private String user_nickname;
	private String school;
	private String major;
	private String gender;
	private String birthday;
	
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
	 * @return the f_user_id
	 */
	public String getF_user_id() {
		return f_user_id;
	}
	/**
	 * @param f_user_id the f_user_id to set
	 */
	public void setF_user_id(String f_user_id) {
		this.f_user_id = f_user_id;
	}
	/**
	 * @return the f_see_state
	 */
	public String getF_see_state() {
		return f_see_state;
	}
	/**
	 * @param f_see_state the f_see_state to set
	 */
	public void setF_see_state(String f_see_state) {
		this.f_see_state = f_see_state;
	}
	/**
	 * @return the fans_see_state
	 */
	public String getFans_see_state() {
		return fans_see_state;
	}
	/**
	 * @param fans_see_state the fans_see_state to set
	 */
	public void setFans_see_state(String fans_see_state) {
		this.fans_see_state = fans_see_state;
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
	 * @return the f_creatime
	 */
	public Timestamp getF_creatime() {
		return f_creatime;
	}
	/**
	 * @param f_creatime the f_creatime to set
	 */
	public void setF_creatime(Timestamp f_creatime) {
		this.f_creatime = f_creatime;
	}
	/**
	 * @return the profile
	 */
	public String getProfile() {
		return profile;
	}
	/**
	 * @param profile the profile to set
	 */
	public void setProfile(String profile) {
		this.profile = profile;
	}
	/**
	 * @return the user_nickname
	 */
	public String getUser_nickname() {
		return user_nickname;
	}
	/**
	 * @param user_nickname the user_nickname to set
	 */
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	/**
	 * @return the school
	 */
	public String getSchool() {
		return school;
	}
	/**
	 * @param school the school to set
	 */
	public void setSchool(String school) {
		this.school = school;
	}
	/**
	 * @return the major
	 */
	public String getMajor() {
		return major;
	}
	/**
	 * @param major the major to set
	 */
	public void setMajor(String major) {
		this.major = major;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Friends [user_id=" + user_id + ", f_user_id=" + f_user_id
				+ ", f_see_state=" + f_see_state + ", fans_see_state="
				+ fans_see_state + ", type=" + type + ", f_creatime="
				+ f_creatime + ", profile=" + profile + ", user_nickname="
				+ user_nickname + ", school=" + school + ", major=" + major
				+ ", gender=" + gender + ", birthday=" + birthday + "]";
	}
	
}
