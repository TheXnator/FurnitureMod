package thexnator.furnituremod.network.message;

import java.util.Random;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import thexnator.furnituremod.entity.EntitySittableBlock;

//Server Packet
public class MessageClickSound implements IMessage, IMessageHandler<MessageClickSound, IMessage>
{

	private Random rand = new Random();
	private double x, y, z;

	public MessageClickSound()
	{
	}

	public MessageClickSound(double posX, double posY, double posZ)
	{
		this.x = posX;
		this.y = posY;
		this.z = posZ;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.x = buf.readDouble();
		this.y = buf.readDouble();
		this.z = buf.readDouble();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeDouble(x);
		buf.writeDouble(y);
		buf.writeDouble(z);
	}

	@Override
	public IMessage onMessage(MessageClickSound message, MessageContext ctx)
	{
		EntityPlayerMP player = ctx.getServerHandler().playerEntity;
		if (player.ridingEntity instanceof EntitySittableBlock)
		{
			player.worldObj.playSoundEffect(message.x, message.y, message.z, "fm:clicksound", 0.75F, 1.0F);
		}
		return null;
	}
}
