package com.nat3z.skyqol.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class Message {

	public static void sendMessage(String message) {
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
	}
	
}
