package com.liancheng.it.controller.admin.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * ��̨�����������
 */
public class AdminInterceptor implements HandlerInterceptor {
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			return true;
		}
		//��Ŀ�����µ�ͼƬ·��
		String path = request.getContextPath();
		String hostPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
		response.sendRedirect(hostPath+"/login.html");
		return false;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {}

}
