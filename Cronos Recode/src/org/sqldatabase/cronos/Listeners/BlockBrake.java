package org.sqldatabase.cronos.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBrake implements Listener {

	@EventHandler
	public void onBlockBrake(BlockBreakEvent e) {
		Player p = e.getPlayer();
			e.setCancelled(true);
			p.sendMessage("§cYou cannot build in the lobby.");
	}
	
}