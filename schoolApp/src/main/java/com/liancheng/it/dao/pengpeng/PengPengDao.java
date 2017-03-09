package com.liancheng.it.dao.pengpeng;

import java.util.List;
import java.util.Map;

import com.liancheng.it.entity.pengpeng.Answer;
import com.liancheng.it.entity.pengpeng.AnswerUsers;
import com.liancheng.it.entity.pengpeng.ChildAnswer;
import com.liancheng.it.entity.pengpeng.OwnAnswer;
import com.liancheng.it.entity.pengpeng.Questions;
import com.liancheng.it.entity.pengpeng.TwoChildAnswer;

public interface PengPengDao {
	
	/**
	 * 添加问题
	 * @param params
	 */
	public void addQuestion(Map<String, Object> params);
	
	/**
	 * admin中的问题报表
	 * @param params
	 * @return
	 */
	public List<Questions> questionReport(Map<String, Object> params);
	
	/**
	 * 问题列表的总数
	 * @param params
	 * @return
	 */
	public int totalQuestions(Map<String, Object> params);
	
	/**
	 * 添加问题的直接回答---1
	 * @param params
	 */
	public void saveAnswer(Map<String, Object> params);
	
	/**
	 * 查询用户的回答
	 * @param params
	 * @return
	 */
	public Answer queryOneAnswer(Map<String, Object> params);
	
	/**
	 * 随机查询某条问题
	 * @return
	 */
	public Questions randOneQuestion();
	
	/**
	 * 查询某条问题的直接子回答
	 * @param params
	 * @return
	 */
	public List<AnswerUsers> queryAnswers(Map<String, Object> params);
	
	/**
	 * 查询某条问题
	 * @param ques_id
	 * @return
	 */
	public Questions queryQuestion(int ques_id);
	
	/**
	 * 查实名或匿名问题列表
	 * @param params
	 * @return
	 */
	public List<Questions> queryTypeQuestions(Map<String, Object> params);
	
	/**
	 * 直接子评论的点赞
	 * @param params
	 */
	public void addChildAnsLaud(Map<String, Object> params);
	
	/**
	 * 添加问题的评论的评论---2
	 * @param params
	 */
	public void addChildAnswer(Map<String, Object> params);
	
	/**
	 * 查询某个点赞用户
	 * @param params
	 * @return
	 */
	public ChildAnswer queryIsLaud(Map<String, Object> params);
	
	/**
	 * 统计碰碰某回答的点赞数
	 * @param ans_id
	 * @return
	 */
	public int countAnswerLaud(int ans_id);
	
	/**
	 * 查询当前登录用户对某问题的回答
	 * @param params
	 * @return
	 */
	public Answer queryCurrUserAnswer(Map<String, Object> params);
	
	/**
	 * 查出当前回答用户的个人信息和回答信息
	 * @param params
	 * @return
	 */
	public OwnAnswer queryOwnAnswer(Map<String, Object> params);
	
	/**
	 * 登录用户对已回答问题展示页的子评论列表实体类
	 * @param params
	 * @return
	 */
	public List<ChildAnswer> queryOwnChildAnswers(Map<String, Object> params);
	
	/**
	 * 仅双方可见的数据查询
	 * @param params
	 * @return
	 */
	public List<ChildAnswer> queryOnlyChildAnswers(Map<String, Object> params);
	
	/**
	 * 查询评论的查看数量
	 * @param ans_id
	 * @return
	 */
	public Answer queryAnswerById(int ans_id);
	
	/**
	 * 增加碰碰评论的查看数量
	 * @param params
	 */
	public void saveAnswerSee(Map<String, Object> params);
	
	/**
	 * 统计碰碰某评论的子评论数量
	 * @param params
	 * @return
	 */
	public int countChildAnswer(Map<String, Object> params);
	
	/**
	 * 查询登录用户是否回答过
	 * @param params
	 * @return
	 */
	public Answer queryUserIsAns(Map<String, Object> params);
	
	/**
	 * 再次添加回答的回答---3
	 * @param params
	 */
	public void addTwoChildAns(Map<String, Object> params);
	
	/**
	 * 查询评论里面的评论
	 * @param child_ans_id
	 * @return
	 */
	public List<TwoChildAnswer> queryTwoChildAns(int child_ans_id);
	
	/**
	 * 查询评论里面的评论,仅好友可见
	 * @param params
	 * @return
	 */
	public List<TwoChildAnswer> queryOnlyTwoChildAns(Map<String, Object> params);
	
	/**
	 * 批量删除问题
	 * @param ids
	 * @return
	 */
	public int batchDelQuestions(List<Integer> ids);
	
	
}
