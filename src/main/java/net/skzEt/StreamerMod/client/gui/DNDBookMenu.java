package net.skzEt.StreamerMod.client.gui;

import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.skzEt.StreamerMod.entity.DNDBookEntity;
import net.skzEt.StreamerMod.registries.ModItems;
import net.skzEt.StreamerMod.registries.ModMenuTypes;

public class DNDBookMenu extends AbstractContainerMenu {
    public final DNDBookEntity entity;
    private final Level level;
    private final ContainerData data;


    public DNDBookMenu(int containerId, Inventory playerInventory, FriendlyByteBuf buf) {
        this(containerId, playerInventory,
                (DNDBookEntity) playerInventory.player.level().getEntity(buf.readVarInt()),
                new SimpleContainerData(4));
    }

    // ID is null

    public DNDBookMenu(int containerId, Inventory playerInventory, DNDBookEntity entity, ContainerData data) {
        super(ModMenuTypes.DND_MENU.get(), containerId);
        this.entity = entity;
        this.level = playerInventory.player.level();
        this.data = data;
    }


    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, entity.level().getSharedSpawnPos()),
                player, Block.byItem(ModItems.DND_BOOK.get()));
    }
}
