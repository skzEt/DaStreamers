package net.skzEt.DaStreamers.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.skzEt.DaStreamers.Streamer;
import net.skzEt.DaStreamers.registries.ModBlocks;
import net.skzEt.DaStreamers.worldgen.ore.ModOrePlacement;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> TWITCH_ORE_PLACED_KEY = registerKey("twitch_ore_placed");

    public static final ResourceKey<PlacedFeature> SMILE_KEY = registerKey("smile_placed");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeature = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, TWITCH_ORE_PLACED_KEY, configuredFeature.getOrThrow(ModConfiguredFeatures.OVERWORLD_TWITCH_ORE_KEY),
                ModOrePlacement.commonOrePlacement(5,
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(-75), VerticalAnchor.absolute(40))));

        register(context, SMILE_KEY, configuredFeature.getOrThrow(ModConfiguredFeatures.SMILE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 3),
                        ModBlocks.SMILE_SAPLING.get()));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Streamer.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}