package com.liancheng.it.service.commont;

import net.minidev.json.JSONObject;

public interface CommontService {
	
	public JSONObject addAction(String com_user_id, String user_id, int active_user_id, 
			String content);//���action
	public JSONObject addLaud(String com_user_id, int active_user_id);//����
	public JSONObject laudList(int active_user_id, String hostPath);//ĳ˵˵�ĵ����û��б�
	public JSONObject addcToc(String child_user_id, int com_id, String parent_user_id, 
			String content);//������۵�����
	public JSONObject cToc(String user_id, String com_user_id, int active_user_id, 
			String hostPath01, String hostPath02);//չʾ���ۼ�������
	public JSONObject childComLaud(String child_user_id, int com_id, String parent_user_id);//�����۵���
	public JSONObject cancelLaud(String com_user_id, int active_user_id);//ȡ������
	
}
