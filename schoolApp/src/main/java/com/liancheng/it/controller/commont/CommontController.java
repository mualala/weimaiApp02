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
	
	/**
	 * 用户添加评论
	 * @param action_user_id 评论用户的uuid
	 * @param active_user_id 动态的的id
	 * @param content 评论的内容
	 */
	@RequestMapping(value="/addCommont.do")
	@ResponseBody
	public JSONObject addAction(@RequestParam("com_user_id") String com_user_id, 
			@RequestParam("active_user_id") int active_user_id, 
			@RequestParam("content") String content){
		System.out.println("进了添加用户评论！！！");
		
		JSONObject jsonObject = commontService.addAction(com_user_id, active_user_id, content);
		
		return jsonObject;
	}
	
	/**
	 * 点赞
	 */
	@RequestMapping(value="/addLaud.do")
	@ResponseBody
	public JSONObject laud(@RequestParam("com_user_id") String com_user_id, 
			@RequestParam("active_user_id") int active_user_id){
		System.out.println("进了用户点赞请求！！");
		JSONObject jsonObject = commontService.addLaud(com_user_id, active_user_id);
		
		return jsonObject;
	}
	
	/**
	 * 某条说说的点赞用户列表
	 */
	@RequestMapping(value="/laudList.do")
	@ResponseBody
	public JSONObject laudList(@RequestParam("active_user_id") int active_user_id, 
			HttpServletRequest request){
		
		System.out.println("进入点赞用户列表展示！！");
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = commontService.laudList(active_user_id, hostPath);
		return jsonObject;
	}
	
	/**
	 * 添加子评论
	 * @param children_id 子评论用户的uuid
	 * @param child_content 子评论的内容
	 * @param com_user_id 上级父评论的uuid
	 */
	@RequestMapping(value="/addcToc.do")
	@ResponseBody
	public JSONObject addcToc(@RequestParam("child_user_id") String child_user_id, 
			@RequestParam("com_id") int com_id, 
			@RequestParam("parent_user_id") String parent_user_id, 
			@RequestParam("content") String content){
		System.out.println("进了添加子评论！！！！");
		JSONObject jsonObject = commontService.addcToc(child_user_id, com_id, parent_user_id, content);
		
		return jsonObject;
	}
	
	/**
	 * 动态的详情页
	 */
	@RequestMapping(value="/detailActive.do")
	@ResponseBody
	public JSONObject detailActive(@RequestParam("com_user_id") String com_user_id, 
			@RequestParam("active_user_id") int active_user_id, 
			HttpServletRequest request){
		System.out.println("进了详情页。。");
		System.out.println("com_user_id="+com_user_id+",active_user_id="+active_user_id);
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		String hostPath02 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_active/";
		JSONObject jsonObject = commontService.cToc(com_user_id, active_user_id, hostPath01, hostPath02);		
		
		return jsonObject;
	}
	
	@RequestMapping("/childLaud.do")
	@ResponseBody
	public JSONObject childLaud(@RequestParam("child_user_id") String child_user_id, 
			@RequestParam("com_id") int com_id, 
			@RequestParam("parent_user_id") String parent_user_id){
		System.out.println("进了子评论点赞。。。。");
		System.out.println(child_user_id+com_id+parent_user_id);
		JSONObject jsonObject = commontService.childComLaud(child_user_id, com_id, parent_user_id);
		
		return jsonObject;
	}
	
	
}
