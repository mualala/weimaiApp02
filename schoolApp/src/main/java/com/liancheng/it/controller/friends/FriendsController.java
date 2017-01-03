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
	
	/**
	 * 确认成为好友
	 * @param f_user_id要添加好友的uuid
	 */
	@RequestMapping(value="/addFriends.do")
	@ResponseBody
	public JSONObject addFriends(@RequestParam("user_id") String user_id, 
			@RequestParam("f_user_id") String f_user_id){
		System.out.println("进了确认添加好友。。。。");
		JSONObject jsonObject = friendsService.confirmAddFriends(user_id, f_user_id);
		return jsonObject;
	}
	
	/**
	 * 展示朋友的朋友
	 */
	@RequestMapping(value="/fTof.do")
	@ResponseBody
	public JSONObject showfTof(@RequestParam("user_id") String user_id){
		
		System.out.println("进了展示朋友的朋友的请求。。");
		JSONObject jsonObject = friendsService.showfTof(user_id);
		return jsonObject;
	}
	
	/**
	 * 用户添加关注用户
	 */
	@RequestMapping("/addAttention.do")
	@ResponseBody
	public JSONObject addAttention(@RequestParam("user_id") String user_id, 
			@RequestParam("f_user_id") String f_user_id){
		System.out.println("进了用户添加关注用户。。。");
		JSONObject jsonObject = friendsService.addAttention(user_id, f_user_id);
		return jsonObject;
	}
	
	/**
	 * 用户取消关注
	 */
	@RequestMapping("/cancelAttention.do")
	@ResponseBody
	public JSONObject cancelAttention(@RequestParam("user_id") String user_id, 
			@RequestParam("f_user_id") String f_user_id){
		System.out.println("进了用户取消关注。。。");
		JSONObject jsonObject = friendsService.cancelAttention(user_id, f_user_id);
		return jsonObject;
	}
	
	/**
	 * 用户的关注用户列表
	 */
	@RequestMapping("/attentionUsers.do")
	@ResponseBody
	public JSONObject attentionUsers(@RequestParam("user_id") String user_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		System.out.println("进了"+user_id+"用户关注的用户列表。。。");
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = friendsService.attentionUsers(user_id, pageSize, pageNumber, hostPath01);
		return jsonObject;
	}
	
	/**
	 * fans的用户列表
	 */
	@RequestMapping("/fans.do")
	@ResponseBody
	public JSONObject fans(@RequestParam("user_id") String user_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		System.out.println("进了"+user_id+"的fans的用户列表。。。");
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = friendsService.showFans(user_id, pageSize, pageNumber, hostPath01);
		return jsonObject;
	}
	
	/**
	 * 用户的朋友列表
	 */
	@RequestMapping("/showFriendList.do")
	@ResponseBody
	public JSONObject showFriendList(@RequestParam("user_id") String user_id, 
			HttpServletRequest request){
		
		System.out.println("进了"+user_id+"的朋友列表。。。");
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = friendsService.friendsList(user_id, hostPath01);
		return jsonObject;
	}
	
	/**
	 * 取消用户的fans用户
	 */
	@RequestMapping("/cancelFans.do")
	@ResponseBody
	public JSONObject cancelFans(@RequestParam("user_id") String user_id, 
			@RequestParam("f_user_id") String f_user_id){
		System.out.println("user_id="+user_id+",f_user_id="+f_user_id);
		System.out.println("进了用户取消fans。。。");
		JSONObject jsonObject = friendsService.cancelFans(user_id, f_user_id);
		return jsonObject;
	}
	
	
	
}
