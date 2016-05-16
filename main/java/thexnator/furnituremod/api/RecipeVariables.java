package thexnator.furnituremod.api;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;

public class RecipeVariables {
	
	private HashMap<String, Object> variables = new HashMap<String, Object>();
	
	public Map<String, Object> getMap() {
		return variables;
	}
	
	/**
	 * Adds a value to the recipe variables
	 * 
	 * @param variable
	 *            The type of variable
	 * @param object
	 *            The object to bind to the variable
	 * @return RecipeVariables instance
	 * 
	 * @see {@link RecipeVariables} for required variables
	 * */
	@Deprecated
	public RecipeVariables addValue(String variable, Object object) {
		variables.put(variable, object);
		return this;
	}

	/**
	 * Sets the input ItemStack for the recipe<br>
	 * <br>
	 * Valid for recipe type(s):<br> 
	 * CHOPPING_BOARD,<br> 
	 * DISHWASHER,<br>  
	 * FREEZER,<br>  
	 * MICROWAVE,<br>  
	 * MINEBAY,<br>  
	 * OVEN,<br>  
	 * PRINTER,<br>
	 * TOASTER,<br>    
	 * WASHING_MACHINE
	 * 
	 * @param input
	 *            The input ItemStack for the recipe
	 * @return Instance of RecipeVariables
	 */
	public RecipeVariables setInput(ItemStack input) {
		variables.put("input", input);
		return this;
	}
	
	/**
	 * Sets the output ItemStack for the recipe<br>
	 * <br>
	 * Valid for recipe type(s):<br> 
	 * CHOPPING_BOARD,<br> 
	 * DISHWASHER,<br>  
	 * FREEZER,<br>  
	 * MICROWAVE,<br>  
	 * MINEBAY,<br>  
	 * OVEN,<br>  
	 * PRINTER,<br>
	 * TOASTER,<br>  
	 * WASHING_MACHINE
	 * 
	 * @param output
	 *            The output ItemStack for the recipe
	 * @return Instance of RecipeVariables
	 */
	public RecipeVariables setOutput(ItemStack output) {
		variables.put("output", output);
		return this;
	}
}