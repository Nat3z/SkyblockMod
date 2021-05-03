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

import org.apache.commons.lang3.time.StopWatch;

import com.nat3z.skyqol.CheckForUpdates;
import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.config.Config;
import com.nat3z.skyqol.config.Feature;
import com.nat3z.skyqol.utils.Scheduler;
import com.nat3z.skyqol.utils.Utilities;
import com.nat3z.skyqol.utils.gui.Button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GUI extends GuiScreen {
	public static boolean SkipGuiAnim;

	private GuiButton backPage;
	private GuiButton nextPage;
	private Button button2;
	private Button button3;
	private Button EditGuiLocation;
	static StopWatch wait = new StopWatch();

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
    
    public GUI (int page) {
    	this.page = page;
    }
    
    
    
	@Override
    public void initGui() {
		wait.reset();
		
		wait.start();
		if (SkipGuiAnim) {
			doTheShit();
		} else {
			Scheduler.runTask(() -> {
				doTheShit();
			}, 900);
		}
    }
	
	private void doTheShit() {
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		int height = sr.getScaledHeight();
		int width = sr.getScaledWidth();

        int xSize = Math.min(width-100/sr.getScaleFactor(), 500);

        int x2 = (sr.getScaledWidth() - xSize)/2;
		
    	if (width <= 455) {
			noWait = true;
    		return;
    	}
        
		System.out.println("nsm: " + width);
		
    	super.initGui();
    	// Always Present
    	
    	if (width <= 456) {
        	button1 = new Button(4, x2 / 1 * 25, (int) (height * 0.3), 50, 20, Toggoff, "   ");
        	button2 = new Button(5, x2 / 1 * 25, (int) (height * 0.52), 50, 20, Toggoff, "   ");
        	button3 = new Button(5, x2 / 1 * 25, (int) (height * 0.74), 50, 20, Toggoff, "   ");
    	} else {
        	button1 = new Button(4, (int)(width / 2 * 1.6), (int) (height * 0.3), 50, 20, Toggoff, "   ");
        	button2 = new Button(5, (int)(width / 2 * 1.6), (int) (height * 0.52), 50, 20, Toggoff, "   ");
        	button3 = new Button(6, (int)(width / 2 * 1.6), (int) (height * 0.74), 50, 20, Toggoff, "   ");
    	}
    	
		backPage = new Button(50, width / 2 - 150, (int) (height * 0.1), 50, 50, back, "   ");
		nextPage = new Button(51, width / 2 + 100, (int) (height * 0.1), 50, 50, next, "   ");


		github = new Button(99, 0, 0, 50, 50, new ResourceLocation("natemodskyblock", "icons/github.png"), "   ");
		EditGuiLocation = new Button(789, width - 200, 0, 200, 30, new ResourceLocation("natemodskyblock", "bigo.png"), "Edit Gui Locations");
		 
		buttonList.add(button1);
        buttonList.add(button2);
        buttonList.add(button3);
        buttonList.add(EditGuiLocation);
		buttonList.add(nextPage);
		buttonList.add(backPage);

        buttonList.add(github);
		
		if (CheckForUpdates.updateversion) {
    		updatetoversion = new Button(100, width / 2 - 100, 0, 200, 30, Menu, "Update Nate's Secret Mod");
    		buttonList.add(updatetoversion);
		}
		noWait = true;
        wino();
	}
	
	private int pagelen = 7;
	boolean noWait = false;
    @SuppressWarnings("static-access")
	@Override
    public void drawScreen(final int x, final int y, final float partialTicks) {
    	super.drawDefaultBackground();
    	if (noWait) {
    		if (wait.isStarted())
    			wait.stop();
    		
            // dark background
            
            GlStateManager.enableBlend();
            
            ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
            int width = scaledResolution.getScaledWidth();
            int height = scaledResolution.getScaledHeight();

            int xSize = Math.min(width-100/scaledResolution.getScaleFactor(), 500);
            int ySize = Math.min(height-100/scaledResolution.getScaleFactor(), 500);

            int x2 = (scaledResolution.getScaledWidth() - xSize)/2;
            int y2 = (scaledResolution.getScaledWidth() - ySize)/2;

            
            if (width <= 455) {
        		drawCenteredString(fontRendererObj, "Please Resize Your Gui.", width / 2, (int)(height * 0.5), this.getChromaColor());
            	
            	return;
            }
            
            Minecraft.getMinecraft().getTextureManager().bindTexture(Menu);
            Gui.drawModalRectWithCustomSizedTexture(x2 / 2, (int) (height * 0.25), 0, 0, (int) (width / 2) + 250,  (int) (height / 1.5), 356, 267);
            
            if (page == 1) {
            	showText(0, "Skip Gui Animation", "Skip the Gui Animation.");
            	showText(1, "Disable Clicking Unenchanted Items", "Prevents clicking unenchanted items in minion containers.");
            	showText(2, "Highlight Unclaimed Farming Contests", "Highlights all unclaimed farming contests.");
            } else if (page == 2) {
            	showText(0, "3 Weirdos Solver", "Solves the 3 Weirdos puzzle.");
            	showText(1, "Copy Dungeon Deaths & Fails", "Copies all dungeon deaths and fails.");
            	showText(2, "Reparty Dungeon Teammates", "Reparties all dungeon teammates in party.");

            } else if (page == 3) {
            	showText(0, "Dungeon Kicker", "Kick users that join who do not fit sufficient requirements.");
            	showText(1, "Click Anywhere to Open Maddox", "When the Maddox Batphone dialogue appears, you can click anywhere to open.");
            	showText(2, "Enable Music Player", "Activate the Music Player and read files in nsm_playlist.");
            } else if (page == 4) {
            	showText(0, "Enable Music Player HUD", "Activates a HUD that shows you the current song playing.");
            	showText(1, "Dungeon Secrets HUD", "Adds a HUD to show the amount of Dungeon Secrets you have in a room.");
            	showText(2, "Ultrasequencer Solver", "Guides you in the Ultrasequencer experiment.");
            } else if (page == 5) {
            	showText(0, "Chronomatron Solver", "Guides you in the Chronomatron experiment.");
            	showText(1, "Superpairs Solver", "Guides you in the Superpairs experiment.");
            	showText(2, "Bonemerang Warning", "Notifies you if the held Bonemerang is not ready.");
            } else if (page == 6) {
            	showText(0, "Puzzler Solver", "Solves the Puzzler puzzle in the Dwarven Mines.");
            	showText(1, "Griffin Burrow Waypoints", "Highlights all burrows using waypoints.");
            	showText(2, "Dungeon Profit Checker", "Gets the profit of your dungeon chest.");
            } else if (page == 7) {
            	showText(0, "Dungeons Profit HUD", "Shows a Hud on screen for each dungeon chest profit.");
            }
            
    		String pageText = "Page: " + page + "/" + pagelen;

    		int pageWidth = mc.fontRendererObj.getStringWidth(pageText);

            
    		this.drawString(mc.fontRendererObj, pageText, width / 2 - pageWidth / 2, 10, Color.white.getRGB());
    	} else {
    		drawCenteredString(fontRendererObj, "Nate's Secret Mod", width / 2, (int)(height * 0.5), this.getChromaColor());
    		drawCenteredString(fontRendererObj, "By Nat3z", width / 2 + 2, (int)(height * 0.54), this.getChromaColor());
    	}
        super.drawScreen(x, y, partialTicks);
        
        
    }

	protected void actionPerformed(final GuiButton butt) {
		Button guiButton = (Button) butt;
		
        if (guiButton.enabled) {
        	new Thread(() -> {
            	if (guiButton.textureResource == Toggdis) {
            		Utilities.sendWarning("This module has been disabled remotely through the data repository.");
            		return;
            	}
            	
            	
            	if (guiButton.id == 4) {
            		if (page == 1) {
            			if (this.SkipGuiAnim) {
            				this.SkipGuiAnim = false;
            			} else {
            				this.SkipGuiAnim = true;
            			}
            		}
            		else if (page == 2) {
            			if (Feature.weirdossolver) {
            				Feature.weirdossolver = false;
            			} else {
            				Feature.weirdossolver = true;
            			}
            		}
            		
            		else if (page == 3) {
            			Main.INSTANCE.openDung();
            		}
            		
            		else if (page == 4) {
            			if (Feature.MusicHud) {
            				Feature.MusicHud = false;
            			} else {
            				Feature.MusicHud = true;
            			}
            		}
            		
            		else if (page == 5) {
            			if (Feature.ExperimentChronomatron) {
            				Feature.ExperimentChronomatron = false;
            			} else {
            				Feature.ExperimentChronomatron = true;
            			}
            		} else if (page == 6) {
            			if (Feature.PuzzlerSolver) {
            				Feature.PuzzlerSolver = false;
            			} else {
            				Feature.PuzzlerSolver = true;
            			}
            		} else if (page == 7) {
            			if (Feature.ProfitHud) {
            				Feature.ProfitHud = false;
            			} else {
            				Feature.ProfitHud = true;
            			}
            		}
            	}
            	
            	
            	// 2nd toggle
            	
            	else if (guiButton.id == 5) {
            		if (page == 1) {
            			if (Feature.AntiNonEnchanted) {
            				Feature.AntiNonEnchanted = false;
            			} else {
            				Feature.AntiNonEnchanted = true;
            			}
            		}
            		
            		else if (page == 2) {
            			if (Feature.CopyFails) {
            				Feature.CopyFails = false;
            			} else {
            				Feature.CopyFails = true;
            			}
            		}
            		
            		
            		else if (page == 3) {
            			if (Feature.AnywhereSlayer) {
            				Feature.AnywhereSlayer = false;
            			} else {
            				Feature.AnywhereSlayer = true;
            			}
            		}
            		
            		else if (page == 4) {
            			if (Feature.SecretHud) {
            				Feature.SecretHud = false;
            			} else {
            				Feature.SecretHud = true;
            			}
            		}
            		
            		else if (page == 5) {
            			if (Feature.ExperimentSuperpairs) {
            				Feature.ExperimentSuperpairs = false;
            			} else {
            				Feature.ExperimentSuperpairs = true;
            			}
            		} else if (page == 6) {
            			if (Feature.BurrowWaypoint) {
            				Feature.BurrowWaypoint = false;
            			} else {
            				Feature.BurrowWaypoint = true;
            			}
            		}
            	}
            	
            	
            	// 3rd button
            	else if (guiButton.id == 6) {
            		if (page == 1) {
            			if (Feature.FarmingContests) {
            				Feature.FarmingContests = false;
            			} else {
            				Feature.FarmingContests = true;
            			}
            		}
            		
            		else if (page == 2) {
            			if (Feature.DungeonReparty) {
            				Feature.DungeonReparty = false;
            			} else {
            				Feature.DungeonReparty = true;
            			}
            		}
            		
            		else if (page == 3) {
            			if (Feature.MusicPlayer) {
            				Feature.MusicPlayer = false;
            			} else {
            				Feature.MusicPlayer = true;
            			}
            		}
            		
            		else if (page == 4) {
            			if (Feature.ExperimentUltrasequencer) {
            				Feature.ExperimentUltrasequencer = false;
            			} else {
            				Feature.ExperimentUltrasequencer = true;
            			}
            		}
            		
            		else if (page == 5) {
            			if (Feature.BonemerangWarn) {
            				Feature.BonemerangWarn = false;
            			} else {
            				Feature.BonemerangWarn = true;
            			}
            		}
            		
            		else if (page == 6) {
            			if (Feature.DungeonProfits) {
            				Feature.DungeonProfits = false;
            			} else {
            				Feature.DungeonProfits = true;
            			}
            		}         		
            		
            	}
            	
            	
            	
            	
            	else if (guiButton.id == 50) {
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
            	} else if (guiButton.id == 789) {
            		Minecraft.getMinecraft().thePlayer.closeScreen();
            		Main.INSTANCE.MoveUI = true;
            		return;
            	}
            	
            	wino();
        	}).start();
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
				
			
				
				if (this.SkipGuiAnim)
					button1.textureResource = Toggon;
				else
					button1.textureResource = Toggoff;
				
				
        		if (Feature.AntiNonEnchanted)
					button2.textureResource = Toggon;
				else
					button2.textureResource = Toggoff;
				if (!Main.config.modules.get("antinonenchanted").booleanValue())
					button2.textureResource = Toggdis;
				


        		if (Feature.FarmingContests)
					button3.textureResource = Toggon;
				else
					button3.textureResource = Toggoff;
				if (!Main.config.modules.get("unclaimedfarmingcontests").booleanValue())
					button3.textureResource = Toggdis;
				
				button3.displayString = " ";
				button2.displayString = " ";
				button1.displayString = " ";

			break;
	    
			case 2:
				buttonList.remove(nextPage);
				buttonList.remove(backPage);
				buttonList.remove(nextPage);
				buttonList.remove(backPage);
				
        		if (Feature.weirdossolver)
					button1.textureResource = Toggon;
				else
					button1.textureResource = Toggoff;
				if (!Main.config.modules.get("weirdossolver").booleanValue())
					button1.textureResource = Toggdis;
				
        		if (Feature.CopyFails)
					button2.textureResource = Toggon;
				else
					button2.textureResource = Toggoff;
				if (!Main.config.modules.get("copydungeonfail").booleanValue())
					button2.textureResource = Toggdis;
				
        		if (Feature.DungeonReparty)
					button3.textureResource = Toggon;
				else
					button3.textureResource = Toggoff;
				if (!Main.config.modules.get("dungeonreparty").booleanValue())
					button3.textureResource = Toggdis;
				
				button3.displayString = " ";
				button2.displayString = " ";
				button1.displayString = " ";
				
			break;
			
			case 3:
				buttonList.remove(nextPage);
				buttonList.remove(backPage);
				buttonList.remove(nextPage);
				buttonList.remove(backPage);

				button1.textureResource = this.edit;
				if (!Main.config.modules.get("dungeonkicker"))
					button1.textureResource = this.Toggdis;
				
        		if (Feature.AnywhereSlayer)
					button2.textureResource = Toggon;
				else
					button2.textureResource = Toggoff;
				if (!Main.config.modules.get("maddoxautophone").booleanValue())
					button2.textureResource = Toggdis;
				


        		if (Feature.MusicPlayer)
					button3.textureResource = Toggon;
				else
					button3.textureResource = Toggoff;
				
				button3.displayString = " ";
				button2.displayString = " ";
				button1.displayString = " ";
				
			break;
			
			case 4:
				buttonList.remove(nextPage);
				buttonList.remove(backPage);
				buttonList.remove(nextPage);
				buttonList.remove(backPage);

        		if (Feature.MusicHud)
					button1.textureResource = Toggon;
				else
					button1.textureResource = Toggoff;
				


        		if (Feature.SecretHud)
					button2.textureResource = Toggon;
				else
					button2.textureResource = Toggoff;
				if (!Main.config.modules.get("secrethud").booleanValue())
					button2.textureResource = Toggdis;
				
				
				
        		if (Feature.ExperimentUltrasequencer)
					button3.textureResource = Toggon;
				else
					button3.textureResource = Toggoff;
				if (!Main.config.modules.get("ultraseqsolver").booleanValue())
					button3.textureResource = Toggdis;
				
				button3.displayString = " ";
				button2.displayString = " ";
				button1.displayString = " ";
								
			break;
			
			case 5:
				buttonList.remove(nextPage);
				buttonList.remove(backPage);
				buttonList.remove(nextPage);
				buttonList.remove(backPage);

        		if (Feature.ExperimentChronomatron)
					button1.textureResource = Toggon;
				else
					button1.textureResource = Toggoff;
				if (!Main.config.modules.get("chronomsolver").booleanValue())
					button1.textureResource = Toggdis;
				
				
				
        		if (Feature.ExperimentSuperpairs)
					button2.textureResource = Toggon;
				else
					button2.textureResource = Toggoff;
				if (!Main.config.modules.get("superpairssolver").booleanValue())
					button2.textureResource = Toggdis;
				
				
				
				
				
        		if (Feature.BonemerangWarn)
					button3.textureResource = Toggon;
				else
					button3.textureResource = Toggoff;
				if (!Main.config.modules.get("bonemerangwarn").booleanValue())
					button3.textureResource = Toggdis;
				
				button3.displayString = " ";
				button2.displayString = " ";
				button1.displayString = " ";
			break;
			
			case 6:
				buttonList.remove(nextPage);
				buttonList.remove(backPage);
				buttonList.remove(nextPage);
				buttonList.remove(backPage);

        		if (Feature.PuzzlerSolver)
					button1.textureResource = Toggon;
				else
					button1.textureResource = Toggoff;
				if (!Main.config.modules.get("puzzler").booleanValue())
					button1.textureResource = Toggdis;
				
        		if (Feature.BurrowWaypoint)
					button2.textureResource = Toggon;
				else
					button2.textureResource = Toggoff;
				if (!Main.config.modules.get("burrowpoints").booleanValue())
					button2.textureResource = Toggdis;	
				
				
        		if (Feature.DungeonProfits)
					button3.textureResource = Toggon;
				else
					button3.textureResource = Toggoff;
				if (!Main.config.modules.get("profit").booleanValue())
					button3.textureResource = Toggdis;					
				button3.displayString = " ";
				button2.displayString = " ";
				button1.displayString = " ";
			break;
			
			case 7:
				buttonList.remove(nextPage);
				buttonList.remove(backPage);
				buttonList.remove(nextPage);
				buttonList.remove(backPage);

        		if (Feature.ProfitHud)
					button1.textureResource = Toggon;
				else
					button1.textureResource = Toggoff;
				if (!Main.config.modules.get("profit").booleanValue())
					button1.textureResource = Toggdis;
				
				button2.textureResource = Toggdis;
				button3.textureResource = Toggdis;
				
				button3.displayString = " ";
				button2.displayString = " ";
				button1.displayString = " ";
			break;
			
			default:
				if (page == (pagelen + 1)) {
					page = 1;
				} else {
					page = pagelen;
				}
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
        
    	if (width <= 456) {
        	if (order == 0) {
                this.drawString(name, width / 15 - 20, (int) (height * 0.15), Color.white.getRGB(), 2);
                this.drawString(description, width / 6 - 20, (int) (height * 0.379), Color.white.getRGB(), 1);
        	} else if (order == 1) {
                this.drawString(name, width / 15 - 20, (int) (height * 0.25), Color.white.getRGB(), 2);
                this.drawString(description, width / 6 - 20, (int) (height * 0.579), Color.white.getRGB(), 1);
        	} else if (order == 2) {
                this.drawString(name, width / 15 - 20, (int) (height * 0.35), Color.white.getRGB(), 2);
                this.drawString(description, width / 6 - 20, (int) (height * 0.779), Color.white.getRGB(), 1);
        	}
    	} else {
        	if (order == 0) {
                this.drawString(name, width / 15 + 10, (int) (height * 0.15), Color.white.getRGB(), 2);
                this.drawString(description, width / 6 + 10, (int) (height * 0.379), Color.white.getRGB(), 1);
        	} else if (order == 1) {
                this.drawString(name, width / 15 + 10, (int) (height * 0.25), Color.white.getRGB(), 2);
                this.drawString(description, width / 6 + 10, (int) (height * 0.579), Color.white.getRGB(), 1);
        	} else if (order == 2) {
                this.drawString(name, width / 15 + 10, (int) (height * 0.35), Color.white.getRGB(), 2);
                this.drawString(description, width / 6 + 10, (int) (height * 0.779), Color.white.getRGB(), 1);
        	}
    	}

    }
    
    @Override
    public void onGuiClosed() {
    	noWait = false;
		Config.writeBooleanConfig("feature", "SuperpairsSolver", Feature.ExperimentSuperpairs);
		Config.writeBooleanConfig("feature", "ChronoSolver", Feature.ExperimentChronomatron);
		Config.writeBooleanConfig("feature", "ExperimentSequencer", Feature.ExperimentUltrasequencer);

		//
		Config.writeBooleanConfig("feature", "BurrowWaypoints", Feature.BurrowWaypoint);
		Config.writeBooleanConfig("feature", "AnywhereSlayer", Feature.AnywhereSlayer);
		Config.writeBooleanConfig("feature", "CopyFails", Feature.CopyFails);

		//
		Config.writeBooleanConfig("feature", "AntiNonEnchanted", Feature.AntiNonEnchanted);
		Config.writeBooleanConfig("feature", "FarmingContests", Feature.FarmingContests);
		Config.writeBooleanConfig("feature", "SecretHud", Feature.SecretHud);
		Config.writeBooleanConfig("feature", "DungeonReparty", Feature.DungeonReparty);
		Config.writeBooleanConfig("feature", "PuzzlerSolver", Feature.PuzzlerSolver);
		Config.writeBooleanConfig("feature", "profits", Feature.DungeonProfits);

		//
		
		Config.writeBooleanConfig("feature", "WierdosSolver", Feature.weirdossolver);
		Config.writeBooleanConfig("feature", "BonemerangWarn", Feature.BonemerangWarn);
		Config.writeBooleanConfig("music", "MusicHud", Feature.MusicHud);
		Config.writeBooleanConfig("music", "MusicPlayer", Feature.MusicPlayer);
		
		Config.writeBooleanConfig("persistent", "SKIPGUI", this.SkipGuiAnim);
		Config.writeBooleanConfig("feature", "ProfitHud", Feature.ProfitHud);

    }
    
}