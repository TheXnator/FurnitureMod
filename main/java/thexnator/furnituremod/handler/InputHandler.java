package thexnator.furnituremod.handler;

import java.util.Random;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import thexnator.furnituremod.blocks.BlockOfficeChair;
import thexnator.furnituremod.init.FurnitureBlocks;
import thexnator.furnituremod.network.PacketHandler;
import thexnator.furnituremod.network.message.MessageClickSound;

public class InputHandler
{
	private static final IProperty<EnumFacing> DIRECTION = BlockOfficeChair.DIRECTION;
	public static KeyBinding key_clicksound;
	public static boolean keyclickPressed = false;
	public static boolean keyHasBeenPressed = false;
	private Random rand = new Random();

	public InputHandler()
	{
		key_clicksound = new KeyBinding("key.clicksound.desc", Keyboard.KEY_C, "keys.fm.category");
		ClientRegistry.registerKeyBinding(key_clicksound);
	}

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event)
	{
		if (key_clicksound.isPressed())
		{
			keyclickPressed = true;
		}
	}

	@SubscribeEvent
	public void playerTick(PlayerTickEvent event)
	{
		if (event.player.worldObj.getBlockState(new BlockPos((int) Math.floor(event.player.posX), (int) Math.floor(event.player.posY), (int) Math.floor(event.player.posZ))).getBlock() == FurnitureBlocks.officechair)
		{
			if (keyclickPressed)
			{
				keyclickPressed = false;
				PacketHandler.INSTANCE.sendToServer(new MessageClickSound(event.player.posX, event.player.posY, event.player.posZ));
			}
		}
	}

	private IBlockState onBlockSit(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return null;
	}

	public KeyBinding getClickSoundKey()
	{
		return key_clicksound;
	}
}
