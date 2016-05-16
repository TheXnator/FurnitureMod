package thexnator.furnituremod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.EnumWorldBlockLayer;

public class BlockTable extends Block {

	public BlockTable(Material materialIn) {
		super(materialIn);
		setHardness(1.0F);
		setStepSound(Block.soundTypeWood);
	}
	
	@Override
	public boolean isOpaqueCube() 
	{
		return false;
	}
	
	@Override
	public EnumWorldBlockLayer getBlockLayer() 
	{
		return EnumWorldBlockLayer.CUTOUT;
	}

}
