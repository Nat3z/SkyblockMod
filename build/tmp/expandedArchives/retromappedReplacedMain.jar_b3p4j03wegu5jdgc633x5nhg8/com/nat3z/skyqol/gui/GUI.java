/*
 * Welcome to DungeonsMod GUI Utilities!
 * I have used the following sources to help create this GUI
 * 
 * Sources:
 * Refraction Mod Template
 * StackOverflow
 * fourms.minecraftforge.net
 */

package com.nat3z.skyqol.gui;

import java.awt.Color;

import me.nat3z.Utilities;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;

public class GUI extends GuiScreen {

    private GuiButton button1;
    private byte byte0 = -16;
    @Override
    public void func_73866_w_() {
    	super.func_73866_w_();
    	button1 = new GuiButton(1, this.getCenter() - 100, this.getRowPos(4), 200, 20, EnumChatFormatting.RED + "Enable Mod");
        field_146292_n.add(button1);
    }

    @Override
    public void func_73863_a(final int x, final int y, final float partialTicks) {
        // dark background
        super.func_146276_q_();
        this.field_146289_q.func_78276_b("Nate's Skyblock Mod", this.getCenter() - 10, this.getRowPos(4), getChromaColor());
        super.func_73863_a(x, y, partialTicks);
    }

    protected void func_146284_a(final GuiButton guiButton) {
        if (guiButton.field_146124_l) {
            switch (guiButton.field_146127_k) {
                case 1: {
                	guiButton.field_146126_j = EnumChatFormatting.GREEN + "Enable Mod";
                    Utilities.sendMessage(EnumChatFormatting.GREEN + "Mod Enabled");
                    break;
                }
            }
        }
    }

    public int getRowPos(final int rowNumber) {
        return this.field_146295_m / 4 + (24 * rowNumber - 24) + this.byte0;
    }

    public int getCenter() {
        return this.field_146294_l / 2;
    }

    public static int getChromaColor() {
        return Color.HSBtoRGB(System.currentTimeMillis() % 1000L / 1000.0f, 0.8f, 0.8f);
    }

}