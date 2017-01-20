package com.liancheng.it.service.user;


import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.minidev.json.JSONObject;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liancheng.it.dao.user.CheckCodeDao;
import com.liancheng.it.dao.user.UserDao;
import com.liancheng.it.entity.friends.Friends;
import com.liancheng.it.entity.user.Coder;
import com.liancheng.it.entity.user.User;
import com.liancheng.it.util.HttpSenderUtil;
import com.liancheng.it.util.Jwt;
import com.liancheng.it.util.RegistCheckCode;
import com.liancheng.it.util.UUIDUtil;
/**
 * �û���¼ҵ��
 */

@Service("userService")//ɨ��service
@Aspect
@Transactional
public class UserServiceImpl implements UserService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource(name="userDao")//ע��userDao
	private UserDao userDao;
	@Resource(name="checkCodeDao")//ע��checkCodeDao
	private CheckCodeDao checkCodeDao;

	/**
	 * ע��ҵ��
	 */
	public JSONObject addUser(String phoneNum,String password,String code,String nickname){
		JSONObject resultJSON=new JSONObject();
		User user = new User();
		Coder checkCode = new Coder();
		
		if(password==null || "".equals(password)){//�������Ϊ��
			resultJSON.put("msg", "����Ϊ��,����������!");
			return resultJSON;
		}
		if(code==null || "".equals(code)){//�����֤��Ϊ��
			resultJSON.put("msg", "��֤��Ϊ��,��������֤��!");
			return resultJSON;
		}else {
			checkCode = checkCodeDao.findCheckCodeByPhoneNum(phoneNum);//������֤���ȡ�û���֤��Ϣ
			
			System.out.println("��ѯ�û�����֤����Ϣcoder��"+checkCode+"\n����ĵ绰="+phoneNum+
					"\n�������֤��="+code);
			if(checkCode==null){//���û�л�ȡ��֤��
				resultJSON.put("status", false);
				resultJSON.put("msg", "�ú���δ��ȡ��֤��");
				return resultJSON;
			}else if(code.equals(checkCode.getCode()) && phoneNum.equals(checkCode.getId())) {//��֤��͵绰������ȷ�������
				
				System.out.println("���ˡ�����");
				
				long currentime = System.currentTimeMillis();
				long creatime = checkCode.getCreatime().getTime();
				
				if(currentime<creatime+1*60*1000){//�ж���֤���Ƿ���ڣ�5����Ϊ����ʱ��
					try {
						user.setUser_id(UUIDUtil.creatId());//����������uuid
						user.setPhoneNum(phoneNum);
						user.setNickname(nickname);//�����������û���
						user.setPhoneNum(phoneNum);//����û��绰����
						user.setCreatime(new Timestamp(System.currentTimeMillis()));
						user.setPassword(UUIDUtil.md5(password));//�������� 
						
						userDao.saveUser(user);//����ע���û�
						checkCodeDao.deleteCoderByPhoneNum(phoneNum);//ע��ɹ���ɾ����֤��
					} catch (Exception e) {
						e.printStackTrace();
					}
					System.out.println("ע���û�����Ϣ��"+user);
					
					Date date = new Date();
					Map<String , Object> payload=new HashMap<String, Object>();
					payload.put("uid", phoneNum);//�û�ID
					payload.put("iat", date.getTime());//����ʱ��
					payload.put("ext",date.getTime()+1000*60*60);//����ʱ��1Сʱ
//					String token=Jwt.createToken(payload);
					
					resultJSON.put("status", true);
					resultJSON.put("msg", "ע��ɹ�");
					return resultJSON;
				}else {//��֤�����
					checkCodeDao.deleteCoderByPhoneNum(phoneNum);//��֤����ھ�ɾ��������֤���¼
					resultJSON.put("status", false);
					resultJSON.put("msg", "��֤��ʧЧ�������»�ȡ��֤��");
				}
			}else {
				resultJSON.put("msg", "ע��ʧ��");
				return resultJSON;
			}
		}
		return resultJSON;
	}
	
	public boolean checkLoginUser(String phoneNum, String password){
		User user = userDao.queryUserByPhone(phoneNum);
		try {
			if(user!=null && (UUIDUtil.md5(password)).equals(user.getPassword())){
				logger.info("�û����Ե�¼");
				return true;
			}
		} catch (Exception e) {
			logger.info("��¼ʱmd5����ʧ��");
		}
		return false;
	}
	
	/**
	 * ������֤�벢������֤��ҵ��
	 */
	public JSONObject createCode(String phoneNum){
		JSONObject resultJSON=new JSONObject();
		Coder coder =  new Coder();
		Coder checkCode = checkCodeDao.findCheckCodeByPhoneNum(phoneNum);
		if(userDao.queryUserByPhone(phoneNum)!=null){//�ֻ������Ѿ�ע��
			resultJSON.put("status", false);
			resultJSON.put("msg", "���ֻ�������ע��");
			return resultJSON;
		}else {//�ֻ�����ûע���
			if(checkCode != null){//�жϸ��ֻ��Ƿ��ȡ����֤��
//				Date date=new Date();
				long currentime = System.currentTimeMillis();
				long creatime = checkCodeDao.findCheckCodeByPhoneNum(phoneNum).getCreatime().getTime();
				
				if(currentime<creatime+1*60*1000){//�ж���֤���Ƿ���ڣ�1�����ڲ����ٻ�ȡ��֤��
					resultJSON.put("status", false);
					resultJSON.put("msg", "����1���Ӻ��ȡ");
					System.out.println("��ȡ����֤�룬1min���ܻ�ȡ");
					return resultJSON;
				}else{
					checkCodeDao.deleteCoderByPhoneNum(phoneNum);//5���Ӻ�ɾ������֤��
				}
			}
			String code = RegistCheckCode.getCheckCode();
			String codemsg = "���ã�������֤��Ϊ��"+code+"��Чʱ��1���ӣ��뼰ʱ���ע�ᣡ";
			
			coder.setCode(code);
			coder.setCreatime(new Timestamp(System.currentTimeMillis()));
			coder.setId(phoneNum);
			checkCodeDao.saveCoder(coder);//����һ����֤��
			System.out.println("��������֤�룺"+ coder.toString());
			try {
				HttpSenderUtil.batchSend("http://sapi.253.com/msg/HttpBatchSendSM",
						"qishidianzi", "Qsdz757980", phoneNum, codemsg, true, null);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("���ֻ���"+phoneNum+"������֤��ʧ��");
			}
			resultJSON.put("status", true);
			resultJSON.put("msg", "��ȡ��֤��ɹ�");
			resultJSON.put("data", code);
			System.out.println("��֤�뷵�ص�json��"+resultJSON.toJSONString());
			return resultJSON;
		}
		
	}
	
	
}
