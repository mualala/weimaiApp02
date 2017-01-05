package com.liancheng.it.dao.active;

import java.util.List;
import java.util.Map;

import com.liancheng.it.entity.active.Active;
import com.liancheng.it.entity.active.ActiveVerify;
import com.liancheng.it.entity.active.ClassActive;
import com.liancheng.it.entity.active.CycFriendsActvie;

public interface ActiveUserDao {
	
	public void addActive(Active active);//����̬
	public int queryActiveCounts(String user_id);//��ѯ�û���̬����
	public List<Active> queryOwnActive(Map<String, Object> params);//��ҳ��ѯ���˶�̬
	//��ҳ��ѯ��ҳ�����ȥ���ȫ���û���̬;Ҳ������ѯ��ҳ��̬homeActive,type_a=��ҳ��̬
	public List<ClassActive> queryClassActive(Map<String, Object> params);
	public List<CycFriendsActvie> queryFriendsActvie(Map<String, Object> params);//��ҳ��ѯ����Ȧ��̬
	public List<ClassActive> queryHomeActive(Map<String, Object> params);//��ѯ��ҳ��̬��ȫ���û�
	
	public Active queryActiveSee(int active_user_id);//�鿴˵˵�Ĳ鿴������
	public void updateSee(Active active);//����˵˵�Ĳ鿴״̬������ͳ�Ʋ鿴������
	public Active isSee();//�Ƿ�鿴
	
	public ClassActive queryActiveId(int active_user_id);//��ѯĳ��˵˵�Ķ�̬���ڴ�������
	
	
	public List<Active> queryAdmin(Map<String, Object> params);//��̬���ں�̨���
	public int queryAdminTotal();//��ѯδ��˶�̬��������
	
	public void oneActiveVerify(int active_user_id);//���ͨ��ĳһ��˵˵
	public void oneActiveNoVerify(int active_user_id);//��˲�ͨ��ĳһ��˵˵
	
	public List<ActiveVerify> activeVerifies(Map<String, Object> params);//����̬����֤�б�
	public int activeVerifiesTotal();//����̬����֤�б��������
	
	public void modifyStuVerify(Map<String, Object> params);//�޸�ѧ��֤��֤
	public void modifyCertiVerify(Map<String, Object> params);//�޸ı�ҵ֤��֤
	
	public ActiveVerify queryStuAndCertiVerify(String classActive);//��ѯ����̬�Ŀ���״̬
	public List<Active> queryKeyActive(String text);//��̬�Ĺؼ��ʼ���
	public Active queryDownCount(int active_user_id);//��ѯ��Դ�ļ������ش���
	public void addDownCount(Map<String, Object> params);//������Դ�ļ������ش���
	public Active queryOneActive(int active_user_id);//��ѯĳ����̬����������ҳ
	public int batchModifyAcvtive(List<Integer> ids);//�����޸Ķ�̬����֤״̬
	
	public List<Active> queryActForUserInfo(String user_id);//��ѯĳ�û��Ķ�̬���ڸ�����Ϣչʾҳ��
	
	public int queryTotalActs(String user_id);//��ѯĳ�û����������ͨ���Ķ�̬����
	
	
}
