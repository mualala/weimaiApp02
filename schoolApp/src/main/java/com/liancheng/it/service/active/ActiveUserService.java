package com.liancheng.it.service.active;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.liancheng.it.entity.active.ActiveVerify;
import com.liancheng.it.entity.active.ThemeCateg;

import net.minidev.json.JSONObject;

public interface ActiveUserService {
	
	public JSONObject addActive(MultipartFile[] pics, MultipartFile[] docum, 
			String user_id, String type_a, String type_b, String saysay, String title, 
			String position, String localBasePath);//用户添加动态
	public JSONObject showOwnActive(String user_id,String v_user_id, int pageSize, 
			int pageNumber, String hostPath01, String hostPath02);//查看个人动态
	public JSONObject showClassActive(String user_id, String type_a, String type_b, 
			int pageSize, int pageNumber, 
			String hostPath01, String hostPath02);//首页大类的动态展示
	public JSONObject showFriendsActvie(String user_id, int pageSize, int pageNumber, 
			String hostPath01, String hostPath02);//展示朋友圈动态
	
	public JSONObject addSeeActive(int active_user_id);//添加说说查看的数量
	
	public JSONObject adminActive(int pageSize, int pageNumber, String searchText, 
			String startDate, String endDate, String sortName, String sortOrder, String hostPath);//发说说的动态审核
	public JSONObject verifyActive(int active_user_id);//审核通过某条说说
	public JSONObject noVerifyActive(int active_user_id);//审核不通过某条说说
	
	public JSONObject authActive(int pageSize, int pageNumber, String searchText, 
			String sortName, String sortOrder, String hostPath02);//发动态的验证列表
	public List<ThemeCateg> allThemeCateg(String hostPath03);//获取全部的主题分类
	public JSONObject modifyVerify(String state, String class_active, String verify);//修改发动态的验证开关
	public JSONObject keyActive(String text, String hostPath01, String hostPath02, 
			int pageSize, int pageNumber);//说说的关键词检索
//	public JSONObject showdetailActive(int active_user_id, String hostPath01, 
//			String hostPath02);//展示动态的详情页
	public JSONObject batchDoVerify(String ids);//批量保存发动态的验证为1的状态
	public JSONObject deleteOneActive(int active_user_id, String user_id);//删除某条动态
	public JSONObject rankActiveKey();//动态关键词的前7个
	
	public JSONObject addFavor(int active_user_id, String user_id);//添加动态进收藏夹
	public JSONObject favorList(String user_id, int pageSize, int pageNumber, 
			String hostPath01, String hostPath02);//用户的收藏夹列表
	public JSONObject deleteOneFavor(int favor_id);//删除某条收藏
	public JSONObject batchDelThemeCateg(String ids);//批量删除主题大类
	public JSONObject attachThemeCateg(MultipartFile themePic, String themeCateg, String localBasePath);//添加主题大类
	public JSONObject attachActiveTwoCateg(MultipartFile twoPic, String themeCateg, String twoCateg, String localBasePath);//后台添加动态的二级分类
	public JSONObject activeCategReport(int pageSize, int pageNumber, 
			String startDate, String endDate);//动态分类数量的报表
	public JSONObject oneThemeTwoClass(String class_active);
	public JSONObject themeCateg();
	public JSONObject twoCategSelect(String themeCateg);
	public JSONObject detailActiveRepoet(String searchText, String startDate, String endDate, 
			String themeClass, String twoClass, String state, String sortName, String sortOrder, int pageSize, 
			int pageNumber, String hostPath02);
	
	
}
