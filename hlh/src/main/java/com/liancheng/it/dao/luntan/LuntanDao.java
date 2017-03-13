package com.liancheng.it.dao.luntan;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.liancheng.it.entity.luntan.Commont;
import com.liancheng.it.entity.luntan.Luntan;
import com.liancheng.it.entity.luntan.TwoCommont;

public interface LuntanDao {
	
	/**
	 * 保存评论
	 * @param params
	 * @return
	 */
	int saveCommont(Luntan params);
	
	/**
	 * 查询分页论坛数据
	 * @param params
	 * @return
	 */
	List<Luntan> queryPaginationLT(Map<String, Object> params);
	
	/**
	 * 论坛分页的总记录数
	 * @param params
	 * @return
	 */
	int totalPaginationLT(Map<String, Object> params);
	
	/**
	 * 根据论坛id查询论坛数据
	 * @param lt_id
	 * @return
	 */
	Luntan queryLTById(@Param("lt_id") int lt_id);
	
	/**
	 * 查询某条帖子的一级评评论
	 * @param lt_id
	 * @return
	 */
	List<Commont> queryOneLVComm(@Param("lt_id") int lt_id);
	
	/**
	 * 保存帖子的一级评论
	 * @param user_id
	 * @param other_user_id
	 * @param content
	 * @return
	 */
	int saveOneLVComm(Map<String, Object> params);
	
	/**
	 * 保存帖子的二级评论
	 * @param params
	 * @return
	 */
	int saveTwoLVComm(Map<String, Object> params);
	
	/**
	 * 查询多级评论
	 * @param comm_id
	 * @param user_id
	 * @param other_user_id
	 * @return
	 */
	List<TwoCommont> queryMultiLVComm(@Param("comm_id") int comm_id);
	
	
}
