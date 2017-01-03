package com.liancheng.it.util;
  
  
import java.util.ArrayList;  
import java.util.List;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  
public class ImgUtil {  
    /** 
     * 从HTML源码中提取图片路径，最后以一个 String 类型的 List 返回，如果不包含任何图片，则返回一个 size=0　的List 
     * 需要注意的是，此方法只会提取以下格式的图片：.jpg|.bmp|.eps|.gif|.mif|.miff|.png|.tif|.tiff|.svg|.wmf|.jpe|.jpeg|.dib|.ico|.tga|.cut|.pic 
     * @param htmlCode HTML源码 
     * @return <img>标签 src 属性指向的图片地址的List集合 
     * @author Carl He 
     */  
    public static List<String> getImageSrc(String htmlCode) {
   	System.out.println(htmlCode);
        List<String> imageSrcList = new ArrayList<String>(); 
        Pattern p_p = Pattern.compile("ueditor/upload/image/[\\d]{8}/[\\d]{19}.(jpg|bmp|eps|gif|mif|miff|png|tif|tiff|svg|wmf|jpe|jpeg|dib|ico|tga|cut|pic)");

        Matcher m = p_p.matcher(htmlCode);  
          
        String src = null;  
        while (m.find()) { 
        	src = m.group();
        	System.out.println(src);

            imageSrcList.add(src);  
        }  
//        System.out.println(imageSrcList);
        return imageSrcList;  
    }  
    public static List<String> getVideoSrc(String htmlCode) {
//       	System.out.println(htmlCode);
            List<String> imageSrcList = new ArrayList<String>(); 
            Pattern p_p = Pattern.compile("ueditor/upload/video/[\\d]{8}/[\\d]{19}.(flv|swf|mkv|avi|rm|rmvb|mpg|ogg|ogv|mov|wmv|mp4|webm|mp3|wav|mid)");

            Matcher m = p_p.matcher(htmlCode);  
              
            String src = null;  
            while (m.find()) { 
            	src = m.group();
            	System.out.println(src);

                imageSrcList.add(src);  
            }  
//            System.out.println(imageSrcList);
            return imageSrcList;  
        }  
  
   public static void main(String[] args) {
	String str = "ueditor/upload/image/20161101/1477991235807033899.png";

	getStr(str);
}
   public static String getStr(String str){
	   Pattern p = Pattern.compile("ueditor/upload/image/[\\d]{8}/[\\d]{19}.(jpg|bmp|eps|gif|mif|miff|png|tif|tiff|svg|wmf|jpe|jpeg|dib|ico|tga|cut|pic)");
//	   Pattern p = Pattern.compile("ue.*(png|jpg|gif)");
//	   Pattern p = Pattern.compile("/[\\d]{8}/[\\d]{19}.png");
	   Matcher m = p.matcher(str);
	   
	   while(m.find()){  
		   System.out.println(m.group());  
		   } 
	return null;
	   
   }
}  