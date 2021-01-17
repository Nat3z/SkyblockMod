package com.nat3z.skyqol.features;

import com.nat3z.skyqol.Config;
import com.nat3z.skyqol.Main;

import me.nat3z.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.GuiScreenEvent.MouseInputEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AntiNonEnchanted {
    @SubscribeEvent
    public void containerevent(MouseInputEvent.Pre event) {
    	if (!Main.config.isModEnabled() || !Main.config.isAntiNonEnchantedEnabled())
    		return;
    	
    	
    	
    	if (event.gui instanceof GuiContainer) {
    		try {
    			
    			if (!Main.isHypixel())
    				return;
    			
    			if (!Main.isOnSkyblock())
    				return;
    			//Main m = new Main();
    	//		if (!m.isInSkyblock)
    	//			return;
    			GuiContainer inventory = (GuiContainer) event.gui;
    			Container inv = (Container) inventory.inventorySlots;
    			if (!inv.getSlot(4).getStack().getDisplayName().toLowerCase().contains("minion"))
    				return;
    			Slot slot = inventory.getSlotUnderMouse();
        		if (slot != null) {
        			ItemStack stack = slot.getStack();
        			
        			String nameofStack = EnumChatFormatting.getTextWithoutFormattingCodes(stack.getDisplayName());
        			if (nameofStack.equals("Super Compactor 3000") || nameofStack.equals("Diamond Spreading") || 
       					nameofStack.equals("Minion Expander") || nameofStack.equals("Foul Flesh") || 
       					nameofStack.equals("Upgrade Slot") || nameofStack.equals("Automated Shipping") || nameofStack.equals("Fuel") ||
       					nameofStack.equals("Minion Skin Slot") || nameofStack.contains("Catalyst") || nameofStack.equals("Hamster Wheel") ||
        				nameofStack.contains("Bucket") || nameofStack.contains("Hopper") || nameofStack.equals("Auto Smelter") ||
        				nameofStack.equals("Flycatcher") || nameofStack.equals("Flint Shovel") || nameofStack.equals("Next Tier") ||
        				nameofStack.equals("Ideal Layout") || nameofStack.equals("Collect All") || nameofStack.equals("Quick-Upgrade Minion") || nameofStack.equals("Quick-Upgrade Minion") || nameofStack.equals("Pickup Minion") || nameofStack.contains("Minion Skin"))
        			return;
        			
        			
        			if (inv.getSlot(37).getStack().getDisplayName().contains("Super Comp") || inv.getSlot(46).getStack().getDisplayName().contains("Super Comp")) {
        				if (!(stack.getItem() == null) && !stack.isItemEnchanted()) {
        					event.setCanceled(true);
        				}
        			}
        		}
    		} catch (Exception exception) {} // This is used to Catch Null Pointers
    	}

    }
}
