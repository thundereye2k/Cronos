package org.sqldatabase.cronos.Listeners;

import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;

public class DismountEvent implements Listener {
	
	@EventHandler
	public void playerDismount(EntityDismountEvent e) {
		Entity entity = e.getDismounted();
		if(entity instanceof EnderPearl) {
			entity.remove();
		}
	}

}