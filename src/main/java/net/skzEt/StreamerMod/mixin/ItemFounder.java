package net.skzEt.StreamerMod.mixin;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.skzEt.StreamerMod.util.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class ItemFounder {
    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void injectUseOn(UseOnContext context, CallbackInfoReturnable<InteractionResult> returnable) {
        Player player = context.getPlayer();
        ItemStack itemStack = context.getItemInHand();
        BlockPos blockPos = context.getClickedPos();
        Level level = context.getLevel();
        boolean foundBlock = false;

        if (!level.isClientSide()) {
            if (itemStack.getItem() == Items.STICK) {
                for (int i = 1; i <= blockPos.getY(); i++) {
                    BlockPos checkPos = blockPos.below(i);
                    BlockState blockState = level.getBlockState(checkPos);

                    if (isValuable(blockState)) {
                        outputValuableCoordinates(blockPos.below(i), player, blockState.getBlock());
                        foundBlock = true;
                        break;
                    }
                }
                if (!foundBlock) {
                    player.sendSystemMessage(Component.literal("Not found"));
                }
                returnable.setReturnValue(InteractionResult.SUCCESS);
            }
        }
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at " +
                "(" + blockPos.getX() + " " + blockPos.getY() + " " + blockPos.getZ() + ")"));
    }

    private boolean isValuable(BlockState state) {
        return true;// state.is(ModTags.Blocks.VALUABLES);
    }
}
