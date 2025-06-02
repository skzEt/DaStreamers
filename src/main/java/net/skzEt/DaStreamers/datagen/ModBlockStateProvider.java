package net.skzEt.DaStreamers.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skzEt.DaStreamers.Streamer;
import net.skzEt.DaStreamers.registries.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Streamer.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.TWITCH_BLOCK);
        blockWithItem(ModBlocks.TWITCH_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_TWITCH_ORE);
        blockWithItem(ModBlocks.GINGER_BLOCK);
        blockWithItem(ModBlocks.SUGAR_BLOCK);

        blockItem(ModBlocks.GINGER_STAIRS);
        blockItem(ModBlocks.SUGAR_STAIRS);
        blockItem(ModBlocks.SUGAR_SLAB);

        stairsBlock(ModBlocks.GINGER_STAIRS.get(), blockTexture(ModBlocks.GINGER_BLOCK.get()));
        doorBlockWithRenderType(ModBlocks.GINGER_DOOR.get(), modLoc("block/ginger_door_bottom"), modLoc("block/ginger_door_top"), "cutout");
        stairsBlock(ModBlocks.SUGAR_STAIRS.get(), blockTexture(ModBlocks.SUGAR_BLOCK.get()));
        slabBlock(ModBlocks.SUGAR_SLAB.get(), blockTexture(ModBlocks.SUGAR_BLOCK.get()), blockTexture(ModBlocks.SUGAR_BLOCK.get()));

        logBlock(ModBlocks.SMILE_LOG.get());
        axisBlock(ModBlocks.SMILE_WOOD.get(), blockTexture(ModBlocks.SMILE_LOG.get()), blockTexture(ModBlocks.SMILE_LOG.get()));
        logBlock(ModBlocks.STRIPPED_SMILE_LOG.get());
        axisBlock(ModBlocks.STRIPPED_SMILE_WOOD.get(), blockTexture(ModBlocks.STRIPPED_SMILE_LOG.get()), blockTexture(ModBlocks.STRIPPED_SMILE_LOG.get()));

        blockItem(ModBlocks.SMILE_LOG);
        blockItem(ModBlocks.SMILE_WOOD);
        blockItem(ModBlocks.STRIPPED_SMILE_LOG);
        blockItem(ModBlocks.STRIPPED_SMILE_WOOD);

        blockWithItem(ModBlocks.SMILE_PLANKS);
        leavesBlock(ModBlocks.SMILE_LEAVES);
        saplingBlock(ModBlocks.SMILE_SAPLING);
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                        blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockItem(RegistryObject<? extends Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("dastreamers:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockItem(RegistryObject<? extends Block> blockRegistryObject, String appendix) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("dastreamers:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + appendix));
    }
}
