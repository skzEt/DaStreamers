package net.skzEt.StreamerMod.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import net.minecraft.resources.ResourceLocation;
import net.skzEt.StreamerMod.Streamer;

@JeiPlugin
public class ModJEI implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(Streamer.MOD_ID, "jei_plugin");
    }
}
