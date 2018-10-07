package org.sqldatabase.cronos.HubItems;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.sqldatabase.cronos.Main;

public class ItemManager {
	
	static FileConfiguration config = Main.instance.getConfig();

	public static void giveItems(Player p) {
		PlayerInventory inv = p.getInventory();
		inv.clear();
		inv.setItem(config.getInt("Items.EnderButt.slot") - 1, EnderButt.enderButt());
		inv.setItem(config.getInt("Items.MagicClock.slot") - 1, MagicClock.hidePlayers());
		inv.setItem(config.getInt("Items.Selector.slot") - 1, ServerSelector.selector());
		inv.setItem(config.getInt("Items.Gadgets.SLOT") - 1, Gadgets.gadgets());
		p.updateInventory();
	}
	
}