package com.liancheng.it.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.liancheng.it.entity.user.SeeControl;
import com.liancheng.it.entity.user.User;
import com.liancheng.it.entity.user.UserChart;
import com.liancheng.it.entity.user.Visitor;

public interface UserDao {
	
	/**
	 * ͨ���绰�����ѯ�û�
	 * @param phoneNum
	 * @return
	 */
	@Select("select * from user	where phoneNum=#{phoneNum}")
	public User findByPhoneNum(@Param("phoneNum") String phoneNum);
	
	/**
	 * ����user_id��ѯ�û���Ϣ
	 * @param user_id
	 * @return
	 */
	public User findByUserId(String user_id);
	
	/**
	 * ����У԰�Ų�ѯ�û���Ϣ
	 * @param user
	 * @return
	 */
	public User findBySchoolId(User user);
	
	/**
	 * �����û�
	 * @param user
	 * @return
	 */
	public int save(User user);
	
	/**
	 * ����/�����û�ͷ��
	 * @param user
	 */
	public void saveProfile(User user);
	
	/**
	 * �����û�id��ѯ�û�
	 * @param user_id
	 * @return
	 */
	public User findById(String user_id);
	
	/**
	 * �����ǳƲ�ѯ�û���Ϣ
	 * @param user_nickname
	 * @return
	 */
	public List<User> findByNickName(String user_nickname);
	
	/**
	 * �����û�idɾ���û�
	 * @param user_id
	 */
	public void deleteById(String user_id);
	
	/**
	 * �����û���Ϣ 
	 * @param user
	 */
	public void editUser(User user);
	
	/**
	 * ��ѯ�û�ͷ��
	 * @param user_id
	 * @return
	 */
	public String queryUserProfile(String user_id);
	
	/**
	 * ��ѯ�û��ǳ�
	 * @param user_id
	 * @return
	 */
	public String queryUserNickName(String user_id);
	
	/**
	 * �����û�ѧ��֤��֤��Ϣ
	 * @param params
	 */
	public void saveStuVerify(Map<String, Object> params);
	
	/**
	 * �����û���ҵ֤��֤��Ϣ
	 * @param params
	 */
	public void saveCertiVerify(Map<String, Object> params);
	
	/**
	 * ���ں�̨��������֤��Ϣ��ѯ
	 * @param params
	 * @return
	 */
	public List<User> queryAdminVerify(Map<String, Object> params);
	
	/**
	 * �����֤������
	 * @param params
	 * @return
	 */
	public int queryCountVerifies(Map<String, Object> params);
	
	/**
	 * �޸�ѧ��֤��֤״̬
	 * @param params
	 */
	public void modifyStuAdmin(Map<String, Object> params);
	
	/**
	 * �޸ı�ҵ֤��֤״̬
	 * @param params
	 */
	public void modifyCertiAdmin(Map<String, Object> params);
	
	/**
	 * �˺�������û�
	 * @param params
	 * @return
	 */
	public List<User> randPeoples(Map<String, Object> params);
	
	/**
	 * �˺��û���ɸѡ
	 * @param params
	 * @return
	 */
	public List<User> filterPeoples(Map<String, Object> params);
	
	/**
	 * ����Ȧ�ķ����û��б�
	 * @param params
	 * @return
	 */
	public List<User> queryTypeUser(Map<String, Object> params);
	
	/**
	 * ����Ȧ����������
	 * @param param
	 * @return
	 */
	public List<User> queryCheckMaimai(String param);
	
	/**
	 * ���ݷ����ߵ�uuid��ѯ�ÿ�
	 * @param params
	 * @return
	 */
	public Visitor queryVisitorByUserId(Map<String, Object> params);
	
	/**
	 * ����ÿ�
	 * @param params
	 */
	public void saveVisitor(Map<String, Object> params);
	
	/**
	 * ��ѯ�ÿ�����
	 * @param user_id
	 * @return
	 */
	public int totalVisitors(String user_id);
	
	/**
	 * ��ѯ�ÿ͵��û��б�
	 * @param params
	 * @return
	 */
	public List<Visitor> queryVisitorList(Map<String, Object> params);
	
	/**
	 * ���ں�̨�û�����
	 * @param params
	 * @return
	 */
	public List<User> queryUserList(Map<String, Object> params);
	
	/**
	 * ��ѯ�ж����û���
	 * @param params
	 * @return
	 */
	public int totalUserCount(Map<String, Object> params);
	
	/**
	 * �޸��û�������
	 * @param params
	 */
	public void ModifyPassword(Map<String, Object> params);
	
	/**
	 * home.htmlҳ��chart��ɸѡ���ֵ
	 * @return
	 */
	public List<UserChart> queryChartControlYM();
	
	/**
	 * home.htmlҳ��chart������
	 * @param selectDate
	 * @return
	 */
	public List<UserChart> queryChartData(String selectDate);
	
	/**
	 * ȫ���û���
	 * @return
	 */
	public int queryAllNetPeople();
	
	/**
	 * ����δ��˵��û�
	 * @return
	 */
	public int totalNoVerifyUser();
	
	/**
	 * �Ƿ���Է���Ϣ
	 * @param params
	 */
	public void modifySendMsg(Map<String, Object> params);
	
	/**
	 * �û��������ԵĿ���
	 * @param params
	 */
	public void modifyAddSwitch(Map<String, Object> params);
	
	/**
	 * �޸�����Ȧ�Ƿ�ɿ��Ŀ���
	 * @param params
	 */
	public void modifyLifeSee(Map<String, Object> params);
	
	/**
	 * ���治�������Ҷ�̬�Ĺ�ϵ
	 * @param params
	 */
	public void saveTaNoSeeOwnActive(Map<String, Object> params);
	
	/**
	 * ��ѯ���������Ҷ�̬�Ĺ�ϵ
	 * @param params
	 * @return
	 */
	public SeeControl queryTaNoSeeOwnActive(Map<String, Object> params);
	
	/**
	 * ���²��������Ҷ�̬�Ĺ�ϵ
	 * @param params
	 */
	public void updateTaNoSeeOwnActive(Map<String, Object> params);
	
	/**
	 * ��ѯ�ɿ��������˿ռ���û��б�
	 * @param params
	 * @return
	 */
	public List<SeeControl> querySeeControlState(Map<String, Object> params);
	
	/**
	 * ����/����û�
	 * @param params
	 */
	public void blockUser(Map<String, Object> params);
	
	
}
