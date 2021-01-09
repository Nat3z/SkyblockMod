package com.nat3z.skyqol.gui;

import com.nat3z.skyqol.Main;

import club.sk1er.mods.core.ModCore;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class Commands extends CommandBase {

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
        ModCore.getInstance().getGuiHandler().open(Main.INSTANCE.getConfig().gui());
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public boolean canCommandSenderUseCommand(final ICommandSender sender) {
        return true;
    }

}