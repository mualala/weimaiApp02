package com.liancheng.it.dao.admin;

import java.util.List;
import java.util.Map;

import com.liancheng.it.entity.active.ActiveVerify;
import com.liancheng.it.entity.admin.Admin;
import com.liancheng.it.entity.admin.BannerPicReport;
import com.liancheng.it.entity.admin.DunbarCircle;

public interface AdminDao {

	public Integer saveAdmin(Admin admin);
	public Integer updateAdmin(Admin admin);
	public Admin findByName(Map<String,String> map);
	
	public Admin checkAdmin(String adminame);//���admin��¼
	
	
	public List<BannerPicReport> queryBannerPics();//��̨bannerͼ�ı���
	public int TotalBannersCount();//��ѯ��̨banner���ܼ�¼��
	public void updateBanner(Map<String, Object> params);//��̨����banner
	public BannerPicReport queryBannerById(int ban_id);//��ѯbannerͼ
	public void deleteBannerPicById(Map<String, Object> params);//����banner��idɾ��bannerͼƬ
	public void updateDunbaVal(Map<String, Object> params);//���¶ٰ�ͬ��Բ������
	public DunbarCircle queryDunbar();//��ȡ�ٰ�ͬ��Բ������
	
}
