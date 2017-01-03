package com.liancheng.it.service.active;

import org.springframework.web.multipart.MultipartFile;

import net.minidev.json.JSONObject;

public interface ActiveUserService {
	
	public JSONObject addActive(MultipartFile[] pics, MultipartFile[] docum, 
			String user_id, String type_a, String type_b, String saysay, 
			String localBasePath);//用户添加动态
	public JSONObject showOwnActive(String user_id,String v_user_id, int pageSize, 
			int pageNumber, String hostPath01, String hostPath02);//查看个人动态
	public JSONObject showClassActive(String type_a, int pageSize, int pageNumber, 
			String hostPath01, String hostPath02,String com_user_id);//首页大类的动态展示
	public JSONObject showFriendsActvie(String user_id, int pageSize, int pageNumber, 
			String hostPath01, String hostPath02, String com_user_id);//展示朋友圈动态
	
	public JSONObject addSeeActive(int active_user_id);//添加说说查看的数量
	
	public JSONObject adminActive(int pageSize, int pageNumber, String hostPath);//发说说的动态审核
	public JSONObject verifyActive(int active_user_id);//审核通过某条说说
	public JSONObject noVerifyActive(int active_user_id);//审核不通过某条说说
	
	public JSONObject authActive(int pageSize, int pageNumber);//发动态的验证列表
	public JSONObject modifyVerify(String state, String class_active, String verify);//修改发动态的验证开关
	public JSONObject keyActive(String text, String hostPath01, String hostPath02);//说说的关键词检索
//	public JSONObject showdetailActive(int active_user_id, String hostPath01, 
//			String hostPath02);//展示动态的详情页
	public JSONObject batchDoVerify(String ids);//批量保存发动态的验证为1的状态
	
}
