package com.nat3z.skyqol.commands;

import com.nat3z.skyqol.CheckIfSupporter;
import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.config.Config;
import com.nat3z.skyqol.config.Feature;
import com.nat3z.skyqol.utils.Utilities;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class DebugToggle extends CommandBase {
	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "nsmdebug";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "/nsmdebug";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		
		if (!Feature.Debug) {
			Feature.Debug = true;
			Utilities.sendMessage("Debug Mode Has Been Enabled.");
		} else {
			Feature.Debug = false;
			Utilities.sendMessage("Debug Mode Has Been Disabled.");
		}
		
		Config.writeBooleanConfig("feature", "Debug", Feature.Debug);
		
	}
	
    public int getRequiredPermissionLevel() {
        return 0;
    }

    public boolean canCommandSenderUseCommand(final ICommandSender sender) {
        return true;
    }
}
