package thexnator.furnituremod.api;

import java.util.Map;

import thexnator.furnituremod.handler.ConfigurationHandler;

public class RecipeRegistryComm extends RecipeAPI implements IRecipeRegistry
{

	private static RecipeRegistryComm furnitureRegister = null;
	private String modName;

	public static RecipeRegistryComm getInstance(String modName)
	{
		if (furnitureRegister == null)
		{
			furnitureRegister = new RecipeRegistryComm();
		}
		furnitureRegister.modName = modName;
		return furnitureRegister;
	}

	@Override
	public void registerRecipe(String type, RecipeVariables variables)
	{
		Map<String, Object> varMap = variables.getMap();
		
	if (type.equalsIgnoreCase("oven"))
		{
			if (RecipeConditions.hasOvenArgs(varMap))
			{
				addOvenRecipe(RecipeData.convertFrom(varMap), COMM);
			}
			else
			{
				if (ConfigurationHandler.api_debug)
				{
					RecipeUtil.printRequired(type, "Missing required variables", modName);
				}
			}
		}
	
	else if (type.equalsIgnoreCase("wardrobe"))
	{
		if (RecipeConditions.hasWardrobeArgs(varMap))
		{
			addWardrobeRecipe(RecipeData.convertFrom(varMap), COMM);
		}
		else
		{
			if (ConfigurationHandler.api_debug)
			{
				RecipeUtil.printRequired(type, "Missing required variables for " + type, modName);
			}
		}
	}
	
	else
	{
		if (ConfigurationHandler.api_debug)
		{
			System.err.println("## RecipeAPI Error Report ##");
			System.err.println("From Mod: " + modName);
			System.err.println("Description: " + " The mod '" + modName + "' is trying to add a non existing recipe type '" + type + "'.");
			System.err.println("Type: " + type + " (Unknown)");
		}
	}
	
	
	varMap.clear();
	varMap = null;
}

	@Override
	public void registerRecipe(RecipeType type, RecipeVariables variables) {
	registerRecipe(type.toString(), variables);
	}
}
