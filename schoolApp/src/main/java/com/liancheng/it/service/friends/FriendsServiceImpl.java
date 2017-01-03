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
		
		//查询当前用户的好友数量
		int friendCount = friendsDao.queryFriendCount(user_id);
		if(friendCount>=50){
			jsonObject.put("status", false);
			jsonObject.put("msg", "您当前核心好友数量已达到上限");
			return jsonObject;
		}else {
			Friends friends01 = new Friends();
			Friends friends02 = new Friends();
			Timestamp u_reatime = new Timestamp(System.currentTimeMillis());
			if(!"".equals(f_user_id)){
				//先发起者加一条记录
				friends01.setUser_id(user_id);
				friends01.setF_user_id(f_user_id);
				friends01.setF_creatime(u_reatime);
				friendsDao.addFriend(friends01);
				//被加的再追加一条记录
				friends02.setUser_id(f_user_id);
				friends02.setF_user_id(user_id);
				friends02.setF_creatime(u_reatime);
				friendsDao.addFriend(friends02);
				
				System.out.println("添加好友成功！！！");
				jsonObject.put("status", true);
				jsonObject.put("msg", "添加好友成功");
				return jsonObject;
			}else {
				jsonObject.put("status", false);
				jsonObject.put("msg", "添加好友失败,好友的云信id为空");
				System.out.println("添加好友失败！！！！！");
				return jsonObject;
			}
		}
	}
	
	public JSONObject showfTof(String user_id){
		JSONObject jsonObject = new JSONObject();
		List<Friends> friends = friendsDao.queryFriends(user_id);
		if(friends!=null || friends.size()>0){//只要用户有朋友
			ArrayList<Friends> fTof = (ArrayList<Friends>) friendsDao.queryfTof(user_id);
			jsonObject.put("msg", "朋友的朋友查询成功！");
			jsonObject.put("data", fTof);
			return jsonObject;
		}else {
			jsonObject.put("msg", "朋友的朋友查询失败！");
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
			jsonObject.put("msg", "您已关注该用户!");
			jsonObject.put("status", false);
		}else {
			params.put("user_id", user_id);
			params.put("f_user_id", f_user_id);
			params.put("f_creatime", new Timestamp(System.currentTimeMillis()));
			friendsDao.addAttention(params);
			jsonObject.put("msg", "关注用户成功!");
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
		jsonObject.put("msg", "移除关注成功");
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
				//添加用户信息
				f.setProfile(hostPath01+user.getProfile());
				f.setUser_nickname(user.getUser_nickname());
				f.setSchool(user.getSchool());
				f.setMajor(user.getMajor());
				f.setGender(user.getGender());
				f.setBirthday(user.getBirthday());
			}
		}
		jsonObject.put("msg", "关注的用户列表成功!");
		jsonObject.put("status", true);
		jsonObject.put("data", attentions);
		jsonObject.put("type", 0);//表示关注的用户列表
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
				//添加用户信息
				f.setProfile(hostPath01+user.getProfile());
				f.setUser_nickname(user.getUser_nickname());
				f.setSchool(user.getSchool());
				f.setMajor(user.getMajor());
				f.setGender(user.getGender());
				f.setBirthday(user.getBirthday());
			}
		}
		jsonObject.put("msg", "粉丝的用户列表成功!");
		jsonObject.put("status", true);
		jsonObject.put("data", fans);
		jsonObject.put("type", 1);//表示粉丝的用户列表
		jsonObject.put("totalFans", friendsDao.queryTotalFans(user_id));
		return jsonObject;
	}
	
	public JSONObject friendsList(String user_id, String hostPath01){
		JSONObject jsonObject = new JSONObject();
		List<Friends> friends = friendsDao.queryFriendList(user_id);
		if(friends.size()>0 || friends!=null){
			for(Friends f:friends){
				f.setProfile(hostPath01+f.getProfile());//添加用户的url头像
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
		jsonObject.put("msg", "移除粉丝成功");
		return jsonObject;
	}
	
}
