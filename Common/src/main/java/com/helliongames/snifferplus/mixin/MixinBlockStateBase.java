package com.helliongames.snifferplus.mixin;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class MixinBlockStateBase {

    @Shadow public abstract boolean is(Block $$0);

    @Inject(method = "getLightEmission", at = @At("RETURN"), cancellable = true)
    private void snifferplus_makeTorchflowerGlow(CallbackInfoReturnable<Integer> cir) {
        if (this.is(Blocks.TORCHFLOWER)) {
            cir.setReturnValue(15);
        }
    }
}
