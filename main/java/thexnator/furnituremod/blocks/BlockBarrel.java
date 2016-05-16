package thexnator.furnituremod.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thexnator.furnituremod.FurnitureMod;
import thexnator.furnituremod.init.FurnitureBlocks;
import thexnator.furnituremod.tileentity.TileEntityBarrel;

public class BlockBarrel extends BlockFurnitureTile {

	public BlockBarrel(Material materialIn) {
		super(materialIn);
		setHardness(1.0F);
		setStepSound(Block.soundTypeWood);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			TileEntity tile_entity = world.getTileEntity(pos);

			if (tile_entity instanceof TileEntityBarrel)
			{
				player.openGui(FurnitureMod.instance, 0, world, pos.getX(), pos.getY(), pos.getZ());
			}
		}
		return true;
	}

	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TileEntityBarrel();
	}
	
	public Block getBlockDropped(IBlockState state, Random rand, int fortune)
	{
		return FurnitureBlocks.barrel_block;
	}
	
	@SideOnly(Side.CLIENT)
	public EnumWorldBlockLayer getBlockLayer()
	{
		return EnumWorldBlockLayer.CUTOUT;
	}

}
