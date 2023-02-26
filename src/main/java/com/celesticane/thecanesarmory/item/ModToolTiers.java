package com.celesticane.thecanesarmory.item;

import com.celesticane.thecanesarmory.CanesArmory;
import com.celesticane.thecanesarmory.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static Tier ENCORIUM;

    static {
        ENCORIUM = TierSortingRegistry.registerTier(
                new ForgeTier(5,2551,10f,5f,18, ModTags.Blocks.NEEDS_ENCORIUM_TOOL, () -> Ingredient.of(ModItems.ENCORIUM.get())),
                new ResourceLocation(CanesArmory.MODID, "encorium"), List.of(Tiers.NETHERITE), List.of());
    }
}
