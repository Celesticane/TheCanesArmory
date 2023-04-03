package com.celesticane.thecanesarmory.datagen;

import com.celesticane.thecanesarmory.block.ModBlocks;
import com.celesticane.thecanesarmory.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }


    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        nineBlockStorageRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, ModItems.ENCORIUM.get(),RecipeCategory.MISC, ModBlocks.ENCORIUM_BLOCK.get());

      //  ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.NETHERITE_DETECTOR.get())
             //   .define('n', Items.NETHERITE_INGOT)
             //   .define('g', Items.GOLD_NUGGET)
             //   .define('c', Items.COMPASS)
             //   .define('t', Items.REDSTONE_TORCH)
             //   .define('p', Items.GLASS_PANE)
             //   .pattern("tpt")
             //   .pattern("gng")
             //   .pattern(" n ")
             //   .unlockedBy("has_netherite", inventoryTrigger(ItemPredicate.Builder.item()
             //           .of(Items.NETHERITE_INGOT).build()))
             //   .save(consumer);

    }
}
