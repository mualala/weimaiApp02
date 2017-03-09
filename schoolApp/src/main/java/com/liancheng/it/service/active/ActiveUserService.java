package com.liancheng.it.service.active;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.liancheng.it.entity.active.ThemeCateg;

import net.minidev.json.JSONObject;

public interface ActiveUserService {
	
	/**
	 * �û�����̬
	 * @param pics
	 * @param docum
	 * @param user_id
	 * @param type_a
	 * @param type_b
	 * @param saysay
	 * @param title
	 * @param position
	 * @param localBasePath
	 * @return
	 */
	public JSONObject addActive(MultipartFile[] pics, MultipartFile[] docum, 
			String user_id, String type_a, String type_b, String saysay, String title, 
			String position, String localBasePath);
	
	/**
	 * չʾ���˶�̬
	 * @param user_id
	 * @param v_user_id
	 * @param pageSize
	 * @param pageNumber
	 * @param hostPath01
	 * @param hostPath02
	 * @return
	 */
	public JSONObject showOwnActive(String user_id,String v_user_id, int pageSize, 
			int pageNumber, String hostPath01, String hostPath02);
	
	/**
	 * ��ҳ����Ķ�̬չʾ,����ҳ��̬��չʾ
	 * @param user_id
	 * @param type_a
	 * @param type_b
	 * @param pageSize
	 * @param pageNumber
	 * @param hostPath01
	 * @param hostPath02
	 * @return
	 */
	public JSONObject showClassActive(String user_id, String type_a, String type_b, 
			int pageSize, int pageNumber, 
			String hostPath01, String hostPath02);
	
	/**
	 * չʾ����Ȧ��̬
	 * @param user_id
	 * @param pageSize
	 * @param pageNumber
	 * @param hostPath01
	 * @param hostPath02
	 * @return
	 */
	public JSONObject showFriendsActvie(String user_id, int pageSize, int pageNumber, 
			String hostPath01, String hostPath02);
	
	/**
	 * ˵˵��Ӳ鿴����
	 * @param active_user_id
	 * @return
	 */
	public JSONObject addSeeActive(int active_user_id);
	
	/**
	 * ��˵˵�����
	 * @param pageSize
	 * @param pageNumber
	 * @param searchText
	 * @param startDate
	 * @param endDate
	 * @param sortName
	 * @param sortOrder
	 * @param hostPath
	 * @return
	 */
	public JSONObject adminActive(int pageSize, int pageNumber, String searchText, 
			String startDate, String endDate, String sortName, String sortOrder, String hostPath);
	
	/**
	 * ��̬���ͨ��
	 * @param active_user_id
	 * @return
	 */
	public JSONObject verifyActive(int active_user_id);
	
	/**
	 * ��̬��˲�ͨ��
	 * @param active_user_id
	 * @return
	 */
	public JSONObject noVerifyActive(int active_user_id);
	
	/**
	 * ��̨�Է�˵˵��Ȩ����֤�б�
	 * @param pageSize
	 * @param pageNumber
	 * @param searchText
	 * @param sortName
	 * @param sortOrder
	 * @param hostPath02
	 * @return
	 */
	public JSONObject authActive(int pageSize, int pageNumber, String searchText, 
			String sortName, String sortOrder, String hostPath02);
	
	/**
	 * ��ȡȫ�����������
	 * @param hostPath03
	 * @return
	 */
	public List<ThemeCateg> allThemeCateg(String hostPath03);
	
	/**
	 * ���ķ���̬��Ȩ��
	 * @param state �Ƿ�������̬��֤,0��1
	 * @param class_active ��̬�ķ���
	 * @param verify ��ѧ�����Ǳ�ҵ֤��֤
	 * @return
	 */
	public JSONObject modifyVerify(String state, String class_active, String verify);
	
	/**
	 * ��̬��ȫ�ļ�������
	 * @param text
	 * @param hostPath01
	 * @param hostPath02
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	public JSONObject keyActive(String text, String hostPath01, String hostPath02, 
			int pageSize, int pageNumber);
	
	/**
	 * �������淢��̬����֤Ϊ1��״̬
	 * @param ids
	 * @return
	 */
	public JSONObject batchDoVerify(String ids);
	
	/**
	 * ɾ����̬
	 * @param active_user_id
	 * @param user_id
	 * @return
	 */
	public JSONObject deleteOneActive(int active_user_id, String user_id);
	
	/**
	 * ��̬�����Ĺؼ�������ǰ7��
	 * @return
	 */
	public JSONObject rankActiveKey();
	
	/**
	 * �û���Ӷ�̬���ղ�
	 * @param active_user_id
	 * @param user_id
	 * @return
	 */
	public JSONObject addFavor(int active_user_id, String user_id);
	
	/**
	 * �û��Ķ�̬�ղ��б�
	 * @param user_id
	 * @param pageSize
	 * @param pageNumber
	 * @param hostPath01
	 * @param hostPath02
	 * @return
	 */
	public JSONObject favorList(String user_id, int pageSize, int pageNumber, 
			String hostPath01, String hostPath02);
	
	/**
	 * ɾ��ĳ���ղصĶ�̬
	 * @param favor_id
	 * @return
	 */
	public JSONObject deleteOneFavor(int favor_id);
	
	/**
	 * ����ɾ���������
	 * @param ids
	 * @return
	 */
	public JSONObject batchDelThemeCateg(String ids);
	
	/**
	 * ����������
	 * @param themePic
	 * @param themeCateg
	 * @param localBasePath
	 * @return
	 */
	public JSONObject attachThemeCateg(MultipartFile themePic, String themeCateg, String localBasePath);
	
	/**
	 * ��̨��Ӷ�̬�Ķ�������
	 * @param twoPic
	 * @param themeCateg
	 * @param twoCateg
	 * @param localBasePath
	 * @return
	 */
	public JSONObject attachActiveTwoCateg(MultipartFile twoPic, String themeCateg, String twoCateg, String localBasePath);
	
	/**
	 * ��̬���������ı���
	 * @param pageSize
	 * @param pageNumber
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public JSONObject activeCategReport(int pageSize, int pageNumber, 
			String startDate, String endDate);
	
	/**
	 * �����������õ�ĳ�����µĶ�������
	 * @param class_active
	 * @return
	 */
	public JSONObject oneThemeTwoClass(String class_active);
	
	/**
	 * �������
	 * @return
	 */
	public JSONObject themeCateg();
	
	/**
	 * ��������
	 * @param themeCateg
	 * @return
	 */
	public JSONObject twoCategSelect(String themeCateg);
	
	/**
	 * ��̨�Ķ�̬��ϸ����
	 * @param searchText
	 * @param startDate
	 * @param endDate
	 * @param themeClass
	 * @param twoClass
	 * @param state
	 * @param sortName
	 * @param sortOrder
	 * @param pageSize
	 * @param pageNumber
	 * @param hostPath02
	 * @return
	 */
	public JSONObject detailActiveRepoet(String searchText, String startDate, String endDate, 
			String themeClass, String twoClass, String state, String sortName, String sortOrder, int pageSize, 
			int pageNumber, String hostPath02);
	
	
	
}
