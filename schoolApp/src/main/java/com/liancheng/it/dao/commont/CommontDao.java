package com.liancheng.it.dao.commont;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.liancheng.it.entity.commont.ChildCommont;
import com.liancheng.it.entity.commont.Commont;
import com.liancheng.it.entity.commont.LaudUser;

public interface CommontDao {
	
	public void addAction(Commont commont);//�������
	public int totalCommont(int active_user_id);//ĳ��˵˵����������
	public void addLaud(Commont commont);//����
	public int totalLaud(int active_user_id);//ĳ��˵˵�ĵ�������
	public int totalChildComLaud(String parent_user_id);//��ѯÿ�������۵ĵ�������
	public Commont queryIsLaud(Map<String, Object> params);//��ѯ�û�����
	public ChildCommont queryChildComIsLaud(Map<String, Object> params);//��ѯ�����۵��û��ĵ���
	public List<LaudUser> queryLauds(int active_user_id);//��ѯĳ��˵˵���޵��û�
	public List<Commont> queryComs(int active_user_id);//��ѯ˵˵������ֱ������
	
	public void addChildCom(ChildCommont childCom);//���������
	public List<ChildCommont> queryChildComms(Map<String, Object> params);//�������۵�������
	
	public int totalCom(int active_user_id);//˵˵��ֱ����������
	public int totalChildCom(Map<String, Object> params);//˵˵������������
	public void addChildComLaud(ChildCommont childCom);//��������۵ĵ���
	
	public int queryTotalComNoSee(String user_id);//��ѯĳ�û�ֱ�������۵�δ�鿴����
	public void batchModifyComNoSee(Map<String, Object> params);//����Ȧֱ�������۸ĳ��û��Ѳ鿴״̬
	public List<Commont> queryCommontsByActId(int active_user_id);//����act��id��ѯ������
	public void deleteCommsByActId(int active_user_id);//����act��idɾ��������
	public void deleteChildCommsByCommId(List<Integer> com_id);//ɾ�������۵�����
	public int deleteLaud(Map<String, Object> params);//ȡ������
	
}
