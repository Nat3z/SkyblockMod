package com.nat3z.skyqol.features;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.text.DecimalFormat;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.google.gson.JsonObject;
import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.config.Feature;
import com.nat3z.skyqol.events.GuiChestBackgroundDrawnEvent;
import com.nat3z.skyqol.events.SlotClickedEvent;
import com.nat3z.skyqol.utils.ItemUtils;
import com.nat3z.skyqol.utils.Utilities;
import com.nat3z.skyqol.utils.api.APIHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringUtils;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

public class DungeonsProfit {

	public static JsonObject theProfit = APIHandler.getResponse("https://raw.githubusercontent.com/Nat3z/SkyblockMod-EssentialData/main/data/dungeonProfit.json");
	public int[] profits = new int[7];
	
	
	@SuppressWarnings("static-access")
	@SubscribeEvent
	public void getProfit(GuiChestBackgroundDrawnEvent event) {
		
		if (!Main.config.modules.get("profit") || !Feature.DungeonProfits) return;
		if (Main.isOnSkyblock() && Main.isInDungeons()) {
			try {
				if (event.displayName.contains("Wood") || event.displayName.contains("Gold") || event.displayName.contains("Emer") || event.displayName.contains("Diamond") || event.displayName.contains("Obsidian") || event.displayName.contains("Bedrock")) {
					String theId = ItemUtils.getItemType(event.slots.get(11).getStack());
					String theIdSlot12 = ItemUtils.getItemType(event.slots.get(12).getStack());
					String theIdSlot13 = ItemUtils.getItemType(event.slots.get(13).getStack());
					
					if (theId != null)
						if (theId.equals("ENCHANTED_BOOK")) {
							theId = StringUtils.stripControlCodes(ItemUtils.getItemLore(event.slots.get(11).getStack()).get(0));
							String[] slit = theId.split(" ");
							theId = theId.replace(" " + slit[slit.length - 1], "").replaceAll(" ", "_").toUpperCase();
						}
					if (theIdSlot12 != null)
						if (theIdSlot12.equals("ENCHANTED_BOOK")) {
							theIdSlot12 = StringUtils.stripControlCodes(ItemUtils.getItemLore(event.slots.get(12).getStack()).get(0));
							String[] slit = theIdSlot12.split(" ");
							theIdSlot12 = theIdSlot12.replace(" " + slit[slit.length - 1], "").replaceAll(" ", "_").toUpperCase();
						}
					
					if (theIdSlot13 != null)
						if (theIdSlot13.equals("ENCHANTED_BOOK")) {
							theIdSlot13 = StringUtils.stripControlCodes(ItemUtils.getItemLore(event.slots.get(13).getStack()).get(0));
							String[] slit = theIdSlot13.split(" ");
							theIdSlot13 = theIdSlot13.replace(" " + slit[slit.length - 1], "").replaceAll(" ", "_").toUpperCase();
						}
					
					int coins = 0;
					String toolTip = event.slots.get(31).getStack().getTagCompound().getCompoundTag("display").getTagList("Lore", 8).getStringTagAt(6).replaceAll(",", "").replace("Coins", "").replaceAll(" ", "").toString();
		    		toolTip = StringUtils.stripControlCodes(toolTip);
		    		
		    		
		    		
		    		if (!toolTip.toLowerCase().contains("free"))
						coins = Integer.parseInt(toolTip);
		    		
					int am1 = 0;
					int am2 = 0;
					int am3 = 0;

		    		if (theProfit.has(theId) && theId != null)
		    			am1 = theProfit.get(theId).getAsInt();
		    		
		    		if (theProfit.has(theIdSlot12) && theIdSlot12 != null)
			    		am2 = theProfit.get(theIdSlot12).getAsInt();
		    		
		    		if (theProfit.has(theIdSlot13) && theIdSlot13 != null)
			    		am3 = theProfit.get(theIdSlot13).getAsInt();
		    		
		    		int result = 0;
		    		result = (am1 + am2 + am3) - coins;
		    		if (result >= 1000000000) {
			    		String re = "" + result;
			    		re = re.substring(0, Math.min(re.length(), 1));
			    		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
			    		int guiLeft = (sr.getScaledWidth() - 176) / 2;
			    		int guiTop = (sr.getScaledHeight() - 222) / 2;
			    			
			    		int x = guiLeft + 85;
			    		int y = guiTop + (int)6.6;
			    		GL11.glTranslated(0, 0, 1);
			    		Minecraft.getMinecraft().fontRendererObj.drawString("Profit: " + re + "B", x, y, new Color(53, 143, 59).getRGB());
			    		GL11.glTranslated(0, 0, -1);
		    		}
		    		else if (result >= 100000000) {
		    			String re = "" + result;
		    			re = re.substring(0, Math.min(re.length(), 3));
		    			ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		    			int guiLeft = (sr.getScaledWidth() - 176) / 2;
		    			int guiTop = (sr.getScaledHeight() - 222) / 2;
		    			
		    			int x = guiLeft + 85;
		    			int y = guiTop + (int)6.6;
		    			GL11.glTranslated(0, 0, 1);
		    			Minecraft.getMinecraft().fontRendererObj.drawString("Profit: " + re + "M", x, y, new Color(53, 143, 59).getRGB());
		    			GL11.glTranslated(0, 0, -1);
		    		}
		    		else if (result >= 10000000) {
		    			String re = "" + result;
		    			re = re.substring(0, Math.min(re.length(), 2));
		    			ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		    			int guiLeft = (sr.getScaledWidth() - 176) / 2;
		    			int guiTop = (sr.getScaledHeight() - 222) / 2;
		    			
		    			int x = guiLeft + 85;
		    			int y = guiTop + (int)6.6;
		    			GL11.glTranslated(0, 0, 1);
		    			Minecraft.getMinecraft().fontRendererObj.drawString("Profit: " + re + "M", x, y, new Color(53, 143, 59).getRGB());
		    			GL11.glTranslated(0, 0, -1);
		    		}
		    		else if (result >= 1000000) {
		    			String re = "" + result;
		    			re = re.substring(0, Math.min(re.length(), 1));
		    			ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		    			int guiLeft = (sr.getScaledWidth() - 176) / 2;
		    			int guiTop = (sr.getScaledHeight() - 222) / 2;
		    			
		    			int x = guiLeft + 85;
		    			int y = guiTop + (int)6.6;
		    			GL11.glTranslated(0, 0, 1);
		    			Minecraft.getMinecraft().fontRendererObj.drawString("Profit: +" + re + "M", x, y, Color.green.getRGB());
		    			GL11.glTranslated(0, 0, -1);
		    		}
		    		
		    		if (result < 0) {
		    			DecimalFormat myFormatter = new DecimalFormat("###,###");
		    			String output = myFormatter.format(result);
		    			
		    			ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		    			int guiLeft = (sr.getScaledWidth() - 176) / 2;
		    			int guiTop = (sr.getScaledHeight() - 222) / 2;
		    			
		    			int x = guiLeft + 85;
		    			int y = guiTop + (int)6.6;
		    			GL11.glTranslated(0, 0, 1);
		    			Minecraft.getMinecraft().fontRendererObj.drawString("Profit: " + output, x, y, Color.red.getRGB());
		    			GL11.glTranslated(0, 0, -1);
		    			
		    		} else if (result >= 0) {
		    			DecimalFormat myFormatter = new DecimalFormat("###,###");
		    			String output = myFormatter.format(result);
		    			
		    			ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
		    			int guiLeft = (sr.getScaledWidth() - 176) / 2;
		    			int guiTop = (sr.getScaledHeight() - 222) / 2;
		    			
		    			int x = guiLeft + 85;
		    			int y = guiTop + (int)6.6;
		    			GL11.glTranslated(0, 0, 1);
		    			Minecraft.getMinecraft().fontRendererObj.drawString("Profit: +" + output, x, y, Color.yellow.getRGB());
		    			GL11.glTranslated(0, 0, -1);
		    		}
		    		
		    		if (event.displayName.contains("Wood")) {
		    			this.profits[1] = result;
		    		} else if (event.displayName.contains("Gold")) {
		    			this.profits[2] = result;
		    		} else if (event.displayName.contains("Diamond")) {
		    			this.profits[3] = result;
		    		} else if (event.displayName.contains("Emer")) {
		    			this.profits[4] = result;
		    		} else if (event.displayName.contains("Obsidian")) {
		    			this.profits[5] = result;
		    		} else if (event.displayName.contains("Bedrock")) {
		    			this.profits[6] = result;
		    		}
		    	}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("static-access")
	@SubscribeEvent
	public void profitShow(RenderTickEvent event) {
		if (Minecraft.getMinecraft().currentScreen instanceof GuiScreen && !(Minecraft.getMinecraft().currentScreen instanceof GuiChest)) return;
		
		if (Feature.ProfitHud && Main.config.modules.get("profit")) {
			if (this.profits[1] != 0) {
    			DecimalFormat myFormatter = new DecimalFormat("###,###,###");
    			String output = myFormatter.format(this.profits[1]);
				Minecraft.getMinecraft().fontRendererObj.drawString("Wood Chest: " + EnumChatFormatting.GRAY + output, Feature.ProfitHUD[0], Feature.ProfitHUD[1], new Color(135, 91, 41).getRGB(), true);

			}
			
			if (this.profits[2] != 0) {
    			DecimalFormat myFormatter = new DecimalFormat("###,###,###");
    			String output = myFormatter.format(this.profits[2]);
				Minecraft.getMinecraft().fontRendererObj.drawString("Gold Chest: " + EnumChatFormatting.GRAY + output, Feature.ProfitHUD[0], Feature.ProfitHUD[1] + 10, new Color(196, 156, 10).getRGB(), true);
			}
			
			if (this.profits[3] != 0) {
    			DecimalFormat myFormatter = new DecimalFormat("###,###,###");
    			String output = myFormatter.format(this.profits[3]);
				Minecraft.getMinecraft().fontRendererObj.drawString("Diamond Chest: " + EnumChatFormatting.GRAY + output, Feature.ProfitHUD[0], Feature.ProfitHUD[1] + 20, new Color(8, 207, 203).getRGB(), true);
			}
			
			if (this.profits[4] != 0) {
    			DecimalFormat myFormatter = new DecimalFormat("###,###,###");
    			String output = myFormatter.format(this.profits[4]);
				Minecraft.getMinecraft().fontRendererObj.drawString("Emerald Chest: " + EnumChatFormatting.GRAY + output, Feature.ProfitHUD[0], Feature.ProfitHUD[1] + 30, new Color(52, 150, 39).getRGB(), true);
			}
			
			if (this.profits[5] != 0) {
    			DecimalFormat myFormatter = new DecimalFormat("###,###,###");
    			String output = myFormatter.format(this.profits[5]);
				Minecraft.getMinecraft().fontRendererObj.drawString("Obsidian Chest: " + EnumChatFormatting.GRAY + output, Feature.ProfitHUD[0], Feature.ProfitHUD[1] + 40, Color.gray.getRGB(), true);
			}
			
			if (this.profits[6] != 0) {
    			DecimalFormat myFormatter = new DecimalFormat("###,###,###");
    			String output = myFormatter.format(this.profits[6]);
				Minecraft.getMinecraft().fontRendererObj.drawString("Bedrock Chest: " + EnumChatFormatting.GRAY + output, Feature.ProfitHUD[0], Feature.ProfitHUD[1] + 50, new Color(18, 18, 18).getRGB(), true);
			}
			
		}
	}
	
	@SubscribeEvent
	public void onLeaveWorld(WorldEvent.Load event) {
		this.profits = new int[7];
	} 
	
	@SubscribeEvent
	public void toolTip(SlotClickedEvent event) {
		if (!Feature.Debug) return;
		if (!Mouse.getEventButtonState()) return;
		try {
			if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
				String theId = ItemUtils.getItemType(event.slot.getStack());
				if (theId.equals("ENCHANTED_BOOK")) {
					theId = StringUtils.stripControlCodes(ItemUtils.getItemLore(event.slot.getStack()).get(0));
					String[] slit = theId.split(" ");
					theId = theId.replace(" " + slit[slit.length - 1], "").replaceAll(" ", "_").toUpperCase();
				}
				
				System.out.println(theId);
				StringSelection stringSelection = new StringSelection(theId);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, null);
				Utilities.sendMessage("Copied Skyblock Item ID.");
				event.setCanceled(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Utilities.sendWarning("An unexpected error occurred.");
		}

	}
}
