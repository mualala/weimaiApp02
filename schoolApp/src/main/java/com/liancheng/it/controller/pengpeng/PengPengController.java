package com.liancheng.it.controller.pengpeng;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.minidev.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liancheng.it.service.pengpeng.PPService;

/**
 * 添加的碰碰功能
 */
@Controller
@RequestMapping("/pengpeng")
public class PengPengController {
	
	@Resource
	private PPService pPService;
	
	/**
	 * 碰碰添加直接子回答
	 * @param user_id 登录用户的云信id
	 */
	@RequestMapping("/addAnswer.do")
	@ResponseBody
	public JSONObject addAnswer(@RequestParam("user_id") String user_id, 
			@RequestParam("ques_id") int ques_id, 
			@RequestParam(value="content",required=false) String content, 
			@RequestParam("name_type") String name_type){
		System.out.println("进了碰碰添加直接子回答的请求。。。");
		JSONObject jsonObject = pPService.addAswer(user_id, ques_id, content, name_type);
		
		return jsonObject;
	}
	
	/**
	 * 碰碰首页
	 * @param user_id 登录用户的云信id
	 */
	@RequestMapping("/defMain.do")
	@ResponseBody
	public JSONObject defMain(@RequestParam("user_id") String user_id, 
			@RequestParam("ques_id") int ques_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			@RequestParam("name_type") String name_type, 
			@RequestParam("gender") String gender, 
			HttpServletRequest request){
		System.out.println("进了碰碰默认首页。。。");
		System.out.println("ques_id="+ques_id+",name_type="+name_type+",gender="+gender);
		System.out.println("".equals(name_type));
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		
		JSONObject jsonObject = pPService.showMain(user_id, ques_id, pageSize, pageNumber, name_type, gender, hostPath01);
		return jsonObject;
	}
	
	/**
	 * 碰碰的问题列表
	 */
	@RequestMapping("/questionList.do")
	@ResponseBody
	public JSONObject questionList(@RequestParam("name_type") String name_type, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber){
		System.out.println("进了碰碰的问题列表...");
		JSONObject jsonObject = pPService.questionList(name_type, pageSize, pageNumber);
		return jsonObject;
	}
	
	/**
	 * 直接子评论的点赞
	 */
	@RequestMapping("/childAnsLaud.do")
	@ResponseBody
	public JSONObject childAnsLaud(@RequestParam("user_id") String user_id, 
			@RequestParam("ans_id") int ans_id){
		System.out.println("进了碰碰直接子评论的点赞。。。");
		JSONObject jsonObject = pPService.addChildAnsLaud(user_id, ans_id);
		return jsonObject;
	}
	
	/**
	 * 某登录用户对问题回答后的展示列表
	 */
	@RequestMapping("/showCurrUserAnswer.do")
	@ResponseBody
	public JSONObject showCurrUserAnswer(@RequestParam("ques_id") int ques_id, 
			@RequestParam("user_id") String user_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		System.out.println("进了某登录用户对问题回答后的展示列表。。。");
		System.out.println("ques_id="+ques_id+",user_id="+user_id+",pageSize="+pageSize+",pageNumber="+pageNumber);
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = pPService.currUserAnswer(ques_id, user_id, pageSize, pageNumber, hostPath01);
		return jsonObject;
	}
	
	/**
	 * 添加问题的评论的评论
	 */
	@RequestMapping("/addChildAnswer.do")
	@ResponseBody
	public JSONObject addChildAnswer(@RequestParam("user_id") String user_id, 
			@RequestParam("ans_id") int ans_id, 
			@RequestParam("content") String content){
		System.out.println("进了添加问题的评论的评论。。。");
		JSONObject jsonObject = pPService.addChildAnswer(user_id, ans_id, content);
		return jsonObject;
	}
	
	/**
	 * 展示仅双方可见的评论列表
	 */
	@RequestMapping("/showOnlyChildAnswer.do")
	@ResponseBody
	public JSONObject showOnlyChildAnswer(@RequestParam("ques_id") int ques_id, 
			@RequestParam("own_user_id") String own_user_id, 
			@RequestParam("other_user_id") String other_user_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		System.out.println("进了展示仅双方可见的评论列表。。。");
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = pPService.onlyChildAnswer(ques_id, own_user_id, other_user_id, pageSize, pageNumber, hostPath01);
		return jsonObject;
	}
	
	/**
	 * 碰碰增加回答的浏览量
	 */
	@RequestMapping("/addSee.do")
	@ResponseBody
	public JSONObject addSee(@RequestParam("ans_id") int ans_id){
		System.out.println("进了碰碰增加回答的浏览量。。。");
		JSONObject jsonObject = pPService.addAnswerSee(ans_id);
		return jsonObject;
	}
	
	
	
}
