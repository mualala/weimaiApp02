package com.liancheng.it.controller.user;


import java.io.InputStream;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.liancheng.it.service.user.UserService;

import net.minidev.json.JSONObject;
/**
 * ʵ�ֵ�¼���������ݿ���֤�ʺ�����
 *
 */

@Controller//��controllerע��
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	/**
	 * ��¼
	 */
	@RequestMapping(value="/login.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public JSONObject doLogin(@RequestParam("phoneNum") String phoneNum, 
			@RequestParam("password") String password, 
			ModelMap model, 
			HttpServletRequest request){
//		String phoneNum = request.getParameter("phoneNum");
//		String password = request.getParameter("password");
//		String token =request.getParameter("schoolId");
		String username = (String) request.getServletContext().getAttribute("username");
		System.out.println("2---��¼ʱA��ֵ="+username);
		request.getServletContext().setAttribute("username", phoneNum);
		System.out.println("��¼��������������");
		JSONObject result = new JSONObject();
		result = userService.checkLogin(phoneNum, password, request);//����service����ʵ��ҵ���߼�
		return result;
	}
	
	/**
	 * ��֤������
	 */
	@RequestMapping(value="/checkPhoneNum.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public JSONObject checkCode(HttpServletRequest request){
		JSONObject result = new JSONObject();
		System.out.println("����ĵ绰���룺"+request.getParameter("phoneNum"));
		if(request.getParameter("phoneNum") == null || "".equals(request.getParameter("phoneNum"))){
			System.out.println("ʧ��!!!!�绰����Ϊ��");
			System.out.println("aaaa");
			result.put("msg", "�ֻ���λ�գ��������ֻ�����");
			return result;
		}else {
			result = userService.checkUserRegist(request.getParameter("phoneNum"));
			System.out.println("���������֤������,�绰����:"+request.getParameter("phoneNum"));
			System.out.println("bbbb");
			return result;
		}
	}
	
	/**
	 * ע��
	 */
	@RequestMapping(value="/regist.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public JSONObject regist(HttpServletRequest request){
		String phoneNum = request.getParameter("phoneNum");
		String code = request.getParameter("code");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		
		System.out.println("�����û�ע�ᣬphone��"+phoneNum);
		System.out.println("ע���û������룺" + password);
		System.out.println("�������֤�룺" + code);
		JSONObject result = userService.addUser(phoneNum, password, code,username);
		return result;
	}
	
	/**
	 * �ϴ��û�ͷ��
	 */
	@RequestMapping(value="/profile.do", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject profile(@RequestParam("profile") MultipartFile pic, 
			@RequestParam("user_id") String user_id, 
			HttpServletRequest request, HttpServletResponse response){
		
		//��HttpServletRequestת����MultipartHttpServletRequest
		//(MultipartHttpServletRequest)request
		
		System.out.println("�����û�ͷ���ϴ������û�id=" + user_id);
		
		//�������ϵĵ�ַ��url
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		System.out.println("basePath="+basePath);
		
		//��ȡ���汾���û�ͷ���·��
		String localBasePath = request.getSession().getServletContext().getRealPath("/")+"images\\local_user_profile\\";//��ȡ���ش���·��
		
		String picName = String.valueOf(System.currentTimeMillis());//����ͼƬΨһ������
		String suffix = pic.getOriginalFilename().substring(pic.getOriginalFilename().lastIndexOf("."));//��ȡ�ļ��ĺ�׺��
		
		System.out.println("���ر����·��="+localBasePath + picName + suffix);
		userService.saveProfile(pic, user_id, localBasePath + picName + suffix,picName,localBasePath,suffix);
		
		JSONObject resultJSON = new JSONObject();
		resultJSON.put("msg", "ͷ���ϴ��ɹ�!");
		resultJSON.put("data", basePath+picName+suffix);
		System.out.println("�ϴ�ͷ�񷵻ص����ݣ�"+resultJSON.toJSONString());
		return resultJSON;
	}
	
	/**
	 * �û�������Ϣչʾ
	 */
	@RequestMapping("/showUserInfo.do")
	@ResponseBody
	public JSONObject showUserInfo(@RequestParam("user_id") String user_id, 
			HttpServletRequest request){
		System.out.println("�����û���Ϣչʾ.....");
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		String hostPath02 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_active/";
		
		JSONObject result = userService.userInfo(user_id, hostPath01, hostPath02);
		return result;
	}
	
	
	/**
	 * �����û�������Ϣ
	 */
	@RequestMapping(value="/edit.do",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject eidtUser(@RequestParam("user_id") String user_id, 
			@RequestParam(value="username",required=false) String username, 
			@RequestParam(value="gender",required=false) String gender, 
			@RequestParam(value="birthday",required=false) String birthday, 
			@RequestParam(value="star",required=false) String star, 
			@RequestParam(value="e_state",required=false) String e_state, 
			@RequestParam(value="grade",required=false) String grade, 
			@RequestParam(value="profession",required=false) String profession, 
			@RequestParam(value="major",required=false) String major, 
			@RequestParam(value="school",required=false) String school, 
			@RequestParam(value="highschool",required=false) String highschool, 
			@RequestParam(value="province",required=false) String province, 
			@RequestParam(value="city",required=false) String city, 
			@RequestParam(value="county",required=false) String county, 
			@RequestParam(value="lable",required=false) String lable, 
			@RequestParam(value="skill",required=false) String skill){
		System.out.println("major="+major+",school="+school);
		System.out.println("�����û���Ϣ���£�");
		JSONObject jsonObject = userService.editUser(user_id, username, gender, birthday, star, e_state, grade, 
				profession, school, major, highschool, province, city, county, lable, skill);
		System.out.println("major="+major+",school="+school);
		return jsonObject;
	}
	
	/**
	 * �ϴ��û���֤��Ϣ
	 * @param type ��֤��Ϣ�����ͣ�ѧ��֤���Ǳ�ҵ֤
	 */
	@RequestMapping(value="/verify.do")
	@ResponseBody
	public JSONObject uploadVerify(@RequestParam("verify") MultipartFile verify, 
			HttpServletRequest request){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		
		String type = multipartRequest.getParameter("type");
		String user_id = multipartRequest.getParameter("user_id");
		
		//�����ļ��ı���·��
		String localBasePath = request.getSession().getServletContext().getRealPath("/")+"images\\local_verify\\";
		String picName = String.valueOf(System.currentTimeMillis());//����ͼƬΨһ������
		//��ȡ�ļ��ĺ�׺��
		String suffix = verify.getOriginalFilename().substring(verify.getOriginalFilename().lastIndexOf("."));
		
		JSONObject jsonObject = userService.userVerify(verify, type, user_id, localBasePath, picName, suffix);
		
		return jsonObject;
	}
	
	/**
	 * �˺�������û��б�
	 */
	@RequestMapping(value="/peoples.do",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject peoples(@RequestParam("user_id") String user_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		System.out.println("�����˺�������û��б�������");
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		
		JSONObject jsonObject = userService.randPeoples(user_id, pageSize, pageNumber, hostPath01);
		return jsonObject;
	}
	/**
	 * �˺�������Ȧ�û���ɸѡ����
	 * @param age01 ��ѯ�ĳ�ʼ����
	 * @param age02 ��ѯ�Ľ�������
	 */
	@RequestMapping(value="/filterPeoples.do")
	@ResponseBody
	public JSONObject filterPeoples(@RequestParam(value="user_id") String user_id, 
			@RequestParam(value="pageSize",required=false) int pageSize, 
			@RequestParam(value="pageNumber",required=false) int pageNumber, 
			@RequestParam(value="gender",required=false) String gender, 
			@RequestParam(value="grade",required=false) String grade, 
			@RequestParam(value="age01",required=false) int age01, 
			@RequestParam(value="age02",required=false) int age02, 
			@RequestParam(value="major",required=false) String major, 
			@RequestParam(value="type",required=false) String type, 
			HttpServletRequest request){
		System.out.println("�����˺��û���ɸѡ���󡣡���");
		System.out.println("user_id="+user_id+",pageNumber="+pageNumber+",pageSize="+pageSize+",gender="+gender+",grade="+grade+",age01="+age01+",age02="+age02+",major="+major+",type="+type);
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = userService.filterPeoples(user_id, pageSize, pageNumber, 
				gender, grade, age01, age02, major, type, hostPath01);
		return jsonObject;
	}
	
	/**
	 * �˺��û�������
	 */
	@RequestMapping("peoplesSearch.do")
	@ResponseBody
	public JSONObject peoplesSearch(@RequestParam("param") String param, 
			HttpServletRequest request){
		System.out.println("�����˺��û�������������");
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = userService.peoplesSearch(param, hostPath01);
		return jsonObject;
	}
	
	/**
	 * ����Ȧ�ķ����û�
	 */
	@RequestMapping("/maimaiCateg.do")
	@ResponseBody
	public JSONObject maimaiCateg(@RequestParam("user_id") String user_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			@RequestParam("type") String type, 
			HttpServletRequest request){
		System.out.println("��������Ȧ�û������б�����");
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		
		JSONObject jsonObject = userService.maimaiCateg(user_id, pageSize, pageNumber, type, hostPath01);
		return jsonObject;
	}
	
	/**
	 * ����Ȧ���û�����
	 */
	@RequestMapping("/maimaiSearch.do")
	@ResponseBody
	public JSONObject maimaiSearch(@RequestParam("param") String param, 
			HttpServletRequest request){
		System.out.println("��������Ȧ���û�����������");
		System.out.println("param="+param);
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		
		JSONObject jsonObject = userService.maimaiSearch(param, hostPath01);
		return jsonObject;
	}
	
	/**
	 * �ÿ͵��û��б�
	 */
	@RequestMapping("/showVisitorList.do")
	@ResponseBody
	public JSONObject showVisitorList(@RequestParam("user_id") String user_id, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		System.out.println("�ÿ͵��û��б�����");
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		JSONObject jsonObject = userService.visitorList(user_id, pageSize, pageNumber, hostPath01);
		return jsonObject;
	}
	
	/**
	 * ����ѧУapi���ݵĽӿ�
	 */
	@RequestMapping("/schoolAPI.do")
	@ResponseBody
	public net.sf.json.JSONObject schoolAPI(){
//		JSONObject jsonObject = new JSONObject();
		net.sf.json.JSONObject j = new net.sf.json.JSONObject();
		Properties prop = new Properties();
		InputStream in= this.getClass().getClassLoader().getResourceAsStream("/json/json.properties");
		try {
			prop.load(in);
		} catch (Exception e) {
			System.out.println("ѧУ��api���ݶ�ȡʧ��");
			j.accumulate("status", false);
			j.accumulate("msg", "ѧУ��api���ݶ�ȡʧ��");
			return j;
		}
		j.accumulate("data", prop.getProperty("university"));
		j.accumulate("status", true);
		j.accumulate("msg", "ѧУ��api���ݶ�ȡ�ɹ�");
		return j;
	}
	
}
