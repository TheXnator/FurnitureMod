package thexnator.furnituremod;

import java.awt.Color;
import java.util.ArrayList;

import amerifrance.guideapi.ModInformation;
import amerifrance.guideapi.api.GuideRegistry;
import amerifrance.guideapi.api.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.abstraction.EntryAbstract;
import amerifrance.guideapi.api.abstraction.IPage;
import amerifrance.guideapi.api.base.Book;
import amerifrance.guideapi.api.base.EntryBase;
import amerifrance.guideapi.api.util.PageHelper;
import amerifrance.guideapi.category.CategoryItemStack;
import amerifrance.guideapi.category.CategoryResourceLocation;
import amerifrance.guideapi.entry.EntryItemStack;
import amerifrance.guideapi.entry.EntryResourceLocation;
import amerifrance.guideapi.page.PageFurnaceRecipe;
import amerifrance.guideapi.page.PageIRecipe;
import amerifrance.guideapi.page.PageImage;
import amerifrance.guideapi.page.PageSound;
import amerifrance.guideapi.page.PageText;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import thexnator.furnituremod.init.FurnitureBlocks;

public class IntroBook
{

    public static void registerTests(int amountOfBooks)
    {
        for (int i = 0; i < amountOfBooks; i++)
            testBook(new Book(), "TestBook" + i);
    }

    public static void testBook(Book book, String title) 
    {
        PageText page1 = new PageText("HERE IS SOME TEXT FOR YOU TO DRAW LEWL. I AM VERY LONG FOR NOTHING MATE");
        PageText page2 = new PageText("HERE IS SOME TEXT FOR YOU TO DRAW LEWL. I AM VERY LONG FOR NOTHING MATE.\n\nNew paragraph!");
        PageImage page3 = new PageImage(new ResourceLocation(ModInformation.GUITEXLOC + "testimage.png"));
        PageIRecipe page4 = new PageIRecipe(GameRegistry.addShapedRecipe(new ItemStack(Items.diamond), "XXX", "YYY", "ZZZ", 'X', Items.apple, 'Y', Blocks.beacon, 'Z', Items.beef));
        ShapedOreRecipe shapedOreRecipe = new ShapedOreRecipe(Items.beef, "XXX", "YYY", "ZZZ", 'X', "stairWood", 'Y', "stone", 'Z', "ingotIron");
        PageIRecipe page5 = new PageIRecipe(shapedOreRecipe);
        ArrayList<ItemStack> shapelessList = new ArrayList<ItemStack>();
        shapelessList.add(new ItemStack(Items.cauldron));
        shapelessList.add(new ItemStack(Items.golden_carrot));
        ShapelessRecipes shapelessRecipes = new ShapelessRecipes(new ItemStack(Items.blaze_rod), shapelessList);
        PageIRecipe page6 = new PageIRecipe(shapelessRecipes);
        ShapelessOreRecipe shapelessOreRecipe = new ShapelessOreRecipe(new ItemStack(Items.baked_potato), "ingotIron", "stairWood");
        PageIRecipe page7 = new PageIRecipe(shapelessOreRecipe);
        PageSound page8 = new PageSound(page6, "mob.pig.say");
        PageFurnaceRecipe page9 = new PageFurnaceRecipe(new ItemStack(Items.potato));
        PageFurnaceRecipe page10 = new PageFurnaceRecipe(new ItemStack(Items.diamond_axe));

        ArrayList<IPage> pages = new ArrayList<IPage>();
        pages.add(page1);
        pages.add(page2);
        pages.addAll(PageHelper.pagesForLongText("HERE IS SOME TEXT FOR YOU TO DRAW LEWL. I AM VERY LONG FOR NOTHING MATE", new ItemStack(Items.diamond)));
        pages.add(page3);
        pages.add(page4);
        pages.add(page5);
        pages.add(page6);
        pages.add(page7);
        pages.add(page8);
        pages.add(page9);
        pages.add(page10);

        EntryItemStack entry1 = new EntryItemStack(pages, "TestEntry1", new ItemStack(Items.potato));
        EntryItemStack entry2 = new EntryItemStack(pages, "TestEntry2", new ItemStack(Blocks.dirt));
        EntryBase entry3 = new EntryBase(pages, "TestEntry3");
        EntryResourceLocation entry4 = new EntryResourceLocation(pages, "TestEntry4", new ResourceLocation(ModInformation.GUITEXLOC + "testimage.png"));
        ArrayList<EntryAbstract> entries = new ArrayList<EntryAbstract>();
        entries.add(entry1);
        entries.add(entry2);
        entries.add(entry3);
        entries.add(entry4);

        CategoryResourceLocation category1 = new CategoryResourceLocation(entries, "TestCategory1", new ResourceLocation(ModInformation.GUITEXLOC + "testimage.png"));
        CategoryItemStack category2 = new CategoryItemStack(entries, "Table", new ItemStack(FurnitureBlocks.table_block));
        CategoryItemStack category3 = new CategoryItemStack(entries, "Chair", new ItemStack(FurnitureBlocks.chair_block));
        CategoryItemStack category4 = new CategoryItemStack(entries, "Coffee Table", new ItemStack(FurnitureBlocks.coffeetable_block));
        CategoryItemStack category5 = new CategoryItemStack(entries, "Barrel", new ItemStack(FurnitureBlocks.barrel_block));
        CategoryItemStack category6 = new CategoryItemStack(entries, "Computer", new ItemStack(FurnitureBlocks.computer_block));
        CategoryItemStack category7 = new CategoryItemStack(entries, "Counter", new ItemStack(FurnitureBlocks.counter_block));
        CategoryItemStack category8 = new CategoryItemStack(entries, "Corner Counter", new ItemStack(FurnitureBlocks.countercorner_block));
        CategoryItemStack category9 = new CategoryItemStack(entries, "Desk", new ItemStack(FurnitureBlocks.desk_block));
        CategoryItemStack category10 = new CategoryItemStack(entries, "Fish Tank", new ItemStack(FurnitureBlocks.fishtank_block));
        CategoryItemStack category11 = new CategoryItemStack(entries, "Hedge", new ItemStack(FurnitureBlocks.hedge_block));
        CategoryItemStack category12 = new CategoryItemStack(entries, "Oven", new ItemStack(FurnitureBlocks.oven_block));
        CategoryItemStack category13 = new CategoryItemStack(entries, "Picket Fence", new ItemStack(FurnitureBlocks.picketfence_block));
        CategoryItemStack category14 = new CategoryItemStack(entries, "Shelf", new ItemStack(FurnitureBlocks.shelf_block));
        CategoryItemStack category15 = new CategoryItemStack(entries, "Sofa", new ItemStack(FurnitureBlocks.sofa_block));
        CategoryItemStack category16 = new CategoryItemStack(entries, "Tile", new ItemStack(FurnitureBlocks.tile_block));
        CategoryItemStack category17 = new CategoryItemStack(entries, "Wardrobe", new ItemStack(FurnitureBlocks.wardrobe_block));
        CategoryItemStack category18 = new CategoryItemStack(entries, "Washing Machine", new ItemStack(FurnitureBlocks.washingmachine_block));
        CategoryItemStack category19 = new CategoryItemStack(entries, "Bed", new ItemStack(FurnitureBlocks.bed));
        CategoryItemStack category20 = new CategoryItemStack(entries, "Bedside Cabinet", new ItemStack(FurnitureBlocks.bedside_cabinet));
        CategoryItemStack category21 = new CategoryItemStack(entries, "Book", new ItemStack(FurnitureBlocks.book));
        CategoryItemStack category22 = new CategoryItemStack(entries, "Dolphin", new ItemStack(FurnitureBlocks.dolphin));
        CategoryItemStack category23 = new CategoryItemStack(entries, "Drawers", new ItemStack(FurnitureBlocks.drawers));
        CategoryItemStack category24 = new CategoryItemStack(entries, "Mirror", new ItemStack(FurnitureBlocks.mirror));
        CategoryItemStack category25 = new CategoryItemStack(entries, "Mug", new ItemStack(FurnitureBlocks.mug));
        CategoryItemStack category26 = new CategoryItemStack(entries, "Office Chair", new ItemStack(FurnitureBlocks.officechair));
        CategoryItemStack category27 = new CategoryItemStack(entries, "Paper Stack", new ItemStack(FurnitureBlocks.paperstack));
        CategoryItemStack category28 = new CategoryItemStack(entries, "Printer", new ItemStack(FurnitureBlocks.printer));

        ArrayList<CategoryAbstract> categories = new ArrayList<CategoryAbstract>();
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);
        categories.add(category4);
        categories.add(category5);

        book.setCategoryList(categories);
        book.setTitle(title);
        book.setWelcomeMessage(title);
        book.setDisplayName(title);
        book.setColor(new Color(171, 80, 30));
        book.setSpawnWithBook(false);

        GuideRegistry.registerBook(book, true);
    }
}


