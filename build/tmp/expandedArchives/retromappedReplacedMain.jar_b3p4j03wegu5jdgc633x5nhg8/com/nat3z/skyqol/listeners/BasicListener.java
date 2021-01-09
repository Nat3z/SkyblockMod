package com.nat3z.skyqol.listeners;

import com.nat3z.skyqol.Config;
import com.nat3z.skyqol.Main;

import me.nat3z.ChatColor;
import me.nat3z.ItemUtils;
import me.nat3z.TextDraw;
import me.nat3z.Utilities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringUtils;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BasicListener {
	
    private Minecraft mc;
    @SubscribeEvent
    public void onTick(ItemTooltipEvent event) {
    	if (!Config.dungeonsprofitcheck)
    		return;
    	
    	this.mc = Minecraft.func_71410_x();
    	EntityPlayerSP p = mc.field_71439_g;
    	
    	if(event.itemStack.func_82833_r().toLowerCase().contains("open reward chest")) {
    		
    		double money = 0;
    		String slotinv11 = p.field_71070_bA.func_75139_a(11).func_75211_c().func_82833_r().toString().toLowerCase();
    		slotinv11 = StringUtils.func_76338_a(slotinv11);
    		String slotinv12 = p.field_71070_bA.func_75139_a(12).func_75211_c().func_82833_r().toString().toLowerCase();
    		slotinv12 = StringUtils.func_76338_a(slotinv12);
    		String slotinv13 = p.field_71070_bA.func_75139_a(13).func_75211_c().func_82833_r().toString().toLowerCase();
    		slotinv13 = StringUtils.func_76338_a(slotinv13);
    		
    		String book11 = ItemUtils.getItemLore(p.field_71070_bA.func_75139_a(11).func_75211_c()).toString().toLowerCase();
    		book11 = StringUtils.func_76338_a(book11);
    		String book12 = ItemUtils.getItemLore(p.field_71070_bA.func_75139_a(12).func_75211_c()).toString().toLowerCase();
    		book12 = StringUtils.func_76338_a(book12);
    		String book13 = ItemUtils.getItemLore(p.field_71070_bA.func_75139_a(13).func_75211_c()).toString().toLowerCase();
    		book13 = StringUtils.func_76338_a(book13);

    		if(slotinv11.contains("wither ches") || slotinv12.contains("wither ches") || slotinv13.contains("wither ches"))
    			money = money + 35000000.0;
    		if(slotinv11.contains("wither leg") || slotinv12.contains("wither leg") || slotinv13.contains("wither leg"))
    			money = money + 5000000.0;
    		if(slotinv11.contains("wither boot") || slotinv12.contains("wither boot") || slotinv13.contains("wither boot"))
    			money = money + 1500000.0;
    		if(slotinv11.contains("wither helm") || slotinv12.contains("wither helm") || slotinv13.contains("wither helm"))
    			money = money + 2300000.0;
    		if(slotinv11.contains("hand") || slotinv12.contains("hand") || slotinv13.contains("hand"))
    			money = money + 300000000.0;
    		if(slotinv11.contains("precur") || slotinv12.contains("precur") || slotinv13.contains("precur"))
    			money = money + 643000.0;
    		if(slotinv11.contains("wither b") || slotinv12.contains("wither b") || slotinv13.contains("wither b"))
    			money = money + 2300000.0;
    		if(book11.contains("rej") || book12.contains("rej") || book13.contains("rej"))
    			money = money + 30000.0;
    		if(slotinv11.contains("shadow war") || slotinv12.contains("shadow war") || slotinv13.contains("shadow war"))
    			money = money + 95000000.0;
    		if(slotinv11.contains("wither shiel") || slotinv12.contains("wither shiel") || slotinv13.contains("wither shiel"))
    			money = money + 73000000.0;
    		if(slotinv11.contains("imp") || slotinv12.contains("imp") || slotinv13.contains("imp"))
    			money = money + 80000000.0;
    		if(slotinv11.contains("dark or") || slotinv12.contains("dark or") || slotinv13.contains("dark or"))
    			money = money + 310000.0;
    		if(slotinv11.contains("shadow assassin bo") || slotinv12.contains("shadow assassin bo") || slotinv13.contains("shadow assassin bo"))
    			money = money + 1200000.0;
    		if(slotinv11.contains("shadow assassin le") || slotinv12.contains("shadow assassin le") || slotinv13.contains("shadow assassin le"))
    			money = money + 4000000.0;
    		if(slotinv11.contains("shadow assassin chest") || slotinv12.contains("shadow assassin chest") || slotinv13.contains("shadow assassin chest"))
    			money = money + 25000000.0;
    		if(slotinv11.contains("shadow assassin he") || slotinv12.contains("shadow assassin he") || slotinv13.contains("shadow assassin he"))
    			money = money + 1800000.0;
    		if(slotinv11.contains("liv") || slotinv12.contains("liv") || slotinv13.contains("liv"))
    			money = money + 9000000.0;
    		if(slotinv11.contains("shadow fur") || slotinv12.contains("shadow fur") || slotinv13.contains("shadow fur"))
    			money = money + 13000000.0;
    		if(slotinv11.contains("warped st") || slotinv12.contains("warped st") || slotinv13.contains("warped st"))
    			money = money + 4500000.0;
    		if(slotinv11.contains("last bre") || slotinv12.contains("last bre") || slotinv13.contains("last bre"))
    			money = money + 8500000.0;
    		
    		if(slotinv11.contains("giant to") || slotinv12.contains("giant to") || slotinv13.contains("giant to"))
    			money = money + 420000.0;
    		if(slotinv11.contains("ancient r") || slotinv12.contains("ancient r") || slotinv13.contains("ancient r"))
    			money = money + 1000000.0;
    		if(slotinv11.contains("necromancer sw") || slotinv12.contains("necromancer sw") || slotinv13.contains("necromancer sw"))
    			money = money + 6000000.0;
    		if(slotinv11.contains("necromancer lord h") || slotinv12.contains("necromancer lord h") || slotinv13.contains("necromancer lord h"))
    			money = money + 590000.0;
    		if(slotinv11.contains("necromancer lord b") || slotinv12.contains("necromancer lord b") || slotinv13.contains("necromancer lord b"))
    			money = money + 1000000.0;
    		if(slotinv11.contains("necromancer lord l") || slotinv12.contains("necromancer lord l") || slotinv13.contains("necromancer lord l"))
    			money = money + 3600000.0;
    		if(slotinv11.contains("necromancer lord c") || slotinv12.contains("necromancer lord c") || slotinv13.contains("necromancer lord c"))
    			money = money + 24000000.0;
    		if(slotinv11.contains("precursor e") || slotinv12.contains("precursor e") || slotinv13.contains("precursor e"))
    			money = money + 22000000.0;
    		if(slotinv11.contains("giant's s") || slotinv12.contains("giant's s") || slotinv13.contains("giant's s"))
    			money = money + 22000000.0;

    		if(slotinv11.contains("spirit bon") || slotinv12.contains("spirit bon") || slotinv13.contains("spirit bon"))
    			money = money + 4300000.0;

    		if(slotinv11.contains("spirit wi") || slotinv12.contains("spirit wi") || slotinv13.contains("spirit wi"))
    			money = money + 3000000.0;
    		if(slotinv11.contains("spirit sw") || slotinv12.contains("spirit sw") || slotinv13.contains("spirit sw"))
    			money = money + 2500000.0;
    		
    		if(slotinv11.contains("adaptive h") || slotinv12.contains("adaptive h") || slotinv13.contains("adaptive h"))
    			money = money + 780000.0;
    		if(slotinv11.contains("adaptive c") || slotinv12.contains("adaptive c") || slotinv13.contains("adaptive c"))
    			money = money + 3500000.0;
    		if(slotinv11.contains("adaptive l") || slotinv12.contains("adaptive l") || slotinv13.contains("adaptive l"))
    			money = money + 1000000.0;
    		if(slotinv11.contains("adaptive b") || slotinv12.contains("adaptive b") || slotinv13.contains("adaptive b"))
    			money = money + 220000.0;
    		if(slotinv11.contains("adaptive s") || slotinv12.contains("adaptive s") || slotinv13.contains("adaptive s"))
    			money = money + 900000.0;
    		
    		if(slotinv11.contains("scarf") || slotinv12.contains("scarf") || slotinv13.contains("scarf"))
    			money = money + 429000.0;
    		
    		if(slotinv11.contains("bonzo's st") || slotinv12.contains("bonzo's st") || slotinv13.contains("bonzo's st"))
    			money = money + 2000000.0;
    		if(slotinv11.contains("bonzo's m") || slotinv12.contains("bonzo's m") || slotinv13.contains("bonzo's m"))
    			money = money + 420000.0;
    		
    		if(slotinv11.contains("recom") || slotinv12.contains("recom") || slotinv13.contains("recom"))
    			money = money + 5200000.0;
    		if(slotinv11.contains("fuming") || slotinv12.contains("fuming") || slotinv13.contains("fuming"))
    			money = money + 1000000.0;
    		if(slotinv11.contains("hot po") || slotinv12.contains("hot po") || slotinv13.contains("hot po"))
    			money = money + 52000.0;
    		// Dungeon Book
    		if(book11.contains("feather") || book12.contains("feather") || book13.contains("feather"))
    			money = money + 10000.0;
    		if(book11.contains("ultimate w") || book12.contains("ultimate w") || book13.contains("ultimate w"))
    			money = money + 113000.0;
    		if(book11.contains("wisdo") || book12.contains("wisdo") || book13.contains("wisdo"))
    			money = money + 50000.0;
    		if(book11.contains("comb") || book12.contains("comb") || book13.contains("comb"))
    			money = money + 30000.0;
    		if(book11.contains("soul e") || book12.contains("soul e") || book13.contains("soul e"))
    			money = money + 1000000.0;
    		if(book11.contains("ultimate jer") || book12.contains("ultimate jer") || book13.contains("ultimate jer"))
    			money = money + 10000.0;
    		if(book11.contains("bank") || book12.contains("bank") || book13.contains("bank"))
    			money = money + 2.0;
    		if(book11.contains("overload") || book12.contains("overload") || book13.contains("overload"))
    			money = money + 940000.0;
    		if(book11.contains("swar") || book12.contains("swar") || book13.contains("swar"))
    			money = money + 530000.0;
    		if(book11.contains("infin") || book12.contains("infin") || book13.contains("infin"))
    			money = money + 10000.0;
    		/*
    		if(slotinv11.contains("✪✪✪✪✪"))
    			money = money + 600000.0;
    		else if(slotinv11.contains("✪✪✪✪"))
    			money = money + 561000.0;
    		else if(slotinv11.contains("✪✪✪"))
    			money = money + 315000.0;
    		else if(slotinv11.contains("✪✪"))
    			money = money + 150000.0;
    		else if(slotinv11.contains("✪"))
    			money = money + 54000.0;
    		*/
    		String toolTip = event.itemStack.func_77978_p().func_74775_l("display").func_150295_c("Lore", 8).func_150307_f(6).replaceAll(",", "").replace("Coins", "").replaceAll(" ", "").toString();
    		toolTip = StringUtils.func_76338_a(toolTip);
    		if (!toolTip.toLowerCase().contains("free"))
    			money = money - Integer.parseInt(toolTip);
    		    		
			TextDraw draw = new TextDraw();
		
    		if (money < 0) {
    			draw.drawText(EnumChatFormatting.RED + "Profit: " + money, 8, 10, 4210752);
    			//inv.getDisplayName().appendText(" " + EnumChatFormatting.RED + "Profit: -" + money);
    		}
    		else if (money > 0) {
    			draw.drawText(EnumChatFormatting.GREEN + "Profit: +" + money, 8, 10, 4210752);
    			//inv.getDisplayName().appendText(" " + EnumChatFormatting.GREEN + "Profit: +" + money);
    		}
    		else {
    			draw.drawText(EnumChatFormatting.GRAY + "Profit: No Profit", 8, 10, 4210752);
    			//inv.getDisplayName().appendText(" " + EnumChatFormatting.GRAY + "Profit: We could not find any information about this item! It must be rare!");
    		}
    	}

    	
    }
}