package br.com.gunter.crudweb.config;

import java.io.IOException;
import java.util.Properties;

public class ConfigUtil {
	private static Properties properties = new Properties();
	static {
		try (var input = ConfigUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
			properties.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
