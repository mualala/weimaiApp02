package com.liancheng.it.dao.admin;

import java.util.List;
import java.util.Map;

import com.liancheng.it.entity.active.ActiveVerify;
import com.liancheng.it.entity.admin.Admin;

public interface AdminDao {

	public Integer saveAdmin(Admin admin);
	public Integer updateAdmin(Admin admin);
	public Admin findByName(Map<String,String> map);
	
	
	public Admin checkAdmin(String adminame);//¼ì²éadminµÇÂ¼
	
}
