package com.lnint.utils;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;


public class XmlUtils {
	
	public static Map<String, String> xmlElements(String xmlDoc) {
		Map<String, String> result = new HashMap<String, String>();
		
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new StringReader(xmlDoc));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {
				switch (event) {
					case XmlPullParser.START_DOCUMENT:
						break;
					case XmlPullParser.START_TAG:
				        if("versionCode".equals(parser.getName())) {
					        result.put("versionCode", parser.getAttributeValue(null, "value"));
				        }else if("versionName".equals(parser.getName())) {
				        	result.put("versionName", parser.getAttributeValue(null, "value"));
				        }else if("downloadUrl".equals(parser.getName())) {
				        	result.put("downloadUrl", parser.getAttributeValue(null, "value"));
				        }
				        break;
					case XmlPullParser.END_TAG:
						break;
				}
				event = parser.next();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
    }
}
