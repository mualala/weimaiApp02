package com.liancheng.it.dao.admin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.liancheng.it.entity.admin.Admin;
import com.liancheng.it.entity.admin.AdminLoginInfo;
import com.liancheng.it.entity.admin.BannerPicReport;
import com.liancheng.it.entity.admin.DunbarCircle;

public interface AdminDao {

	public Integer saveAdmin(Admin admin);
	public Integer updateAdmin(Admin admin);
	public Admin findByName(Map<String,String> map);
	
	/**
	 * ���admin��¼
	 * @param adminame
	 * @return
	 */
	@Select("select * from admin where admin_name=#{adminame}")
	public Admin checkAdmin(@Param("adminame") String adminame);
	
	/**
	 * ��̨bannerͼ�ı���
	 * @return
	 */
	public List<BannerPicReport> queryBannerPics();
	
	/**
	 * ��ѯ��̨banner���ܼ�¼��
	 * @return
	 */
	@Select("select count(*) from banner_pic")
	public int TotalBannersCount();
	
	/**
	 * ��̨����banner
	 * @param params
	 */
	public void updateBanner(Map<String, Object> params);
	
	/**
	 * ��ѯbannerͼ
	 * @param ban_id
	 * @return
	 */
	@Select("select * from banner_pic where ban_id=#{ban_id}")
	public BannerPicReport queryBannerById(int ban_id);
	
	/**
	 * ����banner��idɾ��bannerͼƬ
	 * @param params
	 */
	public void deleteBannerPicById(Map<String, Object> params);
	
	/**
	 * ���¶ٰ�ͬ��Բ������
	 * @param params
	 */
	public void updateDunbaVal(Map<String, Object> params);
	
	/**
	 * ��ȡ�ٰ�ͬ��Բ������
	 * @return
	 */
	@Select("select * from dunbar_circle where id=1")
	public DunbarCircle queryDunbar();
	
	/**
	 * �޸ĺ�̨��¼���˺�
	 * @param params
	 */
	public void modifyAdminUser(Map<String, Object> params);
	
	/**
	 * �����̨�ĵ�¼��Ϣ
	 * @param params
	 */
	public void saveAdminLoginInfo(Map<String, Object> params);
	
	/**
	 * ��¼��־�ı���
	 * @param params
	 * @return
	 */
	public List<AdminLoginInfo> adminLogRepot(Map<String, Object> params);
	
	/**
	 * ��ѯ��̨��¼��־������
	 * @return
	 */
	@Select("select count(*) from admin_login_info")
	public int countAdminLog();
	
	/**
	 * ����ɾ����¼��־
	 * @param ids
	 * @return
	 */
	public int batchDelLog(List<Integer> ids);
	
	/**
	 * ��̨��bannnerͼ����
	 * @param params
	 * @return
	 */
	public List<BannerPicReport> bannerReport(Map<String, Object> params);
	
	/**
	 * ��̨����ɾ��banner
	 * @param ids
	 * @return
	 */
	public int batchDelBanner(List<Integer> ids);
	
	/**
	 * ��ѯ����ͼƬid
	 * @return
	 */
	@Select("select MAX(ban_id) from banner_pic")
	public int queryMaxBannerId();
	
	/**
	 * ����bannerͼ
	 * @param params
	 */
	public void updateBannerPic(Map<String, Object> params);
	
	/**
	 * ����bannerͼ
	 * @param params
	 */
	public void insertBannerPic(Map<String, Object> params);
	
	/**
	 * �����û�����ѯadmin
	 * @param adminName
	 * @return
	 */
	@Select("select * from admin where admin_name=#{adminName}")
	public Admin queryByAdminName(@Param("adminUser") String adminName);
	
	
}
