package net.skzEt.StreamerMod.registries;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skzEt.StreamerMod.Streamer;


public class ModBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENITY =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Streamer.MOD_ID);


}
