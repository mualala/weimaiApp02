package com.liancheng.it.entity.user;

/**
 * 用户表
 */
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.liancheng.it.entity.active.Active;

public class User implements Serializable {
	
	private static final long serialVersionUID = 6060586885980643283L;
	
	private int schoolId;//校园号
	private String user_id;
	private String phoneNum;//电话号码
	private String password;//密码
	private String user_nickname;//用户名
	private String profile;//用户头像地址
	private String stu_state;//学生证验证的状态，0=审核中，1=审核通过，2=审核不通过
	private String certi_state;//毕业证验证的状态，0=审核中，1=审核通过，2=审核不通过
	private String stu_verify;//学生证图片
	private String certi_verify;//毕业证图片
	private String verify_state;//0=不是认证用户；1是认证用户
	private String gender;//性别
	private String star;//星座
	private String e_state;//情感状态
	private int level;//等级，多少颗星星
	private String province;//省份
	private String city;//市
//	private String area;//区
	private String county;//县
	private String birthday;//出生日期
	private String grade;//年级
	private String profession;//行业
	private String major;//专业
	private String school;//大学
	private String highschool;//高中
	private String lable;//个人签名
	private String skill;//个人技能
	private int life_see;//生活圈动态粉丝可见
	private int add_switch;//加好友是否需要验证
	private int send_msg;//是否可以发消息
	private String token;//接入云信所需的token
	private Timestamp creatime;//注册时间
	private Timestamp lastmodifytime;//最后一次更新时间
	
	private List<String> skills;
	private List<Active> actives;
	private String reportCreaTime;//用于后台报表
	private String reportLastTime;//用于后台报表
	
	private List<String> pics;//用于个人信息展示的4张图片
	private int oneActCount;//个人总的动态的数量
	private int visit;//访客数量
	private int fans;//粉丝数量
	private String home;//家乡
	
	/**
	 * @return the schoolId
	 */
	public int getSchoolId() {
		return schoolId;
	}
	/**
	 * @param schoolId the schoolId to set
	 */
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
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
	 * @return the phoneNum
	 */
	public String getPhoneNum() {
		return phoneNum;
	}
	/**
	 * @param phoneNum the phoneNum to set
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the stu_state
	 */
	public String getStu_state() {
		return stu_state;
	}
	/**
	 * @param stu_state the stu_state to set
	 */
	public void setStu_state(String stu_state) {
		this.stu_state = stu_state;
	}
	/**
	 * @return the certi_state
	 */
	public String getCerti_state() {
		return certi_state;
	}
	/**
	 * @param certi_state the certi_state to set
	 */
	public void setCerti_state(String certi_state) {
		this.certi_state = certi_state;
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
	 * @return the verify_state
	 */
	public String getVerify_state() {
		return verify_state;
	}
	/**
	 * @param verify_state the verify_state to set
	 */
	public void setVerify_state(String verify_state) {
		this.verify_state = verify_state;
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
	 * @return the star
	 */
	public String getStar() {
		return star;
	}
	/**
	 * @param star the star to set
	 */
	public void setStar(String star) {
		this.star = star;
	}
	/**
	 * @return the e_state
	 */
	public String getE_state() {
		return e_state;
	}
	/**
	 * @param e_state the e_state to set
	 */
	public void setE_state(String e_state) {
		this.e_state = e_state;
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
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the county
	 */
	public String getCounty() {
		return county;
	}
	/**
	 * @param county the county to set
	 */
	public void setCounty(String county) {
		this.county = county;
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
	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * @return the profession
	 */
	public String getProfession() {
		return profession;
	}
	/**
	 * @param profession the profession to set
	 */
	public void setProfession(String profession) {
		this.profession = profession;
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
	 * @return the highschool
	 */
	public String getHighschool() {
		return highschool;
	}
	/**
	 * @param highschool the highschool to set
	 */
	public void setHighschool(String highschool) {
		this.highschool = highschool;
	}
	/**
	 * @return the lable
	 */
	public String getLable() {
		return lable;
	}
	/**
	 * @param lable the lable to set
	 */
	public void setLable(String lable) {
		this.lable = lable;
	}
	/**
	 * @return the skill
	 */
	public String getSkill() {
		return skill;
	}
	/**
	 * @param skill the skill to set
	 */
	public void setSkill(String skill) {
		this.skill = skill;
	}
	/**
	 * @return the life_see
	 */
	public int getLife_see() {
		return life_see;
	}
	/**
	 * @param life_see the life_see to set
	 */
	public void setLife_see(int life_see) {
		this.life_see = life_see;
	}
	/**
	 * @return the add_switch
	 */
	public int getAdd_switch() {
		return add_switch;
	}
	/**
	 * @param add_switch the add_switch to set
	 */
	public void setAdd_switch(int add_switch) {
		this.add_switch = add_switch;
	}
	/**
	 * @return the send_msg
	 */
	public int getSend_msg() {
		return send_msg;
	}
	/**
	 * @param send_msg the send_msg to set
	 */
	public void setSend_msg(int send_msg) {
		this.send_msg = send_msg;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the creatime
	 */
	public Timestamp getCreatime() {
		return creatime;
	}
	/**
	 * @param creatime the creatime to set
	 */
	public void setCreatime(Timestamp creatime) {
		this.creatime = creatime;
	}
	/**
	 * @return the lastmodifytime
	 */
	public Timestamp getLastmodifytime() {
		return lastmodifytime;
	}
	/**
	 * @param lastmodifytime the lastmodifytime to set
	 */
	public void setLastmodifytime(Timestamp lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}
	/**
	 * @return the skills
	 */
	public List<String> getSkills() {
		return skills;
	}
	/**
	 * @param skills the skills to set
	 */
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	/**
	 * @return the actives
	 */
	public List<Active> getActives() {
		return actives;
	}
	/**
	 * @param actives the actives to set
	 */
	public void setActives(List<Active> actives) {
		this.actives = actives;
	}
	/**
	 * @return the reportCreaTime
	 */
	public String getReportCreaTime() {
		return reportCreaTime;
	}
	/**
	 * @param reportCreaTime the reportCreaTime to set
	 */
	public void setReportCreaTime(String reportCreaTime) {
		this.reportCreaTime = reportCreaTime;
	}
	/**
	 * @return the reportLastTime
	 */
	public String getReportLastTime() {
		return reportLastTime;
	}
	/**
	 * @param reportLastTime the reportLastTime to set
	 */
	public void setReportLastTime(String reportLastTime) {
		this.reportLastTime = reportLastTime;
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
	 * @return the oneActCount
	 */
	public int getOneActCount() {
		return oneActCount;
	}
	/**
	 * @param oneActCount the oneActCount to set
	 */
	public void setOneActCount(int oneActCount) {
		this.oneActCount = oneActCount;
	}
	/**
	 * @return the visit
	 */
	public int getVisit() {
		return visit;
	}
	/**
	 * @param visit the visit to set
	 */
	public void setVisit(int visit) {
		this.visit = visit;
	}
	/**
	 * @return the fans
	 */
	public int getFans() {
		return fans;
	}
	/**
	 * @param fans the fans to set
	 */
	public void setFans(int fans) {
		this.fans = fans;
	}
	/**
	 * @return the home
	 */
	public String getHome() {
		return home;
	}
	/**
	 * @param home the home to set
	 */
	public void setHome(String home) {
		this.home = home;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [schoolId=" + schoolId + ", user_id=" + user_id
				+ ", phoneNum=" + phoneNum + ", password=" + password
				+ ", user_nickname=" + user_nickname + ", profile=" + profile
				+ ", stu_state=" + stu_state + ", certi_state=" + certi_state
				+ ", stu_verify=" + stu_verify + ", certi_verify="
				+ certi_verify + ", verify_state=" + verify_state + ", gender="
				+ gender + ", star=" + star + ", e_state=" + e_state
				+ ", level=" + level + ", province=" + province + ", city="
				+ city + ", county=" + county + ", birthday=" + birthday
				+ ", grade=" + grade + ", profession=" + profession
				+ ", major=" + major + ", school=" + school + ", highschool="
				+ highschool + ", lable=" + lable + ", skill=" + skill
				+ ", life_see=" + life_see + ", add_switch=" + add_switch
				+ ", send_msg=" + send_msg + ", token=" + token + ", creatime="
				+ creatime + ", lastmodifytime=" + lastmodifytime + ", skills="
				+ skills + ", actives=" + actives + ", reportCreaTime="
				+ reportCreaTime + ", reportLastTime=" + reportLastTime
				+ ", pics=" + pics + ", oneActCount=" + oneActCount
				+ ", visit=" + visit + ", fans=" + fans + ", home=" + home
				+ "]";
	}
	
}
