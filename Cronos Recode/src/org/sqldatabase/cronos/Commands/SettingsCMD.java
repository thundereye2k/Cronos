package org.sqldatabase.cronos.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sqldatabase.cronos.GUIS.SettingsGUI;

public class SettingsCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args) {
		Player p = (Player) sender;
		if(c.equalsIgnoreCase("settings")) {
			if(p.hasPermission("hub.settings")) {
				if(sender instanceof Player) {
					SettingsGUI.openGUI(p);
				}
			}
		}
		return true;
	}

}