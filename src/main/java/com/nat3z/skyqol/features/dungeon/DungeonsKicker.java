package com.nat3z.skyqol.features.dungeon;

import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.config.Feature;
import com.nat3z.skyqol.utils.Scheduler;

import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DungeonsKicker {
	
	// Dungeon Finder > stinkymrpoo joined the dungeon group! (Mage Level 19)
	
	@SuppressWarnings("static-access")
	@SubscribeEvent
	public void dkick(ClientChatReceivedEvent event) {
		if (!Main.config.modules.get("dungeonkicker")) return;
		
		String message = event.message.getUnformattedText();
		if (message.contains(": "))
			return;
		
		
		if (message.contains("Dungeon Finder >")) {
			System.out.println(message);
			if (message.contains("de-listed.")) {
				return;
			}
			
			String[] split = message.split(" ");
			
			String user = split[3];
			String dclass = split[8].replace("(", "");
			int level = Integer.parseInt(split[10].replace(")", ""));
			
			boolean allowed = false;
			
			switch (dclass) {
			
				case "Tank":
					if (Feature.tank)
						allowed = true;
					break;
					
				case "Berserk":
					if (Feature.berserk)
						allowed = true;
					break;
					
				case "Healer":
					if (Feature.healer)
						allowed = true;
					break;
			
				case "Mage":
					if (Feature.mage)
						allowed = true;
					break;
					
				case "Archer":
					if (Feature.archer)
						allowed = true;
					break;
			}
			int minlvl = Feature.minimumClassLevel;
			
						
			if (!(level >= minlvl) || !allowed) {
				System.out.println("Attempting to kick " + user + "\n : Level is " + level + " | Minimum is " + minlvl + "\n"
				+
				"Class is " + dclass + " and returned with : " + allowed);
				
				ChatComponentText update = new ChatComponentText(EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + user + " does not fit the given requirements. Click this message to kick them.");
				update.setChatStyle(update.getChatStyle().setChatClickEvent(new ClickEvent(Action.RUN_COMMAND, "/party kick " + user)));
				Scheduler.runTask(() -> Minecraft.getMinecraft().thePlayer.addChatComponentMessage(update), 100);
			}
		}
	}
	
}
