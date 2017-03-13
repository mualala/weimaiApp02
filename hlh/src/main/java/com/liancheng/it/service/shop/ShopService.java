package com.liancheng.it.service.shop;

import org.springframework.web.multipart.MultipartFile;

import net.minidev.json.JSONObject;

public interface ShopService {
	
	/**
	 * 创建店铺
	 * @param pics
	 * @param user_id
	 * @param shop_name
	 * @param shop_type
	 * @param shop_describe
	 * @param shop_address
	 * @param contacts_name
	 * @param contacts_phone
	 * @param localBasePath
	 * @return
	 */
	public JSONObject createShop(MultipartFile pics, String user_id, String shop_name, 
			String shop_type, String shop_describe, String shop_address, String contacts_name, 
			String contacts_phone, String localBasePath);
	
	/**
	 * 展示店铺的所有分类
	 * @return
	 */
	public JSONObject showShopType();
	
	/**
	 * 分页展示商铺
	 * @param shopType
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	public JSONObject showShopPaginData(String shopType, int pageSize, int pageNumber, String hostPath01);
	
	
	
	
	
}
