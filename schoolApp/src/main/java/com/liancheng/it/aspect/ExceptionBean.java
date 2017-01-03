package com.liancheng.it.aspect;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component//ɨ�赽spring����
@Aspect//ָ���ָ��Ϊ�������
public class ExceptionBean {
	
	//e����û�б�����׳����쳣����
	@AfterThrowing(throwing="e",pointcut="within(com.liancheng.it.controller..*)")
	public void execute(Exception e){
		
		FileWriter fw;
		try {
			//���쳣��Ϣд���ļ���
			fw = new FileWriter("d:\\hongbao\note_writer.log",true);
			//����pw����д��Ϣ
			PrintWriter pw = new PrintWriter(fw);
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String timestr = sdf.format(date);
			pw.println("********************************************");
			pw.println("*�쳣���ͣ�"+e);
			pw.println("*�쳣ʱ�䣺"+timestr);
			pw.println("*****************�쳣����********************");
			e.printStackTrace(pw);
			pw.close();
			fw.close();
		} catch (IOException ex) {
			
			System.out.println("��¼�쳣ʧ��");
		}
		
		
	}
}
