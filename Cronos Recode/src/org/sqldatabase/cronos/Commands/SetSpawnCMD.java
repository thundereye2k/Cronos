package org.sqldatabase.cronos.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sqldatabase.cronos.Settings.Settings;
import org.sqldatabase.cronos.Utils.CronosUtils;

public class SetSpawnCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args) {
		Player p = (Player) sender;
		if(c.equalsIgnoreCase("setspawn")) {
			if(sender instanceof Player) {
				if(p.hasPermission("hub.admin")) {
					CronosUtils.setSpawn(p);
					p.sendMessage("§aSpawn location set to your current location");
				} else {
					p.sendMessage(Settings.noperm);
				}
			}
		}
		return true;
	}

}