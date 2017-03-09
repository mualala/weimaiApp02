package com.liancheng.it.service.friends;


import java.util.List;

import com.liancheng.it.entity.friends.Friends;

import net.minidev.json.JSONObject;

public interface FriendsService {
	
	/**
	 * 请求加好友,调用云信接口
	 * @param user_id
	 * @param f_user_id
	 * @param msg
	 * @param type
	 * @return
	 */
	public JSONObject reqAddFriends(String user_id, String f_user_id, String msg, String type);
	
	/**
	 * 展示好友的好友
	 * @param user_id
	 * @param hostPath01
	 * @return
	 */
	public List<Friends> showfTof(String user_id, String hostPath01);
	
	/**
	 * 用户添加关注用户
	 * @param user_id
	 * @param f_user_id
	 * @return
	 */
	public JSONObject addAttention(String user_id,String f_user_id);
	
	/**
	 * 用户取消关注
	 * @param user_id
	 * @param f_user_id
	 * @return
	 */
	public JSONObject cancelAttention(String user_id,String f_user_id);
	
	/**
	 * 用户的关注用户列表
	 * @param user_id
	 * @param pageSize
	 * @param pageNumber
	 * @param hostPath01
	 * @return
	 */
	public JSONObject attentionUsers(String user_id, int pageSize, int pageNumber, 
			String hostPath01);
	
	/**
	 * fans的用户列表
	 * @param user_id
	 * @param pageSize
	 * @param pageNumber
	 * @param hostPath01
	 * @return
	 */
	public JSONObject showFans(String user_id, int pageSize, int pageNumber, 
			String hostPath01);
	
	/**
	 * 用户的朋友列表
	 * @param user_id
	 * @param hostPath01
	 * @return
	 */
	public JSONObject friendsList(String user_id, String hostPath01);
	
	/**
	 * 取消用户的fans用户
	 * @param user_id
	 * @param f_user_id
	 * @return
	 */
	public JSONObject cancelFans(String user_id, String f_user_id);
	
	/**
	 * 删除朋友
	 * @param user_id
	 * @param f_user_id
	 * @return
	 */
	public JSONObject deleteFriend(String user_id, String f_user_id);
	
	
}
