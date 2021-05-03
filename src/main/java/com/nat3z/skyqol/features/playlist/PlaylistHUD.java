package com.nat3z.skyqol.features.playlist;

import java.awt.Color;

import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.config.Feature;
import com.nat3z.skyqol.features.playlist.PlaylistFolder.Song;
import com.nat3z.skyqol.utils.Utilities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;

public class PlaylistHUD {
	
    public static final ResourceLocation Menu = new ResourceLocation("natemodskyblock", "bigo.png");
	
	@SuppressWarnings("static-access")
	@SubscribeEvent
	public void playlist(RenderTickEvent event) {
		if (!Feature.MusicHud)
			return;
		if (Minecraft.getMinecraft().currentScreen instanceof GuiScreen ) {
			return;
		}
			if (!PlaylistFolder.songName.equals(" ")) {
		        GlStateManager.enableBlend();
		        Gui.drawRect(Feature.MusicHUD[0], Feature.MusicHUD[1], Feature.MusicHUD[0] + 200, Feature.MusicHUD[1] + 40, new Color(168, 168, 168, 125).getRGB());
		        Minecraft.getMinecraft().fontRendererObj.drawString(Main.playlist.songName, Feature.MusicHUD[0] + 10, Feature.MusicHUD[1] + 15, Color.white.getRGB(), true);
		        Minecraft.getMinecraft().fontRendererObj.drawString((Main.playlist.currentSong + 1) + "/" + Main.playlist.songlist.size(), Feature.MusicHUD[0] + 10, Feature.MusicHUD[1] + 26, Color.white.getRGB(), true);
		        
		        if (Main.playlist.songlist.get(Main.playlist.currentSong).paused) {
			        Minecraft.getMinecraft().fontRendererObj.drawString("Paused", Feature.MusicHUD[0] + 50, Feature.MusicHUD[1] + 26, Color.yellow.getRGB(), true);
		        }
		        
			} else {
		        GlStateManager.enableBlend();
		        Gui.drawRect(Feature.MusicHUD[0], Feature.MusicHUD[1], Feature.MusicHUD[0] + 200, Feature.MusicHUD[1] + 40, new Color(168, 168, 168, 125).getRGB());
		        Minecraft.getMinecraft().fontRendererObj.drawString("No Song Selected!", Feature.MusicHUD[0] + 10, Feature.MusicHUD[1] + 15, Color.white.getRGB(), true);
			}
	}
	
	@SubscribeEvent
	public void leaveWorldEvent(RenderTickEvent event) {
		if (Minecraft.getMinecraft().currentScreen instanceof GuiScreen) {
			if (PlaylistFolder.songName.equals(" ")) {
				PlaylistFolder.songName = " ";
				for (Song song : PlaylistFolder.songlist) {
					song.stop();
				}
			}
		}
	}
	
    public void drawString(String text, int width, int height, int color, int scale) {
        GlStateManager.pushMatrix();
        GlStateManager.scale(scale, scale, scale);
        Minecraft.getMinecraft().fontRendererObj.drawString(text, width, height, color, true);
        GlStateManager.popMatrix();
    }
	
}
