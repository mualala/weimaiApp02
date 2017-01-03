package com.liancheng.it.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//��װ��ͬ��������
@Component//ɨ�裬�ȼ���Bean
@Aspect//�ȼ���<aop:aspect>����
public class LoggerBean {
	//�ȼ���<aop:before>����
	//��Contrller����ִ��ǰ����ִ��logController����
	@Before("within(com.liancheng.it.controller..*)")
	public void logController(){
		System.out.println("����Controller��������");
	}
	
	@Before("within(com.liancheng.it.service..*)")
	public void logService(){
		System.out.println("����Service��������");
}
}
