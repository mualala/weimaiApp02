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
	 * �������
	 * @param params
	 */
	public void addQuestion(Map<String, Object> params);
	
	/**
	 * admin�е����ⱨ��
	 * @param params
	 * @return
	 */
	public List<Questions> questionReport(Map<String, Object> params);
	
	/**
	 * �����б������
	 * @param params
	 * @return
	 */
	public int totalQuestions(Map<String, Object> params);
	
	/**
	 * ��������ֱ�ӻش�---1
	 * @param params
	 */
	public void saveAnswer(Map<String, Object> params);
	
	/**
	 * ��ѯ�û��Ļش�
	 * @param params
	 * @return
	 */
	public Answer queryOneAnswer(Map<String, Object> params);
	
	/**
	 * �����ѯĳ������
	 * @return
	 */
	public Questions randOneQuestion();
	
	/**
	 * ��ѯĳ�������ֱ���ӻش�
	 * @param params
	 * @return
	 */
	public List<AnswerUsers> queryAnswers(Map<String, Object> params);
	
	/**
	 * ��ѯĳ������
	 * @param ques_id
	 * @return
	 */
	public Questions queryQuestion(int ques_id);
	
	/**
	 * ��ʵ�������������б�
	 * @param params
	 * @return
	 */
	public List<Questions> queryTypeQuestions(Map<String, Object> params);
	
	/**
	 * ֱ�������۵ĵ���
	 * @param params
	 */
	public void addChildAnsLaud(Map<String, Object> params);
	
	/**
	 * �����������۵�����---2
	 * @param params
	 */
	public void addChildAnswer(Map<String, Object> params);
	
	/**
	 * ��ѯĳ�������û�
	 * @param params
	 * @return
	 */
	public ChildAnswer queryIsLaud(Map<String, Object> params);
	
	/**
	 * ͳ������ĳ�ش�ĵ�����
	 * @param ans_id
	 * @return
	 */
	public int countAnswerLaud(int ans_id);
	
	/**
	 * ��ѯ��ǰ��¼�û���ĳ����Ļش�
	 * @param params
	 * @return
	 */
	public Answer queryCurrUserAnswer(Map<String, Object> params);
	
	/**
	 * �����ǰ�ش��û��ĸ�����Ϣ�ͻش���Ϣ
	 * @param params
	 * @return
	 */
	public OwnAnswer queryOwnAnswer(Map<String, Object> params);
	
	/**
	 * ��¼�û����ѻش�����չʾҳ���������б�ʵ����
	 * @param params
	 * @return
	 */
	public List<ChildAnswer> queryOwnChildAnswers(Map<String, Object> params);
	
	/**
	 * ��˫���ɼ������ݲ�ѯ
	 * @param params
	 * @return
	 */
	public List<ChildAnswer> queryOnlyChildAnswers(Map<String, Object> params);
	
	/**
	 * ��ѯ���۵Ĳ鿴����
	 * @param ans_id
	 * @return
	 */
	public Answer queryAnswerById(int ans_id);
	
	/**
	 * �����������۵Ĳ鿴����
	 * @param params
	 */
	public void saveAnswerSee(Map<String, Object> params);
	
	/**
	 * ͳ������ĳ���۵�����������
	 * @param params
	 * @return
	 */
	public int countChildAnswer(Map<String, Object> params);
	
	/**
	 * ��ѯ��¼�û��Ƿ�ش��
	 * @param params
	 * @return
	 */
	public Answer queryUserIsAns(Map<String, Object> params);
	
	/**
	 * �ٴ���ӻش�Ļش�---3
	 * @param params
	 */
	public void addTwoChildAns(Map<String, Object> params);
	
	/**
	 * ��ѯ�������������
	 * @param child_ans_id
	 * @return
	 */
	public List<TwoChildAnswer> queryTwoChildAns(int child_ans_id);
	
	/**
	 * ��ѯ�������������,�����ѿɼ�
	 * @param params
	 * @return
	 */
	public List<TwoChildAnswer> queryOnlyTwoChildAns(Map<String, Object> params);
	
	/**
	 * ����ɾ������
	 * @param ids
	 * @return
	 */
	public int batchDelQuestions(List<Integer> ids);
	
	
}
