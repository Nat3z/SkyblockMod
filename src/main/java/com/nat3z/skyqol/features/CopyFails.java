package com.nat3z.skyqol.features;

import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.config.Feature;
import com.nat3z.skyqol.utils.Utilities;

import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CopyFails {
	
	@SuppressWarnings("static-access")
	@SubscribeEvent
	public void onFail(ClientChatReceivedEvent event) {
		if (Feature.CopyFails || Main.config.modules.get("copydungeonfail")) {
			String message = event.message.getUnformattedText();
			if (message.contains(": "))
				return;
			
			if (Main.isOnSkyblock() && Main.isInDungeons()) {
				if (message.contains("You were killed by") || message.contains(" was killed by") || message.contains("died to a")) {
					StringSelection stringSelection = new StringSelection(message);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
					Utilities.sendMessage("Death Copied!");
				} else if (message.contains("Yikes!")) {
					StringSelection stringSelection = new StringSelection(message);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(stringSelection, null);
					Utilities.sendMessage("Fail Copied!");
				}
			}
		}
	}
	
}
