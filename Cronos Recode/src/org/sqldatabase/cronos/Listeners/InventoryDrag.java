package org.sqldatabase.cronos.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

public class InventoryDrag implements Listener {

	@EventHandler
	public void invDrag(InventoryDragEvent e) {
		Player p = (Player) e.getWhoClicked();
		e.setCancelled(true);
		p.updateInventory();
	}
	
}