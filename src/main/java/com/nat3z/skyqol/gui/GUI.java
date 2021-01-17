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
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import com.google.gson.JsonObject;
import com.nat3z.skyqol.CheckForUpdates;
import com.nat3z.skyqol.Main;

import me.nat3z.APIHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringUtils;
import net.minecraftforge.fml.common.versioning.DefaultArtifactVersion;

public class GUI extends GuiScreen {
	private GuiButton closeGUI;
	private GuiButton backPage;
	private GuiButton nextPage;
	private GuiButton button2;
	private GuiButton button3;
	private int page;
    private GuiButton button1;
    private GuiButton button4;
    private GuiButton github;
    
    private GuiButton updatetoversion;

	
    @SuppressWarnings("unused")
	private byte byte0 = -16;
    
    public GUI (int page) {
    	this.page = page;
    }
    
    @Override
    public void initGui() {
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

		int height = sr.getScaledHeight();
		int width = sr.getScaledWidth();
    	super.initGui();
    	
    	// Always Present
		backPage = new GuiButton(0, width / 2 - 100, (int) (height * 0.1), 80, 20, EnumChatFormatting.GRAY + "< Back");
		nextPage = new GuiButton(0, width / 2 + 20, (int) (height * 0.1), 80, 20, EnumChatFormatting.GRAY + "Next >");
    	
		button1 = new GuiButton(0, width / 2 - 100, (int) (height *0.2), EnumChatFormatting.GRAY + "None");
		
		button2 = new GuiButton(0, width / 2 - 100, (int) (height *0.3), EnumChatFormatting.GRAY + "None");
		button3 = new GuiButton(0, width / 2 - 100, (int) (height *0.4), EnumChatFormatting.GRAY + "None");
		button4 = new GuiButton(0, width / 2 - 100, (int) (height *0.5), EnumChatFormatting.GRAY + "None");

		closeGUI = new GuiButton(0, 2, height - 20, 80, 20, "Close");
		github = new GuiButton(0, 2, height - 40, 80, 20, "Github");

		pages();
		
		
		if (CheckForUpdates.updateversion) {
    		updatetoversion = new GuiButton(0, width / 2 - 100, (int) (height * 0.9), EnumChatFormatting.GREEN + "Update Nate's Skyblock Mod");
    		this.buttonList.add(updatetoversion);
    	}
		
		
		
        buttonList.add(closeGUI);
        buttonList.add(nextPage);
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(button4);
        buttonList.add(github);

    }

    @Override
    public void drawScreen(final int x, final int y, final float partialTicks) {
        // dark background
        super.drawDefaultBackground();
        super.drawScreen(x, y, partialTicks);

    }

    protected void actionPerformed(final GuiButton guiButton) {
        if (guiButton.enabled) {
        	String buttonwithout = StringUtils.stripControlCodes(guiButton.displayString);
        	
        	//if (buttonwithout.equals("Close")) {
            //	Minecraft.getMinecraft().thePlayer.closeScreen();
           // 	return;
          //  }
        	switch (buttonwithout) {
        	
    			case "Mod Status: Disabled":
    				Main.config.setModEnabled(true);
    				break;
    			case "Mod Status: Enabled":
    				Main.config.setModEnabled(false);
    				break;
    				
    			case "Anti Non Enchanted: Disabled":
    				Main.config.setAntiNonEnchantedEnabled(true);
    				break;
    			case "Anti Non Enchanted: Enabled":
    				Main.config.setAntiNonEnchantedEnabled(false);
    				break;
    				
    			case "Highlight Unclaimed Contests: Enabled":
    				Main.config.setUnclaimedFarmingContest(false);
    				break;
            	
    			case "Highlight Unclaimed Contests: Disabled":
    				Main.config.setUnclaimedFarmingContest(true);
            		break;
            		
    			case "Update Nate's Skyblock Mod":
    				try {
    					Desktop.getDesktop().browse(new URI("https://github.com/Nat3z/SkyblockMod/releases/latest"));
    				} catch (IOException | URISyntaxException e) {
    					e.printStackTrace();
    				}
    				break;
        	
    			case "Warning for Rare Items in Sec. Chest: Enabled":
    				Main.config.setWarnPeopleForRarerItemInSecretChest(false);
    				break;
            	
    			case "Warning for Rare Items in Sec. Chest: Disabled":
    				Main.config.setWarnPeopleForRarerItemInSecretChest(true);
            		break;
            		
    			case "Next >":
    				page++;
    				break;
    			case "< Back":
    				page--;
    				break;
            	
    			case "Github":
    				try {
    					Desktop.getDesktop().browse(new URI("https://github.com/Nat3z/SkyblockMod"));
    				} catch (IOException | URISyntaxException e) {
    					e.printStackTrace();
    				}
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
		switch (page) {
			case 1:
				buttonList.remove(backPage);
				buttonList.remove(backPage);

				buttonList.add(nextPage);
				if (Main.config.isModEnabled())
					button1.displayString = "Mod Status: " + EnumChatFormatting.GREEN + "Enabled";
				else
					button1.displayString = "Mod Status: " + EnumChatFormatting.RED + "Disabled";
				
				if (Main.config.isAntiNonEnchantedEnabled())
					button2.displayString = "Anti Non Enchanted: " + EnumChatFormatting.GREEN + "Enabled";
				else
					button2.displayString = "Anti Non Enchanted: " + EnumChatFormatting.RED + "Disabled";

				if (Main.config.isUnclaimedFarmingContest())
					button3.displayString = "Highlight Unclaimed Contests: " + EnumChatFormatting.GREEN + "Enabled";
				else
					button3.displayString = "Highlight Unclaimed Contests: " + EnumChatFormatting.RED + "Disabled";

				if (Main.config.isWarnPeopleForRarerItemInSecretChest())
					button4.displayString = "Warning for Rare Items in Sec. Chest: " + EnumChatFormatting.GREEN + "Enabled";
				else
					button4.displayString = "Warning for Rare Items in Sec. Chest: " + EnumChatFormatting.RED + "Disabled";
			break;
	    
			case 2:
				buttonList.add(backPage);
				buttonList.remove(nextPage);
				button1.displayString = EnumChatFormatting.GRAY + "None";
				
				button2.displayString = EnumChatFormatting.GRAY + "None";
				button3.displayString = EnumChatFormatting.GRAY + "None";
				button4.displayString = EnumChatFormatting.GRAY + "None";


			break;
			
			default:
				page = 2;
				pages();
		}
    }
    

}
