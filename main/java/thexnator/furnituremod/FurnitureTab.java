package thexnator.furnituremod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import thexnator.furnituremod.init.FurnitureBlocks;

public class FurnitureTab extends CreativeTabs
{

	public FurnitureTab(String label)
	{
		super(label);
		this.setBackgroundImageName("furniture.png");
	}
	
	@Override
	public Item getTabIconItem()
	{
		return Item.getItemFromBlock(FurnitureBlocks.coffeetable_block);
	}

}
