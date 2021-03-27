package com.nat3z.skyqol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import me.nat3z.APIHandler;
import me.nat3z.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CheckIfSupporter {
	private boolean sus = false;
	private static boolean isSupport = false;
	@SubscribeEvent
	public void supportman(EntityJoinWorldEvent event) {
		if (sus) return;
    	sus = true;
		new Thread(() -> {
	    	try {
		  
			    String cosmetics1 = APIHandler.getStringFromUrl("https://raw.githubusercontent.com/Nat3z/SkyblockMod/main/SUPPORTERS");
			    
			    if (cosmetics1.contains(Minecraft.getMinecraft().thePlayer.getName())) {
			    	isSupport = true;
			    	wait(3000);
			    	Utilities.sendMessage("Thanks for supporting Nate's Secret Mod! Your help is appreciated!");
			    }
	    	} catch (Exception ex) {
		        Utilities.sendWarning("An error ocurred while getting your Supporter Status.");
		    }
		}).start();
	}
	public static void wait(int ms) {
	    try {
	        Thread.sleep(ms);
	    } catch(InterruptedException ex) {
	        Thread.currentThread().interrupt();
	    }
	}

	public static void isSupporting(Runnable run) {
		if (isSupport == true) {
			run.run();
		}
	}
}
