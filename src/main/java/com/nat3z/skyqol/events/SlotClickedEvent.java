package com.nat3z.skyqol.events;

import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * Mostly ALL of this code is from Danker's
 * https://github.com/bowser0000/SkyblockMod/
 * @author Danker's
 */
@Cancelable
public class SlotClickedEvent extends Event {
    public final GuiChest chest;
    public final IInventory inventory;
    public final String inventoryName;
    public final Slot slot;
    public final ItemStack item;

    public SlotClickedEvent(GuiChest chest, IInventory inventory, String inventoryName, Slot slot, ItemStack item) {
        this.chest = chest;
        this.inventory = inventory;
        this.inventoryName = inventoryName;
        this.slot = slot;
        this.item = item;
    }

    public SlotClickedEvent(GuiChest chest, IInventory inventory, String inventoryName, Slot slot) {
        this.chest = chest;
        this.inventory = inventory;
        this.inventoryName = inventoryName;
        this.slot = slot;
        item = null;
    }
}
