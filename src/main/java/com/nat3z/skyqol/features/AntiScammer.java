package com.nat3z.skyqol.features;

import org.lwjgl.input.Keyboard;

import com.nat3z.skyqol.events.GuiChestBackgroundDrawnEvent;
import com.nat3z.skyqol.events.SlotClickedEvent;
import com.nat3z.skyqol.utils.Message;
import com.nat3z.skyqol.utils.Utilities;
import com.nat3z.skyqol.utils.api.APIHandler;
import com.nat3z.skyqol.utils.api.RequestAPI;

import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.actors.threadpool.Arrays;

public class AntiScammer {

	public static String[] scamList = APIHandler.getStringFromUrl("https://raw.githubusercontent.com/Nat3z/SkyblockMod-EssentialData/main/data/scammerlist.nsmData").split("/NLINE");
	boolean sus = false;
	@SubscribeEvent
	public void scammer(ClientChatReceivedEvent event) {
		String message = event.message.getUnformattedText();
		if (!message.contains(": ") && message.contains("has sent you a trade request.")) {
			event.setCanceled(true);
			Message.sendMessage(EnumChatFormatting.RED + "Checking if " + message.split(" ")[0].replace(".", "") + " is a scammer.");

			new Thread(() -> {
				boolean sus = false;
				for (String string : scamList) {
					if (string.contains(RequestAPI.getUUID(StringUtils.stripControlCodes(message.split(" ")[0])))) {
						sus = true;
						String reason = string.split("-")[2].replaceFirst(" ", "");
						ChatComponentText update = new ChatComponentText(EnumChatFormatting.DARK_RED + "" + EnumChatFormatting.BOLD + "HERE");
						update.setChatStyle(update.getChatStyle().setChatClickEvent(new ClickEvent(Action.RUN_COMMAND, "/trade " + StringUtils.stripControlCodes(message.split(" ")[0]))));					
						Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "" + EnumChatFormatting.STRIKETHROUGH + "----------------------------------------" + "\n" + 
								message.split(" ")[0] + EnumChatFormatting.YELLOW + " has been reported as a scammer because of the reason: " + "\n" + EnumChatFormatting.RED + "" + EnumChatFormatting.ITALIC + reason
								+ "\n" + EnumChatFormatting.YELLOW + 
										
								"If you still want to trade with them, please click ").appendSibling(update).appendSibling(new ChatComponentText("\n" + EnumChatFormatting.RED + "" + EnumChatFormatting.STRIKETHROUGH + "----------------------------------------")));
						break;
					}
				}
			
				if (!sus) {
					Minecraft.getMinecraft().thePlayer.addChatComponentMessage(event.message);
				}
			}).start();			
		} else if (message.startsWith("You have sent a trade request to")) {
			event.setCanceled(true);
			Message.sendMessage(EnumChatFormatting.RED + "Checking if " + message.split(" ")[7].replace(".", "") + " is a scammer.");
			new Thread(() -> {
				boolean sus = false;
				for (String string : scamList) {
					if (string.contains(RequestAPI.getUUID(StringUtils.stripControlCodes(message.split(" ")[7].replace(".", ""))))) {
						sus = true;
						String reason = string.split("-")[2].replaceFirst(" ", "");
						Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "" + EnumChatFormatting.STRIKETHROUGH + "----------------------------------------" + "\n" + 
								message.split(" ")[7].replace(".", "") + EnumChatFormatting.YELLOW + " has been reported as a scammer because of the reason: " + "\n" + EnumChatFormatting.RED + "" + EnumChatFormatting.ITALIC + reason
								+ "\n" + EnumChatFormatting.YELLOW + 
										
								"If you still want to trade with them, please be cautious." + "\n" + EnumChatFormatting.RED + "" + EnumChatFormatting.STRIKETHROUGH + "----------------------------------------"));
						break;
					}
				}
			
				if (!sus) {
					Minecraft.getMinecraft().thePlayer.addChatComponentMessage(event.message);
				}
			}).start();
		}
		
	}
	
}
