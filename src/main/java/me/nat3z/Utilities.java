package me.nat3z;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Utilities {
	
	public static void displayGUI(GuiScreen gui) {
        Minecraft.getMinecraft().displayGuiScreen(gui);
	}
	public static void sendMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "[" + EnumChatFormatting.AQUA + "Nate's Skyblock Mod"+ EnumChatFormatting.DARK_AQUA + "]: " + EnumChatFormatting.RESET + message));
	}
	public static void sendWarning(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "[" + EnumChatFormatting.RED + "Nate's Skyblock Mod"+ EnumChatFormatting.DARK_RED + "]: " + EnumChatFormatting.RED + message));
	}
	
}