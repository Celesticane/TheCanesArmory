package com.celesticane.thecanesarmory.datagen;

import com.celesticane.thecanesarmory.CanesArmory;
import com.celesticane.thecanesarmory.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
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
        /* This next one's something of a hack.
        Essentially, I datagenned Timeless Debris as a full-fledged axis block,
        and then modified the generated model states so that it can only be placed vertically.
        I'm sure there's a more elegant solution to this problem that I'll uncover later, but it works for now, yeah?
         */
        axisBlock((RotatedPillarBlock) ModBlocks.TIMELESS_DEBRIS.get(),
                new ResourceLocation(CanesArmory.MODID, "block/timeless_debris"), new ResourceLocation(CanesArmory.MODID, "block/timeless_debris_top"));
        simpleBlockItem(ModBlocks.TIMELESS_DEBRIS.get(), models().withExistingParent("thecanesarmory:timeless_debris", "minecraft:block/cube_column"));

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

}
