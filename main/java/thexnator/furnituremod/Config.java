package thexnator.furnituremod;

import thexnator.furnituremod.config.properties.ConfigProperty;
import thexnator.furnituremod.config.properties.OnLineModifiable;

public class Config 
{
	@OnLineModifiable
	@ConfigProperty(category = "additional", name = "disableMobNames", comment = "List any mob names you want disabled on the server")
	public static String[] disableMobNames = new String[0];
}
