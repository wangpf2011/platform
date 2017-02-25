package com.lnint.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Date工具类
 * @author wangpf
 *
 * @date:2014年12月4日 下午2:32:35
 * @version : v1.0
 */
@SuppressLint("SimpleDateFormat")
public class  DateUtils {
	
	private static final String FOAMAT_TYPE = "yyyy-MM-dd HH:mm:ss";

	public static boolean isToday(String date) {
		if(date == null || date.equals("")) return false;
		
		try {
			//yyyy-MM-dd HH:mm:ss
			SimpleDateFormat sdfFull = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date mydate = sdfFull.parse(date);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.format(mydate);

			String today = sdf.format(new Date());
			return today.equals(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@SuppressWarnings("static-access")
	public static String getNextday(String date) {
		if(date == null || date.equals("")) return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(date));
			c.add(c.DATE, 1);
			return sdf.format(c.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将MMddHHmm格式化为yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String formatDate(String date) {
		if(StringUtils.isEmpty(date) || date.length() != 8) return "";
		else {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			int year = c.get(Calendar.YEAR);
			return year+"-"+date.substring(0, 2)+"-"+date.substring(2, 4)+" "+date.substring(4, 6)+":"+date.substring(6)+":00";
		}
	}
	
	/** 
     * 两个时间之间相差多少分钟 
     * @param starttime 时间参数 1： 
     * @param endtime 时间参数 2： 
     * @return 相差分钟
     */  
    @SuppressLint("SimpleDateFormat")
	public static long getDistanceMin(String starttime, String endtime) {
        long min = 0;
        try {
        	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        	Date start = df.parse(starttime);  
        	Date end = df.parse(endtime);  
            long time1 = start.getTime();  
            long time2 = end.getTime();  
            long diff = time2 - time1;  
            /*if(time1<time2) {
                diff = time2 - time1;  
            }else {
                diff = time1 - time2;  
            }*/
            min = diff / (1000 * 60);  
        } catch (ParseException e) {  
            e.printStackTrace();  
            return min;
        }  
        return min;  
    } 
    
    /**
	 * @生成时间： 2015年4月30日 下午3:32:25
	 * @方法说明： 将毫秒值格式的日期转成yyyy-MM-dd HH:mm:ss格式的字符串
	 * @参数：seconds ： 毫秒值
	 * @返回值：
	 * @异常：NumberFormatException
	 **/
    public static String formatToDate(String seconds) throws NumberFormatException{
    	DateFormat dateFormat = new SimpleDateFormat(FOAMAT_TYPE);
    	long ss = Long.parseLong(seconds);
    	Date date = new Date(ss);
    	return dateFormat.format(date);
    }

	/**
	 * 日期时间转换
	 * yyyy-MM-dd HH:mm:ss --> yyyy-MM-dd
	 * @param datetime
	 * @return
	 */
	public static String dateConvert(String datetime) {
		if(StringUtils.isEmpty(datetime)) return "";

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(datetime);
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(date);
		}catch (Exception e) {
			e.printStackTrace();
			return datetime;
		}
	}
}
 