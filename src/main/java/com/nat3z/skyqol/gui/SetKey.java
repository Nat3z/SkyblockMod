package com.nat3z.skyqol.gui;

import com.nat3z.skyqol.Config;
import com.nat3z.skyqol.Main;

import me.nat3z.Utilities;
import net.minecraft.client.Minecraft;
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
        return "smsetkey";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/smsetkey" + " <key>";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] arg1) throws CommandException {
    	System.out.println(arg1);
    	if (arg1.length == 0) {
    		String api = Main.persistentValues.getAPI();
    		Utilities.sendWarning("Usage: " + getCommandUsage(sender) + " YOUR CURRENT KEY IS: " + api);
    		return;
    	}
    	Main.persistentValues.setAPI(arg1[0]);
    	System.out.println("Modified API key");
    	Utilities.sendMessage("The API Key Has Been Set To: " + arg1[0]);
    }
    
    public int getRequiredPermissionLevel() {
        return 0;
    }

    public boolean canCommandSenderUseCommand(final ICommandSender sender) {
        return true;
    }

}