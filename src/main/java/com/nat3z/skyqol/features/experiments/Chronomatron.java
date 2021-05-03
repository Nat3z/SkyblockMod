package com.nat3z.skyqol.features.experiments;

import java.awt.Color;
import java.util.List;

import org.lwjgl.input.Mouse;

import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.config.Config;
import com.nat3z.skyqol.config.Feature;
import com.nat3z.skyqol.events.GuiChestBackgroundDrawnEvent;
import com.nat3z.skyqol.events.SlotClickedEvent;
import com.nat3z.skyqol.utils.Utilities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class Chronomatron {
	public static String[] chromotronofgreatnestlist = new String[64];
	public static int mouseclicks = 1;
    public static int previousround = 1;
    public static int timerSec = 2;
	@SuppressWarnings("static-access")
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void MainRenderSolver(GuiChestBackgroundDrawnEvent event) {

		if (!Feature.ExperimentChronomatron) return;
		if (!(boolean)Main.config.modules.get("chronomsolver")) return;

		try {
			if (Minecraft.getMinecraft().currentScreen instanceof GuiChest) {
	            GuiChest inventory = (GuiChest) Minecraft.getMinecraft().currentScreen;
	            Container containerChest = inventory.inventorySlots;
	            List<Slot> invSlots = inventory.inventorySlots.inventorySlots;
	            String displayName = ((ContainerChest) containerChest).getLowerChestInventory().getDisplayName().getUnformattedText();
				
				if (displayName.startsWith("Chronomatron (")) {
					if (invSlots.get(49).getStack() != null) {
						int timerSeconds = 0;
						if (!invSlots.get(49).getStack().getItem().equals(Item.getItemFromBlock(Blocks.glowstone)))
							timerSeconds = Integer.parseInt(StringUtils.stripControlCodes(invSlots.get(49).getStack().getDisplayName()).replaceAll("[a-zA-Z]|:| |!", ""));
													
			           if (timerSeconds == (timerSec + 1)) {
			                if (timerSeconds != 0)
			                	timerSec = timerSeconds;
			                	
			                int round = invSlots.get(4).getStack().stackSize;
			                previousround = round;
			                for (int i = 10; i <= 43; i++) {
								Slot slot = invSlots.get(i);
								if (slot != null)
									if (slot.getStack() != null)
										if (slot.getStack().getItem().equals(Item.getItemFromBlock(Blocks.stained_hardened_clay))) {
											chromotronofgreatnestlist[round] = slot.getStack().getDisplayName();
											mouseclicks = 1;
											break;
										}
			                }
			            }
					}
                    if (mouseclicks < this.chromotronofgreatnestlist.length) {
						int curr = 0;
			            for (String string : chromotronofgreatnestlist) {
			            	if (string == null) continue;
			            	
			            	curr++;
			            	if (curr == mouseclicks)
			            		Minecraft.getMinecraft().fontRendererObj.drawString(EnumChatFormatting.GOLD + "> " + string, 15, curr * 10, Color.white.getRGB(), true);
			            	else
				            	Minecraft.getMinecraft().fontRendererObj.drawString(StringUtils.stripControlCodes(string), 15, curr * 10, Color.darkGray.getRGB(), true);
			            }
			            
			            if (event.slots.get(49) == null) return;
			            if (event.slots.get(49).getStack() == null) return;

			            
				        if (!event.slots.get(49).getStack().getDisplayName().equals("§aRemember the pattern!")) {
			               for (int i = 10; i <= 43; i++) {
			                    Slot slot = event.slots.get(i);
								if (slot.slotNumber == 0)
									return;
			                    
			                    
				            	if (slot.getStack() != null && slot != null) {
				    				if (slot.getStack().getDisplayName() != " " && slot.getStack().getDisplayName() != "" && slot.getStack().getDisplayName() != null ) {
				    					if (chromotronofgreatnestlist[mouseclicks] != null)	
											if (StringUtils.stripControlCodes(slot.getStack().getDisplayName()).equals(StringUtils.stripControlCodes(chromotronofgreatnestlist[mouseclicks]))) {
												Utilities.showOnSlot(event.chestSize, slot.xDisplayPosition, slot.yDisplayPosition, new Color(140, 255, 0).getRGB() + 0xE5000000);
											}
				    				}
				            	}
							}
						}
			        }
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    @SuppressWarnings("static-access")
	@SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onSlotClick(SlotClickedEvent event) {
    	try {
			if (!Feature.ExperimentChronomatron) return;
			if (!(boolean)Main.config.modules.get("chronomsolver")) return;

	        if (event.inventoryName.startsWith("Chronomatron (")) {
	            ItemStack item = event.item;            
	            if (!Mouse.getEventButtonState()) return;
	            
	            // TODO find a way to fix this: StringUtils.stripControlCodes(chest.inventorySlots.getSlot(49).getStack().getDisplayName()).startsWith("Timer: ") && 
	            if (item.getItem().equals(Item.getItemFromBlock(Blocks.stained_glass)) || item.getItem().equals(Item.getItemFromBlock(Blocks.stained_hardened_clay))) {
	                mouseclicks++;
	            }
	        }
        } catch (Exception e) {
        	//TODO handle exception lol
        }
    }
	
	@SuppressWarnings("static-access")
	@SubscribeEvent
	public void changeClick(SlotClickedEvent event) {
		if (Feature.ExperimentChronomatron && Main.config.modules.get("chronomsolver")) {
			if (event.inventoryName.startsWith("Chronomatron (")) {
				if (!Mouse.getEventButtonState()) return;
				event.setCanceled(true);
				
				Minecraft.getMinecraft().playerController.windowClick(event.chest.inventorySlots.windowId, event.slot.slotNumber, 2, 1, Minecraft.getMinecraft().thePlayer);
			}
		}
	}
	
	@SubscribeEvent
	public void ultraTooltip(ItemTooltipEvent event) {
		if (!Config.modules.get("ultraseqsolver")) return;
		if (Minecraft.getMinecraft().currentScreen instanceof GuiChest)
			if (Feature.ExperimentUltrasequencer) {
	            GuiChest inventory = (GuiChest) Minecraft.getMinecraft().currentScreen;
	            Container containerChest = inventory.inventorySlots;
	            
	            String displayName = ((ContainerChest) containerChest).getLowerChestInventory().getDisplayName().getUnformattedText().trim();
		        
				if (StringUtils.stripControlCodes(displayName).startsWith("Ultrasequencer (")) {
					event.toolTip.clear();
				}
			}
	}
    
    @SubscribeEvent
    public void clear(GuiOpenEvent event) {
    	mouseclicks = 1;
    	previousround = 1;
    	timerSec = 2;
    	chromotronofgreatnestlist = new String[64];
    }
}
