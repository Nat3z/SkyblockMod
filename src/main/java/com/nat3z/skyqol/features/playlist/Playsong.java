package com.nat3z.skyqol.features.playlist;

import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.utils.Utilities;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class Playsong extends CommandBase {

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "playnsm";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "/playnsm SONGID";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (Main.playlist.songlist.isEmpty()) {
			Utilities.sendWarning("No Songs Are Found! Add songs in the .wav format to the nsm_playlist folder in your config then /refreshplaylist!");
			return;
		}
	
		Main.playlist.songlist.get(0).start();
		Utilities.sendMessage("Now playing " + Main.playlist.songlistName.get(0) + "!");
		
	}
	
    public int getRequiredPermissionLevel() {
        return 0;
    }

    public boolean canCommandSenderUseCommand(final ICommandSender sender) {
        return true;
    }

}
