package thexnator.furnituremod;

import java.lang.reflect.Method;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCMessage;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import thexnator.furnituremod.api.IRecipeRegistry;
import thexnator.furnituremod.api.RecipeRegistry;
import thexnator.furnituremod.api.RecipeRegistryComm;
import thexnator.furnituremod.api.Recipes;
import thexnator.furnituremod.entity.EntityHangGlider;
import thexnator.furnituremod.events.PlayerActionEvent;
import thexnator.furnituremod.gui.GuiHandler;
import thexnator.furnituremod.handler.ConfigurationHandler;
import thexnator.furnituremod.handler.InputHandler;
import thexnator.furnituremod.init.FurnitureBlocks;
import thexnator.furnituremod.init.FurnitureItems;
import thexnator.furnituremod.init.FurnitureTileEntities;
import thexnator.furnituremod.mobs.MailMan;
import thexnator.furnituremod.network.PacketHandler;
import thexnator.furnituremod.network.event.NetworkEventManager;
import thexnator.furnituremod.proxy.CommonProxy;
import thexnator.furnituremod.renderer.PlayerBodyRenderEvent;

@Mod(modid = Reference.MODID, name = Reference.MOD_NAME, version = Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS, acceptedMinecraftVersions=Reference.ACCEPTED_MC_VERSION)
public class FurnitureMod implements IWorldGenerator
{	
	MailMan MailMan = new MailMan(Minecraft.getMinecraft().theWorld);
	
	private static final int ENTITY_HANGGLIDER_ID = 701;
	
	@Instance(Reference.MODID)
	public static FurnitureMod instance;
	
	@SidedProxy(modId=Reference.MODID, clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	private final ApiSetup apiSetup = new ApiSetup();
	
	public static final FurnitureTab tabFurniture = new FurnitureTab("tabFurniture");
	
	public static int renderIdFull;

	public static int renderIdFlat;
	
	public static ResourceLocation location(String path) 
	{
		return new ResourceLocation("fm", path);
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{
		chunkX = chunkX * 16;
		chunkZ = chunkZ * 16;
		if(world.provider.getDimensionId()==-1)MailMan.generateNether(world, random, chunkX, chunkZ);
		if(world.provider.getDimensionId()==0)MailMan.generateSurface(world, random, chunkX, chunkZ);
	}
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		NetworkEventManager.INSTANCE
		.startRegistration()
		.register(PlayerActionEvent.class);
		
		MailMan.instance = this.instance;
		
		MailMan.preInit(event);
		
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		
		FurnitureBlocks.init();
		FurnitureBlocks.register();
		FurnitureItems.init();
		FurnitureItems.register();
		FurnitureTileEntities.register();
		
		PacketHandler.init();
		
		EntityRegistry.registerModEntity(EntityHangGlider.class, "Hang Glider", ENTITY_HANGGLIDER_ID, FurnitureMod.instance, 64, 1, true);
		
		//MinecraftForge.EVENT_BUS.register(MapDataManager.instance);
		
		MinecraftForge.EVENT_BUS.register(new EntityEventHandler());
		
		if (event.getSide() == Side.CLIENT)
		{
			FMLCommonHandler.instance().bus().register(new InputHandler());
		}
		
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		
		proxy.preInit();
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		GameRegistry.registerWorldGenerator(this, 1);
		OBJLoader.instance.addDomain("fm");
		proxy.registerRenderInformation();
		proxy.registerRenders();
		proxy.init();
		//FurnitureEvents.init();
		
		MailMan.load(event);
		
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	}
	
	@EventHandler
	public void serverInit(FMLServerStartingEvent event)
	{
		MailMan.serverLoad(event);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
		RecipeRegistry.registerDefaultRecipes();
		RecipeRegistry.registerConfigRecipes();
		Recipes.addCommRecipesToLocal();
		Recipes.updateDataList();	
	}
	
	@EventHandler
	public void processIMC(FMLInterModComms.IMCEvent event)
	{
		if (event.getMessages().size() > 0)
		{
			if (ConfigurationHandler.api_debug)
			{
				System.out.println("RecipeAPI (InterModComm): Registering recipes from " + event.getMessages().size() + " mod(s).");
			}
		}
		for (IMCMessage imcMessage : event.getMessages())
		{
			if (!imcMessage.isStringMessage())
				continue;

			if (imcMessage.key.equalsIgnoreCase("register"))
			{
				register(imcMessage.getStringValue(), imcMessage.getSender());
			}
		}
	}
	
	public void register(String method, String modid)
	{
		String[] data = method.split("\\.");
		String methodName = data[data.length - 1];
		String className = method.substring(0, method.length() - methodName.length() - 1);
		String modName = Loader.instance().getIndexedModList().get(modid).getName();
		
		try
		{
			Class clazz = Class.forName(className);
			Method registerMethod = clazz.getDeclaredMethod(methodName, IRecipeRegistry.class);
			registerMethod.invoke(null, (IRecipeRegistry) RecipeRegistryComm.getInstance(modName));
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
