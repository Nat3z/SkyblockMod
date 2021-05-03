package com.nat3z.skyqol.gui;

import java.awt.Color;

import com.nat3z.skyqol.config.Feature;
import com.nat3z.skyqol.utils.Utilities;
import com.nat3z.skyqol.utils.gui.Button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiControls;
import net.minecraft.client.gui.GuiKeyBindingList;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.fml.client.GuiModList;
import net.minecraftforge.fml.client.GuiScrollingList;
import net.minecraftforge.fml.client.GuiSlotModList;

@SuppressWarnings("unused")
public class GuiRunner extends GuiScrollingList {


    private final GUI parent;

    public GuiRunner(GUI parent, int listWidth) {
        super(Minecraft.getMinecraft(), listWidth, parent.height, 32, parent.height - 5, 5, 15, parent.width, parent.height);
        this.parent = parent;
    }

    @Override
    protected int getSize() {
        return 30;
    }

    @Override
    protected void drawBackground() {
        this.parent.drawDefaultBackground();
    }

    @Override
    protected int getContentHeight() {
        return (this.getSize()) * 15 + 1;
    }

    @Override
    protected void drawScreen(int mouseX, int mouseY) {
        parent.showText(0, "oh", "sahi");

        super.drawScreen(mouseX, mouseY);
    }

    @Override
    protected void drawSlot(int idx, int right, int top, int height, Tessellator tess) {
    	FontRenderer font = Minecraft.getMinecraft().fontRendererObj;
        font.drawString(font.trimStringToWidth("a",       listWidth - 10), this.left + 3 , top +  2, 0xFF2222);
    	
    }

    protected int getRight() {
        return this.right;
    }

	@Override
	protected void elementClicked(int index, boolean doubleClick) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isSelected(int index) {
		// TODO Auto-generated method stub
		return false;
	}
    
}
