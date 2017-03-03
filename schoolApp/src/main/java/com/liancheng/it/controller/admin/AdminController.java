package com.liancheng.it.controller.admin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.liancheng.it.service.active.ActiveUserService;
import com.liancheng.it.service.admin.AdminService;
import com.liancheng.it.service.friends.FriendsService;
import com.liancheng.it.service.pengpeng.PPService;
import com.liancheng.it.service.user.UserService;

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
	@Resource
	private FriendsService friendsService;
	
	/**
	 * ��¼����
	 */
	/*
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
	*/
	/**
	 * ��¼����
	 */
	@RequestMapping(value="/login.do",produces="application/json;charset=UTF-8")
	@ResponseBody
	public JSONObject login(@RequestParam("username") String adminame, 
			@RequestParam("password") String password, HttpServletRequest request){
		JSONObject jsonObject = adminService.checkAmdin(adminame, password, request);
		return jsonObject;
	}
	
	
	/**
	 * ��˵˵�����
	 */
	@RequestMapping(value="/activeReport.do", produces="application/json;charset=UTF-8", 
			method=RequestMethod.POST)
	@ResponseBody
	public JSONObject activeReport(@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			@RequestParam(value="searchText",required=false) String searchText, 
			@RequestParam(value="startDate",required=false) String startDate, 
			@RequestParam(value="endDate",required=false) String endDate, 
			@RequestParam(value="sortName",required=false) String sortName, 
			@RequestParam(value="sortOrder",required=false) String sortOrder, 
			HttpServletRequest request){
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_active/";
		JSONObject jsonObject = activeUserService.adminActive(pageSize, pageNumber, searchText, startDate, endDate, sortName, sortOrder, hostPath);
		
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
			@RequestParam("pageNumber") int pageNumber, 
			@RequestParam(value="searchText",required=false) String searchText, 
			@RequestParam(value="sortName",required=false) String sortName,  
			@RequestParam(value="sortOrder",required=false) String sortOrder, 
			HttpServletRequest request){
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath02 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_active/theme/";
		System.out.println("��̨�Է�˵˵��Ȩ����֤�б�����");
		JSONObject jsonObject = activeUserService.authActive(pageSize, pageNumber, searchText, sortName, sortOrder, hostPath02);
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
			@RequestParam(value="searchText",required=false) String searchText, 
			@RequestParam(value="sortName",required=false) String sortName, 
			@RequestParam(value="sortOrder",required=false) String sortOrder, 
			@RequestParam(value="startDate",required=false) String startDate, 
			@RequestParam(value="endDate",required=false) String endDate, 
			@RequestParam(value="schoolID",required=false) String schoolID,
			@RequestParam(value="phoneNum",required=false) String phoneNum, 
			HttpServletRequest request){
		System.out.println("���������Ϣ��֤��������󡣡�");
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_verify/";
		JSONObject jsonObject = userService.verifyReport(pageSize, pageNumber, searchText, sortName, sortOrder, startDate, endDate, schoolID, phoneNum, hostPath);
		return jsonObject;
	}
	
	/**
	 * ��̨������֤ѧ��֤���ҵ֤
	 */
	@RequestMapping(value="/oneUserVerify.do")
	@ResponseBody
	public JSONObject adminUserVerify(@RequestParam("schoolId") int schoolId, 
			@RequestParam("verify") String verify, 
			@RequestParam("state") String state, 
			@RequestParam("otherState") String otherState){
		System.out.println("���˺�̨������֤ѧ��֤���ҵ֤����");
		JSONObject jsonObject = userService.oneUserVerify(schoolId, verify, state, otherState);
		return jsonObject;
	}
	
	/**
	 * ��̨���ⱨ��
	 */
	@RequestMapping(value="/questionsManager.do")
	@ResponseBody
	public JSONObject questionsManager(@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			@RequestParam(value="searchText",required=false) String searchText, 
			@RequestParam(value="startDate",required=false) String startDate, 
			@RequestParam(value="endDate",required=false) String endDate, 
			@RequestParam(value="sortName",required=false) String sortName, 
			@RequestParam(value="sortOrder",required=false) String sortOrder){
		System.out.println("���˺�̨���ⱨ����");
		JSONObject jsonObject = pPService.questionsReport(pageSize, pageNumber, searchText, startDate, endDate, sortName, sortOrder);
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
		String localBasePath = request.getSession().getServletContext().getRealPath("/")+"images/local_active/";
		
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
	
	/**
	 * bannerͼչʾ
	 
	@RequestMapping("/showBannerPicRep.do")
	@ResponseBody
	public JSONObject showBannerPicRep(@RequestParam("user_id") String user_id, 
			HttpServletRequest request){
		System.out.println("����bannerͼչʾ������");
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		String hostPath02 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_banner/";
		JSONObject jsonObject = adminService.bannerPicReport(user_id, hostPath01, hostPath02);
		jsonObject.put("fTof", friendsService.showfTof(user_id, hostPath01));//������ѵ�����
		jsonObject.put("themeCateg", activeUserService.allThemeCateg());//������е��������
		return jsonObject;
	}
	*/
	/**
	 * ��̨�ϴ�bannerͼƬ
	 */
	@RequestMapping("/upOneBannerPic.do")
	@ResponseBody
	public JSONObject upOneBannerPic(
			@RequestParam("bannerPic") MultipartFile bannerPic, 
			HttpServletRequest request){
		System.out.println("���˺�̨�ϴ�bannerͼƬ����");
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String picId = multipartRequest.getParameter("picId");
		String thirdURL = multipartRequest.getParameter("thirdURL");
		//�����ļ��ı���·��
		String localBasePath = request.getSession().getServletContext().getRealPath("/")+"images/local_banner/";
		JSONObject jsonObject = adminService.upBannerPic(bannerPic, picId, thirdURL, localBasePath);
		return jsonObject;
	}
	
	/**
	 * ɾ��bannerͼ
	 */
	@RequestMapping("/deleteBannerPic.do")
	@ResponseBody
	public JSONObject deleteBannerPic(@RequestParam("picId") int picId, 
			HttpServletRequest request){
		System.out.println("���˺�̨ɾ��bannerͼƬ������");
		//�����ļ��ı���·��
		String localBasePath = request.getSession().getServletContext().getRealPath("/")+"images/local_banner/";
		JSONObject jsonObject = adminService.modifyBannerPic(picId, localBasePath);
		return jsonObject;
	}
	
	/**
	 * ��̨���û�����
	 */
	@RequestMapping("/userReport.do")
	@ResponseBody
	public JSONObject userReport(@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			@RequestParam(value="searchText",required=false) String searchText, 
			@RequestParam(value="verifyState",required=false) String verifyState, 
			@RequestParam(value="blockState",required=false) String blockState, 
			@RequestParam(value="school",required=false) String school, 
			@RequestParam(value="gender",required=false) String gender, 
			@RequestParam(value="profession",required=false) String profession, 
			@RequestParam(value="startDate",required=false) String startDate, 
			@RequestParam(value="endDate",required=false) String endDate, 
			@RequestParam(value="sortName",required=false) String sortName,  
			@RequestParam(value="sortOrder",required=false) String sortOrder, 
			@RequestParam(value="schoolID",required=false) String schoolID, 
			HttpServletRequest request){
		
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath01 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_user_profile/";
		String hostPath02 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_verify/";
		JSONObject jsonObject = userService.userReport(pageSize, pageNumber, searchText, verifyState, 
				blockState, school, gender, profession, startDate, endDate, sortName, sortOrder, schoolID, hostPath01, hostPath02);
		return jsonObject;
	}
	
	@RequestMapping("/updateDunbaVal.do")
	@ResponseBody
	public JSONObject updateDunbaVal(@RequestParam("dunba") String dunbaVal){
		JSONObject jsonObject = adminService.editDunbarVal(dunbaVal);
		return jsonObject;
	}
	
	@RequestMapping("/dunbaVal.do")
	@ResponseBody
	public JSONObject dunbaVal(){
		JSONObject jsonObject = adminService.gotDunbarContent();
		return jsonObject;
	}
	
	/**
	 * ���º�̨�˻�������
	 */
	@RequestMapping("/editAdminUser.do")
	@ResponseBody
	public JSONObject editAdminUser(@RequestParam("newAdmin") String admin_name, 
			@RequestParam("newPwd") String password){
		System.out.println(admin_name+","+password);
		JSONObject jsonObject = adminService.editAdmin(admin_name, password);
		return jsonObject;
	}
	
	/**
	 * �˳���̨��¼
	 */
	@RequestMapping("/exitAdmin.do")
	@ResponseBody
	public JSONObject exitAdmin(HttpServletRequest request){
		JSONObject jsonObject = new JSONObject();
		//��Ŀ�����µ�url
		String path = request.getContextPath();
		String exitAdminPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/login.html";
		jsonObject.put("url", exitAdminPath);
		return jsonObject;
	}
	
	/**
	 * ��̨��¼��־�ı���
	 * @return
	 */
	@RequestMapping(value="/adminLogReport.do",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject adminLogReport(
			@RequestParam("dateVal") String dateVal, 
			@RequestParam("adminId") String adminId, 
			@RequestParam(value="sortName",required=false) String sortName, 
			@RequestParam(value="sortOrder",required=false) String sortOrder, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber){
		JSONObject jsonObject = adminService.adminLogReport(dateVal, adminId, sortName, 
				sortOrder, pageSize, pageNumber);
		return jsonObject;
	}
	
	/**
	 * ����ɾ����̨��¼��־
	 * @param ids
	 * @return
	 */
	@RequestMapping("/batchDelLog.do")
	@ResponseBody
	public JSONObject batchDelLog(@RequestParam("param") String ids){
		JSONObject jsonObject = adminService.batchDelLog(ids);
		return jsonObject;
	}
	
	/**
	 * ����ɾ������������
	 * @param ids
	 * @return
	 */
	@RequestMapping("/batchDelQuestion.do")
	@ResponseBody
	public JSONObject batchDelQuestion(@RequestParam("param") String ids){
		JSONObject jsonObject = pPService.batchDelQuestions(ids);
		return jsonObject;
	}
	
	/**
	 * ����ɾ���������
	 * @param ids
	 * @return
	 */
	@RequestMapping("/batchDelThemeCateg.do")
	@ResponseBody
	public JSONObject batchDelThemeCateg(@RequestParam("param") String ids){
		JSONObject jsonObject = activeUserService.batchDelThemeCateg(ids);
		return jsonObject;
	}
	
	/**
	 * ����������
	 * @param themeCateg �������
	 * @param themePics UIͼ��
	 * @param request
	 * @return
	 */
	@RequestMapping("/attachThemeCateg.do")
	@ResponseBody
	public JSONObject attachThemeCateg(
			@RequestParam(value="themePicVal",required=false) MultipartFile themePic, 
			HttpServletRequest request){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String themeCateg = multipartRequest.getParameter("themeCategVal");
		//�����ļ��ı���·��
		String localBasePath = request.getSession().getServletContext().getRealPath("/")+"images/local_active/theme/";
		JSONObject jsonObject = activeUserService.attachThemeCateg(themePic, themeCateg, localBasePath);
		return jsonObject;
	}
	
	@RequestMapping("/bannerReport.do")
	@ResponseBody
	public JSONObject bannerReport(@RequestParam(value="sortName",required=false) String sortName, 
			@RequestParam(value="sortOrder",required=false) String sortOrder, 
			@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath02 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_banner/";
		JSONObject jsonObject = adminService.bannerReport(pageSize, pageNumber, sortName, sortOrder, hostPath02);
		return jsonObject;
	}
	
	/**
	 * ����ɾ��banner
	 * @param ids
	 * @return
	 */
	@RequestMapping("/batchDelBannerPic.do")
	@ResponseBody
	public JSONObject batchDelBannerPic(@RequestParam("param") String ids){
		JSONObject jsonObject = adminService.batchDelBanner(ids);
		return jsonObject;
	}
	
	/**
	 * ��ȡbannerͼ������
	 * @return
	 */
	@RequestMapping("/bannerIndex.do")
	@ResponseBody
	public JSONObject bannerIndex(){
		JSONObject jsonObject = adminService.bannerIndex();
		return jsonObject;
	}
	
	@RequestMapping("/updateBannerPic.do")
	@ResponseBody
	public JSONObject updateBannerPic(@RequestParam("bannerPicVal") MultipartFile bannerPic, 
			HttpServletRequest request){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String selectPicId = multipartRequest.getParameter("selectPicIdVal"); 
		String title = multipartRequest.getParameter("titleVal");
		String thredURL = multipartRequest.getParameter("thredURLVal");
		//�����ļ��ı���·��
		String localBasePath = request.getSession().getServletContext().getRealPath("/")+"images/local_banner/";
		JSONObject jsonObject = adminService.updateBannerPic(bannerPic, selectPicId, title, thredURL, localBasePath);
		return jsonObject;
	}
	
	/**
	 * home.htmlҳ��chart��ɸѡ���ֵ
	 * @return
	 */
	@RequestMapping("/homeSelectorVal.do")
	@ResponseBody
	public JSONObject homeSelectorVal(HttpServletRequest request){
		JSONObject jsonObject = userService.getHomeSelectorVal();
		jsonObject.put("loginIP", request.getRemoteAddr());
		jsonObject.put("hint", new Timestamp(System.currentTimeMillis()));
		return jsonObject;
	}
	
	/**
	 * home.htmlҳ��chart������
	 * @return
	 */
	@RequestMapping("/homeChartData.do")
	@ResponseBody
	public JSONObject homeChartData(@RequestParam("selectDate") String selectDate){
		JSONObject jsonObject = userService.getHomeChartData(selectDate);
		return jsonObject;
	}
	
	/**
	 * ���ԭʼ��̨��¼����
	 * @param originalPwd
	 * @return
	 */
	@RequestMapping("/checkOriginalPwd.do")
	@ResponseBody
	public JSONObject checkOriginalPwd(@RequestParam("adminUser") String adminUser, 
			@RequestParam("originalPwd") String originalPwd){
		JSONObject jsonObject = adminService.checkOriginalPwd(adminUser, originalPwd);
		return jsonObject;
	}
	
	/**
	 * �õ�index.html��֪ͨ����
	 * @return
	 */
	@RequestMapping("/notifies.do")
	@ResponseBody
	public JSONObject notifies(){
		JSONObject jsonObject = adminService.getNotifies();
		return jsonObject;
	}
	
	/**
	 * ��̨��Ӷ�̬�Ķ�������
	 * @param twoPicVal ���������uiͼ��
	 * @param themeCategVal �����������
	 * @param twoCategVal ������������
	 * @return
	 */
	@RequestMapping("/attachActiveTwoCateg.do")
	@ResponseBody
	public JSONObject attachActiveTwoCateg(@RequestParam(value="twoPicVal",required=false) MultipartFile twoPic, 
			HttpServletRequest request){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String themeCateg = multipartRequest.getParameter("themeCategVal");
		String twoCateg = multipartRequest.getParameter("twoCategVal");
		//�����ļ��ı���·��
		String localBasePath = request.getSession().getServletContext().getRealPath("/")+"images/local_active/theme/";
		JSONObject jsonObject = activeUserService.attachActiveTwoCateg(twoPic, themeCateg, twoCateg, localBasePath);
		return jsonObject;
	}
	
	/**
	 * ��̬���������ı���
	 */
	@RequestMapping("/activeCategReport.do")
	@ResponseBody
	public JSONObject activeCategReport(@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			@RequestParam("startDate") String startDate, 
			@RequestParam("endDate") String endDate){
		JSONObject jsonObject = activeUserService.activeCategReport(pageSize, pageNumber, startDate, endDate);
		return jsonObject;
	}
	
	/**
	 * �����û�
	 * @param schoolIds
	 * @return
	 */
	@RequestMapping("/unOrblockUser.do")
	@ResponseBody
	public JSONObject unOrblockUser(@RequestParam("user_id") String user_id, 
			@RequestParam("lock") int lock){
		JSONObject jsonObject = userService.blockUser(user_id, lock);
		return jsonObject;
	}
	
	@RequestMapping("/categSelect.do")
	@ResponseBody
	public JSONObject themeCateg(){
		JSONObject jsonObject = activeUserService.themeCateg();
		return jsonObject;
	}
	
	@RequestMapping("/twoCategSelect.do")
	@ResponseBody
	public JSONObject twoCategSelect(@RequestParam("themeCateg") String themeCateg){
		JSONObject jsonObject = activeUserService.twoCategSelect(themeCateg);
		return jsonObject;
	}
	
	@RequestMapping("/detailActiveRepoet.do")
	@ResponseBody
	public JSONObject detailActiveRepoet(@RequestParam("searchText") String searchText, 
			@RequestParam(value="startDate",required=false) String startDate, 
			@RequestParam(value="endDate",required=false) String endDate, 
			@RequestParam(value="themeClass",required=false) String themeClass, 
			@RequestParam(value="twoClass",required=false) String twoClass, 
			@RequestParam(value="state",required=false) String state, 
			@RequestParam(value="sortName",required=false) String sortName, 
			@RequestParam(value="sortOrder",required=false) String sortOrder, 
			@RequestParam(value="pageSize",required=false) int pageSize, 
			@RequestParam(value="pageNumber",required=false) int pageNumber, 
			HttpServletRequest request){
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath02 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_active/";
		JSONObject jsonObject = activeUserService.detailActiveRepoet(searchText, startDate, endDate, 
				themeClass, twoClass, state, sortName, sortOrder, pageSize, pageNumber, hostPath02);
		return jsonObject;
	}
	
}
