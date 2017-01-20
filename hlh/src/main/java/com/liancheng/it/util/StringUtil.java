package com.liancheng.it.util;

import java.util.List;  

public class StringUtil {  
    public static String listToString(List list, String c){  
        StringBuilder sb = new StringBuilder();  
        for(int i=0; i<list.size(); i++){  
            sb.append(list.get(i)).append(c);  
        }  
        return sb.toString().substring(0,sb.toString().length()-1);  
    }  
  
}  
