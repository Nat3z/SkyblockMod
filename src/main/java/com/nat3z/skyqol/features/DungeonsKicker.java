package com.nat3z.skyqol.features;

import com.nat3z.skyqol.Main;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DungeonsKicker {
	
	// Dungeon Finder > stinkymrpoo joined the dungeon group! (Mage Level 19)
	
	@SubscribeEvent
	public void dkick(ClientChatReceivedEvent event) {
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
					if (Main.persistentValues.isTankdk())
						allowed = true;
					break;
					
				case "Berserk":
					if (Main.persistentValues.isBersdk())
						allowed = true;
					break;
					
				case "Healer":
					if (Main.persistentValues.isHealerdk())
						allowed = true;
					break;
			
				case "Mage":
					if (Main.persistentValues.isMagedk())
						allowed = true;
					break;
					
				case "Archer":
					if (Main.persistentValues.isArcherdk())
						allowed = true;
					break;
			}
			int minlvl = Integer.parseInt(Main.apis.getMincdk());
			
						
			if (!(level >= minlvl) || !allowed) {
				System.out.println("Attempting to kick " + user + "\n : Level is " + level + " | Minimum is " + minlvl + "\n"
				+
				"Class is " + dclass + " and returned with : " + allowed);
				Minecraft.getMinecraft().thePlayer.sendChatMessage("/party kick " + user);
			}
		}
	}
	
}
