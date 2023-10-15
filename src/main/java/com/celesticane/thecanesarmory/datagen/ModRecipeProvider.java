package com.celesticane.thecanesarmory.datagen;

import com.celesticane.thecanesarmory.block.ModBlocks;
import com.celesticane.thecanesarmory.item.ModItems;
import com.celesticane.thecanesarmory.util.ModTags;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.NonNullList;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.FireworkRocketRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.data.ForgeItemTagsProvider;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }


    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        nineBlockStorageRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, ModItems.ENCORIUM.get(), RecipeCategory.MISC, ModBlocks.ENCORIUM_BLOCK.get());
        copySmithingTemplate(consumer, ModItems.CANES_TEMPLATE_ITEM.get(), Items.GOLD_BLOCK);
        //for (Item item : ModTags.Items.SMITHING_TEMPLATES) {
        //   copyTemplateViaBlank(item).save(consumer);
        //}
        encoriumSmithing(consumer, Items.NETHERITE_SWORD, RecipeCategory.COMBAT, ModItems.ENCORIUM_SWORD.get());
        encoriumSmithing(consumer, Items.NETHERITE_SHOVEL, RecipeCategory.TOOLS, ModItems.ENCORIUM_SHOVEL.get());
        encoriumSmithing(consumer, Items.NETHERITE_PICKAXE, RecipeCategory.TOOLS, ModItems.ENCORIUM_PICKAXE.get());
        encoriumSmithing(consumer, Items.NETHERITE_AXE, RecipeCategory.TOOLS, ModItems.ENCORIUM_AXE.get());
        encoriumSmithing(consumer, Items.NETHERITE_HOE, RecipeCategory.TOOLS, ModItems.ENCORIUM_HOE.get());
        encoriumSmithing(consumer, ModItems.NETHERITE_DETECTOR.get(), RecipeCategory.TOOLS, ModItems.ENCORIUM_DETECTOR.get());
        encoriumSmithing(consumer, Items.NETHERITE_HELMET, RecipeCategory.COMBAT, ModItems.ENCORIUM_HELMET.get());
        encoriumSmithing(consumer, Items.NETHERITE_CHESTPLATE, RecipeCategory.COMBAT, ModItems.ENCORIUM_CHESTPLATE.get());
        encoriumSmithing(consumer, Items.NETHERITE_LEGGINGS, RecipeCategory.COMBAT, ModItems.ENCORIUM_LEGGINGS.get());
        encoriumSmithing(consumer, Items.NETHERITE_BOOTS, RecipeCategory.COMBAT, ModItems.ENCORIUM_BOOTS.get());
        canesSmithing(consumer, Items.IRON_SWORD, Items.REDSTONE_BLOCK, RecipeCategory.COMBAT, ModItems.AGILE_SWORD.get());
        canesSmithing(consumer, Items.STONE_SWORD, Items.OBSIDIAN, RecipeCategory.COMBAT, ModItems.OBSIDIAN_SWORD.get());
        //canesSmithing(consumer, Items.SHIELD, Items.DIAMOND, RecipeCategory.COMBAT, ModItems.DIAMOND_SHIELD.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.ENCORIUM_TEMPLATE_ITEM.get())
                .define('n', Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                .define('b', ModItems.TEMPLATE_BLANK.get())
                .define('e', Items.END_STONE)
                .pattern("ene")
                .pattern("ebe")
                .pattern("ene")
                .unlockedBy("has_blank", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.TEMPLATE_BLANK.get()).build()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.NETHERITE_DETECTOR.get())
                .define('n', Items.NETHERITE_INGOT)
                .define('g', Items.GOLD_NUGGET)
                .define('c', Items.COMPASS)
                .define('t', Items.REDSTONE_TORCH)
                .define('p', Items.GLASS_PANE)
                .pattern("tpt")
                .pattern("gcg")
                .pattern(" n ")
                .unlockedBy("has_netherite", inventoryTrigger(ItemPredicate.Builder.item().of(Items.NETHERITE_INGOT).build()))
                .save(consumer);
    }

        public static ShapelessRecipeBuilder copyTemplateViaBlank(Item template) {
            return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, template, 2).requires(template).requires(ModItems.TEMPLATE_BLANK.get()).unlockedBy("has_blank", has(ModItems.TEMPLATE_BLANK.get()));
        }

        protected static void canesSmithing(Consumer<FinishedRecipe> pFinishedRecipeConsumer, Item pIngredientItem, Item upgradeCatalyst, RecipeCategory pCategory, Item pResultItem) {
            SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.CANES_TEMPLATE_ITEM.get()), Ingredient.of(pIngredientItem), Ingredient.of(upgradeCatalyst), pCategory, pResultItem).unlocks("has_canes_template", has(ModItems.CANES_TEMPLATE_ITEM.get())).save(pFinishedRecipeConsumer, getItemName(pResultItem) + "_smithing");
        }
        protected static void encoriumSmithing(Consumer<FinishedRecipe> pFinishedRecipeConsumer, Item pIngredientItem, RecipeCategory pCategory, Item pResultItem) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.ENCORIUM_TEMPLATE_ITEM.get()), Ingredient.of(pIngredientItem), Ingredient.of(ModItems.ENCORIUM.get()), pCategory, pResultItem).unlocks("has_encorium_ingot", has(ModItems.ENCORIUM_TEMPLATE_ITEM.get())).save(pFinishedRecipeConsumer, getItemName(pResultItem) + "_smithing");
    }



    }
