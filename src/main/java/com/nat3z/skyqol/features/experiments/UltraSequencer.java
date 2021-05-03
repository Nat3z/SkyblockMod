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
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
@SuppressWarnings("static-access")
public class UltraSequencer {
	

    static Slot[] slotlist = new Slot[39];
    
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void MainRenderSolver(ClientTickEvent event) {
		if (event.phase != Phase.START) return;
		if (!(Minecraft.getMinecraft().currentScreen instanceof GuiChest)) return;
		
		if (!Feature.ExperimentUltrasequencer) return;
		if (!Config.modules.get("ultraseqsolver")) return;
			try {
	            GuiChest inventory = (GuiChest) Minecraft.getMinecraft().currentScreen;
	            Container containerChest = inventory.inventorySlots;
	            
	            List<Slot> invSlots = inventory.inventorySlots.inventorySlots;
	            String displayName = ((ContainerChest) containerChest).getLowerChestInventory().getDisplayName().getUnformattedText().trim();
		        
				if (StringUtils.stripControlCodes(displayName).startsWith("Ultrasequencer (")) {
						
					if (invSlots.size() <= 49) return;
					
					if (invSlots.get(49).getStack().getItem().equals(Item.getItemFromBlock(Blocks.glowstone))) {
						slotlist = new Slot[39];
						for (int sl = 9; sl <= 44; sl++) {
	                        if (invSlots.get(sl) == null || invSlots.get(sl).getStack() == null) continue;
	
							Slot slot = invSlots.get(sl);
							if (slot.getStack().getItem().equals(Items.dye)) {
								int slotnum = slot.getStack().stackSize;
								
								if (slot.getStack().getDisplayName() == null) return;
								if (slotlist[slotnum + 1] == null)
									slotlist[slotnum + 1] = slot;
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	public static int onClick = 0;
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void slotShow(GuiChestBackgroundDrawnEvent event) {
		try {
			if (!(Feature.ExperimentUltrasequencer)) return;
			if (!(boolean)Main.config.modules.get("ultraseqsolver")) return;
			
			if (StringUtils.stripControlCodes(event.displayName).startsWith("Ultrasequencer (")) {
				if (event.slots.size() <= 49) return;

				for (int i = 2; i < slotlist.length; i++) {
					Slot stack = slotlist[i];
					if (stack == null)
						return;
					
					if (stack.getStack() == null) continue;
					
					if (!stack.getStack().getItem().equals(Items.dye)) {
				        Utilities.showOnSlot(event.chestSize, stack.xDisplayPosition, stack.yDisplayPosition, new Color(140, 255, 0).getRGB());
				        if (slotlist[i + 1] != null)
				            if (slotlist[i + 1] != null) {
				            	if (!slotlist[i + 1].getStack().getItem().equals(Items.dye)) {
				            		Utilities.showOnSlot(event.chestSize, slotlist[i + 1].xDisplayPosition, slotlist[i + 1].yDisplayPosition, new Color(0, 255, 250).getRGB());
				            	}
				            }
				        break;    
				     }
				}
				
				for (int i = 2; i < slotlist.length; i++ ) {
					Slot slot = slotlist[i];
					
					if (slot == null) return;
					if (slot.getStack() == null) continue;
					
					if (!slot.getStack().getItem().equals(Items.dye)) {
						if (slot.getStack().getDisplayName().equals(" ")) return;
						int slotnum = slot.getStack().stackSize;
				        Utilities.displayStringOnSlot(event.chestSize, slot.xDisplayPosition, slot.yDisplayPosition, "" + slotnum);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	@SubscribeEvent
	public void changeClick(SlotClickedEvent event) {
		if (!Config.modules.get("ultraseqsolver")) return;

		if (Feature.ExperimentUltrasequencer) {
			if (event.inventoryName.startsWith("Ultrasequencer (")) {
				if (!Mouse.getEventButtonState()) return;
				event.setCanceled(true);
				
				Minecraft.getMinecraft().playerController.windowClick(event.chest.inventorySlots.windowId, event.slot.slotNumber, 2, 1, Minecraft.getMinecraft().thePlayer);
			}
		}
	}
	
	@SubscribeEvent
	public void ultraTooltip(ItemTooltipEvent event) {
		if (!Config.modules.get("chronomsolver")) return;
		if (Minecraft.getMinecraft().currentScreen instanceof GuiChest)
			if (Feature.ExperimentChronomatron) {
	            GuiChest inventory = (GuiChest) Minecraft.getMinecraft().currentScreen;
	            Container containerChest = inventory.inventorySlots;
	            
	            String displayName = ((ContainerChest) containerChest).getLowerChestInventory().getDisplayName().getUnformattedText().trim();
		        
				if (StringUtils.stripControlCodes(displayName).startsWith("Chronomatron (")) {
					event.toolTip.clear();
				}
			}
	}
	
    @SubscribeEvent
    public void clear(GuiOpenEvent event) {
    	slotlist = new Slot[39];
    }
}
