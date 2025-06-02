package net.skzEt.DaStreamers.client.screens;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.skzEt.DaStreamers.Streamer;

@Mod.EventBusSubscriber(modid = Streamer.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class LevelLoadingBackground {
    @SubscribeEvent
    public static void onRenderScreenBackground(ScreenEvent.Render.BackgroundRendered event) {
        if (event.getScreen() instanceof LevelLoadingScreen levelScreen) {
            GuiGraphics graphics = event.getGuiGraphics();

            graphics.blit(TitleBackground.TEXTURES[TitleBackground.random],
                    0, 0, 0, 0,
                    Minecraft.getInstance().getWindow().getGuiScaledWidth(),
                    Minecraft.getInstance().getWindow().getGuiScaledHeight(),
                    Minecraft.getInstance().getWindow().getGuiScaledWidth(),
                    Minecraft.getInstance().getWindow().getGuiScaledHeight());
        }
    }

}
