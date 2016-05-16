package thexnator.furnituremod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockCounterCorner extends Block {

	public BlockCounterCorner(Material materialIn) {
		super(materialIn);
		setHardness(1.0F);
		setStepSound(Block.soundTypeStone);
	}
	
}
