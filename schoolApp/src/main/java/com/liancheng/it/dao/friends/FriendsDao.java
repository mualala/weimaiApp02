package com.liancheng.it.dao.friends;

import java.util.List;
import java.util.Map;

import com.liancheng.it.entity.friends.Friends;

import net.minidev.json.JSONObject;

public interface FriendsDao {
	
	/**
	 * ��Ӻ���
	 * @param friends
	 */
	public void addFriend(Friends friends);
	
	/**
	 * �������ѵ�����
	 * @param params
	 * @return
	 */
	public List<Friends> queryfTof(Map<String, Object> params);
	
	/**
	 * ����ĳ���û��ĺ���
	 * @param user_id
	 * @return
	 */
	public List<Friends> queryFriends(String user_id);
	
	/**
	 * ��ӹ�ע�û�
	 * @param params
	 */
	public void addAttention(Map<String, Object> params);
	
	/**
	 * ��ѯ�û��ѹ�עû
	 * @param params
	 * @return
	 */
	public int countIsAttention(Map<String, Object> params);
	
	/**
	 * �û�ȡ����ע
	 * @param params
	 */
	public void cancelAttention(Map<String, Object> params);
	
	/**
	 * ��ѯ��ע���û��б�
	 * @param params
	 * @return
	 */
	public List<Friends> queryAttentions(Map<String, Object> params);
	
	/**
	 * ��ע������
	 * @param user_id
	 * @return
	 */
	public int queryTotalAttention(String user_id);
	
	/**
	 * ��ѯ�û���fans�б�
	 * @param params
	 * @return
	 */
	public List<Friends> queryFans(Map<String, Object> params);
	
	/**
	 * fans������
	 * @param user_id
	 * @return
	 */
	public int queryTotalFans(String user_id);
	
	/**
	 * ��ѯ��¼�û��ĺ�������
	 * @param user_id
	 * @return
	 */
	public int queryFriendCount(String user_id);
	
	/**
	 * ��ѯ���ѵ��б�
	 * @param user_id
	 * @return
	 */
	public List<Friends> queryFriendList(String user_id);
	
	/**
	 * �Ƴ�ĳ��fans
	 * @param params
	 */
	public void cancelFans(Map<String, Object> params);
	
	/**
	 * ���鿴���ѵ����Ѳ鿴״̬����Ϊ1���Ѳ鿴
	 * @param user_id
	 */
	public void updateFToFSee(String user_id);
	
	/**
	 * ��ѯ����
	 * @param params
	 * @return
	 */
	public Friends queryIsFriend(Map<String, Object> params);
	
	/**
	 * �Ƿ�ɿ���״̬
	 * @param params
	 * @return
	 */
	public Friends queryIsAttention(Map<String, Object> params);
	
	/**
	 * ɾ������
	 * @param params
	 * @return
	 */
	public int deleteFriend(Map<String, Object> params);
	
	/**
	 * ��ѯ�Ƿ���fans
	 * @param params
	 * @return
	 */
	public Friends queryIsFans(Map<String, Object> params);
	
	
	
}