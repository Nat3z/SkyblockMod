package com.nat3z.skyqol.features;

import com.nat3z.skyqol.Config;
import com.nat3z.skyqol.Main;

import me.nat3z.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.GuiScreenEvent.MouseInputEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AntiNonEnchanted {
    @SuppressWarnings("static-access")
	@SubscribeEvent
    public void containerevent(MouseInputEvent.Pre event) {
    	if (!Main.config.isModEnabled() || !Main.config.isAntiNonEnchantedEnabled() || !Main.config.modules.get("antinonenchanted"))
    		return;
    	
    	
    	
    	if (event.gui instanceof GuiContainer) {
    		try {
    			if (!Main.isHypixel() || !Main.isOnSkyblock())
    				return;
    			GuiChest ninventory = (GuiChest) Minecraft.getMinecraft().currentScreen;
        		ContainerChest ninv = (ContainerChest) ninventory.inventorySlots;
        		IInventory nameinv = ninv.getLowerChestInventory();
        		
    			GuiContainer inventory = (GuiContainer) event.gui;
    			Container inv = (Container) inventory.inventorySlots;
    			if (nameinv.getDisplayName().getUnformattedText().toLowerCase().contains("minion") && !nameinv.getDisplayName().getUnformattedText().toLowerCase().contains("chest")) {
        			Slot slot = inventory.getSlotUnderMouse();
            		if (slot != null) {
            			
            			if (
            					slot.slotNumber == 3  ||
            					slot.slotNumber == 4  ||
            					slot.slotNumber == 5  || 
            					slot.slotNumber == 19 ||
            					slot.slotNumber == 28 ||
            					slot.slotNumber == 37 ||
            					slot.slotNumber == 46 || 
            					slot.slotNumber == 48 ||
            					slot.slotNumber == 53 
            				)
            			return;
            			
            			ItemStack stack = slot.getStack();
            			if (inv.getSlot(37).getStack().getDisplayName().contains("Super Comp") || inv.getSlot(46).getStack().getDisplayName().contains("Super Comp")) {
            				if (!(stack.getItem() == null) && !stack.isItemEnchanted()) {
            					event.setCanceled(true);
            				}
            			}
            		}
    			} else if (nameinv.getDisplayName().getUnformattedText().toLowerCase().contains("minion") && nameinv.getDisplayName().getUnformattedText().toLowerCase().contains("chest")) {
        			Slot slot = inventory.getSlotUnderMouse();
        			ItemStack stack = slot.getStack();
        			if (slot != null) {
        				if (!(stack.getItem() == null) && !stack.isItemEnchanted()) {
        					event.setCanceled(true);
        				}
        			}
    			}
    		} catch (Exception exception) {} // This is used to Catch Null Pointers
    	}

    }
}
