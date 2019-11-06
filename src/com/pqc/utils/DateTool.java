package com.pqc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {

	 //封装日期工具类
	 private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 //日期转字符串的方法
	 public static String dateToString(Date date) {
		 return sdf.format(date);
	 }
	 //字符串转日期的方法
	 public static Date stringToDate(String datestr) {
		 try {
			return sdf.parse(datestr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	 }
}
