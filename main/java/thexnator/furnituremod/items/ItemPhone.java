package thexnator.furnituremod.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thexnator.furnituremod.FurnitureMod;
import thexnator.furnituremod.tileentity.TileEntityPhone;

public class ItemPhone extends Item {
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side,float hitX, float hitY, float hitZ) {
			TileEntity tile_entity = world.getTileEntity(pos);
			
			tile_entity = new TileEntityPhone()
			{
				
			};

			if (tile_entity instanceof TileEntityPhone)
			{
				player.openGui(FurnitureMod.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
			}
		return true;
	}

}