package com.nat3z.skyqol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.nat3z.skyqol.utils.Utilities;
import com.nat3z.skyqol.utils.api.APIHandler;
import com.nat3z.skyqol.utils.api.RequestAPI;

import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CheckIfSupporter {
	private static boolean sus = false;
	public static boolean isSupport = false;
	
	
	public static void checkSupport() {
		if (sus) return;
    	sus = true;
		new Thread(() -> {
	    	try {
		  
			    String cosmetics1 = APIHandler.getStringFromUrl("https://raw.githubusercontent.com/Nat3z/SkyblockMod-EssentialData/main/SUPPORTERS");
			    
			    System.out.println(RequestAPI.getUUID(Minecraft.getMinecraft().thePlayer.getName()));
			    
			    if (cosmetics1.contains(RequestAPI.getUUID(Minecraft.getMinecraft().thePlayer.getName()))) {
			    	isSupport = true;
			    	wait(3000);
			    	Utilities.sendMessage("Thanks for supporting Nate's Secret Mod! Your help is appreciated!");
			    }
	    	} catch (Exception ex) {
		        Utilities.sendWarning("An error ocurred while getting your string.");
		    }
		}).start();
	}
	
	@SubscribeEvent
	public void supportman(EntityJoinWorldEvent event) {
		checkSupport();
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

