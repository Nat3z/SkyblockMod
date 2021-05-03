package com.nat3z.skyqol.commands;

import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.features.AntiScammer;
import com.nat3z.skyqol.utils.Utilities;
import com.nat3z.skyqol.utils.api.APIHandler;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

//https://reportdatahandler.nat3z.repl.co/?reason=Scammed%20my%20Fot&username=Just
public class ReportUser extends CommandBase {

	@Override
	public String getCommandName() {
		// TODO Auto-generated method stub
		return "nsmreport";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		// TODO Auto-generated method stub
		return "/nsmreport";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		if (args.length > 1) {
			new Thread(() -> {
				String username = args[0];
				
				for (String string : AntiScammer.scamList) {
					if (string.contains(username)) {
						Utilities.sendWarning("This user has already been added to the scammers list!");
						return;
					}
				}
				
				String reason = "";
				for (int i = 1; i < args.length; i++) {
					if (i == 1) {
						reason += args[i];
					} else {
						reason += "+" + args[i];
					}
				}
				
				System.out.println(reason);
				
				String returnValue = APIHandler.getStringFromUrl("https://reportdatahandler.nat3z.repl.co/?username=" + username + "&reason=" + reason);
				
				if (returnValue == null) {
					Utilities.sendWarning("An unexpected error occurred.");
					return;
				}
				
				if (returnValue.equals("0")) {
					Utilities.sendMessage("Successfully sent report! It may take a while for the report to process but please be patient!");
				} else if (returnValue.equals("-1")) {
					Utilities.sendWarning("An unexpected error occurred.");
				} else if (returnValue.equals("-2")) {
					Utilities.sendWarning("Our report webserver has reached the maximum amount of reports. Please wait a while before sending one again.");
				} else if (returnValue.equals("-3")) {
					Utilities.sendWarning("A report for the given user already exists!");
				}
			}).start();
		} else {
			Utilities.sendWarning("Invalid Paramaters. Remember to provide proof by sending a screenshot link in the reason. The command usage is /nsmreport {USERNAME} {REASON}");
		}
	}
	
    public int getRequiredPermissionLevel() {
        return 0;
    }

    public boolean canCommandSenderUseCommand(final ICommandSender sender) {
        return true;
    }
	
}
