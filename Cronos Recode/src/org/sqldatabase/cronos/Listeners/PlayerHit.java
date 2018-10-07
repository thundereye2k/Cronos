package org.sqldatabase.cronos.Listeners;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.sqldatabase.cronos.Main;

import net.md_5.bungee.api.ChatColor;

public class PlayerHit implements Listener {

	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		System.out.println("test");
		if (e.getDamager() instanceof Player) {
            Player damaged = (Player) e.getEntity();
            Player attacker = (Player) e.getDamager();
            String message = Main.instance.getConfig().getString("Player-Pop").replace("%player%", damaged.getName());
            attacker.hidePlayer(damaged);
            attacker.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            attacker.playSound(attacker.getLocation(), Sound.SUCCESSFUL_HIT, 10, 1);
            e.setCancelled(true);
        }
	}
	
}