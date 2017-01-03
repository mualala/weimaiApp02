package com.liancheng.it.controller.active;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.minidev.json.JSONObject;
import net.sf.json.JSON;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.liancheng.it.dao.active.ActiveUserDao;
import com.liancheng.it.entity.active.Active;
import com.liancheng.it.service.active.ActiveUserService;
import com.liancheng.it.util.UUIDUtil;

/**
 * �û���̬
 */
@Controller
@RequestMapping("/active")
public class ActiveUserController {
	
	@Resource
	private ActiveUserService activeUserService;
	@Resource(name="activeUserDao")
	private ActiveUserDao activeUserDao;//������Դ�����Ǽ�¼����
	
	/**
	 * �û�����̬
	 */
	@RequestMapping(value="/addActive.do", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addActive(
			@RequestParam(value="active_pic",required=false) MultipartFile[] pics, 
			@RequestParam(value="docum",required=false) MultipartFile[] docum, 
			HttpServletRequest request){
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String user_id = multipartRequest.getParameter("user_id");
		String type_a = multipartRequest.getParameter("type_a");
		String type_b = multipartRequest.getParameter("type_b");
		String saysay = multipartRequest.getParameter("saysay");
//		int pageSize = Integer.valueOf(multipartRequest.getParameter("pageSize"));
		System.out.println(pics+"\n"+docum);
		System.out.println("���˷���̬�����󡣡���");
		System.out.println("user_id="+user_id+",type_a="+type_a+",type_b="+",saysay="+saysay);
		//�����ļ��ı���·��
		String localBasePath = request.getSession().getServletContext().getRealPath("/")+"images\\local_active\\";
		
		JSONObject jsonObject = activeUserService.addActive(pics, docum, user_id, type_a, type_b, saysay, localBasePath);
		System.out.println("���ص�json="+jsonObject);
		return jsonObject;
		//���ظ��µĶ�̬����
		//��Ŀ�����µ�ͼƬ·��
//		String path = request.getContextPath();
//		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
//		String hostPath02 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_active/";
//		if((Boolean) jsonObject.get("status")){
//			return activeUserService.showClassActive(type_a, pageSize, 1, hostPath01, hostPath02, user_id);
//		}else {
//			
//		}
	}
	
	/**
	 * չʾ���˶�̬
	 * @param user_id �û���uuid
	 * @param pageSize ÿҳ��ʾ�ļ�¼��
	 * @param pageNumber �ڼ�ҳ
	 */
	@RequestMapping(value="/showOwnActive.do")
	@ResponseBody
	public JSONObject ownActive(@RequestParam("user_id") String user_id, 
			@RequestParam("v_user_id") String v_user_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		String hostPath02 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_active/";
		
		System.out.println("���˸��˶�̬չʾ����"+hostPath02);
		JSONObject jsonObject = activeUserService.showOwnActive(user_id, v_user_id, pageSize, pageNumber, hostPath01, hostPath02);
		
		return jsonObject;
	}
	
	
	/**
	 * ��ҳ����Ķ�̬չʾ,����ҳ��̬��չʾ
	 */
	@RequestMapping(value="/classActive.do")
	@ResponseBody
	public JSONObject friendsActive(@RequestParam("type_a") String type_a, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			@RequestParam("com_user_id") String com_user_id, 
			HttpServletRequest request){
		System.out.println("����չʾ��������󡣡���");
		System.out.println("type_a="+type_a+",pageSize="+pageSize+",pageNumber="+pageNumber);
		
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		String hostPath02 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_active/";
		System.out.println("������ҳ����Ķ�̬չʾ����"+hostPath02);
		JSONObject jsonObject = activeUserService.showClassActive(type_a, pageSize, 
				pageNumber, hostPath01, hostPath02, com_user_id);
//		HttpSession session = request.getSession();
//		System.out.println("��̬�����ȡsession="+session.getAttribute("username"));
		System.out.println("active��A������ֵ="+request.getServletContext().getAttribute("username"));
		return jsonObject;
	}
	
	
	/**
	 * չʾ����Ȧ��̬
	 */
	@RequestMapping(value="/friendsActive.do")
	@ResponseBody
	public JSONObject showCycFriendsActive(@RequestParam("user_id") String user_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			@RequestParam("com_user_id") String com_user_id, 
			HttpServletRequest request){
		
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		String hostPath02 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_active/";
		System.out.println("��������Ȧ��̬��չʾ����");
		JSONObject jsonObject = activeUserService.showFriendsActvie(user_id, pageSize, 
				pageNumber, hostPath01, hostPath02, com_user_id);
		return jsonObject;
	}
	
	/**
	 * ˵˵��Ӳ鿴����
	 */
	@RequestMapping(value="/seeAction.do")
	@ResponseBody
	public JSONObject seeActive(@RequestParam("active_user_id") int active_user_id){
		
		System.out.println("����׷��˵˵�鿴����������");
		JSONObject jsonObject = activeUserService.addSeeActive(active_user_id);
		
		return jsonObject;
	}
	
	/**
	 * ��̬��ȫ�ļ�������
	 */
	@RequestMapping(value="/fullText.do")
	@ResponseBody
	public JSONObject fullText(@RequestParam("text") String text, 
			HttpServletRequest request){
		
		System.out.println("��̬��ȫ�ļ������󣡣�");
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		String hostPath02 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_active/";
		JSONObject jsonObject = activeUserService.keyActive(text, hostPath01, hostPath02);
		
		return jsonObject;
	}
	
	/**
	 * ���ض�̬�е���Դ�ļ�������ʹ��restful���
	 */
	@RequestMapping("/{fileName}/download")
	public ModelAndView download(@PathVariable("fileName") String fileName, 
			@RequestParam(value="active_user_id",required=false) int active_user_id, 
			HttpServletRequest request, 
			HttpServletResponse response){
//		response.setContentType("text/html;charset=utf-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		//�����ļ��ı���·��
		String localBasePath = request.getSession().getServletContext().getRealPath("/")+"images\\local_active\\";
		
		String downloadPath = localBasePath+"__"+fileName;
		System.out.println(downloadPath);
		
		try {
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-Disposition", "attachment; filename=\""+new String(fileName.getBytes("UTF-8"))+"\"");
			bis = new BufferedInputStream(new FileInputStream(downloadPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while(-1 != (bytesRead = bis.read(buff,0,buff.length))){
				bos.write(buff, 0, bytesRead);
			}
			Active active = activeUserDao.queryDownCount(active_user_id);
			//�Ȳ�ѯ���ش���
			String docCount = active.getDoc_down_count();
			String[] docCounts = docCount.split(",");
			System.out.println(docCounts.length);
			//��ȡ�ļ���λ��
			String docStr = active.getDocum();
			String[] docNames = docStr.split(",");
			for(int i=0;i<docNames.length;i++){
				if(docNames[i].equals("__"+fileName)){//ƥ��ɹ�,���ļ���λ��,�ı���Ӧ��ֵ
					System.out.println("�ļ���λ��="+i);
					docCounts[i] = String.valueOf(Integer.valueOf(docCounts[i])+1);//��Ӧ�����ش�����һ
				}
			}
			//����ƴ��doc_down_count�ֶ�
			String docDownCount = "";
			for(String doc:docCounts){
				docDownCount = docDownCount+doc+",";
			}
			System.out.println(docDownCount);
			//�������ݿ���е����ش���
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("active_user_id", active_user_id);
			params.put("doc_down_count", docDownCount);
			activeUserDao.addDownCount(params);
			
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
