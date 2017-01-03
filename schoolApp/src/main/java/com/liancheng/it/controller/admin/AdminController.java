package com.liancheng.it.controller.admin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.liancheng.it.entity.active.Active;
import com.liancheng.it.service.active.ActiveUserService;
import com.liancheng.it.service.admin.AdminService;
import com.liancheng.it.service.pengpeng.PPService;
import com.liancheng.it.service.user.UserService;
import com.liancheng.it.util.GetCilentIp;

import net.minidev.json.JSONObject;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Resource
	private AdminService adminService;
	@Resource
	private ActiveUserService activeUserService;
	@Resource
	private UserService userService;
	@Resource
	private PPService pPService;
	
	/**
	 * ��¼����
	 */
	@RequestMapping(value="/login.do",produces="application/json;charset=UTF-8")
	public String login(@RequestParam("username") String adminame, 
			@RequestParam("password") String password, HttpServletRequest request){
		
		System.out.println("username="+adminame+",password="+password);
		if("".equals(adminame) || "".equals(password)){
			return "reLogin";
		}
		if(adminService.checkAmdin(adminame, password)){
			request.setAttribute("adminame", adminame);
			return "index";
		}
		return "reLogin";
	}
	
	/**
	 * ��˵˵�����
	 */
	@RequestMapping(value="/activeReport.do", produces="application/json;charset=UTF-8", 
			method=RequestMethod.POST)
	@ResponseBody
	public JSONObject activeReport(@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		
		System.out.println("���˺�̨��˵˵����ˣ���");
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_active/";
		JSONObject jsonObject = activeUserService.adminActive(pageSize, pageNumber, hostPath);
		
		return jsonObject;
	}
	
	/**
	 * ��̬���ͨ��
	 */
	@RequestMapping(value="/oneVerify.do")
	@ResponseBody
	public JSONObject activeVerify(@RequestParam("active_user_id") int active_user_id){
		System.out.println("���˶�̬������ˡ�����");
		JSONObject jsonObject = activeUserService.verifyActive(active_user_id);
		return jsonObject;
	}
	
	/**
	 * ��̬��˲�ͨ��
	 */
	@RequestMapping(value="/oneNoVerify.do")
	@ResponseBody
	public JSONObject activeNoVerify(@RequestParam("active_user_id") int active_user_id){
		System.out.println("���˶�̬������� ��ͨ��������");
		JSONObject jsonObject = activeUserService.noVerifyActive(active_user_id);
		return jsonObject;
	}
	
	/**
	 * ��̨�Է�˵˵��Ȩ����֤�б�
	 */
	@RequestMapping(value="/verifies.do")
	@ResponseBody
	public JSONObject authVerifies(@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber){
		System.out.println("��̨�Է�˵˵��Ȩ����֤�б�����");
		JSONObject jsonObject = activeUserService.authActive(pageSize, pageNumber);
		return jsonObject;
	}
	
	/**
	 * ���ķ���̬��Ȩ��
	 * @param state �Ƿ�������̬��֤,0��1
	 * @param class_active ��̬�ķ���
	 * @param verify ��ѧ�����Ǳ�ҵ֤��֤
	 */
	@RequestMapping(value="modifyVerify.do")
	@ResponseBody
	public JSONObject modifyVerify(@RequestParam("state") String state, 
			@RequestParam("class_active") String class_active, 
			@RequestParam("verify") String verify){
		System.out.println("�����޸ķ�˵˵��Ȩ�޸��ġ�����");
		System.out.println("state="+state+",verify="+verify);
		JSONObject jsonObject = activeUserService.modifyVerify(state, class_active, verify);
		return jsonObject;
	}
	
	/**
	 * ��̨�û���֤��Ϣ�ı�������
	 */
	@RequestMapping(value="/userVerifyReport.do")
	@ResponseBody
	public JSONObject verifyInfo(@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		System.out.println("���������Ϣ��֤��������󡣡�");
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_verify/";
		JSONObject jsonObject = userService.verifyReport(pageSize, pageNumber, hostPath);
		return jsonObject;
	}
	
	/**
	 * ��̨������֤ѧ��֤���ҵ֤
	 */
	@RequestMapping(value="/oneUserVerify.do")
	@ResponseBody
	public JSONObject adminUserVerify(@RequestParam("schoolId") int schoolId, 
			@RequestParam("verify") String verify, 
			@RequestParam("state") String state){
		System.out.println("���˺�̨������֤ѧ��֤���ҵ֤����");
		JSONObject jsonObject = userService.oneUserVerify(schoolId, verify, state);
		return jsonObject;
	}
	
	/**
	 * ��̨���ⱨ��
	 */
	@RequestMapping(value="/questionsManager.do")
	@ResponseBody
	public JSONObject questionsManager(@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber){
		System.out.println("���˺�̨���ⱨ����");
		JSONObject jsonObject = pPService.questionsReport(pageSize, pageNumber);
		return jsonObject;
	}
	
	/**
	 * ��̨�������
	 */
	@RequestMapping(value="/addQuestion.do")
	@ResponseBody
	public JSONObject addQuestion(@RequestParam("question") String question){
		JSONObject jsonObject = pPService.addQuestion(question);
		return jsonObject;
	}
	
	/**
	 * ��̨ϵͳ�Է���̬���������
	 */
	@RequestMapping("/batchActiveVerify.do")
	@ResponseBody
	public JSONObject batchActiveVerify(@RequestParam("param") String ids){
		System.out.println("���˶�̬������������󡣡���");
		System.out.println(ids);
		JSONObject jsonObject = activeUserService.batchDoVerify(ids);
		return jsonObject;
	}
	
	/**
	 * ��̨����ϵͳ����Դ�ļ������ص���ת����
	 */
	@RequestMapping("/{fileName}/toDownDocum.do")
	public ModelAndView toDownDocum(@PathVariable("fileName") String documName, 
			HttpServletRequest request, 
			HttpServletResponse response){
		System.out.println("������ת����");
		
//		response.setContentType("text/html;charset=utf-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		//�����ļ��ı���·��
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
			System.out.println("�����ļ�ʧ��!");
			e.printStackTrace();
		} finally {
			if(bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					System.out.println("bis�ر�ʧ��!");
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					System.out.println("bos�ر�ʧ��!");
				}
			}
		}
		return null;
	}
	
	
}
