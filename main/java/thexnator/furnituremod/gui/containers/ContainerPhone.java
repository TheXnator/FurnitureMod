package thexnator.furnituremod.gui.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerPhone extends Container
{
	private IInventory PhoneInventory;

	public ContainerPhone(IInventory playerInventory, IInventory PhoneInventory)
	{
		this.PhoneInventory = PhoneInventory;
		PhoneInventory.openInventory(null);

		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				this.addSlotToContainer(new Slot(PhoneInventory, j + i * 3, 8 + j * 18 + 54, 18 + i * 18));
			}
		}

		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, j * 18 + 8, i * 18 + 86));
			}
		}

		for (int i = 0; i < 9; i++)
		{
			this.addSlotToContainer(new Slot(playerInventory, i, i * 18 + 8, 144));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityPlayer)
	{
		return this.PhoneInventory.isUseableByPlayer(entityPlayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotNum)
	{
		ItemStack itemCopy = null;
		Slot slot = (Slot) this.inventorySlots.get(slotNum);

		if (slot != null && slot.getHasStack())
		{
			ItemStack item = slot.getStack();
			itemCopy = item.copy();

			if (slotNum < 9)
			{
				if (!this.mergeItemStack(item, 9, this.inventorySlots.size(), true))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(item, 0, 9, false))
			{
				return null;
			}

			if (item.stackSize == 0)
			{
				slot.putStack((ItemStack) null);
			}
			else
			{
				slot.onSlotChanged();
			}
		}

		return itemCopy;
	}

	@Override
	public void onContainerClosed(EntityPlayer entityPlayer)
	{
		super.onContainerClosed(entityPlayer);
		this.PhoneInventory.closeInventory(entityPlayer);
	}
}
