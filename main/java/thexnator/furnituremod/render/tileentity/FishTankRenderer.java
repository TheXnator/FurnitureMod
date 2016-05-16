package thexnator.furnituremod.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import thexnator.furnituremod.blocks.BlockFishTank;
import thexnator.furnituremod.tileentity.TileEntityFishTank;

public class FishTankRenderer extends TileEntitySpecialRenderer<TileEntityFishTank>
{
	
	private EntityItem entityItem = new EntityItem(Minecraft.getMinecraft().theWorld, 0D, 0D, 0D, new ItemStack(Items.fish));
	
	@Override
	public void renderTileEntityAt(TileEntityFishTank te, double posX, double posY, double posZ, float partialTick, int breakStage) 
	{
		entityItem.hoverStart = 0;
		
		if(!(te.getBlockType() instanceof BlockFishTank))
			return;
		TileEntityFishTank tileEntityFishTank = (TileEntityFishTank) te;
		
		GlStateManager.pushMatrix();
		{
			GlStateManager.translate(posX, posY, posZ);
			GlStateManager.translate(0.5, -0.15, 0.5);
			GlStateManager.scale(0.9, 0.9, 0.9);
			float rotation = (float) tileEntityFishTank.rotation + partialTick;
			if(tileEntityFishTank.special) rotation *= 50;
			GlStateManager.rotate(rotation, 0, 1, 0);
			GlStateManager.translate(0.2, 0, 0);
			GlStateManager.rotate(45F, 1, 0, 0);
			GlStateManager.rotate(-90F, 0, 1, 0);
			GlStateManager.translate(-0.25, 0, 0);
			Minecraft.getMinecraft().getRenderManager().renderEntityWithPosYaw(entityItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
		}
		GlStateManager.popMatrix();
	}

}
