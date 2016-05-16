package thexnator.furnituremod.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import thexnator.furnituremod.gui.containers.ContainerBarrel;
import thexnator.furnituremod.gui.containers.ContainerOven;
import thexnator.furnituremod.gui.containers.ContainerWardrobe;
import thexnator.furnituremod.tileentity.TileEntityBarrel;
import thexnator.furnituremod.tileentity.TileEntityOven;
import thexnator.furnituremod.tileentity.TileEntityPhone;
import thexnator.furnituremod.tileentity.TileEntityWardrobe;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile_entity = world.getTileEntity(new BlockPos(x, y, z));
		if (tile_entity instanceof TileEntityBarrel)
		{
			return new ContainerBarrel(player.inventory, (TileEntityBarrel) tile_entity);
		}
		
		if (tile_entity instanceof TileEntityOven)
		{
			return new ContainerOven(player.inventory, (TileEntityOven) tile_entity);
		}
		
		if (tile_entity instanceof TileEntityWardrobe)
		{
			return new ContainerWardrobe(player.inventory, (TileEntityWardrobe) tile_entity);
		}
		
		if (tile_entity instanceof TileEntityPhone)
		{
			return new GuiPhone(player.inventory, (TileEntityPhone) tile_entity);
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile_entity = world.getTileEntity(new BlockPos(x, y, z));
		if (tile_entity instanceof TileEntityBarrel)
		{
			return new GuiBarrel(player.inventory, (TileEntityBarrel) tile_entity);
		}
		
		if (tile_entity instanceof TileEntityOven)
		{
			return new GuiOven(player.inventory, (TileEntityOven) tile_entity);
		}
		
		if (tile_entity instanceof TileEntityWardrobe)
		{
			return new GuiWardrobe(player.inventory, (TileEntityWardrobe) tile_entity);
		}
		
		if (tile_entity instanceof TileEntityPhone)
		{
			return new GuiPhone(player.inventory, (TileEntityPhone) tile_entity);
		}
		
		return null;
	}
}
