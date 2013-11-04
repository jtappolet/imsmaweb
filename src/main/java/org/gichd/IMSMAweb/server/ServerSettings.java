package org.gichd.IMSMAweb.server;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.plist.PropertyListConfiguration;

public class ServerSettings {

	private static PropertyListConfiguration properties = null;
	private static String filePath = "server-settings.plist";
	
	static{
	
		try {
			properties = new PropertyListConfiguration(filePath);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void addSetting(String key, String value){
		
		properties.setProperty(key, value);
		try {
			properties.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String getSetting(String key){
		
		return properties.getString(key);
		
	}
	
	public static float getPropertyAsFloat(String key){
		
		return properties.getFloat(key);
		
	}
	
	public static double getPropertyAsDouble(String key){
		
		return properties.getDouble(key);
		
	}
	
	public static int getPropertyAsInteger(String key){
		
		return properties.getInt(key);
		
	}
	
}
