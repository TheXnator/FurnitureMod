package thexnator.furnituremod.blocks;

import java.util.List;
import java.util.Random;

import com.ibm.icu.text.MessagePattern.Part;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import thexnator.furnituremod.entity.EntitySittableBlock;
import thexnator.furnituremod.init.FurnitureBlocks;
import thexnator.furnituremod.init.FurnitureItems;
import thexnator.furnituremod.network.PacketHandler;
import thexnator.furnituremod.tileentity.TileEntityDolphin;
import thexnator.furnituremod.tileentity.TileEntitySingleBed;
import thexnator.furnituremod.util.CollisionHelper;
import thexnator.furnituremod.util.SittableUtil;

public abstract class BlockSingleBed extends BlockFurniture implements ITileEntityProvider
{
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyEnum<BlockSingleBed.Part> PART = PropertyEnum.<BlockSingleBed.Part>create("part", BlockSingleBed.Part.class);
    public static final PropertyBool OCCUPIED = PropertyBool.create("occupied");
	public static final PropertyInteger COLOUR = PropertyInteger.create("colour", 0, 15);
	
	public BlockSingleBed(Material material)
	{
		super(material);
		this.setHardness(0.5F);
		this.setStepSound(Block.soundTypeCloth);
				
		IBlockState baseState = this.blockState.getBaseState();
//		if(isSpecial())
//		{
//			this.setDefaultState(baseState.withProperty(FACING, EnumFacing.NORTH));
//		}
//		else
//		{
		this.setDefaultState(this.blockState.getBaseState().withProperty(PART, BlockSingleBed.Part.HEAD).withProperty(OCCUPIED, Boolean.valueOf(false)).withProperty(FACING, EnumFacing.NORTH).withProperty(COLOUR, Integer.valueOf(0)));
		this.setBedBounds();
			//}
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
			int colour = ((TileEntitySingleBed) world.getTileEntity(pos)).getColour();
			state = state.withProperty(COLOUR, Integer.valueOf(colour));
		}
		
		if (state.getValue(PART) == BlockSingleBed.Part.FOOT)
		{
			IBlockState iblockstate = world.getBlockState(pos.offset((EnumFacing)state.getValue(FACING)));
	        if (iblockstate.getBlock() == this)
	        {
	            state = state.withProperty(OCCUPIED, iblockstate.getValue(OCCUPIED));
	        }
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
		
		BlockPos blockpos = pos.offset(((EnumFacing)state.getValue(FACING)).getOpposite());
    	world.setBlockState(blockpos, FurnitureBlocks.bed.getDefaultState().withProperty(PART, Part.HEAD)/*.withProperty(OCCUPIED, Boolean.valueOf(false)).withProperty(FACING, state.getValue(FACING)).withProperty(COLOUR, state.getValue(COLOUR))*/, 2);
    	
		return state;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (worldIn.isRemote)
        {
            return true;
        }
        else
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
    						System.out.println("Still working on Jeb beds!");
    					}
    				}
    			}
    			
    			TileEntity tileEntity = worldIn.getTileEntity(pos);
    			if (tileEntity instanceof TileEntitySingleBed) 
    			{
    				TileEntitySingleBed TileEntitySingleBed = (TileEntitySingleBed) tileEntity;
    				if (currentItem != null) 
    				{
    					if (currentItem.getItem() instanceof ItemDye) 
    					{
    						TileEntitySingleBed.setColour(currentItem.getItemDamage());
    						currentItem.stackSize--;
    						worldIn.markBlockForUpdate(pos);
    						return true;
    					}
    				}
    					
    				}
    		}
        	
            if (state.getValue(PART) != BlockSingleBed.Part.HEAD)
            {
                pos = pos.offset((EnumFacing)state.getValue(FACING));
                state = worldIn.getBlockState(pos);

                if (state.getBlock() != this)
                {
                    return true;
                }
            }

            if (worldIn.provider.canRespawnHere() && worldIn.getBiomeGenForCoords(pos) != BiomeGenBase.hell)
            {
                if (((Boolean)state.getValue(OCCUPIED)).booleanValue())
                {
                    EntityPlayer entityplayer = this.getPlayerInBed(worldIn, pos);

                    if (entityplayer != null)
                    {
                        playerIn.addChatComponentMessage(new ChatComponentTranslation("tile.bed.occupied", new Object[0]));
                        return true;
                    }

                    state = state.withProperty(OCCUPIED, Boolean.valueOf(false));
                    worldIn.setBlockState(pos, state, 4);
                }

                EntityPlayer.EnumStatus entityplayer$enumstatus = playerIn.trySleep(pos);

                if (entityplayer$enumstatus == EntityPlayer.EnumStatus.OK)
                {
                    state = state.withProperty(OCCUPIED, Boolean.valueOf(true));
                    worldIn.setBlockState(pos, state, 4);
                    return true;
                }
                else
                {
                    if (entityplayer$enumstatus == EntityPlayer.EnumStatus.NOT_POSSIBLE_NOW)
                    {
                        playerIn.addChatComponentMessage(new ChatComponentTranslation("tile.bed.noSleep", new Object[0]));
                    }
                    else if (entityplayer$enumstatus == EntityPlayer.EnumStatus.NOT_SAFE)
                    {
                        playerIn.addChatComponentMessage(new ChatComponentTranslation("tile.bed.notSafe", new Object[0]));
                    }

                    return true;
                }
            }
            else
            {
                worldIn.setBlockToAir(pos);
                BlockPos blockpos = pos.offset(((EnumFacing)state.getValue(FACING)).getOpposite());

                if (worldIn.getBlockState(blockpos).getBlock() == this)
                {
                    worldIn.setBlockToAir(blockpos);
                }

                worldIn.newExplosion((Entity)null, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, 5.0F, true, true);
                return true;
            }
        }
		
		
	}
	
	private EntityPlayer getPlayerInBed(World worldIn, BlockPos pos)
    {
        for (EntityPlayer entityplayer : worldIn.playerEntities)
        {
            if (entityplayer.isPlayerSleeping() && entityplayer.playerLocation.equals(pos))
            {
                return entityplayer;
            }
        }

        return null;
    }
	
	public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

        if (state.getValue(PART) == BlockSingleBed.Part.HEAD)
        {
            if (worldIn.getBlockState(pos.offset(enumfacing.getOpposite())).getBlock() != this)
            {
                worldIn.setBlockToAir(pos);
            }
        }
        else if (worldIn.getBlockState(pos.offset(enumfacing)).getBlock() != this)
        {
            worldIn.setBlockToAir(pos);

            if (!worldIn.isRemote)
            {
                this.dropBlockAsItem(worldIn, pos, state, 0);
            }
        }
    }
	
	private void setBedBounds()
    {
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5625F, 1.0F);
    }
	
	public static BlockPos getSafeExitLocation(World worldIn, BlockPos pos, int tries)
    {
        EnumFacing enumfacing = (EnumFacing)worldIn.getBlockState(pos).getValue(FACING);
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();

        for (int l = 0; l <= 1; ++l)
        {
            int i1 = i - enumfacing.getFrontOffsetX() * l - 1;
            int j1 = k - enumfacing.getFrontOffsetZ() * l - 1;
            int k1 = i1 + 2;
            int l1 = j1 + 2;

            for (int i2 = i1; i2 <= k1; ++i2)
            {
                for (int j2 = j1; j2 <= l1; ++j2)
                {
                    BlockPos blockpos = new BlockPos(i2, j, j2);

                    if (hasRoomForPlayer(worldIn, blockpos))
                    {
                        if (tries <= 0)
                        {
                            return blockpos;
                        }

                        --tries;
                    }
                }
            }
        }

        return null;
    }

    protected static boolean hasRoomForPlayer(World worldIn, BlockPos pos)
    {
        return World.doesBlockHaveSolidTopSurface(worldIn, pos.down()) && !worldIn.getBlockState(pos).getBlock().getMaterial().isSolid() && !worldIn.getBlockState(pos.up()).getBlock().getMaterial().isSolid();
    }
    
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune)
    {
        if (state.getValue(PART) == BlockSingleBed.Part.FOOT)
        {
            super.dropBlockAsItemWithChance(worldIn, pos, state, chance, 0);
        }
    }
    
//    @Override
//    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
//    	BlockPos blockpos = pos.offset(((EnumFacing)state.getValue(FACING)).getOpposite());
//    	worldIn.setBlockState(blockpos, FurnitureBlocks.bed.getDefaultState().withProperty(PART, state.getValue(PART)), 2);
//    	super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
//    }

    public int getMobilityFlag()
    {
        return 1;
    }
    
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        if (player.capabilities.isCreativeMode && state.getValue(PART) == BlockSingleBed.Part.HEAD)
        {
            BlockPos blockpos = pos.offset(((EnumFacing)state.getValue(FACING)).getOpposite());

            if (worldIn.getBlockState(blockpos).getBlock() == this)
            {
                worldIn.setBlockToAir(blockpos);
            }
        }
    }

    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getHorizontal(meta);
        return (meta & 8) > 0 ? this.getDefaultState().withProperty(PART, BlockSingleBed.Part.HEAD).withProperty(FACING, enumfacing).withProperty(OCCUPIED, Boolean.valueOf((meta & 4) > 0)) : this.getDefaultState().withProperty(PART, BlockSingleBed.Part.FOOT).withProperty(FACING, enumfacing);
    }
    
    public int getMetaFromState(IBlockState state)
    {
        int i = 0;
        i = i | ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();

        if (state.getValue(PART) == BlockSingleBed.Part.HEAD)
        {
            i |= 8;

            if (((Boolean)state.getValue(OCCUPIED)).booleanValue())
            {
                i |= 4;
            }
        }

        return i;
    }
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(FurnitureBlocks.bed);
	}

	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntitySingleBed();
	}

	@Override
	protected BlockState createBlockState()
	{
		return isSpecial() ? new BlockState(this,  new IProperty[] { FACING, PART, OCCUPIED }) : new BlockState(this, new IProperty[] { FACING, COLOUR, PART, OCCUPIED});
	}
	
	public abstract boolean isSpecial();
	
	public static enum Part implements IStringSerializable
    {
        HEAD("head"),
        FOOT("foot");

        private final String name;

        private Part(String name)
        {
            this.name = name;
        }

        public String toString()
        {
            return this.name;
        }

        public String getName()
        {
            return this.name;
        }
    }

}
