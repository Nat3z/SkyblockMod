package com.nat3z.skyqol.features;

import java.awt.Color;

import javax.vecmath.Vector3f;

import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.config.Config;
import com.nat3z.skyqol.config.Feature;
import com.nat3z.skyqol.utils.Message;
import com.nat3z.skyqol.utils.Utilities;

import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PuzzlerSolver {
	Vector3f coords = null;
	// ◀ ▲ ▼ ▶
	@SubscribeEvent
	public void solverGet(ClientChatReceivedEvent event) {
		if (!Feature.PuzzlerSolver || !Config.modules.get("puzzler")) return;
		
		String message = StringUtils.stripControlCodes(event.message.getUnformattedText());
		if (message.toLowerCase().contains("puzzler")) {
			String[] split = message.split(" ");
			if (split[0].toLowerCase().contains("npc") && split[1].toLowerCase().contains("puzzler:") && !split[2].contains("Nice!") && !split[2].contains("Come") && split.length > 2) {
				String[] withoutNPC = message.replace("[NPC] Puzzler: ", "").split("|");
				
				int x = 181;
				int y = 195;
				int z = 135;
				for (String string : withoutNPC) {
					
					if (string.equals("◀")) {
						x += 1;
					} else if (string.equals("▶")) {
						x -= 1;
					} else if (string.equals("▲")) {
						z += 1;
					} else if (string.equals("▼")) {
						z -= 1;
					}
				}
				
				Message.sendMessage(EnumChatFormatting.GREEN + "" + EnumChatFormatting.BOLD + "Now highlighting Puzzler's block.");
				
				coords = new Vector3f(x, y, z);
			} else if (split[0].toLowerCase().contains("npc") && split[1].toLowerCase().contains("puzzler:")) {
				coords = null;
			}
		}
	}
	
	@SubscribeEvent
	public void renderHighlight(DrawBlockHighlightEvent event) {
		if (!Feature.PuzzlerSolver || !Config.modules.get("puzzler")) return;
		if (coords != null && Main.isInDwarven()) {
			Utilities.HighlightBlock(coords, 0.5f, event.partialTicks, new Color(176, 23, 23));
		}
	}
	
}
