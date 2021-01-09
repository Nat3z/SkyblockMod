package com.nat3z.skyqol;

import java.io.File;

import club.sk1er.vigilance.Vigilant;
import club.sk1er.vigilance.data.Property;
import club.sk1er.vigilance.data.PropertyType;
import net.minecraftforge.common.config.Configuration;

@SuppressWarnings("unused")
public class Config extends Vigilant {
	
    @Property(
            type = PropertyType.SWITCH, name = "Stop Clicking on Unenchanted Items",
            description = "If your minion has a Super Compactor 3000, you will not click unenchanted items.",
            category = "Quality of Life", subcategory = "Quality of Life"
        )
        public static boolean stopclickenchant;

    @Property(
            type = PropertyType.SWITCH, name = "Dungeons Profit Check",
            description = "Checks the value of each item in Dungeons Chests",
            category = "Dungeons", subcategory = "Dungeons"
        )
        public static boolean dungeonsprofitcheck;
    /*@Property(
            type = PropertyType.SWITCH, name = "Stop Placing Flowers",
            description = "Doesn't Place Spirit Sceptre and Flower of Truth",
            category = "Dungeons", subcategory = "Dungeons"
        )
        public static boolean stopplacingflowers;
    @Property(
            type = PropertyType.SWITCH, name = "Toggle Teleport Pad Message",
            description = "Toggles the teleport pad message",
            category = "Chat", subcategory = "Island"
        )
        public static boolean stopteleportpad;
*/

    public Config() {
        super(new File("./config/nateskyblockmod.toml"));
        initialize();
    }
}
