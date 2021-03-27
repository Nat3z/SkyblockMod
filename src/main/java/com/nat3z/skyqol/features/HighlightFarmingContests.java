package com.nat3z.skyqol.features;

import java.awt.Color;
import java.util.List;

import com.nat3z.skyqol.Main;

import me.nat3z.ItemUtils;
import me.nat3z.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HighlightFarmingContests {

    @SuppressWarnings("static-access")
	@SubscribeEvent
    public void stop(GuiScreenEvent.BackgroundDrawnEvent event) {
    	if (!Main.config.isModEnabled() || !Main.config.isUnclaimedFarmingContest() || !Main.config.modules.get("unclaimedfarmingcontests"))
    		return;
    	
		if (!Main.isHypixel())
			return;
    	
    	if (Minecraft.getMinecraft().currentScreen instanceof GuiChest) {
    		try {
    			if (!Main.isOnSkyblock())
    				return;
			GuiChest inventory = (GuiChest) Minecraft.getMinecraft().currentScreen;
    		ContainerChest inv = (ContainerChest) inventory.inventorySlots;
    		IInventory nameinv = inv.getLowerChestInventory();
    		if (!nameinv.getDisplayName().getUnformattedText().equals("Your Contests"))
    			return;
    		
    		List<Slot> invSlots = inventory.inventorySlots.inventorySlots;
    		for (Slot slot : invSlots) {
    			try {    				
    				if (slot.getStack().isItemEnchanted()) {
						ItemStack stack = slot.getStack();
						List<String> lore = ItemUtils.getItemLore(stack);
						String medal = EnumChatFormatting.getTextWithoutFormattingCodes(lore.get(11).toLowerCase().replace("you earned a ", "").replace(" medal", ""));
						if (EnumChatFormatting.getTextWithoutFormattingCodes(medal).contains("gold"))
	    					Utilities.showOnSlot(inventory.inventorySlots.inventorySlots.size(), slot.xDisplayPosition, slot.yDisplayPosition, Color.yellow.getRGB());
						else if (EnumChatFormatting.getTextWithoutFormattingCodes(medal).contains("silver"))
	    					Utilities.showOnSlot(inventory.inventorySlots.inventorySlots.size(), slot.xDisplayPosition, slot.yDisplayPosition, Color.DARK_GRAY.getRGB());
						else if (EnumChatFormatting.getTextWithoutFormattingCodes(medal).contains("bronze"))
	    					Utilities.showOnSlot(inventory.inventorySlots.inventorySlots.size(), slot.xDisplayPosition, slot.yDisplayPosition, Color.orange.getRGB());
	    			}
    			} catch (Exception ex) {}
    		}	
    			
    		
    		} catch (Exception ex) {}
    	}
	
    }
    
}