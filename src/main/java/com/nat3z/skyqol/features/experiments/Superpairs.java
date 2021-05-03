package com.nat3z.skyqol.features.experiments;

import java.util.List;

import org.lwjgl.input.Mouse;

import com.nat3z.skyqol.Main;
import com.nat3z.skyqol.config.Config;
import com.nat3z.skyqol.config.Feature;
import com.nat3z.skyqol.events.GuiChestBackgroundDrawnEvent;
import com.nat3z.skyqol.events.SlotClickedEvent;
import com.nat3z.skyqol.utils.ItemUtils;
import com.nat3z.skyqol.utils.Utilities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/** color reveal slot shit from danker's
 * @author Danker's
**/
public class Superpairs {
	static ItemStack[] cards = new ItemStack[100];
	/**
	 * GetPairs was yoinked from Danker's Skyblock Mod.
	 * I'M SORRY I TRIED TO MAKE IT BY MYSELF BUT IT JUST DOESN'T WORK
	 * @author Danker's
	 * 
	 * @param event
	 */
	@SubscribeEvent
	public void getPairs(ClientTickEvent event) {
		if (event.phase != Phase.START) return;
		
		Minecraft mc = Minecraft.getMinecraft();
		
		if (mc.currentScreen instanceof GuiChest && Main.isHypixel() && Main.isOnSkyblock()) {
			ContainerChest chest = (ContainerChest) mc.thePlayer.openContainer;
			List<Slot> slots = ((GuiChest) mc.currentScreen).inventorySlots.inventorySlots;
			
            String chestName = chest.getLowerChestInventory().getDisplayName().getUnformattedText().trim();
            
            if (Feature.ExperimentSuperpairs && chestName.startsWith("Superpairs (") && Config.modules.get("superpairssolver")) {
            	for (int i = 0; i < 53; i++) {
            		ItemStack stack = slots.get(i).getStack();
            		
            		if (stack == null) continue;
            		
            		String itemName = stack.getDisplayName();
            		
            		if (Item.getIdFromItem(stack.getItem()) == 95 || Item.getIdFromItem(stack.getItem()) == 160) continue;
            		
                    if (itemName.contains("Instant Find") || itemName.contains("Gained +")) continue;
                    
                    if (cards[i] != null) continue;
                    
                    cards[i] = stack.copy().setStackDisplayName(itemName);
            	}
            }
		}
	}
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void doRenderShit(RenderTickEvent event) {
		if (Minecraft.getMinecraft().currentScreen instanceof GuiChest) {
			List<Slot> slots = Minecraft.getMinecraft().thePlayer.openContainer.inventorySlots;
            Container containerChest = ((GuiChest) Minecraft.getMinecraft().currentScreen).inventorySlots;

            GuiChest chest = (GuiChest) Minecraft.getMinecraft().currentScreen;
            IInventory inventory = ((ContainerChest) containerChest).getLowerChestInventory();
            String displayName = inventory.getDisplayName().getUnformattedText().trim();
            
			if (Feature.ExperimentSuperpairs && displayName.contains("Superpairs (") && Config.modules.get("superpairssolver")) {
				for (int i = 0; i < 53; i++) {
					ItemStack item = cards[i];
					if (item == null) continue;
					Utilities.renderItem(item, slots.get(i).xDisplayPosition, slots.get(i).yDisplayPosition, -100);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void changeClick(SlotClickedEvent event) {
		if (!Config.modules.get("superpairssolver")) return;

		if (Feature.ExperimentSuperpairs) {
			if (event.inventoryName.startsWith("Superpairs (")) {
				if (!Mouse.getEventButtonState()) return;
				event.setCanceled(true);
				
				Minecraft.getMinecraft().playerController.windowClick(event.chest.inventorySlots.windowId, event.slot.slotNumber, 2, 1, Minecraft.getMinecraft().thePlayer);
			}
		}
	}
	
	@SubscribeEvent
	public void onTooltip(ItemTooltipEvent event) {
		if (Feature.ExperimentSuperpairs && Main.isHypixel() && Main.isOnSkyblock()) {
			Minecraft mc = Minecraft.getMinecraft();
			
			if (mc.currentScreen instanceof GuiChest) {
	            ContainerChest chest = (ContainerChest) mc.thePlayer.openContainer;
	            IInventory inv = chest.getLowerChestInventory();
	            String displayName = inv.getDisplayName().getUnformattedText();
	            if (displayName.contains("Superpairs (")) {
	            	ItemStack item = event.itemStack;
	                if (Item.getIdFromItem(event.itemStack.getItem()) != 95) return;
	                if (item.getDisplayName().contains("Click any button") || item.getDisplayName().contains("Click a second button") || item.getDisplayName().contains("Next button is instantly rewarded")) {
	                    Slot slot = ((GuiChest) mc.currentScreen).getSlotUnderMouse();
	                    
	                    ItemStack stack = cards[slot.getSlotIndex()];
	                    
	                    if (stack == null) return;
	                    
	                    event.toolTip.add(" ");
	                    event.toolTip.add(stack.getDisplayName());
	                    event.toolTip.addAll(ItemUtils.getItemLore(stack));
	                }
	            }
			}
		}
	}
	
    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent event) {
        cards = new ItemStack[100];
    }
}
