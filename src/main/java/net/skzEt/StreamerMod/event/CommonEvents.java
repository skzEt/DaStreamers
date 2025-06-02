package net.skzEt.StreamerMod.event;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.EntityLeaveLevelEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.skzEt.StreamerMod.Streamer;
import net.skzEt.StreamerMod.registries.ModEffects;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = Streamer.MOD_ID)
public class CommonEvents {
    public static UUID playerUUID;

    public static Vec3 playerPosition;

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            try {
                playerUUID = player.getUUID();
            } catch (IllegalArgumentException e) {
                System.out.println("Player UUID in null");
            }
            player.getPersistentData().putBoolean("used", false);
            setDefault();
        }
    }

    @SubscribeEvent
    public static void onEntityLeaveLevel(EntityLeaveLevelEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            player.getPersistentData().putBoolean("used", false);
            setDefault();
        }
    }

    private static void spawnTnt(Player pPlayer, Level pLevel) {
        Vec3 playerPos = pPlayer.position();
        PrimedTnt tnt = EntityType.TNT.create(pLevel);
        Pig pig = EntityType.PIG.create(pLevel);

        tnt.setPos(playerPos.x, playerPos.y + 15, playerPos.z);
        pig.setPos(playerPos.x, playerPos.y + 10, playerPos.z);

        pLevel.addFreshEntity(tnt);
        pLevel.addFreshEntity(pig);

    }
    private static int ticks = 0;
    private static int spawnedTNT = 0;
    private static boolean wait = true;
    private static int timer = 5;

    private static void setDefault() {
        timer = 5;
        wait = true;
        spawnedTNT = 0;
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();

        if (server == null || playerUUID == null) return;

        ServerPlayer player = server.getPlayerList().getPlayer(playerUUID);
        if (player == null) return;

        playerPosition = player.position();

        if (player.getPersistentData().getBoolean("used")) {
            ticks++;
            if (ticks >= 20) {
                ticks = 0;
                if (wait) {
                    if (timer <= 0) {
                        wait = false;
                        return;
                    }
                    player.sendSystemMessage(Component.translatable("streamermod.message.tnt1", timer), true);
                    timer--;
                    return;
                }
                spawnTnt(player, player.level());
                spawnedTNT++;
                player.sendSystemMessage(Component.translatable("streamermod.message.tnt", 25 - spawnedTNT), true);
            }
            if (spawnedTNT == 25) {
                player.getPersistentData().putBoolean("used", false);
                setDefault();
            }
        }

    }

    @SubscribeEvent
    public static void onLivingHurtEvent(LivingHurtEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            if (player.getPersistentData().getBoolean("abilityUsed_3")) {
                if (player.isFallFlying()) {
                    event.setCanceled(true);
                    player.getPersistentData().putBoolean("abilityUsed_3", false);
                }
            }
        }
    }
}
