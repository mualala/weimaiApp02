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

public class CreateIMId {

	
	/**
	 * ���������ŵ�webserviceͨ�Ŵ����û�token
	 * 
	 * @param accid Ϊ����ID,��󳤶�32�ַ�,���뱣֤һ��APP��Ψһ(ֻ������ĸ�����֡�����»���_��@����ǵ��Լ����-���,�����ִ�Сд)
	 * @return MD5����
	 * @throws Exception
	 */
	public static String getIMId(String accid,String username) throws Exception{
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String url = "https://api.netease.im/nimserver/user/create.action";
		HttpPost httpPost = new HttpPost(url);
		
		String appKey = "fbc784689ba0db58767462fd69859a83";
		String appSecret = "0c3145212d28";
		String nonce = String.valueOf(Math.random()*100000);
		String curTime = String.valueOf((new Date()).getTime() / 1000L);
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
		System.out.println("����������״̬��(401,200֮���)����"+imResult);
		return null;
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
		
//		String a = "{\"code\":200,\"info\":{\"token\":\"a6a0cb167dc429a0f3047b58cb6e354d\",\"accid\":\"10000bb\",\"name\":\"\"}}";
//		String b = "{'code':200,'info':100}";
		
		System.out.println(getIMId("10000bba","sh"));
		
//		System.out.println(idJsonParse(a));
		
//		JSONObject jsonObject = new JSONObject(b);
//		String aa = a.replaceAll("\"", "'");
//		JSONObject jsonObject02 = new JSONObject(aa);
//		System.out.println(aa);
//		JSONObject jsonObject03 = (JSONObject) jsonObject02.get("info");
//		System.out.println(jsonObject03);
//		System.out.println(jsonObject.get("code").toString());
	}
	
}
