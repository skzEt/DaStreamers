package net.skzEt.StreamerMod.entity;

import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.skzEt.StreamerMod.client.gui.DNDBookMenu;
import net.skzEt.StreamerMod.event.CommonEvents;
import org.jetbrains.annotations.Nullable;

import java.util.Random;
import java.util.UUID;

import static net.minecraft.world.item.enchantment.EnchantmentHelper.hasTag;

public class DNDBookEntity extends ItemEntity implements MenuProvider {
    protected final ContainerData data;

    private int randomCube;
    private int mana;
    private int maxMana = 2500;
    private int successfulCube;

    public DNDBookEntity(EntityType<? extends ItemEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        data = new ContainerData() {
            @Override
            public int get(int i) {
                return switch (i) {
                    case 0 -> randomCube;
                    case 1 -> mana;
                    case 2 -> maxMana;
                    case 3 -> successfulCube;
                    default -> 0;
                };
            }

            @Override
            public void set(int i, int i1) {
                switch (i) {
                    case 0 -> randomCube = i1;
                    case 1 -> mana = i1;
                    case 2 -> maxMana = i1;
                    case 3 -> successfulCube = i1;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public void tick() {
        super.tick();
        UUID playerUUID;

        try {
            playerUUID = CommonEvents.playerUUID;
        } catch (IllegalArgumentException e) {
            return;
        }

        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        Player player = server.getPlayerList().getPlayer(playerUUID);
        Level level = player.level();

        if (mana <= maxMana) {
            if (mana >= neededMana(player)) {
                if (player.getPersistentData().getBoolean("used")) {
                    useAttack(player, level);
                    player.getPersistentData().putBoolean("used", false);
                }
            }
        } else mana = maxMana;
    }

    private int neededMana(Player player) {
        return player.getPersistentData().getInt("SavedInt");
    }

    private void useAttack(Player player, Level level) {
        String attack = player.getPersistentData().getString("SavedAttack");
        if (attack.equals("Explosion")) {
            if (getRandomInt() > 3) {
                int randomDistance = new Random().nextInt(1, 5);
                level.explode(player, getBlockPos(player, level, randomDistance).getX(),
                        getBlockPos(player, level, randomDistance).getY(), getBlockPos(player, level, randomDistance).getZ(),
                        new Random().nextFloat(1, 8), Level.ExplosionInteraction.BLOCK);
            } else {
                level.explode(player, player.getX(), player.getY(), player.getZ(),
                        new Random().nextFloat(2, 4), Level.ExplosionInteraction.NONE);
            }
        }
    }

    private BlockPos getBlockPos(Player player, Level level, int maxDistance) {
        Vec3 eyePos = player.getEyePosition(1);
        Vec3 viewPos = player.getViewVector(1);
        Vec3 endPos = eyePos.add(viewPos.x * maxDistance * 100, viewPos.y * maxDistance * 100,
                viewPos.z * maxDistance * 100);

        ClipContext context = new ClipContext(eyePos, endPos, ClipContext.Block.OUTLINE,
                ClipContext.Fluid.SOURCE_ONLY, player);

        BlockHitResult result = level.clip(context);

        if (result.getType() == HitResult.Type.BLOCK) {
            return result.getBlockPos();
        }

        return null;
    }

    private int getRandomInt() {
        return new Random().nextInt(1, 6);
    }

    private boolean hasTag(Player player) {
        if (player.getPersistentData().isEmpty()) return false;
        return player.getPersistentData().contains("SavedAttack");
    }


    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new DNDBookMenu(i, inventory, this, data);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("item.streamermod.dnd_book");
    }


}
