package org.sqldatabase.cronos.HubItems;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.sqldatabase.cronos.Main;
import org.sqldatabase.cronos.Utils.ItemBuilder;

import net.md_5.bungee.api.ChatColor;

public class Gadgets {
	
	static FileConfiguration config = Main.instance.getConfig();
	
	public static ItemStack gadgets() {
		String name = ChatColor.translateAlternateColorCodes('&', config.getString("Items.Gadgets.Name"));
		Material m = Material.valueOf(config.getString("Items.Gadgets.Item"));
		ItemStack gadgets = new ItemBuilder(m, 1).setName(name).toItemStack();
		
		
		return gadgets;
	}

}
