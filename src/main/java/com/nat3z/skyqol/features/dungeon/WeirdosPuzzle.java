package com.nat3z.skyqol.features.dungeon;

import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.config.Feature;
import com.nat3z.skyqol.utils.api.APIHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WeirdosPuzzle {
	
	public static String nsm_data = APIHandler.getStringFromUrl("https://raw.githubusercontent.com/Nat3z/SkyblockMod-EssentialData/main/data/solvers/3weirdos.nsmData");
	
	@SuppressWarnings("static-access")
	@SubscribeEvent
	public void WierdosSolver(ClientChatReceivedEvent event) {
		if (Feature.weirdossolver && Main.config.modules.get("weirdossolver")) {
			String message = event.message.getUnformattedText();
			
			if (message.contains("reward") && message.startsWith("[NPC]")) {
				
				String[] split = message.split(" ");
				
				String withoutNPCshiet = message.replace("[NPC] ", "").replace(split[1] + " ", "");
				
				if (message.contains("At least one of them is lying, and the reward is not in")) {
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "" + EnumChatFormatting.BOLD + split[2] + " has the blessing."));
				} 
				else if (nsm_data.contains(withoutNPCshiet)) {
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "" + EnumChatFormatting.BOLD + split[2] + " has the blessing."));
				}
				
			}
			
		}
	}
	
}
