package thexnator.furnituremod.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import thexnator.furnituremod.Reference;
import thexnator.furnituremod.network.message.MessageClickSound;
import thexnator.furnituremod.network.message.MessageConfig;

public class PacketHandler
{
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MODID);

	public static void init()
	{
		INSTANCE.registerMessage(MessageConfig.class, MessageConfig.class, 0, Side.CLIENT);
		INSTANCE.registerMessage(MessageClickSound.class, MessageClickSound.class, 1, Side.SERVER);
	}
}
