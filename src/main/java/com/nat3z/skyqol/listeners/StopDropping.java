/*package com.nat3z.skyqol.listeners;

import java.util.concurrent.TimeUnit;

import com.nat3z.skyqol.Config;
import com.nat3z.skyqol.Main;

import club.sk1er.mods.core.util.Multithreading;
import me.nat3z.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class StopDropping {
	private Main m = new Main();
	@SubscribeEvent
	public void StopPlace(BlockEvent.PlaceEvent event) {
		if (!Config.stopplacingflowers)
			return;
		Utilities.sendMessage("Recieved Place Block Event");
		EntityPlayerSP p = Minecraft.getMinecraft().thePlayer;
		if (p.getItemInUse().getDisplayName().contains("Flower of Truth") || p.getItemInUse().getDisplayName().contains("Spirit Sceptre"))
			event.setCanceled(true);
		else {
			Utilities.sendWarning("Is not holding Flower of Truth/Spirit Sceptre");
		}
	}
	@SubscribeEvent
	public void disablechat(ClientChatReceivedEvent event) {
		Utilities.sendMessage("Recieved Event");
		if (Config.stopteleportpad && event.message.getUnformattedTextForChat().contains("Warped from the ") || event.message.getUnformattedTextForChat().contains("This Teleport Pad does not have a destination set!"))
			event.setCanceled(true);
			
	}
  /*
    @SubscribeEvent
    public void chatI(WorldEvent.Load event) {
    	if (m.stopDropping)
    		return;
    	m.stopDropping = true;
    	Utilities.sendMessage("Success do WorldEvent");
    	Multithreading.schedule(
    			() -> m.stopDropping = false,
    			9, TimeUnit.SECONDS);
    }
    */
	
//}
