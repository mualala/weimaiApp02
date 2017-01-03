package com.liancheng.it.dao.pengpeng;

import java.util.List;
import java.util.Map;

import com.liancheng.it.entity.pengpeng.Answer;
import com.liancheng.it.entity.pengpeng.AnswerUsers;
import com.liancheng.it.entity.pengpeng.ChildAnswer;
import com.liancheng.it.entity.pengpeng.OwnAnswer;
import com.liancheng.it.entity.pengpeng.Questions;

public interface PengPengDao {
	
	public void addQuestion(Map<String, Object> params);//�������
	public List<Questions> questionReport(Map<String, Object> params);//admin�е����ⱨ��
	public int totalQuestions();//�����б������
	
	public void saveAnswer(Map<String, Object> params);//��������ֱ�ӻش�
	public Answer queryOneAnswer(Map<String, Object> params);//��ѯ�û��Ļش�
	public Questions randOneQuestion();//�����ѯĳ������
	public List<AnswerUsers> queryAnswers(Map<String, Object> params);//��ѯĳ�������ֱ���ӻش�
	public Questions queryQuestion(int ques_id);//��ѯĳ������
	public List<Questions> queryTypeQuestions(Map<String, Object> params);//��ʵ�������������б�
	public void addChildAnsLaud(Map<String, Object> params);//ֱ�������۵ĵ���
	public void addChildAnswer(Map<String, Object> params);//�����������۵�����
	public ChildAnswer queryIsLaud(Map<String, Object> params);//��ѯĳ�������û�
	public int countAnswerLaud(int ans_id);//ͳ������ĳ�ش�ĵ�����
	public Answer queryCurrUserAnswer(Map<String, Object> params);//��ѯ��ǰ��¼�û���ĳ����Ļش�
	
	public OwnAnswer queryOwnAnswer(Map<String, Object> params);//�����ǰ�ش��û��ĸ�����Ϣ�ͻش���Ϣ
	public List<ChildAnswer> queryOwnChildAnswers(Map<String, Object> params);//��¼�û����ѻش�����չʾҳ���������б�ʵ����
	public List<ChildAnswer> queryOnlyChildAnswers(Map<String, Object> params);//��˫���ɼ������ݲ�ѯ
	
	public Answer queryAnswerById(int ans_id);//��ѯ���۵Ĳ鿴����
	public void saveAnswerSee(Map<String, Object> params);//�����������۵Ĳ鿴����
	
	public int countChildAnswer(Map<String, Object> params);//ͳ������ĳ���۵�����������
	
}
