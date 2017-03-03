package test;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

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
	
	@Test
	public void testDaySort(){
		Map<String, String> treeMap = new TreeMap<String, String>(new Comparator<String>() {  
		    public int compare(String o1, String o2) {  
		        // return o1.compareTo(o2); // 默认：升序排列  
		        return o1.compareTo(o2);  // 降序排列  
		        // return 0;    // 只返回存储的第一个key的值，这里是"ccccc"  
		    }  
		});
		treeMap.put(String.valueOf(System.currentTimeMillis()+1), "1");  
		treeMap.put(String.valueOf(System.currentTimeMillis()+2), "2");  
		treeMap.put(String.valueOf(System.currentTimeMillis()+3), "3");  
		treeMap.put(String.valueOf(System.currentTimeMillis()+4), "4");  
		  
		for (String key : treeMap.keySet()) {  
		    System.out.println(key+" : "+treeMap.get(key));  
		}
	}
	
}
