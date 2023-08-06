package com.celesticane.thecanesarmory.item;

import com.celesticane.thecanesarmory.CanesArmory;
import com.celesticane.thecanesarmory.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static Tier OBSIDIAN;
    public static Tier ENCORIUM;

    static {
        OBSIDIAN = TierSortingRegistry.registerTier(
                new ForgeTier(3,16,8.0f,8f,8, Tags.Blocks.OBSIDIAN, () -> Ingredient.of(Items.OBSIDIAN)),
                new ResourceLocation(CanesArmory.MODID, "obsidian"), List.of(Tiers.IRON), List.of(Tiers.DIAMOND));
        ENCORIUM = TierSortingRegistry.registerTier(
                new ForgeTier(5,2551,10f,6f,18, ModTags.Blocks.NEEDS_ENCORIUM_TOOL, () -> Ingredient.of(ModItems.ENCORIUM.get())),
                new ResourceLocation(CanesArmory.MODID, "encorium"), List.of(Tiers.NETHERITE), List.of());

    }
}
