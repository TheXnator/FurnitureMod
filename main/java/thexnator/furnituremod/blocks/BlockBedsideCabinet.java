package thexnator.furnituremod.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBedsideCabinet extends BlockFurnitureTile {

	public BlockBedsideCabinet(Material material) {
		super(material);
		this.setHardness(1.0F);
		this.setStepSound(soundTypeWood);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return null;
	}

}
