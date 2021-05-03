package com.nat3z.skyqol.features.playlist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.nat3z.skyqol.utils.Utilities;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;

public class PlaylistFolder {
	public static String songName = " ";
	public static List<Song> songlist = new ArrayList<>();
	public static List<String> songlistName = new ArrayList<>();
	public static int currentSong = -1;
	
	
	private final File folder;
	
	public PlaylistFolder(File configDir) {
		this.folder = new File(configDir.getAbsolutePath() + "/nsm_playlist/");
		createSongFolder();
	}
	
	public void createSongFolder() {
		if (!this.folder.exists()) {
			System.out.println("[Nate's Secret Mod] Song Folder Does Not Exist! Creating One..");
			try {
				folder.mkdir();
				initSongs();
			} catch (Exception e) { 
				System.out.println("[Nate's Secret Mod] Unable to create Song Folder.");
			}
		} else {
			initSongs();
		}
	}
	
	public int currentsongSubtract() {
		currentSong--;
		return currentSong;
	}
	
	public void initSongs() {
        if (folder == null) return;
        if (!folder.exists()) {
        	createSongFolder(); 
        	return;	
        }
        
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
		    if (file.isFile() && file.getName().endsWith(".wav") || file.getName().endsWith(".mp3")) {
		    	try {
		    		System.out.println(file.getName());
					songlist.add(new Song(file, 1));
					songlistName.add(file.getName().replaceAll(".wav", ""));
				} catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
					System.out.println("[Nate's Secret Mod] Playlist Creation Error!");
					e.printStackTrace();
				}
		    }
		}
        
	}
	
	/**
	 * thx dankers skyblock mod ily <3
	 * @author Dankers
	 *
	 */
    public static class Song {
    	public File file = null;
        public Clip music;
        public boolean paused = false;
        
        
        public Song(File file, int volume) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
            if (file.exists()) {
            	this.file = file;
                music = AudioSystem.getClip();
                AudioInputStream ais = AudioSystem.getAudioInputStream(file);
                music.open(ais);
                
                setVolume(volume);
            }
        }

        public void start() {
            if (music != null) {
            	PlaylistFolder.songName = file.getName().replaceAll(".wav", "");
                music.setMicrosecondPosition(0);
                paused = false;
        		PlaylistFolder.songName = EnumChatFormatting.GREEN + PlaylistFolder.songName;
                music.start();
            }
        }

        public void stop() {
            if (music != null) {
            	music.stop();
                paused = false;
            	PlaylistFolder.songName = " ";
            }
        }
        
        public void pause() {
        	if (music != null) {
        		music.stop();
        		paused = true;
        		PlaylistFolder.songName = EnumChatFormatting.YELLOW + PlaylistFolder.songName;
        	}
        }
        
        public void unpause() {
        	if (music != null) {
        		music.start();
        		paused = false;
        		PlaylistFolder.songName = EnumChatFormatting.GREEN + PlaylistFolder.songName;
        	}
        }

        public boolean setVolume(int volume) {
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            if (music == null) return false;
            if (volume <= 0 || volume > 100) {
                if (player != null) Utilities.sendWarning("Volume can only be set between 0% and 100%.");;
                return false;
            }

            float decibels = (float) (20 * Math.log(volume / 100.0));
            FloatControl control = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
            if (decibels <= control.getMinimum() || decibels >= control.getMaximum()) {
                return false;
            }

            control.setValue(decibels);
            return true;
        }

    }
}
