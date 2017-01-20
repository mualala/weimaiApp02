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
	
	public Admin checkAdmin(String adminame);//检查admin登录
	
	
	public List<BannerPicReport> queryBannerPics();//后台banner图的报表
	public int TotalBannersCount();//查询后台banner的总记录数
	public void updateBanner(Map<String, Object> params);//后台更新banner
	public BannerPicReport queryBannerById(int ban_id);//查询banner图
	public void deleteBannerPicById(Map<String, Object> params);//根据banner的id删除banner图片
	public void updateDunbaVal(Map<String, Object> params);//更新顿巴同心圆的内容
	public DunbarCircle queryDunbar();//获取顿巴同心圆的内容
	
}
