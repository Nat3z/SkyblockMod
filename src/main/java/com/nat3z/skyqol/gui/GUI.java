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
    public void initGui() {
    	super.initGui();
    	button1 = new GuiButton(1, this.getCenter() - 100, this.getRowPos(4), 200, 20, EnumChatFormatting.RED + "Enable Mod");
        buttonList.add(button1);
    }

    @Override
    public void drawScreen(final int x, final int y, final float partialTicks) {
        // dark background
        super.drawDefaultBackground();
        this.fontRendererObj.drawString("Nate's Skyblock Mod", this.getCenter() - 10, this.getRowPos(4), getChromaColor());
        super.drawScreen(x, y, partialTicks);
    }

    protected void actionPerformed(final GuiButton guiButton) {
        if (guiButton.enabled) {
            switch (guiButton.id) {
                case 1: {
                	guiButton.displayString = EnumChatFormatting.GREEN + "Enable Mod";
                    Utilities.sendMessage(EnumChatFormatting.GREEN + "Mod Enabled");
                    break;
                }
            }
        }
    }

    public int getRowPos(final int rowNumber) {
        return this.height / 4 + (24 * rowNumber - 24) + this.byte0;
    }

    public int getCenter() {
        return this.width / 2;
    }

    public static int getChromaColor() {
        return Color.HSBtoRGB(System.currentTimeMillis() % 1000L / 1000.0f, 0.8f, 0.8f);
    }

}