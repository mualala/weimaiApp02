package com.liancheng.it.task;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.context.ServletContextAware;

import com.liancheng.it.dao.user.UserDao;
import com.liancheng.it.listener.AppRequestListener;

@Controller
public class PollingActives implements ServletContextAware {
//	private HttpServletRequest request;
	private Model model;
	@Resource(name="userDao")//ע��userDao
	private UserDao userDao;
	private ServletContext servletContext;
	public void pollActive(){
//		System.out.println(model);
//		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
//		System.out.println("webApplicationContext="+webApplicationContext);
//		ServletContext servletContext = webApplicationContext.getServletContext();
		System.out.println("3---��ʱ���е������ĵ�����ֵ="+servletContext.getAttribute("username"));
		System.out.println("3.2---�������л�ȡ��aa="+servletContext.getAttribute("aa"));
		System.out.println("3.3---�������л�ȡ��userList="+(servletContext.getAttribute("userList")));
//		if(model != null){
//			Map modelMap = model.asMap();
//			
//		}
		AppRequestListener appRequestListener = new AppRequestListener();
//		appRequestListener.aa();
	}

	public void setServletContext(ServletContext arg0) {
		servletContext = arg0;
	}
	
}
