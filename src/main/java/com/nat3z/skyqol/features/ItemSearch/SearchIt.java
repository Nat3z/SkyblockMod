package com.nat3z.skyqol.features.ItemSearch;

import java.awt.Color;
import java.util.List;

import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.utils.ItemUtils;
import com.nat3z.skyqol.utils.Utilities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SearchIt {

	public static String query = "";
	@SubscribeEvent
    public void high(GuiScreenEvent.BackgroundDrawnEvent event) {
		if (!Main.isHypixel() || query.isEmpty() || query.equals(""))
			return;
		
    	if (Minecraft.getMinecraft().currentScreen instanceof GuiChest) {
			GuiChest inventory = (GuiChest) Minecraft.getMinecraft().currentScreen;
    		List<Slot> invSlots = inventory.inventorySlots.inventorySlots;
    		
    		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("You are Searching for " + EnumChatFormatting.ITALIC + query + EnumChatFormatting.RESET + " in this Inventory.", 5, 5, Color.white.getRGB());
    		Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Type /search to clear your current search.", 5, 20, Color.white.getRGB());
    		for (Slot slot : invSlots) {
    			try {
        			if (slot != null && slot.getStack() != null) {
        				List<String> lorelmo = ItemUtils.getItemLore(slot.getStack());
        				if (slot.getStack().getDisplayName().contains("Enchanted Book") && StringUtils.stripControlCodes(lorelmo.get(0)).contains(query)) {
        					Utilities.showOnSlot(inventory.inventorySlots.inventorySlots.size(), slot.xDisplayPosition, slot.yDisplayPosition, new Color(246, 255, 0).getRGB());
        				} else if (slot.getStack().getDisplayName().toLowerCase().contains(query.toLowerCase())) {
        					Utilities.showOnSlot(inventory.inventorySlots.inventorySlots.size(), slot.xDisplayPosition, slot.yDisplayPosition, new Color(246, 255, 0).getRGB());
        	    		} else if (!slot.getStack().getDisplayName().contains("Enchanted Book") || !StringUtils.stripControlCodes(lorelmo.get(0)).contains(query)) {
        					Utilities.showOnSlot(inventory.inventorySlots.inventorySlots.size(), slot.xDisplayPosition, slot.yDisplayPosition, new Color(0, 0, 0, 100).getRGB());
        	    		}
        			}
    			} catch (Exception ex) { /* Handles Exception */}
    		}
    	}
    }
}
