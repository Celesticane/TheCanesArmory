package com.celesticane.thecanesarmory.item;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class ModSmithingTemplates extends SmithingTemplateItem {

    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE; //Both of these two are 'borrowed' from Minecraft's SmithingTemplateItem class for the sake of consistency

    private static final String DESCRIPTION_ID = Util.makeDescriptionId("item", new ResourceLocation("smithing_template"));
    private static final Component INGREDIENTS_TITLE = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.ingredients"))).withStyle(TITLE_FORMAT);
    private static final Component APPLIES_TO_TITLE = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.applies_to"))).withStyle(TITLE_FORMAT);
    private static final ResourceLocation EMPTY_SLOT_HELMET = new ResourceLocation("item/empty_armor_slot_helmet");
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = new ResourceLocation("item/empty_armor_slot_chestplate");
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS = new ResourceLocation("item/empty_armor_slot_leggings");
    private static final ResourceLocation EMPTY_SLOT_BOOTS = new ResourceLocation("item/empty_armor_slot_boots");
    private static final ResourceLocation EMPTY_SLOT_HOE = new ResourceLocation("item/empty_slot_hoe");
    private static final ResourceLocation EMPTY_SLOT_AXE = new ResourceLocation("item/empty_slot_axe");
    private static final ResourceLocation EMPTY_SLOT_SWORD = new ResourceLocation("item/empty_slot_sword");
    private static final ResourceLocation EMPTY_SLOT_SHOVEL = new ResourceLocation("item/empty_slot_shovel");
    private static final ResourceLocation EMPTY_SLOT_PICKAXE = new ResourceLocation("item/empty_slot_pickaxe");
    private static final ResourceLocation EMPTY_SLOT_INGOT = new ResourceLocation("item/empty_slot_ingot");
/*    private final Component appliesTo;
    private final Component ingredients;
    private final Component upgradeDescription; */


    //private static final Component ENCORIUM_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.encorium_upgrade_applies_to"))).
    private static final Component ENCORIUM_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation("encorium_upgrade"))).withStyle(TITLE_FORMAT);

    public ModSmithingTemplates(Component p_266834_, Component p_267043_, Component p_267048_, Component p_267278_, Component p_267090_, List<ResourceLocation> p_266755_, List<ResourceLocation> p_267060_) {
        super(p_266834_, p_267043_, p_267048_, p_267278_, p_267090_, p_266755_, p_267060_);
    }

    //public static SmithingTemplateItem createEncoriumUpgradeTemplate() {
    //return new SmithingTemplateItem(ENCORIUM_UPGRADE_APPLIES_TO, NETHERITE_UPGRADE_INGREDIENTS, ENCORIUM_UPGRADE, NETHERITE_UPGRADE_BASE_SLOT_DESCRIPTION, ENCORIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createNetheriteUpgradeIconList(), createNetheriteUpgradeMaterialList());
    //}

}

