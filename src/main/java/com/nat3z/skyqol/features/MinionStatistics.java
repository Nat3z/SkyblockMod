package com.nat3z.skyqol.features;

import java.awt.Color;
import java.util.List;

import com.nat3z.skyqol.Main;

import club.sk1er.mods.core.universal.ChatColor;
import me.nat3z.ItemUtils;
import me.nat3z.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class MinionStatistics {

	
	
	@SubscribeEvent
	public void minionStats(GuiScreenEvent.BackgroundDrawnEvent event) {
		if (!Main.config.isMinionStats() || !Main.config.isModEnabled())
			return;
		
		try {
		if (Minecraft.getMinecraft().currentScreen instanceof GuiChest  && Main.isOnSkyblock()) {
			GuiChest inventory = (GuiChest) Minecraft.getMinecraft().currentScreen;
    			
    			
    			ContainerChest inv = (ContainerChest) inventory.inventorySlots;
    			
    			
    			
    			IInventory nameinv = inv.getLowerChestInventory();
    			if (!nameinv.getDisplayName().getUnformattedText().contains("Minion") || nameinv.getDisplayName().getUnformattedText().equals("Crafted Minions") || nameinv.getDisplayName().getUnformattedText().contains("Recipe") || nameinv.getDisplayName().getUnformattedText().contains("Chest"))
    				return;
    			
    			if (nameinv.getDisplayName().getUnformattedText().contains("Fishing Minion")) {
    				
    				List<String> lore = ItemUtils.getItemLore(inv.getSlot(4).getStack());
    				
    				String TimeBetweenActions = lore.get(5);
    				TimeBetweenActions = ChatColor.stripColor(TimeBetweenActions);
    				
    				String MaxStorage = lore.get(6);
    				MaxStorage = ChatColor.stripColor(MaxStorage);

    				String ResourcesGenerated = lore.get(7);
    				ResourcesGenerated = ChatColor.stripColor(ResourcesGenerated);
    				
    				ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

    				int height = sr.getScaledHeight();
    				int width = sr.getScaledWidth();
    				
    				Minecraft.getMinecraft().fontRendererObj.drawString(EnumChatFormatting.GREEN + ResourcesGenerated,(int) (width / 1.4 - 100), (int) (height *0.410), Color.white.getRGB(), true);
    				Minecraft.getMinecraft().fontRendererObj.drawString(EnumChatFormatting.GREEN + MaxStorage,(int) (width / 1.4 - 100), (int) (height *0.430), Color.white.getRGB(), true);
    				Minecraft.getMinecraft().fontRendererObj.drawString(EnumChatFormatting.GREEN + TimeBetweenActions, (int) (width / 1.4 - 100), (int) (height *0.450), Color.white.getRGB(), true);
    				
    				return;
    			}
    			
				List<String> lore = ItemUtils.getItemLore(inv.getSlot(4).getStack());
				
				String TimeBetweenActions = lore.get(6);
				TimeBetweenActions = ChatColor.stripColor(TimeBetweenActions);
				
				String MaxStorage = lore.get(7);
				MaxStorage = ChatColor.stripColor(MaxStorage);

				String ResourcesGenerated = lore.get(8);
				ResourcesGenerated = ChatColor.stripColor(ResourcesGenerated);
				
				ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

				int height = sr.getScaledHeight();
				int width = sr.getScaledWidth();
				
				Minecraft.getMinecraft().fontRendererObj.drawString(EnumChatFormatting.GREEN + ResourcesGenerated,(int) (width / 1.4 - 100), (int) (height *0.410), Color.white.getRGB(), true);
				Minecraft.getMinecraft().fontRendererObj.drawString(EnumChatFormatting.GREEN + MaxStorage,(int) (width / 1.4 - 100), (int) (height *0.430), Color.white.getRGB(), true);
				Minecraft.getMinecraft().fontRendererObj.drawString(EnumChatFormatting.GREEN + TimeBetweenActions, (int) (width / 1.4 - 100), (int) (height *0.450), Color.white.getRGB(), true);

		}
		
		} catch (NullPointerException | ArrayIndexOutOfBoundsException ex) {  } // Handles Exception, If You Receive ArrayIndexOutOfBounds Exception, Please Contact me.
	}
	
	
}
