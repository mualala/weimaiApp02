package com.liancheng.it.listener;

import java.util.ArrayList;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.liancheng.it.entity.user.User;

public class AppContextListener implements ServletContextListener {
	
	private ArrayList<String> userList;
	
	
	public void contextInitialized(ServletContextEvent sce) {
//		userList = (ArrayList<String>) sce.getServletContext().getAttribute("userList");
//		if (userList) {
//			
//		}
		
		
	}

	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
