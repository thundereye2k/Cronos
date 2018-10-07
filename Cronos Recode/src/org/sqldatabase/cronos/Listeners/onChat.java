package org.sqldatabase.cronos.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.sqldatabase.cronos.Main;
import org.sqldatabase.cronos.Settings.Settings;

import net.md_5.bungee.api.ChatColor;

public class onChat implements Listener {
	
	@EventHandler
	public void PlayerChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String chat = ChatColor.translateAlternateColorCodes('&', Settings.chatformat.replace("%player%", Main.getDisplayName(p))).replace("%message%", e.getMessage().replace("&", "& ")).replace("%doublearrow%", "»");
		e.setFormat(chat);
		
		
	}

}