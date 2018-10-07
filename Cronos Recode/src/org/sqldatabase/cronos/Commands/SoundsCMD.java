package org.sqldatabase.cronos.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SoundsCMD implements CommandExecutor {

	//https://pornhub.com
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args) {
		Player p = (Player) sender;
		if(c.equalsIgnoreCase("sounds")) {
			if(MsgCMD.sounds.get(p.getUniqueId()).equals(true)) {
				MsgCMD.sounds.remove(p.getUniqueId());
				MsgCMD.sounds.put(p.getUniqueId(), false);
				p.sendMessage("§7You will no longer hear message sounds.");
			} else {
				MsgCMD.sounds.remove(p.getUniqueId());
				MsgCMD.sounds.put(p.getUniqueId(), true);
				p.sendMessage("§7You will now hear message sounds.");
			}
		}
		return true;
	}

}
