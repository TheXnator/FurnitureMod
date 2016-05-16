package thexnator.furnituremod.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thexnator.furnituremod.entity.EntitySittableBlock;
import thexnator.furnituremod.handler.InputHandler;
import thexnator.furnituremod.init.FurnitureBlocks;
import thexnator.furnituremod.util.CollisionHelper;
import thexnator.furnituremod.util.SittableUtil;

public class BlockOfficeChair extends BlockFurniture {
	
	public static PropertyDirection DIRECTION = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

	public BlockOfficeChair(Material materialIn) {
		super(materialIn);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean isFullCube()
	{
		return false;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!player.isSneaking())
		{
			if (SittableUtil.sitOnBlockWithRotationOffset(world, pos.getX(), pos.getY(), pos.getZ(), player, 0.4D, getMetaFromState(state), 0.1D))
			{
				world.updateComparatorOutputLevel(pos, this);
				if (world.isRemote)
				{
					player.addChatComponentMessage(new ChatComponentText("Press C to click your pen with it in your hand."));
				}
			}
		}
		return false;
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, BlockPos pos)
	{
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.2F, 1.0F);
	}

	@Override
	public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
	{
		if (!(collidingEntity instanceof EntitySittableBlock))
		{
			int metadata = getMetaFromState(state);
			float[] data = CollisionHelper.fixRotation(metadata, 1.0F, 0.1F, 1.0F, 1.0F);
			setBlockBounds(data[0], 1.0F, data[1], data[2], 1.2F, data[3]);
			super.addCollisionBoxesToList(world, pos, state, mask, list, collidingEntity);
			setBlockBounds(0.1F, 0.0F, 0.1F, 1.0F, 1.2F, 1.0F);
			super.addCollisionBoxesToList(world, pos, state, mask, list, collidingEntity);
		}
		else
		{
			setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
			super.addCollisionBoxesToList(world, pos, state, mask, list, collidingEntity);
		}
	}

	
	public Block getBlockDropped(IBlockState state, Random rand, int fortune)
	{
		if (this == FurnitureBlocks.chair_block)
			return FurnitureBlocks.chair_block;
		return null;
	}
	
	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		IBlockState state = super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer);
		state = state.withProperty(DIRECTION, placer.getHorizontalFacing());
		return state;
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((EnumFacing) state.getValue(DIRECTION)).getHorizontalIndex();
	}

	@Override
	protected BlockState createBlockState()
	{
		return new BlockState(this, new IProperty[] { DIRECTION });
	}

}