package thexnator.furnituremod.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thexnator.furnituremod.FurnitureMod;
import thexnator.furnituremod.Reference;
import thexnator.furnituremod.items.FreshnerItem;
import thexnator.furnituremod.items.ItemHangGlider;
import thexnator.furnituremod.items.ItemPhone;
import thexnator.furnituremod.items.SuperFreshnerItem;
import thexnator.furnituremod.items.XnatorItem;

public class FurnitureItems 
{	
	public static Item xnator_item;
	public static Item freshner_item;
	public static Item superfreshner_item;
	public static Item phone;
	//Bedroom Update
	public static Item introbook;
	//Glider
	public static Item hangglider;
	public static Item gliderwingleft;
	public static Item gliderwingright;
	
	public static void init()
	{
		xnator_item = new XnatorItem().setUnlocalizedName("xnator_item").setCreativeTab(FurnitureMod.tabFurniture);
		freshner_item = new FreshnerItem().setUnlocalizedName("freshner_item").setCreativeTab(FurnitureMod.tabFurniture);
		superfreshner_item = new SuperFreshnerItem().setUnlocalizedName("superfreshner_item").setCreativeTab(FurnitureMod.tabFurniture);
		phone = new ItemPhone().setUnlocalizedName("phone").setCreativeTab(FurnitureMod.tabFurniture);
		hangglider = new ItemHangGlider().setUnlocalizedName("hangglider").setCreativeTab(FurnitureMod.tabFurniture);
		gliderwingleft = new Item().setUnlocalizedName("gliderwingleft").setCreativeTab(FurnitureMod.tabFurniture);
		gliderwingright = new Item().setUnlocalizedName("gliderwingright").setCreativeTab(FurnitureMod.tabFurniture);
	}
	
	public static void register()
	{
		GameRegistry.registerItem(xnator_item, xnator_item.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(freshner_item, freshner_item.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(superfreshner_item, superfreshner_item.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(phone, phone.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(hangglider, hangglider.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(gliderwingleft, gliderwingleft.getUnlocalizedName().substring(5));
		GameRegistry.registerItem(gliderwingright, gliderwingright.getUnlocalizedName().substring(5));
	}
	
	public static void registerRenders()
	{
		registerRender(xnator_item);
		registerRender(freshner_item);
		registerRender(superfreshner_item);
		registerRender(phone);
		registerRender(hangglider);
		registerRender(gliderwingleft);
		registerRender(gliderwingright);
	}
	
	public static void registerRender(Item item)
	{
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
