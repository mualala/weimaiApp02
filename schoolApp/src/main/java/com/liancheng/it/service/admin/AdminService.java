package com.liancheng.it.service.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import net.minidev.json.JSONObject;


public interface AdminService {
	
	/**
	 * 登录请求
	 * @param adminame
	 * @param password
	 * @param request
	 * @return
	 */
	public JSONObject checkAmdin(String adminame, String password, HttpServletRequest request);
	
	public JSONObject bannerPicReport(String user_id, String hostPath01, String hostPath02);
	
	/**
	 * 后台上传banner图片
	 * @param bannerPic
	 * @param picId
	 * @param thirdURL
	 * @param localBasePath
	 * @return
	 */
	public JSONObject upBannerPic(MultipartFile bannerPic, String picId, 
			String thirdURL, String localBasePath);
	
	/**
	 * 删除对应的banner图片
	 * @param picId
	 * @param localBasePath
	 * @return
	 */
	public JSONObject modifyBannerPic(int picId, String localBasePath);
	
	/**
	 * 修改顿巴同心圆的内容
	 * @param dunbaVal
	 * @return
	 */
	public JSONObject editDunbarVal(String dunbaVal);
	
	/**
	 * 获取顿巴同心圆的内容
	 * @return
	 */
	public JSONObject gotDunbarContent();
	
	/**
	 * 更新后台账户的请求
	 * @param admin_name
	 * @param password
	 * @return
	 */
	public JSONObject editAdmin(String admin_name, String password);
	
	/**
	 * 登录日志报表
	 * @param dateVal
	 * @param adminId
	 * @param sortName
	 * @param sortOrder
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	public JSONObject adminLogReport(String dateVal, String adminId, String sortName, 
			String sortOrder, int pageSize, int pageNumber);
	
	/**
	 * 批量删除后台登录日志
	 * @param ids
	 * @return
	 */
	public JSONObject batchDelLog(String ids);
	
	/**
	 * 后台的banner报表
	 * @param pageSize
	 * @param pageNumber
	 * @param sortName
	 * @param sortOrder
	 * @param hostPath02
	 * @return
	 */
	public JSONObject bannerReport(int pageSize, int pageNumber,String sortName, 
			String sortOrder, String hostPath02);
	
	/**
	 * 批量删除banner
	 * @param ids
	 * @return
	 */
	public JSONObject batchDelBanner(String ids);
	
	/**
	 * 获取banner的索引
	 * @return
	 */
	public JSONObject bannerIndex();
	
	/**
	 * 更新banner图
	 * @param bannerPic
	 * @param selectPicId
	 * @param title
	 * @param thredURL
	 * @param localBasePath
	 * @return
	 */
	public JSONObject updateBannerPic(MultipartFile bannerPic, String selectPicId, String title, 
			String thredURL, String localBasePath);
	
	/**
	 * 检查原始后台登录密码
	 * @param adminUser
	 * @param originalPwd
	 * @return
	 */
	public JSONObject checkOriginalPwd(String adminUser, String originalPwd);
	
	/**
	 * 得到index.html的通知数据
	 * @return
	 */
	public JSONObject getNotifies();
	
	
}
