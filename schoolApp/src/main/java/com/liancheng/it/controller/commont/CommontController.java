package com.liancheng.it.controller.commont;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.minidev.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liancheng.it.service.commont.CommontService;

@Controller
@RequestMapping(value="/commont")
public class CommontController {
	
	@Resource
	private CommontService commontService;
	
	@RequestMapping(value="/addCommont.do")
	@ResponseBody
	public JSONObject addCommont(@RequestParam("com_user_id") String com_user_id, 
			@RequestParam("user_id") String user_id, 
			@RequestParam("active_user_id") int active_user_id, 
			@RequestParam("content") String content){
		JSONObject jsonObject = commontService.addAction(com_user_id, user_id, active_user_id, content);
		return jsonObject;
	}
	
	@RequestMapping(value="/addLaud.do")
	@ResponseBody
	public JSONObject laud(@RequestParam("com_user_id") String com_user_id, 
			@RequestParam("active_user_id") int active_user_id){
		JSONObject jsonObject = commontService.addLaud(com_user_id, active_user_id);
		return jsonObject;
	}
	
	@RequestMapping("/cancelLaud.do")
	@ResponseBody
	public JSONObject cancelLaud(@RequestParam("com_user_id") String com_user_id, 
			@RequestParam("active_user_id") int active_user_id){
		JSONObject jsonObject = commontService.cancelLaud(com_user_id, active_user_id);
		return jsonObject;
	}
	
	@RequestMapping(value="/laudList.do")
	@ResponseBody
	public JSONObject laudList(@RequestParam("active_user_id") int active_user_id, 
			HttpServletRequest request){
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = commontService.laudList(active_user_id, hostPath);
		return jsonObject;
	}
	
	@RequestMapping(value="/addcToc.do")
	@ResponseBody
	public JSONObject addcToc(@RequestParam("child_user_id") String child_user_id, 
			@RequestParam("com_id") int com_id, 
			@RequestParam("parent_user_id") String parent_user_id, 
			@RequestParam("child_content") String content){
		JSONObject jsonObject = commontService.addcToc(child_user_id, com_id, parent_user_id, content);
		return jsonObject;
	}
	
	@RequestMapping(value="/detailActive.do")
	@ResponseBody
	public JSONObject detailActive(@RequestParam("user_id") String user_id, 
			@RequestParam("com_user_id") String com_user_id, 
			@RequestParam("active_user_id") int active_user_id, 
			HttpServletRequest request){
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		String hostPath02 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_active/";
		JSONObject jsonObject = commontService.cToc(user_id, com_user_id, active_user_id, hostPath01, hostPath02);		
		return jsonObject;
	}
	
	@RequestMapping("/childLaud.do")
	@ResponseBody
	public JSONObject childLaud(@RequestParam("child_user_id") String child_user_id, 
			@RequestParam("com_id") int com_id, 
			@RequestParam("parent_user_id") String parent_user_id){
		JSONObject jsonObject = commontService.childComLaud(child_user_id, com_id, parent_user_id);
		return jsonObject;
	}
	
	
}
