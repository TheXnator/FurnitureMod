package thexnator.furnituremod.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thexnator.furnituremod.ClientTickHandler;
import thexnator.furnituremod.GliderPlayerRenderHandler;
import thexnator.furnituremod.entity.EntityHangGlider;
import thexnator.furnituremod.init.FurnitureBlocks;
import thexnator.furnituremod.init.FurnitureItems;
import thexnator.furnituremod.init.FurnitureRecipes;
import thexnator.furnituremod.mobs.MailMan;
import thexnator.furnituremod.render.tileentity.FishTankRenderer;
import thexnator.furnituremod.render.tileentity.OvenRenderer;
import thexnator.furnituremod.renderer.PlayerBodyRenderEvent;
import thexnator.furnituremod.renderer.entity.EntityHangGliderRenderer;
import thexnator.furnituremod.tileentity.TileEntityFishTank;
import thexnator.furnituremod.tileentity.TileEntityOven;

public class ClientProxy extends CommonProxy
{	
	@SideOnly(Side.CLIENT)
	public static GliderPlayerRenderHandler renderEvents;
	private AbstractClientPlayer player;
	
	public static boolean rendering = false;
	public static Entity renderEntity = null;
	public static Entity backupEntity = null;

	@SuppressWarnings({ "unchecked", "deprecation" })
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
		player = Minecraft.getMinecraft().thePlayer;
		MinecraftForge.EVENT_BUS.register(new ClientTickHandler());
		MinecraftForge.EVENT_BUS.register(new PlayerBodyRenderEvent(player, 0));
		MinecraftForge.EVENT_BUS.register(new GliderPlayerRenderHandler());
		MinecraftForge.EVENT_BUS.register(this);
		//IntroBook.registerTests(1);
	}
	
	@Override
	public void init()
	{
		FurnitureRecipes.init();
		System.out.println("ClientProxy init");
	}
	
	@Override
	public void registerRenderInformation() 
	{
		if (FurnitureItems.hangglider != null) {
			RenderingRegistry.registerEntityRenderingHandler(EntityHangGlider.class, new IRenderFactory<EntityHangGlider>() 
			{

				@Override
				public Render<EntityHangGlider> createRenderFor(RenderManager manager)
				{
					return new EntityHangGliderRenderer(manager);
				}
			});

			MinecraftForge.EVENT_BUS.register(new GliderPlayerRenderHandler());
		}
	}
}

