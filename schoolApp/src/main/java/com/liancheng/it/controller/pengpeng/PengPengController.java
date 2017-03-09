package com.liancheng.it.controller.pengpeng;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.minidev.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liancheng.it.service.pengpeng.PPService;

@Controller
@RequestMapping("/pengpeng")
public class PengPengController {
	
	@Resource
	private PPService pPService;
	
	@RequestMapping("/addAnswer.do")
	@ResponseBody
	public JSONObject addAnswer(@RequestParam("user_id") String user_id, 
			@RequestParam("ques_id") int ques_id, 
			@RequestParam(value="content",required=false) String content, 
			@RequestParam("name_type") String name_type){
		JSONObject jsonObject = pPService.addAswer(user_id, ques_id, content, name_type);
		return jsonObject;
	}
	
	@RequestMapping("/defMain.do")
	@ResponseBody
	public JSONObject defMain(@RequestParam("user_id") String user_id, 
			@RequestParam("ques_id") int ques_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			@RequestParam("name_type") String name_type, 
			@RequestParam("gender") String gender, 
			HttpServletRequest request){
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = pPService.showMain(user_id, ques_id, pageSize, pageNumber, name_type, gender, hostPath01);
		return jsonObject;
	}
	
	@RequestMapping("/questionList.do")
	@ResponseBody
	public JSONObject questionList(@RequestParam("user_id") String user_id, 
			@RequestParam("name_type") String name_type, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber){
		JSONObject jsonObject = pPService.questionList(user_id, name_type, pageSize, pageNumber);
		return jsonObject;
	}
	
	@RequestMapping("/childAnsLaud.do")
	@ResponseBody
	public JSONObject childAnsLaud(@RequestParam("user_id") String user_id, 
			@RequestParam("ans_id") int ans_id){
		JSONObject jsonObject = pPService.addChildAnsLaud(user_id, ans_id);
		return jsonObject;
	}
	
	@RequestMapping("/showCurrUserAnswer.do")
	@ResponseBody
	public JSONObject showCurrUserAnswer(@RequestParam("ques_id") int ques_id, 
			@RequestParam("user_id") String user_id, 
			@RequestParam("name_type") String name_type, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = pPService.currUserAnswer(ques_id, user_id, name_type, pageSize, pageNumber, hostPath01);
		return jsonObject;
	}
	
	@RequestMapping("/addChildAnswer.do")
	@ResponseBody
	public JSONObject addChildAnswer(@RequestParam("user_id") String user_id, 
			@RequestParam("other_user_id") String parent_user_id, 
			@RequestParam("ans_id") int ans_id, 
			@RequestParam("content") String content){
		JSONObject jsonObject = pPService.addChildAnswer(user_id, parent_user_id, ans_id, content);
		return jsonObject;
	}
	
	@RequestMapping("/showOnlyChildAnswer.do")
	@ResponseBody
	public JSONObject showOnlyChildAnswer(@RequestParam("ques_id") int ques_id, 
			@RequestParam("own_user_id") String own_user_id, 
			@RequestParam("other_user_id") String other_user_id, 
			@RequestParam("name_type") String name_type, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = pPService.onlyChildAnswer(ques_id, own_user_id, other_user_id, name_type, pageSize, pageNumber, hostPath01);
		return jsonObject;
	}
	
	@RequestMapping("/addSee.do")
	@ResponseBody
	public JSONObject addSee(@RequestParam("ans_id") int ans_id){
		JSONObject jsonObject = pPService.addAnswerSee(ans_id);
		return jsonObject;
	}
	
	@RequestMapping("/addTwoChildAnswer.do")
	@ResponseBody
	public JSONObject addTwoChildAnswer(@RequestParam("user_id") String user_id, 
			@RequestParam("other_user_id") String parent_user_id, 
			@RequestParam("ans_id") int child_ans_id, 
			@RequestParam("content") String content){
		JSONObject jsonObject = pPService.addTwoChildAns(user_id, parent_user_id, child_ans_id, content);
		return jsonObject;
	}
	
	
	
}
