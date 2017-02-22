package com.liancheng.it.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;  
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.codec.binary.StringUtils;

public class StringUtil {
	public static final String GZIP_ENCODE_UTF_8 = "UTF-8";
	public static final String GZIP_ENCODE_ISO_8859_1 = "ISO-8859-1";
	
	/**
	 * 字符串压缩为GZIP字节数组
	 */
	public static byte[] compress(String str) {  
        return compress(str, GZIP_ENCODE_UTF_8);  
    } 
    
    /**
     * 字符串压缩为GZIP字节数组
     */
    public static byte[] compress(String str, String encoding) {
    	if (str == null || str.length() == 0) {  
            return null;  
        }  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        GZIPOutputStream gzip;  
        try {  
            gzip = new GZIPOutputStream(out);  
            gzip.write(str.getBytes(encoding));  
            gzip.close();  
        } catch (IOException e) {  
            System.out.println("gzip compress error.");  
        }  
        return out.toByteArray();
    }
    
    /**
     * GZIP解压缩成字节数组
     */
    public static byte[] unCompress(byte[] bytes) throws IOException{
    	if (bytes == null || bytes.length == 0) {  
            return null;  
        }  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);  
        try {  
            GZIPInputStream ungzip = new GZIPInputStream(in);  
            byte[] buffer = new byte[256];  
            int n;  
            while ((n = ungzip.read(buffer)) >= 0) {  
                out.write(buffer, 0, n);  
            }  
        } catch (IOException e) {  
            System.out.println("gzip uncompress error.");  
        }
        return out.toByteArray();
    }
    
    public static String uncompressToString(byte[] bytes) {  
        return uncompressToString(bytes, GZIP_ENCODE_UTF_8);  
    }
    
    /**
     * 将gzip解压缩为原始的字符串
     */
    public static String uncompressToString(byte[] bytes, String encoding) {
        if (bytes == null || bytes.length == 0) {  
            return null;  
        }  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);  
        try {  
            GZIPInputStream ungzip = new GZIPInputStream(in);  
            byte[] buffer = new byte[256];  
            int n;  
            while ((n = ungzip.read(buffer)) >= 0) {  
                out.write(buffer, 0, n);  
            }  
            return out.toString(encoding);  
        } catch (IOException e) {  
            System.out.println("gzip uncompress to string error.");  
        }  
        return null;  
    }
    
    /**
     * 将gzip解压缩为原始的字节数组
     */
    public static byte[] uncompress(byte[] bytes) {  
        if (bytes == null || bytes.length == 0) {  
            return null;  
        }  
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);  
        try {  
            GZIPInputStream ungzip = new GZIPInputStream(in);  
            byte[] buffer = new byte[256];  
            int n;  
            while ((n = ungzip.read(buffer)) >= 0) {  
                out.write(buffer, 0, n);  
            }  
        } catch (IOException e) {  
            System.out.println("gzip uncompress error.");  
        }  
  
        return out.toByteArray();  
    }
    
    public static void main(String[] args) throws IOException{
		String str = "哦ID还欠我IEuehwqiouoi231231231321";
		String str02 = "%5B%7B%22lastUpdateTime%22%3A%222011-10-28+9%3A39%3A41%22%2C%22smsList%22%3A%5B%7B%22liveState%22%3A%221";
		
		System.out.println("原长度：" + str.length());
		System.out.println("原始字符串："+str+"\n");
		
		byte[] ysStr = compress(str);
		System.out.println("压缩后的字符串长度："+ysStr.length);
		System.out.println("压缩后的字符串="+ysStr.toString());
		System.out.println("压缩后的字符串="+ysStr+"\n");
		
		
		String unStr = uncompressToString(ysStr);
		System.out.println("解压缩后的字符串长度："+unStr.length());
		System.out.println("解压缩后的字符串："+unStr);
		System.out.println("---<<"+uncompressToString(ysStr.toString().getBytes()));
		
	}
    
    
    
    
    
    
    
    
    /*
    public static String listToString(List list, String c){  
        StringBuilder sb = new StringBuilder();  
        for(int i=0; i<list.size(); i++){  
            sb.append(list.get(i)).append(c);  
        }  
        return sb.toString().substring(0,sb.toString().length()-1);  
    }*/
}  
