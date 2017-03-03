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
	 * ���������ŵ�webserviceͨ�Ŵ����û�token
	 * 
	 * @param accid Ϊ����ID,��󳤶�32�ַ�,���뱣֤һ��APP��Ψһ(ֻ������ĸ�����֡�����»���_��@����ǵ��Լ����-���,�����ִ�Сд)
	 * @return MD5����
	 * @throws Exception
	 */
	public static String getIMUserId(String accid,String username) throws Exception{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = "https://api.netease.im/nimserver/user/create.action";
		HttpPost httpPost = new HttpPost(url);
		
		String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);
		
		//���������header
		httpPost.addHeader("AppKey",appKey);//������ƽ̨�����appkey
		httpPost.addHeader("Nonce", nonce);//�����(��󳤶�128���ַ�)
		httpPost.addHeader("CurTime", curTime);//��ǰUTCʱ���,��1970��1��1��0��0 ��0 �뿪ʼ�����ڵ�����(String)
		httpPost.addHeader("CheckSum",checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//��������Ĳ���
		List<NameValuePair> vpns = new ArrayList<NameValuePair>();
		vpns.add(new BasicNameValuePair("accid", accid));
		vpns.add(new BasicNameValuePair("name", username));//����û��ǳ�
		httpPost.setEntity(new UrlEncodedFormEntity(vpns, "utf-8"));
		
		//ִ������
		HttpResponse response = httpClient.execute(httpPost);
		String imResult = EntityUtils.toString(response.getEntity(),"utf-8");
		String token = "";
		if(idJsonParse(imResult) != null){
			token = idJsonParse(imResult);
		}
		return token;
	}
	
	/**
	 * ����/����û�
	 * @param user_id
	 * @param lock ���� 1���� 0���
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
		
		//���������header
		httpPost.addHeader("AppKey",appKey);//������ƽ̨�����appkey
		httpPost.addHeader("Nonce", nonce);//�����(��󳤶�128���ַ�)
		httpPost.addHeader("CurTime", curTime);//��ǰUTCʱ���,��1970��1��1��0��0 ��0 �뿪ʼ�����ڵ�����(String)
		httpPost.addHeader("CheckSum",checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//��������Ĳ���
		List<NameValuePair> vpns = new ArrayList<NameValuePair>();
		vpns.add(new BasicNameValuePair("accid", user_id));
		httpPost.setEntity(new UrlEncodedFormEntity(vpns, "utf-8"));
		
		//ִ������
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
	 * ���ѹ�ϵ
	 * @param user_id
	 * @param f_user_id
	 * @param type //1ֱ�ӼӺ��ѣ�2����Ӻ��ѣ�3ͬ��Ӻ��ѣ�4�ܾ��Ӻ���
	 * @return
	 */
	public static boolean addFriends(String user_id, String f_user_id, String msg, String type) throws Exception{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = "https://api.netease.im/nimserver/friend/add.action";
		HttpPost httpPost = new HttpPost(url);
		
		String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);
		
		//���������header
		httpPost.addHeader("AppKey",appKey);//������ƽ̨�����appkey
		httpPost.addHeader("Nonce", nonce);//�����(��󳤶�128���ַ�)
		httpPost.addHeader("CurTime", curTime);//��ǰUTCʱ���,��1970��1��1��0��0 ��0 �뿪ʼ�����ڵ�����(String)
		httpPost.addHeader("CheckSum",checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//��������Ĳ���
		List<NameValuePair> vpns = new ArrayList<NameValuePair>();
		vpns.add(new BasicNameValuePair("accid", user_id));
		vpns.add(new BasicNameValuePair("faccid", f_user_id));
		vpns.add(new BasicNameValuePair("type", type));
		if("2".equals(type)){
			vpns.add(new BasicNameValuePair("msg", msg));//����Ӻ��ѵ�msg
		}
		httpPost.setEntity(new UrlEncodedFormEntity(vpns, "utf-8"));
		
		//ִ������
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
	 * ɾ�����ѹ�ϵ
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
		
		//���������header
		httpPost.addHeader("AppKey",appKey);//������ƽ̨�����appkey
		httpPost.addHeader("Nonce", nonce);//�����(��󳤶�128���ַ�)
		httpPost.addHeader("CurTime", curTime);//��ǰUTCʱ���,��1970��1��1��0��0 ��0 �뿪ʼ�����ڵ�����(String)
		httpPost.addHeader("CheckSum",checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//��������Ĳ���
		List<NameValuePair> vpns = new ArrayList<NameValuePair>();
		vpns.add(new BasicNameValuePair("accid", user_id));
		vpns.add(new BasicNameValuePair("faccid", f_user_id));
		httpPost.setEntity(new UrlEncodedFormEntity(vpns, "utf-8"));
		
		//ִ������
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
	 * �����û�
	 * @param user_id
	 * @param other_user_id
	 * @param value 0:ȡ��������������1:�������������
	 * @return
	 */
	public static boolean setSpecialRelation(String user_id, String other_user_id, String value) throws Exception{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = "https://api.netease.im/nimserver/user/setSpecialRelation.action";
		HttpPost httpPost = new HttpPost(url);
		
		String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);
		
		//���������header
		httpPost.addHeader("AppKey",appKey);//������ƽ̨�����appkey
		httpPost.addHeader("Nonce", nonce);//�����(��󳤶�128���ַ�)
		httpPost.addHeader("CurTime", curTime);//��ǰUTCʱ���,��1970��1��1��0��0 ��0 �뿪ʼ�����ڵ�����(String)
		httpPost.addHeader("CheckSum",checkSum);
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//��������Ĳ���
		List<NameValuePair> vpns = new ArrayList<NameValuePair>();
		vpns.add(new BasicNameValuePair("accid", user_id));
		vpns.add(new BasicNameValuePair("targetAcc", other_user_id));
		vpns.add(new BasicNameValuePair("relationType", "1"));//1:������������2:�����б����
		vpns.add(new BasicNameValuePair("value", value));
		httpPost.setEntity(new UrlEncodedFormEntity(vpns, "utf-8"));
		
		//ִ������
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
			System.out.println("��������idʱ��CheckSum����ʧ��(����401)��");
			return null;
		}
		if("414".equals(state)){
			System.out.println("�Ѿ�ע������id");
			return null;
		}
		if("200".equals(state)){
			/*
			 json�ṹ������������
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
		System.out.println("����������״̬��(401,200֮���)!!"+imResult);
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
