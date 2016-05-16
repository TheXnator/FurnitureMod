package thexnator.furnituremod;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import thexnator.furnituremod.entity.EntityHangGlider;

public class ClientTickHandler {

	// TODO split
	private static int ticks = 0;

	@SubscribeEvent
	public void onRenderTickStart(TickEvent.RenderTickEvent evt) {
		if (evt.phase == Phase.START && Minecraft.getMinecraft().theWorld != null) {
			preRenderTick(Minecraft.getMinecraft(), Minecraft.getMinecraft().theWorld, evt.renderTickTime);
		}
	}

	public void preRenderTick(Minecraft mc, World world, float renderTick) {
		EntityHangGlider.updateGliders(world);
	}

	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent evt) {
		ticks++;
	}

	public static int getTicks() {
		return ticks;
	}
}
