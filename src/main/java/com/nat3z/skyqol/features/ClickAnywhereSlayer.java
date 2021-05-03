package com.nat3z.skyqol.features;

import org.lwjgl.input.Mouse;

import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.config.Feature;
import com.nat3z.skyqol.utils.Utilities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.MouseInputEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClickAnywhereSlayer {
	
	private String cmd = null;
	
	@SuppressWarnings("static-access")
	@SubscribeEvent
	public void slayerclickany(ClientChatReceivedEvent event) {
		if (!Main.isHypixel() || !Main.isOnSkyblock() || !Feature.AnywhereSlayer || !Main.config.modules.get("maddoxautophone").booleanValue())
			return;
		
		if (event.message.getUnformattedText().toLowerCase().contains("open menu")) {
			IChatComponent component = event.message.getSiblings().get(2);
				if (component.getChatStyle().getChatClickEvent() != null) {
    				cmd = component.getChatStyle().getChatClickEvent().getValue();
    				Utilities.sendMessage("Click Anywhere to open Batphone Menu!");
    			}
		}
	}
	
	@SubscribeEvent
    public void slayerclickhandler(GuiScreenEvent.MouseInputEvent.Post event) {
		if (!Main.isHypixel() || !Main.isOnSkyblock() || !Feature.AnywhereSlayer)
			return;
		
		if (!Mouse.getEventButtonState()) return;
		
		if (event.gui != null)
			if (event.gui instanceof GuiContainer || event.gui instanceof GuiChat || event.gui instanceof GuiInventory) {
				
				if (cmd != null) {
					Minecraft.getMinecraft().thePlayer.sendChatMessage(cmd);
					cmd = null;
				}
				
			}
	}
	
	
}
