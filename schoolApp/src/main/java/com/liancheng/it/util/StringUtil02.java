package com.liancheng.it.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class StringUtil02 {
		// gzipÑ¹Ëõ   
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
		
		// gzip½âÑ¹Ëõ   
		public static String uncompress(String str) throws IOException {   
			if (str == null || str.length() == 0) {   
				return str;   
			}   
			ByteArrayOutputStream out = new ByteArrayOutputStream();   
			ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));   
			GZIPInputStream gunzip = new GZIPInputStream(in);
			byte[] buffer = new byte[256];   
		    int n;   
		    while ((n = gunzip.read(buffer))>= 0) {   
		    	out.write(buffer, 0, n);   
		    }   
		    return out.toString();   
		}
		
}
