package com.nat3z.skyqol.features;

import java.awt.Color;
import java.util.ArrayList;

import javax.vecmath.Vector3f;

import org.apache.commons.lang3.time.StopWatch;
import org.lwjgl.input.Mouse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.config.Feature;
import com.nat3z.skyqol.utils.ItemUtils;
import com.nat3z.skyqol.utils.RenderUtil;
import com.nat3z.skyqol.utils.Utilities;
import com.nat3z.skyqol.utils.api.RequestAPI;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
// With Help From Skytils Github Repository!
// https://github.com/Skytils/SkytilsMod/blob/main/src/main/java/skytils/skytilsmod/features/impl/events/GriffinBurrows.java
@SuppressWarnings("static-access")
public class BurrowWaypoints {
	
	StopWatch refreshBurrows = new StopWatch();
    public static ArrayList<Burrow> burrows = new ArrayList<>();
    public static ArrayList<BlockPos> destroyedBurrows = new ArrayList<>();
    
	boolean showBurrow = false;
	
	@SubscribeEvent
	public void getBurrows(ClientTickEvent event) {
		if (!Feature.BurrowWaypoint || !Main.config.modules.get("burrowpoints")) return;
		if (Main.isHypixel() && Main.isOnSkyblock() && Main.isInHub()) {

			if (!refreshBurrows.isStarted()) refreshBurrows.start();
			if (refreshBurrows.getTime() >= 180000 || wait) {
				refreshBurrows.reset();
				wait = false;
				for (ItemStack stack : ItemUtils.getCurrentHotbar()) {
					if (stack != null)
						if (StringUtils.stripControlCodes(stack.getDisplayName()).contains("Ancestral Spade")) {
							Utilities.sendMessage("Searching for Griffin Burrows!");
							getBurrows();
							break;
						}
				}
				
			}
			
			if (Mouse.isButtonDown(0)) {
				MovingObjectPosition pos = Minecraft.getMinecraft().objectMouseOver;
				if (pos != null) {
					BlockPos bpos = pos.getBlockPos();
					checkBurrow(bpos);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void renderWaypoint(DrawBlockHighlightEvent event) {
		if (!Feature.BurrowWaypoint || !Main.config.modules.get("burrowpoints")) return;

		if (Main.isHypixel() && Main.isOnSkyblock() && Main.isInHub() && !burrows.isEmpty()) {
			try {
				for (Burrow burrow : burrows) {
				    if (burrow != null) {
				    	Utilities.drawWaypoint(event.partialTicks, burrow.getVector(), EnumChatFormatting.AQUA + "Burrow: " + burrow.chain + "/4" + EnumChatFormatting.BOLD + " " + burrow.typeAsRead);
				    	Utilities.HighlightBlock(burrow.getVector(), 0.5f, event.partialTicks, new Color(0, 191, 163));
				    }
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private boolean wait = false;
	
	@SubscribeEvent
	public void onReload(WorldEvent.Load event) {
		burrows.clear();
		destroyedBurrows.clear();
		wait = true;
	}
	
	@SubscribeEvent
	public void onChat(ClientChatReceivedEvent event) {
		if (!Feature.BurrowWaypoint || !Main.config.modules.get("burrowpoints")) return;
		String message = event.message.getUnformattedText();
		
		if (message.contains("You dug out a Griffin Burrow") || message.contains("You finished the Griffin burrow chain!")) {
			if (lastDugBurrow != null) {
				destroyedBurrows.add(lastDugBurrow);
				
				burrows.removeIf(burrow -> burrow.getBlockPos().equals(lastDugBurrow));
				
				lastDugBurrow = null;
			}
		}
	}
	
    public static BlockPos lastDugBurrow = null;

	@SubscribeEvent
	public void onDamageEvent(PlayerInteractEvent event) {
		checkBurrow(event.pos);
	}
	
	public void checkBurrow(BlockPos pos) {
		if (!Feature.BurrowWaypoint || !Main.config.modules.get("burrowpoints")) return;

		if (Minecraft.getMinecraft().theWorld == null || Minecraft.getMinecraft().thePlayer == null) return;
		if (pos == null) return;
		
		ItemStack item = Minecraft.getMinecraft().thePlayer.getHeldItem();
		IBlockState blockstate = Minecraft.getMinecraft().theWorld.getBlockState(pos);
		if (item != null && blockstate != null)
			if (Main.isInHub() && StringUtils.stripControlCodes(item.getDisplayName()).contains("Ancestral Spade")) {
				if (burrows.stream().anyMatch(burrow -> burrow.getBlockPos().equals(pos) && blockstate.getBlock().equals(Blocks.grass))) {
					lastDugBurrow = pos;
				}
			}
	}
	
	public void getBurrows() {
		new Thread(() -> {
			
			String api = Feature.apiKey;
			if (api == "none") {
				Utilities.sendWarning("Your api key is empty! Use: " + EnumChatFormatting.RED + "/nsmsetkey {KEY}");
				showBurrow = false;
				return;
			}
			
			JsonObject response = RequestAPI.getProfile(RequestAPI.getLatestProfileID(RequestAPI.getUUID(Minecraft.getMinecraft().thePlayer.getName()), Feature.apiKey));
			
			if (!response.get("success").getAsBoolean()) {
				Utilities.sendWarning("An error occurred when contacting Hypixel API! Make sure you have the correct API Key set.");
				showBurrow = false;
				return;
			}
			
			JsonObject thePlayer = response.get("profile").getAsJsonObject().get("members").getAsJsonObject().get(Minecraft.getMinecraft().thePlayer.getGameProfile().getId().toString().replaceAll("[\\-]", "")).getAsJsonObject();
			
			JsonArray burrowList = thePlayer.get("griffin").getAsJsonObject().get("burrows").getAsJsonArray();
			
			burrows.clear();
			
			burrowList.forEach(elm -> {
				JsonObject object = elm.getAsJsonObject();
				
                int x = object.get("x").getAsInt();
                int y = object.get("y").getAsInt();
                int z = object.get("z").getAsInt();
                int type = object.get("type").getAsInt();
                int tier = object.get("tier").getAsInt();
                int chain = object.get("chain").getAsInt();
                Burrow burrow = new Burrow(x, y, z, type, tier, chain);
                
                burrows.add(burrow);
			});
			burrows.removeIf(burrow -> destroyedBurrows.contains(burrow.getBlockPos()));
			
			
			if (burrows.isEmpty()) {
				Utilities.sendMessage("No Burrows Were Found! Try and dig one up and we'll catch on automagically!");
			} else 
				Utilities.sendMessage(EnumChatFormatting.GREEN + "" + EnumChatFormatting.BOLD + "SUCCESS!" + EnumChatFormatting.RESET + " NSM got " + burrows.size() + " burrows!");
			if (burrowList.isJsonNull()) return;
		
		}).start();
		
	}
	
	
	
	
	// Code inspired from Skytils
	public static class Burrow {
		public int x = 0;
		public int y = 0;
		public int z = 0;
		public int type = 0;
		public String typeAsRead = EnumChatFormatting.GREEN + "" + EnumChatFormatting.BOLD + "START";
		public int tier = 0;
		public int chain = 0;
		
		
		public Burrow(int x, int y, int z, int type, int tier, int chain) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.type = type;
			this.tier = tier;
			this.chain = chain + 1;
			System.out.println(type + " " + chain);
			if (type == 1) {
				this.typeAsRead = EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "MOB";
			} else if (type == 3) {
				if (this.chain == 4)
					this.typeAsRead = EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "END";
				else
					this.typeAsRead = EnumChatFormatting.GOLD + "" + EnumChatFormatting.BOLD + "LOOT";
			}
		}
		
        public Vector3f getVector() {
            return new Vector3f(x, y, z);
        }
        
        public BlockPos getBlockPos() {
        	return new BlockPos(x, y, z);
        }

	}
	
}
