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
import javax.servlet.http.HttpSession;

import net.minidev.json.JSONObject;
import net.sf.json.JSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.liancheng.it.util.UUIDUtil;

/**
 * 用户动态
 */
@Controller
@RequestMapping("/active")
public class ActiveUserController {
	
	@Resource
	private ActiveUserService activeUserService;
	@Resource(name="activeUserDao")
	private ActiveUserDao activeUserDao;//用于资源下载是记录数量
	
	/**
	 * 用户发表动态
	 */
	@RequestMapping(value="/addActive.do", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addActive(
			@RequestParam(value="active_pic",required=false) MultipartFile[] pics, 
			@RequestParam(value="docum",required=false) MultipartFile[] docum, 
			HttpServletRequest request){
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String user_id = multipartRequest.getParameter("user_id");
		String type_a = multipartRequest.getParameter("type_a");
		String type_b = multipartRequest.getParameter("type_b");
		String saysay = multipartRequest.getParameter("saysay");
//		int pageSize = Integer.valueOf(multipartRequest.getParameter("pageSize"));
		System.out.println(pics+"\n"+docum);
		System.out.println("进了发动态的请求。。。");
		System.out.println("user_id="+user_id+",type_a="+type_a+",type_b="+",saysay="+saysay);
		//保存文件的本地路径
		String localBasePath = request.getSession().getServletContext().getRealPath("/")+"images\\local_active\\";
		
		JSONObject jsonObject = activeUserService.addActive(pics, docum, user_id, type_a, type_b, saysay, localBasePath);
		System.out.println("返回的json="+jsonObject);
		return jsonObject;
		//返回更新的动态数据
		//项目环境下的图片路径
//		String path = request.getContextPath();
//		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
//		String hostPath02 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_active/";
//		if((Boolean) jsonObject.get("status")){
//			return activeUserService.showClassActive(type_a, pageSize, 1, hostPath01, hostPath02, user_id);
//		}else {
//			
//		}
	}
	
	/**
	 * 展示个人动态
	 * @param user_id 用户的uuid
	 * @param pageSize 每页显示的记录数
	 * @param pageNumber 第几页
	 */
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
		
		System.out.println("进了个人动态展示。。"+hostPath02);
		JSONObject jsonObject = activeUserService.showOwnActive(user_id, v_user_id, pageSize, pageNumber, hostPath01, hostPath02);
		
		return jsonObject;
	}
	
	
	/**
	 * 首页大类的动态展示,和首页动态的展示
	 */
	@RequestMapping(value="/classActive.do")
	@ResponseBody
	public JSONObject friendsActive(@RequestParam("type_a") String type_a, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			@RequestParam("com_user_id") String com_user_id, 
			HttpServletRequest request){
		System.out.println("进了展示大类的请求。。。");
		System.out.println("type_a="+type_a+",pageSize="+pageSize+",pageNumber="+pageNumber);
		
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		String hostPath02 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_active/";
		System.out.println("进了首页大类的动态展示。。"+hostPath02);
		JSONObject jsonObject = activeUserService.showClassActive(type_a, pageSize, 
				pageNumber, hostPath01, hostPath02, com_user_id);
//		HttpSession session = request.getSession();
//		System.out.println("动态里面获取session="+session.getAttribute("username"));
		System.out.println("active中A的属性值="+request.getServletContext().getAttribute("username"));
		return jsonObject;
	}
	
	
	/**
	 * 展示朋友圈动态
	 */
	@RequestMapping(value="/friendsActive.do")
	@ResponseBody
	public JSONObject showCycFriendsActive(@RequestParam("user_id") String user_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			@RequestParam("com_user_id") String com_user_id, 
			HttpServletRequest request){
		
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		String hostPath02 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_active/";
		System.out.println("进了朋友圈动态的展示！！");
		JSONObject jsonObject = activeUserService.showFriendsActvie(user_id, pageSize, 
				pageNumber, hostPath01, hostPath02, com_user_id);
		return jsonObject;
	}
	
	/**
	 * 说说添加查看数量
	 */
	@RequestMapping(value="/seeAction.do")
	@ResponseBody
	public JSONObject seeActive(@RequestParam("active_user_id") int active_user_id){
		
		System.out.println("进入追加说说查看的数量！！");
		JSONObject jsonObject = activeUserService.addSeeActive(active_user_id);
		
		return jsonObject;
	}
	
	/**
	 * 动态的全文检索请求
	 */
	@RequestMapping(value="/fullText.do")
	@ResponseBody
	public JSONObject fullText(@RequestParam("text") String text, 
			HttpServletRequest request){
		
		System.out.println("动态的全文检索请求！！");
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		String hostPath02 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_active/";
		JSONObject jsonObject = activeUserService.keyActive(text, hostPath01, hostPath02);
		
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
//		response.setContentType("text/html;charset=utf-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		//保存文件的本地路径
		String localBasePath = request.getSession().getServletContext().getRealPath("/")+"images\\local_active\\";
		
		String downloadPath = localBasePath+"__"+fileName;
		System.out.println(downloadPath);
		
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
		return null;
	}
	
	
	
	
}
