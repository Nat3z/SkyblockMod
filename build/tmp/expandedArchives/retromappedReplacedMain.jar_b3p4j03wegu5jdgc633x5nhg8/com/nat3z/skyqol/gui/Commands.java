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
    public String func_71517_b() {
        return "nsm";
    }

    @Override
    public String func_71518_a(ICommandSender sender) {
        return "/nsm";
    }

    @Override
    public void func_71515_b(ICommandSender sender, String[] args) throws CommandException {
        ModCore.getInstance().getGuiHandler().open(Main.INSTANCE.getConfig().gui());
    }

    public int func_82362_a() {
        return 0;
    }

    public boolean func_71519_b(final ICommandSender sender) {
        return true;
    }

}