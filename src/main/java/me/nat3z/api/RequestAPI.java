package me.nat3z.api;

import com.google.gson.JsonObject;
import com.nat3z.skyqol.Main;

import me.nat3z.APIHandler;

public class RequestAPI {
	public static AuctionHouse ah = new AuctionHouse();
	private static String getUUID(String username) {
		JsonObject response = APIHandler.getResponse("https://api.mojang.com/users/profiles/minecraft/" + username);
		return response.get("id").getAsString();
	}
	public static JsonObject attemptAPI(String name, String typeofrequest) {
		JsonObject response = APIHandler.getResponse("https://api.hypixel.net/skyblock/" + typeofrequest + "?key=" + Main.persistentValues.getAPI() + "&player=" + getUUID(name));
		System.out.println(getUUID(name));
		return response;
	}

	
}

