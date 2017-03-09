package com.liancheng.it.dao.active;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.liancheng.it.entity.active.Active;
import com.liancheng.it.entity.active.ActiveCategReport;
import com.liancheng.it.entity.active.ActiveVerify;
import com.liancheng.it.entity.active.ClassActive;
import com.liancheng.it.entity.active.CycFriendsActvie;
import com.liancheng.it.entity.active.Favorites;
import com.liancheng.it.entity.active.TextKey;

public interface ActiveUserDao {
	
	/**
	 * ����̬
	 * @param active
	 */
	public void addActive(Active active);
	
	/**
	 * ��ѯ�û���̬����
	 * @param user_id
	 * @return
	 */
	@Select("select count(*) from active where user_id=#{user_id}")
	public int queryActiveCounts(@Param("user_id") String user_id);
	
	/**
	 * ��ҳ��ѯ���˶�̬
	 * @param params
	 * @return
	 */
	public List<Active> queryOwnActive(Map<String, Object> params);
	
	/**
	 * ��������fans���˿ɼ�����Ȧ
	 * @param params
	 * @return
	 */
	public List<Active> queryOwnActiveAndSee(Map<String, Object> params);
	
	/**
	 * ��¼�û���ѯ�����˵ĸ��˶�̬
	 * @param params
	 * @return
	 */
	public List<Active> queryOtherOneActive(Map<String, Object> params);
	
	/**
	 * ��ҳ��ѯ��ҳ�����ȥ���ȫ���û���̬;Ҳ������ѯ��ҳ��̬homeActive,type_a=��ҳ��̬
	 * @param params
	 * @return
	 */
	public List<ClassActive> queryClassActive(Map<String, Object> params);
	
	/**
	 * ��ҳ��ѯ����Ȧ��̬
	 * @param params
	 * @return
	 */
	public List<CycFriendsActvie> queryFriendsActvie(Map<String, Object> params);
	
	/**
	 * ��ѯ��ҳ��̬��ȫ���û�
	 * @param params
	 * @return
	 */
	public List<ClassActive> queryHomeActive(Map<String, Object> params);
	
	/**
	 * �鿴˵˵�Ĳ鿴������
	 * @param active_user_id
	 * @return
	 */
	@Select("select see from active where active_user_id=#{active_user_id}")
	public Active queryActiveSee(@Param("active_user_id") int active_user_id);
	
	/**
	 * ����˵˵�Ĳ鿴״̬������ͳ�Ʋ鿴������
	 * @param active
	 */
	public void updateSee(Active active);
	
	/**
	 * �Ƿ�鿴
	 * @return
	 */
	public Active isSee();
	
	/**
	 * ��ѯĳ��˵˵�Ķ�̬���ڴ�������
	 * @param active_user_id
	 * @return
	 */
	@Select("select * from class_active where active_user_id=#{active_user_id}")
	public ClassActive queryActiveId(int active_user_id);

	/**
	 * ��̬���ں�̨���
	 * @param params
	 * @return
	 */
	public List<Active> queryAdmin(Map<String, Object> params);
	
	/**
	 * ��ѯδ��˶�̬��������
	 * @param params
	 * @return
	 */
	public int queryAdminTotal(Map<String, Object> params);
	
	/**
	 * ���ͨ��ĳһ��˵˵
	 * @param active_user_id
	 */
	@Update("update active set state='1' where active_user_id=#{active_user_id}")
	public void oneActiveVerify(int active_user_id);
	
	/**
	 * ��˲�ͨ��ĳһ��˵˵
	 * @param active_user_id
	 */
	public void oneActiveNoVerify(int active_user_id);
	
	/**
	 * ����̬����֤�б�
	 * @param params
	 * @return
	 */
	public List<ActiveVerify> activeVerifies(Map<String, Object> params);
	
	/**
	 * ��ȡ���е��������
	 * @return
	 */
	public List<ActiveVerify> allActiveVerifies();
	
	/**
	 * һ����̬��������ж�������
	 * @param class_active
	 * @return
	 */
	public List<ActiveVerify> allTwoActiveVerifies(String class_active);
	
	/**
	 * ����̬����֤�б��������
	 * @return
	 */
	public int activeVerifiesTotal();
	
	/**
	 * �޸�ѧ��֤��֤
	 * @param params
	 */
	public void modifyStuVerify(Map<String, Object> params);
	
	/**
	 * �޸ı�ҵ֤��֤
	 * @param params
	 */
	public void modifyCertiVerify(Map<String, Object> params);
	
	/**
	 * ��ѯ����̬�Ŀ���״̬
	 * @param classActive
	 * @return
	 */
	public ActiveVerify queryStuAndCertiVerify(String classActive);
	
	/**
	 * ��̬�Ĺؼ��ʼ���
	 * @param params
	 * @return
	 */
	public List<Active> queryKeyActive(Map<String, Object> params);
	
	/**
	 * ��ѯ��Դ�ļ������ش���
	 * @param active_user_id
	 * @return
	 */
	public Active queryDownCount(int active_user_id);
	
	/**
	 * ������Դ�ļ������ش���
	 * @param params
	 */
	public void addDownCount(Map<String, Object> params);
	
	/**
	 * ��ѯĳ����̬����������ҳ
	 * @param params
	 * @return
	 */
	public Active queryOneActive(Map<String, Object> params);
	
	/**
	 * �����޸Ķ�̬����֤״̬
	 * @param ids
	 * @return
	 */
	public int batchModifyAcvtive(List<Integer> ids);
	
	/**
	 * ��ѯĳ�û��Ķ�̬���ڸ�����Ϣչʾҳ��
	 * @param user_id
	 * @return
	 */
	public List<Active> queryActForUserInfo(String user_id);
	
	/**
	 * ��ѯĳ�û����������ͨ���Ķ�̬����
	 * @param user_id
	 * @return
	 */
	public int queryTotalActs(String user_id);
	
	/**
	 * ����ĳ����̬id��ѯ��̬����
	 * @param active_user_id
	 * @return
	 */
	public Active queryActiveByActId(int active_user_id);
	
	/**
	 * ɾ��ĳ����̬����
	 * @param active_user_id
	 */
	public void deleteOneActive(int active_user_id);
	
	/**
	 * �����û�����������
	 * @param text
	 */
	public void saveTextKey(String text);
	
	/**
	 * ��̬�Ĺؼ�������
	 * @return
	 */
	public List<TextKey> hotTextKey();
	
	/**
	 * �����¼�û��ղصĶ�̬
	 * @param params
	 */
	public void saveFavorites(Map<String, Object> params);
	
	/**
	 * ��ѯ�ղ��б�
	 * @param params
	 * @return
	 */
	public List<Active> queryFavoritesList(Map<String, Object> params);
	
	/**
	 * �û�ɾ����̬
	 * @param favor_id
	 */
	public void deleteOneFavor(int favor_id);
	
	/**
	 * ��ѯĳ�û���ĳ���ղ�
	 * @param params
	 * @return
	 */
	public Favorites queryOneFavor(Map<String, Object> params);
	
	/**
	 * ����ɾ���������
	 * @param ids
	 * @return
	 */
	public int batchDelThemeCateg(List<Integer> ids);
	
	/**
	 * ����������
	 * @param params
	 */
	public void saveThemeCateg(Map<String, Object> params);
	
	/**
	 * ȫ����̬����
	 * @return
	 */
	public int queryAllNetActive();
	
	/**
	 * ��ѯ�ܵ�δ��˶�̬����
	 * @return
	 */
	public int totalNoVerifyActive();
	
	/**
	 * ��Ӷ�������
	 * @param params
	 */
	public void saveTwoCateg(Map<String, Object> params);
	
	/**
	 * ��ѯ��̬�ķ���ͳ�Ʊ���
	 * @param params
	 * @return
	 */
	public List<ActiveCategReport> queryActiveCategReport(Map<String, Object> params);
	
	/**
	 * ��ѯ�ܵĶ�̬�ķ���ͳ�Ʊ���
	 * @param params
	 * @return
	 */
	public int totalActiveCategReport(Map<String, Object> params);
	
	/**
	 * ��ѯ����ж�̬������(ֻҪ�����һ������)
	 * @param user_id
	 * @return
	 */
	public CycFriendsActvie queryCurrFriendProfile(String user_id);
	
	/**
	 * �����������͵����Ʋ�ѯ�Ƿ����������
	 * @param class_active
	 * @return
	 */
	public int queryByThemeName(String class_active);
	
	/**
	 * ��̨�����������
	 * @param params
	 */
	public void updateThemeCateg(Map<String, Object> params);
	
	/**
	 * ��ѯһ����������ж�������
	 * @param class_active
	 * @return
	 */
	public List<ActiveVerify> queryOneThemeHaveTwoClass(String class_active);
	
	/**
	 * �������
	 * @return
	 */
	public List<ActiveVerify> themeCateg();
	
	/**
	 * ��������
	 * @param themeCateg
	 * @return
	 */
	public List<ActiveVerify> twoCateg(String themeCateg);
	
	/**
	 * ��ѯ��̬����ϸ����
	 * @param params
	 * @return
	 */
	public List<Active> detailActiveRepoet(Map<String, Object> params);
	
	/**
	 * ��ϸ��̬������ܵ�����
	 * @param params
	 * @return
	 */
	public int totalDetailActiveRepoet(Map<String, Object> params);
	
	
}
