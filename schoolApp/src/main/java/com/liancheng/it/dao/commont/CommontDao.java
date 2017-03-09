package com.liancheng.it.dao.commont;

import java.util.List;
import java.util.Map;

import com.liancheng.it.entity.commont.ChildCommont;
import com.liancheng.it.entity.commont.Commont;
import com.liancheng.it.entity.commont.LaudUser;

public interface CommontDao {
	
	/**
	 * 添加评论
	 * @param commont
	 */
	public void addAction(Commont commont);
	
	/**
	 * 某条说说的评论数量
	 * @param active_user_id
	 * @return
	 */
	public int totalCommont(int active_user_id);
	
	/**
	 * 点赞
	 * @param commont
	 */
	public void addLaud(Commont commont);
	
	/**
	 * 某条说说的点赞数量
	 * @param active_user_id
	 * @return
	 */
	public int totalLaud(int active_user_id);
	
	/**
	 * 查询每条子评论的点赞数量
	 * @param parent_user_id
	 * @return
	 */
	public int totalChildComLaud(String parent_user_id);
	
	/**
	 * 查询用户点赞
	 * @param params
	 * @return
	 */
	public Commont queryIsLaud(Map<String, Object> params);
	
	/**
	 * 查询子评论的用户的点赞
	 * @param params
	 * @return
	 */
	public ChildCommont queryChildComIsLaud(Map<String, Object> params);
	
	/**
	 * 查询某条说说点赞的用户
	 * @param active_user_id
	 * @return
	 */
	public List<LaudUser> queryLauds(int active_user_id);
	
	/**
	 * 查询说说的所有直接评论
	 * @param active_user_id
	 * @return
	 */
	public List<Commont> queryComs(int active_user_id);
	
	/**
	 * 添加子评论
	 * @param childCom
	 */
	public void addChildCom(ChildCommont childCom);
	
	/**
	 * 查找评论的子评论
	 * @param params
	 * @return
	 */
	public List<ChildCommont> queryChildComms(Map<String, Object> params);
	
	/**
	 * 说说的直接评论数量
	 * @param active_user_id
	 * @return
	 */
	public int totalCom(int active_user_id);
	
	/**
	 * 说说的子评论数量
	 * @param params
	 * @return
	 */
	public int totalChildCom(Map<String, Object> params);
	
	/**
	 * 添加子评论的点赞
	 * @param childCom
	 */
	public void addChildComLaud(ChildCommont childCom);
	
	/**
	 * 查询某用户直接子评论的未查看数量
	 * @param user_id
	 * @return
	 */
	public int queryTotalComNoSee(String user_id);
	
	/**
	 * 朋友圈直接子评论改成用户已查看状态
	 * @param params
	 */
	public void batchModifyComNoSee(Map<String, Object> params);
	
	/**
	 * 根据act的id查询子评论
	 * @param active_user_id
	 * @return
	 */
	public List<Commont> queryCommontsByActId(int active_user_id);
	
	/**
	 * 根据act的id删除子评论
	 * @param active_user_id
	 */
	public void deleteCommsByActId(int active_user_id);
	
	/**
	 * 删除子评论的评论
	 * @param com_id
	 */
	public void deleteChildCommsByCommId(List<Integer> com_id);
	
	/**
	 * 取消点赞
	 * @param params
	 * @return
	 */
	public int deleteLaud(Map<String, Object> params);
	
	
	
}
