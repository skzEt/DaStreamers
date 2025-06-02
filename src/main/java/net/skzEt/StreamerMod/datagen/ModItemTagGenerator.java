package net.skzEt.StreamerMod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.skzEt.StreamerMod.Streamer;
import net.skzEt.StreamerMod.registries.ModBlocks;
import net.skzEt.StreamerMod.registries.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {

    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, Streamer.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.GLASSES.get(),
                        ModItems.DRAKE_PENDANT.get(),
                        ModItems.STINTIK_HELMET.get(),
                        ModItems.BOXERS.get(),
                        ModItems.DND.get());
        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.SMILE_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_SMILE_LOG.get().asItem())
                .add(ModBlocks.SMILE_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_SMILE_WOOD.get().asItem());
        this.tag(ItemTags.PLANKS)
                .add(ModBlocks.SMILE_PLANKS.get().asItem());
    }
}
