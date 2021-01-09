package me.nat3z;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import net.minecraft.client.Minecraft;
import net.minecraft.crash.CrashReport;

public class CheckForPiracy {

	public void skyQOLPiracy() throws IOException {

		    String urlString = "http://nat3z.github.io/sbpiracy/";

		    // create the url
		    URL url = new URL(urlString);

		    // open the url stream, wrap it an a few "readers"
		    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

		    // write the output to stdout
		    String line = reader.readLine();
		    
		    if (!line.contains(Minecraft.func_71410_x().field_71439_g.func_110124_au().toString())) {
					
				System.out.println("Piracy detected. Stopping Minecraft Installation");
				Minecraft.func_71410_x().func_71404_a(new CrashReport("SkyblockQOL Mod - We have detected piracy. If you believe this is a mistake, please contact the Devs.", new Exception()));
					
			}

		    // close our reader
		    reader.close();
	
	}
}