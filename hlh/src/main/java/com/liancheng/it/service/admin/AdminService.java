package com.liancheng.it.service.admin;

import org.springframework.web.multipart.MultipartFile;

import net.minidev.json.JSONObject;


public interface AdminService {
	
	public boolean checkAmdin(String adminame, String password);
	
	public JSONObject upBannerPic(MultipartFile bannerPic, String picId, String type, String localBasePath);//后台更新banner图
	public JSONObject modifyBannerPic(int picId, String type, String localBasePath);//删除对应的banner图片
	
}
