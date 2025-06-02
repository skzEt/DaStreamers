package net.skzEt.StreamerMod.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.skzEt.StreamerMod.Streamer;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECT =
            DeferredRegister.create(Registries.MOB_EFFECT, Streamer.MOD_ID);

    public static int rgbToHex(int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }
}
