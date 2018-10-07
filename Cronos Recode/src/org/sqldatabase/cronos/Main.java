package org.sqldatabase.cronos;

import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import org.sqldatabase.cronos.Commands.CronosCMD;
import org.sqldatabase.cronos.Commands.Dev123CMD;
import org.sqldatabase.cronos.Commands.MsgCMD;
import org.sqldatabase.cronos.Commands.ReplyCMD;
import org.sqldatabase.cronos.Commands.SetSpawnCMD;
import org.sqldatabase.cronos.Commands.SettingsCMD;
import org.sqldatabase.cronos.Commands.SoundsCMD;
import org.sqldatabase.cronos.Commands.SpawnCMD;
import org.sqldatabase.cronos.Commands.togglePMCMD;
import org.sqldatabase.cronos.Listeners.Manager;
import org.sqldatabase.cronos.Settings.PlayerSettings;
import org.sqldatabase.cronos.Settings.Settings;
import org.sqldatabase.cronos.Tablist.Azazel;
import org.sqldatabase.cronos.Tablist.tab.Provider.TabProvider;
import org.sqldatabase.cronos.Utils.Bungeecord;
import org.sqldatabase.cronos.Utils.Config;
import org.sqldatabase.cronos.Utils.CronosUtils;
import org.sqldatabase.cronos.scoreboard.ScoreboardProvider;
import org.sqldatabase.cronos.scoreboard.providers.ScoreboardManager;

import lombok.Getter;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

public class Main extends JavaPlugin {

    public static Boolean enabled;
    @Getter public static Main instance;
    @Getter public static Logger log = Bukkit.getLogger();
    @Getter public static Permission perms = null;
    @Getter public static Chat chat = null;
    @Getter public static String something;
    @Getter public static int globalcount;
    @Getter public static ArrayList<UUID> loggingin = new ArrayList<UUID>();
    Server server = Bukkit.getServer();
    @Getter public static Config selector;
    @Getter public static Config playerdata;
    @Getter private Azazel azazel;
    @Getter public static String loading = "○•••••";
    
    public void onEnable() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            log.severe(String.format("[%s] Disabled. Vault is missing!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        };
        setupConfig();
        instance = this;
        
        //initialise stuffs
        setupWorld();
        azazel = new Azazel(this, new TabProvider());
        registerCommands();
        new PlayerSettings(this);
        setupVault();
        new ScoreboardManager(this, new ScoreboardProvider(this), ChatColor.translateAlternateColorCodes('&', getConfig().getString("SCOREBOARD.TITLE")));
        Manager.setup();
        new Bungeecord().setup();
        new BukkitRunnable() {

            @Override
            public void run() {
            	CronosUtils.AddUptime();
            	CronosUtils.addPlaytime();
                for (Player pp : Bukkit.getOnlinePlayers()) {
                    if (pp.getLocation().getBlock().getType() == Material.WATER || pp.getLocation().getBlock().getType() == Material.STATIONARY_WATER) {
                        pp.teleport(CronosUtils.getSpawn());
                    }
                }
            }

        }.runTaskTimer(this, 0L, 20L);
        
        new BukkitRunnable() {

			@Override
			public void run() {
				if(loading == "○•••••") {
            		loading = "•○••••";
            	} else if(loading == "•○••••") {
            		loading = "••○•••";
            	} else if(loading == "••○•••") {
            		loading = "•••○••";
            	} else if(loading == "•••○••") {
            		loading = "••••○•";
            	} else if(loading == "••••○•") {
            		loading = "•••••○";
            	} else {
            		loading = "○•••••";
            	}
				
			}
        	
        }.runTaskTimer(this, 0L, 5L);
    }

    @Override
    public void onDisable() {
        for (UUID s : MsgCMD.sounds.keySet()) {
            String ns = s.toString();
            playerdata.getConfiguration().set(ns + ".SOUNDS", MsgCMD.sounds.get(s));
            playerdata.getConfiguration().set(ns + ".TOGGLED", MsgCMD.toggle.get(s));
            playerdata.getConfiguration().set(ns + ".PLAYTIME", CronosUtils.playtime.get(s));
            playerdata.saveConfig();
        }
    }


    public void registerCommands() {
        getCommand("togglepm").setExecutor(new togglePMCMD());
        getCommand("setspawn").setExecutor(new SetSpawnCMD());
        getCommand("spawn").setExecutor(new SpawnCMD());
        getCommand("dev123").setExecutor(new Dev123CMD());
        getCommand("cronos").setExecutor(new CronosCMD());
        getCommand("msg").setExecutor(new MsgCMD());
        getCommand("m").setExecutor(new MsgCMD());
        getCommand("message").setExecutor(new MsgCMD());
        getCommand("tell").setExecutor(new MsgCMD());
        getCommand("w").setExecutor(new MsgCMD());
        getCommand("r").setExecutor(new ReplyCMD());
        getCommand("reply").setExecutor(new ReplyCMD());
        getCommand("sounds").setExecutor(new SoundsCMD());
        getCommand("settings").setExecutor(new SettingsCMD());
    }

    public void setupConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        selector = new Config(this, "selector.yml", null);
        selector.saveDefaultConfig();
        playerdata = new Config(this, "player-data.yml", null);
        playerdata.saveDefaultConfig();
    }


    public void setupWorld() {
        World world = server.getWorld(Settings.worldname);
        world.setPVP(false);
        world.setDifficulty(Difficulty.PEACEFUL);
        world.setAnimalSpawnLimit(0);
        world.setAmbientSpawnLimit(0);
        world.setWeatherDuration(0);
        world.setMonsterSpawnLimit(0);
        world.setTime(0);
        world.setStorm(false);
    }

    public void setupVault() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = (Permission) rsp.getProvider();
        RegisteredServiceProvider<Chat> rsp1 = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp1.getProvider();
    }


    public static String getDisplayName(Player p) {
        String name;
        String prefix = chat.getGroupPrefix(Settings.worldname, perms.getPrimaryGroup(p));
        name = prefix + p.getName();
        return name;
    }

}
