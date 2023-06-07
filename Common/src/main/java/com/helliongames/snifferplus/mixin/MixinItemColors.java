package com.helliongames.snifferplus.mixin;

import com.helliongames.snifferplus.blocks.SnifferPlusBlocks;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ItemColors.class)
public class MixinItemColors {

    @Inject(method = "createDefault",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/color/item/ItemColors;register(Lnet/minecraft/client/color/item/ItemColor;[Lnet/minecraft/world/level/ItemLike;)V",
                    ordinal = 5),
            locals = LocalCapture.CAPTURE_FAILHARD)
    private static void snifferplus_registerBlockColorProviders(BlockColors blockColors, CallbackInfoReturnable<ItemColors> cir, ItemColors itemColors) {
        itemColors.register((itemStack, i) -> {
            BlockState blockState = ((BlockItem)itemStack.getItem()).getBlock().defaultBlockState();
            return blockColors.getColor(blockState, null, null, i);
        }, SnifferPlusBlocks.STONE_PINE_LEAVES);
    }
}
