package com.helliongames.snifferplus.mixin;

import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(StairBlock.class)
public interface StairBlockAccessor {

    @Invoker("<init>")
    static StairBlock createStairBlock(BlockState state,  BlockBehaviour.Properties properties) {
        throw new UnsupportedOperationException();
    }
}
