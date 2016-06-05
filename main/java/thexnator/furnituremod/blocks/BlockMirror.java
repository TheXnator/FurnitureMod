package thexnator.furnituremod.blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thexnator.furnituremod.entity.EntitySittableBlock;
import thexnator.furnituremod.util.CollisionHelper;

public class BlockMirror extends BlockFurniture 
{

	public BlockMirror(Material material) 
	{
		super(material);
		this.setHardness(1.0F);
		this.setStepSound(soundTypeGlass);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, BlockPos pos)
	{
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB mask, @SuppressWarnings("rawtypes") List list, Entity collidingEntity)
	{
		if (!(collidingEntity instanceof EntitySittableBlock))
		{
			int metadata = getMetaFromState(state);
			float[] data = CollisionHelper.fixRotation(metadata, 1.0F, 0.1F, 1.0F, 1.0F);
			setBlockBounds(data[0], 0.6F, data[1], data[2], 1.0F, data[3]);
			super.addCollisionBoxesToList(world, pos, state, mask, list, collidingEntity);
			setBlockBounds(0.1F, 0.0F, 0.1F, 1.0F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(world, pos, state, mask, list, collidingEntity);
		}
		else
		{
			setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
			super.addCollisionBoxesToList(world, pos, state, mask, list, collidingEntity);
		}
	}

}
