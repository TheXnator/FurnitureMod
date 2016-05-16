package thexnator.furnituremod.init;

import net.minecraftforge.fml.common.registry.GameRegistry;
import thexnator.furnituremod.tileentity.TileEntityBarrel;
import thexnator.furnituremod.tileentity.TileEntityCoordinateTeleporter;
import thexnator.furnituremod.tileentity.TileEntityDolphin;
import thexnator.furnituremod.tileentity.TileEntityFishTank;
import thexnator.furnituremod.tileentity.TileEntityMug;
import thexnator.furnituremod.tileentity.TileEntityOven;
import thexnator.furnituremod.tileentity.TileEntityWardrobe;

public class FurnitureTileEntities
{
	public static void register()
	{
		GameRegistry.registerTileEntity(TileEntityCoordinateTeleporter.class, "fmCoordinateTeleporter");
		GameRegistry.registerTileEntity(TileEntityBarrel.class, "fmBarrel");
		GameRegistry.registerTileEntity(TileEntityOven.class, "fmOven");
		GameRegistry.registerTileEntity(TileEntityWardrobe.class, "fmWardrobe");
		GameRegistry.registerTileEntity(TileEntityMug.class, "fmMug");
		GameRegistry.registerTileEntity(TileEntityDolphin.class, "fmDolphin");
		GameRegistry.registerTileEntity(TileEntityFishTank.class, "fmFishTank");
	}
}
