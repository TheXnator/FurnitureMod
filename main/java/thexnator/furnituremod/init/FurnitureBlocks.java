package thexnator.furnituremod.init;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thexnator.furnituremod.FurnitureMod;
import thexnator.furnituremod.Reference;
import thexnator.furnituremod.blocks.BlockBarrel;
import thexnator.furnituremod.blocks.BlockBedsideCabinet;
import thexnator.furnituremod.blocks.BlockBook;
import thexnator.furnituremod.blocks.BlockChair;
import thexnator.furnituremod.blocks.BlockCoffeeTable;
import thexnator.furnituremod.blocks.BlockComputer;
import thexnator.furnituremod.blocks.BlockCounter;
import thexnator.furnituremod.blocks.BlockCounterCorner;
import thexnator.furnituremod.blocks.BlockDesk;
import thexnator.furnituremod.blocks.BlockDolphinNormal;
import thexnator.furnituremod.blocks.BlockDrawers;
import thexnator.furnituremod.blocks.BlockFishTank;
import thexnator.furnituremod.blocks.BlockHedge;
import thexnator.furnituremod.blocks.BlockMirror;
import thexnator.furnituremod.blocks.BlockMug;
import thexnator.furnituremod.blocks.BlockOfficeChair;
import thexnator.furnituremod.blocks.BlockOven;
import thexnator.furnituremod.blocks.BlockPaperStack;
import thexnator.furnituremod.blocks.BlockPicketFence;
import thexnator.furnituremod.blocks.BlockPrinter;
import thexnator.furnituremod.blocks.BlockShelf;
import thexnator.furnituremod.blocks.BlockSingleBedNormal;
import thexnator.furnituremod.blocks.BlockSofa;
import thexnator.furnituremod.blocks.BlockTable;
import thexnator.furnituremod.blocks.BlockTile;
import thexnator.furnituremod.blocks.BlockWardrobe;
import thexnator.furnituremod.blocks.BlockWashingMachine;

public class FurnitureBlocks {
	
	//Initial Furniture
	public static Block table_block;
	public static Block chair_block;
	public static Block sofa_block;
	public static Block barrel_block;
	public static Block counter_block;
	public static Block countercorner_block;
	public static Block washingmachine_block;
	public static Block coffeetable_block;
	public static Block tile_block;
	public static Block oven_block;
	public static Block picketfence_block;
	public static Block hedge_block;
	public static Block computer_block;
	public static Block wardrobe_block;
	public static Block desk_block;
	public static Block shelf_block;
	public static Block fishtank_block;
	public static Block book;
	public static Block printer;
	public static Block picketfencecorner;
	public static Block officechair;
	public static Block mug;
	public static Block paperstack;
	//Bedroom Update
	public static Block dolphin;
	public static Block bed;
	public static Block drawers;
	public static Block bedside_cabinet;
	public static Block mirror;
	
	public static void init()
	{
		//Initial Furniture
		table_block = new BlockTable(Material.wood).setUnlocalizedName("table_block").setCreativeTab(FurnitureMod.tabFurniture);
		chair_block = new BlockChair(Material.wood).setUnlocalizedName("chair_block").setCreativeTab(FurnitureMod.tabFurniture);
		sofa_block = new BlockSofa(Material.cloth).setUnlocalizedName("sofa_block").setCreativeTab(FurnitureMod.tabFurniture);
		barrel_block = new BlockBarrel(Material.wood).setUnlocalizedName("barrel_block").setCreativeTab(FurnitureMod.tabFurniture);
		counter_block = new BlockCounter(Material.rock).setUnlocalizedName("counter_block").setCreativeTab(FurnitureMod.tabFurniture);
		countercorner_block = new BlockCounterCorner(Material.rock).setUnlocalizedName("countercorner_block").setCreativeTab(FurnitureMod.tabFurniture);
		washingmachine_block = new BlockWashingMachine(Material.rock).setUnlocalizedName("washingmachine_block").setCreativeTab(FurnitureMod.tabFurniture);
		coffeetable_block = new BlockCoffeeTable(Material.wood).setUnlocalizedName("coffeetable_block").setCreativeTab(FurnitureMod.tabFurniture);
		tile_block = new BlockTile(Material.rock).setUnlocalizedName("tile_block").setCreativeTab(FurnitureMod.tabFurniture);
		oven_block = new BlockOven(Material.rock).setUnlocalizedName("oven_block").setCreativeTab(FurnitureMod.tabFurniture);
		picketfence_block = new BlockPicketFence(Material.rock).setUnlocalizedName("picketfence_block").setCreativeTab(FurnitureMod.tabFurniture);
		hedge_block = new BlockHedge(Material.leaves).setUnlocalizedName("hedge_block").setCreativeTab(FurnitureMod.tabFurniture);
		computer_block = new BlockComputer(Material.rock).setUnlocalizedName("computer_block").setCreativeTab(FurnitureMod.tabFurniture);
		desk_block = new BlockDesk(Material.wood).setUnlocalizedName("desk_block").setCreativeTab(FurnitureMod.tabFurniture);
		wardrobe_block = new BlockWardrobe(Material.wood).setUnlocalizedName("wardrobe_block").setCreativeTab(FurnitureMod.tabFurniture);
		fishtank_block = new BlockFishTank(Material.rock).setUnlocalizedName("fishtank_block").setCreativeTab(FurnitureMod.tabFurniture);
		shelf_block = new BlockShelf(Material.wood).setUnlocalizedName("shelf_block").setCreativeTab(FurnitureMod.tabFurniture);
		book = new BlockBook(Material.cloth).setUnlocalizedName("book").setCreativeTab(FurnitureMod.tabFurniture);
		printer = new BlockPrinter(Material.iron).setUnlocalizedName("printer").setCreativeTab(FurnitureMod.tabFurniture);
		picketfencecorner = new BlockPicketFence(Material.rock).setUnlocalizedName("picketfencecorner").setCreativeTab(FurnitureMod.tabFurniture);
		officechair = new BlockOfficeChair(Material.cloth).setUnlocalizedName("officechair").setCreativeTab(FurnitureMod.tabFurniture);
		mug = new BlockMug(Material.rock).setUnlocalizedName("mug").setCreativeTab(FurnitureMod.tabFurniture);
		paperstack = new BlockPaperStack(Material.grass).setUnlocalizedName("paperstack").setCreativeTab(FurnitureMod.tabFurniture);
		//Bedroom Update
		dolphin = new BlockDolphinNormal().setUnlocalizedName("dolphin").setCreativeTab(FurnitureMod.tabFurniture);
		bed = new BlockSingleBedNormal(Material.cloth).setUnlocalizedName("bed");
		drawers = new BlockDrawers(Material.wood).setUnlocalizedName("drawers").setCreativeTab(FurnitureMod.tabFurniture);
		bedside_cabinet = new BlockBedsideCabinet(Material.wood).setUnlocalizedName("bedside_cabinet").setCreativeTab(FurnitureMod.tabFurniture);
		mirror = new BlockMirror(Material.glass).setUnlocalizedName("mirror").setCreativeTab(FurnitureMod.tabFurniture);
	}
	
	public static void register()
	{
		//Initial Furniture
		GameRegistry.registerBlock(table_block, table_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(chair_block, chair_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(sofa_block, sofa_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(barrel_block, barrel_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(counter_block, counter_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(countercorner_block, countercorner_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(washingmachine_block, washingmachine_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(coffeetable_block, coffeetable_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(tile_block, tile_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(oven_block, oven_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(picketfence_block, picketfence_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(hedge_block, hedge_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(computer_block, computer_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(desk_block, desk_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(wardrobe_block, wardrobe_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(fishtank_block, fishtank_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(shelf_block, shelf_block.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(book, book.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(printer, printer.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(picketfencecorner, picketfencecorner.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(officechair, officechair.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(mug, mug.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(paperstack, paperstack.getUnlocalizedName().substring(5));
		//Bedroom Update
		GameRegistry.registerBlock(dolphin, dolphin.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(bed, bed.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(drawers, drawers.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(bedside_cabinet, bedside_cabinet.getUnlocalizedName().substring(5));
		GameRegistry.registerBlock(mirror, mirror.getUnlocalizedName().substring(5));
	}
	
	public static void registerRenders()
	{
		//Initial Furniture
		registerRender(table_block);
		registerRender(chair_block);
		registerRender(sofa_block);
		registerRender(barrel_block);
		registerRender(counter_block);
		registerRender(countercorner_block);
		registerRender(washingmachine_block);
		registerRender(coffeetable_block);
		registerRender(tile_block);
		registerRender(oven_block);
		registerRender(picketfence_block);
		registerRender(hedge_block);
		registerRender(computer_block);
		registerRender(desk_block);
		registerRender(wardrobe_block);
		registerRender(fishtank_block);
		registerRender(shelf_block);
		registerRender(book);
		registerRender(printer);
		registerRender(picketfencecorner);
		registerRender(officechair);
		registerRender(mug);
		registerRender(paperstack);
		//Bedroom Update
		registerRender(dolphin);
		registerRender(bed);
		registerRender(drawers);
		registerRender(bedside_cabinet);
		registerRender(mirror);
	}
	
	public static void registerRender(Block block)
	{
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}
}
