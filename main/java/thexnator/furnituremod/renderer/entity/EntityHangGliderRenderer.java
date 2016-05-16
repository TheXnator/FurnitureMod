package thexnator.furnituremod.renderer.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thexnator.furnituremod.FurnitureMod;
import thexnator.furnituremod.entity.EntityHangGlider;
import thexnator.furnituremod.renderer.DisplayListWrapper;
import thexnator.furnituremod.util.renderers.GL11U;
import thexnator.furnituremod.util.utilclasses.PrintUtil;

public class EntityHangGliderRenderer extends Render<EntityHangGlider> {

	private static final float QUAD_HALF_SIZE = 2.4f;
	private static final float ONGROUND_ROTATION = 90f;

	private final DisplayListWrapper gliderRender = new DisplayListWrapper() {
		@Override
		public void compile() {
			GlStateManager.disableCull();
			GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
			GL11.glBegin(GL11.GL_QUADS);

			GL11.glTexCoord2f(1, 1);
			GL11.glVertex3f(QUAD_HALF_SIZE, 0, QUAD_HALF_SIZE);

			GL11.glTexCoord2f(0, 1);
			GL11.glVertex3f(-QUAD_HALF_SIZE, 0, QUAD_HALF_SIZE);

			GL11.glTexCoord2f(0, 0);
			GL11.glVertex3f(-QUAD_HALF_SIZE, 0, -QUAD_HALF_SIZE);

			GL11.glTexCoord2f(1, 0);
			GL11.glVertex3f(QUAD_HALF_SIZE, 0, -QUAD_HALF_SIZE);

			GL11.glEnd();
			GlStateManager.enableCull();

		}
	};

	private static final ResourceLocation texture = FurnitureMod.location("textures/entity/hangglider.png");

	public EntityHangGliderRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityHangGlider glider, double x, double y, double z, float f, float f1) {
		final EntityPlayer owner = glider.getPlayer();
		if (owner == null) return;

		final Minecraft minecraft = Minecraft.getMinecraft();
		final boolean isLocalPlayer = owner == minecraft.thePlayer;
		final boolean isFpp = minecraft.gameSettings.thirdPersonView == 0;
		final boolean isDeployed = glider.isDeployed();

		if (isLocalPlayer && isFpp && isDeployed) return;

		final float rotation = interpolateRotation(glider.prevRotationYaw, glider.rotationYaw, f1);

		GL11.glPushMatrix();

		GL11.glTranslated(x, y, z);
		GL11.glRotatef(180.0F - rotation, 0.0F, 1.0F, 0.0F);

		if (isLocalPlayer) {
			if (isDeployed) {
				// move up and closer to back
				GL11.glTranslated(0, +0.2, +0.3);
			} else {
				if (isFpp) {
					// move over head when flying in FPP
					GL11.glTranslated(0, +0.2, 0);
				} else {
					// move closer to back and forward when flying in TDD
					GL11.glTranslated(0, -0.8, -1.0);
				}
			}
		} else {
			if (isDeployed) {
				// move up little bit (other player center is lower)
				GL11.glTranslated(0, +0.8, +0.3);
			} else {
				// move closer to back and forward when flying
				GL11.glTranslated(0, -0.5, -1.0);
			}
		}

		if (isDeployed) {
			GL11.glRotatef(ONGROUND_ROTATION, 1, 0, 0);
			GL11.glScalef(0.4f, 1f, 0.4f);
		}

		bindTexture(texture);
		gliderRender.render();
		GL11.glPopMatrix();
	}

	/* Interpolate rotation */
	private static float interpolateRotation(float prevRotation, float nextRotation, float modifier) {
		float rotation = nextRotation - prevRotation;

		while (rotation < -180.0F)
			rotation += 360.0F;

		while (rotation >= 180.0F) {
			rotation -= 360.0F;
		}

		return prevRotation + modifier * rotation;
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityHangGlider entity) {
		return texture;
	}
}
