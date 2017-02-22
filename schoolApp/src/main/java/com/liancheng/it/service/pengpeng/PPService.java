package com.liancheng.it.service.pengpeng;

import net.minidev.json.JSONObject;

public interface PPService {
	
	public JSONObject questionsReport(int pageSize, int pageNumber, String searchText, 
			String startDate, String endDate, String sortName, String sortOrder);//admin中的后台报表
	public JSONObject addQuestion(String question);//后台添加问题列表
	
	public JSONObject addAswer(String user_id, int ques_id, String content, String name_type);//添加问题的直接只回答---1
	public JSONObject showMain(String user_id, int ques_id, int pageSize, 
			int pageNumber, String name_type, String gender, String hostPath);//碰碰默认首页的数据
	public JSONObject questionList(String user_id, String name_type, int pageSize, int pageNumber);//碰碰的问题列表
	public JSONObject addChildAnsLaud(String user_id, int ans_id);//直接子评论的点赞
	public JSONObject currUserAnswer(int ques_id, String user_id, String name_type, int pageSize, 
			int pageNumber, String hostPath01);//某登录用户对问题回答后的展示列表
	public JSONObject addChildAnswer(String user_id, String parent_user_id, int ans_id, String content);//添加问题的评论的评论---2
	public JSONObject onlyChildAnswer(int ques_id, String own_user_id, String other_user_id, 
			String name_type, int pageSize, int pageNumber, String hostPath01);//仅双方可见的评论列表展示
	public JSONObject addAnswerSee(int ans_id);//增加碰碰评论的查看数量
	public JSONObject addTwoChildAns(String user_id, String parent_user_id, int child_ans_id, 
			String content);//再添加回答的回答---3
	public JSONObject batchDelQuestions(String ids);//批量删除碰碰的问题
	
}
