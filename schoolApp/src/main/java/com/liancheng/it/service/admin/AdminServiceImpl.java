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

@Service("adminService")//ɨ��service
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
				jsonObject.put("msg", "�û�������!");
				return jsonObject;
			}else if(!md5.equals(admin.getPassword())){
				jsonObject.put("status", false);
				jsonObject.put("msg", "��¼�������!");
				return jsonObject;
			}else {
				String ip = request.getRemoteAddr();
				String browserInfo = request.getHeader("user-agent");
				//��¼��̨�ĵ�¼��Ϣ
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("admin_id", adminame);
				params.put("login_ip", ip);
				params.put("browser_info", browserInfo);
				params.put("login_time", new Timestamp(System.currentTimeMillis()));
				adminDao.saveAdminLoginInfo(params);
				
				jsonObject.put("status", true);
				jsonObject.put("msg", "��¼�ɹ�!");
				return jsonObject;
			}
		} catch (Exception e) {
			jsonObject.put("status", false);
			jsonObject.put("msg", "��¼ʧ��,����ϵϵͳ����Ա");
			return jsonObject;
		}
	}
	
	public JSONObject bannerPicReport(String user_id, String hostPath01, String hostPath02){
		JSONObject jsonObject = new JSONObject();
		List<BannerPicReport> bannerPics = adminDao.queryBannerPics();
		if(bannerPics!=null || bannerPics.size()>0){
			for(BannerPicReport ban:bannerPics){
				ban.setBan_pic(hostPath02+ban.getBan_pic());//����bannerͼƬ�ĵ�����url
				try {
					if(ban.getBan_creatime()!=null){
						ban.setStr_ban_creatime(DateUtil.formatDate(ban.getBan_creatime()));
					}
					if(ban.getBan_lastmodify_time()!=null){
						ban.setStr_ban_lastmodify_time(DateUtil.formatDate(ban.getBan_lastmodify_time()));
					}
				} catch (Exception e) {
					System.out.println("bannerͼչʾʱ��ת������");
				}
			}
		}
		jsonObject.put("data", bannerPics);
		jsonObject.put("status", true);
		jsonObject.put("totalComNoSee", commontDao.queryTotalComNoSee(user_id));//����ܵ�δ�鿴��������
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
				//ɾ����������ԭʼpicId��ͼƬ
				File existFile = new File(localBasePath+existBanner.getBan_pic());
				if(existFile.exists()){
					System.out.println("������������ǰ���ļ���="+localBasePath+existBanner.getBan_pic());
					existFile.delete();
				}
				BanPicName = bannerPic.getOriginalFilename();//��ȡ�ϴ��ļ�������
				File serviceBanPic = new File(localBasePath+picId+"__"+BanPicName);
				//�Ƚ�ͼƬ���浽����
				bannerPic.transferTo(new File(localBasePath+picId+"__"+BanPicName));
				System.out.println("bannerͼ�����������·��="+localBasePath+picId+"__"+BanPicName);
			} catch (Exception e) {
				System.out.println("��̨bannerͼ����ʧ�ܡ�����");
				jsonObject.put("msg", "bannerͼ����������ʧ��,����ϵϵͳ����Ա");
				return jsonObject;
			}
		}
		if("".equals(thirdURL)){//����ϴ�������·��û����,�Ͳ��ܸ������ݿ��е�ֵ
			params.put("ban_url", existBanner.getBan_url());
		}else {
			params.put("ban_url", thirdURL);
		}
		//���浽���ݿ�
		params.put("ban_pic", picId+"__"+BanPicName);
		params.put("ban_lastmodify_time", new Timestamp(System.currentTimeMillis()));
		adminDao.updateBanner(params);
		jsonObject.put("msg", "bannerͼ����ɹ�");
		return jsonObject;
	}
	
	public JSONObject modifyBannerPic(int picId, String localBasePath){
		JSONObject jsonObject = new JSONObject();
		BannerPicReport existBanner = adminDao.queryBannerById(picId);
		//ɾ����������ԭʼpicId��ͼƬ
		File existFile = new File(localBasePath+existBanner.getBan_pic());
		if(existFile.exists()){
			System.out.println("������������ǰ���ļ���="+localBasePath+existBanner.getBan_pic());
			existFile.delete();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ban_id", picId);
		params.put("ban_lastmodify_time", new Timestamp(System.currentTimeMillis()));
		adminDao.deleteBannerPicById(params);
		jsonObject.put("status", true);
		jsonObject.put("msg", "ɾ��ͼƬ�ɹ�");
		return jsonObject;
	}
	
	public JSONObject editDunbarVal(String dunbaVal){
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("content", dunbaVal);
		params.put("last_modify_time", new Timestamp(System.currentTimeMillis()));
		adminDao.updateDunbaVal(params);
		jsonObject.put("status", true);
		jsonObject.put("msg", "�ٰ�ͬ��Բ���ݸ��³ɹ�");
		return jsonObject;
	}
	
	public JSONObject gotDunbarContent(){
		JSONObject jsonObject = new JSONObject();
		DunbarCircle dunbarCircle = adminDao.queryDunbar();
		jsonObject.put("status", true);
		jsonObject.put("data", dunbarCircle);
		jsonObject.put("msg", "�ٰ�ͬ��Բ���ݻ�ȡ�ɹ�");
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
			jsonObject.put("msg", "���º�̨�˻��ɹ�");
			return jsonObject;
		} catch (Exception e) {
			jsonObject.put("status", false);
			jsonObject.put("msg", "���º�̨�˻�ʧ��,md5����ʧ��");
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
		jsonObject.put("msg", "�ɹ�ɾ����"+count+"����¼��־");
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
		jsonObject.put("msg", "�ɹ�ɾ����"+count+"��bannerͼ");
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
		String suffix = bannerPic.getOriginalFilename().substring(bannerPic.getOriginalFilename().lastIndexOf("."));//��ȡ�ļ��ĺ�׺��
		List<BannerPicReport> banners = adminDao.queryBannerPics();
		List<Integer> picIndexs = new ArrayList<Integer>();
		if (banners != null) {
			for(BannerPicReport b:banners){
				picIndexs.add(b.getBan_id());
			}
			if(picIndexs.contains(Integer.valueOf(selectPicId))){
				//����bannerͼ
				//��ɾ����������ԭ�ȵ�bannerͼ
				BannerPicReport oldBannerPic = adminDao.queryBannerById(Integer.valueOf(selectPicId));
				File oldPic = new File(localBasePath+oldBannerPic.getBan_pic());
				if(oldPic.isFile() && oldPic.exists()){
					oldPic.delete();
				}
				try {
					//���浽����������
					bannerPic.transferTo(new File(localBasePath+picName+suffix));
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("ban_id", selectPicId);
					params.put("title", title);
					params.put("ban_pic", picName+suffix);
					params.put("ban_url", thredURL);
					params.put("ban_lastmodify_time", new Timestamp(System.currentTimeMillis()));
					adminDao.updateBannerPic(params);
					jsonObject.put("msg", "����banner��Ϣ�ɹ�");
					return jsonObject;
				} catch (Exception e) {
					System.out.println("bannerͼ���浽����������ʧ��");
					jsonObject.put("msg", "���banner��Ϣʧ��");
					return jsonObject;
				}
			}else {
				//���bannerͼ
				try {
					//���浽����������
					bannerPic.transferTo(new File(localBasePath+picName+suffix));
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("ban_id", selectPicId);
					params.put("title", title);
					params.put("ban_pic", picName+suffix);
					params.put("ban_url", thredURL);
					params.put("ban_creatime", new Timestamp(System.currentTimeMillis()));
					adminDao.insertBannerPic(params);
					jsonObject.put("msg", "���banner��Ϣ�ɹ�");
					return jsonObject;
				} catch (Exception e) {
					System.out.println("bannerͼ���浽����������ʧ��");
					jsonObject.put("msg", "���banner��Ϣʧ��");
					return jsonObject;
				}
			}
		}else {
			//���bannerͼ
			try {
				//���浽����������
				bannerPic.transferTo(new File(localBasePath+picName+suffix));
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("ban_id", selectPicId);
				params.put("title", title);
				params.put("ban_pic", picName+suffix);
				params.put("ban_url", thredURL);
				params.put("ban_creatime", new Timestamp(System.currentTimeMillis()));
				adminDao.insertBannerPic(params);
				jsonObject.put("msg", "���banner��Ϣ�ɹ�");
				return jsonObject;
			} catch (Exception e) {
				System.out.println("bannerͼ���浽����������ʧ��");
				jsonObject.put("msg", "���banner��Ϣʧ��");
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
					jsonObject.put("msg", "ԭʼ��������");
					return jsonObject;
				}else {
					jsonObject.put("status", true);
					jsonObject.put("msg", "ԭʼ������ȷ");
					return jsonObject;
				}
			}else {
				jsonObject.put("status", false);
				jsonObject.put("msg", "�˻�������");
				return jsonObject;
			}
		} catch (Exception e) {
			System.out.println("��̨�޸�����md5ʧ��");
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
