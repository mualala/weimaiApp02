package com.liancheng.it.service.friends;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.minidev.json.JSONObject;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.liancheng.it.dao.friends.FriendsDao;
import com.liancheng.it.dao.user.UserDao;
import com.liancheng.it.entity.friends.Friends;
import com.liancheng.it.entity.user.User;

@Service
@Aspect
@Transactional
public class FriendsServiceImpl implements FriendsService {
	
	@Resource(name="friendsDao")
	private FriendsDao friendsDao;
	@Resource(name="userDao")
	private UserDao userDao;
	
	public JSONObject confirmAddFriends(String user_id, String f_user_id){
		JSONObject jsonObject = new JSONObject();
		
		//��ѯ��ǰ�û��ĺ�������
		int friendCount = friendsDao.queryFriendCount(user_id);
		if(friendCount>=50){
			jsonObject.put("status", false);
			jsonObject.put("msg", "����ǰ���ĺ��������Ѵﵽ����");
			return jsonObject;
		}else {
			Friends friends01 = new Friends();
			Friends friends02 = new Friends();
			Timestamp u_reatime = new Timestamp(System.currentTimeMillis());
			if(!"".equals(f_user_id)){
				//�ȷ����߼�һ����¼
				friends01.setUser_id(user_id);
				friends01.setF_user_id(f_user_id);
				friends01.setF_creatime(u_reatime);
				friendsDao.addFriend(friends01);
				//���ӵ���׷��һ����¼
				friends02.setUser_id(f_user_id);
				friends02.setF_user_id(user_id);
				friends02.setF_creatime(u_reatime);
				friendsDao.addFriend(friends02);
				
				System.out.println("��Ӻ��ѳɹ�������");
				jsonObject.put("status", true);
				jsonObject.put("msg", "��Ӻ��ѳɹ�");
				return jsonObject;
			}else {
				jsonObject.put("status", false);
				jsonObject.put("msg", "��Ӻ���ʧ��,���ѵ�����idΪ��");
				System.out.println("��Ӻ���ʧ�ܣ���������");
				return jsonObject;
			}
		}
	}
	
	public JSONObject showfTof(String user_id){
		JSONObject jsonObject = new JSONObject();
		List<Friends> friends = friendsDao.queryFriends(user_id);
		if(friends!=null || friends.size()>0){//ֻҪ�û�������
			ArrayList<Friends> fTof = (ArrayList<Friends>) friendsDao.queryfTof(user_id);
			jsonObject.put("msg", "���ѵ����Ѳ�ѯ�ɹ���");
			jsonObject.put("data", fTof);
			return jsonObject;
		}else {
			jsonObject.put("msg", "���ѵ����Ѳ�ѯʧ�ܣ�");
			return jsonObject;
		}
	}
	
	public JSONObject addAttention(String user_id,String f_user_id){
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> params02 = new HashMap<String, Object>();
		params02.put("user_id", user_id);
		params02.put("f_user_id", f_user_id);
		int count = friendsDao.countIsAttention(params02);
		if(count>0){
			jsonObject.put("msg", "���ѹ�ע���û�!");
			jsonObject.put("status", false);
		}else {
			params.put("user_id", user_id);
			params.put("f_user_id", f_user_id);
			params.put("f_creatime", new Timestamp(System.currentTimeMillis()));
			friendsDao.addAttention(params);
			jsonObject.put("msg", "��ע�û��ɹ�!");
			jsonObject.put("status", true);
		}
		return jsonObject;
	}
	
	public JSONObject cancelAttention(String user_id,String f_user_id){
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", user_id);
		params.put("f_user_id", f_user_id);
		friendsDao.cancelAttention(params);
		jsonObject.put("msg", "�Ƴ���ע�ɹ�");
		jsonObject.put("status", true);
		return jsonObject;
	}
	
	public JSONObject attentionUsers(String user_id, int pageSize, int pageNumber, 
			String hostPath01){
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		params.put("user_id", user_id);
		params.put("start", start);
		params.put("end", end);
		List<Friends> attentions = friendsDao.queryAttentions(params);
		if(attentions.size()>0){
			for(Friends f:attentions){
				User user = userDao.findByUserId(f.getF_user_id());
				//����û���Ϣ
				f.setProfile(hostPath01+user.getProfile());
				f.setUser_nickname(user.getUser_nickname());
				f.setSchool(user.getSchool());
				f.setMajor(user.getMajor());
				f.setGender(user.getGender());
				f.setBirthday(user.getBirthday());
			}
		}
		jsonObject.put("msg", "��ע���û��б�ɹ�!");
		jsonObject.put("status", true);
		jsonObject.put("data", attentions);
		jsonObject.put("type", 0);//��ʾ��ע���û��б�
		jsonObject.put("totalAttention", friendsDao.queryTotalAttention(user_id));
		return jsonObject;
	}
	
	public JSONObject showFans(String user_id, int pageSize, int pageNumber, String hostPath01){
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		int start = (pageNumber-1)*pageSize;
		int end = pageSize;
		params.put("f_user_id", user_id);
		params.put("start", start);
		params.put("end", end);
		List<Friends> fans = friendsDao.queryFans(params);
		if(fans.size()>0){
			for(Friends f:fans){
				User user = userDao.findByUserId(f.getUser_id());
				//����û���Ϣ
				f.setProfile(hostPath01+user.getProfile());
				f.setUser_nickname(user.getUser_nickname());
				f.setSchool(user.getSchool());
				f.setMajor(user.getMajor());
				f.setGender(user.getGender());
				f.setBirthday(user.getBirthday());
			}
		}
		jsonObject.put("msg", "��˿���û��б�ɹ�!");
		jsonObject.put("status", true);
		jsonObject.put("data", fans);
		jsonObject.put("type", 1);//��ʾ��˿���û��б�
		jsonObject.put("totalFans", friendsDao.queryTotalFans(user_id));
		return jsonObject;
	}
	
	public JSONObject friendsList(String user_id, String hostPath01){
		JSONObject jsonObject = new JSONObject();
		List<Friends> friends = friendsDao.queryFriendList(user_id);
		if(friends.size()>0 || friends!=null){
			for(Friends f:friends){
				f.setProfile(hostPath01+f.getProfile());//����û���urlͷ��
			}
		}
		jsonObject.put("status", true);
		jsonObject.put("data", friends);
		jsonObject.put("totalFriends", friendsDao.queryFriendCount(user_id));
		return jsonObject;
	}
	
	public JSONObject cancelFans(String user_id, String f_user_id){
		JSONObject jsonObject = new JSONObject();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", user_id);
		params.put("f_user_id", f_user_id);
		friendsDao.cancelFans(params);
		
		jsonObject.put("status", true);
		jsonObject.put("msg", "�Ƴ���˿�ɹ�");
		return jsonObject;
	}
	
}
