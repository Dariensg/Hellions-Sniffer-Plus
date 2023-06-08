package com.helliongames.snifferplus.mixin;

import com.helliongames.snifferplus.registration.SnifferPlusBlocks;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.world.level.FoliageColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BlockColors.class)
public class MixinBlockColors {

    @Inject(method = "createDefault",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/color/block/BlockColors;register(Lnet/minecraft/client/color/block/BlockColor;[Lnet/minecraft/world/level/block/Block;)V",
                    ordinal = 3),
            locals = LocalCapture.CAPTURE_FAILHARD)
    private static void snifferplus_registerBlockColorProviders(CallbackInfoReturnable<BlockColors> cir, BlockColors blockColors) {
        blockColors.register((blockState, blockAndTintGetter, blockPos, i) -> FoliageColor.getEvergreenColor(), SnifferPlusBlocks.STONE_PINE_LEAVES.get());
    }
}
