package com.nat3z.skyqol.gui;

import java.awt.Color;
import java.io.IOException;

import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.config.Config;
import com.nat3z.skyqol.config.Feature;
import com.nat3z.skyqol.utils.gui.Button;
import com.nat3z.skyqol.utils.gui.MoveabbleButtons;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class MoveGUI extends GuiScreen {

    int moveXMusic = Feature.MusicHUD[0], moveYMusic = Feature.MusicHUD[1];
    int moveXSecrets = Feature.CopyFailsHUD[0], moveYSecrets = Feature.CopyFailsHUD[1];
    
    int moveXProfit = Feature.ProfitHUD[0], moveYProfit = Feature.ProfitHUD[1];

    boolean moving = false;
    long pressTime = 0;
    
    int currentmove = -1;
    
    MoveabbleButtons musicplayerhud;
    MoveabbleButtons Secretshud;
    MoveabbleButtons ProfitHud;

    
    
    @Override
    public void initGui() {
      super.initGui();
      GlStateManager.enableBlend();
      if (Feature.MusicHud)
    	  buttonList.add(musicplayerhud = new MoveabbleButtons(1, moveXMusic, moveYMusic, 200, 40, "Song Playlist", moving));
      if (Feature.SecretHud)
    	  buttonList.add(Secretshud = new MoveabbleButtons(2, moveXSecrets, moveYSecrets, 75, 30, "Secrets", moving));
      
      if (Feature.ProfitHud)
    	  buttonList.add(ProfitHud = new MoveabbleButtons(3, moveXProfit, moveYProfit, 200, 100, "Dungeons Profit Hud", moving));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      drawDefaultBackground();
      if (Feature.MusicHud) {
          musicplayerhud.xPosition = moveXMusic;
          musicplayerhud.yPosition = moveYMusic;
      }
      if (Feature.SecretHud) {
          Secretshud.xPosition = moveXSecrets;
          Secretshud.yPosition = moveYSecrets;  
      }
      
      if (Feature.ProfitHud) {
    	  ProfitHud.xPosition = moveXProfit;
    	  ProfitHud.yPosition = moveYProfit;
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
      return false;
    }

    @Override
    protected void actionPerformed(GuiButton guiButton) throws IOException {
      if (guiButton.id == 1) {
        moving = true;
        currentmove = 1;
        if (pressTime == 0) {
          pressTime = System.currentTimeMillis();
        } else {
          pressTime = 0;
        }
      } else if (guiButton.id == 2) {
          moving = true;
          currentmove = 2;
          if (pressTime == 0) {
            pressTime = System.currentTimeMillis();
          } else {
            pressTime = 0;
          }
        } else if (guiButton.id == 3) {
            moving = true;
            currentmove = 3;
            if (pressTime == 0) {
              pressTime = System.currentTimeMillis();
            } else {
              pressTime = 0;
            }
        }
      super.actionPerformed(guiButton);
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton,
        long timeSinceLastClick) {
      super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
      
      if (moving && currentmove == 1) {
    	  	moveXMusic = mouseX;
        	moveYMusic = mouseY;
      } else if (moving && currentmove == 2) {
    	  	moveXSecrets = mouseX;
    	  	moveYSecrets = mouseY;
      } else if (moving && currentmove == 3) {
  	  	moveXProfit = mouseX;
  	  	moveYProfit = mouseY;
      }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
      super.mouseReleased(mouseX, mouseY, state);
      if (Feature.MusicHud) {
    	  Feature.MusicHUD[0] = moveXMusic;
    	  Feature.MusicHUD[1] = moveYMusic;
    	  
          Config.writeIntConfig("locations", "MusicHudX", Feature.MusicHUD[0]);
          Config.writeIntConfig("locations", "MusicHudY", Feature.MusicHUD[1]);
      }
      if (Feature.SecretHud) {
    	  Feature.CopyFailsHUD[0] = moveXSecrets;
    	  Feature.CopyFailsHUD[1] = moveYSecrets;
    	  
          Config.writeIntConfig("locations", "CopyFailsX", Feature.CopyFailsHUD[0]);
          Config.writeIntConfig("locations", "CopyFailsY", Feature.CopyFailsHUD[1]);
      }
      
      if (Feature.ProfitHud) {
    	  Feature.ProfitHUD[0] = moveXProfit;
    	  Feature.ProfitHUD[1] = moveYProfit;
    	  
          Config.writeIntConfig("locations", "ProfitHudX", Feature.ProfitHUD[0]);
          Config.writeIntConfig("locations", "ProfitHudY", Feature.ProfitHUD[1]);
      }
      
      currentmove = -1;
      
      moving = false;
    }
  }
