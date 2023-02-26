package com.celesticane.thecanesarmory.datagen;

import com.celesticane.thecanesarmory.CanesArmory;
import com.celesticane.thecanesarmory.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;


public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CanesArmory.MODID,existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.ENCORIUM_BLOCK);
        //columnBlockWithItem(ModBlocks.TIMELESS_DEBRIS);
        //blockWithItem(ModBlocks.TIMELESS_DEBRIS);

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
    //private void columnBlockWithItem(RegistryObject<Block> blockRegistryObject) {
        //logBlockWithRenderType(stuff, Column);
        //simpleBlockItem(blockRegistryObject.get(), blockRegistryObject.getId());

}
