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
 * ��ӵ���������
 */
@Controller
@RequestMapping("/pengpeng")
public class PengPengController {
	
	@Resource
	private PPService pPService;
	
	/**
	 * �������ֱ���ӻش�
	 * @param user_id ��¼�û�������id
	 */
	@RequestMapping("/addAnswer.do")
	@ResponseBody
	public JSONObject addAnswer(@RequestParam("user_id") String user_id, 
			@RequestParam("ques_id") int ques_id, 
			@RequestParam(value="content",required=false) String content, 
			@RequestParam("name_type") String name_type){
		System.out.println("�����������ֱ���ӻش�����󡣡���");
		JSONObject jsonObject = pPService.addAswer(user_id, ques_id, content, name_type);
		
		return jsonObject;
	}
	
	/**
	 * ������ҳ
	 * @param user_id ��¼�û�������id
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
		System.out.println("��������Ĭ����ҳ������");
		System.out.println("ques_id="+ques_id+",name_type="+name_type+",gender="+gender);
		System.out.println("".equals(name_type));
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		
		JSONObject jsonObject = pPService.showMain(user_id, ques_id, pageSize, pageNumber, name_type, gender, hostPath01);
		return jsonObject;
	}
	
	/**
	 * �����������б�
	 */
	@RequestMapping("/questionList.do")
	@ResponseBody
	public JSONObject questionList(@RequestParam("name_type") String name_type, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber){
		System.out.println("���������������б�...");
		JSONObject jsonObject = pPService.questionList(name_type, pageSize, pageNumber);
		return jsonObject;
	}
	
	/**
	 * ֱ�������۵ĵ���
	 */
	@RequestMapping("/childAnsLaud.do")
	@ResponseBody
	public JSONObject childAnsLaud(@RequestParam("user_id") String user_id, 
			@RequestParam("ans_id") int ans_id){
		System.out.println("��������ֱ�������۵ĵ��ޡ�����");
		JSONObject jsonObject = pPService.addChildAnsLaud(user_id, ans_id);
		return jsonObject;
	}
	
	/**
	 * ĳ��¼�û�������ش���չʾ�б�
	 */
	@RequestMapping("/showCurrUserAnswer.do")
	@ResponseBody
	public JSONObject showCurrUserAnswer(@RequestParam("ques_id") int ques_id, 
			@RequestParam("user_id") String user_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		System.out.println("����ĳ��¼�û�������ش���չʾ�б�����");
		System.out.println("ques_id="+ques_id+",user_id="+user_id+",pageSize="+pageSize+",pageNumber="+pageNumber);
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = pPService.currUserAnswer(ques_id, user_id, pageSize, pageNumber, hostPath01);
		return jsonObject;
	}
	
	/**
	 * �����������۵�����
	 */
	@RequestMapping("/addChildAnswer.do")
	@ResponseBody
	public JSONObject addChildAnswer(@RequestParam("user_id") String user_id, 
			@RequestParam("ans_id") int ans_id, 
			@RequestParam("content") String content){
		System.out.println("���������������۵����ۡ�����");
		JSONObject jsonObject = pPService.addChildAnswer(user_id, ans_id, content);
		return jsonObject;
	}
	
	/**
	 * չʾ��˫���ɼ��������б�
	 */
	@RequestMapping("/showOnlyChildAnswer.do")
	@ResponseBody
	public JSONObject showOnlyChildAnswer(@RequestParam("ques_id") int ques_id, 
			@RequestParam("own_user_id") String own_user_id, 
			@RequestParam("other_user_id") String other_user_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		System.out.println("����չʾ��˫���ɼ��������б�����");
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = pPService.onlyChildAnswer(ques_id, own_user_id, other_user_id, pageSize, pageNumber, hostPath01);
		return jsonObject;
	}
	
	/**
	 * �������ӻش�������
	 */
	@RequestMapping("/addSee.do")
	@ResponseBody
	public JSONObject addSee(@RequestParam("ans_id") int ans_id){
		System.out.println("�����������ӻش�������������");
		JSONObject jsonObject = pPService.addAnswerSee(ans_id);
		return jsonObject;
	}
	
	
	
}
