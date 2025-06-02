package net.skzEt.StreamerMod.registries;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skzEt.StreamerMod.Streamer;
import net.skzEt.StreamerMod.worldgen.tree.ModTreeGrows;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Streamer.MOD_ID);

    public static final RegistryObject<Block> TWITCH_ORE = registerBlock("twitch_ore",
            () -> new DropExperienceBlock(UniformInt.of(1, 2),BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                    .strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DEEPSLATE_TWITCH_ORE = registerBlock("deepslate_twitch_ore",
            () -> new DropExperienceBlock(UniformInt.of(1, 2), BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE)
                    .strength(2f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TWITCH_BLOCK = registerBlock("twitch_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)
                    .sound(SoundType.ANVIL)));
    public static final RegistryObject<Block> GINGER_BLOCK = registerBlock("ginger_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<StairBlock> GINGER_STAIRS = registerBlock("ginger_stairs",
            () -> new StairBlock(ModBlocks.GINGER_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<DoorBlock> GINGER_DOOR = registerBlock("ginger_door",
            () -> new DoorBlock(BlockSetType.CHERRY, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<Block> SUGAR_BLOCK = registerBlock("sugar_block",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<StairBlock> SUGAR_STAIRS = registerBlock("sugar_stairs",
            () -> new StairBlock(ModBlocks.SUGAR_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    public static final RegistryObject<SlabBlock> SUGAR_SLAB = registerBlock("sugar_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL)));
    // Wood
    public static final RegistryObject<RotatedPillarBlock> SMILE_LOG = registerBlock("smile_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_LOG)));
    public static final RegistryObject<RotatedPillarBlock> SMILE_WOOD = registerBlock("smile_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_WOOD)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_SMILE_LOG = registerBlock("stripped_smile_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_CHERRY_LOG)));
    public static final RegistryObject<RotatedPillarBlock> STRIPPED_SMILE_WOOD = registerBlock("stripped_smile_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_CHERRY_WOOD)));
    public static final RegistryObject<Block> SMILE_PLANKS = registerBlock("smile_planks",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }});
    public static final RegistryObject<Block> SMILE_LEAVES = registerBlock("smile_leaves",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_LEAVES)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });
    public static final RegistryObject<Block> SMILE_SAPLING = registerBlock("smile_sapling",
            () -> new SaplingBlock(ModTreeGrows.SMILE, BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_SAPLING)));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
