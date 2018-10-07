package org.sqldatabase.cronos.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;
import org.sqldatabase.cronos.Settings.Settings;

public class DoubleJump implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if((p.getGameMode() != GameMode.CREATIVE) && (p.getLocation().subtract(0,1,0).getBlock().getType() != Material.AIR) && (!p.isFlying())) {
			p.setAllowFlight(true);
		}
	}

	
	@EventHandler
	public void onPlayerToggleFlight(PlayerToggleFlightEvent e) {
		World world = Bukkit.getServer().getWorld(Settings.worldname);
		Player p = e.getPlayer();
		if(p.getGameMode() == GameMode.CREATIVE) return;
		e.setCancelled(true);
		p.setAllowFlight(false);
		p.setFlying(false);
		p.setVelocity(new Vector(0, 3, 0));
		p.playSound(p.getLocation(), Sound.EXPLODE, 10, 1);
		world.playEffect(p.getLocation().subtract(0, 1, 0), Effect.EXPLOSION_LARGE, 10);
	}
	
 }