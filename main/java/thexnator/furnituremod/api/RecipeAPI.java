package thexnator.furnituremod.api;

import net.minecraft.item.ItemStack;

public class RecipeAPI
{
	public static final int LOCAL = 0;
	public static final int REMOTE = 1;
	public static final int COMM = 2;

	public static void addOvenRecipe(RecipeData itemData, int type)
	{
		switch (type)
		{
		case LOCAL:
			Recipes.localOvenRecipes.add(itemData);
			break;
		case REMOTE:
			Recipes.remoteOvenRecipes.add(itemData);
			break;
		case COMM:
			Recipes.commOvenRecipes.add(itemData);
			break;
		}
	}
	
	public static void addWardrobeRecipe(RecipeData itemData, int type)
	{
		switch (type)
		{
		case LOCAL:
			Recipes.localWardrobeRecipes.add(itemData);
			break;
		case REMOTE:
			Recipes.remoteWardrobeRecipes.add(itemData);
			break;
		case COMM:
			Recipes.commWardrobeRecipes.add(itemData);
			break;
		}
	}
	
	public static RecipeData getOvenRecipeFromInput(ItemStack itemStack)
	{
		return Recipes.getOvenRecipeFromInput(itemStack);
	}
	
	public static RecipeData getWardrobeRecipeFromInput(ItemStack itemStack)
	{
		return Recipes.getWardrobeRecipeFromInput(itemStack);
	}
	
}
