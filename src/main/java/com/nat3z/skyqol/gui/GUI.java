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
import me.nat3z.gui.Button;

import com.nat3z.skyqol.CheckForUpdates;
import com.nat3z.skyqol.Main;

import me.nat3z.LerpingInteger;
import me.nat3z.Utilities;
import org.lwjgl.input.Mouse;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;

public class GUI extends GuiScreen {
	private GuiButton backPage;
	private GuiButton nextPage;
	private Button button2;
	private Button swapUI;
	private Button EditGuiLocation;

	
	private int page;
    private Button button1;
    private GuiButton github;
    
    private GuiButton updatetoversion;
        
    public final ResourceLocation Menu = new ResourceLocation("natemodskyblock", "bigo.png");
    public final ResourceLocation edit = new ResourceLocation("natemodskyblock", "Edit.png");
    
    public final ResourceLocation Toggoff = new ResourceLocation("natemodskyblock", "toggleoff.png");
    public final ResourceLocation Toggon = new ResourceLocation("natemodskyblock", "toggon.png");
    public final ResourceLocation Toggdis = new ResourceLocation("natemodskyblock", "toggledis.png");

    public final ResourceLocation next = new ResourceLocation("natemodskyblock", "nextpage.png");
    public final ResourceLocation back = new ResourceLocation("natemodskyblock", "backpage.png");

	
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
		
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        int width1 = scaledResolution.getScaledWidth();
        int height1 = scaledResolution.getScaledHeight();

        int xSize = Math.min(width1-100/scaledResolution.getScaleFactor(), 500);
        int ySize = Math.min(height1-100/scaledResolution.getScaleFactor(), 400);

        int x2 = (scaledResolution.getScaledWidth() - xSize)/2;
        int y2 = (scaledResolution.getScaledHeight() - ySize)/2;
		
    	if (width1 <= 455) {
    		updatetoversion = new Button(69420, width / 2 - 100, 0, 500, 30, Menu, "Switch to Classic UI");
    		buttonList.add(updatetoversion);
    		return;
    	}
        
		System.out.println("nsm: " + width1);
		
    	super.initGui();
    	// Always Present
    	
    	if (width1 <= 456) {
        	button1 = new Button(4, x2 / 1 * 25, (int) (height * 0.3), 50, 20, Toggoff, "   ");
        	button2 = new Button(5, x2 / 1 * 25, (int) (height * 0.52), 50, 20, Toggoff, "   ");
    	} else {
        	button1 = new Button(4, (int)(width / 2 * 1.6), (int) (height * 0.3), 50, 20, Toggoff, "   ");
        	button2 = new Button(5, (int)(width / 2 * 1.6), (int) (height * 0.52), 50, 20, Toggoff, "   ");
    	}
    	
		backPage = new Button(50, width / 2 - 150, (int) (height * 0.1), 50, 50, back, "   ");
		nextPage = new Button(51, width / 2 + 100, (int) (height * 0.1), 50, 50, next, "   ");


		github = new Button(99, 0, 0, 50, 50, new ResourceLocation("natemodskyblock", "icons/github.png"), "   ");
		EditGuiLocation = new Button(789, width - 200, 0, 200, 30, new ResourceLocation("natemodskyblock", "bigo.png"), "Edit Gui Locations");

		swapUI = new Button(69420, width / 2 - 100, (int) (height * 0.946), 200, 30, Menu, "Switch to Classic UI");
		        
		buttonList.add(swapUI);
        buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(EditGuiLocation);
		buttonList.add(nextPage);
		buttonList.add(backPage);

        buttonList.add(github);
		
		if (CheckForUpdates.updateversion) {
    		updatetoversion = new Button(100, width / 2 - 100, 0, 200, 30, Menu, "Update Nate's Secret Mod");
    		buttonList.add(updatetoversion);
		}
        wino();
    }
	
	private int pagelen = 6;
	
    @Override
    public void drawScreen(final int x, final int y, final float partialTicks) {
        // dark background
        super.drawDefaultBackground();
        GlStateManager.enableBlend();
        
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        int width = scaledResolution.getScaledWidth();
        int height = scaledResolution.getScaledHeight();

        int xSize = Math.min(width-100/scaledResolution.getScaleFactor(), 500);

        int x2 = (scaledResolution.getScaledWidth() - xSize)/2;
        
        
        if (width <= 455) {
        	drawString("Please Resize your Gui or switch to the Classic UI by clicking this message.", 5, 5, getChromaColor(), 1);
        	
        	return;
        }
        
        Minecraft.getMinecraft().getTextureManager().bindTexture(Menu);
        Gui.drawModalRectWithCustomSizedTexture(x2 / 2, (int) (height * 0.25), 0, 0, (int) (width / 2) + 250, 267, 356, 267);
        
        if (page == 1) {
        	showText(0, "Mod Status", "Change the mod status.");
        	showText(1, "Disable Clicking Unenchanted Items", "Prevents clicking unenchanted items in minion containers.");    
        } else if (page == 2) {
        	showText(0, "Highlight Unclaimed Farming Contests", "Highlights all unclaimed farming contests.");
        	showText(1, "Warn for Rare Items in Secret Chests", "Highlights rare items you obtain from secret chests.");
        } else if (page == 3) {
        	showText(0, "Copy Dungeon Deaths & Fails", "Copies all dungeon deaths and fails.");
        	showText(1, "Reparty Dungeon Teammates", "Reparties all dungeon teammates in party.");
        } else if (page == 4) {
        	showText(0, "Click Anywhere to Open Maddox", "When the Maddox Batphone dialogue appears, you can click anywhere to open.");
        	showText(1, "Dungeon Kicker", "Kick users that join who do not fit sufficient requirements.");
        } else if (page == 5) {
        	showText(0, "Enable Music Player", "Activate the Music Player and read files in nsm_playlist.");
        	showText(1, "Enable Music Player HUD", "Activates a HUD that shows you the current song playing.");
        	
            mc.fontRendererObj.drawString("MEDIA CONTROLS", width / 2 - 50, (int) (height * 0.63), Color.white.getRGB(), true);
            mc.fontRendererObj.drawString("CTRL + BACKSPACE - Pause The Music", width / 2 - 100, (int) (height * 0.66), Color.white.getRGB(), true);
            mc.fontRendererObj.drawString("CTRL + Equals - Next Song In Playlist", width / 2 - 100, (int) (height * 0.69), Color.white.getRGB(), true);
            mc.fontRendererObj.drawString("CTRL + Subtract - Last Song In Playlist", width / 2 - 105, (int) (height * 0.72), Color.white.getRGB(), true);

        } else if (page == 6) {
        	showText(0, "Dungeon Secrets HUD", "Adds a HUD to show the amount of Dungeon Secrets you have in a room.");
        }
    	if (width <= 456) {
            this.drawString("Page " + page + "/" + pagelen, width / 2 - 140, (int) (height * 0.08), Color.white.getRGB(), 2);
            mc.fontRendererObj.drawString("Nate's Secret Mod", width / 2 - 50, (int) (height * 0.8), Color.white.getRGB(), true);
    	} else {
            this.drawString("Page " + page + "/" + pagelen, width / 2 - 195, (int) (height * 0.08), Color.white.getRGB(), 2);
            mc.fontRendererObj.drawString("Nate's Secret Mod", width / 2 - 50, (int) (height * 0.8), Color.white.getRGB(), true);
    	}

        super.drawScreen(x, y, partialTicks);
        
        
    }

    protected void actionPerformed(final GuiButton guiButton) {
        if (guiButton.enabled) {
        	
        	
        	
        	if (guiButton.id == 4) {
        		if (page == 1) {
            		if (Main.config.isModEnabled()) {
            			Main.config.setModEnabled(false);
            		} else {
            			Main.config.setModEnabled(true);
            		}
        		} else if (page == 2) {
            		if (Main.config.isUnclaimedFarmingContest()) {
            			Main.config.setUnclaimedFarmingContest(false);
            		} else {
            			Main.config.setUnclaimedFarmingContest(true);
            		}
        		} else if (page == 3) {
            		if (Main.config.isCopydungeonfail()) {
            			Main.config.setCopydungeonfail(false);
            		} else {
            			Main.config.setCopydungeonfail(true);
            		}
        		} else if (page == 4) {
            		if (Main.config.isMaddoxautophone()) {
            			Main.config.setMaddoxautophone(false);
            		} else {
            			Main.config.setMaddoxautophone(true);
            		}
        		} else if (page == 5) {
            		if (Main.config.isMusicplayer()) {
            			Main.config.setMusicplayer(false);
            		} else {
            			Main.config.setMusicplayer(true);
            		}
        		} else if (page == 6) {
            		if (Main.config.isSecrethud()) {
            			Main.config.setSecrethud(false);
            		} else {
            			Main.config.setSecrethud(true);
            		}
        		}
        	} else if (guiButton.id == 5) {
        		if (page == 1) {
            		if (Main.config.isAntiNonEnchantedEnabled()) {
            			Main.config.setAntiNonEnchantedEnabled(false);
            		} else {
            			Main.config.setAntiNonEnchantedEnabled(true);
            		}
        		} else if (page == 2) {
            		if (Main.config.isWarnPeopleForRarerItemInSecretChest()) {
            			Main.config.setWarnPeopleForRarerItemInSecretChest(false);
            		} else {
            			Main.config.setWarnPeopleForRarerItemInSecretChest(true);
            		}
        		} else if (page == 3) {
            		if (Main.config.isDungeonreparty()) {
            			Main.config.setDungeonreparty(false);
            		} else {
            			Main.config.setDungeonreparty(true);
            		}
        		} else if (page == 4) {
        			Minecraft.getMinecraft().thePlayer.closeScreen();
        			Main.INSTANCE.openDung();
        			return;
        		} else if (page == 5) {
            		if (Main.config.isMusicplayerhud()) {
            			Main.config.setMusicplayerhud(false);
            		} else {
            			Main.config.setMusicplayerhud(true);
            		}
        		}
        	} else if (guiButton.id == 50) {
    			page--;
        	} else if (guiButton.id == 51) {
        		page++;
        	} else if (guiButton.id == 99) {
				try {
					Desktop.getDesktop().browse(new URI("https://github.com/Nat3z/SkyblockMod"));
				} catch (IOException | URISyntaxException e) {
					e.printStackTrace();
				}
        	} else if (guiButton.id == 100) {
				try {
					Desktop.getDesktop().browse(new URI("https://github.com/Nat3z/SkyblockMod/releases/latest"));
				} catch (IOException | URISyntaxException e) {
					e.printStackTrace();
				}
        	} else if (guiButton.id == 69420) {
        		Minecraft.getMinecraft().thePlayer.closeScreen();
        		Main.INSTANCE.openClassicUI();
        		return;
        	} else if (guiButton.id == 789) {
        		Minecraft.getMinecraft().thePlayer.closeScreen();
        		Main.INSTANCE.MoveUI = true;
        		return;
        	}
        	
        	wino();
        }
    }
   
   

    public static int getChromaColor() {
        return Color.HSBtoRGB(System.currentTimeMillis() % 1500L / 1500.0f, 0.8f, 0.8f);
    }
    @SuppressWarnings("static-access")
	public void wino() {
		switch (page) {
			case 1:
				buttonList.remove(nextPage);
				buttonList.remove(backPage);
				buttonList.remove(nextPage);
				
				if (Main.config.isModEnabled())
					button1.textureResource = Toggon;
				else
					button1.textureResource = Toggoff;
				
				
				if (Main.config.isAntiNonEnchantedEnabled())
					button2.textureResource = Toggon;
				else
					button2.textureResource = Toggoff;
				if (!Main.config.modules.get("antinonenchanted").booleanValue())
					button2.textureResource = Toggdis;
				
				button2.displayString = " ";
				button1.displayString = " ";

			break;
	    
			case 2:
				buttonList.remove(nextPage);
				buttonList.remove(backPage);
				buttonList.remove(nextPage);
				buttonList.remove(backPage);
				
				if (Main.config.isUnclaimedFarmingContest())
					button1.textureResource = Toggon;
				else
					button1.textureResource = Toggoff;
				if (!Main.config.modules.get("unclaimedfarmingcontests").booleanValue())
					button1.textureResource = Toggdis;
				
				if (Main.config.isWarnPeopleForRarerItemInSecretChest())
					button2.textureResource = Toggon;
				else
					button2.textureResource = Toggoff;
				if (!Main.config.modules.get("warnpeopleforrareriteminsecretchest").booleanValue())
					button2.textureResource = Toggdis;
				
				button2.displayString = " ";
				button1.displayString = " ";
				
			break;
			
			case 3:
				buttonList.remove(nextPage);
				buttonList.remove(backPage);
				buttonList.remove(nextPage);
				buttonList.remove(backPage);

				if (Main.config.isCopydungeonfail())
					button1.textureResource = Toggon;
				else
					button1.textureResource = Toggoff;
				if (!Main.config.modules.get("copydungeonfail").booleanValue())
					button1.textureResource = Toggdis;

				if (Main.config.isDungeonreparty())
					button2.textureResource = Toggon;
				else
					button2.textureResource = Toggoff;
				if (!Main.config.modules.get("dungeonreparty").booleanValue())
					button2.textureResource = Toggdis;
				
				button2.displayString = " ";
				button1.displayString = " ";
				
			break;
			
			case 4:
				buttonList.remove(nextPage);
				buttonList.remove(backPage);
				buttonList.remove(nextPage);
				buttonList.remove(backPage);

				if (Main.config.isMaddoxautophone())
					button1.textureResource = Toggon;
				else
					button1.textureResource = Toggoff;
				if (!Main.config.modules.get("maddoxautophone").booleanValue())
					button1.textureResource = Toggdis;
				
				button2.textureResource = edit;
				button2.displayString = "Edit";
				
			break;
			
			case 5:
				buttonList.remove(nextPage);
				buttonList.remove(backPage);
				buttonList.remove(nextPage);
				buttonList.remove(backPage);

				if (Main.config.isMusicplayer())
					button1.textureResource = Toggon;
				else
					button1.textureResource = Toggoff;
				if (!Main.config.modules.get("musicplayer").booleanValue())
					button1.textureResource = Toggdis;
				
				if (Main.config.isMusicplayerhud())
					button2.textureResource = Toggon;
				else
					button2.textureResource = Toggoff;
				if (!Main.config.modules.get("musicplayer").booleanValue())
					button2.textureResource = Toggdis;
				
				button2.displayString = " ";
				button1.displayString = " ";
			break;
			
			case 6:
				buttonList.remove(nextPage);
				buttonList.remove(backPage);
				buttonList.remove(nextPage);
				buttonList.remove(backPage);

				if (Main.config.isSecrethud())
					button1.textureResource = Toggon;
				else
					button1.textureResource = Toggoff;
				if (!Main.config.modules.get("secrethud").booleanValue())
					button1.textureResource = Toggdis;
				
				button2.textureResource = Toggdis;
				
				button2.displayString = " ";
				button1.displayString = " ";
			break;
			
			default:
				page = pagelen;
				wino();
				return;
		}
		
		buttonList.add(nextPage);
		buttonList.add(backPage);
    }
    
    public void drawString(String text, int width, int height, int color, int scale) {
        GlStateManager.pushMatrix();
        GlStateManager.scale(scale, scale, scale);
        mc.fontRendererObj.drawString(text, width, height, color, true);
        GlStateManager.popMatrix();
    }
    
    public void showText(int order, String name, String description) {
    	
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        int width = scaledResolution.getScaledWidth();
        int height = scaledResolution.getScaledHeight();

        int xSize = Math.min(width-100/scaledResolution.getScaleFactor(), 500);
        
        int x2 = (scaledResolution.getScaledWidth() - xSize)/2;  
        
        
    	if (width <= 456) {
        	if (order == 0) {
                this.drawString(name, width / 15 - 20, (int) (height * 0.15), Color.white.getRGB(), 2);
                this.drawString(description, width / 6 - 20, (int) (height * 0.379), Color.white.getRGB(), 1);
        	} else if (order == 1) {
                this.drawString(name, width / 15 - 20, (int) (height * 0.25), Color.white.getRGB(), 2);
                this.drawString(description, width / 6 - 20, (int) (height * 0.579), Color.white.getRGB(), 1);
        	}
    	} else {
        	if (order == 0) {
                this.drawString(name, width / 15 + 10, (int) (height * 0.15), Color.white.getRGB(), 2);
                this.drawString(description, width / 6 + 10, (int) (height * 0.379), Color.white.getRGB(), 1);
        	} else if (order == 1) {
                this.drawString(name, width / 15 + 10, (int) (height * 0.25), Color.white.getRGB(), 2);
                this.drawString(description, width / 6 + 10, (int) (height * 0.579), Color.white.getRGB(), 1);
        	}
    	}

    }
    
}