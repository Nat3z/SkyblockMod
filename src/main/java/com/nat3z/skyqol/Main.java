package com.nat3z.skyqol;

import java.io.*;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.nat3z.skyqol.config.APIString;
import com.nat3z.skyqol.config.NSMConfigGuiL;
import com.nat3z.skyqol.config.PersistentValue;
import com.nat3z.skyqol.events.GuiChestBackgroundDrawnEvent;
import com.nat3z.skyqol.events.KeyPressedEvent;
import com.nat3z.skyqol.features.*;
import com.nat3z.skyqol.features.ItemSearch.ItemSearcher;
import com.nat3z.skyqol.features.ItemSearch.SearchIt;
import com.nat3z.skyqol.features.playlist.PlaylistFolder;
import com.nat3z.skyqol.features.playlist.PlaylistHUD;
import com.nat3z.skyqol.features.playlist.Playsong;
import com.nat3z.skyqol.features.playlist.PressKeyPlay;
import com.nat3z.skyqol.features.playlist.RefreshPlaylist;
import com.nat3z.skyqol.features.terminal.ClickInOrder;
import com.nat3z.skyqol.features.terminal.SelectAllBLANK;
import com.nat3z.skyqol.features.terminal.WhatStartsWith;
import com.nat3z.skyqol.gui.Commands;
import com.nat3z.skyqol.gui.GUI;
import com.nat3z.skyqol.gui.GUIKicker;
import com.nat3z.skyqol.gui.MoveGUI;
import com.nat3z.skyqol.gui.MoveUI;
import com.nat3z.skyqol.gui.SetKey;
import com.nat3z.skyqol.gui.classic.ClassicUI;

import me.nat3z.APIHandler;
import me.nat3z.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.Slot;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod (modid = "natemodskyblock", version = "1.0.9", name = "Nate's Secret Mod")
public class Main {

	public static String version = "1.0.9";
	
    public boolean guiOpen = false;
    public boolean DungeonKicker = false;
    public boolean ClassicUIop = false;
    public boolean MoveUI = false;

    
    @Mod.Instance("natemodskyblock")
    public static Main INSTANCE;
    public boolean enabled = true;
    public static Config config;
    public static APIString apis;

    public boolean stopDropping = false;
    public int pagetoopen = 1;
    public static PersistentValue persistentValues;
    public static PlaylistFolder playlist;
    public static NSMConfigGuiL guic;

    
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
        apis = new APIString(event.getModConfigurationDirectory());
        playlist = new PlaylistFolder(event.getModConfigurationDirectory());
        guic = new NSMConfigGuiL(event.getModConfigurationDirectory());
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
        
        // TERMINALS
        //MinecraftForge.EVENT_BUS.register(new ClickInOrder());
        //MinecraftForge.EVENT_BUS.register(new SelectAllBLANK());
        //MinecraftForge.EVENT_BUS.register(new WhatStartsWith());
        
        MinecraftForge.EVENT_BUS.register(new DungeonsKicker());
        MinecraftForge.EVENT_BUS.register(new ClickAnywhereSlayer());
        MinecraftForge.EVENT_BUS.register(new SearchIt());

        MinecraftForge.EVENT_BUS.register(new PlaylistHUD());
        MinecraftForge.EVENT_BUS.register(new PressKeyPlay());
        MinecraftForge.EVENT_BUS.register(new SecretDisplay());

        
        ClientCommandHandler.instance.registerCommand(new ItemSearcher(this));
        ClientCommandHandler.instance.registerCommand(new Commands(this));
        ClientCommandHandler.instance.registerCommand(new SetKey(this));

        ClientCommandHandler.instance.registerCommand(new RefreshPlaylist());
        ClientCommandHandler.instance.registerCommand(new Playsong());
        ClientCommandHandler.instance.registerCommand(new MoveUI());

        for(String key : Config.modules.keySet()){
        	remoteDisable(key);
        }
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	persistentValues.loadValues();
    	config.loadValues();
    	apis.loadValues();
    	guic.loadValues();
    }
    
    @SubscribeEvent
    public void onTick(TickEvent.RenderTickEvent event) {
        if(guiOpen) {
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen) new GUI(pagetoopen));
            this.pagetoopen = 1;
        	this.guiOpen = false;
            return;
        } else if (DungeonKicker) {
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen) new GUIKicker());
        	this.DungeonKicker = false;
            return;
        } else if (ClassicUIop) {
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen) new ClassicUI(pagetoopen));
        	this.ClassicUIop = false;
            return;
        } else if (MoveUI) {
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen) new MoveGUI());
        	this.MoveUI = false;
            return;
        }
    }
    

	public void openGUI() {
		this.guiOpen = true;
	}
	public void openDung() {
		this.DungeonKicker = true;
	}
	public void openClassicUI() {
		this.ClassicUIop = true;
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
    				if (sCleaned.contains("Dungeon Cleared"))
    					return true;
    			}
    		}
    		
    	} catch (Exception e) { e.printStackTrace(); }
		return false;
	}
	
	@SuppressWarnings("static-access")
	public static void remoteDisable(String type) {
		if (APIHandler.getStringFromUrl("https://raw.githubusercontent.com/Nat3z/SkyblockMod-EssentialData/main/DISABLED").contains(type)) {
			config.modules.replace(type, false);
		};
	}
	/**
	 * This method and event was created by Dankers Skyblock Mod
	 * @author Dankers
	 */
	@SubscribeEvent
    public void onGuiRender(GuiScreenEvent.BackgroundDrawnEvent event) {
        if (event.gui instanceof GuiChest) {
            GuiChest inventory = (GuiChest) event.gui;
            Container containerChest = inventory.inventorySlots;
            if (containerChest instanceof ContainerChest) {
                List<Slot> invSlots = inventory.inventorySlots.inventorySlots;
                String displayName = ((ContainerChest) containerChest).getLowerChestInventory().getDisplayName().getUnformattedText().trim();
                int chestSize = inventory.inventorySlots.inventorySlots.size();

                MinecraftForge.EVENT_BUS.post(new GuiChestBackgroundDrawnEvent(inventory, displayName, chestSize, invSlots));
            }
        }
    }
	
	@SubscribeEvent
	public void keyPressEvent(KeyInputEvent event) {
		MinecraftForge.EVENT_BUS.post(new KeyPressedEvent(Keyboard.getEventKey(), Keyboard.getEventKeyState()));
	}
    
}