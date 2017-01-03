package com.liancheng.it.dao.user;

import java.util.List;
import java.util.Map;

import com.liancheng.it.entity.user.User;
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
	public int queryCountVerifies();//�����֤������
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
	
}
