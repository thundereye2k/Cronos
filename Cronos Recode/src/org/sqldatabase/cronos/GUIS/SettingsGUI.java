package org.sqldatabase.cronos.GUIS;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.sqldatabase.cronos.Main;
import org.sqldatabase.cronos.Commands.MsgCMD;
import org.sqldatabase.cronos.Utils.CronosUtils;
import org.sqldatabase.cronos.Utils.ItemBuilder;

public class SettingsGUI {

	public static void openGUI(Player p) {
		try {
			Inventory inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', Main.instance.getConfig().getString("SETTINGS.GUI-TITLE")));
			
			if(MsgCMD.toggle.get(p.getUniqueId()) == true) {
				ArrayList<String> lore = new ArrayList<String>();
				lore.add(CronosUtils.loreline);
				lore.add("");
				lore.add("§7Toggles private message on/off.");
				lore.add("");
				lore.add(CronosUtils.loreline);
				ItemStack is = new ItemBuilder(Material.BOOK, 1).setName("§eToggle pm's §a(Enabled)").setLore(lore).toItemStack();
				inv.setItem(0, is);
			} else {
				ArrayList<String> lore = new ArrayList<String>();
				lore.add(CronosUtils.loreline);
				lore.add("");
				lore.add("§7Toggles private message on/off.");
				lore.add("");
				lore.add(CronosUtils.loreline);
				ItemStack is = new ItemBuilder(Material.BOOK, 1).setName("§eToggle pm's §c(Disabled)").setLore(lore).toItemStack();
				inv.setItem(0, is);
			}
			
			if(MsgCMD.sounds.get(p.getUniqueId()) == true) {
				ArrayList<String> lore = new ArrayList<String>();
				lore.add(CronosUtils.loreline);
				lore.add("");
				lore.add("§7Toggles message sounds on/off.");
				lore.add("");
				lore.add(CronosUtils.loreline);
				ItemStack is = new ItemBuilder(Material.JUKEBOX, 1).setName("§eToggle Sounds's §a(Enabled)").setLore(lore).toItemStack();
				inv.setItem(1, is);
			} else {
				ArrayList<String> lore = new ArrayList<String>();
				lore.add(CronosUtils.loreline);
				lore.add("");
				lore.add("§7Toggles message sounds on/off.");
				lore.add("");
				lore.add(CronosUtils.loreline);
				ItemStack is = new ItemBuilder(Material.JUKEBOX, 1).setName("§eToggle Sounds's §c(Disabled)").setLore(lore).toItemStack();
				inv.setItem(1, is);
			}
			
			
			
			
			
			
			
			
			
			
			p.openInventory(inv);
		
		} catch (NullPointerException e) {
			p.sendMessage("§cYour profile has not yet been loaded, Please relog.");
		} catch (Exception e) {
			p.sendMessage("§cAn error occured whilst loading your profile.");
		}
	}
}
