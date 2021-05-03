package com.nat3z.skyqol.utils.gui;

import java.awt.Color;

import com.nat3z.skyqol.utils.Message;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;

public class MoveabbleButtons extends GuiButton {

	  /**
	   * Create a button that is assigned a feature (to toggle/change color etc.).
	   */
		
	  private String buttonText;
	  private boolean moving = false;
	  public MoveabbleButtons(int buttonId, int x, int y, int width, int height, String buttonText, boolean moving) {
	    super(buttonId, x, y, width, height, buttonText);
	    this.buttonText = buttonText;
	    this.moving = moving;
	  }

	  @SuppressWarnings("static-access")
		@Override
		  public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		    if (visible) {
		      Color cNoHover = new Color(181, 181, 181, 40);
		      Color cYesHover = new Color(68, 116, 179, 100);
	          this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
	          int i = this.getHoverState(this.hovered);
	          
		      if (i == 2 || this.moving) {
			      this.drawCenteredString(buttonText, xPosition + width / 2,
				          yPosition + height / 2 - Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT / 2,
				          new Color(255, 187, 0).getRGB(), 1F);
			      Gui.drawRect(xPosition, yPosition, xPosition + width, yPosition + height, cYesHover.getRGB());
		      } else {
			      this.drawCenteredString(buttonText, xPosition + width / 2,
				          yPosition + height / 2 - Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT / 2,
				          0xFFFFFF, 1F);
			      Gui.drawRect(xPosition, yPosition, xPosition + width, yPosition + height, cNoHover.getRGB());
		      }

		    }
		  }
	  
	  public static void drawCenteredString(String text, int x, int y, int color, double scale) {
		    GlStateManager.pushMatrix();
		    GlStateManager.scale(scale, scale, 1);
		    Minecraft.getMinecraft().fontRendererObj.drawString(text,
		        (int) (x / scale) - Minecraft.getMinecraft().fontRendererObj.getStringWidth(text) / 2,
		        (int) (y / scale), color, true);
		    GlStateManager.popMatrix();
		  }
	  
	  
	}