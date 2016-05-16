package thexnator.furnituremod.network.senders;

import io.netty.channel.Channel;
import net.minecraft.entity.Entity;
import thexnator.furnituremod.network.DimCoord;
import thexnator.furnituremod.network.ExtendedOutboundHandler;
import thexnator.furnituremod.network.IPacketTargetSelector;
import thexnator.furnituremod.network.targets.SelectChunkWatchers;
import thexnator.furnituremod.network.targets.SelectEntityWatchers;

public class ExtPacketSenderFactory {

	public static ITargetedPacketSender<DimCoord> createBlockSender(Channel channel) {
		return new ExtTargetedPacketSender<DimCoord>(channel, SelectChunkWatchers.INSTANCE) {
			@Override
			protected void configureChannel(Channel channel, DimCoord target) {
				super.configureChannel(channel, target);
				setTargetAttr(channel, target);
			}
		};
	}

	public static ITargetedPacketSender<Entity> createEntitySender(Channel channel) {
		return new ExtTargetedPacketSender<Entity>(channel, SelectEntityWatchers.INSTANCE) {
			@Override
			protected void configureChannel(Channel channel, Entity target) {
				super.configureChannel(channel, target);
				setTargetAttr(channel, target);
			}
		};
	}

	private static class ExtTargetedPacketSender<T> extends TargetedPacketSenderBase<T> {

		public final IPacketTargetSelector selector;

		public ExtTargetedPacketSender(Channel channel, IPacketTargetSelector selector) {
			super(channel);
			this.selector = selector;
		}

		@Override
		protected void configureChannel(Channel channel, T target) {
			channel.attr(ExtendedOutboundHandler.MESSAGETARGET).set(selector);
		}
	}

}
