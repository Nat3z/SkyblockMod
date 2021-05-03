package com.nat3z.skyqol.commands;

import com.google.gson.JsonObject;
import com.nat3z.skyqol.CheckIfSupporter;
import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.features.AntiScammer;
import com.nat3z.skyqol.features.DungeonsProfit;
import com.nat3z.skyqol.features.dungeon.WeirdosPuzzle;
import com.nat3z.skyqol.utils.Utilities;
import com.nat3z.skyqol.utils.api.APIHandler;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class RefreshRepo extends CommandBase{

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "nsmrepo";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "/nsmrepo";
	}

	@SuppressWarnings("static-access")
	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		new Thread(() -> {
			
			try {
				Main.config.modules.forEach((name, bool) -> Main.config.modules.replace(name, true));
				Main.nsm_data = APIHandler.getStringFromUrl("https://raw.githubusercontent.com/Nat3z/SkyblockMod-EssentialData/main/DISABLED");
				for (String string : Main.config.modules.keySet()) {
					Main.remoteDisable(string);
				}
				
				AntiScammer.scamList = APIHandler.getStringFromUrl("https://raw.githubusercontent.com/Nat3z/SkyblockMod-EssentialData/main/data/scammerlist.nsmData").split("/NLINE");
				DungeonsProfit.theProfit = APIHandler.getResponse("https://raw.githubusercontent.com/Nat3z/SkyblockMod-EssentialData/main/data/dungeonProfit.json");
				CheckIfSupporter.checkSupport();
				WeirdosPuzzle.nsm_data = APIHandler.getStringFromUrl("https://raw.githubusercontent.com/Nat3z/SkyblockMod-EssentialData/main/data/solvers/3weirdos.nsmData");
				Utilities.sendMessage("Successfully refreshed connection to repository.");
			} catch (Exception ex) {
				ex.printStackTrace();
				Utilities.sendWarning("An unexpected error occurred when refreshing repository connection.");
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
