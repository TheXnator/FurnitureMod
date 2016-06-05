package thexnator.furnituremod.render.tileentity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import thexnator.furnituremod.tileentity.TileEntityMug;

@SuppressWarnings("rawtypes")
public class MugRenderer extends TileEntitySpecialRenderer
{

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double posX, double posY, double posZ, float p_180535_8_, int p_180535_9_)
	{
		TileEntityMug tileEntityCup = (TileEntityMug) tileEntity;
		if (tileEntityCup.getDrink() != null)
		{
			@SuppressWarnings("unused")
			Tessellator tessellator = Tessellator.getInstance();
			GL11.glPushMatrix();
			{
				GL11.glTranslatef((float) posX + 0.5F, (float) posY, (float) posZ + 0.5F);
				GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
				GL11.glDisable(GL11.GL_CULL_FACE);
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glEnable(GL11.GL_BLEND);
	
				GL11.glColor4f(tileEntityCup.red / 255F, tileEntityCup.green / 255F, tileEntityCup.blue / 255F, 1.0F);
	
				GL11.glBegin(GL11.GL_QUADS);
	
				// North Face
				GL11.glVertex3d(-0.125, 0.5 * 0.0625, -0.125);
				GL11.glVertex3d(0.125, 0.5 * 0.0625, -0.125);
				GL11.glVertex3d(0.125, 0.4, -0.125);
				GL11.glVertex3d(-0.125, 0.4, -0.125);
	
				// South Face
				GL11.glVertex3d(-0.125, 0.5 * 0.0625, 0.125);
				GL11.glVertex3d(0.125, 0.5 * 0.0625, 0.125);
				GL11.glVertex3d(0.125, 0.4, 0.125);
				GL11.glVertex3d(-0.125, 0.4, 0.125);
	
				// West Face
				GL11.glVertex3d(-0.125, 0.5 * 0.0625, -0.125);
				GL11.glVertex3d(-0.125, 0.5 * 0.0625, 0.125);
				GL11.glVertex3d(-0.125, 0.4, 0.125);
				GL11.glVertex3d(-0.125, 0.4, -0.125);
	
				// East Face
				GL11.glVertex3d(0.125, 0.5 * 0.0625, -0.125);
				GL11.glVertex3d(0.125, 0.5 * 0.0625, 0.125);
				GL11.glVertex3d(0.125, 0.4, 0.125);
				GL11.glVertex3d(0.125, 0.4, -0.125);
	
				// Top Face
				GL11.glVertex3d(-0.125, 0.4, -0.125);
				GL11.glVertex3d(0.125, 0.4, -0.125);
				GL11.glVertex3d(0.125, 0.4, 0.125);
				GL11.glVertex3d(-0.125, 0.4, 0.125);
	
				// Bottom Face
				GL11.glVertex3d(-0.125, 0.5 * 0.0625, -0.125);
				GL11.glVertex3d(0.125, 0.5 * 0.0625, -0.125);
				GL11.glVertex3d(0.125, 0.5 * 0.0625, 0.125);
				GL11.glVertex3d(-0.125, 0.5 * 0.0625, 0.125);
	
				GL11.glEnd();
	
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glEnable(GL11.GL_TEXTURE_2D);
			}
			GL11.glPopMatrix();
		}
	}
}
