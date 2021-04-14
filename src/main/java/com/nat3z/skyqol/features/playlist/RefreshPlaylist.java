package com.nat3z.skyqol.features.playlist;

import com.nat3z.skyqol.Main;

import me.nat3z.Utilities;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class RefreshPlaylist extends CommandBase {

	@Override
	public String getCommandName() {
		return "refreshplaylist";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/refreshplaylist";
	}

	@SuppressWarnings("static-access")
	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		Main.playlist.songlist.forEach(song -> song.stop());
		Main.playlist.songName = " ";
		Main.playlist.currentSong = -1;
		Main.playlist.songlist.clear();
		Main.playlist.initSongs();
		
		Utilities.sendMessage("Successfully Refreshed Playlist!");
	}
	
    public int getRequiredPermissionLevel() {
        return 0;
    }

    public boolean canCommandSenderUseCommand(final ICommandSender sender) {
        return true;
    }
	
	
}
