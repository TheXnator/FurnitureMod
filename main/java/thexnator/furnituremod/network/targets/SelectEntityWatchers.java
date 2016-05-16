package thexnator.furnituremod.network.targets;

import java.util.Collection;
import java.util.Set;

import com.google.common.base.Preconditions;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.handshake.NetworkDispatcher;
import net.minecraftforge.fml.relauncher.Side;
import thexnator.furnituremod.network.IPacketTargetSelector;
import thexnator.furnituremod.utils.NetUtils;

public class SelectEntityWatchers implements IPacketTargetSelector {

	public static final IPacketTargetSelector INSTANCE = new SelectEntityWatchers();

	@Override
	public boolean isAllowedOnSide(Side side) {
		return side == Side.SERVER;
	}

	@Override
	public void listDispatchers(Object arg, Collection<NetworkDispatcher> result) {
		Preconditions.checkArgument(arg instanceof Entity, "Arg must be Entity");
		Entity entity = (Entity)arg;

		Preconditions.checkArgument(entity.worldObj instanceof WorldServer, "Invalid side");
		WorldServer server = (WorldServer)entity.worldObj;
		Set<EntityPlayerMP> players = NetUtils.getPlayersWatchingEntity(server, entity);

		for (EntityPlayerMP player : players) {
			NetworkDispatcher dispatcher = NetUtils.getPlayerDispatcher(player);
			result.add(dispatcher);
		}
	}

}
