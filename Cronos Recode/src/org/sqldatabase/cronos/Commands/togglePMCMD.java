package org.sqldatabase.cronos.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sqldatabase.cronos.Settings.Settings;

public class togglePMCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args) {
		Player p = (Player) sender;
		if(c.equalsIgnoreCase("togglepm")) {
			if(p.hasPermission("cronos.togglepm")) {
				if(MsgCMD.toggle.get(p.getUniqueId()) == true) {
					MsgCMD.toggle.put(p.getUniqueId(), false);
					p.sendMessage(Settings.privateoff);
				} else {
					MsgCMD.toggle.put(p.getUniqueId(), true);
					p.sendMessage(Settings.privateon);
				}
			}
		}
		return true;
	}

}