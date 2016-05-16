package thexnator.furnituremod.api;

import java.util.Map;

import net.minecraft.item.ItemStack;

public class RecipeConditions
{
	public static boolean hasOvenArgs(Map<String, Object> variables)
	{
		if (variables.get("input") == null)
			return false;
		if (!(variables.get("input") instanceof ItemStack))
			return false;
		if (variables.get("output") == null)
			return false;
		if (!(variables.get("output") instanceof ItemStack))
			return false;
		return true;
	}
	
	public static boolean hasWardrobeArgs(Map<String, Object> variables)
	{
		if (variables.get("input") == null)
			return false;
		if (!(variables.get("input") instanceof ItemStack))
			return false;
		return true;
	}
	
}