package com.liancheng.it.service.active;

import org.springframework.web.multipart.MultipartFile;

import net.minidev.json.JSONObject;

public interface ActiveUserService {
	
	public JSONObject addActive(MultipartFile[] pics, MultipartFile[] docum, 
			String user_id, String type_a, String type_b, String saysay, 
			String localBasePath);//�û���Ӷ�̬
	public JSONObject showOwnActive(String user_id,String v_user_id, int pageSize, 
			int pageNumber, String hostPath01, String hostPath02);//�鿴���˶�̬
	public JSONObject showClassActive(String type_a, int pageSize, int pageNumber, 
			String hostPath01, String hostPath02,String com_user_id);//��ҳ����Ķ�̬չʾ
	public JSONObject showFriendsActvie(String user_id, int pageSize, int pageNumber, 
			String hostPath01, String hostPath02, String com_user_id);//չʾ����Ȧ��̬
	
	public JSONObject addSeeActive(int active_user_id);//���˵˵�鿴������
	
	public JSONObject adminActive(int pageSize, int pageNumber, String hostPath);//��˵˵�Ķ�̬���
	public JSONObject verifyActive(int active_user_id);//���ͨ��ĳ��˵˵
	public JSONObject noVerifyActive(int active_user_id);//��˲�ͨ��ĳ��˵˵
	
	public JSONObject authActive(int pageSize, int pageNumber);//����̬����֤�б�
	public JSONObject modifyVerify(String state, String class_active, String verify);//�޸ķ���̬����֤����
	public JSONObject keyActive(String text, String hostPath01, String hostPath02);//˵˵�Ĺؼ��ʼ���
//	public JSONObject showdetailActive(int active_user_id, String hostPath01, 
//			String hostPath02);//չʾ��̬������ҳ
	public JSONObject batchDoVerify(String ids);//�������淢��̬����֤Ϊ1��״̬
	
}
