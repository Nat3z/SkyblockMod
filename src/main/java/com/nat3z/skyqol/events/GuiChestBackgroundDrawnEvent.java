package com.nat3z.skyqol.events;

import java.util.List;

import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.common.eventhandler.Event;
/**
 * This module and event was created by Dankers Skyblock Mod
 * @author Dankers
 */
public class GuiChestBackgroundDrawnEvent extends Event {
    public final GuiChest chest;
    public final String displayName;
    public final int chestSize;
    public final List<Slot> slots;

    public GuiChestBackgroundDrawnEvent(GuiChest chest, String displayName, int chestSize, List<Slot> slots) {
        this.chest = chest;
        this.displayName = displayName;
        this.chestSize = chestSize;
        this.slots = slots;
    }

}