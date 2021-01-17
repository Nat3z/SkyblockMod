package com.nat3z.skyqol;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class Config {

	private final File configValuesFile;
	private boolean modtype;
	private boolean antinonenchanted;
	private boolean unclaimedfarmingcontest;
	private boolean minionstats;
	private boolean warnpeopleforrareiteminsecretchest;

	public Config(File configDir) {
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
				this.minionstats = valuesObject.has("minionstats") ? valuesObject.get("minionstats").getAsBoolean() : false;
				this.warnpeopleforrareiteminsecretchest = valuesObject.has("warnpeopleforrareriteminsecretchest") ? valuesObject.get("warnpeopleforrareriteminsecretchest").getAsBoolean() : false;

			} catch (Exception ex) {
				System.out.println("Nate's Skyblock Mod: There was an error while trying to load Configuration values,");
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
			valuesObject.addProperty("minionstats", this.minionstats);
			valuesObject.addProperty("warnpeopleforrareriteminsecretchest", this.warnpeopleforrareiteminsecretchest);

			bufferedWriter.write(valuesObject.toString());
			bufferedWriter.close();
		} catch (Exception ex) {
			System.out.println("Nate's Skyblock Mod: There was an error while trying to save Configuration values,");
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
	
	public Boolean isMinionStats() {
		return this.minionstats;
	}
	
	public Boolean isWarnPeopleForRarerItemInSecretChest() {
		return this.warnpeopleforrareiteminsecretchest;
	}
	public void setWarnPeopleForRarerItemInSecretChest(Boolean isItEnabled) {
		this.warnpeopleforrareiteminsecretchest = isItEnabled;
		saveValues();
	}
	
	public void setMinionStats(Boolean isItEnabled) {
		this.minionstats = isItEnabled;
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
	
	
	
	// ==============================
	
	public void getButtonLocation(int id) {
		
		switch (id) {
			case 1:
				
		}
		
	}
}
