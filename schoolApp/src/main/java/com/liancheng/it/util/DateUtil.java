package com.liancheng.it.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	 public static  String strDate(Date date)throws ParseException{
         SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date); 
	 }
	 public static  String formatDate(Date date)throws ParseException{
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date); 
	 }
	 public static Date parse(String strDate) throws ParseException{
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(strDate);
    }
	 
	 public static String currToDate(Long curr){
		 Date date = new Date(curr);
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 return sdf.format(date); 
	 }
	 
	 //2017-02-01日期格式的月的最大天数
	 public static int maxOfMonth(String strDate){
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 int maxDay = 0;
		try {
			Date date = sdf.parse(strDate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			return maxDay;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return maxDay;
	 }
	 
	 public static void main(String[] args) throws ParseException {
//		 Long time = System.currentTimeMillis();
//			Date date = new Date(time);
//		 String strDate = "2016-09-06 11:06:33";
//		System.out.println(DateUtil.strDate(date)+time);
//		System.out.println(DateUtil.parse(strDate));
//		System.out.println(DateUtil.formatDate(date));
//		String strArr = "["+"\""+"\""+"]";
//		System.out.println(strArr);
//		System.out.println(currToDate((Long)1481010503000));
		System.out.println(parse("2017-02-01 00:00:00").getTime());
	}
}

