package com.liancheng.it.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;



public class UUIDUtil {

	/**
	 * 利用UUID生成一个主键值
	 * @return 主键值
	 */
		public static String creatId(){
			UUID uuid = UUID.randomUUID();
			String id = uuid.toString();
			System.out.println(id.replace("-", ""));
			return id.replace("-", "");
		}
	/**
	 * 密码加密处理(MD5)
	 * 
	 * @param src 原密码
	 * @return		加密后的内容
	 * @throws Exception 
	 * @throws NoSuchAlgorithmException 
	 */
	public static String md5(String src) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] output = md.digest(src.getBytes());
		//将加密结果output转成字符串输出
		String srr = Base64.encodeBase64String(output);
		return new String(srr);
	}
	public static void main(String[] args) {
		
		System.out.println(creatId());
	}
}
