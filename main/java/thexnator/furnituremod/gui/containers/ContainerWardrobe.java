package thexnator.furnituremod.gui.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import thexnator.furnituremod.api.RecipeAPI;
import thexnator.furnituremod.api.RecipeData;
import thexnator.furnituremod.gui.slots.SlotArmour;

public class ContainerWardrobe extends Container
{
	private IInventory wardrobeInventory;

	public ContainerWardrobe(IInventory playerInventory, IInventory wardrobeInventory)
	{
		this.wardrobeInventory = wardrobeInventory;

		this.addSlotToContainer(new SlotArmour(wardrobeInventory, 0, 80, 43, 0));
		this.addSlotToContainer(new SlotArmour(wardrobeInventory, 1, 64, 60, 1));
		this.addSlotToContainer(new SlotArmour(wardrobeInventory, 2, 96, 60, 2));
		this.addSlotToContainer(new SlotArmour(wardrobeInventory, 3, 80, 76, 3));
		this.addSlotToContainer(new Slot(wardrobeInventory, 4, 125, 7));

		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, j * 18 + 8, i * 18 + 146));
			}
		}

		for (int i = 0; i < 9; i++)
		{
			this.addSlotToContainer(new Slot(playerInventory, i, i * 18 + 8, 204));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return this.wardrobeInventory.isUseableByPlayer(player);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotNum)
	{
		ItemStack itemCopy = null;
		Slot slot = (Slot) this.inventorySlots.get(slotNum);

		if (slot != null && slot.getHasStack())
		{
			ItemStack item = slot.getStack();
			itemCopy = item.copy();

			if (slotNum < 5)
			{
				if (!this.mergeItemStack(item, 5, this.inventorySlots.size(), false))
				{
					return null;
				}
			}
			else if (slotNum > 4)
			{
				RecipeData data = RecipeAPI.getWardrobeRecipeFromInput(item);

				if (data != null)
				{
					if (data.getInput().getItem() instanceof ItemArmor)
					{
						ItemArmor armour = (ItemArmor) data.getInput().getItem();
						if (!this.mergeItemStack(item, armour.armorType, armour.armorType + 1, true))
						{
							return null;
						}
					}
				}
				
					if (!this.mergeItemStack(item, 4, 5, false))
					{
						return null;
					}
				
				else if (slotNum > 4 && slotNum < this.inventorySlots.size() - 9)
				{
					if (!this.mergeItemStack(item, this.inventorySlots.size() - 9, this.inventorySlots.size(), false))
					{
						return null;
					}
				}
				else if (slotNum >= this.inventorySlots.size() - 9 && slotNum < this.inventorySlots.size())
				{
					if (!this.mergeItemStack(item, 5, this.inventorySlots.size() - 9, false))
					{
						return null;
					}
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
	public void onContainerClosed(EntityPlayer player)
	{
		super.onContainerClosed(player);
		wardrobeInventory.closeInventory(player);
	}
}
