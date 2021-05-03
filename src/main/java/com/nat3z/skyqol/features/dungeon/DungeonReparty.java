package com.nat3z.skyqol.features.dungeon;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;

import com.nat3z.skyqol.CheckIfSupporter;
import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.config.Feature;
import com.nat3z.skyqol.utils.Scheduler;
import com.nat3z.skyqol.utils.Utilities;

import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DungeonReparty {
	public static boolean ispartyleader = false;
	public static boolean stopmessages = false;
	private int invitedam = 0;

	public static List<String> partylist = new ArrayList<String>();
	
	public static boolean isError = false;
	
	@SuppressWarnings("static-access")
	@SubscribeEvent
	public void dungeonregister(ClientChatReceivedEvent event) {
		if (Feature.DungeonReparty && Main.isHypixel() && Main.config.modules.get("dungeonreparty")) {
						
			String message = event.message.getUnformattedText();
				
				
			if (message.startsWith("Party Leader:") && stopmessages) {
				String arr[] = message.split(" ", message.length());
				System.out.println(arr[2] + " " + arr[3]);
				if (arr[2].contains("[")) {
					ispartyleader = arr[3].equals(Minecraft.getMinecraft().thePlayer.getName());
				} else {
					ispartyleader = arr[2].equals(Minecraft.getMinecraft().thePlayer.getName());
				}
				event.setCanceled(true);						
			}
				
			if (message.contains("EXTRA STATS")) {
				Scheduler.runTask(() -> {
					ChatComponentText update = new ChatComponentText(EnumChatFormatting.GOLD + "" + EnumChatFormatting.BOLD + "Reparty your Dungeon Party");
					update.setChatStyle(update.getChatStyle().setChatClickEvent(new ClickEvent(Action.RUN_COMMAND, "/nsmrp")));
					Minecraft.getMinecraft().thePlayer.addChatComponentMessage(update);
				}, 500);
			}
			
			if (message.contains("has disbanded") && stopmessages)
				event.setCanceled(true);
				
			if (message.startsWith("Party Members (") && stopmessages) {
				event.setCanceled(true);
			}
				
			if (stopmessages && message.startsWith("Party Members:")) {
				String[] split = message.replace("Party Members: ", "").split(" ");
				for (String string : split) {
					if (!string.contains("]") && !string.contains("●")) {
						partylist.add(StringUtils.stripControlCodes(string));
					}
				}
				
				partylist.forEach(string -> System.out.println(string));
				
				event.setCanceled(true);
			} else if (stopmessages && message.startsWith("Party Moderators:")) {
				String[] split = message.replace("Party Moderators: ", "").split(" ");
				for (String string : split) {
					if (!string.contains("]") && !string.contains("●")) {
						partylist.add(StringUtils.stripControlCodes(string));
					}
				}
				event.setCanceled(true);
			}
				
			if (stopmessages && invitedam < partylist.size() && message.contains("invite")) {
				invitedam++;
				event.setCanceled(true);
			}
			if (stopmessages && invitedam < partylist.size() && (message.contains("find a player with that name!") || message.contains("Couldn't find a player") || message.contains("already invited"))) {
				isError = true;
				invitedam++;
				event.setCanceled(true);
			}
				
			if (invitedam == partylist.size() && stopmessages && !partylist.isEmpty()) {
				Scheduler.runTask(() -> {
					stopmessages = false;
					partylist.clear();
					invitedam = 0;
					ispartyleader = false;
				}, 100);
			}
				
			if (stopmessages && message.contains("------")) {
				event.setCanceled(true);
			}
		}
		
	}
}
