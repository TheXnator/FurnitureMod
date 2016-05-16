package thexnator.furnituremod.api;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thexnator.furnituremod.handler.ConfigurationHandler;
import thexnator.furnituremod.init.FurnitureItems;

public class RecipeRegistry extends RecipeAPI
{

	private static RecipeRegistry furnitureRegister = null;

	public static RecipeRegistry getInstance()
	{
		if (furnitureRegister == null)
		{
			furnitureRegister = new RecipeRegistry();
		}
		return furnitureRegister;
	}
	
	public void registerOvenRecipe(ItemStack input, ItemStack output)
	{
		addOvenRecipe(new RecipeData().setInput(input).setOutput(output), LOCAL);
	}
	
	public void registerWardrobeRecipe(ItemStack input)
	{
		addWardrobeRecipe(new RecipeData().setInput(input), LOCAL);
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

						RecipeRegistry.getInstance().registerOvenRecipe(new ItemStack(input, 1, i_metadata), new ItemStack(output, o_amount, o_metadata));
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
	
	public static void registerWardrobeRecipe(Parser parser, int num)
	{
		String input_item = parser.getValue("input-item", null);

		if (input_item != null)
		{
			Item input = Item.getByNameOrId(input_item);
			if (input != null)
			{
				RecipeRegistry.getInstance().registerWardrobeRecipe(new ItemStack(input));
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

	
	public static void registerConfigRecipes()
	{
		if (ConfigurationHandler.items.length > 0 && ConfigurationHandler.api_debug)
		{
			System.out.println("RecipeAPI (Configuration): Registering " + ConfigurationHandler.items.length + " recipes from the config.");
		}
		Parser parser = Parser.getInstance();
		for (int i = 0; i < ConfigurationHandler.items.length; i++)
		{
			parser.parseLine(ConfigurationHandler.items[i], true);
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

		public static void registerDefaultRecipes()
		{
			if (ConfigurationHandler.oven_1)
				RecipeRegistry.getInstance().registerOvenRecipe(new ItemStack(Items.beef), new ItemStack(Items.cooked_beef));
			if (ConfigurationHandler.oven_2)
				RecipeRegistry.getInstance().registerOvenRecipe(new ItemStack(Items.porkchop), new ItemStack(Items.cooked_porkchop));
			if (ConfigurationHandler.oven_3)
				RecipeRegistry.getInstance().registerOvenRecipe(new ItemStack(Items.potato), new ItemStack(Items.baked_potato));
			if (ConfigurationHandler.oven_4)
				RecipeRegistry.getInstance().registerOvenRecipe(new ItemStack(Items.chicken), new ItemStack(Items.cooked_chicken));
			if (ConfigurationHandler.oven_5)
				RecipeRegistry.getInstance().registerOvenRecipe(new ItemStack(Items.fish, 1, 0), new ItemStack(Items.cooked_fish, 1, 0));
			if (ConfigurationHandler.oven_6)
				RecipeRegistry.getInstance().registerOvenRecipe(new ItemStack(Items.fish, 1, 1), new ItemStack(Items.cooked_fish, 1, 1));
			
			if (ConfigurationHandler.wash_1)
				RecipeRegistry.getInstance().registerWardrobeRecipe(new ItemStack(Items.leather_helmet));
			if (ConfigurationHandler.wash_2)
				RecipeRegistry.getInstance().registerWardrobeRecipe(new ItemStack(Items.leather_chestplate));
			if (ConfigurationHandler.wash_3)
				RecipeRegistry.getInstance().registerWardrobeRecipe(new ItemStack(Items.leather_leggings));
			if (ConfigurationHandler.wash_4)
				RecipeRegistry.getInstance().registerWardrobeRecipe(new ItemStack(Items.leather_boots));
			if (ConfigurationHandler.wash_5)
				RecipeRegistry.getInstance().registerWardrobeRecipe(new ItemStack(Items.chainmail_helmet));
			if (ConfigurationHandler.wash_6)
				RecipeRegistry.getInstance().registerWardrobeRecipe(new ItemStack(Items.chainmail_chestplate));
			if (ConfigurationHandler.wash_7)
				RecipeRegistry.getInstance().registerWardrobeRecipe(new ItemStack(Items.chainmail_leggings));
			if (ConfigurationHandler.wash_8)
				RecipeRegistry.getInstance().registerWardrobeRecipe(new ItemStack(Items.chainmail_boots));
			if (ConfigurationHandler.wash_9)
				RecipeRegistry.getInstance().registerWardrobeRecipe(new ItemStack(Items.iron_helmet));
			if (ConfigurationHandler.wash_10)
				RecipeRegistry.getInstance().registerWardrobeRecipe(new ItemStack(Items.iron_chestplate));
			if (ConfigurationHandler.wash_11)
				RecipeRegistry.getInstance().registerWardrobeRecipe(new ItemStack(Items.iron_leggings));
			if (ConfigurationHandler.wash_12)
				RecipeRegistry.getInstance().registerWardrobeRecipe(new ItemStack(Items.iron_boots));
			if (ConfigurationHandler.wash_13)
				RecipeRegistry.getInstance().registerWardrobeRecipe(new ItemStack(Items.golden_helmet));
			if (ConfigurationHandler.wash_14)
				RecipeRegistry.getInstance().registerWardrobeRecipe(new ItemStack(Items.golden_chestplate));
			if (ConfigurationHandler.wash_15)
				RecipeRegistry.getInstance().registerWardrobeRecipe(new ItemStack(Items.golden_leggings));
			if (ConfigurationHandler.wash_16)
				RecipeRegistry.getInstance().registerWardrobeRecipe(new ItemStack(Items.golden_boots));
			if (ConfigurationHandler.wash_17)
				RecipeRegistry.getInstance().registerWardrobeRecipe(new ItemStack(Items.diamond_helmet));
			if (ConfigurationHandler.wash_18)
				RecipeRegistry.getInstance().registerWardrobeRecipe(new ItemStack(Items.diamond_chestplate));
			if (ConfigurationHandler.wash_19)
				RecipeRegistry.getInstance().registerWardrobeRecipe(new ItemStack(Items.diamond_leggings));
			if (ConfigurationHandler.wash_20)
				RecipeRegistry.getInstance().registerWardrobeRecipe(new ItemStack(Items.diamond_boots));

		}
	}
			
