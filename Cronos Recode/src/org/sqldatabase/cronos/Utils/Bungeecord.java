package org.sqldatabase.cronos.Utils;

import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;
import org.sqldatabase.cronos.Main;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class Bungeecord implements PluginMessageListener {

    public static Bungeecord instance;

    public static HashMap < String, Integer > servers = new HashMap < String, Integer > ();
    static FileConfiguration selector = Main.selector.getConfiguration();
    public static HashMap <String,String> status = new HashMap<String,String>();
    public static String[] serverlist = null;
    
    public void setup() {
    	new BukkitRunnable() {

    		
			@Override
			public void run() {
				for(Player pp : Bukkit.getOnlinePlayers()) {
					getServerCount(pp, "ALL");
					getServers(pp);
					servers.get("servername");
					if(serverlist != null) {
						for(String selector : serverlist) {
							getServerCount(pp, selector);
						}
					}
				}
				
			}
    		
    	}.runTaskTimer(Main.instance, 0L, 5L);
    	Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(Main.instance, "BungeeCord");
        Bukkit.getServer().getMessenger().registerIncomingPluginChannel(Main.instance, "BungeeCord", this);
        
    }


    public static void getServerCount(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerCount");
        out.writeUTF(server);
        player.sendPluginMessage(Main.instance, "BungeeCord", out.toByteArray());
        //actualy
    }

    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (channel.equals("BungeeCord")) {
            ByteArrayDataInput input = ByteStreams.newDataInput(message);
            String subchannel = input.readUTF();
            if (subchannel.equals("PlayerCount")) {
                String serverName = input.readUTF();
                int playerCount = input.readInt();
                if(serverlist != null) {
                	for(String selector : Bungeecord.serverlist) {
    					if(serverName.equalsIgnoreCase(selector)) {
    						servers.put(selector.toLowerCase(), playerCount);
    	                }
                    }
                }
                if (serverName.equalsIgnoreCase("ALL")) {
                    Main.globalcount = playerCount;
                }
            }
            if(subchannel.equals("GetServers")) {
            	serverlist = input.readUTF().split(", ");
            	
            }
        }
    }

    public void playerConnect(Player p, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("ConnectOther");
        out.writeUTF(p.getName());
        out.writeUTF(server);
    }  
    
    public static void getServers(Player player) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("GetServers");
        player.sendPluginMessage(Main.instance, "BungeeCord", out.toByteArray());
    }
    
    
}