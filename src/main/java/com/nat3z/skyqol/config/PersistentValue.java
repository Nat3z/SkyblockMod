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
	
	private boolean bersdk;
	private boolean healerdk;
	private boolean archerdk;
	private boolean tankdk;
	private boolean magedk;


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
								
				this.bersdk = valuesObject.has("bersdk") ? valuesObject.get("bersdk").getAsBoolean() : true;
				this.magedk = valuesObject.has("magedk") ? valuesObject.get("magedk").getAsBoolean() : true;
				this.healerdk = valuesObject.has("healerdk") ? valuesObject.get("healerdk").getAsBoolean() : true;
				this.archerdk = valuesObject.has("archerdk") ? valuesObject.get("archerdk").getAsBoolean() : true;
				this.tankdk = valuesObject.has("tankdk") ? valuesObject.get("tankdk").getAsBoolean() : true;

				
				
			} catch (Exception ex) {
				System.out.println("Nate's Secret Mod: There was an error while trying to load persistent values,");
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

			valuesObject.addProperty("bersdk", this.bersdk);
			valuesObject.addProperty("magedk", this.magedk);
			valuesObject.addProperty("healerdk", this.healerdk);
			valuesObject.addProperty("archerdk", this.archerdk);
			valuesObject.addProperty("tankdk", this.tankdk);

			
			bufferedWriter.write(valuesObject.toString());
			bufferedWriter.close();
			System.out.println("save complete");
		} catch (Exception ex) {
			System.out.println("Nate's Secret Mod: There was an error while trying to save persistent values,");
			ex.printStackTrace();
		}
	}
	
	
	
	public boolean isBersdk() {
		return bersdk;
	}
	public void setBersdk(boolean bersdk) {
		this.bersdk = bersdk;
		saveValues();
	}
	
	public boolean isMagedk() {
		return magedk;
	}
	public void setMagedk(boolean magedk) {
		this.magedk = magedk;
		saveValues();
	}
	
	public boolean isHealerdk() {
		return healerdk;
	}
	public void setHealerdk(boolean healerdk) {
		this.healerdk = healerdk;
		saveValues();
	}

	public boolean isArcherdk() {
		return archerdk;
	}
	public void setArcherdk(boolean archerdk) {
		this.archerdk = archerdk;
		saveValues();
	}

	public boolean isTankdk() {
		return tankdk;
	}
	public void setTankdk(boolean tankdk) {
		this.tankdk = tankdk;
		saveValues();
	}
	
}
