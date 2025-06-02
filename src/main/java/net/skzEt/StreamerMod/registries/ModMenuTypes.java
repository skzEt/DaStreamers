package net.skzEt.StreamerMod.registries;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skzEt.StreamerMod.Streamer;
import net.skzEt.StreamerMod.client.gui.DNDBookMenu;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, Streamer.MOD_ID);
    public static final RegistryObject<MenuType<DNDBookMenu>> DND_MENU = MENU_TYPES.register("dnd_menu",
            () -> IForgeMenuType.create(((i, inventory, friendlyByteBuf) -> {
                if (friendlyByteBuf != null) {
                    return new DNDBookMenu(i, inventory, friendlyByteBuf);
                } else
                    throw new IllegalStateException("Entity is null");
            })));
}
