package thexnator.furnituremod.gui.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class SlotArmour extends Slot
{
	private int armourType;

	public SlotArmour(IInventory machine, int id, int x, int y, int armourType)
	{
		super(machine, id, x, y);
		this.armourType = armourType;
	}

	@Override
	public boolean isItemValid(ItemStack par1ItemStack)
	{
		if (par1ItemStack == null)
		{
			return false;
		}

		if (!(par1ItemStack.getItem() instanceof ItemArmor))
		{
			return false;
		}

		if (((ItemArmor) par1ItemStack.getItem()).armorType != armourType)
		{
			return false;
		}

		return true;
	}
}
