package thexnator.furnituremod;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thexnator.furnituremod.entity.EntityHangGlider;
import thexnator.furnituremod.renderer.PlayerBodyRenderEvent;

public class GliderPlayerRenderHandler
{
	@SubscribeEvent
	public void onPlayerBodyRender(PlayerBodyRenderEvent evt) 
	{
		final AbstractClientPlayer player = evt.player;
		if (!EntityHangGlider.isGliderDeployed(player))
		{
			player.limbSwing = 0f;
			player.prevLimbSwingAmount = 0f;
			player.limbSwingAmount = 0f;
			GL11.glRotatef(75, -1, 0, 0);
			System.out.println("GliderPlayerRenderHandler");
		}
	}
}