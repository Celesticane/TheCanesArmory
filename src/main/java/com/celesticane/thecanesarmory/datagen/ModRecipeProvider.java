package com.celesticane.thecanesarmory.datagen;

import com.celesticane.thecanesarmory.block.ModBlocks;
import com.celesticane.thecanesarmory.item.ModItems;
import com.celesticane.thecanesarmory.util.ModTags;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.NonNullList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.FireworkRocketRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }


    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        nineBlockStorageRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, ModItems.ENCORIUM.get(),RecipeCategory.MISC, ModBlocks.ENCORIUM_BLOCK.get());
        /*for (Item item : ModTags.Items.SMITHING_TEMPLATES) {
            copyTemplateViaBlank(item).save(consumer);
        }*/


        }



        public static ShapelessRecipeBuilder copyTemplateViaBlank(Item template) {
            return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, template, 2).requires(template).requires(ModItems.TEMPLATE_BLANK.get()).unlockedBy("has_blank", has(ModItems.TEMPLATE_BLANK.get()));
        }

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
