package thexnator.furnituremod.renderer.item;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import thexnator.furnituremod.entity.EntityHangGlider;

public class ItemRendererHangGlider {

	public void renderItem(ItemStack stack, Object... data) {
		EntityLivingBase par1EntityLiving = (EntityLivingBase)data[1];
		if (EntityHangGlider.isEntityHoldingGlider(par1EntityLiving)) return;

		Tessellator tessellator = Tessellator.getInstance();
		GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		GL11.glTranslatef(-0.0F, -0.3F, 0.0F);
		GL11.glScalef(1.5F, 1.5F, 1.5F);
		GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);

	}
}
