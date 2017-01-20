package com.liancheng.it.entity.commont;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * ֱ�����۵�ʵ����
 */
public class Commont implements Serializable {
	
	private static final long serialVersionUID = 6843617332021047294L;
	
	private int com_id;//action��id
	private String com_user_id;//�����û���uuid
	private String user_id;//����̬�û���uuid
	private int active_user_id;//��̬�ĵ�id
	private String type;//1=���ۣ�2=����
	private String content;//���۵�����
	private String see_state;//˵˵��ֱ�����۵Ĳ鿴״̬;0=δ�鿴��1=�鿴
	private Timestamp com_creatime;//action������ʱ��
	
	private int totalCommont;//˵˵����������
	private ArrayList<ChildCommont> childcomms;//���۵�����
	private String profile;//�û���ͷ��
	private String user_nickname;//�û����ǳ�
	private int totalChildRev;//˵˵�������۵Ļظ�����
	
	/**
	 * @return the com_id
	 */
	public int getCom_id() {
		return com_id;
	}
	/**
	 * @param com_id the com_id to set
	 */
	public void setCom_id(int com_id) {
		this.com_id = com_id;
	}
	/**
	 * @return the com_user_id
	 */
	public String getCom_user_id() {
		return com_user_id;
	}
	/**
	 * @param com_user_id the com_user_id to set
	 */
	public void setCom_user_id(String com_user_id) {
		this.com_user_id = com_user_id;
	}
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
	 * @return the active_user_id
	 */
	public int getActive_user_id() {
		return active_user_id;
	}
	/**
	 * @param active_user_id the active_user_id to set
	 */
	public void setActive_user_id(int active_user_id) {
		this.active_user_id = active_user_id;
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
	 * @return the see_state
	 */
	public String getSee_state() {
		return see_state;
	}
	/**
	 * @param see_state the see_state to set
	 */
	public void setSee_state(String see_state) {
		this.see_state = see_state;
	}
	/**
	 * @return the com_creatime
	 */
	public Timestamp getCom_creatime() {
		return com_creatime;
	}
	/**
	 * @param com_creatime the com_creatime to set
	 */
	public void setCom_creatime(Timestamp com_creatime) {
		this.com_creatime = com_creatime;
	}
	/**
	 * @return the totalCommont
	 */
	public int getTotalCommont() {
		return totalCommont;
	}
	/**
	 * @param totalCommont the totalCommont to set
	 */
	public void setTotalCommont(int totalCommont) {
		this.totalCommont = totalCommont;
	}
	/**
	 * @return the childcomms
	 */
	public ArrayList<ChildCommont> getChildcomms() {
		return childcomms;
	}
	/**
	 * @param childcomms the childcomms to set
	 */
	public void setChildcomms(ArrayList<ChildCommont> childcomms) {
		this.childcomms = childcomms;
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
	 * @return the totalChildRev
	 */
	public int getTotalChildRev() {
		return totalChildRev;
	}
	/**
	 * @param totalChildRev the totalChildRev to set
	 */
	public void setTotalChildRev(int totalChildRev) {
		this.totalChildRev = totalChildRev;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Commont [com_id=" + com_id + ", com_user_id=" + com_user_id
				+ ", user_id=" + user_id + ", active_user_id=" + active_user_id
				+ ", type=" + type + ", content=" + content + ", see_state="
				+ see_state + ", com_creatime=" + com_creatime
				+ ", totalCommont=" + totalCommont + ", childcomms="
				+ childcomms + ", profile=" + profile + ", user_nickname="
				+ user_nickname + ", totalChildRev=" + totalChildRev + "]";
	}
	
}
