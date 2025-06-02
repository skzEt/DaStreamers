package net.skzEt.StreamerMod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.skzEt.StreamerMod.registries.ModBlocks;
import net.skzEt.StreamerMod.registries.ModItems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {

    protected ModBlockLootTables(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        this.add(ModBlocks.TWITCH_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.TWITCH_ORE.get(), ModItems.TWITCH_DIAMOND.get(), 1, 3));
        this.add(ModBlocks.DEEPSLATE_TWITCH_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.DEEPSLATE_TWITCH_ORE.get(), ModItems.TWITCH_DIAMOND.get(), 1, 3));
        this.dropSelf(ModBlocks.TWITCH_BLOCK.get());

        this.add(ModBlocks.GINGER_BLOCK.get(),
                block -> createMultipleOreDrops(ModBlocks.GINGER_BLOCK.get(), ModItems.MAZELLOVVV_COOKIE.get(), 2, 4));
        this.add(ModBlocks.GINGER_STAIRS.get(),
                block -> createMultipleOreDrops(ModBlocks.GINGER_STAIRS.get(), ModItems.MAZELLOVVV_COOKIE.get(), 3, 6));
        this.add(ModBlocks.GINGER_DOOR.get(),
                block -> createMultipleOreDrops(ModBlocks.GINGER_DOOR.get(), ModItems.MAZELLOVVV_COOKIE.get(), 3, 6));
        this.add(ModBlocks.SUGAR_BLOCK.get(),
                block -> createMultipleOreDrops(ModBlocks.SUGAR_BLOCK.get(), Items.SUGAR, 2, 4));
        this.add(ModBlocks.SUGAR_STAIRS.get(),
                block -> createMultipleOreDrops(ModBlocks.SUGAR_STAIRS.get(), Items.SUGAR, 3, 6));
        this.add(ModBlocks.SUGAR_SLAB.get(),
                block -> createMultipleOreDrops(ModBlocks.SUGAR_SLAB.get(), Items.SUGAR, 1, 3));

        dropSelf(ModBlocks.SMILE_LOG.get());
        dropSelf(ModBlocks.SMILE_WOOD.get());
        dropSelf(ModBlocks.SMILE_PLANKS.get());
        dropSelf(ModBlocks.SMILE_LEAVES.get());
        dropSelf(ModBlocks.STRIPPED_SMILE_LOG.get());
        dropSelf(ModBlocks.STRIPPED_SMILE_WOOD.get());
        dropSelf(ModBlocks.SMILE_SAPLING.get());

        this.add(ModBlocks.SMILE_LEAVES.get(),
                block -> createLeavesDrops(block, ModBlocks.SMILE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

    }

    protected LootTable.Builder createMultipleOreDrops(Block pBlock, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                pBlock, this.applyExplosionDecay(
                        pBlock, LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }

    HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
