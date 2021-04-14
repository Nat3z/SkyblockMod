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
import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.nat3z.skyqol.Main;

import me.nat3z.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;

public class GUIKicker extends GuiScreen {
	private GuiButton closeGUI;
	private GuiButton mage;
	private GuiButton bers;
	private GuiButton tank;

	private GuiButton archer;
	private GuiButton healer;
	private GuiTextField minlvl;
    @SuppressWarnings("unused")
	private byte byte0 = -16;
    
    @Override
    public void initGui() {
    	
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
				
		int height = sr.getScaledHeight();
		int width = sr.getScaledWidth();
    	super.initGui();
    	System.out.println("ba");
    	// Always Present
		mage = new GuiButton(0, width / 2 - 200, (int) (height * 0.1), 100, 20, EnumChatFormatting.GRAY + "Mage");
		bers = new GuiButton(0, width / 2 + 100, (int) (height * 0.1), 100, 20, EnumChatFormatting.GRAY + "Berserk");
		tank = new GuiButton(0, width / 2 - 50, (int) (height * 0.1), 100, 20, EnumChatFormatting.GRAY + "Tank");
		archer = new GuiButton(0, width / 2 + 30, (int) (height * 0.2), 100, 20, EnumChatFormatting.GRAY + "Archer");
		healer = new GuiButton(0, width / 2 - 130, (int) (height * 0.2), 100, 20, EnumChatFormatting.GRAY + "Healer");
		minlvl = new GuiTextField(0, fontRendererObj, width / 2 - 10, (int) (height * 0.3), 20, 20);
		if (Main.apis.getMincdk() != null)
			minlvl.setText(Main.apis.getMincdk());
		else {
			Main.apis.setMincdk("0");
			minlvl.setText(Main.apis.getMincdk());
		}
		
		minlvl.setFocused(true);
		minlvl.setMaxStringLength(2);
		closeGUI = new GuiButton(0, 2, height - 20, 80, 20, "Close");
		
		
        buttonList.add(closeGUI);
        buttonList.add(mage);
        buttonList.add(bers);
        buttonList.add(tank);
        buttonList.add(healer);
        buttonList.add(archer);
        pages();
    }

    @Override
    public void drawScreen(final int x, final int y, final float partialTicks) {
        // dark background
    	
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

		int height = sr.getScaledHeight();
		int width = sr.getScaledWidth();
		 		
        super.drawDefaultBackground();
        
        Minecraft.getMinecraft().fontRendererObj.drawString("Minimum Class Level", width / 2 - 55, (int) (height * 0.4), Color.white.getRGB(), true);
        Minecraft.getMinecraft().fontRendererObj.drawString("If you are doing a single digit number, please do not have 0 as the first number.", width / 2 - 200, (int) (height * 0.45), Color.red.getRGB(), true);
        minlvl.drawTextBox();
        super.drawScreen(x, y, partialTicks);

    }
    
    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        minlvl.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
    	if (typedChar == '0' || typedChar == '1' || typedChar == '2' || typedChar == '3' || typedChar == '4' || typedChar == '5' || typedChar == '6' ||
    			typedChar == '7' || typedChar == '8' || typedChar == '9' || keyCode == Keyboard.KEY_BACK)
    	{
            minlvl.textboxKeyTyped(typedChar, keyCode);
            Main.apis.setMincdk(minlvl.getText());
    	}
    	else if (keyCode == Keyboard.KEY_RIGHT || keyCode == Keyboard.KEY_LEFT)
            minlvl.textboxKeyTyped(typedChar, keyCode);
    	else if (keyCode == Keyboard.KEY_ESCAPE)
    		minlvl.setFocused(false);
    }
    @Override
    public void updateScreen() {
        minlvl.updateCursorCounter();
    }

    protected void actionPerformed(final GuiButton guiButton) {
        if (guiButton.enabled) {
        	String buttonwithout = StringUtils.stripControlCodes(guiButton.displayString);
        	if (buttonwithout.contains("Support")) {
        		Utilities.sendWarning("I'm sorry, this module is restricted to only Supporters because it is in its beta stages. If you want access to this, please report bugs or apply fixes/suggestions to our Github page.");
        		return;
        	} else if (buttonwithout.contains("Force Disabled")) {
        		Utilities.sendWarning("I'm sorry, this module has been remotely disabled. This only happens if something bad happened in the code or it has been deemed as a bannable offense by the Hypixel Staff.");
        		return;
        	}
        	switch (buttonwithout) {
        	
    			case "Archer":
    				if (Main.persistentValues.isArcherdk())
    					Main.persistentValues.setArcherdk(false);
    				else
    					Main.persistentValues.setArcherdk(true);
    				break;
    				
    			case "Berserk":
    				if (Main.persistentValues.isBersdk())
    					Main.persistentValues.setBersdk(false);
    				else
    					Main.persistentValues.setBersdk(true);
    				break;
    				
    			case "Healer":
    				if (Main.persistentValues.isHealerdk())
    					Main.persistentValues.setHealerdk(false);
    				else
    					Main.persistentValues.setHealerdk(true);
    				break;
    				
    			case "Tank":
    				if (Main.persistentValues.isTankdk())
    					Main.persistentValues.setTankdk(false);
    				else
    					Main.persistentValues.setTankdk(true);
    				break;
    				
    			case "Mage":
    				if (Main.persistentValues.isMagedk())
    					Main.persistentValues.setMagedk(false);
    				else
    					Main.persistentValues.setMagedk(true);
    				break;
    				
    			case "Close": {
    				Minecraft.getMinecraft().thePlayer.closeScreen();
    				return;
    			}
        	}
        	pages();
        }
    }
   


    public static int getChromaColor() {
        return Color.HSBtoRGB(System.currentTimeMillis() % 1000L / 1000.0f, 0.8f, 0.8f);
    }
    
	public void pages() {
		
		if (Main.persistentValues.isArcherdk())
			archer.displayString = EnumChatFormatting.GREEN + "Archer";
		else
			archer.displayString = EnumChatFormatting.RED + "Archer";
		
		if (Main.persistentValues.isBersdk())
			bers.displayString = EnumChatFormatting.GREEN + "Berserk";
		else
			bers.displayString = EnumChatFormatting.RED + "Berserk";
		
		if (Main.persistentValues.isHealerdk())
			healer.displayString = EnumChatFormatting.GREEN + "Healer";
		else
			healer.displayString = EnumChatFormatting.RED + "Healer";
		
		if (Main.persistentValues.isTankdk())
			tank.displayString = EnumChatFormatting.GREEN + "Tank";
		else
			tank.displayString = EnumChatFormatting.RED + "Tank";
		
		if (Main.persistentValues.isMagedk())
			mage.displayString = EnumChatFormatting.GREEN + "Mage";
		else
			mage.displayString = EnumChatFormatting.RED + "Mage";
		
    }
}