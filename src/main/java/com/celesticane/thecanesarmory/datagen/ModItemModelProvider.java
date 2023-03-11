package com.celesticane.thecanesarmory.datagen;

import com.celesticane.thecanesarmory.CanesArmory;
import com.celesticane.thecanesarmory.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CanesArmory.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.ENCORIUM);
        simpleItem(ModItems.RAW_ENCORIUM);
        simpleItem(ModItems.ENCORIUM_HELMET);
        simpleItem(ModItems.ENCORIUM_CHESTPLATE);
        simpleItem(ModItems.ENCORIUM_LEGGINGS);
        simpleItem(ModItems.ENCORIUM_BOOTS);
        simpleHandheldItem(ModItems.ENCORIUM_SWORD);
        simpleHandheldItem(ModItems.ENCORIUM_SHOVEL);
        simpleHandheldItem(ModItems.ENCORIUM_PICKAXE);
        simpleHandheldItem(ModItems.ENCORIUM_AXE);
        simpleHandheldItem(ModItems.ENCORIUM_HOE);
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
}
