package org.sqldatabase.cronos.HubItems;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.sqldatabase.cronos.Main;

import net.md_5.bungee.api.ChatColor;

public class ServerSelector {
	
	static FileConfiguration config = Main.instance.getConfig();
	
	public static ItemStack selector() {
		Material material = Material.getMaterial(config.getString("Items.Selector.Item"));
		
		ItemStack selector = new ItemStack(material, 1);
		ItemMeta selectormeta = selector.getItemMeta();
		selectormeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', config.getString("Items.Selector.Name")));
		selector.setItemMeta(selectormeta);
		return selector;
	}

}