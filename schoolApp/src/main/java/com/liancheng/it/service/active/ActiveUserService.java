package com.liancheng.it.service.active;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.liancheng.it.entity.active.ActiveVerify;
import com.liancheng.it.entity.active.ThemeCateg;

import net.minidev.json.JSONObject;

public interface ActiveUserService {
	
	public JSONObject addActive(MultipartFile[] pics, MultipartFile[] docum, 
			String user_id, String type_a, String type_b, String saysay, String title, 
			String position, String localBasePath);//�û���Ӷ�̬
	public JSONObject showOwnActive(String user_id,String v_user_id, int pageSize, 
			int pageNumber, String hostPath01, String hostPath02);//�鿴���˶�̬
	public JSONObject showClassActive(String user_id, String type_a, String type_b, 
			int pageSize, int pageNumber, 
			String hostPath01, String hostPath02);//��ҳ����Ķ�̬չʾ
	public JSONObject showFriendsActvie(String user_id, int pageSize, int pageNumber, 
			String hostPath01, String hostPath02);//չʾ����Ȧ��̬
	
	public JSONObject addSeeActive(int active_user_id);//���˵˵�鿴������
	
	public JSONObject adminActive(int pageSize, int pageNumber, String searchText, 
			String startDate, String endDate, String sortName, String sortOrder, String hostPath);//��˵˵�Ķ�̬���
	public JSONObject verifyActive(int active_user_id);//���ͨ��ĳ��˵˵
	public JSONObject noVerifyActive(int active_user_id);//��˲�ͨ��ĳ��˵˵
	
	public JSONObject authActive(int pageSize, int pageNumber, String searchText, 
			String sortName, String sortOrder, String hostPath02);//����̬����֤�б�
	public List<ThemeCateg> allThemeCateg(String hostPath03);//��ȡȫ�����������
	public JSONObject modifyVerify(String state, String class_active, String verify);//�޸ķ���̬����֤����
	public JSONObject keyActive(String text, String hostPath01, String hostPath02, 
			int pageSize, int pageNumber);//˵˵�Ĺؼ��ʼ���
//	public JSONObject showdetailActive(int active_user_id, String hostPath01, 
//			String hostPath02);//չʾ��̬������ҳ
	public JSONObject batchDoVerify(String ids);//�������淢��̬����֤Ϊ1��״̬
	public JSONObject deleteOneActive(int active_user_id, String user_id);//ɾ��ĳ����̬
	public JSONObject rankActiveKey();//��̬�ؼ��ʵ�ǰ7��
	
	public JSONObject addFavor(int active_user_id, String user_id);//��Ӷ�̬���ղؼ�
	public JSONObject favorList(String user_id, int pageSize, int pageNumber, 
			String hostPath01, String hostPath02);//�û����ղؼ��б�
	public JSONObject deleteOneFavor(int favor_id);//ɾ��ĳ���ղ�
	public JSONObject batchDelThemeCateg(String ids);//����ɾ���������
	public JSONObject attachThemeCateg(MultipartFile themePic, String themeCateg, String localBasePath);//����������
	public JSONObject attachActiveTwoCateg(MultipartFile twoPic, String themeCateg, String twoCateg, String localBasePath);//��̨��Ӷ�̬�Ķ�������
	public JSONObject activeCategReport(int pageSize, int pageNumber, 
			String startDate, String endDate);//��̬���������ı���
	public JSONObject oneThemeTwoClass(String class_active);
	public JSONObject themeCateg();
	public JSONObject twoCategSelect(String themeCateg);
	public JSONObject detailActiveRepoet(String searchText, String startDate, String endDate, 
			String themeClass, String twoClass, String state, String sortName, String sortOrder, int pageSize, 
			int pageNumber, String hostPath02);
	
	
}
