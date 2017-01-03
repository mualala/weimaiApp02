package com.liancheng.it.aspect;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component//扫描到spring容器
@Aspect//指组件指定为切面组件
public class ExceptionBean {
	
	//e就是没有表组件抛出的异常对象
	@AfterThrowing(throwing="e",pointcut="within(com.liancheng.it.controller..*)")
	public void execute(Exception e){
		
		FileWriter fw;
		try {
			//将异常信息写入文件中
			fw = new FileWriter("d:\\hongbao\note_writer.log",true);
			//利用pw对象写信息
			PrintWriter pw = new PrintWriter(fw);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String timestr = sdf.format(date);
			pw.println("********************************************");
			pw.println("*异常类型："+e);
			pw.println("*异常时间："+timestr);
			pw.println("*****************异常详情********************");
			e.printStackTrace(pw);
			pw.close();
			fw.close();
		} catch (IOException ex) {
			
			System.out.println("记录异常失败");
		}
		
		
	}
}
