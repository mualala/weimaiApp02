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
	 * ȷ�ϳ�Ϊ����
	 * @param f_user_idҪ��Ӻ��ѵ�uuid
	 */
	@RequestMapping(value="/addFriends.do")
	@ResponseBody
	public JSONObject addFriends(@RequestParam("user_id") String user_id, 
			@RequestParam("f_user_id") String f_user_id){
		System.out.println("����ȷ����Ӻ��ѡ�������");
		JSONObject jsonObject = friendsService.confirmAddFriends(user_id, f_user_id);
		return jsonObject;
	}
	
	/**
	 * չʾ���ѵ�����
	 */
	@RequestMapping(value="/fTof.do")
	@ResponseBody
	public JSONObject showfTof(@RequestParam("user_id") String user_id){
		
		System.out.println("����չʾ���ѵ����ѵ����󡣡�");
		JSONObject jsonObject = friendsService.showfTof(user_id);
		return jsonObject;
	}
	
	/**
	 * �û���ӹ�ע�û�
	 */
	@RequestMapping("/addAttention.do")
	@ResponseBody
	public JSONObject addAttention(@RequestParam("user_id") String user_id, 
			@RequestParam("f_user_id") String f_user_id){
		System.out.println("�����û���ӹ�ע�û�������");
		JSONObject jsonObject = friendsService.addAttention(user_id, f_user_id);
		return jsonObject;
	}
	
	/**
	 * �û�ȡ����ע
	 */
	@RequestMapping("/cancelAttention.do")
	@ResponseBody
	public JSONObject cancelAttention(@RequestParam("user_id") String user_id, 
			@RequestParam("f_user_id") String f_user_id){
		System.out.println("�����û�ȡ����ע������");
		JSONObject jsonObject = friendsService.cancelAttention(user_id, f_user_id);
		return jsonObject;
	}
	
	/**
	 * �û��Ĺ�ע�û��б�
	 */
	@RequestMapping("/attentionUsers.do")
	@ResponseBody
	public JSONObject attentionUsers(@RequestParam("user_id") String user_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		System.out.println("����"+user_id+"�û���ע���û��б�����");
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = friendsService.attentionUsers(user_id, pageSize, pageNumber, hostPath01);
		return jsonObject;
	}
	
	/**
	 * fans���û��б�
	 */
	@RequestMapping("/fans.do")
	@ResponseBody
	public JSONObject fans(@RequestParam("user_id") String user_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		System.out.println("����"+user_id+"��fans���û��б�����");
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = friendsService.showFans(user_id, pageSize, pageNumber, hostPath01);
		return jsonObject;
	}
	
	/**
	 * �û��������б�
	 */
	@RequestMapping("/showFriendList.do")
	@ResponseBody
	public JSONObject showFriendList(@RequestParam("user_id") String user_id, 
			HttpServletRequest request){
		
		System.out.println("����"+user_id+"�������б�����");
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = friendsService.friendsList(user_id, hostPath01);
		return jsonObject;
	}
	
	/**
	 * ȡ���û���fans�û�
	 */
	@RequestMapping("/cancelFans.do")
	@ResponseBody
	public JSONObject cancelFans(@RequestParam("user_id") String user_id, 
			@RequestParam("f_user_id") String f_user_id){
		System.out.println("user_id="+user_id+",f_user_id="+f_user_id);
		System.out.println("�����û�ȡ��fans������");
		JSONObject jsonObject = friendsService.cancelFans(user_id, f_user_id);
		return jsonObject;
	}
	
	
	
}
