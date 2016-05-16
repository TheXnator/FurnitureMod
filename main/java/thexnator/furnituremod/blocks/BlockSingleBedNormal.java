package thexnator.furnituremod.blocks;

import net.minecraft.block.material.Material;

public class BlockSingleBedNormal extends BlockSingleBed
{
	
	public BlockSingleBedNormal(Material material)
	{
		super(material);
	}

	@Override
	public boolean isSpecial() 
	{
		return false;
	}
}
