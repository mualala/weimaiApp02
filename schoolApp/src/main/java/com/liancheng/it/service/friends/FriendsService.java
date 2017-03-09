package com.liancheng.it.service.friends;


import java.util.List;

import com.liancheng.it.entity.friends.Friends;

import net.minidev.json.JSONObject;

public interface FriendsService {
	
	/**
	 * ����Ӻ���,�������Žӿ�
	 * @param user_id
	 * @param f_user_id
	 * @param msg
	 * @param type
	 * @return
	 */
	public JSONObject reqAddFriends(String user_id, String f_user_id, String msg, String type);
	
	/**
	 * չʾ���ѵĺ���
	 * @param user_id
	 * @param hostPath01
	 * @return
	 */
	public List<Friends> showfTof(String user_id, String hostPath01);
	
	/**
	 * �û���ӹ�ע�û�
	 * @param user_id
	 * @param f_user_id
	 * @return
	 */
	public JSONObject addAttention(String user_id,String f_user_id);
	
	/**
	 * �û�ȡ����ע
	 * @param user_id
	 * @param f_user_id
	 * @return
	 */
	public JSONObject cancelAttention(String user_id,String f_user_id);
	
	/**
	 * �û��Ĺ�ע�û��б�
	 * @param user_id
	 * @param pageSize
	 * @param pageNumber
	 * @param hostPath01
	 * @return
	 */
	public JSONObject attentionUsers(String user_id, int pageSize, int pageNumber, 
			String hostPath01);
	
	/**
	 * fans���û��б�
	 * @param user_id
	 * @param pageSize
	 * @param pageNumber
	 * @param hostPath01
	 * @return
	 */
	public JSONObject showFans(String user_id, int pageSize, int pageNumber, 
			String hostPath01);
	
	/**
	 * �û��������б�
	 * @param user_id
	 * @param hostPath01
	 * @return
	 */
	public JSONObject friendsList(String user_id, String hostPath01);
	
	/**
	 * ȡ���û���fans�û�
	 * @param user_id
	 * @param f_user_id
	 * @return
	 */
	public JSONObject cancelFans(String user_id, String f_user_id);
	
	/**
	 * ɾ������
	 * @param user_id
	 * @param f_user_id
	 * @return
	 */
	public JSONObject deleteFriend(String user_id, String f_user_id);
	
	
}
