package com.lnint.common;

import java.io.InputStream;
import java.util.Properties;

public class PropUtils {
	/**  
     * 得到netconfig.properties配置文件中的所有配置属性  
     *   
     * @return Properties对象  
     */  
    public static Properties getConfigProp() throws Exception {   
        Properties props = new Properties();
        //InputStream in = PropUtils.class.getResourceAsStream("/netconfig.properties");
        //props.load(MainApplication.getInstance().getResources().openRawResource(R.raw.netconfig));
		InputStream inStream = PropUtils.class.getClassLoader().getResourceAsStream("test.properties");
		props.load(inStream);
        return props;
    }
    
    public static String getProperty(String name) {
    	String result = "";
    	try {
    		result = getConfigProp().getProperty(name);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return result;
    }
}
