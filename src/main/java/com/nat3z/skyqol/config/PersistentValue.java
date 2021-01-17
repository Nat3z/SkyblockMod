package com.nat3z.skyqol.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class PersistentValue {

	private final File persistentValuesFile;
	private String apikey;
	
	public PersistentValue(File configDir) {
		this.persistentValuesFile = new File(configDir.getAbsolutePath() + "/sbmod_persistent.cfg");
	}
	
	public void loadValues() {
		if (this.persistentValuesFile.exists()) {
			try {
				FileReader reader = new FileReader(this.persistentValuesFile);
				JsonElement fileElement = new JsonParser().parse(reader);
				
				if (fileElement == null || fileElement.isJsonNull())
					throw new JsonParseException("File is Null!");
				
				JsonObject valuesObject = fileElement.getAsJsonObject();
				
				this.apikey = valuesObject.has("api") ? valuesObject.get("api").getAsString() : " ";
				
			} catch (Exception ex) {
				System.out.println("Nate's Skyblock Mod: There was an error while trying to load persistent values,");
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
			FileWriter writer = new FileWriter(this.persistentValuesFile);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);

			valuesObject.addProperty("api", this.apikey);
			bufferedWriter.write(valuesObject.toString());
			bufferedWriter.close();
		} catch (Exception ex) {
			System.out.println("Nate's Skyblock Mod: There was an error while trying to save persistent values,");
			ex.printStackTrace();
		}
	}
	public String getAPI() {
		return this.apikey;
	}
	public void setAPI(String apikey) {
		this.apikey = apikey;
		saveValues();
	}
	
}
