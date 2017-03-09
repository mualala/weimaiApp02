package com.liancheng.it.dao.friends;

import java.util.List;
import java.util.Map;

import com.liancheng.it.entity.friends.Friends;

import net.minidev.json.JSONObject;

public interface FriendsDao {
	
	/**
	 * 添加好友
	 * @param friends
	 */
	public void addFriend(Friends friends);
	
	/**
	 * 查找朋友的朋友
	 * @param params
	 * @return
	 */
	public List<Friends> queryfTof(Map<String, Object> params);
	
	/**
	 * 查找某个用户的好友
	 * @param user_id
	 * @return
	 */
	public List<Friends> queryFriends(String user_id);
	
	/**
	 * 添加关注用户
	 * @param params
	 */
	public void addAttention(Map<String, Object> params);
	
	/**
	 * 查询用户已关注没
	 * @param params
	 * @return
	 */
	public int countIsAttention(Map<String, Object> params);
	
	/**
	 * 用户取消关注
	 * @param params
	 */
	public void cancelAttention(Map<String, Object> params);
	
	/**
	 * 查询关注的用户列表
	 * @param params
	 * @return
	 */
	public List<Friends> queryAttentions(Map<String, Object> params);
	
	/**
	 * 关注的人数
	 * @param user_id
	 * @return
	 */
	public int queryTotalAttention(String user_id);
	
	/**
	 * 查询用户的fans列表
	 * @param params
	 * @return
	 */
	public List<Friends> queryFans(Map<String, Object> params);
	
	/**
	 * fans的总数
	 * @param user_id
	 * @return
	 */
	public int queryTotalFans(String user_id);
	
	/**
	 * 查询登录用户的好友数量
	 * @param user_id
	 * @return
	 */
	public int queryFriendCount(String user_id);
	
	/**
	 * 查询朋友的列表
	 * @param user_id
	 * @return
	 */
	public List<Friends> queryFriendList(String user_id);
	
	/**
	 * 移除某个fans
	 * @param params
	 */
	public void cancelFans(Map<String, Object> params);
	
	/**
	 * 将查看朋友的朋友查看状态设置为1，已查看
	 * @param user_id
	 */
	public void updateFToFSee(String user_id);
	
	/**
	 * 查询朋友
	 * @param params
	 * @return
	 */
	public Friends queryIsFriend(Map<String, Object> params);
	
	/**
	 * 是否可看的状态
	 * @param params
	 * @return
	 */
	public Friends queryIsAttention(Map<String, Object> params);
	
	/**
	 * 删除好友
	 * @param params
	 * @return
	 */
	public int deleteFriend(Map<String, Object> params);
	
	/**
	 * 查询是否是fans
	 * @param params
	 * @return
	 */
	public Friends queryIsFans(Map<String, Object> params);
	
	
	
}