package org.sqldatabase.cronos.GUIS;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.sqldatabase.cronos.Main;
import org.sqldatabase.cronos.Utils.ItemBuilder;

public class GadgetsGUI {

	static FileConfiguration config = Main.instance.getConfig();
	
	public static void openInv(Player p) {
		String guiname = ChatColor.translateAlternateColorCodes('&', config.getString("GADGETS.GUI-TITLE"));
		Inventory inv = Bukkit.createInventory(null, 27, guiname);
		
		inv.setItem(10, new ItemBuilder(Material.LEATHER_HELMET).setName("§eArmour Changer").toItemStack());
		
		
		
		
		
		p.openInventory(inv);
	}
	
}
