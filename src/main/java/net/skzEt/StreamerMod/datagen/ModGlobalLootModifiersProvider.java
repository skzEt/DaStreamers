package net.skzEt.StreamerMod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.skzEt.StreamerMod.Streamer;
import net.skzEt.StreamerMod.registries.ModItems;
import net.skzEt.StreamerMod.loot.AddItemModifier;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {


    public ModGlobalLootModifiersProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Streamer.MOD_ID, registries);
    }

    @Override
    protected void start(HolderLookup.Provider registries) {
        add("boxers_from_ruined_portal",
                new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/ruined_portal")).build(),
                LootItemRandomChanceCondition.randomChance(0.4f).build()}, ModItems.BOXERS.get()));

        add("stintocoin_from_desert_pyramid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.3f).build()}, ModItems.STINTOCOIN.get()));
        add("ball_of_drake_from_desert_pyramid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.1f).build()}, ModItems.DRAKE_PENDANT.get()));
        add("dumpling_sun_from_desert_pyramid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.3f).build()}, ModItems.DUMPLING_SUN.get()));
        add("mazellovvv_cookie_from_desert_pyramid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(ResourceLocation.withDefaultNamespace("chests/desert_pyramid")).build(),
                LootItemRandomChanceCondition.randomChance(0.3f).build()}, ModItems.MAZELLOVVV_COOKIE.get()));
    }
}
