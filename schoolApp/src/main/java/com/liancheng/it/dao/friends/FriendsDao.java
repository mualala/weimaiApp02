package com.liancheng.it.dao.friends;

import java.util.List;
import java.util.Map;

import com.liancheng.it.entity.friends.Friends;

import net.minidev.json.JSONObject;

public interface FriendsDao {
	
	public void addFriend(Friends friends);//��Ӻ���
	public List<Friends> queryfTof(String user_id);//�������ѵ�����
	public List<Friends> queryFriends(String user_id);//����ĳ���û��ĺ���
	public void addAttention(Map<String, Object> params);//��ӹ�ע�û�
	public int countIsAttention(Map<String, Object> params);//��ѯ�û��ѹ�עû
	public void cancelAttention(Map<String, Object> params);//�û�ȡ����ע
	public List<Friends> queryAttentions(Map<String, Object> params);//��ѯ��ע���û��б�
	public int queryTotalAttention(String user_id);//��ע������
	public List<Friends> queryFans(Map<String, Object> params);//��ѯ�û���fans�б�
	public int queryTotalFans(String user_id);//fans������
	public int queryFriendCount(String user_id);//��ѯ��¼�û��ĺ�������
	public List<Friends> queryFriendList(String user_id);//��ѯ���ѵ��б�
	public void cancelFans(Map<String, Object> params);//�Ƴ�ĳ��fans
	
}