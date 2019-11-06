package com.pqc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {

	 //��װ���ڹ�����
	 private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 //����ת�ַ����ķ���
	 public static String dateToString(Date date) {
		 return sdf.format(date);
	 }
	 //�ַ���ת���ڵķ���
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
