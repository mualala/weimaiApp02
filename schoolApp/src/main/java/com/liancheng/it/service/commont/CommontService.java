package com.liancheng.it.service.commont;

import net.minidev.json.JSONObject;

public interface CommontService {
	
	/**
	 * �û��������
	 * @param com_user_id
	 * @param user_id
	 * @param active_user_id
	 * @param content
	 * @return
	 */
	public JSONObject addAction(String com_user_id, String user_id, int active_user_id, String content);
	
	/**
	 * ����
	 * @param com_user_id
	 * @param active_user_id
	 * @return
	 */
	public JSONObject addLaud(String com_user_id, int active_user_id);
	
	/**
	 * ĳ��˵˵�ĵ����û��б�
	 * @param active_user_id
	 * @param hostPath
	 * @return
	 */
	public JSONObject laudList(int active_user_id, String hostPath);
	
	/**
	 * ���������
	 * @param child_user_id
	 * @param com_id
	 * @param parent_user_id
	 * @param content
	 * @return
	 */
	public JSONObject addcToc(String child_user_id, int com_id, String parent_user_id, String content);
	
	/**
	 * ��̬������ҳ
	 * @param user_id
	 * @param com_user_id
	 * @param active_user_id
	 * @param hostPath01
	 * @param hostPath02
	 * @return
	 */
	public JSONObject cToc(String user_id, String com_user_id, int active_user_id, 
			String hostPath01, String hostPath02);
	
	/**
	 * �����۵���
	 * @param child_user_id
	 * @param com_id
	 * @param parent_user_id
	 * @return
	 */
	public JSONObject childComLaud(String child_user_id, int com_id, String parent_user_id);
	
	/**
	 * �Ƴ�����
	 * @param com_user_id
	 * @param active_user_id
	 * @return
	 */
	public JSONObject cancelLaud(String com_user_id, int active_user_id);
	
	
}
