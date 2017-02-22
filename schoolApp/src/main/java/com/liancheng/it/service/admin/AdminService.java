package com.liancheng.it.service.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import net.minidev.json.JSONObject;


public interface AdminService {
	
	public JSONObject checkAmdin(String adminame, String password, HttpServletRequest request);
	
	public JSONObject bannerPicReport(String user_id, String hostPath01, String hostPath02);//���ں�̨��bannerͼ�ı���
	public JSONObject upBannerPic(MultipartFile bannerPic, String picId, 
			String thirdURL, String localBasePath);//��̨����bannerͼ
	public JSONObject modifyBannerPic(int picId, String localBasePath);//ɾ����Ӧ��bannerͼƬ
	public JSONObject editDunbarVal(String dunbaVal);//�޸Ķٰ�ͬ��Բ������
	public JSONObject gotDunbarContent();//��ȡ�ٰ�ͬ��Բ������
	public JSONObject editAdmin(String admin_name, String password);//�޸ĺ�̨���˺�
	public JSONObject adminLogReport(String dateVal, String adminId, String sortName, 
			String sortOrder, int pageSize, int pageNumber);//��¼��־����
	public JSONObject batchDelLog(String ids);//����ɾ����̨��־
	public JSONObject bannerReport(int pageSize, int pageNumber,String sortName, 
			String sortOrder, String hostPath02);//��̨��banner����
	public JSONObject batchDelBanner(String ids);//����ɾ��ͼƬ
	public JSONObject bannerIndex();//��ȡbanner������
	public JSONObject updateBannerPic(MultipartFile bannerPic, String selectPicId, String title, 
			String thredURL, String localBasePath);//����bannerͼ
	public JSONObject checkOriginalPwd(String adminUser, String originalPwd);//���ԭʼ��̨��¼����
	public JSONObject getNotifies();//�õ�index.html��֪ͨ����
	
}
