package org.sqldatabase.cronos.Listeners;

import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.sqldatabase.cronos.Main;
import org.sqldatabase.cronos.HubItems.EnderButt;

public class ProjectileThrow implements Listener {
	
	@EventHandler
	public void onProjectileLaunch(ProjectileLaunchEvent event) {
	    Entity entity = event.getEntity();
	    if(entity instanceof EnderPearl) {
	    	EnderPearl pearl = (EnderPearl) entity;
	    	LivingEntity source = pearl.getShooter();
	    	if(source instanceof Player) {
	    		Player p = (Player) source;
	    		pearl.setPassenger(p);
	    		new BukkitRunnable() {
					
					@Override
					public void run() {
						p.getInventory().setItem(Main.instance.getConfig().getInt("Items.EnderButt.slot") - 1, EnderButt.enderButt());
						
					}
				}.runTaskLater(Main.instance, 10L);
	    		p.sendMessage("h");
	    	}
	    }
	}

}