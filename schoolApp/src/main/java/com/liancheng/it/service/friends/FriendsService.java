package com.liancheng.it.service.friends;


import java.util.List;

import com.liancheng.it.entity.friends.Friends;

import net.minidev.json.JSONObject;

public interface FriendsService {
	
	public JSONObject reqAddFriends(String user_id, String f_user_id, String msg, String type);//����Ӻ���
	public List<Friends> showfTof(String user_id, String hostPath01);//չʾ���ѵĺ���
	public JSONObject addAttention(String user_id,String f_user_id);//��ӹ�ע�û�
	public JSONObject cancelAttention(String user_id,String f_user_id);//ȡ����ע
	public JSONObject attentionUsers(String user_id, int pageSize, int pageNumber, 
			String hostPath01);//ĳ�û���ע���û��б�
	public JSONObject showFans(String user_id, int pageSize, int pageNumber, 
			String hostPath01);//��ѯfans���б�
	public JSONObject friendsList(String user_id, String hostPath01);//��¼�û��������б�
	public JSONObject cancelFans(String user_id, String f_user_id);//ȡ��fans
	public JSONObject deleteFriend(String user_id, String f_user_id);//ɾ������
	
	
}
