package org.sqldatabase.cronos.Listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;
import org.sqldatabase.cronos.Main;

import net.md_5.bungee.api.ChatColor;

public class CommandPreProcess implements Listener {
	
	
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		String command = e.getMessage().split(" ")[0];
		List<String> dcommands = Main.instance.getConfig().getStringList("DISABLED-COMMANDS");
		if(dcommands.contains(command)) {
			e.setCancelled(true);
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.instance.getConfig().getString("CMD-DISABLED")).replace("%command%", command));
		}
		
	    if (!e.isCancelled()) {
	        
	        HelpTopic htopic = Bukkit.getServer().getHelpMap().getHelpTopic(command);
	        if (htopic == null) {
	            p.sendMessage("§cUnknown Command");
	            e.setCancelled(true);
	        }
	    }
	}
	
}