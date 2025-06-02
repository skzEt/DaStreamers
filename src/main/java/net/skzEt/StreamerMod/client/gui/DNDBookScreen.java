package net.skzEt.StreamerMod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.skzEt.StreamerMod.Streamer;
import net.skzEt.StreamerMod.event.CommonEvents;

public class DNDBookScreen extends AbstractContainerScreen<DNDBookMenu> {

    private static final ResourceLocation GUI = ResourceLocation.fromNamespaceAndPath(Streamer.MOD_ID,
            "textures/gui/dnd_book/dnd_book.png");
    private String attack = "";

    public DNDBookScreen(DNDBookMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        Player player = server.getPlayerList().getPlayer(CommonEvents.playerUUID);

        addRenderableWidget(Button.builder(Component.translatable("button.streamermod.attack_1"),
                button -> {
            attack = "Explosion";
            int ExplosionInt = 50;
            player.getPersistentData().putString("Explosion", attack);
            player.getPersistentData().putInt("ExplosionInt", ExplosionInt);
                })
                .bounds(10, 25, 28, 11).build());
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float v, int i, int i1) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F,1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI);

        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(GUI, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}
