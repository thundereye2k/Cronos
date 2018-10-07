package org.sqldatabase.cronos.GUIS;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.sqldatabase.cronos.Main;
import org.sqldatabase.cronos.Utils.ItemBuilder;

import net.md_5.bungee.api.ChatColor;

public class ArmourGUI {

	public static HashMap<UUID, String> armourdata = new HashMap<>();
	public static String invname =  ChatColor.translateAlternateColorCodes('&', Main.instance.getConfig().getString("ARMOUR-CHANGER.GUI-TITLE"));
	
	public static void openGUI(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9, invname);
		if(p.hasPermission("hub.rank1.armour")) {
			inv.setItem(2, new ItemBuilder(Material.LEATHER_HELMET).addEnchant(Enchantment.DURABILITY, 5).toItemStack());
		} else {
			inv.setItem(2, new ItemBuilder(Material.LEATHER_HELMET).toItemStack());
		}
		
		
		if(p.hasPermission("hub.rank2.armour")) {
			inv.setItem(3, new ItemBuilder(Material.IRON_HELMET).addEnchant(Enchantment.DURABILITY, 5).toItemStack());
		} else {
			inv.setItem(3, new ItemBuilder(Material.IRON_HELMET).toItemStack());
		}
		
		
		if(p.hasPermission("hub.rank3.armour")) {
			inv.setItem(4, new ItemBuilder(Material.CHAINMAIL_HELMET).addEnchant(Enchantment.DURABILITY, 5).toItemStack());
		} else {
			inv.setItem(4, new ItemBuilder(Material.CHAINMAIL_HELMET).toItemStack());
		}
		
		
		if(p.hasPermission("hub.rank4.armour")) {
			inv.setItem(5, new ItemBuilder(Material.GOLD_HELMET).addEnchant(Enchantment.DURABILITY, 5).toItemStack());
		} else {
			inv.setItem(5, new ItemBuilder(Material.GOLD_HELMET).toItemStack());
		}
		
		
		if(p.hasPermission("hub.rank5.armour")) {
			inv.setItem(6, new ItemBuilder(Material.DIAMOND_HELMET).addEnchant(Enchantment.DURABILITY, 5).toItemStack());
		} else {
			inv.setItem(6, new ItemBuilder(Material.DIAMOND_HELMET).toItemStack());
		}
		
		p.openInventory(inv);
		
	}
	
	
}
