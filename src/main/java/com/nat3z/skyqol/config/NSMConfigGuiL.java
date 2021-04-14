package com.nat3z.skyqol.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class NSMConfigGuiL {

	private final File persistentValuesFile;
	public int SongPlaylistLocationX;
	public int SongPlaylistLocationY;

	public int SecretsX;
	public int SecretsY;

	public NSMConfigGuiL(File configDir) {
		this.persistentValuesFile = new File(configDir.getAbsolutePath() + "/NSM_ConfigGuiLoc.cfg");
	}
	
	public void loadValues() {
		if (this.persistentValuesFile.exists()) {
			try {
				FileReader reader = new FileReader(this.persistentValuesFile);
				JsonElement fileElement = new JsonParser().parse(reader);
				
				if (fileElement == null || fileElement.isJsonNull())
					throw new JsonParseException("File is Null!");
				
				JsonObject valuesObject = fileElement.getAsJsonObject();
				
				this.SongPlaylistLocationX = valuesObject.has("SongPlaylistX") ? valuesObject.get("SongPlaylistX").getAsInt() : 0;
				this.SongPlaylistLocationY = valuesObject.has("SongPlaylistY") ? valuesObject.get("SongPlaylistY").getAsInt() : 0;

				this.SecretsX = valuesObject.has("SecretsX") ? valuesObject.get("SecretsX").getAsInt() : 0;
				this.SecretsY = valuesObject.has("SecretsY") ? valuesObject.get("SecretsY").getAsInt() : 0;
				
			} catch (Exception ex) {
				System.out.println("Nate's Secret Mod: There was an error while trying to load nsm gui values,");
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

			valuesObject.addProperty("SongPlaylistX", this.SongPlaylistLocationX);
			valuesObject.addProperty("SongPlaylistY", this.SongPlaylistLocationY);

			valuesObject.addProperty("SecretsX", this.SecretsX);
			valuesObject.addProperty("SecretsY", this.SecretsY);
			
			bufferedWriter.write(valuesObject.toString());
			bufferedWriter.close();
			System.out.println("attempting save");
		} catch (Exception ex) {
			System.out.println("Nate's Secret Mod: There was an error while trying to save nsm gui values,");
			ex.printStackTrace();
		}
	}
	
	public void setSongPlaylistLocation(int songPlaylistLocationX, int songPlaylistLocationY) {
		SongPlaylistLocationX = songPlaylistLocationX;
		SongPlaylistLocationY = songPlaylistLocationY;
		saveValues();
	}
	
	public void setSecretsLocation(int x, int y) {
		this.SecretsX = x;
		this.SecretsY = y;
		saveValues();
	}
	
}
