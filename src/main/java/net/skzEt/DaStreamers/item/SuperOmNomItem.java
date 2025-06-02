package net.skzEt.DaStreamers.item;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.skzEt.DaStreamers.client.ModKeyboardHelper;
import net.skzEt.DaStreamers.registries.ModItems;
import net.skzEt.DaStreamers.sound.ModSounds;
import net.skzEt.DaStreamers.util.ModTags;

import java.util.List;

public class SuperOmNomItem extends Item {
    int tickCount = 20;
    public SuperOmNomItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        if (!pContext.getLevel().isClientSide) {
            BlockPos blockPos = pContext.getClickedPos();
            Level level = pContext.getLevel();
            BlockState state = pContext.getLevel().getBlockState(blockPos);

            if (!isValuableBlock(state) || isOreBlock(state)) {
                pContext.getPlayer().getCooldowns().addCooldown(ModItems.SUPER_OM_NOM.get(), tickCount);
                pContext.getLevel().playSeededSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                        ModSounds.OM_NOM_USED.get(), SoundSource.BLOCKS, 1f, 1f, 0);
                BlockPos blockPos1 = new BlockPos(blockPos.getX() + 1, blockPos.getY(), blockPos.getZ() - 1);
                BlockPos blockPos2 = new BlockPos(blockPos.getX() + 1, blockPos.getY(), blockPos.getZ());
                BlockPos blockPos3 = new BlockPos(blockPos.getX() + 1, blockPos.getY(), blockPos.getZ() + 1);
                BlockPos blockPos4 = new BlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ() - 1);
                BlockPos blockPos5 = new BlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ() + 1);
                BlockPos blockPos6 = new BlockPos(blockPos.getX() - 1, blockPos.getY(), blockPos.getZ() - 1);
                BlockPos blockPos7 = new BlockPos(blockPos.getX() - 1, blockPos.getY(), blockPos.getZ());
                BlockPos blockPos8 = new BlockPos(blockPos.getX() - 1, blockPos.getY(), blockPos.getZ() + 1);

                level.destroyBlock(blockPos1, true);
                level.destroyBlock(blockPos2, true);
                level.destroyBlock(blockPos3, true);
                level.destroyBlock(blockPos4, true);
                level.destroyBlock(blockPos, true);
                level.destroyBlock(blockPos5, true);
                level.destroyBlock(blockPos6, true);
                level.destroyBlock(blockPos7, true);
                level.destroyBlock(blockPos8, true);
            } else {
                pContext.getLevel().playSeededSound(null, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                        ModSounds.OM_NOM_EXPLODED.get(), SoundSource.BLOCKS, 1f, 1f, 0);
                pContext.getLevel().explode(null, pContext.getPlayer().getX(), pContext.getPlayer().getY(),
                        pContext.getPlayer().getZ(), 5, Level.ExplosionInteraction.BLOCK);
                pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                        pContext.getItemInHand().getEquipmentSlot());
            }

        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if (ModKeyboardHelper.isHoldingShift()) {
            pTooltipComponents.add(Component.translatable("dastreamers.description.om_nom")
                    .setStyle(Style.EMPTY).withColor(TextColor.fromRgb(0xFF0000).getValue()));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(ModTags.Blocks.OM_NOM_EXPLODE);
    }
    private boolean isOreBlock(BlockState state) {
        return state.is(ModTags.Blocks.OM_NOM_ORE);
    }
}
