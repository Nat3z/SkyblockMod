package com.nat3z.skyqol.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class APIString {

	private final File persistentValuesFile;
	private String apikey;
	private String mincat;


	public APIString(File configDir) {
		this.persistentValuesFile = new File(configDir.getAbsolutePath() + "/HYPIXELAPI_NSM.cfg");
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
				this.mincat = valuesObject.has("mincat") ? valuesObject.get("mincat").getAsString() : "0";

				
			} catch (Exception ex) {
				System.out.println("Nate's Secret Mod: There was an error while trying to load api values,");
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
			System.out.println("attempting save");
			FileWriter writer = new FileWriter(this.persistentValuesFile);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);

			valuesObject.addProperty("api", this.apikey);
			valuesObject.addProperty("mincat", this.mincat);

			
			bufferedWriter.write(valuesObject.toString());
			bufferedWriter.close();
			System.out.println("save complete");
		} catch (Exception ex) {
			System.out.println("Nate's Secret Mod: There was an error while trying to save api values,");
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
	
	public String getMincdk() {
		return this.mincat;
	}
	public void setMincdk(String apikey) {
		this.mincat = apikey;
		saveValues();
	}
	
}
