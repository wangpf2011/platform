package com.lnint.common;

public class AppHelper {
	public static String UserId="";	

	public static String RoleId="";

	public static String UserName="";

	public static String UserPwd="";
	
	public static String LoginName="";

	//public static String WebserviceUrl="192.168.101.114";
	public static String SSLWebserviceUrl = PropUtils.getProperty("IP");
	public static String SSLWebPort = PropUtils.getProperty("SSLPORT");
	public static String SSLWebAppName = PropUtils.getProperty("APP");
	
	//public static String WebserviceUrl="192.168.101.114";
	//public static String WebPort="8888";
	public static String WebserviceUrl = PropUtils.getProperty("IP");
	public static String WebPort = PropUtils.getProperty("PORT");
	public static String WebAppName = PropUtils.getProperty("APP");
	
	
	public static String HTTPSRUL="https://"+SSLWebserviceUrl+":"+SSLWebPort+"/"+SSLWebAppName+"/";
	public static String HTTPRUL="http://"+WebserviceUrl+":"+WebPort+"/"+WebAppName+"/";
	
	public static int LongTime=20000;//
	
	
}

