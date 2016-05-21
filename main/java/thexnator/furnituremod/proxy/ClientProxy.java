package thexnator.furnituremod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thexnator.furnituremod.ClientTickHandler;
import thexnator.furnituremod.FurnitureMod;
import thexnator.furnituremod.GliderPlayerRenderHandler;
import thexnator.furnituremod.entity.EntityHangGlider;
import thexnator.furnituremod.events.PlayerActionEvent;
import thexnator.furnituremod.events.client.RenderEvents;
import thexnator.furnituremod.init.FurnitureBlocks;
import thexnator.furnituremod.init.FurnitureEvents;
import thexnator.furnituremod.init.FurnitureItems;
import thexnator.furnituremod.init.FurnitureRecipes;
import thexnator.furnituremod.items.ItemHangGlider;
import thexnator.furnituremod.mobs.MailMan;
import thexnator.furnituremod.render.tileentity.FishTankRenderer;
import thexnator.furnituremod.render.tileentity.OvenRenderer;
import thexnator.furnituremod.renderer.PlayerBodyRenderEvent;
import thexnator.furnituremod.renderer.entity.EntityHangGliderRenderer;
import thexnator.furnituremod.renderer.item.ItemRendererHangGlider;
import thexnator.furnituremod.tileentity.TileEntityFishTank;
import thexnator.furnituremod.tileentity.TileEntityOven;
import thexnator.furnituremod.util.renderers.GL11U;
import thexnator.furnituremod.util.utilclasses.PrintUtil;

public class ClientProxy extends CommonProxy
{	
	@SideOnly(Side.CLIENT)
	public static GliderPlayerRenderHandler renderEvents;
	private AbstractClientPlayer player;
	
	public static boolean rendering = false;
	public static Entity renderEntity = null;
	public static Entity backupEntity = null;

	@Override
	public void registerRenders()
	{
		RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
		FurnitureItems.registerRenders();
		FurnitureBlocks.registerRenders();
		MailMan.registerRenderers();
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityOven.class, new OvenRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFishTank.class, new FishTankRenderer());
		RenderingRegistry.registerEntityRenderingHandler(EntityHangGlider.class, new EntityHangGliderRenderer(rendermanager));
	}
	
	@Override
	public EntityPlayer getClientPlayer()
	{
		return Minecraft.getMinecraft().thePlayer;
	}

	@Override
	public boolean isSinglePlayer()
	{
		return Minecraft.getMinecraft().isSingleplayer();
	}

	@Override
	public boolean isDedicatedServer()
	{
		return false;
	}

	@Override
	public void preInit()
	{
		MinecraftForge.EVENT_BUS.register(new ClientTickHandler());
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@Override
	public void init()
	{
		//FurnitureEvents.init();
		FurnitureRecipes.init();
		System.out.println("ClientProxy init");
	}
	
	@Override
	public void registerRenderInformation() 
	{
		if (FurnitureItems.hangglider != null) {
			RenderingRegistry.registerEntityRenderingHandler(EntityHangGlider.class, new IRenderFactory<EntityHangGlider>() {

				@Override
				public Render<EntityHangGlider> createRenderFor(RenderManager manager) {
					return new EntityHangGliderRenderer(manager);
				}
			});

			MinecraftForge.EVENT_BUS.register(new GliderPlayerRenderHandler());
		}
	}
}

