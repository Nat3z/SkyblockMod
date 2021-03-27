package com.nat3z.skyqol.features;

import java.util.ArrayList;
import java.util.List;

import com.nat3z.skyqol.CheckIfSupporter;
import com.nat3z.skyqol.Main;

import me.nat3z.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DungeonReparty {
	private boolean ispartyleader = false;
	private boolean attempted = false;
		
	private List<String> partylist = new ArrayList<String>() ;
	@SuppressWarnings("static-access")
	@SubscribeEvent
	public void dungeonregister(ClientChatReceivedEvent event) {
		if (Main.config.isDungeonreparty() && Main.isHypixel() && Main.isOnSkyblock() && Main.config.modules.get("dungeonreparty") && Main.config.isModEnabled()) {
				String message = event.message.getUnformattedText();	
				if (message.contains("Party Leader:")) {
					new Thread(() -> {
						if (partylist.size() != 3) {
							String arr[] = message.split(" ", message.length());
							if (arr[2].contains("[")) {
								ispartyleader = arr[3].equals(Minecraft.getMinecraft().thePlayer.getName());
								return;
							} else {
								ispartyleader = arr[2].equals(Minecraft.getMinecraft().thePlayer.getName());
								return;
							}

						}
					}).start();
				}
				if (message.contains(": "))
					return;
				if (message.contains("has left the party.")) {
					new Thread(() -> {
						String arr[] = message.split(" ", message.length());
						if (arr[0].contains("[")) {
							if (partylist.contains(arr[1])) {
								partylist.remove(partylist.indexOf(arr[1]));
								Utilities.sendMessage("Removed " + arr[1] + " From the Party Index.");
							}
						} else {
							if (partylist.contains(arr[0])) {
								partylist.remove(partylist.indexOf(arr[0]));
								Utilities.sendMessage("Removed " + arr[0] + " From the Party Index.");
							}
						}
						if (!ispartyleader && !attempted) {
							attempted = true;
							Minecraft.getMinecraft().thePlayer.sendChatMessage("/party list");
						}
					}).start();
				}
				if (message.contains("joined the party.")) {
					new Thread(() -> {
						String arr[] = message.split(" ", message.length());
						if (arr[0].contains("[")) {
							if (!partylist.contains(arr[1])) {
								partylist.add(arr[1]);
								Utilities.sendMessage("Registered " + arr[1] + " To the Party Index.");
							}
						} else {
							if (!partylist.contains(arr[0])) {
								partylist.add(arr[0]);
								Utilities.sendMessage("Registered " + arr[0] + " To the Party Index.");
							}
						}
						if (!ispartyleader && !attempted) {
							attempted = true;
							Minecraft.getMinecraft().thePlayer.sendChatMessage("/party list");
						}
					}).start();
				} else if (message.contains("EXTRA STATS") && !partylist.isEmpty()) {
					new Thread(() -> {
						if (ispartyleader) {
							Minecraft.getMinecraft().thePlayer.sendChatMessage("/party disband");
							CheckIfSupporter.wait(2000);
							if (partylist.size() >= 0) {
								Minecraft.getMinecraft().thePlayer.sendChatMessage("/party invite " + partylist.get(0));
								partylist.remove(0);
								CheckIfSupporter.wait(2000);
							}
							if (partylist.size() >= 1) {
								Minecraft.getMinecraft().thePlayer.sendChatMessage("/party invite " + partylist.get(1));
								partylist.remove(1);
								CheckIfSupporter.wait(2000);
							}
							if (partylist.size() >= 2) {
								Minecraft.getMinecraft().thePlayer.sendChatMessage("/party invite " + partylist.get(2));
								partylist.remove(2);
								CheckIfSupporter.wait(2000);
							}
							if (partylist.size() >= 3) {
								Minecraft.getMinecraft().thePlayer.sendChatMessage("/party invite " + partylist.get(3));
								partylist.remove(3);
							}
							ispartyleader = false;
							attempted = false;
						}
					}).start();
				} else if (message.contains("You left the party.")) {
					new Thread(() -> {
						attempted = false;
						ispartyleader = false;
					}).start();
				}
		}
		
	}
}
