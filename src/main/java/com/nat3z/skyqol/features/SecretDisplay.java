
package com.nat3z.skyqol.features;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.config.Feature;
import com.nat3z.skyqol.utils.Message;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

public class SecretDisplay {

    public static final ResourceLocation Menu = new ResourceLocation("natemodskyblock", "bigo.png");
	String secretsoutof = "No";
	@SuppressWarnings("static-access")
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void secdisp(ClientChatReceivedEvent event) {
		if (!Feature.SecretHud) return;
		if (!Main.config.modules.get("secrethud").booleanValue()) return;
		if (!Main.isHypixel()) return;
		if (!Main.isOnSkyblock()) return;
		if (!Main.isInDungeons()) return;
		if (Minecraft.getMinecraft().currentScreen instanceof GuiScreen) return;
		if (event.type != 2) return;
		try {
		
			String actionbar = StringUtils.stripControlCodes(event.message.getUnformattedText());
			String actionbarformat = event.message.getFormattedText();
			if (actionbar.contains("Secrets")) {
				String[] actionbarsplit = actionbar.split(" ");
				List<String> list = Arrays.asList(actionbarsplit);
				secretsoutof = list.get(list.indexOf("Secrets") - 1);
				event.message = new ChatComponentText(actionbarformat.replace("Secrets", "").replace(secretsoutof, ""));
			} else {
				secretsoutof = "No";
			}
		} catch (Exception e) {e.printStackTrace();		}
		
	}
	@SuppressWarnings("static-access")
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void render(RenderTickEvent event) {
		if (!Feature.SecretHud) return;
		if (!Main.config.modules.get("secrethud").booleanValue()) return;
		if (!Main.isHypixel()) return;
		if (!Main.isOnSkyblock()) return;
		if (!Main.isInDungeons()) return;
		if (Minecraft.getMinecraft().currentScreen instanceof GuiScreen) return;
		
		GlStateManager.enableBlend();
        Gui.drawRect(Feature.CopyFailsHUD[0], Feature.CopyFailsHUD[1], Feature.CopyFailsHUD[0] + 75, Feature.CopyFailsHUD[1] + 30, new Color(168, 168, 168, 125).getRGB());
        drawCenteredString(secretsoutof + " Secrets", Feature.CopyFailsHUD[0] + 38, Feature.CopyFailsHUD[1] + 10, Color.white.getRGB(), 1);
	}
	
	  static void drawCenteredString(String text, int x, int y, int color, double scale) {
		    GlStateManager.pushMatrix();
		    GlStateManager.scale(scale, scale, 1);
		    Minecraft.getMinecraft().fontRendererObj.drawString(text,
		        (int) (x / scale) - Minecraft.getMinecraft().fontRendererObj.getStringWidth(text) / 2,
		        (int) (y / scale), color, true);
		    GlStateManager.popMatrix();
		  }
}
