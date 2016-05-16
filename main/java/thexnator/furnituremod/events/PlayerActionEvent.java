package thexnator.furnituremod.events;

import net.minecraft.network.PacketBuffer;
import thexnator.furnituremod.network.event.EventDirection;
import thexnator.furnituremod.network.event.NetworkEvent;
import thexnator.furnituremod.network.event.NetworkEventMeta;
import thexnator.furnituremod.utils.EnumUtils;

@NetworkEventMeta(direction = EventDirection.C2S)
public class PlayerActionEvent extends NetworkEvent {
	public enum Type {
		BOO
	}

	public Type type;

	public PlayerActionEvent() {}

	public PlayerActionEvent(Type type) {
		this.type = type;
	}

	@Override
	protected void readFromStream(PacketBuffer input) {
		int typeId = input.readVarIntFromBuffer();
		type = EnumUtils.fromOrdinal(Type.class, typeId);
	}

	@Override
	protected void writeToStream(PacketBuffer output) {
		output.writeVarIntToBuffer(type.ordinal());
	}

}
