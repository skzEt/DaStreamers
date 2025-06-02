package net.skzEt.StreamerMod.item;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.skzEt.StreamerMod.client.ModKeyboardHelper;
import net.skzEt.StreamerMod.registries.ModItems;
import net.skzEt.StreamerMod.sound.ModSounds;

import java.util.List;
import java.util.Random;

public class TransmitterItem extends Item {
    public TransmitterItem(Properties pProperties) {
        super(pProperties);
    }

    private SoundEvent Sounds[] = new SoundEvent[] {
            ModSounds.FNAF_1.get(),
            ModSounds.FNAF_2.get(),
            ModSounds.FNAF_3.get(),
            ModSounds.FNAF_4.get()
    };

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        int tickCount = 80*20;
        BlockPos pos = pContext.getClickedPos();
        if (!pContext.getPlayer().getCooldowns().isOnCooldown(ModItems.TRANSMITTER.get())) {
            if (1 == new Random().nextInt(1, 10)) {
                pContext.getLevel().playSound(null, pContext.getPlayer().getX(), pContext.getPlayer().getY(), pContext.getPlayer().getZ(),
                        ModSounds.BLYAT.get(), SoundSource.PLAYERS, 0.8f, 1f);
                pContext.getLevel().explode(pContext.getPlayer(), pos.getX(), pos.getY(), pos.getZ(), 30f, Level.ExplosionInteraction.BLOCK);
                pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                        pContext.getItemInHand().getEquipmentSlot());
                return InteractionResult.SUCCESS;
            }
            pContext.getLevel().playSound(null, pos.getX(), pos.getY(), pos.getZ(),
                    Sounds[new Random().nextInt(Sounds.length)], SoundSource.PLAYERS, 1f, 1f);
            pContext.getPlayer().getCooldowns().addCooldown(ModItems.TRANSMITTER.get(), tickCount);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if (ModKeyboardHelper.isHoldingShift()) {
            pTooltipComponents.add(Component.translatable("streamermod.description.transmitter")
                    .setStyle(Style.EMPTY).withColor(TextColor.fromRgb(0xFF0000).getValue()));
        }
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
