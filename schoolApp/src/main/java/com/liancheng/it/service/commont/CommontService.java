package com.liancheng.it.service.commont;

import net.minidev.json.JSONObject;

public interface CommontService {
	
	/**
	 * 用户添加评论
	 * @param com_user_id
	 * @param user_id
	 * @param active_user_id
	 * @param content
	 * @return
	 */
	public JSONObject addAction(String com_user_id, String user_id, int active_user_id, String content);
	
	/**
	 * 点赞
	 * @param com_user_id
	 * @param active_user_id
	 * @return
	 */
	public JSONObject addLaud(String com_user_id, int active_user_id);
	
	/**
	 * 某条说说的点赞用户列表
	 * @param active_user_id
	 * @param hostPath
	 * @return
	 */
	public JSONObject laudList(int active_user_id, String hostPath);
	
	/**
	 * 添加子评论
	 * @param child_user_id
	 * @param com_id
	 * @param parent_user_id
	 * @param content
	 * @return
	 */
	public JSONObject addcToc(String child_user_id, int com_id, String parent_user_id, String content);
	
	/**
	 * 动态的详情页
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
	 * 子评论点赞
	 * @param child_user_id
	 * @param com_id
	 * @param parent_user_id
	 * @return
	 */
	public JSONObject childComLaud(String child_user_id, int com_id, String parent_user_id);
	
	/**
	 * 移除点赞
	 * @param com_user_id
	 * @param active_user_id
	 * @return
	 */
	public JSONObject cancelLaud(String com_user_id, int active_user_id);
	
	
}
