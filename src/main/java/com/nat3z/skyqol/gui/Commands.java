package com.nat3z.skyqol.gui;

import com.nat3z.skyqol.Config;
import com.nat3z.skyqol.Main;

import me.nat3z.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class Commands extends CommandBase {

    @SuppressWarnings("unused")
	private Main mod;

    public Commands(Main mod) {
        this.mod = mod;
    }

    @Override
    public String getCommandName() {
        return "nsm";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/nsm";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
    	this.mod.openGUI();
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public boolean canCommandSenderUseCommand(final ICommandSender sender) {
        return true;
    }

}

class shortcommand extends CommandBase {
	private Main mod;

    public shortcommand(Main mod) {
        this.mod = mod;
    }

    @Override
    public String getCommandName() {
        return "nateskyblockmod";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/nateskyblockmod";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
    	this.mod.openGUI();
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public boolean canCommandSenderUseCommand(final ICommandSender sender) {
        return true;
    }
}