package net.skzEt.DaStreamers;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.ItemEntityRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.skzEt.DaStreamers.registries.*;
import net.skzEt.DaStreamers.loot.ModLootModifiers;
import net.skzEt.DaStreamers.particle.HolyMantleParticles;
import net.skzEt.DaStreamers.sound.ModSounds;

@Mod(Streamer.MOD_ID)
public class Streamer
{
    public static final String MOD_ID = "dastreamers";

    public Streamer(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        ModCreativeModTabs.CREATIVE_MODE_TABS.register(modEventBus);

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockEntity.BLOCK_ENITY.register(modEventBus);

        ModMenuTypes.MENU_TYPES.register(modEventBus);
        ModEntityType.ENTITY_TYPES.register(modEventBus);

        ModLootModifiers.LOOT_MODIFIER_SERIALIZERS.register(modEventBus);
        ModParticles.PARTICLE_TYPE.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);

        ModEffects.MOB_EFFECT.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        }
        @SubscribeEvent
        public static void registerParticleProvider(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(ModParticles.HOLY_MANTLE_PARTICLE.get(), HolyMantleParticles.Provider::new);
        }
        @SubscribeEvent
        public static void onRegisterRenders(EntityRenderersEvent.RegisterRenderers event) {

        }

    }
}
