package thexnator.furnituremod.api;

import java.util.ArrayList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import thexnator.furnituremod.handler.ConfigurationHandler;

public class RecipeRegistryRemote extends RecipeAPI
{
	private static RecipeRegistryRemote furnitureRegister = null;

	public static RecipeRegistryRemote getInstance()
	{
		if (furnitureRegister == null)
		{
			furnitureRegister = new RecipeRegistryRemote();
		}
		return furnitureRegister;
	}
	
	public void registerOvenRecipe(ItemStack input, ItemStack output)
	{
		addOvenRecipe(new RecipeData().setInput(input).setOutput(output), REMOTE);
	}
	
	public void registerWardrobeRecipe(ItemStack input)
	{
		addWardrobeRecipe(new RecipeData().setInput(input), REMOTE);
	}
	
	public static void registerOvenRecipe(Parser parser, int num)
	{
		String input_item = parser.getValue("input-item", null);
		String input_metadata = parser.getValue("input-metadata", "0");
		String output_item = parser.getValue("output-item", null);
		String output_metadata = parser.getValue("output-metadata", "0");
		String output_amount = parser.getValue("output-amount", "1");

		if (input_item != null)
		{
			if (output_item != null)
			{
				Item input = Item.getByNameOrId(input_item);
				Item output = Item.getByNameOrId(output_item);
				if (input != null)
				{
					if (output != null)
					{
						int i_metadata = 0;
						try
						{
							i_metadata = Integer.parseInt(input_metadata);
						}
						catch (NumberFormatException e)
						{
							if (ConfigurationHandler.api_debug)
							{
								RecipeUtil.printReport(parser, num, "input-metadata", "Could not parse the value '" + input_metadata + "' to an integer");
							}
							return;
						}

						int o_amount = 1;
						try
						{
							o_amount = Integer.parseInt(output_amount);
						}
						catch (NumberFormatException e)
						{
							if (ConfigurationHandler.api_debug)
							{
								RecipeUtil.printReport(parser, num, "output-amount", "Could not parse the value '" + output_amount + "' to an integer");
							}
							return;
						}

						int o_metadata = 0;
						try
						{
							o_metadata = Integer.parseInt(output_metadata);
						}
						catch (NumberFormatException e)
						{
							if (ConfigurationHandler.api_debug)
							{
								RecipeUtil.printReport(parser, num, "output-metadata", "Could not parse the value '" + output_metadata + "' to an integer");
							}
							return;
						}

						RecipeRegistryRemote.getInstance().registerOvenRecipe(new ItemStack(input, 1, i_metadata), new ItemStack(output, o_amount, o_metadata));
					}
					else
					{
						if (ConfigurationHandler.api_debug)
						{
							RecipeUtil.printReport(parser, num, "output-item", "The output-item '" + output_item + "' does not exist");
						}
					}
				}
				else
				{
					if (ConfigurationHandler.api_debug)
					{
						RecipeUtil.printReport(parser, num, "input-item", "The input-item '" + input_item + "' does not exist");
					}
				}
			}
			else
			{
				if (ConfigurationHandler.api_debug)
				{
					RecipeUtil.printMissing(parser, num, "output-item", "An output-item is required");
				}
			}
		}
		else
		{
			if (ConfigurationHandler.api_debug)
			{
				RecipeUtil.printMissing(parser, num, "input-item", "An input-item is required");
			}
		}
	}
	
	public static String parseSpaces(String name)
	{
		return name.replaceAll("_", " ");
	}
	
	public static String parseFormatting(String name)
	{
		name = name.replaceAll("&0", EnumChatFormatting.BLACK.toString());
		name = name.replaceAll("&1", EnumChatFormatting.DARK_BLUE.toString());
		name = name.replaceAll("&2", EnumChatFormatting.DARK_GREEN.toString());
		name = name.replaceAll("&3", EnumChatFormatting.DARK_AQUA.toString());
		name = name.replaceAll("&4", EnumChatFormatting.DARK_RED.toString());
		name = name.replaceAll("&5", EnumChatFormatting.DARK_PURPLE.toString());
		name = name.replaceAll("&6", EnumChatFormatting.GOLD.toString());
		name = name.replaceAll("&7", EnumChatFormatting.GRAY.toString());
		name = name.replaceAll("&8", EnumChatFormatting.DARK_GRAY.toString());
		name = name.replaceAll("&9", EnumChatFormatting.BLUE.toString());
		name = name.replaceAll("&a", EnumChatFormatting.GREEN.toString());
		name = name.replaceAll("&b", EnumChatFormatting.AQUA.toString());
		name = name.replaceAll("&c", EnumChatFormatting.RED.toString());
		name = name.replaceAll("&d", EnumChatFormatting.LIGHT_PURPLE.toString());
		name = name.replaceAll("&e", EnumChatFormatting.YELLOW.toString());
		name = name.replaceAll("&f", EnumChatFormatting.WHITE.toString());
		name = name.replaceAll("&k", EnumChatFormatting.OBFUSCATED.toString());
		name = name.replaceAll("&l", EnumChatFormatting.BOLD.toString());
		name = name.replaceAll("&m", EnumChatFormatting.STRIKETHROUGH.toString());
		name = name.replaceAll("&n", EnumChatFormatting.UNDERLINE.toString());
		name = name.replaceAll("&o", EnumChatFormatting.ITALIC.toString());
		name = name.replaceAll("&r", EnumChatFormatting.RESET.toString());
		return name;
	}
	
	public static void registerWardrobeRecipe(Parser parser, int num)
	{
		String input_item = parser.getValue("input-item", null);

		if (input_item != null)
		{
			Item input = Item.getByNameOrId(input_item);
			if (input != null)
			{
				RecipeRegistryRemote.getInstance().registerWardrobeRecipe(new ItemStack(input));
			}
			else
			{
				if (ConfigurationHandler.api_debug)
				{
					RecipeUtil.printReport(parser, num, "input-item", "The input-item '" + input_item + "' does not exist");
				}
			}
		}
		else
		{
			if (ConfigurationHandler.api_debug)
			{
				RecipeUtil.printMissing(parser, num, "input-item", "An input-item is required");
			}
		}
	}
	
	public static void registerRemoteRecipes(ArrayList<String> data)
	{
		if (data.size() > 0)
		{
			System.out.println("RecipeAPI (Remote): Syncing " + data.size() + " recipes from the server.");
		}
		Parser parser = Parser.getInstance();
		for (int i = 0; i < data.size(); i++)
		{
			System.out.println(data.get(i));
			parser.parseLine(data.get(i), false);
			String type = parser.getValue("type", null);

			int realNum = i + 1;

			if (type != null)
			{
				if (type.equalsIgnoreCase("oven"))
				{
					registerOvenRecipe(parser, realNum);
				}
				
				else if (type.equalsIgnoreCase("wardrobe"))
				{
					registerWardrobeRecipe(parser, realNum);
				}
				
			}
			else
			{
				if (ConfigurationHandler.api_debug)
				{
					RecipeUtil.printReport(parser, realNum, "type", "The recipe type '" + type + "' does not exist");
				}
			}
		}
	}
}

