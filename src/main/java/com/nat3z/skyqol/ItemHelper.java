package com.nat3z.skyqol;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class ItemHelper extends CommandBase {

    @SuppressWarnings("unused")
	private Main mod;

    public ItemHelper(Main mod) {
        this.mod = mod;
    }
	
	@Override
	public String getCommandName() {
		return "splitminion";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/splitminion";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		
	}

}
