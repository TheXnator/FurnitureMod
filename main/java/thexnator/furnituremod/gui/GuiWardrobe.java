package thexnator.furnituremod.gui;

import java.util.Arrays;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import thexnator.furnituremod.gui.containers.ContainerWardrobe;
import thexnator.furnituremod.network.PacketHandler;
import thexnator.furnituremod.tileentity.TileEntityWardrobe;

public class GuiWardrobe extends GuiContainer
{
	private static final ResourceLocation gui = new ResourceLocation("fm:textures/gui/wardrobe.png");
	private TileEntityWardrobe tileEntityWardrobe;
	private VertexFormat format = new VertexFormat();

	private GuiButton button_start;

	public GuiWardrobe(InventoryPlayer inventoryPlayer, TileEntityWardrobe tileEntityWardrobe)
	{
		super(new ContainerWardrobe(inventoryPlayer, tileEntityWardrobe));
		this.tileEntityWardrobe = tileEntityWardrobe;
		this.xSize = 176;
		this.ySize = 228;
	}

	@Override
	public void initGui()
	{
		super.initGui();
		Keyboard.enableRepeatEvents(false);
		buttonList.clear();

		int posX = width / 2;
		int posY = height / 2;

		button_start = new GuiButton(0, posX - 35, posY - 109, 32, 20, "Start");
		buttonList.add(button_start);
	}

	@Override
	protected void actionPerformed(GuiButton guibutton)
	{
		if (!guibutton.enabled)
		{
			return;
		}
				
		if (guibutton.id == 0)
		{
			if (!tileEntityWardrobe.isWashing())
			{
				//PacketHandler.INSTANCE.sendToServer(new MessageWardrobe(0, tileEntityWardrobe.getPos().getX(), tileEntityWardrobe.getPos().getY(), tileEntityWardrobe.getPos().getZ()));
			}
			else
			{
				//PacketHandler.INSTANCE.sendToServer(new MessageWardrobe(1, tileEntityWardrobe.getPos().getX(), tileEntityWardrobe.getPos().getY(), tileEntityWardrobe.getPos().getZ()));
			}
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);

		if(isPointInRegion(37, 9, 11, 11, mouseX, mouseY))
		{
			if (tileEntityWardrobe.isWashing())
			{
				drawHoveringText(Arrays.asList(new String[]{"Running"}), mouseX, mouseY);
			}
			else
			{
				drawHoveringText(Arrays.asList(new String[]{"Stopped"}), mouseX, mouseY);
			}
		}
		
		if(isPointInRegion(129, 30, 10, 73, mouseX, mouseY))
		{
			drawHoveringText(Arrays.asList(new String[]{"Freshener: " + tileEntityWardrobe.timeRemaining + "/5000"}), mouseX, mouseY);
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRendererObj.drawString("Inventory", 8, 135, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(gui);
		int l = (width - xSize) / 2;
		int i1 = (height - ySize) / 2;
		this.drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);

		if (tileEntityWardrobe.isWashing())
		{
			int superMode = tileEntityWardrobe.superMode ? 20 : 50;
			int percent = (tileEntityWardrobe.progress % superMode) * 73 / superMode;
			drawTexturedModalRect((l + 34), (i1 + 104) - percent, 176, 73 - percent, 16, percent);
		}

		int percent = tileEntityWardrobe.timeRemaining * 73 / 5000;
		int superMode = tileEntityWardrobe.superMode ? 20 : 0;
		drawTexturedModalRect((l + 129), (i1 + 103) - percent, 192 + superMode, 0, 10, percent);

		drawTexturedModalRect((l + 129), (i1 + 30), 202, 0, 10, 73);

		if (tileEntityWardrobe.isWashing())
		{
			button_start.displayString = "Stop";
			drawColour(l + 37, i1 + 9, 11, 11, 49475);
		}
		else
		{
			button_start.displayString = "Start";
			drawColour(l + 37, i1 + 9, 11, 11, 16711680);
		}
	}

	public void drawColour(int x, int y, int width, int height, int par4)
	{
		drawRect(x, y, x + width, y + height, par4);
	}
}
