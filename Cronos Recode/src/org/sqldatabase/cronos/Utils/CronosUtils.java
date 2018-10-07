package org.sqldatabase.cronos.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.sqldatabase.cronos.Main;

import net.minecraft.util.org.apache.commons.lang3.text.WordUtils;

public class CronosUtils{
	
	private static FileConfiguration config = Main.instance.getConfig();
	public static String chatline = "§7§m-----------------------------------------------------";
	public static String loreline = "§7§m-----------------------------";
	public static Integer uptime = 0;
	public static HashMap<UUID, Integer> playtime = new HashMap<UUID, Integer>();
	
	public static void setSpawn(Player p) {
		int xi = p.getLocation().getBlockX();
		int y = p.getLocation().getBlockY();
		int zi = p.getLocation().getBlockZ();
		double x = xi + 0.5;
		double z = zi + 0.5;
		String world = p.getLocation().getWorld().getName();
		config.set("Spawn.x", x);
		config.set("Spawn.y", y);
		config.set("Spawn.z", z);
		config.set("World-Name", world);
		Main.instance.saveConfig();
		Main.instance.reloadConfig();
	}
	
	public static Location getSpawn() {
		double x = config.getDouble("Spawn.x");
		double y = config.getDouble("Spawn.y");
		double z = config.getDouble("Spawn.z");
		String World = config.getString("World-Name");
		Location location = new Location(Bukkit.getWorld(World), x, y, z);
		return location;
	}
	
	public static String getRank(Player p) {
		String color = Main.chat.getGroupSuffix(p.getWorld(), Main.perms.getPrimaryGroup(p).replaceAll("&", "§"));
		System.out.println(color);
		String rank = WordUtils.capitalize(Main.perms.getPrimaryGroup(p));
		return color + rank;
	}
	
	public static Integer getPing(Player p) {
		CraftPlayer cp = (CraftPlayer) p;
		int i = cp.getHandle().ping;
		
		return i;
	}
	
	public static String translateFromString(String text) {
        return StringEscapeUtils.unescapeJava(ChatColor.translateAlternateColorCodes('&', text));
    }

    public static List < String > translateFromArray(List < String > text) {
        List < String > messages = new ArrayList < String > ();
        for (String string: text) {
            messages.add(translateFromString(string));
        }
        return messages;
    }
    
    public static List<String> replaceFromArray(List<String> text, String oldChar, String newChar) {
        List < String > messages = new ArrayList < String > ();
        for (String string: text) {
            messages.add(string.replace(oldChar, newChar));
        }
        return messages;
    }

    public static String replaceALL(String str, String[] replaceStr, String replaceWithStr) {
    	String string = str;
    	
    	for(String s : replaceStr) {
    		string.replace(s, replaceWithStr);
    	}
    	
    	return string;
    }
    
    public static String getUptime() {
    	
    	long millis = uptime * 1000;
        String hms = String.format("%02dh %02dm %02ds", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        return "" + hms;
    }
    
    public static void AddUptime() {
    	uptime++;
    }
    
    public static void addPlaytime() {
    	for(Player pp : Bukkit.getOnlinePlayers()) {
    		if(playtime.keySet().contains(pp.getUniqueId())) {
    			int pt = playtime.get(pp.getUniqueId()) + 1;
    			playtime.put(pp.getUniqueId(), pt);
    		}
    	}
    }
     
    public static String getPlaytime(UUID id) {
    	if(!playtime.keySet().contains(id)) {
    		return null;
    	}
    	long millis = playtime.get(id) * 1000;
        String hms = String.format("%02dh %02dm %02ds", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        return "" + hms;
        
    }
    
}
