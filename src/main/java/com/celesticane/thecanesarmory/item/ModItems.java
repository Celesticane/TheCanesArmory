package com.celesticane.thecanesarmory.item;

import com.celesticane.thecanesarmory.CanesArmory;
import com.celesticane.thecanesarmory.item.custom.DiamondShieldItem;
import com.celesticane.thecanesarmory.item.custom.GildedArmorItem;
import com.celesticane.thecanesarmory.item.custom.RelicDetectorItem;
import com.celesticane.thecanesarmory.item.ModSmithingTemplates;
import net.minecraft.world.item.*;
import net.minecraftforge.common.property.Properties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CanesArmory.MODID);

    public static final RegistryObject<Item> CANES_TEMPLATE_ITEM = ITEMS.register("canes_upgrade_smithing_template",
            ModSmithingTemplates::createCanesUpgradeTemplate);
    public static final RegistryObject<Item> AGILE_SWORD = ITEMS.register("agile_sword",
            () -> new SwordItem(Tiers.IRON, 3, -1.8f,
                    new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> OBSIDIAN_SWORD = ITEMS.register("obsidian_sword",
            () -> new SwordItem(ModToolTiers.OBSIDIAN, 0, -2.4f,
                    new Item.Properties().stacksTo(1)));
    public static final RegistryObject<DiamondShieldItem> DIAMOND_SHIELD = ITEMS.register("diamond_shield",
            () -> new DiamondShieldItem(new Item.Properties().stacksTo(1).durability(1847)));

    //Gilded Equipment
    public static final RegistryObject<Item> GILDED_SWORD = ITEMS.register("gilded_sword",
            () -> new SwordItem(ModToolTiers.GILDED, 3, -2.4f,
                    new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> GILDED_SHOVEL = ITEMS.register("gilded_shovel",
            () -> new ShovelItem(ModToolTiers.GILDED, 1.5f, -3f,
                    new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> GILDED_PICKAXE = ITEMS.register("gilded_pickaxe",
            () -> new PickaxeItem(ModToolTiers.GILDED, 1, -2.8f,
                    new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> GILDED_AXE = ITEMS.register("gilded_axe",
            () -> new AxeItem(ModToolTiers.GILDED, 5.5f, -3f,
                    new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> GILDED_HOE = ITEMS.register("gilded_hoe",
            () -> new HoeItem(ModToolTiers.GILDED,  -2, -0.5f,
                    new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> GILDED_HELMET = ITEMS.register("gilded_helmet",
            () -> new GildedArmorItem(ModArmorMaterials.GILDED, ArmorItem.Type.HELMET,
                    new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> GILDED_CHESTPLATE = ITEMS.register("gilded_chestplate",
            () -> new GildedArmorItem(ModArmorMaterials.GILDED, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> GILDED_LEGGINGS = ITEMS.register("gilded_leggings",
            () -> new GildedArmorItem(ModArmorMaterials.GILDED, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> GILDED_BOOTS = ITEMS.register("gilded_boots",
            () -> new GildedArmorItem(ModArmorMaterials.GILDED, ArmorItem.Type.BOOTS,
                    new Item.Properties().stacksTo(1)));

    //Timeless Debris and Encorium-related items
        public static final RegistryObject<Item> ENCORIUM = ITEMS.register("encorium_ingot",
                () -> new Item(new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON)));

        public static final RegistryObject<Item> RAW_ENCORIUM = ITEMS.register("encorium_scrap",
                () -> new Item(new Item.Properties().fireResistant()));

        public static final RegistryObject<Item> ENCORIUM_TEMPLATE_ITEM = ITEMS.register("encorium_upgrade_smithing_template",
                ModSmithingTemplates::createEncoriumUpgradeTemplate);

        public static final RegistryObject<Item> ENCORIUM_SWORD = ITEMS.register("encorium_sword",
                () -> new SwordItem(ModToolTiers.ENCORIUM, 3, -2.4f,
                        new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).stacksTo(1)));

        public static final RegistryObject<Item> ENCORIUM_SHOVEL = ITEMS.register("encorium_shovel",
                () -> new ShovelItem(ModToolTiers.ENCORIUM, 1.5f, -3f,
                        new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).stacksTo(1)));

        public static final RegistryObject<Item> ENCORIUM_PICKAXE = ITEMS.register("encorium_pickaxe",
                () -> new PickaxeItem(ModToolTiers.ENCORIUM, 1, -2.8f,
                        new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).stacksTo(1)));

        public static final RegistryObject<Item> ENCORIUM_AXE = ITEMS.register("encorium_axe",
                () -> new AxeItem(ModToolTiers.ENCORIUM, 5, -3f,
                        new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).stacksTo(1)));
        public static final RegistryObject<Item> ENCORIUM_HOE = ITEMS.register("encorium_hoe",
                () -> new HoeItem(ModToolTiers.ENCORIUM, -4, 0f,
                        new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).stacksTo(1)));
        public static final RegistryObject<Item> ENCORIUM_HELMET = ITEMS.register("encorium_helmet",
            () -> new ArmorItem(ModArmorMaterials.ENCORIUM, ArmorItem.Type.HELMET,
                    new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).stacksTo(1)));
        public static final RegistryObject<Item> ENCORIUM_CHESTPLATE = ITEMS.register("encorium_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ENCORIUM, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).stacksTo(1)));
        public static final RegistryObject<Item> ENCORIUM_LEGGINGS = ITEMS.register("encorium_leggings",
            () -> new ArmorItem(ModArmorMaterials.ENCORIUM, ArmorItem.Type.LEGGINGS,
                    new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).stacksTo(1)));
        public static final RegistryObject<Item> ENCORIUM_BOOTS = ITEMS.register("encorium_boots",
            () -> new ArmorItem(ModArmorMaterials.ENCORIUM, ArmorItem.Type.BOOTS,
                    new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).stacksTo(1)));
        //Relic Detectors
        public static final RegistryObject<RelicDetectorItem> NETHERITE_DETECTOR = ITEMS.register("relic_detector",
            () -> new RelicDetectorItem(new Item.Properties().fireResistant().stacksTo(1),3, 1));
        public static final RegistryObject<RelicDetectorItem> ENCORIUM_DETECTOR = ITEMS.register("relic_detector_plus",
            () -> new RelicDetectorItem(new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).stacksTo(1), 4, 1));

        public static final RegistryObject<Item> TEMPLATE_BLANK = ITEMS.register("smithing_template_blank", () -> new Item (new Item.Properties().fireResistant()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
