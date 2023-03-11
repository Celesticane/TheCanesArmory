package com.celesticane.thecanesarmory.util;

import com.celesticane.thecanesarmory.CanesArmory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_ENCORIUM_TOOL
                = tag("needs_encorium_tool");

        public static final TagKey<Block> RELIC_DETECTABLE
                = tag("relic_detectable");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(CanesArmory.MODID, name));
        }
        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }

    }
}
