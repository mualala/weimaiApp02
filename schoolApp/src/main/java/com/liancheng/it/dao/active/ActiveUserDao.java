package com.liancheng.it.dao.active;

import java.util.List;
import java.util.Map;

import com.liancheng.it.entity.active.Active;
import com.liancheng.it.entity.active.ActiveCategReport;
import com.liancheng.it.entity.active.ActiveVerify;
import com.liancheng.it.entity.active.ClassActive;
import com.liancheng.it.entity.active.CycFriendsActvie;
import com.liancheng.it.entity.active.Favorites;
import com.liancheng.it.entity.active.TextKey;
import com.liancheng.it.entity.admin.Admin;
import com.liancheng.it.entity.commont.Commont;

public interface ActiveUserDao {
	
	public void addActive(Active active);//����̬
	public int queryActiveCounts(String user_id);//��ѯ�û���̬����
	public List<Active> queryOwnActive(Map<String, Object> params);//��ҳ��ѯ���˶�̬
	public List<Active> queryOwnActiveAndSee(Map<String, Object> params);//��������fans���˿ɼ�����Ȧ
	public List<Active> queryOtherOneActive(Map<String, Object> params);//��¼�û���ѯ�����˵ĸ��˶�̬
	//��ҳ��ѯ��ҳ�����ȥ���ȫ���û���̬;Ҳ������ѯ��ҳ��̬homeActive,type_a=��ҳ��̬
	public List<ClassActive> queryClassActive(Map<String, Object> params);
	public List<CycFriendsActvie> queryFriendsActvie(Map<String, Object> params);//��ҳ��ѯ����Ȧ��̬
	public List<ClassActive> queryHomeActive(Map<String, Object> params);//��ѯ��ҳ��̬��ȫ���û�
	
	public Active queryActiveSee(int active_user_id);//�鿴˵˵�Ĳ鿴������
	public void updateSee(Active active);//����˵˵�Ĳ鿴״̬������ͳ�Ʋ鿴������
	public Active isSee();//�Ƿ�鿴
	
	public ClassActive queryActiveId(int active_user_id);//��ѯĳ��˵˵�Ķ�̬���ڴ�������
	
	
	public List<Active> queryAdmin(Map<String, Object> params);//��̬���ں�̨���
	public int queryAdminTotal(Map<String, Object> params);//��ѯδ��˶�̬��������
	
	public void oneActiveVerify(int active_user_id);//���ͨ��ĳһ��˵˵
	public void oneActiveNoVerify(int active_user_id);//��˲�ͨ��ĳһ��˵˵
	
	public List<ActiveVerify> activeVerifies(Map<String, Object> params);//����̬����֤�б�
	public List<ActiveVerify> allActiveVerifies();//��ȡ���е��������
	public List<ActiveVerify> allTwoActiveVerifies(String class_active);//
	public int activeVerifiesTotal();//����̬����֤�б��������
	
	public void modifyStuVerify(Map<String, Object> params);//�޸�ѧ��֤��֤
	public void modifyCertiVerify(Map<String, Object> params);//�޸ı�ҵ֤��֤
	
	public ActiveVerify queryStuAndCertiVerify(String classActive);//��ѯ����̬�Ŀ���״̬
	public List<Active> queryKeyActive(Map<String, Object> params);//��̬�Ĺؼ��ʼ���
	public Active queryDownCount(int active_user_id);//��ѯ��Դ�ļ������ش���
	public void addDownCount(Map<String, Object> params);//������Դ�ļ������ش���
	public Active queryOneActive(Map<String, Object> params);//��ѯĳ����̬����������ҳ
	public int batchModifyAcvtive(List<Integer> ids);//�����޸Ķ�̬����֤״̬
	
	public List<Active> queryActForUserInfo(String user_id);//��ѯĳ�û��Ķ�̬���ڸ�����Ϣչʾҳ��
	
	public int queryTotalActs(String user_id);//��ѯĳ�û����������ͨ���Ķ�̬����
	public Active queryActiveByActId(int active_user_id);//����ĳ����̬id��ѯ��̬����
	public void deleteOneActive(int active_user_id);//ɾ��ĳ����̬����
	
	public void saveTextKey(String text);//�����û�����������
	public List<TextKey> hotTextKey();//��̬�Ĺؼ�������
	public void saveFavorites(Map<String, Object> params);//�����¼�û��ղصĶ�̬
	public List<Active> queryFavoritesList(Map<String, Object> params);
	public void deleteOneFavor(int favor_id);//�û�ɾ����̬
	public Favorites queryOneFavor(Map<String, Object> params);//��ѯĳ�û���ĳ���ղ�
	public int batchDelThemeCateg(List<Integer> ids);//����ɾ���������
	public void saveThemeCateg(Map<String, Object> params);//����������
	public int queryAllNetActive();//ȫ����̬����
	public int totalNoVerifyActive();//��ѯ�ܵ�δ��˶�̬����
	public void saveTwoCateg(Map<String, Object> params);//��Ӷ�������
	public List<ActiveCategReport> queryActiveCategReport(Map<String, Object> params);//��ѯ��̬�ķ���ͳ�Ʊ���
	public int totalActiveCategReport(Map<String, Object> params);//��ѯ�ܵĶ�̬�ķ���ͳ�Ʊ���
	public CycFriendsActvie queryCurrFriendProfile(String user_id);//��ѯ����ж�̬������(ֻҪ�����һ������)
	public int queryByThemeName(String class_active);//�����������͵����Ʋ�ѯ�Ƿ����������
	public void updateThemeCateg(Map<String, Object> params);
}
