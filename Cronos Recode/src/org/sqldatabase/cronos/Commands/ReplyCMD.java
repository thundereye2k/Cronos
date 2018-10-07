package org.sqldatabase.cronos.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.sqldatabase.cronos.Main;
import org.sqldatabase.cronos.Settings.Settings;

import net.md_5.bungee.api.ChatColor;

public class ReplyCMD implements CommandExecutor {

	static FileConfiguration config = Main.instance.getConfig();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args) {
		Player p = (Player) sender;
		if(c.equalsIgnoreCase("r") || c.equalsIgnoreCase("reply")) {
			if(p.hasPermission("hub.reply")) {
				if(args.length == 0) {
					p.sendMessage("§cUsage: /" + c + " <Message..>");
				} else if (args.length >= 1) {
					String message = "";
					for (int i = 0; i < args.length; i++) {
					    if (message != "") message += " ";
					    String part = args[i];
					    message += part;
					}
					if(MsgCMD.lastplayer.get(p.getUniqueId()) != null) {
						Player target = Bukkit.getPlayer(MsgCMD.lastplayer.get(p.getUniqueId()));
						if(target != null) {
							if(target.isOnline()) {
								target.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("Messaging-Settings.From-Format").replace("%player%", p.getName()).replace("%msg%", message)));
								p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("Messaging-Settings.To-Format").replace("%player%", target.getName()).replace("%msg%", message)));
							} else {
								p.sendMessage("§cThat player is not online.");
							}
						} else {
							p.sendMessage("§cThat player is not online.");
						}
					} else {
						p.sendMessage("§cYou have not messaged anyone.");
					}
				}
			} else {
				p.sendMessage(Settings.noperm);
			}
		}
		return true;
	}

}