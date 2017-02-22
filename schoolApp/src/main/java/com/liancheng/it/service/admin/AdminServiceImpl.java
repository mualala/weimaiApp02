package com.liancheng.it.service.admin;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.liancheng.it.dao.active.ActiveUserDao;
import com.liancheng.it.dao.admin.AdminDao;
import com.liancheng.it.dao.commont.CommontDao;
import com.liancheng.it.dao.user.UserDao;
import com.liancheng.it.entity.admin.Admin;
import com.liancheng.it.entity.admin.AdminLoginInfo;
import com.liancheng.it.entity.admin.BannerPicReport;
import com.liancheng.it.entity.admin.DunbarCircle;
import com.liancheng.it.util.DateUtil;
import com.liancheng.it.util.UUIDUtil;

import net.minidev.json.JSONObject;

@Service("adminService")//扫描service
@Aspect
@Transactional
public class AdminServiceImpl implements AdminService {
	@Resource(name="adminDao")
	private AdminDao adminDao;
	@Resource(name="commontDao")
	private CommontDao commontDao;
	@Autowired
	private ActiveUserDao activeUserDao;
	@Autowired
	private UserDao userDao;

	public JSONObject checkAmdin(String adminame, String password, HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		Admin admin = adminDao.checkAdmin(adminame);
		try {
			String md5 = UUIDUtil.md5(password);
			if(admin == null){
				jsonObject.put("status", false);
				jsonObject.put("msg", "用户名错误!");
				return jsonObject;
			}else if(!md5.equals(admin.getPassword())){
				jsonObject.put("status", false);
				jsonObject.put("msg", "登录密码错误!");
				return jsonObject;
			}else {
				String ip = request.getRemoteAddr();
				String browserInfo = request.getHeader("user-agent");
				//记录后台的登录信息
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("admin_id", adminame);
				params.put("login_ip", ip);
				params.put("browser_info", browserInfo);
				params.put("login_time", new Timestamp(System.currentTimeMillis()));
				adminDao.saveAdminLoginInfo(params);
				
				jsonObject.put("status", true);
				jsonObject.put("msg", "登录成功!");
				return jsonObject;
			}
		} catch (Exception e) {
			jsonObject.put("status", false);
			jsonObject.put("msg", "登录失败,请联系系统管理员");
			return jsonObject;
		}
	}
	
	public JSONObject bannerPicReport(String user_id, String hostPath01, String hostPath02){
		JSONObject jsonObject = new JSONObject();
		List<BannerPicReport> bannerPics = adminDao.queryBannerPics();
		if(bannerPics!=null || bannerPics.size()>0){
			for(BannerPicReport ban:bannerPics){
				ban.setBan_pic(hostPath02+ban.getBan_pic());//设置banner图片的第三方url
				try {
					if(ban.getBan_creatime()!=null){
						ban.setStr_ban_creatime(DateUtil.formatDate(ban.getBan_creatime()));
					}
					if(ban.getBan_lastmodify_time()!=null){
						ban.setStr_ban_lastmodify_time(DateUtil.formatDate(ban.getBan_lastmodify_time()));
					}
				} catch (Exception e) {
					System.out.println("banner图展示时间转换错误");
				}
			}
		}
		jsonObject.put("data", bannerPics);
		jsonObject.put("status", true);
		jsonObject.put("totalComNoSee", commontDao.queryTotalComNoSee(user_id));//添加总的未查看评论数量
		return jsonObject;
	}

	public JSONObject upBannerPic(MultipartFile bannerPic, String picId, 
			String thirdURL, String localBasePath){
		JSONObject jsonObject = new JSONObject();
		BannerPicReport existBanner = adminDao.queryBannerById(Integer.valueOf(picId));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ban_id", Integer.valueOf(picId));
		
		String BanPicName = "";
		if(bannerPic.isEmpty()){
			params.put("ban_pic", existBanner.getBan_pic());
			BanPicName = existBanner.getBan_pic();
		}else {
			try {
				//删除服务器上原始picId的图片
				File existFile = new File(localBasePath+existBanner.getBan_pic());
				if(existFile.exists()){
					System.out.println("服务器存在以前的文件名="+localBasePath+existBanner.getBan_pic());
					existFile.delete();
				}
				BanPicName = bannerPic.getOriginalFilename();//获取上传文件的名称
				File serviceBanPic = new File(localBasePath+picId+"__"+BanPicName);
				//先将图片保存到本地
				bannerPic.transferTo(new File(localBasePath+picId+"__"+BanPicName));
				System.out.println("banner图保存服务器的路径="+localBasePath+picId+"__"+BanPicName);
			} catch (Exception e) {
				System.out.println("后台banner图保存失败。。。");
				jsonObject.put("msg", "banner图服务器保存失败,请联系系统管理员");
				return jsonObject;
			}
		}
		if("".equals(thirdURL)){//如果上传的三方路径没有填,就不能覆盖数据库中的值
			params.put("ban_url", existBanner.getBan_url());
		}else {
			params.put("ban_url", thirdURL);
		}
		//保存到数据库
		params.put("ban_pic", picId+"__"+BanPicName);
		params.put("ban_lastmodify_time", new Timestamp(System.currentTimeMillis()));
		adminDao.updateBanner(params);
		jsonObject.put("msg", "banner图保存成功");
		return jsonObject;
	}
	
	public JSONObject modifyBannerPic(int picId, String localBasePath){
		JSONObject jsonObject = new JSONObject();
		BannerPicReport existBanner = adminDao.queryBannerById(picId);
		//删除服务器上原始picId的图片
		File existFile = new File(localBasePath+existBanner.getBan_pic());
		if(existFile.exists()){
			System.out.println("服务器存在以前的文件名="+localBasePath+existBanner.getBan_pic());
			existFile.delete();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ban_id", picId);
		params.put("ban_lastmodify_time", new Timestamp(System.currentTimeMillis()));
		adminDao.deleteBannerPicById(params);
		jsonObject.put("status", true);
		jsonObject.put("msg", "删除图片成功");
		return jsonObject;
	}
	
	public JSONObject editDunbarVal(String dunbaVal){
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("content", dunbaVal);
		params.put("last_modify_time", new Timestamp(System.currentTimeMillis()));
		adminDao.updateDunbaVal(params);
		jsonObject.put("status", true);
		jsonObject.put("msg", "顿巴同心圆内容更新成功");
		return jsonObject;
	}
	
	public JSONObject gotDunbarContent(){
		JSONObject jsonObject = new JSONObject();
		DunbarCircle dunbarCircle = adminDao.queryDunbar();
		jsonObject.put("status", true);
		jsonObject.put("data", dunbarCircle);
		jsonObject.put("msg", "顿巴同心圆内容获取成功");
		return jsonObject;
	}
	
	public JSONObject editAdmin(String admin_name, String password){
		JSONObject jsonObject = new JSONObject();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("admin_name", admin_name);
			params.put("password", UUIDUtil.md5(password));
			adminDao.modifyAdminUser(params);
			jsonObject.put("status", true);
			jsonObject.put("msg", "更新后台账户成功");
			return jsonObject;
		} catch (Exception e) {
			jsonObject.put("status", false);
			jsonObject.put("msg", "更新后台账户失败,md5加密失败");
			return jsonObject;
		}
	}
	
	public JSONObject adminLogReport(String dateVal, String adminId, String sortName, 
			String sortOrder, int pageSize, int pageNumber){
		JSONObject jsonObject = new JSONObject();
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("dateVal", dateVal);
		params.put("adminId", adminId);
		params.put("sortName", sortName);
		params.put("sortOrder", sortOrder);
		params.put("start", start);
		params.put("end", end);
		List<AdminLoginInfo> adminLogs = adminDao.adminLogRepot(params);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(adminLogs!=null){
			for(AdminLoginInfo adminLog:adminLogs){
				adminLog.setStrLoginTime(sdf.format(adminLog.getLogin_time()));
			}
		}
		jsonObject.put("rows", adminLogs);
		jsonObject.put("total", adminDao.countAdminLog());
		return jsonObject;
	}
	
	public JSONObject batchDelLog(String ids){
		JSONObject jsonObject = new JSONObject();
		List<Integer> idList = new ArrayList<Integer>();
		if(!"".equals(ids)){
			String[] spIds = ids.split(",");
			for(int i=0;i<spIds.length;i++){
				idList.add(Integer.valueOf(spIds[i]));
			}
		}
		int count = adminDao.batchDelLog(idList);
		jsonObject.put("status", true);
		jsonObject.put("msg", "成功删除了"+count+"条登录日志");
		return jsonObject;
	}
	
	public JSONObject bannerReport(int pageSize, int pageNumber,String sortName, 
			String sortOrder, String hostPath02){
		JSONObject jsonObject = new JSONObject();
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("end", end);
		params.put("sortName", sortName);
		params.put("sortOrder", sortOrder);
		List<BannerPicReport> bannerReport = adminDao.bannerReport(params);
		if(bannerReport != null){
			for(BannerPicReport b:bannerReport){
				if(b != null){
					b.setBan_pic(hostPath02+b.getBan_pic());
				}
			}
		}
		jsonObject.put("rows", bannerReport);
		jsonObject.put("total", adminDao.TotalBannersCount());
		return jsonObject;
	}
	
	public JSONObject batchDelBanner(String ids){
		JSONObject jsonObject = new JSONObject();
		List<Integer> idList = new ArrayList<Integer>();
		if(!"".equals(ids)){
			String[] spIds = ids.split(",");
			for(int i=0;i<spIds.length;i++){
				idList.add(Integer.valueOf(spIds[i]));
			}
		}
		int count = adminDao.batchDelBanner(idList);
		jsonObject.put("status", true);
		jsonObject.put("msg", "成功删除了"+count+"张banner图");
		return jsonObject;
	}
	
	public JSONObject bannerIndex(){
		JSONObject jsonObject = new JSONObject();
		List<BannerPicReport> banners = adminDao.queryBannerPics();
		List<Integer> picIndexs = new ArrayList<Integer>();
		System.out.println(banners);
		if (banners != null && banners.size()>0) {
			System.out.println("zou  le ");
			int maxId = adminDao.queryMaxBannerId();
			for(BannerPicReport b:banners){
				picIndexs.add(b.getBan_id());
			}
			for(int i=0;i<maxId;i++){
				if(!picIndexs.contains(i+1)){
					picIndexs.add(i+1);
				}
			}
			picIndexs.add(maxId+1);
			Collections.sort(picIndexs);
			jsonObject.put("data", picIndexs);
			return jsonObject;
		}else {
			jsonObject.put("data", picIndexs.add(1));
			System.out.println(jsonObject);
			return jsonObject;
		}
	}
	
	public JSONObject updateBannerPic(MultipartFile bannerPic, String selectPicId, String title, 
			String thredURL, String localBasePath){
		JSONObject jsonObject = new JSONObject();
		String picName = String.valueOf(System.currentTimeMillis());
		String suffix = bannerPic.getOriginalFilename().substring(bannerPic.getOriginalFilename().lastIndexOf("."));//获取文件的后缀名
		List<BannerPicReport> banners = adminDao.queryBannerPics();
		List<Integer> picIndexs = new ArrayList<Integer>();
		if (banners != null) {
			for(BannerPicReport b:banners){
				picIndexs.add(b.getBan_id());
			}
			if(picIndexs.contains(Integer.valueOf(selectPicId))){
				//更新banner图
				//先删除服务器上原先的banner图
				BannerPicReport oldBannerPic = adminDao.queryBannerById(Integer.valueOf(selectPicId));
				File oldPic = new File(localBasePath+oldBannerPic.getBan_pic());
				if(oldPic.isFile() && oldPic.exists()){
					oldPic.delete();
				}
				try {
					//保存到服务器磁盘
					bannerPic.transferTo(new File(localBasePath+picName+suffix));
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("ban_id", selectPicId);
					params.put("title", title);
					params.put("ban_pic", picName+suffix);
					params.put("ban_url", thredURL);
					params.put("ban_lastmodify_time", new Timestamp(System.currentTimeMillis()));
					adminDao.updateBannerPic(params);
					jsonObject.put("msg", "更新banner信息成功");
					return jsonObject;
				} catch (Exception e) {
					System.out.println("banner图保存到服务器磁盘失败");
					jsonObject.put("msg", "添加banner信息失败");
					return jsonObject;
				}
			}else {
				//添加banner图
				try {
					//保存到服务器磁盘
					bannerPic.transferTo(new File(localBasePath+picName+suffix));
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("ban_id", selectPicId);
					params.put("title", title);
					params.put("ban_pic", picName+suffix);
					params.put("ban_url", thredURL);
					params.put("ban_creatime", new Timestamp(System.currentTimeMillis()));
					adminDao.insertBannerPic(params);
					jsonObject.put("msg", "添加banner信息成功");
					return jsonObject;
				} catch (Exception e) {
					System.out.println("banner图保存到服务器磁盘失败");
					jsonObject.put("msg", "添加banner信息失败");
					return jsonObject;
				}
			}
		}else {
			//添加banner图
			try {
				//保存到服务器磁盘
				bannerPic.transferTo(new File(localBasePath+picName+suffix));
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("ban_id", selectPicId);
				params.put("title", title);
				params.put("ban_pic", picName+suffix);
				params.put("ban_url", thredURL);
				params.put("ban_creatime", new Timestamp(System.currentTimeMillis()));
				adminDao.insertBannerPic(params);
				jsonObject.put("msg", "添加banner信息成功");
				return jsonObject;
			} catch (Exception e) {
				System.out.println("banner图保存到服务器磁盘失败");
				jsonObject.put("msg", "添加banner信息失败");
				return jsonObject;
			}
		}
	}
	
	public JSONObject checkOriginalPwd(String adminUser, String originalPwd){
		JSONObject jsonObject = new JSONObject();
		Admin admin = adminDao.queryByAdminName(adminUser);
		try {
			if(admin != null){
				if(!(UUIDUtil.md5(originalPwd)).equals(admin.getPassword())){
					jsonObject.put("status", false);
					jsonObject.put("msg", "原始密码有误");
					return jsonObject;
				}else {
					jsonObject.put("status", true);
					jsonObject.put("msg", "原始密码正确");
					return jsonObject;
				}
			}else {
				jsonObject.put("status", false);
				jsonObject.put("msg", "账户名有误");
				return jsonObject;
			}
		} catch (Exception e) {
			System.out.println("后台修改密码md5失败");
		}
		return jsonObject;
	}
	
	public JSONObject getNotifies(){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("noActiveNotify", activeUserDao.totalNoVerifyActive());
		jsonObject.put("noUserNotify", userDao.totalNoVerifyUser());
		return jsonObject;
	}
	
}
