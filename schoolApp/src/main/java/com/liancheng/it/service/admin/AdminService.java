package com.liancheng.it.service.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import net.minidev.json.JSONObject;


public interface AdminService {
	
	/**
	 * ��¼����
	 * @param adminame
	 * @param password
	 * @param request
	 * @return
	 */
	public JSONObject checkAmdin(String adminame, String password, HttpServletRequest request);
	
	public JSONObject bannerPicReport(String user_id, String hostPath01, String hostPath02);
	
	/**
	 * ��̨�ϴ�bannerͼƬ
	 * @param bannerPic
	 * @param picId
	 * @param thirdURL
	 * @param localBasePath
	 * @return
	 */
	public JSONObject upBannerPic(MultipartFile bannerPic, String picId, 
			String thirdURL, String localBasePath);
	
	/**
	 * ɾ����Ӧ��bannerͼƬ
	 * @param picId
	 * @param localBasePath
	 * @return
	 */
	public JSONObject modifyBannerPic(int picId, String localBasePath);
	
	/**
	 * �޸Ķٰ�ͬ��Բ������
	 * @param dunbaVal
	 * @return
	 */
	public JSONObject editDunbarVal(String dunbaVal);
	
	/**
	 * ��ȡ�ٰ�ͬ��Բ������
	 * @return
	 */
	public JSONObject gotDunbarContent();
	
	/**
	 * ���º�̨�˻�������
	 * @param admin_name
	 * @param password
	 * @return
	 */
	public JSONObject editAdmin(String admin_name, String password);
	
	/**
	 * ��¼��־����
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
	 * ����ɾ����̨��¼��־
	 * @param ids
	 * @return
	 */
	public JSONObject batchDelLog(String ids);
	
	/**
	 * ��̨��banner����
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
	 * ����ɾ��banner
	 * @param ids
	 * @return
	 */
	public JSONObject batchDelBanner(String ids);
	
	/**
	 * ��ȡbanner������
	 * @return
	 */
	public JSONObject bannerIndex();
	
	/**
	 * ����bannerͼ
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
	 * ���ԭʼ��̨��¼����
	 * @param adminUser
	 * @param originalPwd
	 * @return
	 */
	public JSONObject checkOriginalPwd(String adminUser, String originalPwd);
	
	/**
	 * �õ�index.html��֪ͨ����
	 * @return
	 */
	public JSONObject getNotifies();
	
	
}
