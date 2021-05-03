package com.nat3z.skyqol.config;

import java.io.File;
import java.util.HashMap;

import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.gui.GUI;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

public class Config {
	
	public static Configuration config;
	public final static String file = "config/Nate's Secret Mod.cfg";
	
	public static HashMap<String, Boolean> modules = new HashMap<>();
	
	@SuppressWarnings("static-access")
	public Config() {//
		this.modules.put("secrethud", true);
		this.modules.put("unclaimedfarmingcontests", true);
		this.modules.put("burrowpoints", true);
		this.modules.put("copydungeonfail", true);
		this.modules.put("maddoxautophone", true);
		this.modules.put("bonemerangwarn", true);
		this.modules.put("antinonenchanted", true);
		this.modules.put("chronomsolver", true);
		this.modules.put("superpairssolver", true);
		this.modules.put("ultraseqsolver", true);
		this.modules.put("weirdossolver", true);
		this.modules.put("dungeonreparty", true);
		this.modules.put("dungeonkicker", true);
		this.modules.put("puzzler", true);

		this.modules.put("profit", true);

		init();

	}
	
	public static void init() {
		config = new Configuration(new File(file));
		if (new File(file).exists()) {
			config.save();
			return;
		}
		try {
			config.load();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			config.save();
		}
	}
	
	public static int getInt(String category, String key) {
		config = new Configuration(new File(file));
		try {
			config.load();
			if (config.getCategory(category).containsKey(key)) {
				return config.get(category, key, 0).getInt();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			config.save();
		}
		return 0;
	}
	
	public static double getDouble(String category, String key) {
		config = new Configuration(new File(file));
		try {
			config.load();
			if (config.getCategory(category).containsKey(key)) {
				return config.get(category, key, 0D).getDouble();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			config.save();
		}
		return 0D;
	}
	
	public static String getString(String category, String key) {
		config = new Configuration(new File(file));
		try {
			config.load();
			if (config.getCategory(category).containsKey(key)) {
				return config.get(category, key, "").getString();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			config.save();
		}
		return "";
	}
	
	public static boolean getBoolean(String category, String key) {
		config = new Configuration(new File(file));
		try {
			config.load();
			if (config.getCategory(category).containsKey(key)) {
				return config.get(category, key, false).getBoolean();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			config.save();
		}
		return true;
	}

	public static void writeIntConfig(String category, String key, int value) {
		config = new Configuration(new File(file));
		try {
			config.load();
			int set = config.get(category, key, value).getInt();
			config.getCategory(category).get(key).set(value);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			config.save();
		}
	}
	
	public static void writeDoubleConfig(String category, String key, double value) {
		config = new Configuration(new File(file));
		try {
			config.load();
			double set = config.get(category, key, value).getDouble();
			config.getCategory(category).get(key).set(value);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			config.save();
		}
	}
	
	public static void writeStringConfig(String category, String key, String value) {
		config = new Configuration(new File(file));
		try {
			config.load();
			String set = config.get(category, key, value).getString();
			config.getCategory(category).get(key).set(value);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			config.save();
		}
	}
	
	public static void writeBooleanConfig(String category, String key, boolean value) {
		config = new Configuration(new File(file));
		try {
			config.load();
			boolean set = config.get(category, key, value).getBoolean();
			config.getCategory(category).get(key).set(value);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			config.save();
		}
	}
	
	public static boolean hasKey(String category, String key) {
		config = new Configuration(new File(file));
		try {
			config.load();
			if (!config.hasCategory(category)) return false;
			return config.getCategory(category).containsKey(key);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			config.save();
		}
		return false;
	}
	
	public static void deleteCategory(String category) {
		config = new Configuration(new File(file));
		try {
			config.load();
			if (config.hasCategory(category)) {
				config.removeCategory(new ConfigCategory(category));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			config.save();
		}
	}

	public static int initInt(String category, String key, int defaultValue) {
		if (!hasKey(category, key)) {
			writeIntConfig(category, key, defaultValue);
			return defaultValue;
		} else {
			return getInt(category, key);
		}
	}

	public static double initDouble(String category, String key, double defaultValue) {
		if (!hasKey(category, key)) {
			writeDoubleConfig(category, key, defaultValue);
			return defaultValue;
		} else {
			return getDouble(category, key);
		}
	}

	public static String initString(String category, String key, String defaultValue) {
		if (!hasKey(category, key)) {
			writeStringConfig(category, key, defaultValue);
			return defaultValue;
		} else {
			return getString(category, key);
		}
	}

	public static boolean initBoolean(String category, String key, boolean defaultValue) {
		if (!hasKey(category, key)) {
			writeBooleanConfig(category, key, defaultValue);
			return defaultValue;
		} else {
			return getBoolean(category, key);
		}
	}
	
	public void reloadConfig() {
		//
		Feature.ExperimentSuperpairs = initBoolean("feature", "SuperpairsSolver", true);
		Feature.ExperimentChronomatron = initBoolean("feature", "ChronoSolver", true);
		Feature.ExperimentUltrasequencer = initBoolean("feature", "ExperimentSequencer", true);

		//
		Feature.BurrowWaypoint = initBoolean("feature", "BurrowWaypoints", false);
		Feature.AnywhereSlayer = initBoolean("feature", "AnywhereSlayer", false);
		Feature.CopyFails = initBoolean("feature", "CopyFails", true);

		//
		Feature.AntiNonEnchanted = initBoolean("feature", "AntiNonEnchanted", true);
		Feature.FarmingContests = initBoolean("feature", "FarmingContests", true);
		Feature.SecretHud = initBoolean("feature", "SecretHud", true);
		Feature.DungeonReparty = initBoolean("feature", "DungeonReparty", true);
		Feature.Debug = initBoolean("feature", "Debug", false);

		Feature.BonemerangWarn = initBoolean("feature", "BonemerangWarn", true);

		//
		Feature.weirdossolver = initBoolean("feature", "WierdosSolver", true);
		Feature.MusicHud = initBoolean("music", "MusicHud", false);
		Feature.MusicPlayer = initBoolean("music", "MusicPlayer", false);
		Feature.PuzzlerSolver = initBoolean("feature", "PuzzlerSolver", true);
		Feature.DungeonProfits = initBoolean("feature", "profits", true);

		
		// Dungeons Kicker
		Feature.mage = initBoolean("dungeonkicker", "mage", true);
		Feature.archer = initBoolean("dungeonkicker", "archer", true);
		Feature.berserk = initBoolean("dungeonkicker", "berserk", true);
		Feature.tank = initBoolean("dungeonkicker", "tank", true);
		Feature.healer = initBoolean("dungeonkicker", "healer", true);
		
		Feature.minimumClassLevel = initInt("dungeonkicker", "ClassLevelMin", 0);
		
		// Move Gui Locations
		
		Feature.CopyFailsHUD[0] = initInt("locations", "CopyFailsX", 0);
		Feature.CopyFailsHUD[1] = initInt("locations", "CopyFailsY", 0);
		
		
		// Music Gui Locations
		
		Feature.MusicHUD[0] = initInt("locations", "MusicHudX", 0);
		Feature.MusicHUD[1] = initInt("locations", "MusicHudY", 20);

		// API
		Feature.apiKey = initString("api", "apiKey", "none");

		// Profit Between Locations
		Feature.ProfitHud = initBoolean("feature", "ProfitHud", false);
		
		Feature.ProfitHUD[0] = initInt("locations", "ProfitHudX", 0);
		Feature.ProfitHUD[1] = initInt("locations", "ProfitHudY", 0);
		
		
		// PERSISTENT BETWEEN LOAD
		Main.initLoad = initBoolean("persistent", "FIRSTLOAD", true);
		GUI.SkipGuiAnim = initBoolean("persistent", "SKIPGUI", false);
	}
}
