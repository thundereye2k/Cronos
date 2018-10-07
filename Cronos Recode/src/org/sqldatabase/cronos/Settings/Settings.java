package org.sqldatabase.cronos.Settings;

import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.sqldatabase.cronos.Main;
import org.sqldatabase.cronos.Utils.CronosUtils;

import net.md_5.bungee.api.ChatColor;

public class Settings {
	
	static FileConfiguration config = Main.instance.getConfig();
	
	public static String worldname = config.getString("World-Name");
	public static String teamspeak = config.getString("Teamspeak");
	public static String servername = config.getString("Server-Name");
	public static String website = config.getString("Website");
	public static String discord = config.getString("Discord");
	public static String store = config.getString("Store");
	public static String noperm = ChatColor.translateAlternateColorCodes('&', config.getString("No-Permission"));
	public static String chatformat = ChatColor.translateAlternateColorCodes('&', config.getString("Chat-Format"));
	public static ArrayList<String> norainbowgui = new ArrayList<String>();
	public static String bottom = store;
	public static String part1 = bottom.substring(0, (bottom.length()/2));
	public static String part2 = bottom.substring(bottom.length()/2);
	public static String privateon = ChatColor.translateAlternateColorCodes('&', config.getString("Messaging-Settings.Toggle-On"));
	public static String privateoff = ChatColor.translateAlternateColorCodes('&', config.getString("Messaging-Settings.Toggle-Off"));
	
	public static String getJoinMessage(Player p) {
		String message = "";
		
		for(String s : config.getStringList("JoinMessage")) {
			String msg;
			msg = ChatColor.translateAlternateColorCodes('&', s);
			msg = msg.replace("%line%", "-----------------------------------------------------");
			msg = msg.replace("%player%", p.getName());
			msg = msg.replace("%rank%", CronosUtils.getRank(p));
			msg = msg.replace("%store%", store);
			msg = msg.replace("%servername%", servername);
			msg = msg.replace("%website%", website);
			msg = msg.replace("%discord%", discord);
			msg = msg.replace("%teamspeak%", teamspeak);
			msg = msg.replace("%doublearrow%", "»");
			message += msg + "\n"; 
		}
		return message;
	}
	
	
}