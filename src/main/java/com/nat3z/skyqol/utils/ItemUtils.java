package com.nat3z.skyqol.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ItemUtils {

	public static List<String> getItemLore(ItemStack itemStack) {
		if(itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("display", 10)) {
			NBTTagCompound display = itemStack.getTagCompound().getCompoundTag("display");
			
			if (display.hasKey("Lore", 9)) {
				NBTTagList lore = display.getTagList("Lore", 8);
				
				List<String> loreAsList = new ArrayList<String>();
				for (int lineNumber = 0; lineNumber < lore.tagCount(); lineNumber++) {
					loreAsList.add(lore.getStringTagAt(lineNumber));
				}
				
				return loreAsList;
			}
		}
		return Collections.emptyList();
	}
	
	public static ItemStack getItemInHand() {
		return Minecraft.getMinecraft().thePlayer.getItemInUse();
	}
	
	public static List<ItemStack> getCurrentHotbar() {
		List<ItemStack> stack = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			stack.add(Minecraft.getMinecraft().thePlayer.inventory.getStackInSlot(i));
		}
		
		return stack;
	}
	
	public static boolean isItemRarity(String legIepicIrareIuncommonIcommon, ItemStack itemStack) {
		List<String> lore = getItemLore(itemStack);
		if (lore.isEmpty())
			return false;
		
		
		if (legIepicIrareIuncommonIcommon.equals("leg") && lore.contains("LEG"))
			return true;
		else if (legIepicIrareIuncommonIcommon.equals("epic") && lore.contains("EPIC"))
			return true;
		else if (legIepicIrareIuncommonIcommon.equals("rare") && lore.contains("RARE"))
			return true;
		else if (legIepicIrareIuncommonIcommon.equals("uncommon") && lore.contains("UNCOMMON"))
			return true;
		else if (legIepicIrareIuncommonIcommon.equals("common") && lore.contains("COMMON"))
			return true;
		
		return false;
	}
	/**
	 * Made By BiscuitDevelopment for SkyblockAddons
	 * @author BiscuitDevelopment
	 * 
	 * @param item
	 * @return
	 */
    public static String getItemType(ItemStack item) {
        if (item == null) {
            throw new NullPointerException("Item cannot be null.");
        }
        else if (!item.hasTagCompound()) {
            return null;
        }

        NBTTagCompound skyBlockData = item.getSubCompound("ExtraAttributes", false);

        if (skyBlockData != null) {
            String itemId = skyBlockData.getString("id");

            if (!itemId.equals("")) {
                return itemId;
            }
        }

        return null;
    }
}
