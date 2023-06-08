package com.helliongames.snifferplus.mixin;

import com.helliongames.snifferplus.registration.SnifferPlusBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TallGrassBlock.class)
public class MixinTallGrassBlock {
    @Inject(method = "performBonemeal", at = @At("HEAD"), cancellable = true)
    private void snifferplus_performFiddlefernBonemeal(ServerLevel level, RandomSource rand, BlockPos pos, BlockState state, CallbackInfo ci) {
        if (state.is(SnifferPlusBlocks.FIDDLEFERN.get())) {
            DoublePlantBlock.placeAt(level, SnifferPlusBlocks.TALL_FIDDLEFERN.get().defaultBlockState(), pos, 2);
            ci.cancel();
        }
    }
}
