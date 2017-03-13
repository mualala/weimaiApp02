package com.liancheng.it.controller.luntan;

import javax.servlet.http.HttpServletRequest;

import net.minidev.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.liancheng.it.service.luntan.LuntanService;

@Controller
@RequestMapping("/luntan")
public class LuntanController {
	
	@Autowired
	private LuntanService luntanService;
	
	@RequestMapping(value="/addCommont.do",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addCommont(
			@RequestParam(value="pics",required=false) MultipartFile[] pics, 
			HttpServletRequest req){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
		String user_id = multipartRequest.getParameter("user_id");
		String lt_content = multipartRequest.getParameter("lt_content");
		String type = multipartRequest.getParameter("type");
		String lt_type = multipartRequest.getParameter("lt_type");
		String area = multipartRequest.getParameter("location");
		//保存文件的本地路径
		String localBasePath = req.getSession().getServletContext().getRealPath("/")+"images/luntan/";
		JSONObject jsonObject = luntanService.addCommont(pics, user_id, lt_content, type, lt_type, area, localBasePath);
		return jsonObject;
	}
	
	@RequestMapping("/showPaginationLT.do")
	@ResponseBody
	public JSONObject showPaginationLT(@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			@RequestParam("type") String type, 
			@RequestParam("lt_type") int lt_type, 
			@RequestParam("area") String area, 
			HttpServletRequest req){
		//项目环境下的图片路径
		String path = req.getContextPath();
		String hostPath01 = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/images/local_user_profile/";
		String hostPath02 = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/images/luntan/";
		JSONObject jsonObject = luntanService.showPaginationLT(pageSize, pageNumber, type, 
				lt_type, area, hostPath01, hostPath02);
		return jsonObject;
	}
	
	@RequestMapping("/showDetailLT.do")
	@ResponseBody
	public JSONObject showDetailLT(@RequestParam("lt_id") int lt_id, 
			HttpServletRequest req){
		//项目环境下的图片路径
		String path = req.getContextPath();
		String hostPath01 = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/images/local_user_profile/";
		String hostPath02 = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/images/luntan/";
		JSONObject jsonObject = luntanService.showDetailLT(lt_id, hostPath01, hostPath02);
		return jsonObject;
	}
	
	@RequestMapping("/addOneLVCommont.do")
	@ResponseBody
	public JSONObject addOneLVCommont(@RequestParam("user_id") String user_id, 
			@RequestParam("other_user_id") String other_user_id, 
			@RequestParam("lt_id") String lt_id, 
			@RequestParam("content") String content){
		JSONObject jsonObject = luntanService.addOneLVCommont(user_id, other_user_id, lt_id, content);
		return jsonObject;
	}
	
	@RequestMapping("/addTwoLVCommont.do")
	@ResponseBody
	public JSONObject addTwoLVCommont(@RequestParam("user_id") String user_id, 
			@RequestParam("other_user_id") String other_user_id, 
			@RequestParam("comm_id") String comm_id, 
			@RequestParam("content") String content){
		JSONObject jsonObject = luntanService.addTwoLVCommont(user_id, other_user_id, comm_id, content);
		return jsonObject;
	}
	
	
}
