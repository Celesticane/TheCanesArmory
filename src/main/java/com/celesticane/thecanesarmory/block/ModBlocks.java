package com.celesticane.thecanesarmory.block;

import com.celesticane.thecanesarmory.CanesArmory;
import com.celesticane.thecanesarmory.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.util.function.Supplier;


public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, CanesArmory.MODID);

    public static final RegistryObject<Block> ENCORIUM_BLOCK = registerBlock("encorium_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(60, 1500).requiresCorrectToolForDrops().sound(SoundType.NETHERITE_BLOCK)), Rarity.UNCOMMON, true);
    //In order to define either rarity or fireproof-ness in the last segments of block registering, you must define both (or none); one or the other won't suffice
    public static final RegistryObject<Block> TIMELESS_DEBRIS = registerBlock("timeless_debris",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS).strength(30, 1500).requiresCorrectToolForDrops().sound(SoundType.ANCIENT_DEBRIS)), Rarity.COMMON, true);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        return registerBlock(name, block, Rarity.COMMON, false);
    }

    //Added thanks to ZestyBlaze from the Mystic Modding community for drafting this next segment of code with me!
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, Rarity rarity, boolean fireproof) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        if(fireproof) {
            registerFireproofBlockItem(name, toReturn, rarity);
        } else {
            registerBlockItem(name, toReturn, rarity);
        }
        return toReturn;
    }

    private static<T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, Rarity rarity) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().rarity(rarity)));
    }

    private static<T extends Block> RegistryObject<Item> registerFireproofBlockItem(String name, RegistryObject<T> block, Rarity rarity) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().fireResistant().rarity(rarity)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}