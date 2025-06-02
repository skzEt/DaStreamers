package net.skzEt.StreamerMod.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.skzEt.StreamerMod.Streamer;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> OM_NOM_EXPLODE = createTag("om_nom_explode");
        public static final TagKey<Block> OM_NOM_ORE = createTag("om_nom_ore");
        public static final TagKey<Block> NEEDS_TWITCH_TOOLS = createTag("needs_twitch_tool");
        public static final TagKey<Block> INCORRECT_FOR_TWITCH_TOOL = createTag("incorrect_for_twitch_tool");
        public static final TagKey<Block> VALUABLES = createTag("valuables");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Streamer.MOD_ID, name));
        }
    }

    public static class Items {
        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Streamer.MOD_ID, name));
        }
    }
}
