package org.sqldatabase.cronos.HubItems;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.sqldatabase.cronos.Main;

import net.md_5.bungee.api.ChatColor;

public class MagicClock {

	static FileConfiguration config = Main.instance.getConfig();
	
	public static ItemStack showPlayers() {
		ItemStack showplayers = new ItemStack(Material.INK_SACK, 1, (short) 8);
		ItemMeta showplayersmeta = showplayers.getItemMeta();
		showplayersmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString("Items.MagicClock.Name-Show")));
		showplayers.setItemMeta(showplayersmeta);
		return showplayers;
	}
	
	public static ItemStack hidePlayers() {
		ItemStack showplayers = new ItemStack(Material.INK_SACK, 1, (short) 10);
		ItemMeta showplayersmeta = showplayers.getItemMeta();
		showplayersmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString("Items.MagicClock.Name-Hide")));
		showplayers.setItemMeta(showplayersmeta);
		return showplayers;
		
	}
	
}