package org.gichd.IMSMAweb.shared;

import org.gichd.IMSMAweb.server.ServerSettings;

public class TESTSettings {

	public static void main(String[] args) {
		ServerSettings.addSetting("test1", "testentry1");
		ServerSettings.addSetting("test2", "testentry2");
		System.out.println(ServerSettings.getSetting("test1"));
	}	

}
