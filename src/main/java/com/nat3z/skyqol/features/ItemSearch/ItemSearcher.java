package com.nat3z.skyqol.features.ItemSearch;

import com.nat3z.skyqol.Main;

import me.nat3z.Utilities;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;

public class ItemSearcher extends CommandBase {

    @SuppressWarnings("unused")
	private Main mod;

    public ItemSearcher(Main mod) {
        this.mod = mod;
    }

    @Override
    public String getCommandName() {
        return "search";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/search ITEM_YOU_ARE_SEARCHING";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
    	if (args.length == 0) {
    		Utilities.sendMessage("No Longer Searching");
    		SearchIt.query = "";
    		return;
    	}
    	String main = "";
    	for (String string : args) {
    		if (string.equals(args[0])) {
        		main += string;
    		} else {
        		main += " " + string;
    		}
		}
		SearchIt.query = main;
		Utilities.sendMessage("Now Searching For " + EnumChatFormatting.ITALIC + main + EnumChatFormatting.RESET + " in inventories.");
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public boolean canCommandSenderUseCommand(final ICommandSender sender) {
        return true;
    }
}
