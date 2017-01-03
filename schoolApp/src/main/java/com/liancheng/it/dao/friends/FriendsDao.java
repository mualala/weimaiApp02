package com.liancheng.it.dao.friends;

import java.util.List;
import java.util.Map;

import com.liancheng.it.entity.friends.Friends;

import net.minidev.json.JSONObject;

public interface FriendsDao {
	
	public void addFriend(Friends friends);//添加好友
	public List<Friends> queryfTof(String user_id);//查找朋友的朋友
	public List<Friends> queryFriends(String user_id);//查找某个用户的好友
	public void addAttention(Map<String, Object> params);//添加关注用户
	public int countIsAttention(Map<String, Object> params);//查询用户已关注没
	public void cancelAttention(Map<String, Object> params);//用户取消关注
	public List<Friends> queryAttentions(Map<String, Object> params);//查询关注的用户列表
	public int queryTotalAttention(String user_id);//关注的人数
	public List<Friends> queryFans(Map<String, Object> params);//查询用户的fans列表
	public int queryTotalFans(String user_id);//fans的总数
	public int queryFriendCount(String user_id);//查询登录用户的好友数量
	public List<Friends> queryFriendList(String user_id);//查询朋友的列表
	public void cancelFans(Map<String, Object> params);//移除某个fans
	
}