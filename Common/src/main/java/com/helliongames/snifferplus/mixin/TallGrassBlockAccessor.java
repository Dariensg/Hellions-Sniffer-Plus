package com.helliongames.snifferplus.mixin;

import net.minecraft.world.level.block.TallGrassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TallGrassBlock.class)
public interface TallGrassBlockAccessor {
    @Invoker("<init>")
    static TallGrassBlock createTallGrassBlock(BlockBehaviour.Properties properties) {
        throw new UnsupportedOperationException();
    }
}
