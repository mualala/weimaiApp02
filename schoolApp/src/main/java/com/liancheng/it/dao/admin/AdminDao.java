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
	 * 检查admin登录
	 * @param adminame
	 * @return
	 */
	@Select("select * from admin where admin_name=#{adminame}")
	public Admin checkAdmin(@Param("adminame") String adminame);
	
	/**
	 * 后台banner图的报表
	 * @return
	 */
	public List<BannerPicReport> queryBannerPics();
	
	/**
	 * 查询后台banner的总记录数
	 * @return
	 */
	@Select("select count(*) from banner_pic")
	public int TotalBannersCount();
	
	/**
	 * 后台更新banner
	 * @param params
	 */
	public void updateBanner(Map<String, Object> params);
	
	/**
	 * 查询banner图
	 * @param ban_id
	 * @return
	 */
	@Select("select * from banner_pic where ban_id=#{ban_id}")
	public BannerPicReport queryBannerById(int ban_id);
	
	/**
	 * 根据banner的id删除banner图片
	 * @param params
	 */
	public void deleteBannerPicById(Map<String, Object> params);
	
	/**
	 * 更新顿巴同心圆的内容
	 * @param params
	 */
	public void updateDunbaVal(Map<String, Object> params);
	
	/**
	 * 获取顿巴同心圆的内容
	 * @return
	 */
	@Select("select * from dunbar_circle where id=1")
	public DunbarCircle queryDunbar();
	
	/**
	 * 修改后台登录的账号
	 * @param params
	 */
	public void modifyAdminUser(Map<String, Object> params);
	
	/**
	 * 保存后台的登录信息
	 * @param params
	 */
	public void saveAdminLoginInfo(Map<String, Object> params);
	
	/**
	 * 登录日志的报表
	 * @param params
	 * @return
	 */
	public List<AdminLoginInfo> adminLogRepot(Map<String, Object> params);
	
	/**
	 * 查询后台登录日志的数量
	 * @return
	 */
	@Select("select count(*) from admin_login_info")
	public int countAdminLog();
	
	/**
	 * 批量删除登录日志
	 * @param ids
	 * @return
	 */
	public int batchDelLog(List<Integer> ids);
	
	/**
	 * 后台的bannner图报表
	 * @param params
	 * @return
	 */
	public List<BannerPicReport> bannerReport(Map<String, Object> params);
	
	/**
	 * 后台批量删除banner
	 * @param ids
	 * @return
	 */
	public int batchDelBanner(List<Integer> ids);
	
	/**
	 * 查询最大的图片id
	 * @return
	 */
	@Select("select MAX(ban_id) from banner_pic")
	public int queryMaxBannerId();
	
	/**
	 * 更新banner图
	 * @param params
	 */
	public void updateBannerPic(Map<String, Object> params);
	
	/**
	 * 插入banner图
	 * @param params
	 */
	public void insertBannerPic(Map<String, Object> params);
	
	/**
	 * 更新用户名查询admin
	 * @param adminName
	 * @return
	 */
	@Select("select * from admin where admin_name=#{adminName}")
	public Admin queryByAdminName(@Param("adminUser") String adminName);
	
	
}
