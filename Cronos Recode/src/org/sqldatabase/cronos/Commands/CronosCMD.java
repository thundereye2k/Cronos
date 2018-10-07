package org.sqldatabase.cronos.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.sqldatabase.cronos.Main;
import org.sqldatabase.cronos.Utils.CronosUtils;

public class CronosCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args) {
		Player p = (Player) sender;
		if(c.equalsIgnoreCase("cronos")) {
			if(args.length == 0) {
				p.sendMessage(CronosUtils.chatline);
				p.sendMessage("");
				p.sendMessage(" §6» §eAuthor: readUTF");
				p.sendMessage(" §6» §eAuthor: " + Main.instance.getDescription().getVersion());
				p.sendMessage("");
				p.sendMessage(CronosUtils.chatline);
			} else if (args.length == 1) {
				if(args[0].equalsIgnoreCase("reload")) {
					if(sender.hasPermission("hub.admin")) {
						long before = System.currentTimeMillis();
						Main.instance.reloadConfig();
						Main.selector.reloadConfig();
						long after = System.currentTimeMillis();
						long calculated = after - before;
						p.sendMessage("§aConfigs successfuly reloaded in " + calculated + "ms");
					}
				}
			}
		}
		return true;
	}

}