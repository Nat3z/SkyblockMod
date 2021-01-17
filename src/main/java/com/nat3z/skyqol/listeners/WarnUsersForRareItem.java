package com.nat3z.skyqol.listeners;

import com.nat3z.skyqol.Main;

import me.nat3z.ChatColor;
import me.nat3z.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class WarnUsersForRareItem {

	// The Name of thing is "Chest"
	@SubscribeEvent
	public void checksifchest(TickEvent.RenderTickEvent event) {

		if (!Main.config.isWarnPeopleForRarerItemInSecretChest() || !Main.config.isModEnabled())
			return;
		
		try {
			if (Minecraft.getMinecraft().currentScreen instanceof GuiChest) {
				
				if (!Main.isInDungeons())
					return;
				GuiChest inventory = (GuiChest) Minecraft.getMinecraft().currentScreen;
    			
    			
    			ContainerChest inv = (ContainerChest) inventory.inventorySlots;
    			
    			IInventory nameinv = inv.getLowerChestInventory();
    			if (!nameinv.getDisplayName().getUnformattedText().equals("Chest"))
    				return;
    			
    			String stripurmomlastnightxdxddxdxdxd = ChatColor.stripColor(inv.getSlot(13).getStack().getDisplayName().toLowerCase());
    			
    			if (stripurmomlastnightxdxddxdxdxd.contains("treasure") || stripurmomlastnightxdxddxdxdxd.contains("health") || stripurmomlastnightxdxddxdxdxd.contains("key"))
    				Utilities.sendTitleCentered(EnumChatFormatting.GOLD + "Rare Item!");
			}
		} catch (Exception ex) { }
		
	}
	
}
