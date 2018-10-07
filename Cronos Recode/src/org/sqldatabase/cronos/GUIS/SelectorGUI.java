package org.sqldatabase.cronos.GUIS;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.sqldatabase.cronos.Main;
import org.sqldatabase.cronos.Utils.Bungeecord;
import org.sqldatabase.cronos.Utils.CronosUtils;

public class SelectorGUI {

	public static void openGUI(Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', Main.instance.getConfig().getString("SELECTOR.GUI-TITLE")));
		FileConfiguration selector = Main.selector.getConfiguration();
		for(String servers : selector.getKeys(false)) {
			Material material = Material.getMaterial(selector.getString(servers + ".MATERIAL"));
			String name = ChatColor.translateAlternateColorCodes('&', selector.getString(servers + ".NAME"));
			int slot = selector.getInt(servers + ".SLOT") - 1;
			List<String> lore = CronosUtils.replaceFromArray(CronosUtils.replaceFromArray(CronosUtils.replaceFromArray(CronosUtils.translateFromArray(selector.getStringList(servers + ".LORE")), "%doublearrow%", "»"), "%line%", "§7§m------------------------------"), "%online%", "" + Bungeecord.servers.get(selector.getString(servers + ".QUEUE-NAME")));
			
			
			ItemStack server = new ItemStack(material, 1);
			ItemMeta servermeta = server.getItemMeta();
			servermeta.setDisplayName(name);
			servermeta.setLore(lore);
			server.setItemMeta(servermeta);
			inv.setItem(slot, server);
		}
		
		
		p.openInventory(inv);
	}
}