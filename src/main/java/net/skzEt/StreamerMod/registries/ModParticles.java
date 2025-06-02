package net.skzEt.StreamerMod.registries;


import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.skzEt.StreamerMod.Streamer;

import java.util.function.Supplier;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPE =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Streamer.MOD_ID);

    public static final Supplier<SimpleParticleType> HOLY_MANTLE_PARTICLE = PARTICLE_TYPE.register("holy_mantle_particle",
            () -> new SimpleParticleType(true));
}
