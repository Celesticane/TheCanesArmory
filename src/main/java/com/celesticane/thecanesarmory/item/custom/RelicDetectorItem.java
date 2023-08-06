package com.celesticane.thecanesarmory.item.custom;

import com.celesticane.thecanesarmory.util.ModTags;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class RelicDetectorItem extends Item {


    public int scanRange;
    public int readoutMode;

    public RelicDetectorItem(Properties pProperties, int scanRange, int readoutMode) {
        super(pProperties);
        this.scanRange = scanRange;
        this.readoutMode = readoutMode;
    }

   @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()) {
            if (this.scanRange == 0) {
                Player player = pContext.getPlayer();
                player.sendSystemMessage(Component.literal("scanRange Must exceed 0, you goof!"));
            } else {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;
            int cubeScan = this.scanRange * 2 + 1;

            for(int i = 0; i <= positionClicked.getY() + 64; i++) {
                BlockState state = pContext.getLevel().getBlockState(positionClicked.below(i));

                if (isRelicDetectable(state)) {
                    outputCoordinates(positionClicked.below(i), player, state.getBlock());
                    foundBlock = true;

                    break;
                }
            }
            if(!foundBlock) {
                player.sendSystemMessage(Component.literal("Error 404: Relics not found."));
            }}
        }
        return InteractionResult.SUCCESS;
    }
    private void outputCoordinates(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(Component.literal(I18n.get(block.getDescriptionId() + " detected at " + "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")")));
    }
    private boolean isRelicDetectable(BlockState state) {
        return state.is(ModTags.Blocks.RELIC_DETECTABLE);
    }
}

/*
*

import com.klikli_dev.theurgy.Theurgy;
import com.klikli_dev.theurgy.TheurgyConstants;
import com.klikli_dev.theurgy.client.scanner.ScanManager;
import com.klikli_dev.theurgy.entity.FollowProjectile;
import com.klikli_dev.theurgy.network.Networking;
import com.klikli_dev.theurgy.network.messages.MessageSetDivinationResult;
import com.klikli_dev.theurgy.registry.SoundRegistry;
import com.klikli_dev.theurgy.registry.TagRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Set;

public class DivinationRodItem extends Item {

    public static final float NOT_FOUND = 7.0f;
    public static final float SEARCHING = 8.0f;

    public Tier defaultTier;
    public TagKey<Block> defaultAllowedBlocksTag;
    public TagKey<Block> defaultDisallowedBlocksTag;

    public int defaultRange;
    public int defaultDuration;
    public boolean defaultAllowAttuning;

    public DivinationRodItem(Properties pProperties, Tier defaultTier, TagKey<Block> defaultAllowedBlocksTag, TagKey<Block> defaultDisallowedBlocksTag, int defaultRange, int defaultDuration, int defaultDurability, boolean defaultAllowAttuning) {
        super(pProperties);
        this.defaultTier = defaultTier;
        this.defaultAllowedBlocksTag = defaultAllowedBlocksTag;
        this.defaultDisallowedBlocksTag = defaultDisallowedBlocksTag;
        this.defaultRange = defaultRange;
        this.defaultDuration = defaultDuration;
        this.defaultAllowAttuning = defaultAllowAttuning;
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity entityLiving, int count) {
        if (entityLiving.level.isClientSide && entityLiving instanceof Player) {
            ScanManager.get().updateScan((Player) entityLiving, false);
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos pos = context.getClickedPos();
        ItemStack stack = context.getItemInHand();

        var tier = this.getMiningTier(stack);
        var allowedBlocksTag = this.getAllowedBlocksTag(stack);
        var disallowedBlocksTag = this.getDisallowedBlocksTag(stack);

        if (player.isShiftKeyDown()) {

            if (!stack.getOrCreateTag().getBoolean(TheurgyConstants.Nbt.Divination.SETTING_ALLOW_ATTUNING)) {
                if (!level.isClientSide) {
                    player.sendSystemMessage(
                            Component.translatable(TheurgyConstants.I18n.Message.DIVINATION_ROD_ATTUNING_NOT_ALLOWED)
                    );
                }
                return InteractionResult.FAIL;
            }

            BlockState state = level.getBlockState(pos);
            if (!state.isAir()) {
                if (!TierSortingRegistry.isCorrectTierForDrops(tier, state)) {
                    if (!level.isClientSide) {
                        player.sendSystemMessage(
                                Component.translatable(
                                        TheurgyConstants.I18n.Message.DIVINATION_ROD_TIER_TOO_LOW,
                                        this.getBlockDisplayComponent(state.getBlock())
                                )
                        );
                    }
                    return InteractionResult.FAIL;
                } else if (!state.is(allowedBlocksTag)) {
                    if (!level.isClientSide) {
                        player.sendSystemMessage(
                                Component.translatable(
                                        TheurgyConstants.I18n.Message.DIVINATION_ROD_BLOCK_NOT_ALLOWED,
                                        this.getBlockDisplayComponent(state.getBlock())
                                )
                        );
                    }
                    return InteractionResult.FAIL;
                } else if (state.is(disallowedBlocksTag)) {
                    if (!level.isClientSide) {
                        player.sendSystemMessage(
                                Component.translatable(
                                        TheurgyConstants.I18n.Message.DIVINATION_ROD_BLOCK_DISALLOWED,
                                        this.getBlockDisplayComponent(state.getBlock())
                                )
                        );
                    }
                    return InteractionResult.FAIL;
                } else {
                    if (!level.isClientSide) {
                        stack.getOrCreateTag().putString(
                                TheurgyConstants.Nbt.Divination.LINKED_BLOCK_ID,
                                ForgeRegistries.BLOCKS.getKey(state.getBlock()).toString()
                        );

                        player.sendSystemMessage(
                                Component.translatable(
                                        TheurgyConstants.I18n.Message.DIVINATION_ROD_LINKED,
                                        this.getBlockDisplayComponent(state.getBlock())
                                )
                        );
                    }

                    level.playSound(player, player.blockPosition(), SoundRegistry.TUNING_FORK.get(),
                            SoundSource.PLAYERS,
                            1, 1);

                    return InteractionResult.SUCCESS;
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        var stack = player.getItemInHand(hand);

        if (!player.isShiftKeyDown()) {
            if (stack.getOrCreateTag().contains(TheurgyConstants.Nbt.Divination.LINKED_BLOCK_ID)) {
                var tag = stack.getTag();
                tag.putFloat(TheurgyConstants.Nbt.Divination.DISTANCE, SEARCHING);
                player.startUsingItem(hand);
                level.playSound(player, player.blockPosition(), SoundRegistry.TUNING_FORK.get(), SoundSource.PLAYERS,
                        1, 1);

                if (level.isClientSide) {
                    var id = new ResourceLocation(stack.getTag().getString(TheurgyConstants.Nbt.Divination.LINKED_BLOCK_ID));
                    var block = ForgeRegistries.BLOCKS.getValue(id);
                    if (block != null) {
                        Block deepslateBlock = null;

                        //also search for deepslate ores
                        if (id.getPath().contains("_ore") && !id.getPath().contains("deepslate_")) {
                            var deepslateId = new ResourceLocation(id.getNamespace(), "deepslate_" + id.getPath());
                            deepslateBlock = ForgeRegistries.BLOCKS.getValue(deepslateId);
                        }

                        ScanManager.get().beginScan(player,
                                deepslateBlock != null ? Set.of(block, deepslateBlock) : Set.of(block),
                                tag.getInt(TheurgyConstants.Nbt.Divination.SETTING_RANGE),
                                tag.getInt(TheurgyConstants.Nbt.Divination.SETTING_DURATION)
                        );
                    }

                }
            } else if (!level.isClientSide) {
                player.sendSystemMessage(Component.translatable(TheurgyConstants.I18n.Message.DIVINATION_ROD_NO_LINK));
            }
        }

        return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entityLiving) {
        if (!(entityLiving instanceof Player player))
            return stack;

        player.getCooldowns().addCooldown(this, stack.getOrCreateTag().getInt(TheurgyConstants.Nbt.Divination.SETTING_DURATION));
        stack.getOrCreateTag().putFloat(TheurgyConstants.Nbt.Divination.DISTANCE, NOT_FOUND);
        if (level.isClientSide) {
            BlockPos result = ScanManager.get().finishScan(player);
            float distance = this.getDistance(player.position(), result);
            stack.getTag().putFloat(TheurgyConstants.Nbt.Divination.DISTANCE, distance);

            Networking.sendToServer(new MessageSetDivinationResult(result, distance));

            if (result != null) {
                stack.getTag().putLong(TheurgyConstants.Nbt.Divination.POS, result.asLong());
                this.spawnResultParticle(result, level, player);
            }
        }
        return stack;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return stack.getOrCreateTag().getInt(TheurgyConstants.Nbt.Divination.SETTING_DURATION);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity pLivingEntity, int pTimeCharged) {
        if (!stack.getOrCreateTag().contains(TheurgyConstants.Nbt.Divination.POS))
            //player interrupted, so we can safely set not found on server, if we don't have a previous result
            stack.getOrCreateTag().putFloat(TheurgyConstants.Nbt.Divination.DISTANCE, NOT_FOUND);
        else {
            //otherwise, restore distance from result
            //nice bonus: will update crystal status on every "display only" use.
            BlockPos result = BlockPos.of(stack.getTag().getLong(TheurgyConstants.Nbt.Divination.POS));
            float distance = this.getDistance(pLivingEntity.position(), result);
            stack.getTag().putFloat(TheurgyConstants.Nbt.Divination.DISTANCE, distance);
        }


        if (level.isClientSide) {
            ScanManager.get().cancelScan();

            //re-use old result
            if (stack.getTag().contains(TheurgyConstants.Nbt.Divination.POS)) {
                BlockPos result = BlockPos.of(stack.getTag().getLong(TheurgyConstants.Nbt.Divination.POS));
                this.spawnResultParticle(result, level, pLivingEntity);
            }
        }
        super.releaseUsing(stack, level, pLivingEntity, pTimeCharged);
    }

    @Override
    public Component getName(ItemStack pStack) {
        if (pStack.hasTag()) {
            var tag = pStack.getTag();
            if (tag.contains(TheurgyConstants.Nbt.Divination.LINKED_BLOCK_ID)) {
                var id = new ResourceLocation(pStack.getTag().getString(TheurgyConstants.Nbt.Divination.LINKED_BLOCK_ID));
                var block = ForgeRegistries.BLOCKS.getValue(id);
                if (block != null) {
                    //we're not using getBlockDisplayComponent because we want custom formatting
                    var blockComponent = ComponentUtils.wrapInSquareBrackets(
                                    block.getName().withStyle(Style.EMPTY.withColor(ChatFormatting.GREEN).withItalic(true))
                            )
                            .withStyle((style) -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, new HoverEvent.ItemStackInfo(new ItemStack(block)))));
                    return Component.translatable(this.getDescriptionId() + ".linked", blockComponent);
                }
            }
        }

        return super.getName(pStack);
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        var tag = stack.getOrCreateTag();

        //fill in any nbt that is not provided by the recipe with default values
        if (!tag.contains(TheurgyConstants.Nbt.Divination.SETTING_TIER))
            tag.putString(TheurgyConstants.Nbt.Divination.SETTING_TIER, TierSortingRegistry.getName(this.defaultTier).toString());

        if (!tag.contains(TheurgyConstants.Nbt.Divination.SETTING_ALLOWED_BLOCKS_TAG))
            tag.putString(TheurgyConstants.Nbt.Divination.SETTING_ALLOWED_BLOCKS_TAG, this.defaultAllowedBlocksTag.location().toString());

        if (!tag.contains(TheurgyConstants.Nbt.Divination.SETTING_DISALLOWED_BLOCKS_TAG))
            tag.putString(TheurgyConstants.Nbt.Divination.SETTING_DISALLOWED_BLOCKS_TAG, this.defaultDisallowedBlocksTag.location().toString());

        if (!tag.contains(TheurgyConstants.Nbt.Divination.SETTING_RANGE))
            tag.putInt(TheurgyConstants.Nbt.Divination.SETTING_RANGE, this.defaultRange);

        if (!tag.contains(TheurgyConstants.Nbt.Divination.SETTING_DURATION))
            tag.putInt(TheurgyConstants.Nbt.Divination.SETTING_DURATION, this.defaultDuration);

        if (!tag.contains(TheurgyConstants.Nbt.Divination.SETTING_DURABILITY))
            tag.putInt(TheurgyConstants.Nbt.Divination.SETTING_DURABILITY, this.defaultDurability);

        if (!tag.contains(TheurgyConstants.Nbt.Divination.SETTING_ALLOW_ATTUNING))
            tag.putBoolean(TheurgyConstants.Nbt.Divination.SETTING_ALLOW_ATTUNING, this.defaultAllowAttuning);

        return super.initCapabilities(stack, nbt);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (pStack.hasTag()) {
            var tag = pStack.getTag();
            if (tag.contains(TheurgyConstants.Nbt.Divination.LINKED_BLOCK_ID)) {
                var id = new ResourceLocation(pStack.getTag().getString(TheurgyConstants.Nbt.Divination.LINKED_BLOCK_ID));
                var block = ForgeRegistries.BLOCKS.getValue(id);
                if (block != null) {
                    var blockComponent = this.getBlockDisplayComponent(block);
                    pTooltipComponents.add(
                            Component.translatable(
                                    TheurgyConstants.I18n.Tooltip.DIVINATION_ROD_LINKED_TO,
                                    blockComponent
                            ).withStyle(ChatFormatting.GRAY));

                    if (tag.contains(TheurgyConstants.Nbt.Divination.POS)) {
                        var pos = BlockPos.of(tag.getLong(TheurgyConstants.Nbt.Divination.POS));
                        pTooltipComponents.add(Component.translatable(TheurgyConstants.I18n.Tooltip.DIVINATION_ROD_LAST_RESULT,
                                blockComponent,
                                ComponentUtils.wrapInSquareBrackets(Component.literal(pos.toShortString())).withStyle(ChatFormatting.GREEN)
                        ).withStyle(ChatFormatting.GRAY));
                    }
                }

            } else {
                pTooltipComponents.add(Component.translatable(TheurgyConstants.I18n.Tooltip.DIVINATION_ROD_NO_LINK));
            }

        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    /**
     * Calculates the distance parameter representing the actual distance.
     *
     * @param playerPosition the player position.
     * @param result         the result position to get the distance to.
     * @return the distance parameter as used in the distance property, not the actual distance.

public float getDistance(Vec3 playerPosition, BlockPos result) {
    if (result == null)
        return NOT_FOUND;

    Vec3 resultCenter = Vec3.atCenterOf(result);
    Vec3 playerPosition2d = new Vec3(playerPosition.x, 0, playerPosition.z);
    Vec3 resultCenter2d = new Vec3(resultCenter.x, 0, resultCenter.z);
    double distance = playerPosition2d.distanceTo(resultCenter2d);

    if (distance < 6.0)
        return 0.0f;
    if (distance < 15.0)
        return 1.0f;
    if (distance < 25.0)
        return 2.0f;
    if (distance < 35.0)
        return 3.0f;
    if (distance < 45)
        return 4.0f;
    if (distance < 65)
        return 5.0f;
    return 6.0f;
}

    protected MutableComponent getBlockDisplayComponent(Block block) {
        var displayName = block.getName();
        return ComponentUtils.wrapInSquareBrackets(displayName)
                .withStyle(ChatFormatting.GREEN)
                .withStyle((style) -> style.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, new HoverEvent.ItemStackInfo(new ItemStack(block)))));
    }

    protected void spawnResultParticle(BlockPos result, Level level, LivingEntity entity) {
        final var visualizationRange = 10.0f;
        var from = new Vec3(entity.getX(), entity.getEyeY() - (double) 0.1F, entity.getZ());
        var resultVec = Vec3.atCenterOf(result);
        var dist = resultVec.subtract(from);
        var dir = dist.normalize();
        var to = dist.length() <= visualizationRange ? resultVec : from.add(dir.scale(visualizationRange));

        }
    }

    public Tier getMiningTier(ItemStack stack) {
        var tier = stack.getOrCreateTag().getString(TheurgyConstants.Nbt.Divination.SETTING_TIER);
        return TierSortingRegistry.byName(new ResourceLocation(tier));
    }

    public TagKey<Block> getAllowedBlocksTag(ItemStack stack) {
        var allowedBlocksTag = stack.getOrCreateTag().getString(TheurgyConstants.Nbt.Divination.SETTING_ALLOWED_BLOCKS_TAG);
        return TagRegistry.makeBlockTag(new ResourceLocation(allowedBlocksTag));
    }

    public TagKey<Block> getDisallowedBlocksTag(ItemStack stack) {
        var disallowedBlocksTag = stack.getOrCreateTag().getString(TheurgyConstants.Nbt.Divination.SETTING_DISALLOWED_BLOCKS_TAG);
        return TagRegistry.makeBlockTag(new ResourceLocation(disallowedBlocksTag));
    }


/**
 * Inner class to avoid classloading issues on the server
 */

/*
public static class DistHelper {

    @SuppressWarnings("deprecation")
    public static ItemPropertyFunction DIVINATION_DISTANCE = (stack, world, entity, i) -> {
        if (!stack.getOrCreateTag().contains(TheurgyConstants.Nbt.Divination.DISTANCE) ||
                stack.getTag().getFloat(TheurgyConstants.Nbt.Divination.DISTANCE) < 0)
            return NOT_FOUND;
        return stack.getTag().getFloat(TheurgyConstants.Nbt.Divination.DISTANCE);
    };

    public static void registerCreativeModeTabs(DivinationRodItem item, CreativeModeTab.Output output) {
        var level = Minecraft.getInstance().level;
        if (level != null) {
            var recipeManager = level.getRecipeManager();
            recipeManager.getRecipes().forEach((recipe) -> {
                if (recipe.getResultItem() != null  && recipe.getResultItem().getItem() == item) {
                    output.accept(recipe.getResultItem().copy());
                }
            });
        }
    }
}
}
* */