package test;

import java.sql.Timestamp;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liancheng.it.dao.user.UserDao;
import com.liancheng.it.entity.user.User;
import com.liancheng.it.util.UUIDUtil;


@SuppressWarnings("unused")
public class TestTxManager extends TestBase {
		
	
//	private UserDao userDao;
//	
//	@Before(value = "test()")
//	public void init(){
//		
//		userDao = getContext().getBean("userDao",UserDao.class);
//	}
	
	@Test
	public void test1(){//验证model层 注册业务
		String[] conf={"conf/spring-mybatis.xml","conf/spring-mvc.xml","conf/spring-aop.xml"};
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		UserDao userDao = ac.getBean("userDao",UserDao.class);
		User user = new User();
		user.setUser_id(UUIDUtil.creatId());
		try {
			user.setPassword(UUIDUtil.md5("123456"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		user.setPhoneNum("18782951708");
		user.setGender("男");
		user.setBirthday("");
		
		try{
			System.out.println(userDao.save(user));
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}
