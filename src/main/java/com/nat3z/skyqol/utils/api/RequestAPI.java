package com.nat3z.skyqol.utils.api;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.config.Feature;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class RequestAPI {
	public static AuctionHouse ah = new AuctionHouse();
	public static String getUUID(String username) {
		JsonObject response = APIHandler.getResponse("https://api.mojang.com/users/profiles/minecraft/" + username);
		return response.get("id").getAsString();
	}
	public static JsonObject attemptAPI(String name, String typeofrequest) {
		JsonObject response = APIHandler.getResponse("https://api.hypixel.net/skyblock/" + typeofrequest + "?key=" + Feature.apiKey + "&player=" + getUUID(name));
		System.out.println(getUUID(name));
		return response;
	}
	
	
	public static JsonObject getProfile(String UUID) {
		JsonObject response = APIHandler.getResponse("https://api.hypixel.net/skyblock/profile?key=" + Feature.apiKey + "&profile=" + UUID);
		return response;
	}
	
	
	/**
	 * From Skytils
	 * https://github.com/Skytils/SkytilsMod/src/main/java/skytils/skytilsmod/utils/APIUtil.java
	 * @author Skytils
	 * @param UUID
	 * @param key
	 * @return
	 */
    public static String getLatestProfileID(String UUID, String key) {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;

        // Get profiles
        System.out.println("Fetching profiles...");
        System.out.println(UUID);
        System.out.println(key);
        JsonObject profilesResponse = APIHandler.getResponse("https://api.hypixel.net/skyblock/profiles?uuid=" + UUID + "&key=" + key);
        if (!profilesResponse.get("success").getAsBoolean()) {
            String reason = profilesResponse.get("cause").getAsString();
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Failed with reason: " + reason));
            return null;
        }
        if (profilesResponse.get("profiles").isJsonNull()) {
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "This player doesn't appear to have played SkyBlock."));
            return null;
        }

        // Loop through profiles to find latest
        System.out.println("Looping through profiles...");
        String latestProfile = "";
        long latestSave = 0;
        JsonArray profilesArray = profilesResponse.get("profiles").getAsJsonArray();

        for (JsonElement profile : profilesArray) {
            JsonObject profileJSON = profile.getAsJsonObject();
            long profileLastSave = 1;
            if (profileJSON.get("members").getAsJsonObject().get(UUID).getAsJsonObject().has("last_save")) {
                profileLastSave = profileJSON.get("members").getAsJsonObject().get(UUID).getAsJsonObject().get("last_save").getAsLong();
            }

            if (profileLastSave > latestSave) {
                latestProfile = profileJSON.get("profile_id").getAsString();
                latestSave = profileLastSave;
            }
        }

        return latestProfile;
    }
	
	public static NBTTagCompound decodeData(String data) {
	    try (ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decodeBase64(data))) {
	        return CompressedStreamTools.readCompressed(bis);
	    } catch (IOException e) {
	      e.printStackTrace();
	      return new NBTTagCompound();
	    }
    }
	
}

