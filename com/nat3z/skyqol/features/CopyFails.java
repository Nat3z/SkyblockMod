package com.nat3z.skyqol.features;

import com.nat3z.skyqol.Main;
import me.nat3z.Utilities;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CopyFails {
	public int deathcount = 0;
	private String person;
	
	@SuppressWarnings("static-access")
	@SubscribeEvent
	public void onFail(ClientChatReceivedEvent event) {
		if (Main.config.isModEnabled() || Main.config.isCopydungeonfail() || Main.config.modules.get("copydungeonfail")) {
			String message = event.message.getUnformattedText();
			if (message.contains(": "))
				return;
			
			if (Main.isOnSkyblock() && Main.isInDungeons()) {
				if (message.contains("You were killed by") || message.contains(" was killed by")) {
					deathcount++;
					StringSelection stringSelection = new StringSelection(message);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
					Utilities.sendMessage("Death Copied!");
					System.out.println(deathcount);
				}
				if (!message.contains(":") && message.contains("Sending to server") && deathcount > 0) {
					deathcount = 0;
					person = null;
				}
			}
		}
	}
	
}
