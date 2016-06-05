package thexnator.furnituremod.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import thexnator.furnituremod.FurnitureMod;
import thexnator.furnituremod.tileentity.TileEntityDrawers;

public class BlockDrawers extends BlockFurnitureTile {

	public BlockDrawers(Material material) {
		super(material);
		this.setHardness(1.0F);
		this.setStepSound(soundTypeWood);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			TileEntity tile_entity = world.getTileEntity(pos);

			if (tile_entity instanceof TileEntityDrawers)
			{
				player.openGui(FurnitureMod.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
			}
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityDrawers();
	}

}
