package com.nat3z.skyqol.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class TextDraw {
	
	public void drawText(String Text, int x, int y, int color) {
		
		FontRenderer fRender = Minecraft.getMinecraft().fontRendererObj;
		
		fRender.drawString(Text, x, y, color);
		return;
		
	}
	
}