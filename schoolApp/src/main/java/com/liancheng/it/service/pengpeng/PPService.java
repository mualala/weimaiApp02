package com.liancheng.it.service.pengpeng;

import net.minidev.json.JSONObject;

public interface PPService {
	
	public JSONObject questionsReport(int pageSize, int pageNumber);//admin�еĺ�̨����
	public JSONObject addQuestion(String question);//��̨��������б�
	
	public JSONObject addAswer(String user_id, int ques_id, String content, String name_type);//��������ֱ��ֻ�ش�
	public JSONObject showMain(String user_id, int ques_id, int pageSize, 
			int pageNumber, String name_type, String gender, String hostPath);//����Ĭ����ҳ������
	public JSONObject questionList(String name_type, int pageSize, int pageNumber);//�����������б�
	public JSONObject addChildAnsLaud(String user_id, int ans_id);//ֱ�������۵ĵ���
	public JSONObject currUserAnswer(int ques_id, String user_id, int pageSize, 
			int pageNumber, String hostPath01);//ĳ��¼�û�������ش���չʾ�б�
	public JSONObject addChildAnswer(String user_id, int ans_id, String content);//�����������۵�����
	public JSONObject onlyChildAnswer(int ques_id, String own_user_id, String other_user_id, 
			int pageSize, int pageNumber, String hostPath01);//��˫���ɼ��������б�չʾ
	public JSONObject addAnswerSee(int ans_id);//�����������۵Ĳ鿴����
}
