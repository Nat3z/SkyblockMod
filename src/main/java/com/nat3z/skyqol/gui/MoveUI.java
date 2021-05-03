package com.nat3z.skyqol.gui;

import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.utils.Utilities;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class MoveUI extends CommandBase {
	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "nsmmove";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "/nsmmove";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		Main.INSTANCE.MoveUI = true;
	}
	
    public int getRequiredPermissionLevel() {
        return 0;
    }

    public boolean canCommandSenderUseCommand(final ICommandSender sender) {
        return true;
    }
}
