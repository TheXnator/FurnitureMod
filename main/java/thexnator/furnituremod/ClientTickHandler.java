package thexnator.furnituremod;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import thexnator.furnituremod.entity.EntityHangGlider;

public class ClientTickHandler {

	private static int ticks = 0;

	@SubscribeEvent
	public void onRenderTickStart(TickEvent.RenderTickEvent evt) {
		if (evt.phase == Phase.START && Minecraft.getMinecraft().theWorld != null) {
			preRenderTick(Minecraft.getMinecraft(), Minecraft.getMinecraft().theWorld, evt.renderTickTime);
		}
	}

	public void preRenderTick(Minecraft mc, World world, float renderTick) {
		EntityHangGlider.updateGliders(world);
		AbstractClientPlayer player = Minecraft.getMinecraft().thePlayer;
		RenderPlayer renderplayer = new RenderPlayer(Minecraft.getMinecraft().getRenderManager());
		if (!EntityHangGlider.isGliderDeployed(player))
		{
			player.limbSwing = 0f;
			player.prevLimbSwingAmount = 0f;
			player.limbSwingAmount = 0f;
			//GL11.glRotatef(75, -1, 0, 0);
			player = Minecraft.getMinecraft().thePlayer;
			//renderplayer.getMainModel().setRotationAngles(0, 0, 0, 0, 0, 90, player);
			//renderplayer.getMainModel().
			GL11.glPushMatrix();
			GL11.glRotatef(90, 0, 0, 0);
			renderplayer.doRender(player, player.posX, player.posY, player.posZ, player.rotationYaw, 1);
			GL11.glPopMatrix();
		}
	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent evt) {
		ticks++;
	}

	public static int getTicks() {
		return ticks;
	}
}
