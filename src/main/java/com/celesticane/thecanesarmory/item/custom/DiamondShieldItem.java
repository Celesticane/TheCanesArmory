package com.celesticane.thecanesarmory.item.custom;

import com.celesticane.thecanesarmory.CanesArmory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class DiamondShieldItem extends ShieldItem {
    public int cooldown;
    public boolean isBlocking;
    public int maxUseDuration;

    public DiamondShieldItem(Properties properties) {
        super(properties);
        this.cooldown = 0;
        this.isBlocking = false;
        this.maxUseDuration = maxUseDuration;
    }

    //@Override
    //public ResourceLocation getModelResource(DiamondShieldItem object) {
    //}

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack stack = player.getItemInHand(interactionHand);
        player.startUsingItem(interactionHand);
        this.isBlocking = true;
        return new InteractionResultHolder<>(InteractionResult.CONSUME, stack);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        if (this.isBlocking) return UseAnim.BLOCK;
        return UseAnim.NONE;
    }

    @Override
    public boolean isValidRepairItem(ItemStack pToRepair, ItemStack pRepair) {
        return pRepair.is(Items.DIAMOND) || super.isValidRepairItem(pToRepair,  pRepair);
    }

    public void onStopUsing(Level level, LivingEntity entity, ItemStack stack, int count) {
        if (!entity.level().isClientSide) this.cooldown++;
        if (this.cooldown >= maxUseDuration) {
            entity.stopUsingItem();
            this.isBlocking = false;
        }
    }




}
