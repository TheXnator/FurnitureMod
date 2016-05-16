package thexnator.furnituremod.network.event;

public interface INetworkEventType {
	public abstract NetworkEvent createPacket();

	public abstract EventDirection getDirection();

}
