package com.liancheng.it.util;

public class RegistCheckCode {
	private static String checkCode;

	public static String getCheckCode() {
			checkCode ="";
		for (int i=0;i<6;i++){
			int code = (int)(Math.random()*10);
			checkCode+=code;
		}
		return checkCode;
	}

	public static void main(String[] args) {
		System.out.println(getCheckCode());
	}
	
}
