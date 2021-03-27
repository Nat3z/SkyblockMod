package me.nat3z;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	// This method is not for Usage in Skyblock Item Check and is only for switch/if statements
	public static String getItemType(ItemStack itemStack, Boolean LogDebugging) {
		String item = itemStack.getDisplayName().replaceAll(" ", "_").toUpperCase();
		ChatColor.stripColor(item);
		
		if (LogDebugging) {
			System.out.println("This method is not for Usage in Skyblock Item Check and is only for switch/if statements");
			System.out.println("-------------------------------------------------------------------------------------------");
			System.out.println("Original Name = " + itemStack.getDisplayName());
			System.out.println("Item Type Recieved = " + item);
			System.out.println("Debug mode = " + LogDebugging);
			System.out.println("-------------------------------------------------------------------------------------------");
		}
		
		return item;
	}
}
