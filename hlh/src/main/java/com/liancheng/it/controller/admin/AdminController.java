package com.liancheng.it.controller.admin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.liancheng.it.service.admin.AdminService;
import com.liancheng.it.service.user.UserService;

import net.minidev.json.JSONObject;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Resource
	private AdminService adminService;
	@Resource
	private UserService userService;
	
	/**
	 * 登录请求
	 */
	@RequestMapping(value="/login.do",produces="application/json;charset=UTF-8")
	public String login(@RequestParam("username") String adminame, 
			@RequestParam("password") String password, HttpServletRequest request){
		
		System.out.println("username="+adminame+",password="+password);
		if("".equals(adminame) || "".equals(password)){
			return "admin_reLogin";
		}
		if(adminService.checkAmdin(adminame, password)){
			request.setAttribute("adminame", adminame);
			return "admin_home";
		}
		return "admin_reLogin";
	}
	
	/**
	 * 后台管理系统对资源文件的下载的跳转请求
	 */
	@RequestMapping("/{fileName}/toDownDocum.do")
	public ModelAndView toDownDocum(@PathVariable("fileName") String documName, 
			HttpServletRequest request, 
			HttpServletResponse response){
		System.out.println("进了跳转请求");
		
//		response.setContentType("text/html;charset=utf-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		//保存文件的本地路径
		String localBasePath = request.getSession().getServletContext().getRealPath("/")+"images\\local_active\\";
		
		String downloadPath = localBasePath+"__"+documName;
		System.out.println(downloadPath);
		
		try {
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-Disposition", "attachment; filename=\""+new String(documName.getBytes("UTF-8"))+"\"");
			bis = new BufferedInputStream(new FileInputStream(downloadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while(-1 != (bytesRead = bis.read(buff,0,buff.length))){
				bos.write(buff, 0, bytesRead);
			}		
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
	
	/**
	 * 后台上传banner图片
	 */
	@RequestMapping("/upOneBannerPic.do")
	@ResponseBody
	public JSONObject upOneBannerPic(
			@RequestParam("bannerPic") MultipartFile bannerPic, 
			HttpServletRequest request){
		System.out.println("进了后台上传banner图片。。");
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String picId = multipartRequest.getParameter("picId");
		String type = multipartRequest.getParameter("type");
		//保存文件的本地路径
		String localBasePath = request.getSession().getServletContext().getRealPath("/")+"images\\local_banner\\";
		JSONObject jsonObject = adminService.upBannerPic(bannerPic, picId, type, localBasePath);
		return jsonObject;
	}
	
	/**
	 * 删除banner图
	 */
	@RequestMapping("/deleteBannerPic.do")
	@ResponseBody
	public JSONObject deleteBannerPic(@RequestParam("picId") int picId, 
			@RequestParam("type") String type, 
			HttpServletRequest request){
		System.out.println("进了后台删除banner图片。。。");
		//保存文件的本地路径
		String localBasePath = request.getSession().getServletContext().getRealPath("/")+"images\\local_banner\\";
		JSONObject jsonObject = adminService.modifyBannerPic(picId, type, localBasePath);
		return jsonObject;
	}
	
	
	
}
