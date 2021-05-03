package com.nat3z.skyqol.gui;

import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.config.Config;
import com.nat3z.skyqol.utils.Utilities;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class SetKey extends CommandBase {

    @SuppressWarnings("unused")
	private Main mod;

    public SetKey(Main mod) {
        this.mod = mod;
    }

    @Override
    public String getCommandName() {
        return "nsmsetkey";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/nsmsetkey" + " <key>";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] arg1) throws CommandException {
    	System.out.println(arg1);
    	if (arg1.length == 0) {
    		Utilities.sendWarning("Usage: " + getCommandUsage(sender));
    		return;
    	}
    	Config.writeStringConfig("api", "apiKey", arg1[0].replaceAll("-", ""));
    	System.out.println("Modified API key");
    	Utilities.sendMessage("Successfully modified API key.");
    	
    	Main.config.reloadConfig();
    }
    
    public int getRequiredPermissionLevel() {
        return 0;
    }

    public boolean canCommandSenderUseCommand(final ICommandSender sender) {
        return true;
    }

}