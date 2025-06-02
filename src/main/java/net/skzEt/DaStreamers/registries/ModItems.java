package net.skzEt.DaStreamers.registries;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.skzEt.DaStreamers.Streamer;
import net.skzEt.DaStreamers.client.ModKeyboardHelper;
import net.skzEt.DaStreamers.item.*;

import java.util.List;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Streamer.MOD_ID);
    // Custom Items
    public static final RegistryObject<Item> OM_NOM = ITEMS.register("om_nom",
            () -> new OmNomItem(new Item.Properties().durability(600)));
    public static final RegistryObject<Item> SUPER_OM_NOM = ITEMS.register("super_om_nom",
            () -> new SuperOmNomItem(new Item.Properties().durability(5)));
    public static final RegistryObject<Item> HOLY_MANTLE = ITEMS.register("holy_mantle",
            () ->  new Item(new Item.Properties().stacksTo(1).fireResistant()) {
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    int hexColor = rgbToHex(16, 231, 255);

                    if (ModKeyboardHelper.isHoldingShift()) {
                        pTooltipComponents.add(Component.translatable("dastreamers.description.holy_mantle")
                                .setStyle(Style.EMPTY).withColor(TextColor.fromRgb(hexColor).getValue()));
                    }
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });
    public static final RegistryObject<Item> TRANSMITTER = ITEMS.register("transmitter",
            () -> new TransmitterItem(new Item.Properties().stacksTo(1).durability(1)));
    public static final RegistryObject<Item> CLOSED_DND_BOOK = ITEMS.register("closed_dnd_book",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> DND_BOOK = ITEMS.register("dnd_book",
            () -> new DNDBook(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> MAZELLOVVV_MICROPHONE = ITEMS.register("mazellovvv_microphone",
            () -> new MazellovvvMircophoneItem(new Item.Properties().stacksTo(1)));
    // Streamers
    public static final RegistryObject<Item> STINTOCOIN = ITEMS.register("stintocoin",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BALL_OF_DRAKE = ITEMS.register("ball_of_drake",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DUMPLING = ITEMS.register("dumpling",
            () -> new Item(new Item.Properties().food(ModFoods.DUMPLING)));
    public static final RegistryObject<Item> DUMPLING_SUN = ITEMS.register("dumpling_sun",
            () -> new Item(new Item.Properties().food(ModFoods.DUMPLING_SUN)));
    public static final RegistryObject<Item> MAZELLOVVV_COOKIE = ITEMS.register("mazellovvv_cookie",
            () -> new Item(new Item.Properties().food(ModFoods.GINGER_BREAD)));

    // Default Items
    public static final RegistryObject<Item> TWITCH_DIAMOND = ITEMS.register("twitch_diamond",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SCHOOL_BOOT = ITEMS.register("school_boot",
            () -> new FuelItem(new Item.Properties(), 400));
    public static final RegistryObject<Item> COIN_ALLOY = ITEMS.register("coin_alloy",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DICE = ITEMS.register("dice",
            () -> new Item(new Item.Properties()));
    // Weapons
    public static final RegistryObject<Item> TWITCH_AXE = ITEMS.register("twitch_axe",
            () -> new AxeItem(ModToolTiers.TWITCH, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.TWITCH, 7, -3.5f))));
    public static final RegistryObject<Item> TWITCH_SWORD = ITEMS.register("twitch_sword",
            () -> new SwordItem(ModToolTiers.TWITCH, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.TWITCH, 4, -2.2f))));
    public static final RegistryObject<Item> TWITCH_SHOVEL = ITEMS.register("twitch_shovel",
            () -> new ShovelItem(ModToolTiers.TWITCH, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.TWITCH, 0, -2.5f))));
    public static final RegistryObject<Item> TWITCH_PICKAXE = ITEMS.register("twitch_pickaxe",
            () -> new PickaxeItem(ModToolTiers.TWITCH, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.TWITCH, 0, -2.5f))));
    public static final RegistryObject<Item> TWITCH_HOE = ITEMS.register("twitch_hoe",
            () -> new HoeItem(ModToolTiers.TWITCH, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.TWITCH, 0, -2.5f))));
    // Armor
    public static final RegistryObject<Item> GLASSES = ITEMS.register("glasses",
            () -> new ArmorItem(ModArmorMaterials.GLASSES, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> STINTIK_HELMET = ITEMS.register("stint_helmet",
            () -> new ArmorItem(ModArmorMaterials.STINT, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> BOXERS = ITEMS.register("boxers",
            () -> new ArmorItem(ModArmorMaterials.BOXERS, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> DND = ITEMS.register("dnd_chestplate",
            () -> new ArmorItem(ModArmorMaterials.DND, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> DRAKE_PENDANT = ITEMS.register("drake_pendant",
            () -> new ArmorItem(ModArmorMaterials.PENDANT, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    // Food
    public static final RegistryObject<Item> OVERCOOKED_DUMPLING = ITEMS.register("overcooked_dumpling",
            () -> new Item(new Item.Properties().food(ModFoods.OVERCOOKED_DUMPLING)));

    public static int rgbToHex(int r, int g, int b) {
        return (r << 16) | (g << 8) | b;
    }
}
