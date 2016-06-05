package thexnator.furnituremod.renderer;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;
import thexnator.furnituremod.entity.EntityHangGlider;

public class PlayerBodyRenderEvent extends Event 
{
	public final AbstractClientPlayer player;

	public final float partialTickTime;

	public PlayerBodyRenderEvent(AbstractClientPlayer player, float partialTickTime)
	{
		this.player = player;
		this.partialTickTime = partialTickTime;
		if (!EntityHangGlider.isGliderDeployed(player))
		{
			player.limbSwing = 0f;
			player.prevLimbSwingAmount = 0f;
			player.limbSwingAmount = 0f;
			GL11.glRotatef(75, -1, 0, 0);
			System.out.println("GliderPlayerRenderHandler");
		}
		System.out.println("PlayerBodyRenderEvent");
	}
}