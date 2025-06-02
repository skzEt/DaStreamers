package net.skzEt.StreamerMod.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skzEt.StreamerMod.Streamer;
import net.skzEt.StreamerMod.entity.DNDBookEntity;

public class ModEntityType {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Streamer.MOD_ID);

    public static final RegistryObject<EntityType<DNDBookEntity>> DND_BOOK_ENTITY =
            ENTITY_TYPES.register("dnd_book_entity", () ->
                    EntityType.Builder.<DNDBookEntity>of(DNDBookEntity::new, MobCategory.MISC)
                            .sized(0.5F, 0.5F)
                            .clientTrackingRange(10)
                            .updateInterval(20)
                            .build("dnd_book_entity"));
}
