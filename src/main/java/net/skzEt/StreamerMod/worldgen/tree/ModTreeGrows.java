package net.skzEt.StreamerMod.worldgen.tree;

import net.minecraft.world.level.block.grower.TreeGrower;
import net.skzEt.StreamerMod.Streamer;
import net.skzEt.StreamerMod.worldgen.ModConfiguredFeatures;

import java.util.Optional;

public class ModTreeGrows {
    public static final TreeGrower SMILE = new TreeGrower(Streamer.MOD_ID + ":smile",
            Optional.empty(), Optional.of(ModConfiguredFeatures.SMILE_KEY),
            Optional.empty());
}
