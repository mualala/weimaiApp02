package com.liancheng.it.controller.active;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.minidev.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.liancheng.it.dao.active.ActiveUserDao;
import com.liancheng.it.entity.active.Active;
import com.liancheng.it.service.active.ActiveUserService;
import com.liancheng.it.service.admin.AdminService;
import com.liancheng.it.service.friends.FriendsService;

/**
 * 用户动态
 */
@Controller
@RequestMapping("/active")
public class ActiveUserController {
	
	@Resource
	private ActiveUserService activeUserService;
	@Resource(name="activeUserDao")
	private ActiveUserDao activeUserDao;//资源下载记录数量
	@Autowired
	private AdminService adminService;
	@Autowired
	private FriendsService friendsService;
	
	
	@RequestMapping(value="/addActive.do", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addActive(
			@RequestParam(value="active_pic",required=false) MultipartFile[] pics, 
			@RequestParam(value="docum",required=false) MultipartFile[] docum, 
			HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String user_id = multipartRequest.getParameter("user_id");
		String type_a = multipartRequest.getParameter("type_a");
		String type_b = multipartRequest.getParameter("type_b");
		String saysay = multipartRequest.getParameter("saysay");
		String title = multipartRequest.getParameter("title");
		String position = multipartRequest.getParameter("position");
		//保存文件的本地路径
		String localBasePath = request.getSession().getServletContext().getRealPath("/")+"images/local_active/";
		jsonObject = activeUserService.addActive(pics, docum, user_id, type_a, type_b, saysay, title, position, localBasePath);
		return jsonObject;
	}
	
	@RequestMapping(value="/showOwnActive.do")
	@ResponseBody
	public JSONObject ownActive(@RequestParam("user_id") String user_id, 
			@RequestParam("v_user_id") String v_user_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		String hostPath02 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_active/";
		JSONObject jsonObject = activeUserService.showOwnActive(user_id, v_user_id, pageSize, pageNumber, hostPath01, hostPath02);
		return jsonObject;
	}
	
	@RequestMapping(value="/classActive.do")
	@ResponseBody
	public JSONObject friendsActive(@RequestParam("user_id") String user_id, 
			@RequestParam("type_a") String type_a, 
			@RequestParam(value="type_b",required=false) String type_b, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		String hostPath02 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_active/";
		JSONObject jsonObject = activeUserService.showClassActive(user_id, type_a, type_b, pageSize, 
				pageNumber, hostPath01, hostPath02);
		return jsonObject;
	}
	
	@RequestMapping(value="/friendsActive.do")
	@ResponseBody
	public JSONObject showCycFriendsActive(@RequestParam("user_id") String user_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		String hostPath02 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_active/";
		JSONObject jsonObject = activeUserService.showFriendsActvie(user_id, pageSize, 
				pageNumber, hostPath01, hostPath02);
		return jsonObject;
	}
	
	@RequestMapping(value="/seeAction.do")
	@ResponseBody
	public JSONObject seeActive(@RequestParam("active_user_id") int active_user_id){
		JSONObject jsonObject = activeUserService.addSeeActive(active_user_id);
		return jsonObject;
	}
	
	@RequestMapping(value="/fullText.do")
	@ResponseBody
	public JSONObject fullText(@RequestParam("text") String text, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		String hostPath02 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_active/";
		JSONObject jsonObject = activeUserService.keyActive(text, hostPath01, hostPath02, pageSize, pageNumber);
		return jsonObject;
	}
	
	/**
	 * 下载动态中的资源文件，单独使用restful风格
	 */
	@RequestMapping("/{fileName}/download")
	public ModelAndView download(@PathVariable("fileName") String fileName, 
			@RequestParam(value="active_user_id",required=false) int active_user_id, 
			HttpServletRequest request, 
			HttpServletResponse response){
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		//保存文件的本地路径
		String localBasePath = request.getSession().getServletContext().getRealPath("/")+"images/local_active/";
		
		String downloadPath = localBasePath+"__"+fileName;
		System.out.println(downloadPath);
		if(new File(downloadPath).exists()){
			try {
				response.setContentType("application/x-msdownload;");
				response.setHeader("Content-Disposition", "attachment; filename=\""+new String(fileName.getBytes("UTF-8"))+"\"");
				bis = new BufferedInputStream(new FileInputStream(downloadPath));
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] buff = new byte[2048];
				int bytesRead;
				while(-1 != (bytesRead = bis.read(buff,0,buff.length))){
					bos.write(buff, 0, bytesRead);
				}
				Active active = activeUserDao.queryDownCount(active_user_id);
				//先查询下载次数
				String docCount = active.getDoc_down_count();
				String[] docCounts = docCount.split(",");
				System.out.println(docCounts.length);
				//获取文件的位置
				String docStr = active.getDocum();
				String[] docNames = docStr.split(",");
				for(int i=0;i<docNames.length;i++){
					if(docNames[i].equals("__"+fileName)){//匹配成功,是文件的位置,改变相应的值
						System.out.println("文件的位置="+i);
						docCounts[i] = String.valueOf(Integer.valueOf(docCounts[i])+1);//对应的下载次数加一
					}
				}
				//重新拼接doc_down_count字段
				String docDownCount = "";
				for(String doc:docCounts){
					docDownCount = docDownCount+doc+",";
				}
				System.out.println(docDownCount);
				//增加数据库表中的下载次数
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("active_user_id", active_user_id);
				params.put("doc_down_count", docDownCount);
				activeUserDao.addDownCount(params);
			} catch (Exception e) {
				System.out.println("下载文件失败!");
				e.printStackTrace();
			} finally {
				if(bis != null){
					try {
						bis.close();
					} catch (IOException e) {
						System.out.println("bis关闭失败!");
					}
				}
				if (bos != null) {
					try {
						bos.close();
					} catch (IOException e) {
						System.out.println("bos关闭失败!");
					}
				}
			}
		}
		return null;
	}
	
	@RequestMapping("/deleteOneActive.do")
	@ResponseBody
	public JSONObject deleteOneActive(@RequestParam("active_user_id") int active_user_id, 
			@RequestParam("user_id") String user_id){
		JSONObject jsonObject = activeUserService.deleteOneActive(active_user_id, user_id);
		return jsonObject;
	}
	
	@RequestMapping("/hotActiveKey.do")
	@ResponseBody
	public JSONObject hotActiveKey(){
		JSONObject jsonObject = activeUserService.rankActiveKey();
		return jsonObject;
	}
	
	@RequestMapping("/addFavorites.do")
	@ResponseBody
	public JSONObject addFavorites(@RequestParam("active_user_id") int active_user_id, 
			@RequestParam("user_id") String user_id){
		JSONObject jsonObject = activeUserService.addFavor(active_user_id, user_id);
		return jsonObject;
	}
	
	@RequestMapping("/showFavoritesList.do")
	@ResponseBody
	public JSONObject showFavoritesList(@RequestParam("user_id") String user_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		String hostPath02 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_active/";
		JSONObject jsonObject = activeUserService.favorList(user_id, pageSize, pageNumber, hostPath01, hostPath02);
		return jsonObject;
	}
	
	@RequestMapping("/deleteFavor.do")
	@ResponseBody
	public JSONObject deleteFavor(@RequestParam("favor_id") int favor_id){
		JSONObject jsonObject = activeUserService.deleteOneFavor(favor_id);
		return jsonObject;
	}
	
	/**
	 * app首页加载的默认数据
	 */
	@RequestMapping("/showAppMain.do")
	@ResponseBody
	public JSONObject showAppMain(@RequestParam("user_id") String user_id, 
			HttpServletRequest request){
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		String hostPath02 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_banner/";
		String hostPath03 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_active/theme/";
		JSONObject jsonObject = adminService.bannerPicReport(user_id, hostPath01, hostPath02);//添加banner图
		jsonObject.put("fTof", friendsService.showfTof(user_id, hostPath01));//添加朋友的朋友
		jsonObject.put("AllActiveCateg", activeUserService.allThemeCateg(hostPath03));//添加所有的主题分类
		if(activeUserDao.queryCurrFriendProfile(user_id) != null){
			jsonObject.put("currFriendProfile", hostPath01+activeUserDao.queryCurrFriendProfile(user_id).getProfile());
		}else {
			jsonObject.put("currFriendProfile", "");
		}
		return jsonObject;
	}
	
	@RequestMapping("/oneThemeTwoClass.do")
	@ResponseBody
	public JSONObject oneThemeTwoClass(@RequestParam("type_a") String class_active){
		JSONObject jsonObject = activeUserService.oneThemeTwoClass(class_active);
		return jsonObject;
	}
	
	
}
