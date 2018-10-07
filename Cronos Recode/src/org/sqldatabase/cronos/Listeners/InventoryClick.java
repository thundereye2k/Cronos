package org.sqldatabase.cronos.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.sqldatabase.cronos.Main;
import org.sqldatabase.cronos.GUIS.ArmourGUI;
import org.sqldatabase.cronos.GUIS.SettingsGUI;
import org.sqldatabase.cronos.Utils.EzQueueUtils;

public class InventoryClick implements Listener {

	static FileConfiguration sc = Main.selector.getConfiguration();
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		ItemStack item = e.getCurrentItem();
		Player player = (Player) e.getWhoClicked();
		e.setCancelled(true);
        if (ChatColor.stripColor(e.getInventory().getName()).equalsIgnoreCase(Main.instance.getConfig().getString("SELECTOR.GUI-TITLE"))) {
        	e.setCancelled(true);
            if ((e.getCurrentItem() == null) ||
                (e.getCurrentItem().getType() == Material.AIR) ||
                (!e.getCurrentItem().hasItemMeta())) {
                player.closeInventory();
                return;
            }
            for(String selector : sc.getKeys(false)) {
            	if(item.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', sc.getString(selector + ".NAME")))) {
            		player.closeInventory();
            		if(!EzQueueUtils.getMaxInQueue(player).equals("")) {
            			player.sendMessage("§cYou are currently queued for " + EzQueueUtils.getQueue(player) + "!");
            			return;
            			}
            		EzQueueUtils.addToQueue(player, sc.getString(selector + ".QUEUE-NAME"));
            		Bukkit.dispatchCommand(player, "/play " + selector);
            			  
            	}
            }
        } else if(e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Main.instance.getConfig().getString("SETTINGS.GUI-TITLE")))) {
        	e.setCancelled(true);
            if ((e.getCurrentItem() == null) ||
                (e.getCurrentItem().getType() == Material.AIR) ||
                (!e.getCurrentItem().hasItemMeta())) {
                player.closeInventory();
                return;
            }
            if(item.getItemMeta().getDisplayName() == "§eToggle pm's §a(Enabled)" || item.getItemMeta().getDisplayName() == "§eToggle pm's §c(Disabled)") {
            	Bukkit.dispatchCommand(player, "togglepm");
            	SettingsGUI.openGUI(player);
            }
            if(item.getItemMeta().getDisplayName() == "§eToggle Sounds's §a(Enabled)" || item.getItemMeta().getDisplayName() == "§eToggle Sounds's §c(Disabled)") {
            	Bukkit.dispatchCommand(player, "sounds");
            	SettingsGUI.openGUI(player);
            }
        } else if(e.getInventory().getName().equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', Main.instance.getConfig().getString("GADGETS.GUI-TITLE")))) {
        	e.setCancelled(true);
            if ((e.getCurrentItem() == null) ||
                (e.getCurrentItem().getType() == Material.AIR) ||
                (!e.getCurrentItem().hasItemMeta())) {
                player.closeInventory();
                return;
            }
            
            if(item.getType() == Material.LEATHER_HELMET) {
            	ArmourGUI.openGUI(player);
            }
            
            
        } else if(e.getInventory().getName().equalsIgnoreCase(ArmourGUI.invname)) {
        	e.setCancelled(true);
            if ((e.getCurrentItem() == null) ||
                (e.getCurrentItem().getType() == Material.AIR) ||
                (!e.getCurrentItem().hasItemMeta())) {
                player.closeInventory();
                return;
            }
            
            if(item.getType() == Material.LEATHER_HELMET) {
            	player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.instance.getConfig().getString("ARMOUR-CHANGER.TYPE-SET").replace("%armour%", "Leather")));
            	player.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET));
            	player.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
            	player.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
            	player.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS));
            	return;
            } else if(item.getType() == Material.IRON_HELMET) {
            	player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.instance.getConfig().getString("ARMOUR-CHANGER.TYPE-SET").replace("%armour%", "Iron")));
            	player.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
            	player.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
            	player.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
            	player.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS));
            	return;
            } else if(item.getType() == Material.CHAINMAIL_HELMET) {
            	player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.instance.getConfig().getString("ARMOUR-CHANGER.TYPE-SET").replace("%armour%", "ChainMail")));
            	player.getInventory().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
            	player.getInventory().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
            	player.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_HELMET));
            	player.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
            	return;
            } else if(item.getType() == Material.GOLD_HELMET) {
            	player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.instance.getConfig().getString("ARMOUR-CHANGER.TYPE-SET").replace("%armour%", "Gold")));
            	player.getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET));
            	player.getInventory().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
            	player.getInventory().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
            	player.getInventory().setBoots(new ItemStack(Material.GOLD_BOOTS));
            	return;
            } else if(item.getType() == Material.DIAMOND_HELMET) {
            	player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.instance.getConfig().getString("ARMOUR-CHANGER.TYPE-SET").replace("%armour%", "Diamond")));
            	player.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
            	player.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
            	player.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
            	player.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
            	return;
            }
        }
	}

}