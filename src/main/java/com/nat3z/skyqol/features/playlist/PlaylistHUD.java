package com.nat3z.skyqol.features.playlist;

import java.awt.Color;

import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.features.playlist.PlaylistFolder.Song;

import me.nat3z.Utilities;
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
	
	@SubscribeEvent
	public void playlist(RenderTickEvent event) {
		if (!Main.config.isMusicplayerhud())
			return;
		if (Minecraft.getMinecraft().currentScreen instanceof GuiScreen ) {
			return;
		}
			if (!PlaylistFolder.songName.equals(" ")) {
		        GlStateManager.enableBlend();
		        Minecraft.getMinecraft().getTextureManager().bindTexture(Menu);
		        Gui.drawModalRectWithCustomSizedTexture(Main.guic.SongPlaylistLocationX, Main.guic.SongPlaylistLocationY, 0, 0, 200, 40, 356, 267);
		        Minecraft.getMinecraft().fontRendererObj.drawString(Main.playlist.songName, Main.guic.SongPlaylistLocationX + 10, Main.guic.SongPlaylistLocationY + 15, Color.white.getRGB(), true);
		        Minecraft.getMinecraft().fontRendererObj.drawString((Main.playlist.currentSong + 1) + "/" + Main.playlist.songlist.size(), Main.guic.SongPlaylistLocationX + 10, Main.guic.SongPlaylistLocationY + 26, Color.white.getRGB(), true);
		        
		        if (Main.playlist.songlist.get(Main.playlist.currentSong).paused) {
			        Minecraft.getMinecraft().fontRendererObj.drawString("Paused", Main.guic.SongPlaylistLocationX + 50, Main.guic.SongPlaylistLocationY + 26, Color.yellow.getRGB(), true);
		        }
		        
			} else {
		        GlStateManager.enableBlend();
		        Minecraft.getMinecraft().getTextureManager().bindTexture(Menu);
		        Gui.drawModalRectWithCustomSizedTexture(Main.guic.SongPlaylistLocationX, Main.guic.SongPlaylistLocationY, 0, 0, 200, 40, 356, 267);
		        Minecraft.getMinecraft().fontRendererObj.drawString("No Song Selected!", Main.guic.SongPlaylistLocationX + 10, Main.guic.SongPlaylistLocationY + 15, Color.white.getRGB(), true);
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
