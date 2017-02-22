package com.liancheng.it.dao.commont;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.liancheng.it.entity.commont.ChildCommont;
import com.liancheng.it.entity.commont.Commont;
import com.liancheng.it.entity.commont.LaudUser;

public interface CommontDao {
	
	public void addAction(Commont commont);//添加评论
	public int totalCommont(int active_user_id);//某条说说的评论数量
	public void addLaud(Commont commont);//点赞
	public int totalLaud(int active_user_id);//某条说说的点赞数量
	public int totalChildComLaud(String parent_user_id);//查询每条子评论的点赞数量
	public Commont queryIsLaud(Map<String, Object> params);//查询用户点赞
	public ChildCommont queryChildComIsLaud(Map<String, Object> params);//查询子评论的用户的点赞
	public List<LaudUser> queryLauds(int active_user_id);//查询某条说说点赞的用户
	public List<Commont> queryComs(int active_user_id);//查询说说的所有直接评论
	
	public void addChildCom(ChildCommont childCom);//添加子评论
	public List<ChildCommont> queryChildComms(Map<String, Object> params);//查找评论的子评论
	
	public int totalCom(int active_user_id);//说说的直接评论数量
	public int totalChildCom(Map<String, Object> params);//说说的子评论数量
	public void addChildComLaud(ChildCommont childCom);//添加子评论的点赞
	
	public int queryTotalComNoSee(String user_id);//查询某用户直接子评论的未查看数量
	public void batchModifyComNoSee(Map<String, Object> params);//朋友圈直接子评论改成用户已查看状态
	public List<Commont> queryCommontsByActId(int active_user_id);//根据act的id查询子评论
	public void deleteCommsByActId(int active_user_id);//根据act的id删除子评论
	public void deleteChildCommsByCommId(List<Integer> com_id);//删除子评论的评论
	public int deleteLaud(Map<String, Object> params);//取消点赞
	
}
