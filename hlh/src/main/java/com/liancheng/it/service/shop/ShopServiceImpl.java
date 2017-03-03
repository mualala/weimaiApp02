package com.liancheng.it.service.shop;

import java.io.File;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import net.minidev.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.liancheng.it.dao.shop.ShopDao;

@Service
public class ShopServiceImpl implements ShopService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ShopDao shopDao;
	
	public JSONObject createShop(MultipartFile pics, String user_id, String shop_name, 
			String shop_type, String shop_describe, String shop_address, String contacts_name, 
			String contacts_phone, String localBasePath){
		JSONObject jsonObject = new JSONObject();
		String picName = String.valueOf(System.currentTimeMillis());
		String picName02 = "";
		if (pics != null) {
			if(!pics.isEmpty()){//只要上传图片不为空
				String suffix = pics.getOriginalFilename().substring(pics.getOriginalFilename().lastIndexOf("."));//获取文件的后缀名
				picName02 = picName02+picName+suffix+",";
				try {
					logger.info("发动态图片保存的本地路径="+localBasePath+picName+suffix);
					//保存图片到本地目录
					pics.transferTo(new File(localBasePath+picName+suffix));
				} catch (Exception e) {
					logger.error("保存店铺头像失败");
				}
			}
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", user_id);
		params.put("shop_name", shop_name);
		params.put("shop_type", shop_type);
		params.put("shop_describe", shop_describe);
		params.put("shop_address", shop_address);
		params.put("contacts_name", contacts_name);
		params.put("contacts_phone", contacts_phone);
		params.put("shop_profile", picName02);
		params.put("shop_creatime", new Timestamp(System.currentTimeMillis()));
		logger.info(params.toString());
		int count = shopDao.saveShop(params);
		if(count == 1){
			jsonObject.put("msg", "创建店铺成功");
		}else {
			jsonObject.put("msg", "创建店铺失败");
		}
		return jsonObject;
	}
	
	
	
	
	
	
	
	
	
	
}
