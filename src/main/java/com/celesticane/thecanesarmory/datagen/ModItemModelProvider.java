package com.celesticane.thecanesarmory.datagen;

import com.celesticane.thecanesarmory.CanesArmory;
import com.celesticane.thecanesarmory.item.ModItems;
import com.celesticane.thecanesarmory.item.custom.DiamondShieldItem;
import com.celesticane.thecanesarmory.item.custom.RelicDetectorItem;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CanesArmory.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.ENCORIUM);
        simpleItem(ModItems.RAW_ENCORIUM);
        simpleItem(ModItems.TEMPLATE_BLANK);
        simpleItem(ModItems.ENCORIUM_TEMPLATE_ITEM);
        simpleItem(ModItems.CANES_TEMPLATE_ITEM);
        simpleHandheldItem(ModItems.AGILE_SWORD);
        simpleHandheldItem(ModItems.OBSIDIAN_SWORD);
        simpleHandheldItem(ModItems.GILDED_SWORD);
        simpleHandheldItem(ModItems.GILDED_SHOVEL);
        simpleHandheldItem(ModItems.GILDED_PICKAXE);
        simpleHandheldItem(ModItems.GILDED_AXE);
        simpleHandheldItem(ModItems.GILDED_HOE);
        simpleHandheldItem(ModItems.ENCORIUM_SWORD);
        simpleHandheldItem(ModItems.ENCORIUM_SHOVEL);
        simpleHandheldItem(ModItems.ENCORIUM_PICKAXE);
        simpleHandheldItem(ModItems.ENCORIUM_AXE);
        simpleHandheldItem(ModItems.ENCORIUM_HOE);
        trimmedArmorItem(ModItems.GILDED_HELMET);
        trimmedArmorItem(ModItems.GILDED_CHESTPLATE);
        trimmedArmorItem(ModItems.GILDED_LEGGINGS);
        trimmedArmorItem(ModItems.GILDED_BOOTS);
        trimmedArmorItem(ModItems.ENCORIUM_HELMET);
        trimmedArmorItem(ModItems.ENCORIUM_CHESTPLATE);
        trimmedArmorItem(ModItems.ENCORIUM_LEGGINGS);
        trimmedArmorItem(ModItems.ENCORIUM_BOOTS);
        detectorItem(ModItems.NETHERITE_DETECTOR);
        detectorItem(ModItems.ENCORIUM_DETECTOR);


    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(CanesArmory.MODID, "item/" + item.getId().getPath()));

    }

    private ItemModelBuilder simpleHandheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(CanesArmory.MODID, "item/" + item.getId().getPath()));

    }
    private ItemModelBuilder detectorItem(RegistryObject<RelicDetectorItem> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(CanesArmory.MODID, "item/" + item.getId().getPath()));

    }


    //Copy-pasted from Kaupenjoe's tutorial series, and in turn created by El_Redstoniano
    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = CanesArmory.MODID;

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath);
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }

}
