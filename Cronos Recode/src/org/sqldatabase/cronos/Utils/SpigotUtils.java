package org.sqldatabase.cronos.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;

public class SpigotUtils {
	
	static String UUIDAPI = "https://api.mojang.com/users/profiles/minecraft/";
	
	public static String getUUID(Player p) {
		try {
			URL url = new URL(UUIDAPI + p.getName());
			url.openConnection();
			ArrayList<String> keys = new ArrayList<String>();
			URLConnection connection = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line = in.readLine();
			HashMap<?,?> uuid = new HashMap<>();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	public static String getUUID(String p) {
		
		
		return null;
	}
	
	

}
