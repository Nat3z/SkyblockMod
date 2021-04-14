package com.nat3z.skyqol;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class Config {

	private final File configValuesFile;
	private boolean modtype;
	private boolean antinonenchanted;
	private boolean unclaimedfarmingcontest;
	private boolean warnpeopleforrareiteminsecretchest;
	private boolean copydungeonfail;
	private boolean dungeonreparty;
	private boolean maddoxautophone;
	private boolean musicplayer;
	private boolean musicplayerhud;
	private boolean secrethud;

	public static HashMap<String, Boolean> modules = new HashMap<>();
	
	public Config(File configDir) {
		modules.put("antinonenchanted", true);
		modules.put("unclaimedfarmingcontests", true);
		modules.put("warnpeopleforrareriteminsecretchest", true);
		modules.put("copydungeonfail", true);
		modules.put("dungeonreparty", true);
		modules.put("maddoxautophone", true);
		modules.put("musicplayer", true);
		modules.put("secrethud", true);

		
		this.configValuesFile = new File(configDir.getAbsolutePath() + "/natemodsb.cfg");
	}
	
	public void loadValues() {
		if (this.configValuesFile.exists()) {
			try {
				FileReader reader = new FileReader(this.configValuesFile);
				JsonElement fileElement = new JsonParser().parse(reader);
				
				if (fileElement == null || fileElement.isJsonNull())
					throw new JsonParseException("File is Null!");
				
				JsonObject valuesObject = fileElement.getAsJsonObject();
				
				this.modtype = valuesObject.has("modtype") ? valuesObject.get("modtype").getAsBoolean() : false;
				this.antinonenchanted = valuesObject.has("antinonenchanted") ? valuesObject.get("antinonenchanted").getAsBoolean() : false;
				this.unclaimedfarmingcontest = valuesObject.has("unclaimedfarmingcontests") ? valuesObject.get("unclaimedfarmingcontests").getAsBoolean() : false;
				this.warnpeopleforrareiteminsecretchest = valuesObject.has("warnpeopleforrareriteminsecretchest") ? valuesObject.get("warnpeopleforrareriteminsecretchest").getAsBoolean() : false;
				this.copydungeonfail = valuesObject.has("copydungeonfail") ? valuesObject.get("copydungeonfail").getAsBoolean() : false;
				this.dungeonreparty = valuesObject.has("dungeonreparty") ? valuesObject.get("dungeonreparty").getAsBoolean() : false;
				this.maddoxautophone = valuesObject.has("maddoxautophone") ? valuesObject.get("maddoxautophone").getAsBoolean() : false;
				this.musicplayer = valuesObject.has("musicplayer") ? valuesObject.get("musicplayer").getAsBoolean() : false;
				this.musicplayerhud = valuesObject.has("musicplayerhud") ? valuesObject.get("musicplayerhud").getAsBoolean() : false;
				this.secrethud = valuesObject.has("secrethud") ? valuesObject.get("secrethud").getAsBoolean() : false;

			} catch (Exception ex) {
				System.out.println("Nate's Secret Mod: There was an error while trying to load Configuration values,");
				ex.printStackTrace();
				saveValues();
				
			}
			
		} else {
			saveValues();
		}
	}
	private void saveValues() {
		JsonObject valuesObject = new JsonObject();
		try {
			FileWriter writer = new FileWriter(this.configValuesFile);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);

			valuesObject.addProperty("modtype", this.modtype);
			valuesObject.addProperty("antinonenchanted", this.antinonenchanted);
			valuesObject.addProperty("unclaimedfarmingcontests", this.unclaimedfarmingcontest);
			valuesObject.addProperty("warnpeopleforrareriteminsecretchest", this.warnpeopleforrareiteminsecretchest);
			valuesObject.addProperty("copydungeonfail", this.copydungeonfail);
			valuesObject.addProperty("dungeonreparty", this.dungeonreparty);
			valuesObject.addProperty("maddoxautophone", this.maddoxautophone);
			valuesObject.addProperty("musicplayer", this.musicplayer);
			valuesObject.addProperty("musicplayerhud", this.musicplayerhud);
			valuesObject.addProperty("secrethud", this.secrethud);

			bufferedWriter.write(valuesObject.toString());
			bufferedWriter.close();
		} catch (Exception ex) {
			System.out.println("Nate's Secret Mod: There was an error while trying to save Configuration values.");
			ex.printStackTrace();
		}
	}
	public Boolean isModEnabled() {
		return this.modtype;
	}
	public Boolean isAntiNonEnchantedEnabled() {
		return this.antinonenchanted;
	}
	
	public Boolean isUnclaimedFarmingContest() {
		return this.unclaimedfarmingcontest;
	}
	public boolean isCopydungeonfail() {
		return copydungeonfail;
	}
	public void setCopydungeonfail(boolean copydungeonfail) {
		this.copydungeonfail = copydungeonfail;
		saveValues();
	}
	
	public boolean isDungeonreparty() {
		return dungeonreparty;
	}
	public void setDungeonreparty(boolean dungeonreparty) {
		this.dungeonreparty = dungeonreparty;
		saveValues();
	}
	
	public Boolean isWarnPeopleForRarerItemInSecretChest() {
		return this.warnpeopleforrareiteminsecretchest;
	}
	public void setWarnPeopleForRarerItemInSecretChest(Boolean isItEnabled) {
		this.warnpeopleforrareiteminsecretchest = isItEnabled;
		saveValues();
	}
	
	
	public void setUnclaimedFarmingContest(Boolean isItEnabled) {
		this.unclaimedfarmingcontest = isItEnabled;
		saveValues();
	}
	
	public void setModEnabled(Boolean isItEnabled) {
		this.modtype = isItEnabled;
		saveValues();
	}
	public void setAntiNonEnchantedEnabled(Boolean isItEnabled) {
		this.antinonenchanted = isItEnabled;
		saveValues();
	}
	
	public boolean isMaddoxautophone() {
		return maddoxautophone;
	}
	
	public void setMaddoxautophone(boolean maddoxautophone) {
		this.maddoxautophone = maddoxautophone;
		saveValues();
	}
	
	public boolean isMusicplayer() {
		return musicplayer;
	}
	
	public void setMusicplayer(boolean musicplayer) {
		this.musicplayer = musicplayer;
		saveValues();
	}
	
	public boolean isMusicplayerhud() {
		return musicplayerhud;
	}
	
	public void setMusicplayerhud(boolean musicplayerhud) {
		this.musicplayerhud = musicplayerhud;
		saveValues();
	}
	
	public boolean isSecrethud() {
		return secrethud;
	}
	
	public void setSecrethud(boolean secrethud) {
		this.secrethud = secrethud;
		saveValues();
	}

}
