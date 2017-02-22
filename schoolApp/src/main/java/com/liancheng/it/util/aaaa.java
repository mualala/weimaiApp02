package com.liancheng.it.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class aaaa {
	
	// 压缩   
	 public static String compress(String str) throws IOException {   
	    if (str == null || str.length() == 0) {   
	     return str;   
	   }
	    ByteArrayOutputStream out = new ByteArrayOutputStream();   
	    GZIPOutputStream gzip = new GZIPOutputStream(out);   
	     gzip.write(str.getBytes());   
	     gzip.close();   
	    return out.toString("ISO-8859-1");   
	   }
	// 解压缩   
	 public static String uncompress(String str) throws IOException {   
	    if (str == null || str.length() == 0) {   
	      return str;   
	  }   
	   ByteArrayOutputStream out = new ByteArrayOutputStream();   
	   ByteArrayInputStream in = new ByteArrayInputStream(str   
	        .getBytes("ISO-8859-1"));   
	    GZIPInputStream gunzip = new GZIPInputStream(in);   
	    byte[] buffer = new byte[256];   
	    int n;   
	   while ((n = gunzip.read(buffer))>= 0) {   
	    out.write(buffer, 0, n);   
	    }   
	    // toString()使用平台默认编码，也可以显式的指定如toString(&quot;GBK&quot;)   
	    return out.toString();
	  }
	// 测试方法   
	  public static void main(String[] args) throws IOException {   
	         
	      //测试字符串   
	     String str="sasa";
	      System.out.println("原长度："+str.length());
	      String aa = compress(str);
	      System.out.println("压缩后长度："+compress(str).length());
	      System.out.println("压缩后："+aa);
	         
	    System.out.println("解压缩："+uncompress("\u001F\b\u0000\u0000\u0000\u0000\u0000\u0000\u0000+N,N\u0004\u0000\n|/\u0004\u0000\u0000\u0000"));   
	  }
	 
}
