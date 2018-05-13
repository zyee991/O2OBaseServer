package com.o2o.util;

import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {
	private static Properties properties;
	
	static {
		properties = new Properties();
		try {
			properties.load(FtpUtil.class.getClassLoader().getResourceAsStream("application.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String get(String key) {
		return properties.getProperty(key);
	}
}
