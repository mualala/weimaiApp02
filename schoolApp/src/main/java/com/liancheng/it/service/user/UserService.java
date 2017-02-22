package com.liancheng.it.service.user;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import net.minidev.json.JSONObject;

public interface UserService {
	
	public JSONObject checkLogin(String phoneNum, String password, HttpServletRequest request, String hostPath01);//��¼
	public JSONObject addUser(String phoneNum,String password,String code,String enterCode);//ע��
	public JSONObject checkUserRegist(String phoneNum ,int codeType);//��֤��
	
	public void saveProfile(MultipartFile pic, String phoneNum, 
			String localBasePath, String picName, String deletLocalPath, String suffix);//�û��ϴ�ͷ��
	public JSONObject findUserById(String user_id);//ͨ��id�����û�
	public JSONObject findUsersByNick(String user_nickname);//ͨ���ǳƲ����û�
	public JSONObject editUser(String phoneNum, String username, String gender, 
			String birthday, String star, String e_state, String grade, String profession, String school,
			String major, String highschool, String province, String city, String county, 
			String lable, String skill);//�޸�����
	public JSONObject editSkills(String user_id, String skills);//�����û�����
	public JSONObject userVerify(MultipartFile verify, String type, 
			String user_id, String localBasePath, String picName, String suffix);//�û���֤ͼƬ
	public JSONObject verifyReport(int pageSize, int pageNumber, String searchText, String sortName,
			String sortOrder, String startDate, String endDate, String schoolID, String phoneNum, String hostPath);//�û���֤��Ϣ��̨����
	public JSONObject oneUserVerify(int schoolId, String verify, String state, String otherState);//��̨������֤ѧ��֤���ҵ֤
	public JSONObject randPeoples(String user_id, int pageSize, int pageNumber, 
			String hostPath01);//�˺����û��б�
	public JSONObject filterPeoples(String user_id, int pageSize, int pageNumber, String gender, 
			String grade, int age01, int age02, String major, String type, String hostPath01);//�˺�������ɸѡ
	public JSONObject maimaiCateg(String user_id, int pageSize, int pageNumber, 
			String type, String hostPath01);//����Ȧ�ķ����û�
	public JSONObject peoplesSearch(String param, String hostPath01);//�˺��û�����������
	public JSONObject maimaiSearch(String param, String hostPath01);//����Ȧ����������
	public JSONObject userInfo(String user_id, String other_user_id, String hostPath01, String hostPath02);//չʾ�û��ĸ�����Ϣ
	public JSONObject visitorList(String user_id, int pageSize, int pageNumber, 
			String hostPath01);//�ÿ͵��û��б�
	public JSONObject userReport(int pageSize, int pageNumber, String searchText, String verifyState, 
			String school, String gender, String profession, String startDate, String endDate, 
			String sortName, String sortOrder, String schoolID, String hostPath01, String hostPath02);//��̨���û�����
	public JSONObject checkUserVrify(String user_id);//����û���֤�Ƿ�ͨ��
	public JSONObject editPassword(String phoneNum, String code, String password);//�޸�����
	public JSONObject seeState(String user_id, String f_user_id);//�Ƿ��ע���Ƿ�ɿ���̬
	public JSONObject getHomeSelectorVal();//home.htmlҳ��chart��ɸѡ���ֵ
	public JSONObject getHomeChartData(String selectDate);
	public JSONObject controlSendMsg(String user_id, int send_msg);//
	public JSONObject addFriendIsVerify(String user_id, int switchVal);
	public JSONObject taNoSeeOwnActive(String user_id, String other_user_id, String type, int state);
	public JSONObject lifeSeeControl(String user_id, int switchVal);
	
}
