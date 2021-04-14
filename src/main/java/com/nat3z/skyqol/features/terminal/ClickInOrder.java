package com.nat3z.skyqol.features.terminal;

import java.awt.Color;

import com.nat3z.skyqol.events.GuiChestBackgroundDrawnEvent;

import me.nat3z.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.Slot;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClickInOrder {

	private int lowestnum = 500000;
	private int lowesta = 500000;

	@SubscribeEvent
	public void MainRenderSolver(GuiChestBackgroundDrawnEvent event) {
				
			if (event.displayName.equals("Click in order!"))

            
				for (int sl = 9; sl < 26; sl++) {
					Slot slot = event.slots.get(sl);
					if (slot != null)
						if (slot.getStack() != null)
							if (slot.getStack().getDisplayName() != " " && slot.getStack().getDisplayName() != "" && slot.getStack().getDisplayName() != null ) {
								int slotnum = slot.getStack().stackSize;
								if (slotnum < lowestnum && slot.getStack().getItemDamage() != 13 && slot.getStack().getItemDamage() != 15) {
									lowestnum = slotnum;
									lowesta = slot.slotNumber;
								}
							}
				}
				if (lowestnum != 500000) {
					Utilities.showOnSlot(event.slots.size(), event.slots.get(lowesta).xDisplayPosition, event.slots.get(lowesta).yDisplayPosition, Color.green.getRGB());
				}
				
				lowestnum = 500000;
				lowesta = 500000;
	}
	
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onTooltip(ItemTooltipEvent event) {
        if (event.toolTip == null) return;

        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;

        if (mc.currentScreen instanceof GuiChest) {
			ContainerChest chest = (ContainerChest) Minecraft.getMinecraft().thePlayer.openContainer;
            String chestName = chest.getLowerChestInventory().getDisplayName().getUnformattedText();

            if (chestName.startsWith("Select all the") || chestName.startsWith("What starts with:") || chestName.startsWith("Click in order") ) {
                event.toolTip.clear();
            }
        }
    }
	
		
}