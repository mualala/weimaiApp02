package com.liancheng.it.service.friends;


import net.minidev.json.JSONObject;

public interface FriendsService {
	
	
	public JSONObject confirmAddFriends(String user_id, String f_user_id);//确认加好友
	public JSONObject showfTof(String user_id);//展示好友的好友
	public JSONObject addAttention(String user_id,String f_user_id);//添加关注用户
	public JSONObject cancelAttention(String user_id,String f_user_id);//取消关注
	public JSONObject attentionUsers(String user_id, int pageSize, int pageNumber, 
			String hostPath01);//某用户关注的用户列表
	public JSONObject showFans(String user_id, int pageSize, int pageNumber, 
			String hostPath01);//查询fans的列表
	public JSONObject friendsList(String user_id, String hostPath01);//登录用户的朋友列表
	public JSONObject cancelFans(String user_id, String f_user_id);//取消fans
	
	
}
