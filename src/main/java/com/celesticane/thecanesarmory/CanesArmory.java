package com.celesticane.thecanesarmory;

import com.celesticane.thecanesarmory.block.ModBlocks;
import com.celesticane.thecanesarmory.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CanesArmory.MODID)
public class CanesArmory
{
    public static final String MODID = "thecanesarmory";
    private static final Logger LOGGER = LogUtils.getLogger();

    public CanesArmory()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.ENCORIUM_BLOCK);
        }
        if (event.getTab() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModBlocks.TIMELESS_DEBRIS);
        }
        if(event.getTab() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModBlocks.TIMELESS_DEBRIS);
            event.accept(ModItems.RAW_ENCORIUM);
            event.accept(ModItems.ENCORIUM);
        }
        if(event.getTab() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.NETHERITE_DETECTOR);
            event.accept(ModItems.ENCORIUM_DETECTOR);
            event.accept(ModItems.ENCORIUM_SHOVEL);
            event.accept(ModItems.ENCORIUM_PICKAXE);
            event.accept(ModItems.ENCORIUM_AXE);
            event.accept(ModItems.ENCORIUM_HOE);
        }
        if(event.getTab() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.AGILE_SWORD);
            event.accept(ModItems.OBSIDIAN_SWORD);
            event.accept(ModItems.ENCORIUM_SWORD);
            event.accept(ModItems.ENCORIUM_HELMET);
            event.accept(ModItems.ENCORIUM_CHESTPLATE);
            event.accept(ModItems.ENCORIUM_LEGGINGS);
            event.accept(ModItems.ENCORIUM_BOOTS);
        }

    }


    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
        }
    }
}
