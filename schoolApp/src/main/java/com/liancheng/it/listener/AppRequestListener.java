package com.liancheng.it.listener;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.liancheng.it.dao.user.UserDao;
import com.liancheng.it.entity.user.User;

@WebListener
public class AppRequestListener implements ServletRequestListener {
	
	private ArrayList<String> userList;
	
	
	
	public void requestDestroyed(ServletRequestEvent sre) {
		userList  = (ArrayList<String>)sre.getServletContext().getAttribute("userList");
		
		if(userList==null)
			userList = new ArrayList<String>();
		
		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
//		String phoneNum = request.getParameter("phoneNum");
//		sre.getServletContext().setAttribute("username", request.getParameter("phoneNum"));
//		String phoneNum = (String) sre.getServletContext().getAttribute("username");
//		System.out.println("1---监听器中获取的登录用户名="+phoneNum);
		String user = (String) sre.getServletContext().getAttribute("aa");
		System.out.println("1.2---监听器中获取的aa="+sre.getServletContext().getAttribute("aa"));
		System.out.println("1.3---监听器中获取的user="+user);
		if(user != null){
			userList.add(user);
		}
		sre.getServletContext().setAttribute("userList", userList);
		System.out.println("1.3---监听器中获取的userList大小="+userList.size());
		System.out.println("requestDestroyed....");
	}

	public void requestInitialized(ServletRequestEvent sre) {
		
	}
	public void aa(){
		System.out.println("，，执行监听器中的aa方法");
		System.out.println("，，userLis="+userList);
		if (userList!=null && userList.size()>0) {
			for (int i = 0; i < userList.size(); i++) {
				System.out.println("第i个在线用户="+userList.get(i));
			}
		}
	}
}
