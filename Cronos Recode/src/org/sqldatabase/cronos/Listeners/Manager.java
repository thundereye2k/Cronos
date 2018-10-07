package org.sqldatabase.cronos.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.sqldatabase.cronos.Main;

public class Manager {

	public static void setup() {
		PluginManager pm = Bukkit.getPluginManager();
		Plugin plugin = Main.instance;
		pm.registerEvents(new PlayerJoin(), plugin);
		pm.registerEvents(new PlayerLeave(), plugin);
		pm.registerEvents(new onChat(), plugin);
		pm.registerEvents(new ePlayerInteract(), plugin);
		pm.registerEvents(new DamageEvent(), plugin);
		pm.registerEvents(new DoubleJump(), plugin);
		pm.registerEvents(new InventoryClick(), plugin);
		pm.registerEvents(new InventoryDrag(), plugin);
		pm.registerEvents(new ItemDrop(), plugin);
		pm.registerEvents(new CommandPreProcess(), plugin);
		pm.registerEvents(new PlayerHit(), plugin);
		pm.registerEvents(new BlockBrake(), plugin);
		pm.registerEvents(new BlockPlace(), plugin);
		pm.registerEvents(new DismountEvent(), plugin);
	}
	
}