package org.sqldatabase.cronos.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.sqldatabase.cronos.Main;
import org.sqldatabase.cronos.HubItems.ItemManager;
import org.sqldatabase.cronos.Settings.Settings;
import org.sqldatabase.cronos.Utils.CronosUtils;

public class PlayerJoin implements Listener {

	long before;
	long after;
	
	static FileConfiguration playerdata = Main.playerdata.getConfiguration();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.sendMessage(Settings.getJoinMessage(p));
		e.setJoinMessage(null);
		before = System.currentTimeMillis();
		p.loadData();
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, Main.instance.getConfig().getInt("Default-Speed") - 1 ));
		p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, Main.instance.getConfig().getInt("Default-Jumpboost") - 1 ));
		after = System.currentTimeMillis();
		long calculated = after - before;
		p.sendMessage("§aProfile succesfully loaded in " + calculated + "ms");
		ItemManager.giveItems(p);
		for(Player pp : ePlayerInteract.hideplayers) {
			pp.hidePlayer(p);
		}
		if(p.hasPermission("hub.staff")) {
			for(Player pp : Bukkit.getOnlinePlayers()) {
				if(pp != p) {
					if(pp.hasPermission("hub.staff.notify")) {
						pp.sendMessage("§9[STAFF] §b" + p.getName() + " has logged online.");
					}
				}
			}
		}
		p.teleport(CronosUtils.getSpawn());
	}
	
}