package thexnator.furnituremod.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import thexnator.furnituremod.gui.containers.ContainerOven;
import thexnator.furnituremod.tileentity.TileEntityOven;

public class GuiOven extends GuiContainer
{
	private TileEntityOven OvenInventory;
	private static final ResourceLocation gui = new ResourceLocation("fm:textures/gui/oven.png");

	public GuiOven(InventoryPlayer inventoryplayer, TileEntityOven tileEntityFreezer)
	{
		super(new ContainerOven(inventoryplayer, tileEntityFreezer));
		this.OvenInventory = tileEntityFreezer;
		this.xSize = 176;
		this.ySize = 228;
	}

	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRendererObj.drawString("Oven", 75, 6, 4210752);
		this.fontRendererObj.drawString("Inventory", 8, (ySize - 96) + 2, 4210752);
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(gui);
		if(OvenInventory.isCooking())
		{
			int progress = OvenInventory.getCookProgressScaled(14);
			drawTexturedModalRect(54 + OvenInventory.getCookingItem() * 18, 55 + (14 - progress), 176, (14 - progress), 14, progress);
		}
	}

	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(gui);
		int l = (width - xSize) / 2;
		int i1 = (height - ySize) / 2;
		this.drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
		int var7;

		var7 = this.OvenInventory.getCookProgressScaled(24);
		drawTexturedModalRect(l + 75, i1 + 20, 176, 14, var7 + 1, 16);
		
	}
}
