package com.liancheng.it.service.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import net.minidev.json.JSONObject;


public interface AdminService {
	
	public JSONObject checkAmdin(String adminame, String password, HttpServletRequest request);
	
	public JSONObject bannerPicReport(String user_id, String hostPath01, String hostPath02);//用于后台的banner图的报表
	public JSONObject upBannerPic(MultipartFile bannerPic, String picId, 
			String thirdURL, String localBasePath);//后台更新banner图
	public JSONObject modifyBannerPic(int picId, String localBasePath);//删除对应的banner图片
	public JSONObject editDunbarVal(String dunbaVal);//修改顿巴同心圆的内容
	public JSONObject gotDunbarContent();//获取顿巴同心圆的内容
	public JSONObject editAdmin(String admin_name, String password);//修改后台的账号
	public JSONObject adminLogReport(String dateVal, String adminId, String sortName, 
			String sortOrder, int pageSize, int pageNumber);//登录日志报表
	public JSONObject batchDelLog(String ids);//批量删除后台日志
	public JSONObject bannerReport(int pageSize, int pageNumber,String sortName, 
			String sortOrder, String hostPath02);//后台的banner报表
	public JSONObject batchDelBanner(String ids);//批量删除图片
	public JSONObject bannerIndex();//获取banner的索引
	public JSONObject updateBannerPic(MultipartFile bannerPic, String selectPicId, String title, 
			String thredURL, String localBasePath);//跟新banner图
	public JSONObject checkOriginalPwd(String adminUser, String originalPwd);//检查原始后台登录密码
	public JSONObject getNotifies();//得到index.html的通知数据
	
}
