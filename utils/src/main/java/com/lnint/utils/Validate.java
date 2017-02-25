package com.lnint.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

	/**
	 * 身份证号校验
	 * @param str
	 * @return
	 */
	public static boolean checkNID(String str) {
		if(str == null || "".equals(str)) 
			return false;
		Pattern pattern = Pattern.compile("^\\d{15}|\\d{18}$");
		Matcher matchr = pattern.matcher(str);
		if(matchr.find()) 
			return true;
		
		return false;
    }
	
	/**
	 * 手机号校验
	 * @param str
	 * @return
	 */
	public static boolean checkMobile(String str) {
		if(str == null || "".equals(str)) 
			return false;
		Pattern pattern = Pattern.compile("^(1[34578])\\d{9}$");
		Matcher matchr = pattern.matcher(str);
		if(matchr.find()) 
			return true;
		
		return false;
	}
	
	/**
	 * 注册用户名校验
	 * @param str
	 * @return
	 */
	public static boolean checkUserName(String str) {
		if(str == null || "".equals(str)) 
			return false;
		Pattern pattern = Pattern.compile("^[a-zA-Z]{1}\\w{5,14}$");
		Matcher matchr = pattern.matcher(str);
		if(matchr.find()) 
			return true;
		
		return false;
	}
	
	/**
	 * 注册邮箱校验
	 * @param str
	 * @return
	 */
	public static boolean checkEmail(String str) {
		if(str == null || "".equals(str)) 
			return false;
		Pattern pattern = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
		Matcher matchr = pattern.matcher(str);
		if(matchr.find()) 
			return true;
		
		return false;
	}
	
	/**
	 * 数字校验（浮点数）
	 * @param str
	 * @return
	 */
	public static boolean checkNumber(String str) {
		if(str == null || "".equals(str)) 
			return false;
		Pattern pattern = Pattern.compile("^\\d+(\\.\\d+)?$");
		Matcher matchr = pattern.matcher(str);
		if(matchr.find()) 
			return true;
		
		return false;
	}
}
