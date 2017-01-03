package com.liancheng.it.dao.pengpeng;

import java.util.List;
import java.util.Map;

import com.liancheng.it.entity.pengpeng.Answer;
import com.liancheng.it.entity.pengpeng.AnswerUsers;
import com.liancheng.it.entity.pengpeng.ChildAnswer;
import com.liancheng.it.entity.pengpeng.OwnAnswer;
import com.liancheng.it.entity.pengpeng.Questions;

public interface PengPengDao {
	
	public void addQuestion(Map<String, Object> params);//添加问题
	public List<Questions> questionReport(Map<String, Object> params);//admin中的问题报表
	public int totalQuestions();//问题列表的总数
	
	public void saveAnswer(Map<String, Object> params);//添加问题的直接回答
	public Answer queryOneAnswer(Map<String, Object> params);//查询用户的回答
	public Questions randOneQuestion();//随机查询某条问题
	public List<AnswerUsers> queryAnswers(Map<String, Object> params);//查询某条问题的直接子回答
	public Questions queryQuestion(int ques_id);//查询某条问题
	public List<Questions> queryTypeQuestions(Map<String, Object> params);//查实名或匿名问题列表
	public void addChildAnsLaud(Map<String, Object> params);//直接子评论的点赞
	public void addChildAnswer(Map<String, Object> params);//添加问题的评论的评论
	public ChildAnswer queryIsLaud(Map<String, Object> params);//查询某个点赞用户
	public int countAnswerLaud(int ans_id);//统计碰碰某回答的点赞数
	public Answer queryCurrUserAnswer(Map<String, Object> params);//查询当前登录用户对某问题的回答
	
	public OwnAnswer queryOwnAnswer(Map<String, Object> params);//查出当前回答用户的个人信息和回答信息
	public List<ChildAnswer> queryOwnChildAnswers(Map<String, Object> params);//登录用户对已回答问题展示页的子评论列表实体类
	public List<ChildAnswer> queryOnlyChildAnswers(Map<String, Object> params);//仅双方可见的数据查询
	
	public Answer queryAnswerById(int ans_id);//查询评论的查看数量
	public void saveAnswerSee(Map<String, Object> params);//增加碰碰评论的查看数量
	
	public int countChildAnswer(Map<String, Object> params);//统计碰碰某评论的子评论数量
	
}
