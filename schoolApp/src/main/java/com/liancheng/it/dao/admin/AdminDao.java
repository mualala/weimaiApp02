package com.liancheng.it.dao.admin;

import java.util.List;
import java.util.Map;

import com.liancheng.it.entity.active.ActiveVerify;
import com.liancheng.it.entity.admin.Admin;
import com.liancheng.it.entity.admin.AdminLoginInfo;
import com.liancheng.it.entity.admin.BannerPicReport;
import com.liancheng.it.entity.admin.DunbarCircle;

public interface AdminDao {

	public Integer saveAdmin(Admin admin);
	public Integer updateAdmin(Admin admin);
	public Admin findByName(Map<String,String> map);
	
	public Admin checkAdmin(String adminame);//检查admin登录
	
	
	public List<BannerPicReport> queryBannerPics();//后台banner图的报表
	public int TotalBannersCount();//查询后台banner的总记录数
	public void updateBanner(Map<String, Object> params);//后台更新banner
	public BannerPicReport queryBannerById(int ban_id);//查询banner图
	public void deleteBannerPicById(Map<String, Object> params);//根据banner的id删除banner图片
	public void updateDunbaVal(Map<String, Object> params);//更新顿巴同心圆的内容
	public DunbarCircle queryDunbar();//获取顿巴同心圆的内容
	public void modifyAdminUser(Map<String, Object> params);//修改后台登录的账号
	
	public void saveAdminLoginInfo(Map<String, Object> params);//保存后台的登录信息
	public List<AdminLoginInfo> adminLogRepot(Map<String, Object> params);//登录日志的报表
	public int countAdminLog();//查询后台登录日志的数量
	public int batchDelLog(List<Integer> ids);//批量删除登录日志
	public List<BannerPicReport> bannerReport(Map<String, Object> params);//后台的bannner图报表
	public int batchDelBanner(List<Integer> ids);//后台批量删除banner
	public int queryMaxBannerId();//查询最大的图片id
	public void updateBannerPic(Map<String, Object> params);//更新banner图
	public void insertBannerPic(Map<String, Object> params);//插入banner图
	public Admin queryByAdminName(String adminName);//更具用户名查询admin
}
