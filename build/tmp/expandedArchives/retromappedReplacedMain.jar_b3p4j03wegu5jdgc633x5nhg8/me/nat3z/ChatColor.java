package me.nat3z;

import net.minecraft.util.StringUtils;

public class ChatColor {
	public static String PINK = "�d";
	public static String PURPLE = "�5";
	public static String DARK_RED = "�4";
	public static String RED = "�c";
	public static String GOLD = "�6";
	public static String AQUA = "�b";
	public static String GREEN = "�a";
	public static String DARK_GREEN = "�2";
	public static String DARK_BLUE = "�1";
	public static String BLUE = "�9";
	public static String BLACK = "�0";
	public static String GRAY = "�7";
	public static String DARK_GRAY = "�8";
	public static String YELLOW = "�e";
	public static String WHITE = "�f";

	public static String BOLD = "�l";
	public static String STRIKETHROUGH = "�m";
	public static String UNDERLINE = "�n";
	public static String ITALICS = "�o";
	public static String RESET = "�r";
	
	public static String stripColor(String string) {
		String result = StringUtils.func_76338_a(string);
		return result;
	}
	public static Boolean isFormatted(String string) {
		if (string.contains("�")) 
			return true;
		else
			return false;
	}

}
