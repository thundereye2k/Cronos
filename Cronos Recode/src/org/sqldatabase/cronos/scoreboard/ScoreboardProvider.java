package org.sqldatabase.cronos.scoreboard;

import org.bukkit.entity.*;
import org.sqldatabase.cronos.Main;
import org.sqldatabase.cronos.Utils.CronosUtils;
import org.sqldatabase.cronos.scoreboard.providers.ScoreboardEntryProvider;

import me.signatured.ezqueueshared.QueueInfo;
import net.md_5.bungee.api.ChatColor;

import java.util.*;

public class ScoreboardProvider implements ScoreboardEntryProvider
{
    private Main plugin;
    
    public ScoreboardProvider(final Main plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public List<String> getScoreboardEntries(final Player player) {
        final List<String> lines = new ArrayList<String>();
        if(!getMaxInQueue(player).equals("")) {
        	for(String s : Main.instance.getConfig().getStringList("SCOREBOARD.QUEUE-LINES")) {
            	s = s.replace("%online%", ""+ Main.globalcount);
            	s = s.replace("%rank%", CronosUtils.getRank(player));
            	s = s.replace("%queuename%", QueueInfo.getQueue(player.getName()));
            	s = s.replace("%position%", getQueue(player));
            	s = s.replace("%inqueue%", getMaxInQueue(player));
            	s = s.replace("%doublearrow%", "»");
            	lines.add(ChatColor.translateAlternateColorCodes('&', s));
            }
        } else {
        	for(String s : Main.instance.getConfig().getStringList("SCOREBOARD.LINES")) {
            	s = s.replace("%online%", ""+ Main.globalcount);
            	s = s.replace("%rank%", CronosUtils.getRank(player));
            	s = s.replace("%doublearrow%", "»");
            	lines.add(ChatColor.translateAlternateColorCodes('&', s));
        	}
        }
        return lines;
    }
    
    private static String getMaxInQueue(Player p) {
		if (QueueInfo.getQueue(p.getName()) == null) {
			return "";
		}
		return "" + QueueInfo.getQueueInfo(QueueInfo.getQueue(p.getName())).getSize();
	}

	private static String getQueue(Player p) {
		if (QueueInfo.getPosition(p.getName()) == -1) {
			return "";
		}
		return "" + QueueInfo.getPosition(p.getName());
	}
}
