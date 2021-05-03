package com.nat3z.skyqol.commands;

import com.nat3z.skyqol.CheckIfSupporter;
import com.nat3z.skyqol.features.dungeon.DungeonReparty;
import com.nat3z.skyqol.utils.Utilities;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ReParty extends CommandBase {

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "nsmrp";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "/nsmrp";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		
		new Thread(() -> {
			DungeonReparty.stopmessages = true;

			DungeonReparty.partylist.clear();
			
			Minecraft.getMinecraft().thePlayer.sendChatMessage("/party list");
				
			CheckIfSupporter.wait(2000);
				
			System.out.println(DungeonReparty.ispartyleader);
				
			if (DungeonReparty.ispartyleader) {
				if (DungeonReparty.partylist.isEmpty()) {
					Utilities.sendWarning("No one but you is in your party!");
					DungeonReparty.partylist.clear();
					DungeonReparty.isError = false;
					DungeonReparty.stopmessages = false;
					DungeonReparty.ispartyleader = false;
					return;
				}
								
				String command = "/party invite";
				Minecraft.getMinecraft().thePlayer.sendChatMessage("/party disband");
				
				for (String string : DungeonReparty.partylist) {
					command += " " + string;
				}
					
				Utilities.sendMessage(command);
				CheckIfSupporter.wait(1000);
				Minecraft.getMinecraft().thePlayer.sendChatMessage(command);
				while (DungeonReparty.stopmessages) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
							e.printStackTrace();
					}
				}
					
				if (DungeonReparty.isError)
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "" + EnumChatFormatting.STRIKETHROUGH + "-----------------------------\n\n" + EnumChatFormatting.RED + "     Repartied Users With Error.\n\n" + EnumChatFormatting.BLUE + "" + EnumChatFormatting.STRIKETHROUGH + "-----------------------------"));
				else
					Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.BLUE + "" + EnumChatFormatting.STRIKETHROUGH + "-----------------------------\n\n" + EnumChatFormatting.YELLOW + "     Repartied all users in Party List. \n\n" + EnumChatFormatting.BLUE + "" + EnumChatFormatting.STRIKETHROUGH + "-----------------------------"));
				DungeonReparty.partylist.clear();
				DungeonReparty.isError = false;
				DungeonReparty.ispartyleader = false;
			} else {
				Utilities.sendWarning("You are not this party's leader!");
				DungeonReparty.partylist.clear();
				DungeonReparty.isError = false;
				DungeonReparty.stopmessages = false;
				DungeonReparty.ispartyleader = false;
			}
		}).start();
		
	}
	
    public int getRequiredPermissionLevel() {
        return 0;
    }

    public boolean canCommandSenderUseCommand(final ICommandSender sender) {
        return true;
    }
}
