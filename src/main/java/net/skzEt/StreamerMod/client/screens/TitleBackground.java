package net.skzEt.StreamerMod.client.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.resources.ResourceLocation;
import net.skzEt.StreamerMod.Streamer;
import net.skzEt.StreamerMod.event.ClientEvent;

import java.util.Random;


public class TitleBackground extends TitleScreen {


    public static ResourceLocation[] TEXTURES = new ResourceLocation[] {
            ResourceLocation.fromNamespaceAndPath(Streamer.MOD_ID,
            "textures/gui/background_1.png"),
            ResourceLocation.fromNamespaceAndPath(Streamer.MOD_ID,
                    "textures/gui/background_2.png"),
            ResourceLocation.fromNamespaceAndPath(Streamer.MOD_ID,
                    "textures/gui/background_3.png"),
            ResourceLocation.fromNamespaceAndPath(Streamer.MOD_ID,
                    "textures/gui/background_4.png"),
            ResourceLocation.fromNamespaceAndPath(Streamer.MOD_ID,
                    "textures/gui/background_5.png"),
            ResourceLocation.fromNamespaceAndPath(Streamer.MOD_ID,
                    "textures/gui/background_6.png")
    };

    public static int random = new Random().nextInt(TEXTURES.length);

    @Override
    public void renderBackground(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        pGuiGraphics.blit(TEXTURES[random], 0, 0,  0, 0, this.width, this.height,
                this.width, this.height);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }
}
