package org.sqldatabase.cronos.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sqldatabase.cronos.Settings.Settings;
import org.sqldatabase.cronos.Utils.CronosUtils;

public class SpawnCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args) {
		if(c.equalsIgnoreCase("spawn")) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				if(p.hasPermission("hub.spawn")) {
					p.teleport(CronosUtils.getSpawn());
					p.sendMessage("§aYou have been teleported to spawn");
				} else {
					p.sendMessage(Settings.noperm);
				}
			}
		}
		return true;
	}
}