package com.nat3z.skyqol.utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.vecmath.Vector3f;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class Utilities extends Gui {
	
	public static void displayGUI(GuiScreen gui) {
        Minecraft.getMinecraft().displayGuiScreen(gui);
	}
	public static void sendMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "[NSM] " + EnumChatFormatting.GREEN + message));
	}
	public static void sendWarning(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "[NSM] " + EnumChatFormatting.RED + message));
	}
	
	public static void sendTitleCentered(String message, int color) {
		
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

		int height = sr.getScaledHeight();
		int width = sr.getScaledWidth();
		int wait = 0;
		while (wait < 5000) {
			wait++;
			Minecraft.getMinecraft().fontRendererObj.drawString(message, (int) (width / 2.1), (int) (height *0.450), color, true);
		}		
	}
	
	public static void drawCenteredString(String text, float x, float y, int color) {
		Minecraft.getMinecraft().fontRendererObj.drawString(text, x - Minecraft.getMinecraft().fontRendererObj.getStringWidth(text) / 2F, y / 2F, color, true);
	}
	
	public static void HighlightBlock(Vector3f coords, float alpha, float partialTicks, Color c) {
		EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
	    double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)partialTicks;
	    double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)partialTicks;
	    double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)partialTicks;
	       
 	   	GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
 	   	GlStateManager.color(0.0F, 0.0F, 0.0F, 1F);
 	   	GlStateManager.enableBlend();
        GlStateManager.disableDepth();
 	   	GlStateManager.depthMask(false);
 	       
 	   	RenderUtil.drawFilledBoundingBox(Blocks.stone.getSelectedBoundingBox(Minecraft.getMinecraft().theWorld, new BlockPos(coords.x, coords.y, coords.z)).offset(-d0, -d1, -d2), 0.5f, new Color(0, 191, 163));
 	   	GlStateManager.depthMask(true);
 	   	GlStateManager.disableBlend();
        GlStateManager.enableDepth();
	}
	
	/*
	 * Code Below Is From Danker's Skyblock Mod
	 * @author Dankers
	 */
	
	public static void showOnSlot(int size, int xSlotPos, int ySlotPos, int color) {
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		int guiLeft = (sr.getScaledWidth() - 176) / 2;
		int guiTop = (sr.getScaledHeight() - 222) / 2;
		
		int x = guiLeft + xSlotPos;
		int y = guiTop + ySlotPos;
		
		if (size != 90) y+= (6 - (size - 36) / 9) * 9;
		
		
		GL11.glTranslated(0, 0, 1);
		Gui.drawRect(x, y, x + 16, y + 16, color);
		GL11.glTranslated(0, 0, -1);

	}

	/**
	 * Code Below is From Our Friends At Skytils!
	 * @author Sychic
	 * @param partialTicks
	 * @param blockPos
	 */
    public static void drawWaypoint(float partialTicks, Vector3f loc, String waypointText) {
        Entity viewer = Minecraft.getMinecraft().getRenderViewEntity();
        double viewerX = viewer.lastTickPosX + (viewer.posX - viewer.lastTickPosX) * partialTicks;
        double viewerY = viewer.lastTickPosY + (viewer.posY - viewer.lastTickPosY) * partialTicks;
        double viewerZ = viewer.lastTickPosZ + (viewer.posZ - viewer.lastTickPosZ) * partialTicks;

        BlockPos pos = new BlockPos(loc.x, loc.y, loc.z);
        double x = pos.getX() - viewerX;
        double y = pos.getY() - viewerY;
        double z = pos.getZ() - viewerZ;
        double distSq = x*x + y*y + z*z;

        GlStateManager.disableDepth();
        GlStateManager.disableCull();
        GlStateManager.disableTexture2D();
        if (distSq > 5*5) RenderUtil.renderBeaconBeam(x, y + 1, z, Color.green.getRGB(), 1.0f, partialTicks);
        RenderUtil.renderWaypointText(waypointText, pos, partialTicks);
        GlStateManager.disableLighting();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.enableCull();
        
    }
    
    
    /**
     * Inspired By Danker's Skyblock Mod
     * @author Dankers
     * @param size
     * @param xSlotPos
     * @param ySlotPos
     * @param resource
     */
	public static void displayImageOnSlot(int size, int xSlotPos, int ySlotPos, ResourceLocation resource) {
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		int guiLeft = (sr.getScaledWidth() - 176) / 2;
		int guiTop = (sr.getScaledHeight() - 222) / 2;
		int x = guiLeft + xSlotPos + 60;
		int y = guiTop + ySlotPos;
		// Move down when chest isn't 6 rows
		if (size != 90) y += (6 - (size - 10) / 9) * 9;
		
        Minecraft.getMinecraft().getTextureManager().bindTexture(resource);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0, 0, 16, 16, 16, 16);

	}
	
	/**
	 * Inspired from Danker's Skyblock Mod
	 * @param size
	 * @param xSlotPos
	 * @param ySlotPos
	 * @param num
	 */
	public static void displayStringOnSlot(int size, int xSlotPos, int ySlotPos, String num) {
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		int guiLeft = (sr.getScaledWidth() - 176) / 2;
		int guiTop = (sr.getScaledHeight() - 222) / 2;
		int x = guiLeft + xSlotPos + 60;
		int y = guiTop + ySlotPos;
		
		if (size != 90) y+= (6 - (size - 36) / 9) * 9;
		
		GL11.glTranslated(0, 0, 1);
		Minecraft.getMinecraft().fontRendererObj.drawString(num, x - 55 , y + 4, new Color(255, 255, 255, 200).getRGB(), true);
		GL11.glTranslated(0, 0, -1);
	}
	
    public static void playSound(String sound, double pitch) {
        Minecraft.getMinecraft().thePlayer.playSound(sound, 1, (float) pitch);
    }
	
	public static List<String> getScoreboardLines() {
		List<String> lines = new ArrayList<>();
		Scoreboard scoreboard = Minecraft.getMinecraft().theWorld.getScoreboard();
		if (scoreboard == null)
			return lines;
		
		ScoreObjective objective = scoreboard.getObjectiveInDisplaySlot(1);
		if (objective == null)
			return lines;
		
		Collection<Score> scores = scoreboard.getSortedScores(objective);
		
		List<Score> list = scores.stream()
				.filter(input -> input != null && input.getPlayerName() != null && !input.getPlayerName()
				.startsWith("#"))
				.collect(Collectors.toList());
		
		if (list.size() > 15)
			scores = Lists.newArrayList(Iterables.skip(list, scores.size() - 15));
		else
			scores = list;
		
		for (Score score : scores) {
			ScorePlayerTeam team = scoreboard.getPlayersTeam(score.getPlayerName());
			lines.add(ScorePlayerTeam.formatPlayerName(team, score.getPlayerName()));
		}
		
		return lines;
	}
	public static String cleanSB(String scoreboard) {
		char[] nvString = StringUtils.stripControlCodes(scoreboard).toCharArray();
		StringBuilder cleaned = new StringBuilder();
		
		for (char c : nvString) {
			if ((int) c > 20 && (int) c < 127) 
				cleaned.append(c);
		}
		
		return cleaned.toString();
	}
	
	/**
	 * From {@link https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/modification-development/2708552-getting-the-text-from-the-players-actionbar}
	 */
	public static String getCurrentActionBar() {
		try {
			String actionBar = (String) ReflectionHelper.findField(GuiIngame.class, "displayedActionBar", "recordPlaying").get(Minecraft.getMinecraft().ingameGUI);
			return actionBar;
		} catch (Exception e) {
		}
		return null;
	}
	
	public static String setCurrentActionBar(String newText) {
		try {
			String pasttext = (String) ReflectionHelper.findField(GuiIngame.class, "displayedActionBar", "recordPlaying").get(Minecraft.getMinecraft().ingameGUI);
			ReflectionHelper.findField(GuiIngame.class, "displayedActionBar", "recordPlaying").set(Minecraft.getMinecraft().ingameGUI, newText);
			return pasttext;
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * Code From Danker's Skyblock Mod!
	 * @author Danker's
	 * <a>https://github.com/bowser0000/SkyblockMod/blob/14efcd8b81763d1c06a4291eeb6a0f1aaeb770fe/src/main/java/me/Danker/utils/Utils.java</a>
	 */
	public static void renderItem(ItemStack item, float xSlotPos, float ySlotPos, float z) {

		GlStateManager.enableRescaleNormal();
		RenderHelper.enableGUIStandardItemLighting();
		GlStateManager.enableDepth();

		GlStateManager.pushMatrix();
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		int guiLeft = (sr.getScaledWidth() - 176) / 2;
		int guiTop = (sr.getScaledHeight() - 222) / 2;
		float x = guiLeft + xSlotPos + 60;
		float y = guiTop + ySlotPos;

		GlStateManager.translate(x - 61, y, z);
		
		GL11.glTranslated(0, 0, 1);
		Minecraft.getMinecraft().getRenderItem().renderItemIntoGUI(item, 0, 0);
		GL11.glTranslated(0, 0, -1);
		
		GlStateManager.popMatrix();

		GlStateManager.disableDepth();
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableRescaleNormal();
	}
	
}