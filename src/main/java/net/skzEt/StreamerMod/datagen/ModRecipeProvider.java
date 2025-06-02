package net.skzEt.StreamerMod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.skzEt.StreamerMod.Streamer;
import net.skzEt.StreamerMod.registries.ModBlocks;
import net.skzEt.StreamerMod.registries.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }
    private static final List<ItemLike> TWITCH_SMELTABLES = List.of(
            ModBlocks.TWITCH_ORE.get(),
            ModBlocks.DEEPSLATE_TWITCH_ORE.get()
    );
    private static final List<ItemLike> STINT_SMELTABLES = List.of(
            ModItems.STINTOCOIN.get()
    );
    private static final List<ItemLike> DUMPLING_SMELTABLES = List.of(
            ModItems.DUMPLING.get()
    );

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        oreSmelting(pRecipeOutput,TWITCH_SMELTABLES, RecipeCategory.MISC, ModItems.TWITCH_DIAMOND.get(), 0.1f, 200, "twitch");
        oreBlasting(pRecipeOutput, TWITCH_SMELTABLES, RecipeCategory.MISC, ModItems.TWITCH_DIAMOND.get(), 0.1f, 100, "twitch");

        oreSmelting(pRecipeOutput, STINT_SMELTABLES, RecipeCategory.MISC, ModItems.COIN_ALLOY.get(), 0f, 300, "stint");
        oreBlasting(pRecipeOutput,STINT_SMELTABLES, RecipeCategory.MISC,ModItems.COIN_ALLOY.get(),0f, 150, "stint");

        oreSmelting(pRecipeOutput, DUMPLING_SMELTABLES, RecipeCategory.MISC, ModItems.OVERCOOKED_DUMPLING.get(), 0f, 450, "dumpling");
        foodSmoking(pRecipeOutput, DUMPLING_SMELTABLES, RecipeCategory.MISC, ModItems.OVERCOOKED_DUMPLING.get(), 0f, 200, "dumpling");
        foodCampfireCooking(pRecipeOutput,DUMPLING_SMELTABLES, RecipeCategory.MISC, ModItems.OVERCOOKED_DUMPLING.get(), 0f, 600, "dumpling");
        // Twitch Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.TWITCH_BLOCK.get())
                .pattern("TTT")
                .pattern("TTT")
                .pattern("TTT")
                .define('T', ModItems.TWITCH_DIAMOND.get())
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get()))
                .save(pRecipeOutput);
        // Twitch Diamond
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TWITCH_DIAMOND.get(), 9)
                .requires(ModBlocks.TWITCH_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.TWITCH_BLOCK.get()), has(ModBlocks.TWITCH_BLOCK.get()))
                .save(pRecipeOutput);
        // Dumpling
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DUMPLING.get())
                .pattern(" W ")
                .pattern("WDW")
                .pattern(" W ")
                .define('W', Items.WHEAT)
                .define('D', ModItems.TWITCH_DIAMOND.get())
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get()))
                .save(pRecipeOutput);
        // School Boot
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SCHOOL_BOOT.get())
                .pattern("KK ")
                .pattern("KB ")
                .pattern("KKC")
                .define('K', Items.DRIED_KELP)
                .define('B', Items.BLACK_DYE)
                .define('C', Items.COAL)
                .unlockedBy(getHasName(Items.COAL), has(Items.COAL))
                .save(pRecipeOutput);
        // Stint-o-Coin
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STINTOCOIN.get())
                .pattern(" G ")
                .pattern("GDG")
                .pattern(" G ")
                .define('G', Items.GOLD_NUGGET)
                .define('D', ModItems.TWITCH_DIAMOND.get())
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get()))
                .save(pRecipeOutput);
        // Ball of Drake
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.BALL_OF_DRAKE.get())
                .pattern(" I ")
                .pattern("IDI")
                .pattern(" I ")
                .define('I', Items.IRON_NUGGET)
                .define('D', ModItems.TWITCH_DIAMOND.get())
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get()))
                .save(pRecipeOutput);
        // Drake Pendant
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DRAKE_PENDANT.get())
                .pattern("I I")
                .pattern("I I")
                .pattern(" B ")
                .define('I', Items.IRON_INGOT)
                .define('B', ModItems.BALL_OF_DRAKE.get())
                .unlockedBy(getHasName(ModItems.BALL_OF_DRAKE.get()), has(ModItems.BALL_OF_DRAKE.get()))
                .save(pRecipeOutput);
        // Glasses
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GLASSES.get())
                .pattern("C C")
                .pattern("GCG")
                .define('C', Items.COAL)
                .define('G', Items.GLASS)
                .unlockedBy(getHasName(Items.COAL), has(ModItems.TWITCH_DIAMOND.get()))
                .save(pRecipeOutput);
        // Dumpling Sun
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DUMPLING_SUN.get())
                .pattern("GD")
                .define('D', ModItems.DUMPLING.get())
                .define('G', ModItems.GLASSES.get())
                .unlockedBy(getHasName(ModItems.DUMPLING.get()), has(ModItems.DUMPLING.get()))
                .save(pRecipeOutput);
        // Stint helmet
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STINTIK_HELMET.get())
                .pattern("CSC")
                .pattern("C C")
                .define('C', ModItems.COIN_ALLOY.get())
                .define('S', ModItems.STINTOCOIN.get())
                .unlockedBy(getHasName(ModItems.STINTOCOIN.get()), has(ModItems.STINTOCOIN.get()))
                .save(pRecipeOutput);
        // Mzlff Microphone
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MAZELLOVVV_MICROPHONE.get())
                .pattern(" DB")
                .pattern(" CD")
                .pattern("C  ")
                .define('D', ModItems.TWITCH_DIAMOND.get())
                .define('B', ModBlocks.TWITCH_BLOCK.get())
                .define('C', ModItems.MAZELLOVVV_COOKIE.get())
                .unlockedBy(getHasName(ModItems.MAZELLOVVV_COOKIE.get()), has(ModItems.MAZELLOVVV_COOKIE.get()))
                .save(pRecipeOutput);
        // Om Nom
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.OM_NOM.get())
                .pattern("WSI")
                .pattern("GBD")
                .pattern("AAA")
                .define('W', Items.WOODEN_PICKAXE)
                .define('S', Items.STONE_PICKAXE)
                .define('I', Items.IRON_PICKAXE)
                .define('G', Items.GOLDEN_PICKAXE)
                .define('D', Items.DIAMOND_PICKAXE)
                .define('B', ModBlocks.TWITCH_BLOCK.get())
                .define('A', ModItems.BALL_OF_DRAKE.get())
                .unlockedBy(getHasName(ModItems.BALL_OF_DRAKE.get()), has(ModItems.BALL_OF_DRAKE.get()))
                .save(pRecipeOutput);
        // Super Om Nom
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SUPER_OM_NOM.get())
                .pattern("RRR")
                .pattern("IOI")
                .pattern("BBB")
                .define('R', Blocks.REDSTONE_BLOCK)
                .define('I', Items.IRON_INGOT)
                .define('B', Blocks.IRON_BLOCK)
                .define('O', ModItems.OM_NOM.get())
                .unlockedBy(getHasName(ModItems.OM_NOM.get()), has(ModItems.OM_NOM.get()))
                .save(pRecipeOutput);
        // Holy Mantle
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HOLY_MANTLE.get())
                .pattern("WTW")
                .pattern("TNT")
                .pattern(" T ")
                .define('W', Items.WITHER_SKELETON_SKULL)
                .define('T', ModBlocks.TWITCH_BLOCK.get())
                .define('N', Items.NETHER_STAR)
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get()))
                .save(pRecipeOutput);
        // Twitch Sword
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TWITCH_SWORD.get())
                .pattern("T")
                .pattern("T")
                .pattern("S")
                .define('T', ModItems.TWITCH_DIAMOND.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get()))
                .save(pRecipeOutput);
        // Twitch Axe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TWITCH_AXE.get())
                .pattern("TT")
                .pattern("TS")
                .pattern(" S")
                .define('T', ModItems.TWITCH_DIAMOND.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get()))
                .save(pRecipeOutput);
        // Twitch Pickaxe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TWITCH_PICKAXE.get())
                .pattern("TTT")
                .pattern(" S ")
                .pattern(" S ")
                .define('T', ModItems.TWITCH_DIAMOND.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get()))
                .save(pRecipeOutput);
        // Twitch Shovel
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TWITCH_SHOVEL.get())
                .pattern("T")
                .pattern("S")
                .pattern("S")
                .define('T', ModItems.TWITCH_DIAMOND.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get()))
                .save(pRecipeOutput);
        // Twitch Hoe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TWITCH_HOE.get())
                .pattern("TT")
                .pattern(" S")
                .pattern(" S")
                .define('T', ModItems.TWITCH_DIAMOND.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get()))
                .save(pRecipeOutput);
        // Ginger Bread
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MAZELLOVVV_COOKIE.get())
                .pattern("WSW")
                .pattern(" D ")
                .pattern("W W")
                .define('W', Items.WHEAT)
                .define('S', Items.SUGAR)
                .define('D', ModItems.TWITCH_DIAMOND.get())
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get()))
                .save(pRecipeOutput);
        // Ginger Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GINGER_BLOCK.get())
                .pattern("GG")
                .pattern("GG")
                .define('G', ModItems.MAZELLOVVV_COOKIE.get())
                .unlockedBy(getHasName(ModItems.MAZELLOVVV_COOKIE.get()), has(ModItems.MAZELLOVVV_COOKIE.get()))
                .save(pRecipeOutput);
        // Ginger Stairs
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GINGER_STAIRS.get(), 6)
                .pattern("G  ")
                .pattern("GG ")
                .pattern("GGG")
                .define('G', ModItems.MAZELLOVVV_COOKIE.get())
                .unlockedBy(getHasName(ModItems.MAZELLOVVV_COOKIE.get()), has(ModItems.MAZELLOVVV_COOKIE.get()))
                .save(pRecipeOutput);
        // Ginger Door
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GINGER_DOOR.get(), 3)
                .pattern("GG")
                .pattern("GG")
                .pattern("GG")
                .define('G', ModItems.MAZELLOVVV_COOKIE.get())
                .unlockedBy(getHasName(ModItems.MAZELLOVVV_COOKIE.get()), has(ModItems.MAZELLOVVV_COOKIE.get()))
                .save(pRecipeOutput);
        // Sugar Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SUGAR_BLOCK.get())
                .pattern("SS")
                .pattern("SS")
                .define('S', Items.SUGAR)
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .save(pRecipeOutput);
        // Sugar Stair
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SUGAR_STAIRS.get(), 6)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .define('S', Items.SUGAR)
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .save(pRecipeOutput);
        // Sugar Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SUGAR_SLAB.get(), 12)
                .pattern("SSS")
                .define('S', Items.SUGAR)
                .unlockedBy(getHasName(Items.SUGAR), has(Items.SUGAR))
                .save(pRecipeOutput);
        // Slime planks
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SMILE_PLANKS.get(), 4)
                .pattern("L")
                .define('L', ModBlocks.SMILE_LOG.get())
                .unlockedBy(getHasName(ModBlocks.SMILE_LOG.get()), has(ModBlocks.SMILE_LOG.get()))
                .save(pRecipeOutput);
        // Craft table
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Blocks.CRAFTING_TABLE)
                .pattern("PP")
                .pattern("PP")
                .define('P', ModBlocks.SMILE_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.SMILE_PLANKS.get()), has(ModBlocks.SMILE_PLANKS.get()))
                .save(pRecipeOutput);
        // Wooden Sword
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.WOODEN_SWORD)
                .pattern("P")
                .pattern("P")
                .pattern("S")
                .define('P', ModBlocks.SMILE_PLANKS.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModBlocks.SMILE_PLANKS.get()), has(ModBlocks.SMILE_PLANKS.get()))
                .save(pRecipeOutput);
        // Wooden Axe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.WOODEN_AXE)
                .pattern("PP")
                .pattern("PS")
                .pattern(" S")
                .define('P', ModBlocks.SMILE_PLANKS.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModBlocks.SMILE_PLANKS.get()), has(ModBlocks.SMILE_PLANKS.get()))
                .save(pRecipeOutput);
        // Wooden Hoe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.WOODEN_HOE)
                .pattern("PP")
                .pattern(" S")
                .pattern(" S")
                .define('P', ModBlocks.SMILE_PLANKS.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModBlocks.SMILE_PLANKS.get()), has(ModBlocks.SMILE_PLANKS.get()))
                .save(pRecipeOutput);
        // Wooden Pickaxe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.WOODEN_PICKAXE)
                .pattern("PPP")
                .pattern(" S ")
                .pattern(" S ")
                .define('P', ModBlocks.SMILE_PLANKS.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModBlocks.SMILE_PLANKS.get()), has(ModBlocks.SMILE_PLANKS.get()))
                .save(pRecipeOutput);
        // Wooden Shovel
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.WOODEN_SHOVEL)
                .pattern("P")
                .pattern("S")
                .pattern("S")
                .define('P', ModBlocks.SMILE_PLANKS.get())
                .define('S', Items.STICK)
                .unlockedBy(getHasName(ModBlocks.SMILE_PLANKS.get()), has(ModBlocks.SMILE_PLANKS.get()))
                .save(pRecipeOutput);
        // Closed DND book
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CLOSED_DND_BOOK.get())
                .pattern("UPL")
                .pattern("PDC")
                .pattern("LCU")
                .define('P', Items.PAPER)
                .define('L', Items.LEATHER)
                .define('D', ModItems.TWITCH_DIAMOND.get())
                .define('C', ModItems.DICE.get())
                .define('U', ModItems.DUMPLING_SUN.get())
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get()))
                .save(pRecipeOutput);
        // DND book
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DND_BOOK.get())
                .pattern("DWD")
                .pattern("WCW")
                .pattern("DWD")
                .define('D', ModItems.DICE.get())
                .define('W', Items.WITHER_SKELETON_SKULL)
                .define('C', ModItems.CLOSED_DND_BOOK.get())
                .unlockedBy(getHasName(ModItems.CLOSED_DND_BOOK.get()), has(ModItems.CLOSED_DND_BOOK.get()))
                .save(pRecipeOutput);
        // Dice
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.DICE.get())
                .pattern("WBW")
                .pattern("BDB")
                .pattern("WBW")
                .define('B', Items.BLACK_DYE)
                .define('W', Items.WHITE_DYE)
                .define('D', ModItems.TWITCH_DIAMOND.get())
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get()))
                .save(pRecipeOutput);
        // Transmitter
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.TRANSMITTER.get())
                .pattern("SPS")
                .pattern("SBS")
                .pattern("SRS")
                .define('S', ModItems.SCHOOL_BOOT.get())
                .define('P', Items.GLASS_PANE)
                .define('R', Blocks.REDSTONE_BLOCK)
                .define('B', ModBlocks.TWITCH_BLOCK.get())
                .unlockedBy(getHasName(ModItems.TWITCH_DIAMOND.get()), has(ModItems.TWITCH_DIAMOND.get()))
                .save(pRecipeOutput);



    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }
    protected static void foodSmoking(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMOKING_RECIPE, SmokingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_smooking");
    }
    protected static void foodCampfireCooking(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                              float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_campfire_cooking");
    }
    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for (ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, Streamer.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
