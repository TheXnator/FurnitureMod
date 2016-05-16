package thexnator.furnituremod.items;

import java.util.Map;

import com.google.common.collect.MapMaker;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thexnator.furnituremod.FurnitureMod;
import thexnator.furnituremod.GliderPlayerRenderHandler;
import thexnator.furnituremod.entity.EntityHangGlider;
import thexnator.furnituremod.renderer.PlayerBodyRenderEvent;
import thexnator.furnituremod.renderer.entity.EntityHangGliderRenderer;

public class ItemHangGlider extends Item {

	private static Map<EntityPlayer, EntityHangGlider> spawnedGlidersMap = new MapMaker().weakKeys().weakValues().makeMap();

	public ItemHangGlider() {
		setCreativeTab(FurnitureMod.tabFurniture);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		if (!world.isRemote && player != null) {
			EntityHangGlider glider = spawnedGlidersMap.get(player);
			if (glider != null){
				despawnGlider(player, glider);
			}else{
				spawnGlider(player);
			}
		}
		return itemStack;
	}

	private static void despawnGlider(EntityPlayer player, EntityHangGlider glider) {
		glider.setDead();
		spawnedGlidersMap.remove(player);
	}

	private static void spawnGlider(EntityPlayer player) {
		EntityHangGlider glider = new EntityHangGlider(player.worldObj, player);
		glider.setPositionAndRotation(player.posX, player.posY, player.posZ, player.rotationPitch, player.rotationYaw);
		player.worldObj.spawnEntityInWorld(glider);
		spawnedGlidersMap.put(player, glider);
	}
}
