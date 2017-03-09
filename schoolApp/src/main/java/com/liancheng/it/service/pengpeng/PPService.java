package com.liancheng.it.service.pengpeng;

import net.minidev.json.JSONObject;

public interface PPService {
	
	/**
	 * admin中的后台报表
	 * @param pageSize
	 * @param pageNumber
	 * @param searchText
	 * @param startDate
	 * @param endDate
	 * @param sortName
	 * @param sortOrder
	 * @return
	 */
	public JSONObject questionsReport(int pageSize, int pageNumber, String searchText, 
			String startDate, String endDate, String sortName, String sortOrder);
	
	/**
	 * 后台添加问题列表
	 * @param question
	 * @return
	 */
	public JSONObject addQuestion(String question);
	
	/**
	 * 碰碰添加直接子回答---1
	 * @param user_id
	 * @param ques_id
	 * @param content
	 * @param name_type
	 * @return
	 */
	public JSONObject addAswer(String user_id, int ques_id, String content, String name_type);
	
	/**
	 * 碰碰默认首页的数据
	 * @param user_id
	 * @param ques_id
	 * @param pageSize
	 * @param pageNumber
	 * @param name_type
	 * @param gender
	 * @param hostPath
	 * @return
	 */
	public JSONObject showMain(String user_id, int ques_id, int pageSize, 
			int pageNumber, String name_type, String gender, String hostPath);
	
	/**
	 * 碰碰的问题列表
	 * @param user_id
	 * @param name_type
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	public JSONObject questionList(String user_id, String name_type, int pageSize, int pageNumber);
	
	/**
	 * 直接子评论的点赞
	 * @param user_id
	 * @param ans_id
	 * @return
	 */
	public JSONObject addChildAnsLaud(String user_id, int ans_id);
	
	/**
	 * 某登录用户对问题回答后的展示列表
	 * @param ques_id
	 * @param user_id
	 * @param name_type
	 * @param pageSize
	 * @param pageNumber
	 * @param hostPath01
	 * @return
	 */
	public JSONObject currUserAnswer(int ques_id, String user_id, String name_type, int pageSize, 
			int pageNumber, String hostPath01);
	
	/**
	 * 碰碰添加问题的评论的评论---2
	 * @param user_id
	 * @param parent_user_id
	 * @param ans_id
	 * @param content
	 * @return
	 */
	public JSONObject addChildAnswer(String user_id, String parent_user_id, int ans_id, String content);
	
	/**
	 * 展示仅双方可见的评论列表
	 * @param ques_id
	 * @param own_user_id
	 * @param other_user_id
	 * @param name_type
	 * @param pageSize
	 * @param pageNumber
	 * @param hostPath01
	 * @return
	 */
	public JSONObject onlyChildAnswer(int ques_id, String own_user_id, String other_user_id, 
			String name_type, int pageSize, int pageNumber, String hostPath01);
	
	/**
	 * 碰碰增加回答的浏览量
	 * @param ans_id
	 * @return
	 */
	public JSONObject addAnswerSee(int ans_id);
	
	/**
	 * 再次在列表中在添加评论---3
	 * @param user_id
	 * @param parent_user_id
	 * @param child_ans_id
	 * @param content
	 * @return
	 */
	public JSONObject addTwoChildAns(String user_id, String parent_user_id, int child_ans_id, 
			String content);
	
	/**
	 * 批量删除碰碰的问题
	 * @param ids
	 * @return
	 */
	public JSONObject batchDelQuestions(String ids);
	
	
}
