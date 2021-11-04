package com.ejs.morevillagesmod;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import net.fabricmc.loader.api.FabricLoader;

public class Config {
	public static String FORT_VILLAGE_SPACING;
	public static String FORT_VILLAGE_SEPARATION;
	public static String FORT_VILLAGE_SALT;
	
	public static String MUSHROOM_SPACING;
	public static String MUSHROOM_SEPARATION;
	public static String MUSHROOM_SALT;
	
	public static String CITY_SPACING;
	public static String CITY_SEPARATION;
	public static String CITY_SALT;
	
	public static String LIGHTHOUSE_SPACING;
	public static String LIGHTHOUSE_SEPARATION;
	public static String LIGHTHOUSE_SALT;

	static {
		@SuppressWarnings("deprecation")
		File configDir = new File(FabricLoader.getInstance().getConfigDirectory(), "fabric");

		if (!configDir.exists()) {
			if (!configDir.mkdir()) {
				System.out.println("Could not create configuration directory: " + configDir.getAbsolutePath());
			}
		}

		File configFile = new File(configDir, "more-villages-mod-config.properties");
		Properties properties = new Properties();

		if (configFile.exists()) {
			try (FileInputStream stream = new FileInputStream(configFile)) {
				properties.load(stream);
				FORT_VILLAGE_SPACING = properties.getProperty("fortified_village_average_distance");
				FORT_VILLAGE_SEPARATION = properties.getProperty("fortified_village_min_distance");
				FORT_VILLAGE_SALT = properties.getProperty("fortified_village_salt");
				
				MUSHROOM_SPACING = properties.getProperty("mushroom_average_distance");
				MUSHROOM_SEPARATION = properties.getProperty("mushroom_min_distance");
				MUSHROOM_SALT = properties.getProperty("mushroom_salt");
				
				CITY_SPACING = properties.getProperty("city_average_distance");
				CITY_SEPARATION = properties.getProperty("city_min_distance");
				CITY_SALT = properties.getProperty("city_salt");
				
				LIGHTHOUSE_SPACING = properties.getProperty("lighthouse_average_distance");
				LIGHTHOUSE_SEPARATION = properties.getProperty("lighthouse_min_distance");
				LIGHTHOUSE_SALT = properties.getProperty("lighthouse_salt");
				
				if(!FORT_VILLAGE_SPACING.matches("[0-9]+")) FORT_VILLAGE_SPACING = "100";
				if(!FORT_VILLAGE_SEPARATION.matches("[0-9]+")) FORT_VILLAGE_SEPARATION = "8";
				if(!FORT_VILLAGE_SALT.matches("[0-9]+")) FORT_VILLAGE_SALT = "10236712";
				
				if(!MUSHROOM_SPACING.matches("[0-9]+")) MUSHROOM_SPACING = "100";
				if(!MUSHROOM_SEPARATION.matches("[0-9]+")) MUSHROOM_SEPARATION = "8";
				if(!MUSHROOM_SALT.matches("[0-9]+")) MUSHROOM_SALT = "10479212";
				
				if(!CITY_SPACING.matches("[0-9]+")) CITY_SPACING = "100";
				if(!CITY_SEPARATION.matches("[0-9]+")) CITY_SEPARATION = "40";
				if(!CITY_SALT.matches("[0-9]+")) CITY_SALT = "101512";
				
				if(!LIGHTHOUSE_SPACING.matches("[0-9]+")) LIGHTHOUSE_SPACING = "100";
				if(!LIGHTHOUSE_SEPARATION.matches("[0-9]+")) LIGHTHOUSE_SEPARATION = "40";
				if(!LIGHTHOUSE_SALT.matches("[0-9]+")) LIGHTHOUSE_SALT = "127693";

			} catch (IOException e) {
				System.out.println("Could not read property file '" + configFile.getAbsolutePath() + "'");
			}
		}

		FORT_VILLAGE_SPACING = (String) properties.computeIfAbsent("fortified_village_average_distance", (a) -> "100");
		FORT_VILLAGE_SEPARATION = (String) properties.computeIfAbsent("fortified_village_min_distance", (a) -> "8");
		FORT_VILLAGE_SALT = (String) properties.computeIfAbsent("fortified_village_salt", (a) -> "10236712");
		
		MUSHROOM_SPACING = (String) properties.computeIfAbsent("mushroom_average_distance", (a) -> "100");
		MUSHROOM_SEPARATION = (String) properties.computeIfAbsent("mushroom_min_distance", (a) -> "8");
		MUSHROOM_SALT = (String) properties.computeIfAbsent("mushroom_salt", (a) -> "10479212");
		
		CITY_SPACING = (String) properties.computeIfAbsent("city_average_distance", (a) -> "100");
		CITY_SEPARATION = (String) properties.computeIfAbsent("city_min_distance", (a) -> "40");
		CITY_SALT = (String) properties.computeIfAbsent("city_salt", (a) -> "101512");
		
		LIGHTHOUSE_SPACING = (String) properties.computeIfAbsent("lighthouse_average_distance", (a) -> "100");
		LIGHTHOUSE_SEPARATION = (String) properties.computeIfAbsent("lighthouse_min_distance", (a) -> "40");
		LIGHTHOUSE_SALT = (String) properties.computeIfAbsent("lighthouse_salt", (a) -> "127693");
		
		try (FileOutputStream stream = new FileOutputStream(configFile)) {
			properties.store(stream, "Properties file");
		} catch (IOException e) {
			System.out.println("Could not store property file '" + configFile.getAbsolutePath() + "'");
		}
	}
}