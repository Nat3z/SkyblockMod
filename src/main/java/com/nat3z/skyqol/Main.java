package com.nat3z.skyqol;

import java.io.*;
import java.util.List;

import javax.vecmath.Vector3f;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.nat3z.skyqol.commands.DebugToggle;
import com.nat3z.skyqol.commands.ReParty;
import com.nat3z.skyqol.commands.RefreshRepo;
import com.nat3z.skyqol.commands.ReportUser;
import com.nat3z.skyqol.config.Config;
import com.nat3z.skyqol.debug.GetData;
import com.nat3z.skyqol.events.DamageBlockEvent;
import com.nat3z.skyqol.events.GuiChestBackgroundDrawnEvent;
import com.nat3z.skyqol.events.KeyPressedEvent;
import com.nat3z.skyqol.events.SlotClickedEvent;
import com.nat3z.skyqol.features.*;
import com.nat3z.skyqol.features.ItemSearch.ItemSearcher;
import com.nat3z.skyqol.features.ItemSearch.SearchIt;
import com.nat3z.skyqol.features.dungeon.DungeonReparty;
import com.nat3z.skyqol.features.dungeon.DungeonsKicker;
import com.nat3z.skyqol.features.dungeon.WeirdosPuzzle;
import com.nat3z.skyqol.features.experiments.Chronomatron;
import com.nat3z.skyqol.features.experiments.Superpairs;
import com.nat3z.skyqol.features.experiments.UltraSequencer;
import com.nat3z.skyqol.features.playlist.PlaylistFolder;
import com.nat3z.skyqol.features.playlist.PlaylistHUD;
import com.nat3z.skyqol.features.playlist.Playsong;
import com.nat3z.skyqol.features.playlist.PressKeyPlay;
import com.nat3z.skyqol.features.playlist.RefreshPlaylist;
import com.nat3z.skyqol.gui.Commands;
import com.nat3z.skyqol.gui.GUI;
import com.nat3z.skyqol.gui.GUIKicker;
import com.nat3z.skyqol.gui.MoveGUI;
import com.nat3z.skyqol.gui.MoveUI;
import com.nat3z.skyqol.gui.SetKey;
import com.nat3z.skyqol.utils.Message;
import com.nat3z.skyqol.utils.Scheduler;
import com.nat3z.skyqol.utils.Utilities;
import com.nat3z.skyqol.utils.api.APIHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod (modid = "natemodskyblock", version = "1.1.0", name = "Nate's Secret Mod")
public class Main {

	public static final String modid = "natemodskyblock";
	public static final String version = "1.1.0";
	
    public boolean guiOpen = false;
    public boolean DungeonKicker = false;
    public boolean MoveUI = false;

    public static Config config = new Config();
    public static boolean initLoad;
    
    
    @Mod.Instance("natemodskyblock")
    public static Main INSTANCE;
    public boolean enabled = true;

    public boolean stopDropping = false;
    public int pagetoopen = 1;
    public static PlaylistFolder playlist;

    
	@EventHandler
    public void preInit(FMLPreInitializationEvent event) throws IOException
    {
    	
        System.out.println("-=-=-=-=-=-=-=-=-");
        System.out.println("Nate's Secret Mod");
        System.out.println("Now Checking Status");
        System.out.println("-=-=-=-=-=-=-=-=-");
        
        // DEBUG
        MinecraftForge.EVENT_BUS.register(new GetData(event.getModConfigurationDirectory()));
        
        playlist = new PlaylistFolder(event.getModConfigurationDirectory());
        config.reloadConfig();
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
        MinecraftForge.EVENT_BUS.register(new UltraSequencer());
        MinecraftForge.EVENT_BUS.register(new Chronomatron());
        MinecraftForge.EVENT_BUS.register(new CheckIfSupporter());
        MinecraftForge.EVENT_BUS.register(new HighlightFarmingContests());
        MinecraftForge.EVENT_BUS.register(new CopyFails());
        MinecraftForge.EVENT_BUS.register(new CheckForUpdates());
        MinecraftForge.EVENT_BUS.register(new DungeonReparty());
        MinecraftForge.EVENT_BUS.register(new DungeonsKicker());
        MinecraftForge.EVENT_BUS.register(new ClickAnywhereSlayer());
        MinecraftForge.EVENT_BUS.register(new SearchIt());
        MinecraftForge.EVENT_BUS.register(new Superpairs());
        MinecraftForge.EVENT_BUS.register(new PlaylistHUD());
        MinecraftForge.EVENT_BUS.register(new PressKeyPlay());
        MinecraftForge.EVENT_BUS.register(new SecretDisplay());
        MinecraftForge.EVENT_BUS.register(new BurrowWaypoints());
        MinecraftForge.EVENT_BUS.register(new BonemerangHelper());
        MinecraftForge.EVENT_BUS.register(new PuzzlerSolver());
        MinecraftForge.EVENT_BUS.register(new WeirdosPuzzle());
        MinecraftForge.EVENT_BUS.register(new DungeonsProfit());
        MinecraftForge.EVENT_BUS.register(new AntiScammer());
        
        for(String key : Config.modules.keySet()){
        	remoteDisable(key);
        }
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	
        ClientCommandHandler.instance.registerCommand(new ItemSearcher(this));
        ClientCommandHandler.instance.registerCommand(new Commands(this));
        ClientCommandHandler.instance.registerCommand(new SetKey(this));
        ClientCommandHandler.instance.registerCommand(new RefreshRepo());
        ClientCommandHandler.instance.registerCommand(new ReportUser());
        
        
        ClientCommandHandler.instance.registerCommand(new DebugToggle());
        ClientCommandHandler.instance.registerCommand(new RefreshPlaylist());
        ClientCommandHandler.instance.registerCommand(new Playsong());
        ClientCommandHandler.instance.registerCommand(new MoveUI());
        
        ClientCommandHandler.instance.registerCommand(new ReParty());
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
        } else if (MoveUI) {
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen) new MoveGUI());
        	this.MoveUI = false;
            return;
        }
    }
    
    @SubscribeEvent
    public void onJoin(EntityJoinWorldEvent event) {
    	if (initLoad) {
    		initLoad = false;
    		
    		Config.writeBooleanConfig("persistent", "FIRSTLOAD", false);
    		
    		Scheduler.runTask(() -> {
        		Message.sendMessage(EnumChatFormatting.DARK_AQUA + "" + EnumChatFormatting.STRIKETHROUGH + "---------------------- " + "\n" + EnumChatFormatting.GREEN + 
    		"Welcome to Nate's Secret Mod!\n" + EnumChatFormatting.DARK_BLUE + " To use this mod and all of its features, use " + EnumChatFormatting.BOLD + "/nsm\n" + EnumChatFormatting.DARK_BLUE +
    			" and all of the modules will be shown!"
    			+ "\n" + EnumChatFormatting.GREEN + " Thanks for your support!\n" + EnumChatFormatting.BLUE + "- Nat3z\n" +
    			EnumChatFormatting.DARK_AQUA + "" + EnumChatFormatting.STRIKETHROUGH + "---------------------- "
    			
        				);
    		}, 2000);
    	}
    }
    
	public void openGUI() {
		this.guiOpen = true;
	}
	public void openDung() {
		this.DungeonKicker = true;
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
	
	public static boolean isInHub() {
       	try {
    		if (isOnSkyblock()) {
    			List<String> scoreboard = Utilities.getScoreboardLines();
    			
    			for (String s : scoreboard) {
    				String sCleaned = Utilities.cleanSB(s);
    				if (sCleaned.contains("Village") || sCleaned.contains("None") || sCleaned.contains("Colosseum")|| sCleaned.contains("Wizard") || sCleaned.contains("Coal") || sCleaned.contains("High Level") || sCleaned.contains("Auction House") || sCleaned.contains("Graveyard") || sCleaned.contains("Bazaar") || sCleaned.contains("Crypt") || sCleaned.contains("Forest") || sCleaned.contains("Wilderness")  || sCleaned.contains("Mountain") || sCleaned.contains("Dark") || sCleaned.contains("Farm") ||sCleaned.contains("Ruins"))
    					return true;
    			}
    		}
    		
    	} catch (Exception e) { e.printStackTrace(); }
		return false;
	}
	
	public static boolean isInDwarven() {
       	try {
    		if (isOnSkyblock()) {
    			List<String> scoreboard = Utilities.getScoreboardLines();
    			
    			for (String s : scoreboard) {
    				String sCleaned = Utilities.cleanSB(s);
    				if (sCleaned.contains("Lift") || sCleaned.contains("Mist") || sCleaned.contains("Dwarven"))
    					return true;
    			}
    		}
    		
    	} catch (Exception e) { e.printStackTrace(); }
		return false;
	}
	public static String nsm_data = APIHandler.getStringFromUrl("https://raw.githubusercontent.com/Nat3z/SkyblockMod-EssentialData/main/DISABLED");
	@SuppressWarnings("static-access")
	public static void remoteDisable(String type) {
		if (nsm_data.contains(type)) {
			config.modules.replace(type, false);
		};
	}
	/**
	 * This method and event was created by Dankers Skyblock Mod
	 * https://github.com/bowser0000/SkyblockMod/
	 * @author Dankers
	 */
    @SuppressWarnings("static-access")
	@SubscribeEvent
    public void onGuiRender(GuiScreenEvent.BackgroundDrawnEvent event) {
        if (!this.isOnSkyblock()) return;
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
	
	/**
	 * Mostly ALL of this code is from Danker's
	 * https://github.com/bowser0000/SkyblockMod/
	 * @author Danker's
	 */
    @SuppressWarnings("static-access")
	@SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onGuiMouseInputPre(GuiScreenEvent.MouseInputEvent.Pre event) {
        if (!this.isOnSkyblock()) return;
        if (Mouse.getEventButton() != 0 && Mouse.getEventButton() != 1 && Mouse.getEventButton() != 2)
            return; // Left click, middle click or right click

        if (event.gui instanceof GuiChest) {
            Container containerChest = ((GuiChest) event.gui).inventorySlots;
            if (containerChest instanceof ContainerChest) {
                // a lot of declarations here, if you get scarred, my bad
                GuiChest chest = (GuiChest) event.gui;
                IInventory inventory = ((ContainerChest) containerChest).getLowerChestInventory();
                Slot slot = chest.getSlotUnderMouse();
                if (slot == null) return;
                ItemStack item = slot.getStack();
                String inventoryName = inventory.getDisplayName().getUnformattedText();
                if (MinecraftForge.EVENT_BUS.post(new SlotClickedEvent(chest, inventory, inventoryName, slot, item))) event.setCanceled(true);
                
            }
        }
    }
    @SubscribeEvent
    public void playerDamagedBlock(com.nat3z.skyqol.events.MouseEvent event) {
    	Vec3 vect = Minecraft.getMinecraft().thePlayer.getPositionEyes(Minecraft.getSystemTime());
    	if (new BlockPos(vect.xCoord, vect.yCoord, vect.zCoord) != null) {
    		MinecraftForge.EVENT_BUS.post(new DamageBlockEvent(new BlockPos(vect.xCoord, vect.yCoord, vect.zCoord)));
    	}
    	
    }
    
    @SubscribeEvent
    public void mouseClicked(MouseEvent event) {
    	if (!Mouse.getEventButtonState()) return;
    	
    	MinecraftForge.EVENT_BUS.post(new com.nat3z.skyqol.events.MouseEvent(Mouse.getEventButton()));
    }
	
	@SubscribeEvent
	public void keyPressEvent(KeyInputEvent event) {
		MinecraftForge.EVENT_BUS.post(new KeyPressedEvent(Keyboard.getEventKey(), Keyboard.getEventKeyState()));
	}
    
}