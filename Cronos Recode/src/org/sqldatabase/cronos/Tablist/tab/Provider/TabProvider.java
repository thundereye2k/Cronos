package org.sqldatabase.cronos.Tablist.tab.Provider;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.sqldatabase.cronos.Main;
import org.sqldatabase.cronos.Tablist.tab.TabAdapter;
import org.sqldatabase.cronos.Tablist.tab.TabTemplate;
import org.sqldatabase.cronos.Utils.Bungeecord;
import org.sqldatabase.cronos.Utils.CronosUtils;
import org.sqldatabase.cronos.Utils.EzQueueUtils;

import me.signatured.ezqueuespigot.util.EzQueueUtil;

public class TabProvider implements TabAdapter {
	
	private static FileConfiguration config = Main.instance.getConfig();
	private static String[] test;
	
    public TabTemplate getTemplate(Player player) {
        TabTemplate template = new TabTemplate();

        //FIRST COLUMN
        template.left(getTab("0-1", player));
        template.left(getTab("0-2", player));
        template.left(getTab("0-3", player));
        template.left(getTab("0-4", player));
        template.left(getTab("0-5", player));
        template.left(getTab("0-6", player));
        template.left(getTab("0-7", player));
        template.left(getTab("0-8", player));
        template.left(getTab("0-9", player));
        template.left(getTab("0-10", player));
        template.left(getTab("0-11", player));
        template.left(getTab("0-12", player));
        template.left(getTab("0-13", player));
        template.left(getTab("0-14", player));
        template.left(getTab("0-15", player));
        template.left(getTab("0-16", player));
        template.left(getTab("0-17", player));
        template.left(getTab("0-18", player));
        template.left(getTab("0-19", player));
        template.left(getTab("0-20", player));
        
      //SECOND COLUMN
        template.middle(getTab("1-1", player));
        template.middle(getTab("1-2", player));
        template.middle(getTab("1-3", player));
        template.middle(getTab("1-4", player));
        template.middle(getTab("1-5", player));
        template.middle(getTab("1-6", player));
        template.middle(getTab("1-7", player));
        template.middle(getTab("1-8", player));
        template.middle(getTab("1-9", player));
        template.middle(getTab("1-10", player));
        template.middle(getTab("1-11", player));
        template.middle(getTab("1-12", player));
        template.middle(getTab("1-13", player));
        template.middle(getTab("1-14", player));
        template.middle(getTab("1-15", player));
        template.middle(getTab("1-16", player));
        template.middle(getTab("1-17", player));
        template.middle(getTab("1-18", player));
        template.middle(getTab("1-19", player));
        template.middle(getTab("1-20", player));
        
        //Third Collumn
        template.right(getTab("2-1", player));
        template.right(getTab("2-2", player));
        template.right(getTab("2-3", player));
        template.right(getTab("2-4", player));
        template.right(getTab("2-5", player));
        template.right(getTab("2-6", player));
        template.right(getTab("2-7", player));
        template.right(getTab("2-8", player));
        template.right(getTab("2-9", player));
        template.right(getTab("2-10", player));
        template.right(getTab("2-11", player));
        template.right(getTab("2-12", player));
        template.right(getTab("2-13", player));
        template.right(getTab("2-14", player));
        template.right(getTab("2-15", player));
        template.right(getTab("2-16", player));
        template.right(getTab("2-17", player));
        template.right(getTab("2-18", player));
        template.right(getTab("2-19", player));
        template.right(getTab("2-20", player));
       
        //1.8
        template.farRight(getTab("3-1", player));
        template.farRight(getTab("3-2", player));
        template.farRight(getTab("3-3", player));
        template.farRight(getTab("3-4", player));
        template.farRight(getTab("3-5", player));
        template.farRight(getTab("3-6", player));
        template.farRight(getTab("3-7", player));
        template.farRight(getTab("3-8", player));
        template.farRight(getTab("3-9", player));
        template.farRight(getTab("3-10", player));
        template.farRight(getTab("3-11", player));
        template.farRight(getTab("3-12", player));
        template.farRight(getTab("3-13", player));
        template.farRight(getTab("3-14", player));
        template.farRight(getTab("3-15", player));
        template.farRight(getTab("3-16", player));
        template.farRight(getTab("3-17", player));
        template.farRight(getTab("3-18", player));
        template.farRight(getTab("3-19", player));
        template.farRight(getTab("3-20", player));
        
        return template;
    }
    
    public static String getTab(String s, Player p) {
    	String str = config.getString("Tablist." + s);
    	str = str.replace("%online%", "" + Main.globalcount);
    	str = str.replace("%ping%", "" + CronosUtils.getPing(p));
    	str = str.replace("%rank%", CronosUtils.getRank(p));
    	str = str.replace("%uptime%", CronosUtils.getUptime());
    	str = str.replace("%doublearrow%", CronosUtils.getUptime());
    	str = str.replace("%name%", p.getName());
    	str = str.replace("%username%", p.getName());
    	str = str.replace("%ign%", p.getName());
    	if(CronosUtils.getUptime() != null) {
    		str = str.replace("%playtime%", CronosUtils.getPlaytime(p.getUniqueId()));
    	}
    	if(Bungeecord.serverlist != test && Bungeecord.serverlist != null) {
    		for(String ls : Bungeecord.servers.keySet()) {
        		
        		str = str.replace("%playercount_" + ls + "%", "" + Bungeecord.servers.get(ls.toLowerCase()));
        		
        	}
    	}
    	return str;
    }
    
}
