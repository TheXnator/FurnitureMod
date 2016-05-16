package thexnator.furnituremod.network.event;


import java.io.IOException;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.network.handshake.NetworkDispatcher;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

public abstract class NetworkEvent extends Event {

	final List<NetworkEvent> replies = Lists.newArrayList();

	NetworkDispatcher dispatcher;

	public EntityPlayer sender;

	protected abstract void readFromStream(PacketBuffer input) throws IOException;

	protected abstract void writeToStream(PacketBuffer output) throws IOException;

	protected void appendLogInfo(List<String> info) {}

	public void reply(NetworkEvent reply) {
		Preconditions.checkState(dispatcher != null, "Can't call this method outside event handler");
		reply.dispatcher = dispatcher;
		this.replies.add(reply);
	}
}
