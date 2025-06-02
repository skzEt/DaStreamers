package net.skzEt.StreamerMod.item;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.skzEt.StreamerMod.client.ModKeyboardHelper;
import net.skzEt.StreamerMod.entity.DNDBookEntity;
import net.skzEt.StreamerMod.event.CommonEvents;
import net.skzEt.StreamerMod.registries.ModEntityType;
import net.skzEt.StreamerMod.registries.ModItems;
import net.skzEt.StreamerMod.util.RayCast;

import java.util.List;
import java.util.Random;

public class DNDBook extends Item {
    public DNDBook(Properties pProperties) {
        super(pProperties);
    }

    private static int cooldown = 5 * 60 * 20;

    private static int descriptionId;
    private static int abilityId = 0;
    private static int maxAbilities = 3;

    private static int cubeInt;

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide()) {
            MinecraftServer server = pLevel.getServer();
            ServerPlayer player = server.getPlayerList().getPlayer(CommonEvents.playerUUID);

            if (!pPlayer.getCooldowns().isOnCooldown(pPlayer.getItemInHand(pUsedHand).getItem())) {
                if (ModKeyboardHelper.isHoldingShift()) {
                    pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand).getItem(), 10);
                    descriptionId++;
                    abilityId++;
                    if (descriptionId > maxAbilities || abilityId > maxAbilities) {
                        descriptionId = 1;
                        abilityId = 1;
                    }
                    switch (descriptionId) {
                        case 1 ->
                                player.sendSystemMessage(Component.translatable("streamermod.message.ability_1"), true);
                        case 2 ->
                                player.sendSystemMessage(Component.translatable("streamermod.message.ability_2"), true);
                        case 3 ->
                                player.sendSystemMessage(Component.translatable("streamermod.message.ability_3"), true);
                    }
                    return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
                }
            }

            if (!pPlayer.getCooldowns().isOnCooldown(pPlayer.getItemInHand(pUsedHand).getItem())) {
                switch (abilityId) {
                    case 1:
                        pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand).getItem(), 60);
                        int random = getD6();
                        cubeInt = random;
                        if (cubeInt > 2 && cubeInt < 6) {
                            player.sendSystemMessage(Component.translatable("streamermod.message.cube", cubeInt), true);
                            try {
                                Thread.sleep(800);
                            } catch (InterruptedException e) {}
                            BlockPos blockPos = RayCast.rayBlock(pPlayer, pLevel, 800);
                            pLevel.explode(pPlayer, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                                    15, Level.ExplosionInteraction.BLOCK);
                            break;
                        } else if (cubeInt == 6) {
                            player.sendSystemMessage(Component.translatable("streamermod.message.max_cube"), true);
                            try {
                                Thread.sleep(800);
                            } catch (InterruptedException e) {}
                            BlockPos blockPos = RayCast.rayBlock(pPlayer, pLevel, 1500);
                            pLevel.explode(pPlayer, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                                    35, Level.ExplosionInteraction.BLOCK);
                            break;
                        } else if (cubeInt == 2) {
                            player.sendSystemMessage(Component.translatable("streamermod.message.cube", cubeInt), true);
                            try {
                                Thread.sleep(800);
                            } catch (InterruptedException e) {}
                            pLevel.explode(pPlayer, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                                    5, Level.ExplosionInteraction.BLOCK);
                            break;
                        } else if (cubeInt == 1) {
                            player.sendSystemMessage(Component.translatable("streamermod.message.cube", cubeInt), true);
                            try {
                                Thread.sleep(800);
                            } catch (InterruptedException e) {}
                            pLevel.explode(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                                    5, Level.ExplosionInteraction.BLOCK);
                            break;
                        }
                        break;
                    case 2:
                        pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand).getItem(), 60);
                        random = getD10();
                        cubeInt = random;
                        if (cubeInt > 4) {
                            player.sendSystemMessage(Component.translatable("streamermod.message.cube", cubeInt), true);
                            BlockPos blockPos = RayCast.rayBlock(pPlayer, pLevel, 1000);
                            Vec3 newPos = new Vec3(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ());
                            pPlayer.setPos(newPos);
                            break;
                        } else if (cubeInt > 2 && cubeInt < 4) {
                            player.sendSystemMessage(Component.translatable("streamermod.message.cube", cubeInt), true);
                            BlockPos blockPos = RayCast.rayBlock(pPlayer, pLevel, 1000);
                            Vec3 newPos = new Vec3(blockPos.getX() + new Random().nextInt(-10, 10), blockPos.getY() + new Random().nextInt(-3, 3), blockPos.getZ() + new Random().nextInt(-10, 10));
                            pPlayer.setPos(newPos);
                            break;
                        } else {
                            player.sendSystemMessage(Component.translatable("streamermod.message.cube", cubeInt), true);
                            Vec3 newPos = new Vec3(pPlayer.getX() + new Random().nextInt(-100, 100), pPlayer.getY() + new Random().nextInt(-5, 10), pPlayer.getZ() + new Random().nextInt(-100, 100));
                            pPlayer.setPos(newPos);
                            break;
                        }
                    case 3:
                        pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand).getItem(), 60);
                        random = getD4();
                        cubeInt = random;
                        Entity entity = RayCast.rayEntity(pPlayer, pLevel, 1000);
                        if (entity == null) {
                            if (cubeInt > 1 && cubeInt < 4) {
                                pPlayer.getCooldowns().addCooldown(pPlayer.getItemInHand(pUsedHand).getItem(), 10*20);
                                player.sendSystemMessage(Component.translatable("streamermod.message.cube", cubeInt), true);
                                pPlayer.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 10*20, 2));
                            } else if (cubeInt == 4) {
                                player.sendSystemMessage(Component.translatable("streamermod.message.max_cube"), true);
                                if (!pPlayer.isCreative()) {
                                    player.getAbilities().flying = true;
                                    player.getAbilities().mayfly = true;
                                    player.onUpdateAbilities();
                                    try {
                                        Thread.sleep(15000);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    player.getAbilities().flying = false;
                                    player.getAbilities().mayfly = false;
                                    player.onUpdateAbilities();
                                }
                            } else {
                                player.sendSystemMessage(Component.translatable("streamermod.message.cube", cubeInt), true);;
                                clearChunkToBedrock(pLevel, pPlayer.blockPosition().below());
                            }
                        } else {
                            if (cubeInt > 1) {
                                player.sendSystemMessage(Component.translatable("streamermod.message.cube", cubeInt), true);
                                Vec3 entityPos = new Vec3(entity.getX(), entity.getY() + 30, entity.getZ());
                                entity.setPos(entityPos);
                            } else {
                                player.sendSystemMessage(Component.translatable("streamermod.message.cube", cubeInt), true);
                                clearChunkToBedrock(pLevel, entity.blockPosition().below());
                            }
                        }
                }
            }
        }
        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }

    private void clearChunkToBedrock(Level level, BlockPos originPos) {
        int chunkX = originPos.getX() >> 4;
        int chunkZ = originPos.getZ() >> 4;

        int startX = chunkX << 4;
        int startZ = chunkZ << 4;

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 1; y < level.getMaxBuildHeight(); y++) {
                    BlockPos pos = new BlockPos(startX + x, y, startZ + z);
                    level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                }
            }
        }
    }

    private static int getD6() {
        return new Random().nextInt(1, 7);
    }
    private static int getD10() {
        return new Random().nextInt(1, 11);
    }
    private static int getD4() {
        return new Random().nextInt(1, 5);
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if (ModKeyboardHelper.isHoldingShift()) {
            pTooltipComponents.add(Component.translatable("streamermod.description.dnd_book")
                    .setStyle(Style.EMPTY).withColor(TextColor.fromRgb(rgbToHex(124, 124, 124)).getValue()));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }

    public static int rgbToHex(int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }
}
