package me.nat3z;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringUtils;

public class Utilities extends Gui {
	
	public static void displayGUI(GuiScreen gui) {
        Minecraft.getMinecraft().displayGuiScreen(gui);
	}
	public static void sendMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_AQUA + "[" + EnumChatFormatting.AQUA + "NSM"+ EnumChatFormatting.DARK_AQUA + "]: " + EnumChatFormatting.RESET + message));
	}
	public static void sendWarning(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "[" + EnumChatFormatting.RED + "NSM"+ EnumChatFormatting.DARK_RED + "]: " + EnumChatFormatting.RED + message));
	}
	
	public static void sendTitleCentered(String message) {
		
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());

		int height = sr.getScaledHeight();
		int width = sr.getScaledWidth();
		
		Minecraft.getMinecraft().fontRendererObj.drawString(message, (int) (width / 2.1), (int) (height *0.450), Color.WHITE.getRGB(), true);
		
		
	}
	
	
	/*
	 * Code Below Is From Danker's Skyblock Mod
	 */
	
	public static void showOnSlot(int size, int xSlotPos, int ySlotPos) {
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		int guiLeft = (sr.getScaledWidth() - 176) / 2;
		int guiTop = (sr.getScaledHeight() - 222) / 2;
		
		int x = guiLeft + xSlotPos;
		int y = guiTop + ySlotPos;
		
		if (size != 90) y+= (6 - (size - 36) / 9) * 9;
		
		GL11.glTranslated(0, 0, 1);
		Gui.drawRect(x, y, x + 16, y + 16, Color.green.getRGB());
		GL11.glTranslated(0, 0, -1);

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
	
}