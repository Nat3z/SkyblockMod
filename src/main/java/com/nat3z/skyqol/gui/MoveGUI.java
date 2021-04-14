package com.nat3z.skyqol.gui;

import java.awt.Color;
import java.io.IOException;

import com.nat3z.skyqol.Main;

import me.nat3z.gui.Button;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class MoveGUI extends GuiScreen {

    int moveXMusic = Main.guic.SongPlaylistLocationX, moveYMusic = Main.guic.SongPlaylistLocationY;
    int moveXSecrets = Main.guic.SecretsX, moveYSecrets = Main.guic.SecretsY;
    
    boolean moving = true;
    long pressTime = 0;
    
    int currentmove = -1;
    
    Button musicplayerhud;
    Button Secretshud;

    
    
    @Override
    public void initGui() {
      super.initGui();
      GlStateManager.enableBlend();
      if (Main.config.isMusicplayerhud())
    	  buttonList.add(musicplayerhud = new Button(1, moveXMusic, moveYMusic, 200, 40, new ResourceLocation("natemodskyblock", "bigo.png"), "Song Playlist"));
      if (Main.config.isSecrethud())
    	  buttonList.add(Secretshud = new Button(2, moveXSecrets, moveYSecrets, 75, 30, new ResourceLocation("natemodskyblock", "bigo.png"), "Secrets"));
      
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      drawDefaultBackground();
      if (Main.config.isMusicplayerhud()) {
          musicplayerhud.xPosition = moveXMusic;
          musicplayerhud.yPosition = moveYMusic;
      }
      if (Main.config.isSecrethud()) {
          Secretshud.xPosition = moveXSecrets;
          Secretshud.yPosition = moveYSecrets;  
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
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
      super.mouseReleased(mouseX, mouseY, state);
      if (Main.config.isMusicplayerhud()) {
          Main.guic.setSongPlaylistLocation(moveXMusic, moveYMusic);
      }
      if (Main.config.isSecrethud()) {
          Main.guic.setSecretsLocation(moveXSecrets, moveYSecrets);
      }
      
      currentmove = -1;
      
      moving = false;
    }
  }
