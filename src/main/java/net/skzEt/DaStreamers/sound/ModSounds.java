package net.skzEt.DaStreamers.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skzEt.DaStreamers.Streamer;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Streamer.MOD_ID);
    public static final RegistryObject<SoundEvent> OM_NOM_USED = registerSoundEvents("om_nom_used");
    public static final RegistryObject<SoundEvent> OM_NOM_EXPLODED = registerSoundEvents("om_nom_exploded");
    public static final RegistryObject<SoundEvent> HOLY_MANTLE = registerSoundEvents("holy_mantle_used");
    public static final RegistryObject<SoundEvent> HOLY_SOUND = registerSoundEvents("holy_sound");
    public static final RegistryObject<SoundEvent> BLYAT = registerSoundEvents("blyat");

    public static final RegistryObject<SoundEvent> FNAF_1 = registerSoundEvents("fnaf_1");
    public static final RegistryObject<SoundEvent> FNAF_2 = registerSoundEvents("fnaf_2");
    public static final RegistryObject<SoundEvent> FNAF_3 = registerSoundEvents("fnaf_3");
    public static final RegistryObject<SoundEvent> FNAF_4 = registerSoundEvents("fnaf_4");

    public static final RegistryObject<SoundEvent> BIRD = registerSoundEvents("bird");
    public static final RegistryObject<SoundEvent> INVALID = registerSoundEvents("invalid");
    public static final RegistryObject<SoundEvent> OKROHKA = registerSoundEvents("okrohka");


    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(Streamer.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
