/*
 * Welcome To The Template!
 * Use This Simple Template To Create Mods!
 * There will be guidance and many templates!
 */

package com.nat3z.skyqol;

import java.io.IOException;

import com.nat3z.skyqol.gui.Commands;
import com.nat3z.skyqol.listeners.AntiNonEnchanted;
import com.nat3z.skyqol.listeners.BasicListener;
//import com.nat3z.skyqol.listeners.StopDropping;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod (modid = "natemodskyblock", version = "1.0.3", name = "Nate's Skyblock Mod")
public class Main {
    public boolean guiOpen;
    @Mod.Instance("natemodskyblock")
    public static Main INSTANCE;
    public boolean enabled = true;
    private final Config config = new Config();
    public boolean stopDropping = false;
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) throws IOException
    {
        System.out.println("-=-=-=-=-=-=-=-=-");
        System.out.println("Nate's Skyblock Mod");
        System.out.println("Now Checking Status");
        System.out.println("-=-=-=-=-=-=-=-=-");
    }
    
    /*
     * Simple Flushed Event Registry
     * MinecraftForge.EVENT_BUS.register(new NAME OF WORKSPACE);
     * 
     * Simple Flushed Command Registry
     * ClientCommandHandler.instance.registerCommand(new NAME OF WORKSPACE);
     */

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        System.out.println("-=-=-=-=-=-=-=-=-");
        System.out.println("Nate's Skyblock Mod");
        System.out.println("  Has Activated");
        System.out.println("-=-=-=-=-=-=-=-=-");
        //MinecraftForge.EVENT_BUS.register(new StopDropping());
        MinecraftForge.EVENT_BUS.register(new BasicListener());
        MinecraftForge.EVENT_BUS.register(new AntiNonEnchanted());
        ClientCommandHandler.instance.registerCommand(new Commands(this));
        // ACTIVATES MODCORE
        ModCoreInstaller.initializeModCore(Minecraft.getMinecraft().mcDataDir);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }

	public void openGUI() {
		this.guiOpen = true;
	}
	
	public String getMessage() {
		return EnumChatFormatting.BLUE + "Test Command";
	}
	
    public Config getConfig() {
        return config;
    }
    
}