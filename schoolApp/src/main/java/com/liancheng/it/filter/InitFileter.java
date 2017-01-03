package com.liancheng.it.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.thetransactioncompany.cors.CORSConfiguration;
import com.thetransactioncompany.cors.CORSFilter;

/**
 * ����˿����������,�ù�������Ҫ����cors-filter-2.2.1.jar��java-property-utils-1.9.1.jar
 * @author 
 *
 */
@WebFilter(urlPatterns={"/*"},asyncSupported=true,
initParams={
	@WebInitParam(name="cors.allowOrigin",value="*"),
	@WebInitParam(name="cors.supportedMethods",value="CONNECT, DELETE, GET, HEAD, OPTIONS, POST, PUT, TRACE"),
	@WebInitParam(name="cors.supportedHeaders",value="token,Accept, Origin, X-Requested-With, Content-Type, Last-Modified"),//ע�⣬���token�ֶη�������ͷ������ˣ�������Ҫ����
	@WebInitParam(name="cors.exposedHeaders",value="Set-Cookie"),
	@WebInitParam(name="cors.supportsCredentials",value="true")
})
public class InitFileter extends CORSFilter implements javax.servlet.Filter {
	public void init(FilterConfig config) throws ServletException {
		System.out.println("������Դ�����������ʼ����");
		super.init(config);
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("���������");
		super.doFilter(request, response, chain);
	}


	public void setConfiguration(CORSConfiguration config) {
		super.setConfiguration(config);
	}

	
	
}
