package thexnator.furnituremod.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class FurnitureRecipes
{
	public static void init()
	{
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.chair_block),
				   "W  ",
				   "WWW",
				   "L L",
				   'W', Blocks.planks, 'L', Blocks.log
				        );

		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.table_block),
						  "SSS",
					      "W W",
						  "W W",
					        'W', Blocks.log, 'S', Blocks.wooden_slab
								);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.sofa_block),
						   "W  ",
						   "WWW",
						   "WWW",
						   'W', Blocks.wool
								);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.counter_block),
							  "BBB",
							  "QQ ",
							  "QQ ",
							  'B', Blocks.lapis_block, 'Q', Blocks.quartz_block
								);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.countercorner_block),
									"BBB",
									"QQQ",
									"QQQ",
									'Q', Blocks.quartz_block, 'B', Blocks.lapis_block
										);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.coffeetable_block),
								  "GGG",
								  "W W",
								  "W W",
								  'W', Blocks.planks, 'G', Blocks.glass
									);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.washingmachine_block),
								  "QQQ",
								  "QGQ",
								  "QQQ",
								  'Q', Blocks.quartz_block, 'G', Blocks.glass
									);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.coffeetable_block),
								  "QLQ",
								  "LQL",
								  "QLQ",
								  'Q', Blocks.quartz_block, 'L', Blocks.lapis_block
									);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.hedge_block),
								  "LLL",
								  "LLL",
								  'L', Blocks.leaves
										);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.wardrobe_block),
							   "WWW",
							   "IWI",
							   "WWW",
							   'W', Blocks.planks, 'I', Blocks.iron_block
								);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.computer_block),
								"CCC",
								"CGC",
								"RCR",
								'C', Blocks.stained_hardened_clay, 'G', Blocks.glass, 'R', Blocks.redstone_block
									);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.desk_block),
		"CCC",
		"Q Q",
		"Q Q",
		'Q', Blocks.quartz_block, 'C', Blocks.stained_hardened_clay
		);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureItems.freshner_item),
		"CCC",
		"CFC",
		"CCC",
		'F', Blocks.red_flower, 'C', Blocks.stained_hardened_clay
		);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureItems.superfreshner_item),
		"CCC",
		"GFG",
		"CCC",
		'F', Blocks.red_flower, 'C', Blocks.stained_hardened_clay, 'G', Blocks.gold_block
		);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.desk_block),
		"CCC",
		"Q Q",
		"Q Q",
		'C', Blocks.stained_hardened_clay, 'Q', Blocks.quartz_block
		);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.wardrobe_block),
		"WWW",
		"WDW",
		"WWW",
		'W', Blocks.planks, 'D', Blocks.diamond_block
		);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.fishtank_block),
		"CCC",
		"CWC",
		"CCC",
		'C', Blocks.stained_hardened_clay, 'W', Items.water_bucket
		);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.shelf_block),
		"WWW",
		"S S",
		'S', Blocks.oak_stairs, 'W', Blocks.planks
		);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.printer),
		"PPP",
		"QQQ",
		"CRC",
		'C', Blocks.stained_hardened_clay, 'P', Items.paper, 'Q', Blocks.quartz_block
		);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.book),
		"BBB",
		"BBB",
		"BBB",
		'B', Items.book
		);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.picketfencecorner),
		"P",
		'P', FurnitureBlocks.picketfence_block
		);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.officechair),
		"WWW",
		"WCW",
		" C ",
		'C', Blocks.stained_hardened_clay, 'W', Blocks.wool
		);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.picketfence_block),
		"Q Q",
		"QQQ",
		"Q Q",
		'Q', Blocks.quartz_block
		);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.barrel_block),
		"W W",
		"W W",
		"WWW",
		'W', Blocks.planks
		);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.tile_block),
		"WBW",
		"BWB",
		"WBW",
		'B', Blocks.stained_hardened_clay, 'W', Blocks.quartz_block
		);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.oven_block),
		"QQQ",
		"QCQ",
		"QQQ",
		'C', Blocks.coal_block, 'Q', Blocks.quartz_block
		);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.mug),
		"Q Q",
		"S Q",
		"QQQ",
		'Q', Blocks.quartz_block, 'S', Blocks.quartz_stairs
		);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureItems.phone),
		"BRB",
		"BGB",
		"BRB",
		'B', Blocks.stained_hardened_clay, 'R', Blocks.redstone_block, 'G', Blocks.glass
		);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureItems.freshner_item),
		"CCC",
		"CGC",
		"CCC",
		'C', Blocks.stained_hardened_clay, 'D', Blocks.gold_block
		);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureItems.superfreshner_item),
		"FDG",
		'F', FurnitureItems.freshner_item, 'D', Items.diamond, 'G', Blocks.gold_block
		);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.paperstack),
		"PPP",
		"PPP",
		"PPP",
		'P', Items.paper
		);
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.dolphin),
		"FF",
		"WW",
		'F', Items.fish, 'W', Blocks.wool
		);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureItems.hangglider),
		"LSR",
		'L', FurnitureItems.gliderwingleft, 'R', FurnitureItems.gliderwingright, 'S', Items.string
		);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureItems.gliderwingleft),
		" SL",
		"SLL",
		"LLL",
		'L', Items.leather, 'S', Items.stick
		);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureItems.gliderwingright),
		"LS ",
		"LLS",
		"LLL",
		'L', Items.leather, 'S', Items.stick
		);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.drawers),
		"WWW",
		"PCP",
		"PPP",
		'W', Blocks.log, 'P', Blocks.planks, 'C', Blocks.chest
		);
		
		GameRegistry.addRecipe(new ItemStack(FurnitureBlocks.bedside_cabinet),
		"WWW",
		"GCG",
		"PPP",
		'W', Blocks.log, 'P', Blocks.planks, 'C', Blocks.chest, 'G', Blocks.glass
		);
	}
}
