package com.nat3z.skyqol.features;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.config.Feature;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

public class BonemerangHelper {
	@SubscribeEvent
	public void getBone(RenderTickEvent event) {
		if (!Main.config.modules.get("bonemerangwarn") || !Feature.BonemerangWarn) return;
		if (Minecraft.getMinecraft().currentScreen instanceof GuiScreen) return;
		
		ItemStack item = Minecraft.getMinecraft().thePlayer.getHeldItem();
		
		if (item == null) return;
		
		if (!item.getDisplayName().equals(null) && !item.getDisplayName().equals("") && item.getDisplayName().contains("Bonemerang")) {
			
			if (item.getItem().equals(Items.ghast_tear)) {
    			ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
    			int guiLeft = (sr.getScaledWidth() - 176) / 2;
    			int guiTop = (sr.getScaledHeight() - 222) / 2;
    			
    			int x = guiLeft + 85;
    			int y = guiTop + (int) 120;
    			
    			Minecraft.getMinecraft().fontRendererObj.drawString("✖", x, y, new Color(135, 7, 0).getRGB(), true);
			} else if (item.getItem().equals(Items.bone)) {
    			ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
    			int guiLeft = (sr.getScaledWidth() - 176) / 2;
    			int guiTop = (sr.getScaledHeight() - 222) / 2;
    			
    			int x = guiLeft + 85;
    			int y = guiTop + (int) 120;
    			
    			Minecraft.getMinecraft().fontRendererObj.drawString("✔", x, y, new Color(18, 150, 3).getRGB(), true);
			}
			
			
		}
	}
	
}
