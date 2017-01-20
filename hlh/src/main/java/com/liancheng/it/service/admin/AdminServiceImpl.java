package com.liancheng.it.service.admin;

import java.io.File;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.liancheng.it.dao.admin.AdminDao;
import com.liancheng.it.entity.admin.Admin;
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

	public boolean checkAmdin(String adminame, String password){
		Admin admin = adminDao.checkAdmin(adminame);
		String md5pwd = "";
		try {
			md5pwd = UUIDUtil.md5(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(admin != null && md5pwd.equals(admin.getPassword())){
			return true;
		}
		return false;
	}


	public JSONObject upBannerPic(MultipartFile bannerPic, String picId, String type, String localBasePath){
		JSONObject jsonObject = new JSONObject();
		BannerPicReport existBanner = adminDao.queryBannerById(Integer.valueOf(picId));
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pic_id", Integer.valueOf(picId));
		
		String BanPicName = String.valueOf(System.currentTimeMillis());
		String picName = "";
		if(bannerPic.isEmpty()){
			params.put("ban_pic", existBanner.getBan_pic());
			BanPicName = existBanner.getBan_pic();
		}else {
			try {
				System.out.println("".equals(existBanner.getBan_pic()));
				//删除服务器上原始picId的图片
				if(!"".equals(existBanner.getBan_pic())){
					File existFile = new File(localBasePath+existBanner.getBan_pic());
					System.out.println(localBasePath+existBanner.getBan_pic());
					System.out.println(existFile.exists());
					if(existFile.exists()){
						System.out.println("服务器存在以前的文件名="+localBasePath+existBanner.getBan_pic());
						existFile.delete();
					}
				}
				picName = bannerPic.getOriginalFilename();//获取上传文件的名称
				//先将图片保存到本地
				bannerPic.transferTo(new File(localBasePath+BanPicName+picName));
				System.out.println("banner图保存服务器的路径="+localBasePath+BanPicName+picName);
			} catch (Exception e) {
				System.out.println("后台banner图保存失败。。。");
				jsonObject.put("msg", "banner图服务器保存失败,请联系系统管理员");
				return jsonObject;
			}
		}
		//保存到数据库
		params.put("ban_pic", BanPicName+picName);
		params.put("type", type);
		params.put("ban_lastmodify_time", new Timestamp(System.currentTimeMillis()));
		System.out.println(params.toString());
		adminDao.updateBanner(params);
		jsonObject.put("msg", "banner图保存成功");
		return jsonObject;
	}
	
	public JSONObject modifyBannerPic(int picId, String type, String localBasePath){
		JSONObject jsonObject = new JSONObject();
		BannerPicReport existBanner = adminDao.queryBannerById(picId);
		//删除服务器上原始picId的图片
		File existFile = new File(localBasePath+existBanner.getBan_pic());
		if(existFile.exists()){
			System.out.println("服务器存在以前的文件名="+localBasePath+existBanner.getBan_pic());
			existFile.delete();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("pic_id", picId);
		params.put("type", type);
		params.put("ban_lastmodify_time", new Timestamp(System.currentTimeMillis()));
		adminDao.deleteBannerPicById(params);
		jsonObject.put("status", true);
		jsonObject.put("msg", "删除图片成功");
		return jsonObject;
	}

	
	
}
