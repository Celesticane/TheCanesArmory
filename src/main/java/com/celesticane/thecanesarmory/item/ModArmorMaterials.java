package com.celesticane.thecanesarmory.item;

import com.celesticane.thecanesarmory.CanesArmory;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

@SuppressWarnings("depreciation")
public enum ModArmorMaterials implements ArmorMaterial {

    GILDED("gilded", 18, new int[]{3, 6, 7, 2}, 25, SoundEvents.ARMOR_EQUIP_GOLD, 1.0F, 0.0F, () -> {
        return Ingredient.of(Items.IRON_INGOT);
    }),
    ENCORIUM("encorium", 45, new int[]{3, 6, 8, 3}, 18, SoundEvents.ARMOR_EQUIP_NETHERITE, 4.0F, 0.3F, () -> {
        return Ingredient.of(ModItems.ENCORIUM.get());
    });

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private ModArmorMaterials(String p_40474_, int p_40475_, int[] p_40476_, int p_40477_, SoundEvent p_40478_, float p_40479_, float p_40480_, Supplier<Ingredient> p_40481_) {
        this.name = p_40474_;
        this.durabilityMultiplier = p_40475_;
        this.slotProtections = p_40476_;
        this.enchantmentValue = p_40477_;
        this.sound = p_40478_;
        this.toughness = p_40479_;
        this.knockbackResistance = p_40480_;
        this.repairIngredient = new LazyLoadedValue<>(p_40481_);
    }

    public int getDurabilityForType(ArmorItem.Type type) {
        return HEALTH_PER_SLOT[type.getSlot().getIndex()] * this.durabilityMultiplier;
    }
    public int getDefenseForType(ArmorItem.Type type) {
        return this.slotProtections[type.getSlot().getIndex()];
    }
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }
    public SoundEvent getEquipSound() {
        return this.sound;
    }
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
    public String getName() {
        return CanesArmory.MODID + ":" + this.name;
    }
    public float getToughness() {
        return this.toughness;
    }
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
