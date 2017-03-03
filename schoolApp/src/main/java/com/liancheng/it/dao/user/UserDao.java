package com.liancheng.it.dao.user;

import java.util.List;
import java.util.Map;

import com.liancheng.it.entity.user.SeeControl;
import com.liancheng.it.entity.user.User;
import com.liancheng.it.entity.user.UserChart;
import com.liancheng.it.entity.user.Visitor;

public interface UserDao {
	
	public User findByPhoneNum(String phoneNum);
	public User findByUserId(String user_id);//����user_id��ѯ�û���Ϣ
	public User findBySchoolId(User user);//����У԰�Ų�ѯ�û���Ϣ
	
	public int save(User user);
	public void saveProfile(User user);
	public User findById(String user_id);
	public List<User> findByNickName(String user_nickname);//�����ǳƲ�ѯ�û���Ϣ
	
	public void deleteById(String user_id);
	
	public void editUser(User user);//�����û���Ϣ 
	public void editSkills();//�����û����ܱ�ǩ
	
	public String queryUserProfile(String user_id);//��ѯ�û�ͷ��
	public String queryUserNickName(String user_id);//��ѯ�û��ǳ�
	public void saveStuVerify(Map<String, Object> params);//�����û�ѧ��֤��֤��Ϣ
	public void saveCertiVerify(Map<String, Object> params);//�����û���ҵ֤��֤��Ϣ
	public List<User> queryAdminVerify(Map<String, Object> params);//���ں�̨��������֤��Ϣ��ѯ
	public int queryCountVerifies(Map<String, Object> params);//�����֤������
	public void modifyStuAdmin(Map<String, Object> params);//�޸�ѧ��֤��֤״̬
	public void modifyCertiAdmin(Map<String, Object> params);//�޸ı�ҵ֤��֤״̬
	
	public List<User> randPeoples(Map<String, Object> params);//�˺�������û�
	public List<User> filterPeoples(Map<String, Object> params);//�˺��û���ɸѡ
	public List<User> queryTypeUser(Map<String, Object> params);//����Ȧ�ķ����û��б�
	
	public List<User> queryCheckMaimai(String param);//����Ȧ����������
	
	public Visitor queryVisitorByUserId(Map<String, Object> params);//���ݷ����ߵ�uuid��ѯ�ÿ�
	public void saveVisitor(Map<String, Object> params);//����ÿ�
	public int totalVisitors(String user_id);//��ѯ�ÿ�����
	public List<Visitor> queryVisitorList(Map<String, Object> params);//��ѯ�ÿ͵��û��б�
	public List<User> queryUserList(Map<String, Object> params);//���ں�̨�û�����
	public int totalUserCount(Map<String, Object> params);//��ѯ�ж����û���
	public void ModifyPassword(Map<String, Object> params);//�޸��û�������
	public List<UserChart> queryChartControlYM();//home.htmlҳ��chart��ɸѡ���ֵ
	public List<UserChart> queryChartData(String selectDate);//home.htmlҳ��chart������
	public int queryAllNetPeople();//ȫ���û���
	public int totalNoVerifyUser();//����δ��˵��û�
	public void modifySendMsg(Map<String, Object> params);//�Ƿ���Է���Ϣ
	public void modifyAddSwitch(Map<String, Object> params);
	public void modifyLifeSee(Map<String, Object> params);
	
	public void saveTaNoSeeOwnActive(Map<String, Object> params);//���治�������Ҷ�̬�Ĺ�ϵ
	public SeeControl queryTaNoSeeOwnActive(Map<String, Object> params);//��ѯ���������Ҷ�̬�Ĺ�ϵ
	public void updateTaNoSeeOwnActive(Map<String, Object> params);//���²��������Ҷ�̬�Ĺ�ϵ
	
	public List<SeeControl> querySeeControlState(Map<String, Object> params);
	public void blockUser(Map<String, Object> params);//����/����û�
	
	
}
