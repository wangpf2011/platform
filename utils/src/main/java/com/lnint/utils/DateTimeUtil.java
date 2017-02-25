package com.lnint.utils;

import android.annotation.SuppressLint;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeUtil {
	
	/**
	 * 根据开始日期、结束日期计算间隔天数
	 * @param start
	 * @param end
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static int getDays(String start, String end) {
		if(start == null || "".equals(start)) return 0;
		if(end == null || "".equals(end)) return 0;
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			Date date1 = sdf.parse(start); 
			Date date2 = sdf.parse(end); 
			GregorianCalendar cal1 = new GregorianCalendar(); 
			GregorianCalendar cal2 = new GregorianCalendar(); 
			cal1.setTime(date1); 
			cal2.setTime(date2); 
			long gap = (cal2.getTimeInMillis()-cal1.getTimeInMillis())/(1000*3600*24);//从间隔毫秒变成间隔天数</strong> 
			if(gap < 0) return 0;
			else return (int) (gap+1);
		}catch(Exception e) {
			e.printStackTrace();
			return 1;
		}
	}
	
	/**
	 * date+hours
	 * @param date
	 * @param hours
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getDate(String date, String hours) {
		double myhours = Double.parseDouble(hours);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date mydate=null;
		try {
			mydate = sdf.parse(date);
			Calendar ca = Calendar.getInstance();
			ca.setTime(mydate);
			double myhour = Math.floor(myhours);
			double mymin = Math.floor((myhours-myhour)*60);
			ca.add(Calendar.HOUR_OF_DAY, (int)myhour);
			ca.add(Calendar.MINUTE, (int)mymin);
			return sdf.format(ca.getTime());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static int compareDate(String date1, String date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if(dt1.getTime() > dt2.getTime()) {
				return 1;
			}else {
				return -1;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
