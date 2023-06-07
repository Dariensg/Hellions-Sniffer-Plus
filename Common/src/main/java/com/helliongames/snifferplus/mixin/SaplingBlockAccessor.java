package com.helliongames.snifferplus.mixin;

import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SaplingBlock.class)
public interface SaplingBlockAccessor {

    @Invoker("<init>")
    static SaplingBlock createSaplingBlock(AbstractTreeGrower treeGrower, BlockBehaviour.Properties properties) {
        throw new UnsupportedOperationException();
    }
}
