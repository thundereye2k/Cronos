package org.sqldatabase.cronos.Commands;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.sqldatabase.cronos.Main;
import org.sqldatabase.cronos.Settings.Settings;
import net.md_5.bungee.api.ChatColor;

public class MsgCMD implements CommandExecutor {

	static FileConfiguration config = Main.instance.getConfig();
	public static HashMap<UUID, String> lastplayer = new HashMap<UUID, String>();
	public static HashMap<UUID, Boolean> sounds = new HashMap<UUID, Boolean>();
	public static HashMap<UUID, Boolean> toggle = new HashMap<UUID, Boolean>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String c, String[] args) {
		Player p = (Player) sender;
		if(c.equalsIgnoreCase("msg") 
				|| c.equalsIgnoreCase("m")
				|| c.equalsIgnoreCase("message") 
				|| c.equalsIgnoreCase("tell") 
				|| c.equalsIgnoreCase("w")) {
			if(p.hasPermission("hub.msg")) {
				if(args.length == 0) {
					p.sendMessage("§cUsage: /" + c + " <Player> <Message>");
				} else if(args.length == 1) {
					p.sendMessage("§cUsage: /" + c + " <Player> <Message>");
				} else if(args.length >= 2) {
					String message = "";
					for (int i = 1; i < args.length; i++) {
					    if (message != "") message += " ";
					    String part = args[i];
					    message += part;
					}
					Player target = Bukkit.getPlayer(args[0]);
					if(target != null) {
						if(target.isOnline()) {
							if(toggle.get(p.getUniqueId()) == true) {
								if(toggle.get(target.getUniqueId()) == true) {
									target.sendMessage(ChatColor.translateAlternateColorCodes('&', 
											config.getString("Messaging-Settings.From-Format").
											replace("%player%", Main.getDisplayName(p)).
											replace("%msg%", message)));
									p.sendMessage(ChatColor.translateAlternateColorCodes('&', 
											config.getString("Messaging-Settings.To-Format").
											replace("%player%", Main.getDisplayName(p))).
											replace("%msg%", message));
									lastplayer.put(p.getUniqueId(), target.getName());
									lastplayer.put(target.getUniqueId(), p.getName());
									if(sounds.get(target.getUniqueId()) == true) {
										target.playSound(target.getLocation(), Sound.SUCCESSFUL_HIT, 10, 1);
									}
								} else {
									p.sendMessage("§c" + target.getName() + " has private messages off.");
								}
							} else {
								p.sendMessage("§cYou cannot message whilst private messages are off.");
							}
						} else {
							p.sendMessage("§cThat player is not online.");
						}
					} else {
						p.sendMessage("§cThat player is not online.");
					}
				}
			} else {
				p.sendMessage(Settings.noperm);
			}
		}
		return true;
	}

}