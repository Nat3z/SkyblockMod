package com.nat3z.skyqol.features;

import java.util.List;

import com.nat3z.skyqol.Main;

import me.nat3z.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.GuiScreenEvent.MouseInputEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HighlightFarmingContests {

    @SubscribeEvent
    public void stop(GuiScreenEvent.BackgroundDrawnEvent event) {
    	if (!Main.config.isModEnabled() || !Main.config.isUnclaimedFarmingContest())
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
    			
    				if (slot.getStack().isItemEnchanted()) {
    					Utilities.showOnSlot(inventory.inventorySlots.inventorySlots.size(), slot.xDisplayPosition, slot.yDisplayPosition);
    				}
    		}	
    			
    		
    		} catch (Exception ex) {}
    	}
	
    }
    
}