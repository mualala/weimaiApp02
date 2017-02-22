package com.liancheng.it.service.commont;

import net.minidev.json.JSONObject;

public interface CommontService {
	
	public JSONObject addAction(String com_user_id, String user_id, int active_user_id, 
			String content);//添加action
	public JSONObject addLaud(String com_user_id, int active_user_id);//点赞
	public JSONObject laudList(int active_user_id, String hostPath);//某说说的点赞用户列表
	public JSONObject addcToc(String child_user_id, int com_id, String parent_user_id, 
			String content);//添加评论的评论
	public JSONObject cToc(String user_id, String com_user_id, int active_user_id, 
			String hostPath01, String hostPath02);//展示评论及子评论
	public JSONObject childComLaud(String child_user_id, int com_id, String parent_user_id);//子评论点赞
	public JSONObject cancelLaud(String com_user_id, int active_user_id);//取消点赞
	
}
