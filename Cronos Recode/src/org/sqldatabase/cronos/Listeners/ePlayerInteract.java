package org.sqldatabase.cronos.Listeners;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.sqldatabase.cronos.Main;
import org.sqldatabase.cronos.GUIS.GadgetsGUI;
import org.sqldatabase.cronos.GUIS.SelectorGUI;
import org.sqldatabase.cronos.HubItems.ItemManager;
import org.sqldatabase.cronos.HubItems.MagicClock;
import org.sqldatabase.cronos.Utils.Cooldown;

public class ePlayerInteract implements Listener {

	FileConfiguration config = Main.instance.getConfig();
	
	static ArrayList<Player> hideplayers = new ArrayList<Player>();
	
	@EventHandler
    public void enderButt(PlayerInteractEvent e) {
        Player p = e.getPlayer();
		Action action = e.getAction();
        if ((action == Action.RIGHT_CLICK_AIR) || (action == Action.RIGHT_CLICK_BLOCK)) {
            Player player = e.getPlayer();
            if (e.getMaterial() == Material.ENDER_PEARL) {
            	e.setCancelled(true);
            	if(p.isInsideVehicle()) {
            		if(p.getVehicle() != null) {
            			Entity entity1 = p.getVehicle();
                    	entity1.remove();
            		}
            	}
            	Item ep = player.getWorld().dropItem(player.getLocation().add(0.0D, 1.0D, 0.0D), new ItemStack(Material.ENDER_PEARL));
                ep.setPickupDelay(10000);
                ep.setVelocity(player.getLocation().getDirection().normalize().multiply(2.0F));
                ep.setPassenger(player);
                player.getWorld().playSound(player.getLocation(), Sound.SHOOT_ARROW, 1.0F, 0.7F);
                deleteItemWhenNeeded(p, ep);
                new BukkitRunnable() {
					
					@Override
					public void run() {
						ItemManager.giveItems(p);
						
					}
				}.runTaskLater(Main.instance, 1L);
            } else if (e.getMaterial() == Material.INK_SACK) {
            	if(!Cooldown.isInCooldown(p.getUniqueId(), "Clock")) {
            		if(hideplayers.contains(p)) {
                		hideplayers.remove(p);
                		p.sendMessage("§aPlayers are no longer hidden");
                		Cooldown c = new Cooldown(p.getUniqueId(), "Clock", 3);
                		c.start();
                		for(Player P : Bukkit.getOnlinePlayers()) {
                			if(P != p) {
                				p.showPlayer(P);
                			}
                		}
                		p.setItemInHand(MagicClock.hidePlayers());
                	} else {
                		hideplayers.add(p);
                		p.sendMessage("§aPlayers are now hidden");
                		Cooldown c = new Cooldown(p.getUniqueId(), "Clock", 3);
                		c.start();
                		for(Player P : Bukkit.getOnlinePlayers()) {
                			if(P != p) {
                				p.hidePlayer(P);
                			}
                			p.setItemInHand(MagicClock.showPlayers());
                		}
                	}
            	} else {
            		int time = Cooldown.getTimeLeft(p.getUniqueId(), "Clock");
                    p.sendMessage("§cYou must wait " + time + "s before using this again.");
            	}
            } else if (e.getMaterial() == Material.getMaterial(config.getString("Items.Selector.Item"))) {
            	SelectorGUI.openGUI(player);
            } else if (e.getMaterial() == Material.getMaterial(config.getString("Items.Gadgets.Item")) ){
            	GadgetsGUI.openInv(player);
            }
        }
    }
	

    public void deleteItemWhenNeeded(final Player player, final Item item) {
        new BukkitRunnable() {
            public void run() {
                item.getPassenger();
                if ((item.getVelocity().getX() == 0.0 || item.getVelocity().getY() == 0.0 || item.getVelocity().getZ() == 0.0) && !item.isDead()) {
                    if (item.getPassenger() != null) {
                        player.setFallDistance(0.1f);
                        this.cancel();
                    }
                    item.eject();
                    item.remove();
                }
            }
        }.runTaskTimer(Main.instance, 2L, 1L);
    }
    	
}