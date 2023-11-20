package com.celesticane.thecanesarmory.datagen;

import com.celesticane.thecanesarmory.CanesArmory;
import com.celesticane.thecanesarmory.item.ModItems;
import com.celesticane.thecanesarmory.loot.AddItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, CanesArmory.MODID);
    }
    @Override
    protected void start() {
        add("cane_template_rabbithunt", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/rabbit")).build(),
                LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.04f, 0.04f).build()}, ModItems.CANES_TEMPLATE_ITEM.get()));

        add("cane_template_dungeon", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.80f).build()}, ModItems.CANES_TEMPLATE_ITEM.get()));
        add("cane_template_buried_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/buried_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.80f).build()}, ModItems.CANES_TEMPLATE_ITEM.get()));
        add("cane_template_netherfort", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/nether_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.60f).build()}, ModItems.CANES_TEMPLATE_ITEM.get()));
        add("cane_template_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.60f).build()}, ModItems.CANES_TEMPLATE_ITEM.get()));
        add("cane_template_stronghold", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/stronghold_corridor")).build(),
                LootItemRandomChanceCondition.randomChance(0.60f).build()}, ModItems.CANES_TEMPLATE_ITEM.get()));
        add("cane_template_bastion", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_other")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.CANES_TEMPLATE_ITEM.get()));
        add("cane_template_bastion_bridge", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.CANES_TEMPLATE_ITEM.get()));
        add("cane_template_outpost", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.CANES_TEMPLATE_ITEM.get()));
        add("cane_template_toolsmith", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village_toolsmith")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.CANES_TEMPLATE_ITEM.get()));
        add("cane_template_blacksmith", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village_blacksmith")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.CANES_TEMPLATE_ITEM.get()));
        add("cane_template_village_weaponsmith", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village_weaponsmith")).build(),
                LootItemRandomChanceCondition.randomChance(0.45f).build()}, ModItems.CANES_TEMPLATE_ITEM.get()));
        add("cane_template_desert", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/desert_temple")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ModItems.CANES_TEMPLATE_ITEM.get()));
        add("cane_template_village_armorer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ModItems.CANES_TEMPLATE_ITEM.get()));
        add("cane_template_village_cartographer", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village_cartographer")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ModItems.CANES_TEMPLATE_ITEM.get()));
        add("cane_template_bonus", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/spawn_bonus_chest")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ModItems.CANES_TEMPLATE_ITEM.get()));
        add("cane_template_portal", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ruined_portal")).build(),
                LootItemRandomChanceCondition.randomChance(0.35f).build()}, ModItems.CANES_TEMPLATE_ITEM.get()));
        add("cane_template_what", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple_dispenser")).build(),
                LootItemRandomChanceCondition.randomChance(0.20f).build()}, ModItems.CANES_TEMPLATE_ITEM.get()));
        add("cane_template_taiga_village", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village_taiga_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.20f).build()}, ModItems.CANES_TEMPLATE_ITEM.get()));
        add("cane_template_snowy_village", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village_snowy_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.20f).build()}, ModItems.CANES_TEMPLATE_ITEM.get()));
        add("cane_template_desert_village", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village_desert_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.20f).build()}, ModItems.CANES_TEMPLATE_ITEM.get()));
        add("cane_template_savanna_village", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village_savana_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.20f).build()}, ModItems.CANES_TEMPLATE_ITEM.get()));
        add("cane_template_plains_village", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village_plains_house")).build(),
                LootItemRandomChanceCondition.randomChance(0.20f).build()}, ModItems.CANES_TEMPLATE_ITEM.get()));

    }
}
