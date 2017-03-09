package com.liancheng.it.service.user;


import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import net.minidev.json.JSONObject;

public interface UserService {
	
	/**
	 * ͨ���û�id�����û�
	 * @param user_id
	 * @return
	 */
	public JSONObject findUserById(String user_id);
	
	/**
	 * ͨ���ǳƲ����û�
	 * @param user_nickname
	 * @return
	 */
	public JSONObject findUsersByNick(String user_nickname);
	
	/**
	 * ��¼
	 * @param phoneNum
	 * @param password
	 * @param request
	 * @param hostPath01
	 * @return
	 */
	public JSONObject checkLogin(String phoneNum, String password, HttpServletRequest request, String hostPath01);
	
	/**
	 * ע��
	 * @param phoneNum
	 * @param password
	 * @param code
	 * @param enterCode
	 * @return
	 */
	public JSONObject addUser(String phoneNum,String password,String code,String enterCode);
	
	/**
	 * ��֤��
	 * @param phoneNum
	 * @param codeType
	 * @return
	 */
	public JSONObject checkUserRegist(String phoneNum ,int codeType);
	
	/**
	 * �ϴ��û�ͷ��
	 */
	public void saveProfile(MultipartFile pic, String phoneNum, 
			String localBasePath, String picName, String deletLocalPath, String suffix);
	
	/**
	 * �����û�������Ϣ
	 * @return
	 */
	public JSONObject editUser(String phoneNum, String username, String gender, 
			String birthday, String star, String e_state, String grade, String profession, String school,
			String major, String highschool, String province, String city, String county, 
			String lable, String skill);
	
	/**
	 * �����û�����
	 * @param user_id
	 * @param skills
	 * @return
	 */
	public JSONObject editSkills(String user_id, String skills);
	
	/**
	 * �ϴ��û���֤��Ϣ
	 * @return
	 */
	public JSONObject userVerify(MultipartFile verify, String type, 
			String user_id, String localBasePath, String picName, String suffix);
	
	/**
	 * �û���֤��Ϣ��̨����
	 * @return
	 */
	public JSONObject verifyReport(int pageSize, int pageNumber, String searchText, String sortName,
			String sortOrder, String startDate, String endDate, String schoolID, String phoneNum, String hostPath);
	
	/**
	 * ��̨������֤ѧ��֤���ҵ֤
	 * @param schoolId
	 * @param verify
	 * @param state
	 * @param otherState
	 * @return
	 */
	public JSONObject oneUserVerify(int schoolId, String verify, String state, String otherState);
	
	/**
	 * �˺����û��б�
	 * @param user_id
	 * @param pageSize
	 * @param pageNumber
	 * @param hostPath01
	 * @return
	 */
	public JSONObject randPeoples(String user_id, int pageSize, int pageNumber, 
			String hostPath01);
	
	/**
	 * �˺�������Ȧ�û���ɸѡ
	 * @param user_id
	 * @param pageSize
	 * @param pageNumber
	 * @param gender
	 * @param grade
	 * @param age01  ��ѯ�ĳ�ʼ����
	 * @param age02 ��ѯ�Ľ�������
	 * @param major
	 * @param type
	 * @param hostPath01
	 * @return
	 */
	public JSONObject filterPeoples(String user_id, int pageSize, int pageNumber, String gender, 
			String grade, int age01, int age02, String major, String type, String hostPath01);
	
	/**
	 * ����Ȧ�ķ����û�
	 * @return
	 */
	public JSONObject maimaiCateg(String user_id, int pageSize, int pageNumber, 
			String type, String hostPath01);
	
	/**
	 * �˺��û�������
	 * @param param
	 * @param hostPath01
	 * @return
	 */
	public JSONObject peoplesSearch(String param, String hostPath01);
	
	/**
	 * ����Ȧ���û�����
	 * @param param
	 * @param hostPath01
	 * @return
	 */
	public JSONObject maimaiSearch(String param, String hostPath01);
	
	/**
	 * �û�������Ϣչʾ
	 * @param user_id
	 * @param other_user_id
	 * @param hostPath01
	 * @param hostPath02
	 * @return
	 */
	public JSONObject userInfo(String user_id, String other_user_id, String hostPath01, String hostPath02);
	
	/**
	 * �ÿ͵��û��б�
	 * @param user_id
	 * @param pageSize
	 * @param pageNumber
	 * @param hostPath01
	 * @return
	 */
	public JSONObject visitorList(String user_id, int pageSize, int pageNumber, 
			String hostPath01);
	
	/**
	 * ��̨���û�����
	 * @return
	 */
	public JSONObject userReport(int pageSize, int pageNumber, String searchText, String verifyState, 
			String blockState, String school, String gender, String profession, String startDate, String endDate, 
			String sortName, String sortOrder, String schoolID, String hostPath01, String hostPath02);
	
	/**
	 * �û�����֤״̬
	 * @param user_id
	 * @return
	 */
	public JSONObject checkUserVrify(String user_id);
	
	/**
	 * ��������
	 * @param phoneNum
	 * @param code
	 * @param password
	 * @return
	 */
	public JSONObject editPassword(String phoneNum, String code, String password);
	
	/**
	 * ��̬�ɲ��ɼ��Ŀ���
	 * @param user_id
	 * @param f_user_id
	 * @return
	 */
	public JSONObject seeState(String user_id, String f_user_id);
	
	/**
	 * home.htmlҳ��chart��ɸѡ���ֵ
	 * @return
	 */
	public JSONObject getHomeSelectorVal();
	
	/**
	 * home.htmlҳ��Echart������
	 * @param selectDate
	 * @return
	 */
	public JSONObject getHomeChartData(String selectDate);
	
	/**
	 * �����Ƿ���Է���Ϣ
	 * @param user_id
	 * @param send_msg ���أ�0�ر� 1����
	 * @return
	 */
	public JSONObject controlSendMsg(String user_id, int send_msg);
	
	/**
	 * �Ӻ���ʱ�Ƿ���Ҫ��֤�Ŀ���
	 * @param user_id
	 * @param switchVal
	 * @return
	 */
	public JSONObject addFriendIsVerify(String user_id, int switchVal);
	
	/**
	 * ���� --����TA���ҵĶ�̬-- �� --����TA�Ķ�̬�Ŀ���--
	 * @param user_id
	 * @param other_user_id
	 * @param type
	 * @param state
	 * @return
	 */
	public JSONObject taNoSeeOwnActive(String user_id, String other_user_id, String type, int state);
	
	/**
	 * ����Ȧ��̬��˿�ɼ���
	 * @param user_id
	 * @param switchVal
	 * @return
	 */
	public JSONObject lifeSeeControl(String user_id, int switchVal);
	
	/**
	 * �����û�
	 * @param user_id
	 * @param lock
	 * @return
	 */
	public JSONObject blockUser(String user_id, int lock);
	
	
}
