package org.sqldatabase.cronos.Utils;

import org.sqldatabase.cronos.Main;

public class CronosAPI {
	
	public CronosAPI() {
		instance = this;
	}
	
	private static CronosAPI instance;
	
	
	/**
	 *  @param Used to get servers name
	 *  @return String
	 */
	public static String getServerName() {
		String name = Main.instance.getConfig().getString("Server-Name");
		return name;
	}
	
	/**
	 *  @param Used to get servers name
	 *  @return Integer
	 */
	public static Integer getOnlinePlayers() {
		Integer online = Main.globalcount;
		return online;
	}
	
}
