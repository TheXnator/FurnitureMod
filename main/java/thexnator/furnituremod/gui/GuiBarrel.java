package thexnator.furnituremod.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import thexnator.furnituremod.gui.containers.ContainerBarrel;

public class GuiBarrel extends GuiContainer
{
	private static final ResourceLocation gui = new ResourceLocation("fm:textures/gui/barrel.png");

	public GuiBarrel(IInventory par1IInventory, IInventory par2IInventory)
	{
		super(new ContainerBarrel(par1IInventory, par2IInventory));
		this.xSize = 176;
		this.ySize = 167;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRendererObj.drawString("Barrel", (this.xSize / 2) - 44, 6, 4210752);
		this.fontRendererObj.drawString("Inventory", 8, this.ySize - 94, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(gui);
		int l = (width - xSize) / 2;
		int i1 = (height - ySize) / 2;
		this.drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
	}
}
