package thexnator.furnituremod.api;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import thexnator.furnituremod.FurnitureMod;

public class Recipes {
	
	public static ArrayList<String> recipeData = new ArrayList<String>();
	
	public static ArrayList<RecipeData> localOvenRecipes = new ArrayList<RecipeData>();
	public static ArrayList<RecipeData> localWardrobeRecipes = new ArrayList<RecipeData>();
	
	public static ArrayList<RecipeData> commOvenRecipes = new ArrayList<RecipeData>();
	public static ArrayList<RecipeData> commWardrobeRecipes = new ArrayList<RecipeData>();

	public static ArrayList<RecipeData> remoteOvenRecipes = new ArrayList<RecipeData>();
	public static ArrayList<RecipeData> remoteWardrobeRecipes = new ArrayList<RecipeData>();

	
	public static RecipeData getOvenRecipeFromInput(ItemStack itemStack)
	{
		ArrayList<RecipeData> recipes = getRecipes("oven");
		for (int i = 0; i < recipes.size(); i++)
		{
			ItemStack validItemStack = recipes.get(i).getInput();
			if (validItemStack.getItem() == itemStack.getItem())
			{
				if (validItemStack.getItemDamage() == itemStack.getItemDamage())
				{
					return recipes.get(i);
				}
			}
		}
		return null;
	}
	
	public static RecipeData getWardrobeRecipeFromInput(ItemStack itemStack)
	{
		ArrayList<RecipeData> recipes = getRecipes("wardrobe");
		for (int i = 0; i < recipes.size(); i++)
		{
			ItemStack validItemStack = recipes.get(i).getInput();
			if (validItemStack.getItem() == itemStack.getItem())
			{
				return recipes.get(i);
			}
		}
		return null;
	}
	
	public static void updateDataList()
	{
		recipeData.clear();
		for (RecipeData data : localOvenRecipes)
		{
			recipeData.add("type=oven," + data.toString());
		}
		
		for (RecipeData data : localWardrobeRecipes)
		{
			recipeData.add("type=wardrobe," + data.toString());
		}
		
	}
	
	public static ArrayList<RecipeData> getRecipes(String type)
	{
		if (FurnitureMod.proxy.isSinglePlayer() |FurnitureMod.proxy.isDedicatedServer())
		{
			if (type.equalsIgnoreCase("oven"))
			{
				return localOvenRecipes;
			}
			
			else if (type.equalsIgnoreCase("wardrobe"))
			{
				return localWardrobeRecipes;
			}
			
		}
		else
		{
			if (type.equalsIgnoreCase("oven"))
			{
				return remoteOvenRecipes;
			}
			
			else if (type.equalsIgnoreCase("wardrobe"))
			{
				return remoteWardrobeRecipes;
			}
			
		}
			
			return new ArrayList<RecipeData>();
			
	}
		
		public static void addCommRecipesToLocal()
		{
			for (RecipeData data : commOvenRecipes)
			{
				localOvenRecipes.add(data);
			}
			
			for (RecipeData data : commWardrobeRecipes)
			{
				localWardrobeRecipes.add(data);
			}
			
		}
		
		public static void clearLocalRecipes()
		{
			localOvenRecipes.clear();
			localWardrobeRecipes.clear();
		}
		
		public static void clearRemoteRecipes()
		{
			remoteOvenRecipes.clear();
			remoteWardrobeRecipes.clear();
		}
		
		public static void clearCommRecipes()
		{
			commOvenRecipes.clear();
			commWardrobeRecipes.clear();
		}
		
		public static void clearAll()
		{
			clearLocalRecipes();
			clearRemoteRecipes();
			clearCommRecipes();
		}
	
}
