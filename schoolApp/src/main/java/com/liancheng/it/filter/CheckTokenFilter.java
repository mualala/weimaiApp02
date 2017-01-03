package com.liancheng.it.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liancheng.it.util.*;


import net.minidev.json.JSONObject;

/**
 * toeknУ������������е�API�ӿ�����Ҫ�����ù�����(���˵�½�ӿ�)
 * @author running@vip.163.com
 *
 */
@WebFilter(urlPatterns="/controller/*")
public class CheckTokenFilter implements Filter {
	
	public void doFilter(ServletRequest argo, ServletResponse arg1,
			FilterChain chain ) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) argo;
		HttpServletResponse response=(HttpServletResponse) arg1;
//		response.setHeader("Access-Control-Allow-Origin", "*");
		if(request.getRequestURI().endsWith("/user/login.do")){
			//��½�ӿڲ�У��token��ֱ�ӷ���
			chain.doFilter(request, response);
			return;
		}
		//����API�ӿ�һ��У��token
		System.out.println("��ʼУ��token");
		//������ͷ�л�ȡtoken
		String token=request.getHeader("token");
		Map<String, Object> resultMap=Jwt.validToken(token);
		TokenState state=TokenState.getTokenState((String)resultMap.get("state"));
		switch (state) {
		case VALID:
			//ȡ��payload������,���뵽request��������
			request.setAttribute("data", resultMap.get("data"));
			//����
			chain.doFilter(request, response);
			break;
		case EXPIRED:
		case INVALID:
			System.out.println("��Чtoken");
			//token���ڻ�����Ч�������������Ϣ���ظ�ajax
			JSONObject outputMSg=new JSONObject();
			outputMSg.put("success", false);
			outputMSg.put("msg", "����token���Ϸ����߹����ˣ������µ�½");
			output(outputMSg.toJSONString(), response);
			break;
		}
		
		
	}
	
	
	public void output(String jsonStr,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8;");
		PrintWriter out = response.getWriter();
//		out.println();
		out.write(jsonStr);
		out.flush();
		out.close();
		
	}
	
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("token��������ʼ����");
	}

	public void destroy() {
		
	}
	
}
