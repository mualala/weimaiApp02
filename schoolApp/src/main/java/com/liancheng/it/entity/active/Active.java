package com.liancheng.it.entity.active;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.liancheng.it.entity.user.User;

/**
 * 朋友圈动态的实体类(包括关注的用户和fans)
 */
public class Active implements Serializable{
	
	private static final long serialVersionUID = 3080851869788832612L;
	
	private int active_user_id;//活动表id
	private String user_id;//用户uuid
	private String type_a;//首页动态大类，校园生活、工作生活、秀一秀等
	private String type_b;//用于扩展分类
	private String saysay;//发表的说说
	private String title;//标题
	private String active_pic;//说说发表的图片
	private String docum;//上传的资源文件
	private String docum_size;//docum_size
	private String state;//状态数据的状态：0=审核中，1=审核通过，2=审核不通过
	private int see;//查看说说的数量
	private String doc_down_count;//文件下载次数
	private String f_see_state;//0=没查看；1=查看
	private Timestamp active_creatime;//说说创建的时间
	
	private List<String> pics;//无关，返回图片的的集合
	private List<String> docums;
	private int totalCommont;//某条说说的评论数量总计
	private String prifile;//用户的头像
	private int totalLaud;//某条说说的点赞数量
	private String user_nickname;//用户的昵称
	private int level;
	private String c_time;//用于后台报表
	private int isLaud;//用户对某条说说是否点赞
	private int isVerify;//某用户是否通过了验证;0=不是认证用户；1是认证用户
	private int favor_id;//收藏夹的id
	
	
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
	 * @return the type_a
	 */
	public String getType_a() {
		return type_a;
	}
	/**
	 * @param type_a the type_a to set
	 */
	public void setType_a(String type_a) {
		this.type_a = type_a;
	}
	/**
	 * @return the type_b
	 */
	public String getType_b() {
		return type_b;
	}
	/**
	 * @param type_b the type_b to set
	 */
	public void setType_b(String type_b) {
		this.type_b = type_b;
	}
	/**
	 * @return the saysay
	 */
	public String getSaysay() {
		return saysay;
	}
	/**
	 * @param saysay the saysay to set
	 */
	public void setSaysay(String saysay) {
		this.saysay = saysay;
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
	 * @return the active_pic
	 */
	public String getActive_pic() {
		return active_pic;
	}
	/**
	 * @param active_pic the active_pic to set
	 */
	public void setActive_pic(String active_pic) {
		this.active_pic = active_pic;
	}
	/**
	 * @return the docum
	 */
	public String getDocum() {
		return docum;
	}
	/**
	 * @param docum the docum to set
	 */
	public void setDocum(String docum) {
		this.docum = docum;
	}
	/**
	 * @return the docum_size
	 */
	public String getDocum_size() {
		return docum_size;
	}
	/**
	 * @param docum_size the docum_size to set
	 */
	public void setDocum_size(String docum_size) {
		this.docum_size = docum_size;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the see
	 */
	public int getSee() {
		return see;
	}
	/**
	 * @param see the see to set
	 */
	public void setSee(int see) {
		this.see = see;
	}
	/**
	 * @return the doc_down_count
	 */
	public String getDoc_down_count() {
		return doc_down_count;
	}
	/**
	 * @param doc_down_count the doc_down_count to set
	 */
	public void setDoc_down_count(String doc_down_count) {
		this.doc_down_count = doc_down_count;
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
	 * @return the active_creatime
	 */
	public Timestamp getActive_creatime() {
		return active_creatime;
	}
	/**
	 * @param active_creatime the active_creatime to set
	 */
	public void setActive_creatime(Timestamp active_creatime) {
		this.active_creatime = active_creatime;
	}
	/**
	 * @return the pics
	 */
	public List<String> getPics() {
		return pics;
	}
	/**
	 * @param pics the pics to set
	 */
	public void setPics(List<String> pics) {
		this.pics = pics;
	}
	/**
	 * @return the docums
	 */
	public List<String> getDocums() {
		return docums;
	}
	/**
	 * @param docums the docums to set
	 */
	public void setDocums(List<String> docums) {
		this.docums = docums;
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
	 * @return the prifile
	 */
	public String getPrifile() {
		return prifile;
	}
	/**
	 * @param prifile the prifile to set
	 */
	public void setPrifile(String prifile) {
		this.prifile = prifile;
	}
	/**
	 * @return the totalLaud
	 */
	public int getTotalLaud() {
		return totalLaud;
	}
	/**
	 * @param totalLaud the totalLaud to set
	 */
	public void setTotalLaud(int totalLaud) {
		this.totalLaud = totalLaud;
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
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * @return the c_time
	 */
	public String getC_time() {
		return c_time;
	}
	/**
	 * @param c_time the c_time to set
	 */
	public void setC_time(String c_time) {
		this.c_time = c_time;
	}
	/**
	 * @return the isLaud
	 */
	public int getIsLaud() {
		return isLaud;
	}
	/**
	 * @param isLaud the isLaud to set
	 */
	public void setIsLaud(int isLaud) {
		this.isLaud = isLaud;
	}
	/**
	 * @return the isVerify
	 */
	public int getIsVerify() {
		return isVerify;
	}
	/**
	 * @param isVerify the isVerify to set
	 */
	public void setIsVerify(int isVerify) {
		this.isVerify = isVerify;
	}
	/**
	 * @return the favor_id
	 */
	public int getFavor_id() {
		return favor_id;
	}
	/**
	 * @param favor_id the favor_id to set
	 */
	public void setFavor_id(int favor_id) {
		this.favor_id = favor_id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Active [active_user_id=" + active_user_id + ", user_id="
				+ user_id + ", type_a=" + type_a + ", type_b=" + type_b
				+ ", saysay=" + saysay + ", title=" + title + ", active_pic="
				+ active_pic + ", docum=" + docum + ", docum_size="
				+ docum_size + ", state=" + state + ", see=" + see
				+ ", doc_down_count=" + doc_down_count + ", f_see_state="
				+ f_see_state + ", active_creatime=" + active_creatime
				+ ", pics=" + pics + ", docums=" + docums + ", totalCommont="
				+ totalCommont + ", prifile=" + prifile + ", totalLaud="
				+ totalLaud + ", user_nickname=" + user_nickname + ", level="
				+ level + ", c_time=" + c_time + ", isLaud=" + isLaud
				+ ", isVerify=" + isVerify + ", favor_id=" + favor_id + "]";
	}
	
}
