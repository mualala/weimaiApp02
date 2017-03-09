package com.liancheng.it.service.active;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.liancheng.it.entity.active.ThemeCateg;

import net.minidev.json.JSONObject;

public interface ActiveUserService {
	
	/**
	 * 用户发表动态
	 * @param pics
	 * @param docum
	 * @param user_id
	 * @param type_a
	 * @param type_b
	 * @param saysay
	 * @param title
	 * @param position
	 * @param localBasePath
	 * @return
	 */
	public JSONObject addActive(MultipartFile[] pics, MultipartFile[] docum, 
			String user_id, String type_a, String type_b, String saysay, String title, 
			String position, String localBasePath);
	
	/**
	 * 展示个人动态
	 * @param user_id
	 * @param v_user_id
	 * @param pageSize
	 * @param pageNumber
	 * @param hostPath01
	 * @param hostPath02
	 * @return
	 */
	public JSONObject showOwnActive(String user_id,String v_user_id, int pageSize, 
			int pageNumber, String hostPath01, String hostPath02);
	
	/**
	 * 首页大类的动态展示,和首页动态的展示
	 * @param user_id
	 * @param type_a
	 * @param type_b
	 * @param pageSize
	 * @param pageNumber
	 * @param hostPath01
	 * @param hostPath02
	 * @return
	 */
	public JSONObject showClassActive(String user_id, String type_a, String type_b, 
			int pageSize, int pageNumber, 
			String hostPath01, String hostPath02);
	
	/**
	 * 展示朋友圈动态
	 * @param user_id
	 * @param pageSize
	 * @param pageNumber
	 * @param hostPath01
	 * @param hostPath02
	 * @return
	 */
	public JSONObject showFriendsActvie(String user_id, int pageSize, int pageNumber, 
			String hostPath01, String hostPath02);
	
	/**
	 * 说说添加查看数量
	 * @param active_user_id
	 * @return
	 */
	public JSONObject addSeeActive(int active_user_id);
	
	/**
	 * 对说说的审核
	 * @param pageSize
	 * @param pageNumber
	 * @param searchText
	 * @param startDate
	 * @param endDate
	 * @param sortName
	 * @param sortOrder
	 * @param hostPath
	 * @return
	 */
	public JSONObject adminActive(int pageSize, int pageNumber, String searchText, 
			String startDate, String endDate, String sortName, String sortOrder, String hostPath);
	
	/**
	 * 动态审核通过
	 * @param active_user_id
	 * @return
	 */
	public JSONObject verifyActive(int active_user_id);
	
	/**
	 * 动态审核不通过
	 * @param active_user_id
	 * @return
	 */
	public JSONObject noVerifyActive(int active_user_id);
	
	/**
	 * 后台对发说说的权限验证列表
	 * @param pageSize
	 * @param pageNumber
	 * @param searchText
	 * @param sortName
	 * @param sortOrder
	 * @param hostPath02
	 * @return
	 */
	public JSONObject authActive(int pageSize, int pageNumber, String searchText, 
			String sortName, String sortOrder, String hostPath02);
	
	/**
	 * 获取全部的主题分类
	 * @param hostPath03
	 * @return
	 */
	public List<ThemeCateg> allThemeCateg(String hostPath03);
	
	/**
	 * 更改发动态的权限
	 * @param state 是否开启发动态验证,0和1
	 * @param class_active 动态的分类
	 * @param verify 是学生还是毕业证验证
	 * @return
	 */
	public JSONObject modifyVerify(String state, String class_active, String verify);
	
	/**
	 * 动态的全文检索请求
	 * @param text
	 * @param hostPath01
	 * @param hostPath02
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	public JSONObject keyActive(String text, String hostPath01, String hostPath02, 
			int pageSize, int pageNumber);
	
	/**
	 * 批量保存发动态的验证为1的状态
	 * @param ids
	 * @return
	 */
	public JSONObject batchDoVerify(String ids);
	
	/**
	 * 删除动态
	 * @param active_user_id
	 * @param user_id
	 * @return
	 */
	public JSONObject deleteOneActive(int active_user_id, String user_id);
	
	/**
	 * 动态搜索的关键词排行前7个
	 * @return
	 */
	public JSONObject rankActiveKey();
	
	/**
	 * 用户添加动态的收藏
	 * @param active_user_id
	 * @param user_id
	 * @return
	 */
	public JSONObject addFavor(int active_user_id, String user_id);
	
	/**
	 * 用户的动态收藏列表
	 * @param user_id
	 * @param pageSize
	 * @param pageNumber
	 * @param hostPath01
	 * @param hostPath02
	 * @return
	 */
	public JSONObject favorList(String user_id, int pageSize, int pageNumber, 
			String hostPath01, String hostPath02);
	
	/**
	 * 删除某条收藏的动态
	 * @param favor_id
	 * @return
	 */
	public JSONObject deleteOneFavor(int favor_id);
	
	/**
	 * 批量删除主题大类
	 * @param ids
	 * @return
	 */
	public JSONObject batchDelThemeCateg(String ids);
	
	/**
	 * 添加主题大类
	 * @param themePic
	 * @param themeCateg
	 * @param localBasePath
	 * @return
	 */
	public JSONObject attachThemeCateg(MultipartFile themePic, String themeCateg, String localBasePath);
	
	/**
	 * 后台添加动态的二级分类
	 * @param twoPic
	 * @param themeCateg
	 * @param twoCateg
	 * @param localBasePath
	 * @return
	 */
	public JSONObject attachActiveTwoCateg(MultipartFile twoPic, String themeCateg, String twoCateg, String localBasePath);
	
	/**
	 * 动态分类数量的报表
	 * @param pageSize
	 * @param pageNumber
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public JSONObject activeCategReport(int pageSize, int pageNumber, 
			String startDate, String endDate);
	
	/**
	 * 根据主题大类得到某分类下的二级分类
	 * @param class_active
	 * @return
	 */
	public JSONObject oneThemeTwoClass(String class_active);
	
	/**
	 * 主题类别
	 * @return
	 */
	public JSONObject themeCateg();
	
	/**
	 * 二级分类
	 * @param themeCateg
	 * @return
	 */
	public JSONObject twoCategSelect(String themeCateg);
	
	/**
	 * 后台的动态详细报表
	 * @param searchText
	 * @param startDate
	 * @param endDate
	 * @param themeClass
	 * @param twoClass
	 * @param state
	 * @param sortName
	 * @param sortOrder
	 * @param pageSize
	 * @param pageNumber
	 * @param hostPath02
	 * @return
	 */
	public JSONObject detailActiveRepoet(String searchText, String startDate, String endDate, 
			String themeClass, String twoClass, String state, String sortName, String sortOrder, int pageSize, 
			int pageNumber, String hostPath02);
	
	
	
}
