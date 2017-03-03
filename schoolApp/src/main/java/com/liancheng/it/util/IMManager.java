package com.liancheng.it.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;





import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class IMManager {

	private static String appKey = "fbc784689ba0db58767462fd69859a83";
	private static String appSecret = "0c3145212d28";
	private static String nonce = String.valueOf(Math.random()*100000);
	private static String curTime = String.valueOf((new Date()).getTime() / 1000L);
	
	/**
	 * 和网易云信的webservice通信创建用户token
	 * 
	 * @param accid 为云信ID,最大长度32字符,必须保证一个APP内唯一(只允许字母、数字、半角下划线_、@、半角点以及半角-组成,不区分大小写)
	 * @return MD5密码
	 * @throws Exception
	 */
	public static String getIMUserId(String accid,String username) throws Exception{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = "https://api.netease.im/nimserver/user/create.action";
		HttpPost httpPost = new HttpPost(url);
		
		String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);
		
		//设置请求的header
		httpPost.addHeader("AppKey",appKey);//开发者平台分配的appkey
		httpPost.addHeader("Nonce", nonce);//随机数(最大长度128个字符)
		httpPost.addHeader("CurTime", curTime);//当前UTC时间戳,从1970年1月1日0点0 分0 秒开始到现在的秒数(String)
		httpPost.addHeader("CheckSum",checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//设置请求的参数
		List<NameValuePair> vpns = new ArrayList<NameValuePair>();
		vpns.add(new BasicNameValuePair("accid", accid));
		vpns.add(new BasicNameValuePair("name", username));//添加用户昵称
		httpPost.setEntity(new UrlEncodedFormEntity(vpns, "utf-8"));
		
		//执行请求
		HttpResponse response = httpClient.execute(httpPost);
		String imResult = EntityUtils.toString(response.getEntity(),"utf-8");
		String token = "";
		if(idJsonParse(imResult) != null){
			token = idJsonParse(imResult);
		}
		return token;
	}
	
	/**
	 * 禁用/解禁用户
	 * @param user_id
	 * @param lock 开关 1禁用 0解禁
	 * @return
	 */
	public static boolean blockIMUser(String user_id, int lock) throws Exception{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = "";
		if(lock==1){
			url = "https://api.netease.im/nimserver/user/block.action";
		}else {
			url = "https://api.netease.im/nimserver/user/unblock.action";
		}
		HttpPost httpPost = new HttpPost(url);
		
		String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);
		
		//设置请求的header
		httpPost.addHeader("AppKey",appKey);//开发者平台分配的appkey
		httpPost.addHeader("Nonce", nonce);//随机数(最大长度128个字符)
		httpPost.addHeader("CurTime", curTime);//当前UTC时间戳,从1970年1月1日0点0 分0 秒开始到现在的秒数(String)
		httpPost.addHeader("CheckSum",checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//设置请求的参数
		List<NameValuePair> vpns = new ArrayList<NameValuePair>();
		vpns.add(new BasicNameValuePair("accid", user_id));
		httpPost.setEntity(new UrlEncodedFormEntity(vpns, "utf-8"));
		
		//执行请求
		HttpResponse response = httpClient.execute(httpPost);
		String imResult = EntityUtils.toString(response.getEntity(),"utf-8");
		JSONObject jsonObject = new JSONObject(imResult);
		String state = jsonObject.get("code").toString();
		if("200".equals(state)){
			return true;
		}else {
			return false;
		}
	}
	
	
	/**
	 * 好友关系
	 * @param user_id
	 * @param f_user_id
	 * @param type //1直接加好友，2请求加好友，3同意加好友，4拒绝加好友
	 * @return
	 */
	public static boolean addFriends(String user_id, String f_user_id, String msg, String type) throws Exception{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = "https://api.netease.im/nimserver/friend/add.action";
		HttpPost httpPost = new HttpPost(url);
		
		String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);
		
		//设置请求的header
		httpPost.addHeader("AppKey",appKey);//开发者平台分配的appkey
		httpPost.addHeader("Nonce", nonce);//随机数(最大长度128个字符)
		httpPost.addHeader("CurTime", curTime);//当前UTC时间戳,从1970年1月1日0点0 分0 秒开始到现在的秒数(String)
		httpPost.addHeader("CheckSum",checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//设置请求的参数
		List<NameValuePair> vpns = new ArrayList<NameValuePair>();
		vpns.add(new BasicNameValuePair("accid", user_id));
		vpns.add(new BasicNameValuePair("faccid", f_user_id));
		vpns.add(new BasicNameValuePair("type", type));
		if("2".equals(type)){
			vpns.add(new BasicNameValuePair("msg", msg));//请求加好友的msg
		}
		httpPost.setEntity(new UrlEncodedFormEntity(vpns, "utf-8"));
		
		//执行请求
		HttpResponse response = httpClient.execute(httpPost);
		String imResult = EntityUtils.toString(response.getEntity(),"utf-8");
		JSONObject jsonObject = new JSONObject(imResult);
		String state = jsonObject.get("code").toString();
		if("200".equals(state)){
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 删除好友关系
	 * @param user_id
	 * @param f_user_id
	 * @return
	 * @throws Exception
	 */
	public static boolean delFriends(String user_id, String f_user_id) throws Exception{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = "https://api.netease.im/nimserver/friend/delete.action";
		HttpPost httpPost = new HttpPost(url);
		
		String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);
		
		//设置请求的header
		httpPost.addHeader("AppKey",appKey);//开发者平台分配的appkey
		httpPost.addHeader("Nonce", nonce);//随机数(最大长度128个字符)
		httpPost.addHeader("CurTime", curTime);//当前UTC时间戳,从1970年1月1日0点0 分0 秒开始到现在的秒数(String)
		httpPost.addHeader("CheckSum",checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//设置请求的参数
		List<NameValuePair> vpns = new ArrayList<NameValuePair>();
		vpns.add(new BasicNameValuePair("accid", user_id));
		vpns.add(new BasicNameValuePair("faccid", f_user_id));
		httpPost.setEntity(new UrlEncodedFormEntity(vpns, "utf-8"));
		
		//执行请求
		HttpResponse response = httpClient.execute(httpPost);
		String imResult = EntityUtils.toString(response.getEntity(),"utf-8");
		JSONObject jsonObject = new JSONObject(imResult);
		String state = jsonObject.get("code").toString();
		if("200".equals(state)){
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 拉黑用户
	 * @param user_id
	 * @param other_user_id
	 * @param value 0:取消黑名单或静音，1:加入黑名单或静音
	 * @return
	 */
	public static boolean setSpecialRelation(String user_id, String other_user_id, String value) throws Exception{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = "https://api.netease.im/nimserver/user/setSpecialRelation.action";
		HttpPost httpPost = new HttpPost(url);
		
		String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);
		
		//设置请求的header
		httpPost.addHeader("AppKey",appKey);//开发者平台分配的appkey
		httpPost.addHeader("Nonce", nonce);//随机数(最大长度128个字符)
		httpPost.addHeader("CurTime", curTime);//当前UTC时间戳,从1970年1月1日0点0 分0 秒开始到现在的秒数(String)
		httpPost.addHeader("CheckSum",checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//设置请求的参数
		List<NameValuePair> vpns = new ArrayList<NameValuePair>();
		vpns.add(new BasicNameValuePair("accid", user_id));
		vpns.add(new BasicNameValuePair("targetAcc", other_user_id));
		vpns.add(new BasicNameValuePair("relationType", "1"));//1:黑名单操作，2:静音列表操作
		vpns.add(new BasicNameValuePair("value", value));
		httpPost.setEntity(new UrlEncodedFormEntity(vpns, "utf-8"));
		
		//执行请求
		HttpResponse response = httpClient.execute(httpPost);
		String imResult = EntityUtils.toString(response.getEntity(),"utf-8");
		JSONObject jsonObject = new JSONObject(imResult);
		String state = jsonObject.get("code").toString();
		if("200".equals(state)){
			return true;
		}else {
			return false;
		}
	}
	
	public static String idJsonParse(String imResult){
		JSONObject jsonObject01 = new JSONObject(imResult);
		String state = jsonObject01.get("code").toString();
		if("401".equals(state)){
			System.out.println("创建云信id时，CheckSum检验失败(返回401)！");
			return null;
		}
		if("414".equals(state)){
			System.out.println("已经注册云信id");
			return null;
		}
		if("200".equals(state)){
			/*
			 json结构如下面这样：
			 {
				"code":200,
				"info":{
					"token":"a6a0cb167dc429a0f3047b58cb6e354d",
					"accid":"10000bb",
					"name":""
					}
			}
			 */
			String toJson = imResult.replaceAll("\"", "'");
			JSONObject jsonObject02 = new JSONObject(toJson);
			JSONObject jsonObject03 = (JSONObject) jsonObject02.get("info");
			String token = jsonObject03.get("token").toString();
			return token;
		}
		System.out.println("返回了其他状态码(401,200之外的)!!"+imResult);
		return null;
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		
//		String a = "{\"code\":200,\"info\":{\"token\":\"a6a0cb167dc429a0f3047b58cb6e354d\",\"accid\":\"10000bb\",\"name\":\"\"}}";
//		String b = "{'code':200,'info':100}";
		
//		System.out.println(getIMUserId("10000bba","sh"));
		
		System.out.println(delFriends("f5db8cbe5d644b4aa72ae4cefbb29e18", "e7d2a1f9ca4041a8917648b5eec3c408"));
//		System.out.println(setSpecialRelation("181d4d3239e449b2938821243327048c", "6340428319bc4b0db3446725634f369b", "1"));
	}
	
}
