package com.celesticane.thecanesarmory.item;

import com.celesticane.thecanesarmory.CanesArmory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CanesArmory.MODID);


    //Timeless Debris and Encorium-related items
        public static final RegistryObject<Item> ENCORIUM = ITEMS.register("encorium_ingot",
                () -> new Item(new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON)));

        public static final RegistryObject<Item> RAW_ENCORIUM = ITEMS.register("encorium_scrap",
                () -> new Item(new Item.Properties().fireResistant()));

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
            () -> new ArmorItem(ModArmorMaterials.ENCORIUM, EquipmentSlot.HEAD,
                    new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).stacksTo(1)));
        public static final RegistryObject<Item> ENCORIUM_CHESTPLATE = ITEMS.register("encorium_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ENCORIUM, EquipmentSlot.CHEST,
                    new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).stacksTo(1)));
        public static final RegistryObject<Item> ENCORIUM_LEGGINGS = ITEMS.register("encorium_leggings",
            () -> new ArmorItem(ModArmorMaterials.ENCORIUM, EquipmentSlot.LEGS,
                    new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).stacksTo(1)));
        public static final RegistryObject<Item> ENCORIUM_BOOTS = ITEMS.register("encorium_boots",
            () -> new ArmorItem(ModArmorMaterials.ENCORIUM, EquipmentSlot.FEET,
                    new Item.Properties().fireResistant().rarity(Rarity.UNCOMMON).stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
