package com.nat3z.skyqol.listeners;

import com.nat3z.skyqol.Config;
import com.nat3z.skyqol.Main;

import me.nat3z.Utilities;
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
    	if (!Config.stopclickenchant)
    		return;
    	
    	if (event.gui instanceof GuiContainer) {
    		try {
    			//Main m = new Main();
    	//		if (!m.isInSkyblock)
    	//			return;
    			GuiContainer inventory = (GuiContainer) event.gui;
    			Container inv = (Container) inventory.field_147002_h;
    			if (!inv.func_75139_a(4).func_75211_c().func_82833_r().toLowerCase().contains("minion"))
    				return;
    			Slot slot = inventory.getSlotUnderMouse();
        		if (slot != null) {
        			ItemStack stack = slot.func_75211_c();
        			
        			String nameofStack = EnumChatFormatting.func_110646_a(stack.func_82833_r());
        			if (nameofStack.equals("Super Compactor 3000") || nameofStack.equals("Diamond Spreading") || 
       					nameofStack.equals("Minion Expander") || nameofStack.equals("Foul Flesh") || 
       					nameofStack.equals("Upgrade Slot") || nameofStack.equals("Automated Shipping") || nameofStack.equals("Fuel") ||
       					nameofStack.equals("Minion Skin Slot") || nameofStack.contains("Catalyst") || nameofStack.equals("Hamster Wheel") ||
        				nameofStack.contains("Bucket") || nameofStack.contains("Hopper") || nameofStack.equals("Auto Smelter") ||
        				nameofStack.equals("Flycatcher") || nameofStack.equals("Flint Shovel") || nameofStack.equals("Next Tier") ||
        				nameofStack.equals("Ideal Layout") || nameofStack.equals("Collect All") || nameofStack.equals("Quick-Upgrade Minion") || nameofStack.equals("Quick-Upgrade Minion") || nameofStack.equals("Pickup Minion") || nameofStack.contains("Minion Skin"))
        			return;
        			
        			if (inv.func_75139_a(37).func_75211_c().func_82833_r().contains("Super Comp") || inv.func_75139_a(46).func_75211_c().func_82833_r().contains("Super Comp")) {
        				if (!(stack.func_77973_b() == null) && !stack.func_77948_v()) {
        					event.setCanceled(true);
        				}
        			}
        		}
    		} catch (NullPointerException exception) {} // This is used to Catch Null Pointers
    	}

    }
}
