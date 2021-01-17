package me.nat3z.api;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nat3z.skyqol.Config;
import com.nat3z.skyqol.Main;

import sun.net.www.protocol.http.HttpURLConnection;

public class AuctionHouse {

	public JsonArray getLowestBin(String name) {
		JsonArray array;
		try {
		String apiUrl = "https://api.hypixel.net/skyblock/auctions?key=" + Main.persistentValues.getAPI();
		JsonObject json = new JsonParser().parse(apiUrl).getAsJsonObject();

		// Open a HTTP connection to the Hypixel API
		URL url = new URL(apiUrl);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		
		JsonParser jsonparse = new JsonParser();
		
		// Request the JSON from the API, parse it and close the reader once it has been parsed.
		InputStreamReader reader = new InputStreamReader(conn.getInputStream());
		JsonObject obj = jsonparse.parse(reader).getAsJsonObject();
			reader.close();

		// Read from the parsed JSON however you want
		array = json.get("item_name").getAsJsonObject().get("SKYWARS").getAsJsonArray();
		
		} catch (IOException ex) {
			ex.printStackTrace();
			array = null;
		}
		return array;
		
	}
	
	
}
