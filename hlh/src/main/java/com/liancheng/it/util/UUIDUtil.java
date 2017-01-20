package com.liancheng.it.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;



public class UUIDUtil {

	/**
	 * ����UUID����һ������ֵ
	 * @return ����ֵ
	 */
		public static String creatId(){
			UUID uuid = UUID.randomUUID();
			String id = uuid.toString();
			return id.replace("-", "");
		}
	/**
	 * ������ܴ���(MD5)
	 * 
	 * @param src ԭ����
	 * @return		���ܺ������
	 * @throws Exception 
	 * @throws NoSuchAlgorithmException 
	 */
	public static String md5(String src) throws Exception {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] output = md.digest(src.getBytes());
			//�����ܽ��outputת���ַ������
			String srr = Base64.encodeBase64String(output);
			System.out.println(new String(srr));
			return new String(srr);
		} catch (NoSuchAlgorithmException e) {
			throw new Exception("�������ʧ��",e);
		}
		
		
	}
	public static void main(String[] args) {
		
		System.out.println(creatId());
	}
}