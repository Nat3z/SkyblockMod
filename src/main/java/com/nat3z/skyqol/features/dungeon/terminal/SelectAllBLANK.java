package com.nat3z.skyqol.features.dungeon.terminal;

import java.awt.Color;

import com.nat3z.skyqol.events.GuiChestBackgroundDrawnEvent;
import com.nat3z.skyqol.utils.Utilities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.Slot;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SelectAllBLANK {
	
	@SubscribeEvent
	public void SelectALL(GuiChestBackgroundDrawnEvent event) {
		if (Minecraft.getMinecraft().currentScreen instanceof GuiChest) {
			ContainerChest chest = (ContainerChest) Minecraft.getMinecraft().thePlayer.openContainer;
				
			if (chest.getLowerChestInventory().getDisplayName().getUnformattedText().contains("Select all the ") && chest.getLowerChestInventory().getDisplayName().getUnformattedText().contains("items!")) {
				String arr[] = chest.getLowerChestInventory().getDisplayName().getUnformattedText().split(" ", chest.getLowerChestInventory().getDisplayName().getUnformattedText().length());
				
				for (int i = 10; i < 43; i++) {
					Slot slot = chest.inventorySlots.get(i);
					
					if (slot != null) {
						if (slot.getStack() != null) {
							if (slot.getStack().getDisplayName() != " " && slot.getStack().getDisplayName() != "" && slot.getStack().getDisplayName() != null ) {
								
								if (slot.getStack().getDisplayName().toLowerCase().contains(arr[3].toLowerCase()) && !slot.getStack().isItemEnchanted()) {
									Utilities.showOnSlot(chest.inventorySlots.size(), slot.xDisplayPosition, slot.yDisplayPosition, Color.green.getRGB());
								}
								
							}
						}
					}
				}
				
			}
		}
	}
	
}
