package com.liancheng.it.dao.commont;

import java.util.List;
import java.util.Map;

import com.liancheng.it.entity.commont.ChildCommont;
import com.liancheng.it.entity.commont.Commont;
import com.liancheng.it.entity.commont.LaudUser;

public interface CommontDao {
	
	/**
	 * �������
	 * @param commont
	 */
	public void addAction(Commont commont);
	
	/**
	 * ĳ��˵˵����������
	 * @param active_user_id
	 * @return
	 */
	public int totalCommont(int active_user_id);
	
	/**
	 * ����
	 * @param commont
	 */
	public void addLaud(Commont commont);
	
	/**
	 * ĳ��˵˵�ĵ�������
	 * @param active_user_id
	 * @return
	 */
	public int totalLaud(int active_user_id);
	
	/**
	 * ��ѯÿ�������۵ĵ�������
	 * @param parent_user_id
	 * @return
	 */
	public int totalChildComLaud(String parent_user_id);
	
	/**
	 * ��ѯ�û�����
	 * @param params
	 * @return
	 */
	public Commont queryIsLaud(Map<String, Object> params);
	
	/**
	 * ��ѯ�����۵��û��ĵ���
	 * @param params
	 * @return
	 */
	public ChildCommont queryChildComIsLaud(Map<String, Object> params);
	
	/**
	 * ��ѯĳ��˵˵���޵��û�
	 * @param active_user_id
	 * @return
	 */
	public List<LaudUser> queryLauds(int active_user_id);
	
	/**
	 * ��ѯ˵˵������ֱ������
	 * @param active_user_id
	 * @return
	 */
	public List<Commont> queryComs(int active_user_id);
	
	/**
	 * ���������
	 * @param childCom
	 */
	public void addChildCom(ChildCommont childCom);
	
	/**
	 * �������۵�������
	 * @param params
	 * @return
	 */
	public List<ChildCommont> queryChildComms(Map<String, Object> params);
	
	/**
	 * ˵˵��ֱ����������
	 * @param active_user_id
	 * @return
	 */
	public int totalCom(int active_user_id);
	
	/**
	 * ˵˵������������
	 * @param params
	 * @return
	 */
	public int totalChildCom(Map<String, Object> params);
	
	/**
	 * ��������۵ĵ���
	 * @param childCom
	 */
	public void addChildComLaud(ChildCommont childCom);
	
	/**
	 * ��ѯĳ�û�ֱ�������۵�δ�鿴����
	 * @param user_id
	 * @return
	 */
	public int queryTotalComNoSee(String user_id);
	
	/**
	 * ����Ȧֱ�������۸ĳ��û��Ѳ鿴״̬
	 * @param params
	 */
	public void batchModifyComNoSee(Map<String, Object> params);
	
	/**
	 * ����act��id��ѯ������
	 * @param active_user_id
	 * @return
	 */
	public List<Commont> queryCommontsByActId(int active_user_id);
	
	/**
	 * ����act��idɾ��������
	 * @param active_user_id
	 */
	public void deleteCommsByActId(int active_user_id);
	
	/**
	 * ɾ�������۵�����
	 * @param com_id
	 */
	public void deleteChildCommsByCommId(List<Integer> com_id);
	
	/**
	 * ȡ������
	 * @param params
	 * @return
	 */
	public int deleteLaud(Map<String, Object> params);
	
	
	
}
