package thexnator.furnituremod.handler;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {

	public static Configuration config;

	public static final String CATEGORY_RECIPE_SETTINGS = "recipe-settings";
	public static final String CATEGORY_API = "recipe-api";
	public static final String CATEGORY_SETTINGS = "settings";

	public static String[] items = {};
	public static boolean canDisplay = true;
	public static boolean hasDisplayedOnce = false;
	public static boolean api_debug = true;
	
	public static boolean oven_1 = true, oven_2 = true, oven_3 = true, oven_4 = true, oven_5 = true, oven_6 = true, oven_7 = true;
	
	public static boolean wash_1 = true, wash_2 = true, wash_3 = true, wash_4 = true, wash_5 = true, wash_6 = true, wash_7 = true, wash_8 = true, wash_9 = true, wash_10 = true;
	public static boolean wash_11 = true, wash_12 = true, wash_13 = true, wash_14 = true, wash_15 = true, wash_16 = true, wash_17 = true, wash_18 = true, wash_19 = true, wash_20 = true;

	public static void init(File file)
	{
		if (config == null)
		{
			config = new Configuration(file);
			loadConfig(false);
		}
	}

	public static void loadConfig(boolean shouldChange)
	{
		
		if (config.hasChanged() && shouldChange)
		{
			
		}
		config.save();
	}

	@SuppressWarnings("unused")
	private static void updateEnabledRecipes()
	{
		oven_1 = config.getBoolean("oven-1", CATEGORY_RECIPE_SETTINGS, oven_1, "Beef -> Cooked Beef");
		oven_2 = config.getBoolean("oven-2", CATEGORY_RECIPE_SETTINGS, oven_2, "Porkchop -> Cooked Porkchop");
		oven_3 = config.getBoolean("oven-3", CATEGORY_RECIPE_SETTINGS, oven_3, "Potato -> Cooked Potato");
		oven_4 = config.getBoolean("oven-4", CATEGORY_RECIPE_SETTINGS, oven_4, "Chicken -> Cooked Chicken");
		oven_5 = config.getBoolean("oven-5", CATEGORY_RECIPE_SETTINGS, oven_5, "Raw Fish -> Cooked Fish");
		oven_6 = config.getBoolean("oven-6", CATEGORY_RECIPE_SETTINGS, oven_6, "Raw Salmon -> Cooked Salmon");
		
		wash_1 = config.getBoolean("wardrobe-1", CATEGORY_RECIPE_SETTINGS, wash_1, "Leather Helmet");
		wash_2 = config.getBoolean("wardrobe-2", CATEGORY_RECIPE_SETTINGS, wash_2, "Leather Chestplate");
		wash_3 = config.getBoolean("wardrobe-3", CATEGORY_RECIPE_SETTINGS, wash_3, "Leather Leggings");
		wash_4 = config.getBoolean("wardrobe-4", CATEGORY_RECIPE_SETTINGS, wash_4, "Leather Boots");
		wash_5 = config.getBoolean("wardrobe-5", CATEGORY_RECIPE_SETTINGS, wash_5, "Chainmail Helmet");
		wash_6 = config.getBoolean("wardrobe-6", CATEGORY_RECIPE_SETTINGS, wash_6, "Chainmail Chestplate");
		wash_7 = config.getBoolean("wardrobe-7", CATEGORY_RECIPE_SETTINGS, wash_7, "Chainmail Leggings");
		wash_8 = config.getBoolean("wardrobe-8", CATEGORY_RECIPE_SETTINGS, wash_8, "Chainmail Boots");
		wash_9 = config.getBoolean("wardrobe-9", CATEGORY_RECIPE_SETTINGS, wash_9, "Iron Helmet");
		wash_10 = config.getBoolean("wardrobe-10", CATEGORY_RECIPE_SETTINGS, wash_10, "Iron Chestplate");
		wash_11 = config.getBoolean("wardrobe-11", CATEGORY_RECIPE_SETTINGS, wash_11, "Iron Leggings");
		wash_12 = config.getBoolean("wardrobe-12", CATEGORY_RECIPE_SETTINGS, wash_12, "Iron Boots");
		wash_13 = config.getBoolean("wardrobe-13", CATEGORY_RECIPE_SETTINGS, wash_13, "Golden Helmet");
		wash_14 = config.getBoolean("wardrobe-14", CATEGORY_RECIPE_SETTINGS, wash_14, "Golden Chestplate");
		wash_15 = config.getBoolean("wardrobe-15", CATEGORY_RECIPE_SETTINGS, wash_15, "Golden Leggings");
		wash_16 = config.getBoolean("wardrobe-16", CATEGORY_RECIPE_SETTINGS, wash_16, "Golden Boots");
		wash_17 = config.getBoolean("wardrobe-17", CATEGORY_RECIPE_SETTINGS, wash_17, "Diamond Helmet");
		wash_18 = config.getBoolean("wardrobe-18", CATEGORY_RECIPE_SETTINGS, wash_18, "Diamond Chestplate");
		wash_19 = config.getBoolean("wardrobe-19", CATEGORY_RECIPE_SETTINGS, wash_19, "Diamond Leggings");
		wash_20 = config.getBoolean("wardrobe-20", CATEGORY_RECIPE_SETTINGS, wash_20, "Diamond Boots");
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs)
	{
		if (eventArgs.modID.equals("fm"))
		{
			loadConfig(true);
		}
	}
}
	
