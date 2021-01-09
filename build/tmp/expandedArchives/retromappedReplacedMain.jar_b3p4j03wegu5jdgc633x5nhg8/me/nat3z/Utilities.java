package me.nat3z;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Utilities {
	
	public static void displayGUI(GuiScreen gui) {
        Minecraft.func_71410_x().func_147108_a(gui);
	}
	public static void sendMessage(String message) {
        Minecraft.func_71410_x().field_71439_g.func_145747_a(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "[" + EnumChatFormatting.AQUA + "Nate's Skyblock Mod"+ EnumChatFormatting.DARK_AQUA + "]: " + EnumChatFormatting.RESET + message));
	}
	public static void sendWarning(String message) {
        Minecraft.func_71410_x().field_71439_g.func_145747_a(new ChatComponentText(EnumChatFormatting.DARK_RED + "[" + EnumChatFormatting.RED + "Nate's Skyblock Mod"+ EnumChatFormatting.DARK_RED + "]: " + EnumChatFormatting.RED + message));
	}
	
}