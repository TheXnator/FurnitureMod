package thexnator.furnituremod.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thexnator.furnituremod.entity.EntitySittableBlock;
import thexnator.furnituremod.tileentity.TileEntityDolphin;
import thexnator.furnituremod.util.CollisionHelper;
import thexnator.furnituremod.util.SittableUtil;

public abstract class BlockDolphin extends BlockFurnitureTile
{
	public static final PropertyInteger COLOUR = PropertyInteger.create("colour", 0, 15);
	
	public BlockDolphin()
	{
		super(Material.cloth);
		this.setHardness(0.5F);
		this.setStepSound(Block.soundTypeCloth);
		
		IBlockState baseState = this.blockState.getBaseState();
		if(isSpecial())
		{
			this.setDefaultState(baseState.withProperty(FACING, EnumFacing.NORTH));
		}
		else
		{
			this.setDefaultState(baseState.withProperty(FACING, EnumFacing.NORTH).withProperty(COLOUR, Integer.valueOf(0)));
		}
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, BlockPos pos)
	{
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.55F, 1.0F);
	}

	@Override
	public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
	{
		if (!(collidingEntity instanceof EntitySittableBlock))
		{
			int metadata = getMetaFromState(state);
			float[] data = CollisionHelper.fixRotation(metadata, 1.0F, 1.0F, 0.55F, 1.0F);
			setBlockBounds(data[0], 0.0F, data[1], data[2], 0.0F, data[3]);
			super.addCollisionBoxesToList(world, pos, state, mask, list, collidingEntity);
			setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.55F, 1.0F);
			super.addCollisionBoxesToList(world, pos, state, mask, list, collidingEntity);
		}
		else
		{
			setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
			super.addCollisionBoxesToList(world, pos, state, mask, list, collidingEntity);
		}
	}

	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		if(!isSpecial())
		{
			int colour = ((TileEntityDolphin) world.getTileEntity(pos)).getColour();
			state = state.withProperty(COLOUR, Integer.valueOf(colour));
		}
		return state;
		
	}

	@Override
	public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
	{
		IBlockState state = super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer);
		if(!isSpecial())
		{
			state = state.withProperty(COLOUR, Integer.valueOf(0));
		}
		return state;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if(!isSpecial())
		{
			ItemStack currentItem = playerIn.getCurrentEquippedItem();
			if(currentItem != null && currentItem.getItem() == Items.name_tag)
			{
				if(currentItem.hasDisplayName())
				{
					if(currentItem.getDisplayName().equals("jeb_"))
					{
						System.out.println("Still working on Jeb dolphins!");
					}
				}
			}
			
			TileEntity tileEntity = worldIn.getTileEntity(pos);
			if (tileEntity instanceof TileEntityDolphin) 
			{
				TileEntityDolphin TileEntityDolphin = (TileEntityDolphin) tileEntity;
				if (currentItem != null) 
				{
					if (currentItem.getItem() instanceof ItemDye) 
					{
						TileEntityDolphin.setColour(currentItem.getItemDamage());
						currentItem.stackSize--;
						worldIn.markBlockForUpdate(pos);
						return true;
					}
				}
			}
		}
		return SittableUtil.sitOnBlock(worldIn, pos.getX(), pos.getY(), pos.getZ(), playerIn, 0.25);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityDolphin();
	}

	@Override
	protected BlockState createBlockState()
	{
		return isSpecial() ? new BlockState(this,  new IProperty[] { FACING }) : new BlockState(this, new IProperty[] { FACING, COLOUR});
	}
	
	public abstract boolean isSpecial();

}
