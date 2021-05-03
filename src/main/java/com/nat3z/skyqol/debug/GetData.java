package com.nat3z.skyqol.debug;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.lwjgl.input.Keyboard;

import com.google.gson.JsonObject;
import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.config.Feature;
import com.nat3z.skyqol.events.KeyPressedEvent;
import com.nat3z.skyqol.utils.ItemUtils;
import com.nat3z.skyqol.utils.Utilities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.Slot;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GetData {
	
	File folder = null;
	
	public GetData(File file) {
		folder = new File(file.getAbsolutePath() + "/nsm_debug/");
	}
	
	boolean saving = false;
	
	@SubscribeEvent
	public void getDataFromLore(GuiScreenEvent.KeyboardInputEvent.Pre event) {
		if (!Feature.Debug) return;
		if (saving) return;
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			
			if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
				
				if (Minecraft.getMinecraft().currentScreen instanceof GuiChest) {
					new Thread(() -> {
						saving = true;
						try {
							GuiChest inv = (GuiChest) Minecraft.getMinecraft().currentScreen;
							Slot slot = inv.getSlotUnderMouse();
							
							String data = "";
							String maxlvl = "";
							String namewithoutlvl = "";
							
							
							for (String string : ItemUtils.getItemLore(slot.getStack())) {
								if (string.contains("----------"))
									break;
								
								data += string + "\n";
							}
							
							String[] split = slot.getStack().getDisplayName().split(" ");
							
							maxlvl = StringUtils.stripControlCodes(split[1].replace("]", ""));
							
							namewithoutlvl = slot.getStack().getDisplayName().replace("[Lvl ", "").replace("] ", "").replace(maxlvl, "");
							
							if (!folder.exists()) {
								folder.mkdir();
							}
							JsonObject valuesObject = new JsonObject();
							FileWriter writer = new FileWriter(this.folder.getAbsolutePath() + "/" + StringUtils.stripControlCodes(namewithoutlvl) + ".json");
							BufferedWriter bufferedWriter = new BufferedWriter(writer);
								
							valuesObject.addProperty("name", namewithoutlvl);
							valuesObject.addProperty("lvl", maxlvl);
							valuesObject.addProperty("lore", data);
								
							bufferedWriter.write(valuesObject.toString());
							bufferedWriter.close();
								
							Utilities.sendMessage("Successfully saved JSON Data!");
						} catch (Exception e) {
							Utilities.sendWarning("An error occurred while parsing data.");
						}
						
						saving = false;
												
					}).start();
				}
				
			}
			
		}
	}
	
}
