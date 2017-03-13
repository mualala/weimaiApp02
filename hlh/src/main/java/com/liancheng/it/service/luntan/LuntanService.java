package com.liancheng.it.service.luntan;

import org.springframework.web.multipart.MultipartFile;

import net.minidev.json.JSONObject;

public interface LuntanService {
	
	/**
	 * 论坛发帖
	 * @param pics
	 * @param user_id
	 * @param lt_content
	 * @param type
	 * @param lt_type
	 * @param area
	 * @param localBasePath
	 * @return
	 */
	public JSONObject addCommont(MultipartFile[] pics, String user_id, String lt_content, String type, 
			String lt_type, String area, String localBasePath);
	
	/**
	 * 分页展示论坛数据
	 * @param pageSize
	 * @param pageNumber
	 * @param type
	 * @param lt_type
	 * @param area
	 * @param hostPath01
	 * @param hostPath02
	 * @return
	 */
	public JSONObject showPaginationLT(int pageSize, int pageNumber, String type, 
			int lt_type, String area, String hostPath01, String hostPath02);
	
	/**
	 * 论坛详情页
	 * @param lt_id
	 * @param hostPath01
	 * @param hostPath02
	 * @return
	 */
	public JSONObject showDetailLT(int lt_id, String hostPath01, String hostPath02);
	
	/**
	 * 添加一级评论
	 * @param user_id
	 * @param other_user_id
	 * @param content
	 * @return
	 */
	public JSONObject addOneLVCommont(String user_id, String other_user_id, String lt_id, String content);
	
	/**
	 * 添加二级评论
	 * @param user_id
	 * @param other_user_id
	 * @param comm_id
	 * @param content
	 * @return
	 */
	public JSONObject addTwoLVCommont(String user_id, String other_user_id, String comm_id, String content);
	
}
