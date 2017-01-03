package test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
















import com.liancheng.it.dao.active.ActiveUserDao;
import com.liancheng.it.dao.commont.CommontDao;
import com.liancheng.it.dao.user.UserDao;
import com.liancheng.it.entity.active.Active;
import com.liancheng.it.entity.commont.ChildCommont;
import com.liancheng.it.entity.commont.Commont;
import com.liancheng.it.service.active.ActiveUserService;
import com.liancheng.it.service.admin.AdminService;
import com.liancheng.it.service.user.UserService;

import net.minidev.json.JSONObject;
import net.sf.json.JSON;

@SuppressWarnings("unused")
public class Service  {
	
	@Test
	public void test8(){
		String[] conf={"conf/spring-mybatis.xml","conf/spring-mvc.xml","conf/spring-aop.xml"};
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		ActiveUserDao activeUserDao = ac.getBean("activeUserDao",ActiveUserDao.class);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", 0);
		params.put("end", 2);
		System.out.println(params.toString());
		List<Active> actives = activeUserDao.queryAdmin(params);
		System.out.println(actives.toString());
	}
	
	@Test
	public void test6(){//查找用户头像
		String[] conf={"conf/spring-mybatis.xml","conf/spring-mvc.xml","conf/spring-aop.xml"};
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		UserDao userDao = ac.getBean("userDao",UserDao.class);
		String i = userDao.queryUserProfile("181d4d3239e449b2938821243327048c");
		System.out.println(i);
	}
	
//	@Test
//	public void test5(){//测试查询某条说说评论的数量
//		String[] conf={"conf/spring-mybatis.xml","conf/spring-mvc.xml","conf/spring-aop.xml"};
//		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
//		CommontDao commontDao = ac.getBean("commontDao",CommontDao.class);
//		int i = commontDao.totalCommont(214);
//		System.out.println(i);
//	}
	
	@Test
	public void test1(){//生成验证码业务
		String[] conf={"conf/spring-mybatis.xml","conf/spring-mvc.xml","conf/spring-aop.xml"};
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		UserService userService = ac.getBean("userService",UserService.class);
		JSONObject result = userService.checkUserRegist("13883261153");
		System.out.println(result);
	}
	
	@Test
	public void test2(){//注册业务
		String[] conf={"conf/spring-mybatis.xml","conf/spring-mvc.xml","conf/spring-aop.xml"};
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		UserService userService = ac.getBean("userService",UserService.class);
		JSONObject result = userService.addUser("18782951708", "123456", "282618","");
		System.out.println(result);
	}
//	@Test
//	public void test3(){//登录业务
//		String[] conf={"conf/spring-mybatis.xml","conf/spring-mvc.xml","conf/spring-aop.xml"};
//		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
//		UserService userService = ac.getBean("userService",UserService.class);
//		JSONObject result = userService.checkLogin("18782951708", "123456");
//		System.out.println(result);
//	}
	
	@Test
	public void othersText(){
//		JSONObject jsonObject = new JSONObject();
		Map<String, Object> defSkills = new HashMap<String, Object>();
		defSkills.put("defSkills", "Offices,Photos,3Dmax,团队管理,摄影,andriod,Web前端");
		org.json.JSONObject orJson = new org.json.JSONObject(defSkills);
//		jsonObject.put("defSkills", defSkills);
		System.out.println(orJson.toString());
	}
	
	@Test
	public void strReplace(){
		String str = "http://localhost:8080/schoolApp/images/local_active/1480560566732-0.jpg";
		int laststr = str.lastIndexOf("/");
		System.out.println(laststr+",total="+str.length());
		System.out.println(str.substring(laststr+1));
	}
	
}
