package thexnator.furnituremod.api;

import java.util.ArrayList;

public class RecipeUtil
{
	public static void printRequired(String type, String desc, String modName)
	{
		System.err.println("## RecipeAPI Error Report ##");
		System.err.println("From Mod: " + modName);
		System.err.println("Type: " + type);
		System.err.println("Description: " + desc);
		System.err.println("Required Variables:");
		for (String var : getRequiredVariablesList(type))
		{
			System.err.println("- " + var);
		}
	}

	public static void printUnknownType(Parser parser, int num, String desc)
	{
		System.err.println("## RecipeAPI Error Report ##");
		System.err.println("Recipe Number: " + num);
		System.err.println("Unknown Type: " + parser.getValue("type", null));
		System.err.println("Description: " + desc);
		System.err.println("Variables: ");
		printVariables(parser, "");
	}

	public static void printMissing(Parser parser, int num, String missingKey, String desc)
	{
		System.err.println("## RecipeAPI Error Report ##");
		System.err.println("Recipe Number: " + num);
		System.err.println("Type: " + parser.getValue("type", null));
		System.err.println("Description: " + desc);
		System.err.println("Variables: ");
		System.err.println("- " + missingKey + ": (Missing)");
		printVariables(parser, "");
	}

	public static void printReport(Parser parser, int num, String erroredKey, String desc)
	{
		System.err.println("## RecipeAPI Error Report ##");
		System.err.println("Recipe Number: " + num);
		System.err.println("Type: " + parser.getValue("type", null) + (erroredKey.equalsIgnoreCase("type") ? " (Error)" : ""));
		System.err.println("Description: " + desc);
		System.err.println("Variables: ");
		printVariables(parser, erroredKey);
	}

	private static void printVariables(Parser parser, String erroredKey)
	{
		for (String key : parser.getMap().keySet())
		{
			if (!key.equalsIgnoreCase("type"))
			{
				String pre = "- " + key + ": " + parser.getValue(key, null);
				if (key.equalsIgnoreCase(erroredKey))
				{
					pre += " (Error)";
				}
				System.err.println(pre);
			}
		}
	}

	private static ArrayList<String> getRequiredVariablesList(String type)
	{
		ArrayList<String> vars = new ArrayList<String>();
		if (type.equalsIgnoreCase("oven") | type.equalsIgnoreCase("wardrobe"))
		{
			vars.add("input");
		}

		if (type.equalsIgnoreCase("oven") | type.equalsIgnoreCase("wardrobe"))
		{
			vars.add("output");
		}
		return vars;
	}
}
