package thexnator.furnituremod.api;

import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeData
{

	/** ItemStack variables */
	private ItemStack input, output, currency;

	/**
	 * This method is used to set the input ItemStack. For instance, the Raw
	 * Beef before it gets cooked into Cooked Beef. Note: For MineBay, the input
	 * ItemStack is the item you are going to sell.
	 * 
	 * @param input
	 *            Sets the input ItemStack. E.g. Raw Beef
	 */
	public RecipeData setInput(ItemStack input)
	{
		this.input = input;
		return this;
	}

	/**
	 * This method is used to set the out ItemStack. For instance, its the
	 * Cooked Beef after its been cooked from Raw Beef. Note: For MineBay, you
	 * do not need to use this method at all. Please refer to setInput() and
	 * setCurrency() only.
	 * 
	 * @param output
	 *            Sets the output ItemStack. E.g. Cooked Beef
	 */
	public RecipeData setOutput(ItemStack output)
	{
		this.output = output;
		return this;
	}

	/**
	 * This method is used to set the currency in MineBay for the specific item.
	 * Note: For the Oven and Freezer, you do not need to use this method at
	 * all. Please refer to setInput() and setOutput() only.
	 * 
	 * @param currency
	 *            Sets the currency ItemStack. E.g. Gold Ingot
	 */

	/** Gets the input ItemStack */
	public ItemStack getInput()
	{
		return input;
	}

	/** Gets the output ItemStack */
	public ItemStack getOutput()
	{
		return output;
	}

	@Override
	public String toString()
	{
		String result = "";
		if (input != null)
		{
			String name = GameRegistry.findUniqueIdentifierFor(input.getItem()).toString();
			result += "input-item=" + name + ",input-amount=" + input.stackSize + ",input-metadata=" + input.getItemDamage() + ",";
		}
		if (output != null)
		{
			String name = GameRegistry.findUniqueIdentifierFor(output.getItem()).toString();
			result += "output-item=" + name + ",output-amount=" + output.stackSize + ",output-metadata=" + output.getItemDamage() + ",";
		}
		return result.substring(0, result.length() - 1);
	}
	
	public static RecipeData convertFrom(Map<String, Object> params)
	{
		RecipeData data = new RecipeData();
		if (params.containsKey("input"))
		{
			Object input = params.get("input");
			if (input instanceof ItemStack)
			{
				data.setInput((ItemStack) input);
			}
		}
		if (params.containsKey("output"))
		{
			Object output = params.get("output");
			if (output instanceof ItemStack)
			{
				data.setOutput((ItemStack) output);
			}
		}
		return data;
	}
}

