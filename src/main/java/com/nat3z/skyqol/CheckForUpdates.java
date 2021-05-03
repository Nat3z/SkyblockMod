package com.nat3z.skyqol;

import com.google.gson.JsonObject;
import com.nat3z.skyqol.utils.Utilities;
import com.nat3z.skyqol.utils.api.APIHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.versioning.DefaultArtifactVersion;



/*
* I have changed the Following:
* Message When You Have An Outdated Version
* Changed request from API
*/
public class CheckForUpdates {
	
	private boolean updateChecked = false;
	
	public static boolean updateversion = false;
	
	/*
	 * Code From Danker's Skyblock Mod
	 */
	

	
	@SubscribeEvent
	public void doCompleteCheck(EntityJoinWorldEvent event) {
		
    	if (!updateChecked) {
			updateChecked = true;
			
    		// MULTI THREAD DRIFTING
    		new Thread(() -> {
    			EntityPlayer player = Minecraft.getMinecraft().thePlayer;	
    			
    			System.out.println("Checking for updates...");
    			JsonObject latestRelease = APIHandler.getResponse("https://api.github.com/repos/Nat3z/SkyblockMod/releases/latest");
    			
    			String latestTag = latestRelease.get("tag_name").getAsString();
    			DefaultArtifactVersion currentVersion = new DefaultArtifactVersion(Main.version);
    			DefaultArtifactVersion latestVersion = new DefaultArtifactVersion(latestTag.substring(1));
    			
    			if (currentVersion.compareTo(latestVersion) < 0) {
    				String releaseURL = latestRelease.get("html_url").getAsString();
    				
    				ChatComponentText update = new ChatComponentText(EnumChatFormatting.GREEN + "" + EnumChatFormatting.BOLD + "[UPDATE TO LATEST] ");
    				update.setChatStyle(update.getChatStyle().setChatClickEvent(new ClickEvent(Action.OPEN_URL, releaseURL)));
    					
	            	CheckIfSupporter.wait(1000);
	            	
    				updateversion = true;
    				player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "[" + EnumChatFormatting.RED + "NSM"+ EnumChatFormatting.DARK_RED + "]: " + EnumChatFormatting.RED + " You are currently using an outdated version of Nate's Secret Mod. Please update to " + EnumChatFormatting.YELLOW + latestTag + ".\n").appendSibling(update));
    			}
    		}).start();
    	}
	
	}
}