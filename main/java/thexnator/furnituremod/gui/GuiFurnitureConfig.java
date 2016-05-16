package thexnator.furnituremod.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import thexnator.furnituremod.handler.ConfigurationHandler;

public class GuiFurnitureConfig extends GuiConfig
{
	public GuiFurnitureConfig(GuiScreen parent)
	{
		super(parent, new ConfigElement(ConfigurationHandler.config.getCategory(ConfigurationHandler.CATEGORY_SETTINGS)).getChildElements(), "fm", false, false, GuiConfig.getAbridgedConfigPath(ConfigurationHandler.config.toString()));
	}
}
