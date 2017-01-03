package com.liancheng.it.entity.commont;

import java.io.Serializable;

/**
 * 说说的点赞用户列表实体类
 */
public class LaudUser implements Serializable {

	private static final long serialVersionUID = -6828905088648967559L;
	
	private String profile;
	private String user_nickname;
	
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LaudUser [profile=" + profile + ", user_nickname="
				+ user_nickname + "]";
	}

}
