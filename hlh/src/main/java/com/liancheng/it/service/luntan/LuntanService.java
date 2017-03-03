package com.liancheng.it.service.luntan;

import org.springframework.web.multipart.MultipartFile;

import net.minidev.json.JSONObject;

public interface LuntanService {
	
	public JSONObject addCommont(MultipartFile[] pics, String user_id, String lt_content, String type, 
			String lt_type, String area, String localBasePath);
	public JSONObject showPaginationLT(int pageSize, int pageNumber, String type, 
			int lt_type, String area, String hostPath01, String hostPath02);
	public JSONObject showDetailLT(int lt_id, String hostPath01, String hostPath02);
	
	
	
	
}
