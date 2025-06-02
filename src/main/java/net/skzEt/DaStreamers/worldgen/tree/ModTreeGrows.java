package net.skzEt.DaStreamers.worldgen.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.skzEt.DaStreamers.Streamer;
import net.skzEt.DaStreamers.worldgen.ModConfiguredFeatures;

import java.util.Optional;

public class ModTreeGrows {
    public static final TreeGrower SMILE = new TreeGrower(Streamer.MOD_ID + ":smile",
            Optional.empty(), Optional.of(ModConfiguredFeatures.SMILE_KEY),
            Optional.empty());
}
