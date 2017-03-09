package com.liancheng.it.service.pengpeng;

import net.minidev.json.JSONObject;

public interface PPService {
	
	/**
	 * admin�еĺ�̨����
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
	 * ��̨��������б�
	 * @param question
	 * @return
	 */
	public JSONObject addQuestion(String question);
	
	/**
	 * �������ֱ���ӻش�---1
	 * @param user_id
	 * @param ques_id
	 * @param content
	 * @param name_type
	 * @return
	 */
	public JSONObject addAswer(String user_id, int ques_id, String content, String name_type);
	
	/**
	 * ����Ĭ����ҳ������
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
	 * �����������б�
	 * @param user_id
	 * @param name_type
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	public JSONObject questionList(String user_id, String name_type, int pageSize, int pageNumber);
	
	/**
	 * ֱ�������۵ĵ���
	 * @param user_id
	 * @param ans_id
	 * @return
	 */
	public JSONObject addChildAnsLaud(String user_id, int ans_id);
	
	/**
	 * ĳ��¼�û�������ش���չʾ�б�
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
	 * ���������������۵�����---2
	 * @param user_id
	 * @param parent_user_id
	 * @param ans_id
	 * @param content
	 * @return
	 */
	public JSONObject addChildAnswer(String user_id, String parent_user_id, int ans_id, String content);
	
	/**
	 * չʾ��˫���ɼ��������б�
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
	 * �������ӻش�������
	 * @param ans_id
	 * @return
	 */
	public JSONObject addAnswerSee(int ans_id);
	
	/**
	 * �ٴ����б������������---3
	 * @param user_id
	 * @param parent_user_id
	 * @param child_ans_id
	 * @param content
	 * @return
	 */
	public JSONObject addTwoChildAns(String user_id, String parent_user_id, int child_ans_id, 
			String content);
	
	/**
	 * ����ɾ������������
	 * @param ids
	 * @return
	 */
	public JSONObject batchDelQuestions(String ids);
	
	
}
