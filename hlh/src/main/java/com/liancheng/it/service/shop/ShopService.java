package com.liancheng.it.service.shop;

import org.springframework.web.multipart.MultipartFile;

import net.minidev.json.JSONObject;

public interface ShopService {
	
	public JSONObject createShop(MultipartFile pics, String user_id, String shop_name, 
			String shop_type, String shop_describe, String shop_address, String contacts_name, 
			String contacts_phone, String localBasePath);
	
}
