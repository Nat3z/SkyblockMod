package me.nat3z;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class TextDraw {
	
	public void drawText(String Text, int x, int y, int color) {
		
		FontRenderer fRender = Minecraft.func_71410_x().field_71466_p;
		
		fRender.func_78276_b(Text, x, y, color);
		return;
		
	}
	
}