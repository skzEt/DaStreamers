package net.skzEt.DaStreamers.registries;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.skzEt.DaStreamers.Streamer;


public class ModBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENITY =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Streamer.MOD_ID);


}
