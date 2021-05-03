package com.nat3z.skyqol.features.playlist;

import org.lwjgl.input.Keyboard;

import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.config.Feature;
import com.nat3z.skyqol.events.KeyPressedEvent;
import com.nat3z.skyqol.features.playlist.PlaylistFolder.Song;
import com.nat3z.skyqol.utils.Utilities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PressKeyPlay {
	
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void press(KeyPressedEvent event) {
		if (!Feature.MusicPlayer)
			return;
		
		if (Minecraft.getMinecraft().currentScreen instanceof GuiScreen)
			return;
		
		
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			
			if (Keyboard.isKeyDown(Keyboard.KEY_BACK)) {
				if (PlaylistFolder.songlist.isEmpty()) {
					Utilities.sendWarning("No Songs Are Found! Add songs in the .wav format to the nsm_playlist folder in your config then /refreshplaylist!");
					return;
				}
				
				if (PlaylistFolder.currentSong == -1)
					return;
				
				
				if (PlaylistFolder.songlist.get(PlaylistFolder.currentSong).paused)
					PlaylistFolder.songlist.get(PlaylistFolder.currentSong).unpause();
				else if (!PlaylistFolder.songlist.get(PlaylistFolder.currentSong).paused)
					PlaylistFolder.songlist.get(PlaylistFolder.currentSong).pause();
			}
			
			else if (Keyboard.isKeyDown(Keyboard.KEY_EQUALS) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
				if (PlaylistFolder.songlist.isEmpty()) {
					Utilities.sendWarning("No Songs Are Found! Add songs in the .wav format to the nsm_playlist folder in your config then /refreshplaylist!");
					return;
				}
				
				// > = Greater Than
				// < = Less than
				
				if (PlaylistFolder.currentSong >= (PlaylistFolder.songlist.size() - 1))
					return;
								
				try {
					for (Song song : PlaylistFolder.songlist) {
							song.stop();
					}
					PlaylistFolder.songlist.get(PlaylistFolder.currentSong += 1).start();
				} catch (IndexOutOfBoundsException e) {			e.printStackTrace();		}
			} else if (Keyboard.isKeyDown(Keyboard.KEY_MINUS) || Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
				if (PlaylistFolder.songlist.isEmpty()) {
					Utilities.sendWarning("No Songs Are Found! Add songs in the .wav format to the nsm_playlist folder in your config then /refreshplaylist!");
					return;
				}
				
				if (PlaylistFolder.currentSong == 0 || PlaylistFolder.currentSong == -1)
					return;
				
				if ((PlaylistFolder.currentSong - 1) != -1) {
					for (Song song : PlaylistFolder.songlist) {
						song.stop();
					}
					PlaylistFolder.songlist.get(Main.playlist.currentsongSubtract()).start();
				}
			}
			
		}
		
		
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void disableMusic(ClientDisconnectionFromServerEvent event) {
		for (Song song : PlaylistFolder.songlist) {
			song.stop();
		}
		
		PlaylistFolder.songName = " ";
		PlaylistFolder.currentSong = -1;
	}
	
}
