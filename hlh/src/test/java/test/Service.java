package test;

import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mysql.jdbc.Connection;

import net.minidev.json.JSONObject;
import net.sf.json.JSON;

@SuppressWarnings("unused")
public class Service  {
	
	@Test
	public void test9(){//测试霍林河的数据库连接
		String[] conf={"conf/spring-mybatis.xml","conf/spring-mvc.xml","conf/spring-aop.xml"};
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		System.out.println(ac.getBean("dbcp"));
		
	}
	
}
