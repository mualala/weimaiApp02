package com.liancheng.it.listener;

import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.liancheng.it.dao.user.UserDao;
import com.liancheng.it.entity.user.User;

@WebListener
public class AppRequestListener implements ServletRequestListener {
	
//	private ArrayList<String> userList;
	private int reqRandInt;//����������ʶ
	
	
	public void requestDestroyed(ServletRequestEvent sre) {
//		userList  = (ArrayList<String>)sre.getServletContext().getAttribute("userList");
//		
//		if(userList==null)
//			userList = new ArrayList<String>();
		
		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
//		String phoneNum = request.getParameter("phoneNum");
//		sre.getServletContext().setAttribute("username", request.getParameter("phoneNum"));
//		String phoneNum = (String) sre.getServletContext().getAttribute("username");
//		System.out.println("1---�������л�ȡ�ĵ�¼�û���="+phoneNum);
//		String user = (String) sre.getServletContext().getAttribute("aa");
//		System.out.println("1.2---�������л�ȡ��aa="+sre.getServletContext().getAttribute("aa"));
//		System.out.println("1.3---�������л�ȡ��user="+user);
//		if(user != null){
//			userList.add(user);
//		}
//		sre.getServletContext().setAttribute("userList", userList);
//		System.out.println("1.3---�������л�ȡ��userList��С="+userList.size());
//		System.out.println("����(��ʶ="+reqRandInt+")������...\n");
	}

	public void requestInitialized(ServletRequestEvent sre) {
//		reqRandInt = new Random().nextInt();
//		System.out.println("����������(�����ʶ="+reqRandInt+")");
	}
//	public void aa(){
//		System.out.println("����ִ�м������е�aa����");
//		System.out.println("����userLis="+userList);
//		if (userList!=null && userList.size()>0) {
//			for (int i = 0; i < userList.size(); i++) {
//				System.out.println("��i�������û�="+userList.get(i));
//			}
//		}
//	}
}