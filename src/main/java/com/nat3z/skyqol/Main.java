package com.nat3z.skyqol;

import java.io.*;
import java.util.List;

import com.nat3z.skyqol.config.PersistentValue;
import com.nat3z.skyqol.features.*;
import com.nat3z.skyqol.gui.Commands;
import com.nat3z.skyqol.gui.GUI;
import com.nat3z.skyqol.gui.SetKey;

import me.nat3z.APIHandler;
import me.nat3z.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod (modid = "natemodskyblock", version = "1.0.8", name = "Nate's Skyblock Mod")
public class Main {

	public static String version = "1.0.8";
	
    public boolean guiOpen = false;
    @Mod.Instance("natemodskyblock")
    public static Main INSTANCE;
    public boolean enabled = true;
    public static Config config;

    public boolean stopDropping = false;
    public int pagetoopen = 1;
    public static PersistentValue persistentValues;
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) throws IOException
    {
    	
        System.out.println("-=-=-=-=-=-=-=-=-");
        System.out.println("Nate's Secret Mod");
        System.out.println("Now Checking Status");
        System.out.println("-=-=-=-=-=-=-=-=-");
        
        // This is probably not the best way of doing this but idgaf
        persistentValues = new PersistentValue(event.getModConfigurationDirectory());
        config = new Config(event.getModConfigurationDirectory());

    }
    
    /*
     * Simple Event Registry
     * MinecraftForge.EVENT_BUS.register(new NAME OF WORKSPACE);
     * 
     * Simple Command Registry
     * ClientCommandHandler.instance.registerCommand(new NAME OF WORKSPACE);
     */

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        System.out.println("-=-=-=-=-=-=-=-=-");
        System.out.println("Nate's Secret Mod");
        System.out.println("  Has Activated");
        System.out.println("-=-=-=-=-=-=-=-=-");
        MinecraftForge.EVENT_BUS.register(new AntiNonEnchanted());
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new CheckIfSupporter());
        MinecraftForge.EVENT_BUS.register(new HighlightFarmingContests());
        MinecraftForge.EVENT_BUS.register(new CopyFails());

        MinecraftForge.EVENT_BUS.register(new WarnUsersForRareItem());
        MinecraftForge.EVENT_BUS.register(new CheckForUpdates());
        MinecraftForge.EVENT_BUS.register(new DungeonReparty());

        ClientCommandHandler.instance.registerCommand(new Commands(this));
        ClientCommandHandler.instance.registerCommand(new SetKey(this));
        
        for(String key : Config.modules.keySet()){
        	remoteDisable(key);
        }
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	persistentValues.loadValues();
    	config.loadValues();
    }
    
    @SubscribeEvent
    public void onTick(TickEvent.RenderTickEvent event) {
        if(guiOpen) {
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen) new GUI(pagetoopen));
            this.pagetoopen = 1;
        	this.guiOpen = false;
            return;
        }
    }
    

	public void openGUI() {
		this.guiOpen = true;
	}
	
	public static boolean isOnSkyblock() {
		
    	Minecraft mc = Minecraft.getMinecraft();
    	EntityPlayerSP p = mc.thePlayer;
    	
    	
    	String sbTitle;
    	
    	// For Some Reason This Bugs Out On Mineplex?????
    	try {
    		sbTitle = EnumChatFormatting.getTextWithoutFormattingCodes(p.getWorldScoreboard().getObjectiveInDisplaySlot(1).getDisplayName());
        	if(sbTitle.toLowerCase().contains("skyblock"))
        		return true;
    	} catch (Exception e) {}
		
		
		return false;
	}
	
	public static boolean isHypixel() {
		try {
			if (Minecraft.getMinecraft().getCurrentServerData().serverIP.contains("hypixel"))
				return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	public static boolean isInDungeons() {
       	try {
    		if (isOnSkyblock()) {
    			List<String> scoreboard = Utilities.getScoreboardLines();
    			
    			for (String s : scoreboard) {
    				String sCleaned = Utilities.cleanSB(s);
    				if (sCleaned.contains("The Catacombs"))
    					return true;
    			}
    		}
    		
    	} catch (Exception e) { e.printStackTrace(); }
		return false;
	}
	
	@SuppressWarnings("static-access")
	public static void remoteDisable(String type) {
		if (APIHandler.getStringFromUrl("https://raw.githubusercontent.com/Nat3z/SkyblockMod/main/DISABLED").contains(type)) {
			config.modules.replace(type, false);
		};
	}
    
}