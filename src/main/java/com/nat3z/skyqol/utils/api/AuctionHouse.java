package com.nat3z.skyqol.utils.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AuctionHouse {

	public String getUserAuction(String name) {
		String auctionparse = RequestAPI.attemptAPI(name, "auction").getAsJsonArray("auctions").toString();
		JsonParser parser = new JsonParser();
		JsonArray json = parser.parse(auctionparse).getAsJsonArray();
		
		return json.getAsJsonArray().get(0).getAsJsonObject().get("_id").getAsString();
	}
	
	
}
