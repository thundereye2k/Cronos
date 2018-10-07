package org.sqldatabase.cronos.HubItems;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.sqldatabase.cronos.Main;

import net.md_5.bungee.api.ChatColor;

public class EnderButt {

	static FileConfiguration config = Main.instance.getConfig();
	
	public static ItemStack enderButt() {
		ItemStack enderbutt = new ItemStack(Material.ENDER_PEARL, 1);
		ItemMeta enderbuttmeta = enderbutt.getItemMeta();
		enderbuttmeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString("Items.EnderButt.Name")));
		enderbutt.setItemMeta(enderbuttmeta);
		return enderbutt;
	}
	
}