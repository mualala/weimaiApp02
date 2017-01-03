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
	 * 登录请求
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
	 * 对说说的审核
	 */
	@RequestMapping(value="/activeReport.do", produces="application/json;charset=UTF-8", 
			method=RequestMethod.POST)
	@ResponseBody
	public JSONObject activeReport(@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		
		System.out.println("进了后台对说说的审核！！");
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_active/";
		JSONObject jsonObject = activeUserService.adminActive(pageSize, pageNumber, hostPath);
		
		return jsonObject;
	}
	
	/**
	 * 动态审核通过
	 */
	@RequestMapping(value="/oneVerify.do")
	@ResponseBody
	public JSONObject activeVerify(@RequestParam("active_user_id") int active_user_id){
		System.out.println("进了动态数据审核。。。");
		JSONObject jsonObject = activeUserService.verifyActive(active_user_id);
		return jsonObject;
	}
	
	/**
	 * 动态审核不通过
	 */
	@RequestMapping(value="/oneNoVerify.do")
	@ResponseBody
	public JSONObject activeNoVerify(@RequestParam("active_user_id") int active_user_id){
		System.out.println("进了动态数据审核 不通过。。。");
		JSONObject jsonObject = activeUserService.noVerifyActive(active_user_id);
		return jsonObject;
	}
	
	/**
	 * 后台对发说说的权限验证列表
	 */
	@RequestMapping(value="/verifies.do")
	@ResponseBody
	public JSONObject authVerifies(@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber){
		System.out.println("后台对发说说的权限验证列表。。。");
		JSONObject jsonObject = activeUserService.authActive(pageSize, pageNumber);
		return jsonObject;
	}
	
	/**
	 * 更改发动态的权限
	 * @param state 是否开启发动态验证,0和1
	 * @param class_active 动态的分类
	 * @param verify 是学生还是毕业证验证
	 */
	@RequestMapping(value="modifyVerify.do")
	@ResponseBody
	public JSONObject modifyVerify(@RequestParam("state") String state, 
			@RequestParam("class_active") String class_active, 
			@RequestParam("verify") String verify){
		System.out.println("进了修改发说说的权限更改。。。");
		System.out.println("state="+state+",verify="+verify);
		JSONObject jsonObject = activeUserService.modifyVerify(state, class_active, verify);
		return jsonObject;
	}
	
	/**
	 * 后台用户验证信息的报表请求
	 */
	@RequestMapping(value="/userVerifyReport.do")
	@ResponseBody
	public JSONObject verifyInfo(@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber, 
			HttpServletRequest request){
		System.out.println("进了身份信息验证的审核请求。。");
		//项目环境下的图片路径
		String path = request.getContextPath();
		String hostPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/images/local_verify/";
		JSONObject jsonObject = userService.verifyReport(pageSize, pageNumber, hostPath);
		return jsonObject;
	}
	
	/**
	 * 后台独项验证学生证或毕业证
	 */
	@RequestMapping(value="/oneUserVerify.do")
	@ResponseBody
	public JSONObject adminUserVerify(@RequestParam("schoolId") int schoolId, 
			@RequestParam("verify") String verify, 
			@RequestParam("state") String state){
		System.out.println("进了后台独项验证学生证或毕业证。。");
		JSONObject jsonObject = userService.oneUserVerify(schoolId, verify, state);
		return jsonObject;
	}
	
	/**
	 * 后台问题报表
	 */
	@RequestMapping(value="/questionsManager.do")
	@ResponseBody
	public JSONObject questionsManager(@RequestParam("pageSize") int pageSize, 
			@RequestParam("pageNumber") int pageNumber){
		System.out.println("进了后台问题报表。。");
		JSONObject jsonObject = pPService.questionsReport(pageSize, pageNumber);
		return jsonObject;
	}
	
	/**
	 * 后台添加问题
	 */
	@RequestMapping(value="/addQuestion.do")
	@ResponseBody
	public JSONObject addQuestion(@RequestParam("question") String question){
		JSONObject jsonObject = pPService.addQuestion(question);
		return jsonObject;
	}
	
	/**
	 * 后台系统对发动态的批量审核
	 */
	@RequestMapping("/batchActiveVerify.do")
	@ResponseBody
	public JSONObject batchActiveVerify(@RequestParam("param") String ids){
		System.out.println("进了动态的批量审核请求。。。");
		System.out.println(ids);
		JSONObject jsonObject = activeUserService.batchDoVerify(ids);
		return jsonObject;
	}
	
	/**
	 * 后台管理系统对资源文件的下载的跳转请求
	 */
	@RequestMapping("/{fileName}/toDownDocum.do")
	public ModelAndView toDownDocum(@PathVariable("fileName") String documName, 
			HttpServletRequest request, 
			HttpServletResponse response){
		System.out.println("进了跳转请求");
		
//		response.setContentType("text/html;charset=utf-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		//保存文件的本地路径
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
			System.out.println("下载文件失败!");
			e.printStackTrace();
		} finally {
			if(bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					System.out.println("bis关闭失败!");
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					System.out.println("bos关闭失败!");
				}
			}
		}
		return null;
	}
	
	
}
