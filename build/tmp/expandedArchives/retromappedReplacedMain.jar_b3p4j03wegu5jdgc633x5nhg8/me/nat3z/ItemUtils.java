package me.nat3z;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ItemUtils {

	public static List<String> getItemLore(ItemStack itemStack) {
		if(itemStack.func_77942_o() && itemStack.func_77978_p().func_150297_b("display", 10)) {
			NBTTagCompound display = itemStack.func_77978_p().func_74775_l("display");
			
			if (display.func_150297_b("Lore", 9)) {
				NBTTagList lore = display.func_150295_c("Lore", 8);
				
				List<String> loreAsList = new ArrayList<String>();
				for (int lineNumber = 0; lineNumber < lore.func_74745_c(); lineNumber++) {
					loreAsList.add(lore.func_150307_f(lineNumber));
				}
				
				return loreAsList;
			}
		}
		return Collections.emptyList();
	}
	// This method is not for Usage in Skyblock Item Check and is only for switch/if statements
	public static String getItemType(ItemStack itemStack, Boolean LogDebugging) {
		String item = itemStack.func_82833_r().replaceAll(" ", "_").toUpperCase();
		ChatColor.stripColor(item);
		
		if (LogDebugging) {
			System.out.println("This method is not for Usage in Skyblock Item Check and is only for switch/if statements");
			System.out.println("-------------------------------------------------------------------------------------------");
			System.out.println("Original Name = " + itemStack.func_82833_r());
			System.out.println("Item Type Recieved = " + item);
			System.out.println("Debug mode = " + LogDebugging);
			System.out.println("-------------------------------------------------------------------------------------------");
		}
		
		return item;
	}
}
