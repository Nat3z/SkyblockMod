package com.nat3z.skyqol.features;

import java.util.ArrayList;
import java.util.List;

import com.nat3z.skyqol.CheckIfSupporter;
import com.nat3z.skyqol.Main;

import me.nat3z.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DungeonReparty {
	private boolean ispartyleader = false;
	private boolean attempted = false;
	private boolean stopmessages = false;
	private int invitedam = 0;

	private List<String> partylist = new ArrayList<String>() ;
	@SuppressWarnings("static-access")
	@SubscribeEvent
	public void dungeonregister(ClientChatReceivedEvent event) {
		if (Main.config.isDungeonreparty() && Main.isHypixel() && Main.config.modules.get("dungeonreparty") && Main.config.isModEnabled()) {
			
				String message = event.message.getUnformattedText();
				
				if (message.endsWith("has disbanded the party!"))
					event.setCanceled(true);
				
				if (stopmessages && invitedam < partylist.size() && message.contains("invite")) {
					invitedam++;
					event.setCanceled(true);
				}
				
				if (invitedam == partylist.size() && stopmessages) {
					new Thread(() -> {
						stopmessages = false;
						partylist.clear();
						invitedam = 0;
					}).start();
				}
				
				if (stopmessages && message.contains("------")) {
					event.setCanceled(true);
					return;
				}
				
				if (message.contains("Party Leader:")) {
						new Thread(() -> {
							if (partylist.size() != 3) {
								stopmessages = true;
								String arr[] = message.split(" ", message.length());
								if (arr[2].contains("[")) {
									ispartyleader = arr[3].equals(Minecraft.getMinecraft().thePlayer.getName());
									event.setCanceled(true);
									stopmessages = false;
									return;
								} else {
									ispartyleader = arr[2].equals(Minecraft.getMinecraft().thePlayer.getName());
									event.setCanceled(true);
									stopmessages = false;
									return;
								}
							}
						}).start();
						
				}
				
				if (message.contains(": "))
					return;
				if (message.contains("has left the party.") || message.toLowerCase().contains("removed from") && !message.toLowerCase().contains("disconnected")) {
					
					
					
					new Thread(() -> {
						String arr[] = message.split(" ", message.length());
						if (arr[0].contains("[")) {
							if (partylist.contains(arr[1])) {
								partylist.remove(partylist.indexOf(arr[1]));
								Utilities.sendMessage("Removed " + arr[1] + " from the Party Index.");
							}
						} else {
							if (partylist.contains(arr[0])) {
								partylist.remove(partylist.indexOf(arr[0]));
								Utilities.sendMessage("Removed " + arr[0] + " From the Party Index.");
							}
						}
						if (!ispartyleader && !attempted) {
							Minecraft.getMinecraft().thePlayer.sendChatMessage("/party list");
							attempted = true;
						}
					}).start();
				}
				if (message.contains("joined the party.")) {
					
					new Thread(() -> {
						String arr[] = message.split(" ", message.length());
						
						if (!ispartyleader && !attempted) {
							stopmessages = true;
							Minecraft.getMinecraft().thePlayer.sendChatMessage("/party list");
							attempted = true;
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

						}
						
						if (arr[0].contains("[") && ispartyleader) {
							if (!partylist.contains(arr[1])) {
								partylist.add(arr[1]);
								Utilities.sendMessage("Registered " + arr[1] + " To the Party Index.");
							}
						} else {
							if (!partylist.contains(arr[0]) && ispartyleader) {
								partylist.add(arr[0]);
								Utilities.sendMessage("Registered " + arr[0] + " To the Party Index.");
							}
						}
					}).start();
				} else if (message.contains("EXTRA STATS") && !partylist.isEmpty()) {
					new Thread(() -> {
						if (ispartyleader) {
							String command = "/party invite";
							stopmessages = true;
							Minecraft.getMinecraft().thePlayer.sendChatMessage("/party disband");
							
							for (String string : partylist) {
								command += " " + string;
							}
							
							System.out.println(command);
							CheckIfSupporter.wait(1000);
							Minecraft.getMinecraft().thePlayer.sendChatMessage(command);
							while (stopmessages) {
								try {
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							
							Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "" + EnumChatFormatting.STRIKETHROUGH + "-----------------------------\n\n" + EnumChatFormatting.YELLOW + "     Repartied all users in party index.\n\n" + EnumChatFormatting.BLUE + "" + EnumChatFormatting.STRIKETHROUGH + "-----------------------------"));
							
						}
					}).start();
				} else if (message.contains("You left the party.") || message.contains("has disbanded the party!") && !stopmessages) {
					new Thread(() -> {
						partylist.clear();
						Utilities.sendMessage("Cleared partylist.");
						attempted = false;
						ispartyleader = false;
					}).start();
				}
		}
		
	}
}
