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
import com.liancheng.it.util.IMManager;

@Service
@Aspect
@Transactional
public class FriendsServiceImpl implements FriendsService {
	
	@Resource(name="friendsDao")
	private FriendsDao friendsDao;
	@Resource(name="userDao")
	private UserDao userDao;
	
	public JSONObject reqAddFriends(String user_id, String f_user_id, String msg, String type){
		JSONObject jsonObject = new JSONObject();
		User user = userDao.findById(user_id);
		if(user.getAdd_switch()==0 && (!"3".equals(type) && !"4".equals(type))){//1 ֱ�ӼӺ���
			try {
				if(IMManager.addFriends(user_id, f_user_id, msg, "1")){
					Friends friends01 = new Friends();
					Friends friends02 = new Friends();
					Timestamp u_reatime = new Timestamp(System.currentTimeMillis());
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
					
					jsonObject.put("status", true);
					jsonObject.put("msg", "��Ӻ��ѳɹ�");
					return jsonObject;
				}else {
					jsonObject.put("status", false);
					jsonObject.put("msg", "��ӼӺ���ʧ��");
					return jsonObject;
				}
			} catch (Exception e) {
				jsonObject.put("status", false);
				jsonObject.put("msg", "��ӼӺ���ʧ��");
				return jsonObject;
			}
		}else if(user.getAdd_switch()==1 && (!"3".equals(type) && !"4".equals(type))) {//2 ����Ӻ���
			try {
				if(IMManager.addFriends(user_id, f_user_id, msg, "2")){
					jsonObject.put("status", true);
					jsonObject.put("msg", "����Ӻ��ѳɹ�");
					return jsonObject;
				}else {
					jsonObject.put("status", false);
					jsonObject.put("msg", "����Ӻ���ʧ��");
					return jsonObject;
				}
			} catch (Exception e) {
				jsonObject.put("status", false);
				jsonObject.put("msg", "����Ӻ���ʧ��");
				return jsonObject;
			}
		}else if(user.getAdd_switch()==1 && "3".equals(type)) {//ȷ�ϳ�Ϊ����
			try {
				if(IMManager.addFriends(user_id, f_user_id, msg, type)){
					Friends friends01 = new Friends();
					Friends friends02 = new Friends();
					Timestamp u_reatime = new Timestamp(System.currentTimeMillis());
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
					
					jsonObject.put("status", true);
					jsonObject.put("msg", "��Ϊ����");
					return jsonObject;
				}else {
					jsonObject.put("status", false);
					jsonObject.put("msg", "ȷ��ʧ��");
					return jsonObject;
				}
			} catch (Exception e) {
				jsonObject.put("status", false);
				jsonObject.put("msg", "ȷ��ʧ��");
				return jsonObject;
			}
		}else {//��ͬ���Ϊ����,type=4
			try {
				if(IMManager.addFriends(user_id, f_user_id, msg, type)){
					jsonObject.put("status", true);
					jsonObject.put("msg", "�ܾ���Ӻ���");
					return jsonObject;
				}else {
					jsonObject.put("status", false);
					jsonObject.put("msg", "�ܾ��Ӻ���ʧ��");
					return jsonObject;
				}
			} catch (Exception e) {
				jsonObject.put("status", false);
				jsonObject.put("msg", "�ܾ��Ӻ���ʧ��");
				return jsonObject;
			}
		}
	}
	
	public List<Friends> showfTof(String user_id, String hostPath01){
//		JSONObject jsonObject = new JSONObject();
		List<Friends> friends = friendsDao.queryFriends(user_id);
		if(friends!=null || friends.size()>0){//ֻҪ�û�������
			for(Friends f:friends){
				if(f.getProfile()==null){
					f.setProfile(hostPath01+"avatar_def.png");
				}else {
					f.setProfile(hostPath01+f.getProfile());//������ѵ����ѵ�ͷ��
				}
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("user_id", user_id);
				params.put("f_user_id", f.getF_user_id());
				List<Friends> friendList = friendsDao.queryfTof(params);//��ѯ���ѵ�����
				if(friendList!=null || friendList.size()>0){//ֻҪ���ѵ����Ѳ�Ϊ��
					for(Friends ff:friendList){
						if(ff.getProfile()==null){
							ff.setProfile(hostPath01+"avatar_def.png");
						}else {
							ff.setProfile(hostPath01+ff.getProfile());//������ѵ����ѵ�ͷ��
						}
					}
				}
				f.setfTofriends(friendList);
			}
//			jsonObject.put("status", true);
//			jsonObject.put("msg", "���ѵ����Ѳ�ѯ�ɹ���");
//			jsonObject.put("data", friends);
			//ͬʱ�޸�״̬Ϊ�Ѳ鿴״̬
			friendsDao.updateFToFSee(user_id);
			return friends;
		}else {
//			jsonObject.put("status", false);
//			jsonObject.put("msg", "���ѵ����Ѳ�ѯʧ�ܣ�");
			return friends;
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
				if(user.getProfile()==null){
					f.setProfile(hostPath01+"avatar_def.png");
				}else {
					f.setProfile(hostPath01+user.getProfile());
				}
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
				if(user.getProfile()==null){
					f.setProfile(hostPath01+"avatar_def.png");
				}else {
					f.setProfile(hostPath01+user.getProfile());
				}
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
				if(f.getProfile()==null){
					f.setProfile(hostPath01+"avatar_def.png");
				}else {
					f.setProfile(hostPath01+f.getProfile());
				}
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
	
	public JSONObject deleteFriend(String user_id, String f_user_id){
		JSONObject jsonObject = new JSONObject();
		try {
			if(IMManager.delFriends(user_id, f_user_id)){
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("user_id", user_id);
				params.put("f_user_id", f_user_id);
				int delCount = friendsDao.deleteFriend(params);
				if(delCount>0){
					jsonObject.put("status", true);
					jsonObject.put("msg", "ɾ�����ѳɹ�");
					return jsonObject;
				}else {
					jsonObject.put("status", false);
					jsonObject.put("msg", "ɾ������ʧ��");
					return jsonObject;
				}
			}else {
				jsonObject.put("status", false);
				jsonObject.put("msg", "ɾ������ʧ��");
				return jsonObject;
			}
		} catch (Exception e) {
			jsonObject.put("status", false);
			jsonObject.put("msg", "ɾ������ʧ��");
			return jsonObject;
		}
	}
	
	
	
}
