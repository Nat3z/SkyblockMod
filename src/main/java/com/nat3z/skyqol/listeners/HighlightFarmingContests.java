package com.nat3z.skyqol.listeners;

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
    	
    	
    	
    	if (Minecraft.getMinecraft().currentScreen instanceof GuiContainer) {
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
    
    /*
     * Literally just copy + paste from AntiNonEnchanted lol
     */
    
    @SubscribeEvent
    public void highlightevent(MouseInputEvent.Pre event) {
    	if (!Main.config.isModEnabled() || !Main.config.isUnclaimedFarmingContest())
    		return;
    	
    	if (event.gui instanceof GuiContainer) {
    		try {
    			if (!Main.isOnSkyblock())
    				return;
    			GuiChest inventory = (GuiChest) Minecraft.getMinecraft().currentScreen;
        		ContainerChest inv = (ContainerChest) inventory.inventorySlots;
        		IInventory nameinv = inv.getLowerChestInventory();
        		
        		if (!nameinv.getDisplayName().getUnformattedText().equals("Your Contests"))
        			return;
        		
    			Slot slot = inventory.getSlotUnderMouse();
        		if (slot != null) {
        			ItemStack stack = slot.getStack();
        			
        			if (stack.isItemEnchanted() || stack.getItem().equals(Items.arrow))
        				return;

        				if (!(stack.getItem() == null))
        					event.setCanceled(true);
        				
        			}
    		} catch (NullPointerException exception) {} // This is used to Catch Null Pointers
    	}

    }
}