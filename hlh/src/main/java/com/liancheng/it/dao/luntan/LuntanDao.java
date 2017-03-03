package com.liancheng.it.dao.luntan;

import java.util.List;
import java.util.Map;

import com.liancheng.it.entity.luntan.Luntan;

public interface LuntanDao {
	
	int saveCommont(Luntan params);
	List<Luntan> queryPaginationLT(Map<String, Object> params);
	int totalPaginationLT(Map<String, Object> params);
	Luntan queryLTById(int lt_id);
	
	
	
	
}
