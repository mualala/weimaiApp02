package com.liancheng.it.service.admin;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;




import javax.annotation.Resource;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liancheng.it.dao.admin.AdminDao;
import com.liancheng.it.entity.admin.Admin;
import com.liancheng.it.util.DateUtil;
import com.liancheng.it.util.UUIDUtil;




import net.minidev.json.JSONObject;

@Service("adminService")//…®√Ëservice
@Aspect
@Transactional
public class AdminServiceImpl implements AdminService {
	@Resource(name="adminDao")
	private AdminDao adminDao;

	public boolean checkAmdin(String adminame, String password){
		Admin admin = adminDao.checkAdmin(adminame);
		String md5pwd = "";
		try {
			md5pwd = UUIDUtil.md5(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(admin != null && md5pwd.equals(admin.getPassword())){
			return true;
		}
		return false;
	}


}
