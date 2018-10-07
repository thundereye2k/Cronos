package org.sqldatabase.cronos.Settings;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.sqldatabase.cronos.Main;
import org.sqldatabase.cronos.Commands.MsgCMD;
import org.sqldatabase.cronos.GUIS.ArmourGUI;
import org.sqldatabase.cronos.Utils.CronosUtils;

public class PlayerSettings implements Listener {

	static FileConfiguration playerdata = Main.playerdata.getConfiguration();
	public Main plugin;
	
	public PlayerSettings(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, this.plugin);
		loadUserData();
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		//MessageSounds
		if(MsgCMD.sounds.get(p.getUniqueId()) == null) {
			MsgCMD.sounds.put(p.getUniqueId(), true);
		}
		//ToggleMessages
		
		if(!MsgCMD.toggle.containsKey(p.getUniqueId())) {
			MsgCMD.toggle.put(p.getUniqueId(), true);
		}
		
		if(!CronosUtils.playtime.containsKey(p.getUniqueId())) {
			CronosUtils.playtime.put(p.getUniqueId(), 0);
		}
		
		
		//add player to settings
		if(!playerdata.getKeys(false).contains(p.getUniqueId().toString())) {
			playerdata.set(p.getUniqueId().toString() + ".SOUNDS", true);
			playerdata.set(p.getUniqueId().toString() + ".TOGGLED", true);
			playerdata.set(p.getUniqueId().toString() + ".PLAYTIME", 0);
			Main.playerdata.saveConfig();
		}
	}
	
	public void loadUserData() {
        for (String s : playerdata.getKeys(false)) {
            UUID uuid = UUID.fromString(s);
            Boolean value = playerdata.getBoolean(s + ".SOUNDS");
            MsgCMD.sounds.put(uuid, value);
            Boolean value1 = playerdata.getBoolean(s + ".TOGGLED");
            MsgCMD.toggle.put(uuid, value1);
            Integer value2 = playerdata.getInt(s + ".PLAYTIME");
            CronosUtils.playtime.put(uuid, value2);
        }
    }
	
}
