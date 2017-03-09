package com.liancheng.it.controller.friends;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.minidev.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liancheng.it.service.friends.FriendsService;


@Controller
@RequestMapping("/friends")
public class FriendsController {
	
	@Resource
	private FriendsService friendsService;
	
	@RequestMapping("/reqAddFriends.do")
	@ResponseBody
	public JSONObject reqAddFriends(@RequestParam("user_id") String user_id, 
			@RequestParam("f_user_id") String f_user_id, 
			@RequestParam("msg") String msg, 
			@RequestParam(value="type",required=false) String type){
		JSONObject jsonObject = friendsService.reqAddFriends(user_id, f_user_id, msg, type);
		return jsonObject;
	}
	
	@RequestMapping("/addAttention.do")
	@ResponseBody
	public JSONObject addAttention(@RequestParam("user_id") String user_id, 
			@RequestParam("f_user_id") String f_user_id){
		JSONObject jsonObject = friendsService.addAttention(user_id, f_user_id);
		return jsonObject;
	}
	
	@RequestMapping("/cancelAttention.do")
	@ResponseBody
	public JSONObject cancelAttention(@RequestParam("user_id") String user_id, 
			@RequestParam("f_user_id") String f_user_id){
		JSONObject jsonObject = friendsService.cancelAttention(user_id, f_user_id);
		return jsonObject;
	}
	
	@RequestMapping("/attentionUsers.do")
	@ResponseBody
	public JSONObject attentionUsers(@RequestParam("user_id") String user_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = friendsService.attentionUsers(user_id, pageSize, pageNumber, hostPath01);
		return jsonObject;
	}
	
	@RequestMapping("/fans.do")
	@ResponseBody
	public JSONObject fans(@RequestParam("user_id") String user_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = friendsService.showFans(user_id, pageSize, pageNumber, hostPath01);
		return jsonObject;
	}
	
	@RequestMapping("/showFriendList.do")
	@ResponseBody
	public JSONObject showFriendList(@RequestParam("user_id") String user_id, 
			HttpServletRequest request){
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = friendsService.friendsList(user_id, hostPath01);
		return jsonObject;
	}
	
	@RequestMapping("/cancelFans.do")
	@ResponseBody
	public JSONObject cancelFans(@RequestParam("user_id") String user_id, 
			@RequestParam("f_user_id") String f_user_id){
		JSONObject jsonObject = friendsService.cancelFans(user_id, f_user_id);
		return jsonObject;
	}
	
	@RequestMapping("/deleteFriend.do")
	@ResponseBody
	public JSONObject deleteFriend(@RequestParam("user_id") String user_id, 
			@RequestParam("f_user_id") String f_user_id){
		JSONObject jsonObject = friendsService.deleteFriend(user_id, f_user_id);
		return jsonObject;
	}
	
	
}
