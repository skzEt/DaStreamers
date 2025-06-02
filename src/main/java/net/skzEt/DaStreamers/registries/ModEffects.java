package net.skzEt.DaStreamers.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.skzEt.DaStreamers.Streamer;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECT =
            DeferredRegister.create(Registries.MOB_EFFECT, Streamer.MOD_ID);

    public static int rgbToHex(int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }
}
