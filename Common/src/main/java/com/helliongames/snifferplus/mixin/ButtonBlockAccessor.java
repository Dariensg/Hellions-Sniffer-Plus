package com.helliongames.snifferplus.mixin;

import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ButtonBlock.class)
public interface ButtonBlockAccessor {

    @Invoker("<init>")
    static ButtonBlock createButtonBlock(BlockBehaviour.Properties properties, BlockSetType blockSetType, int ticksStayPressed, boolean arrowsCanPress) {
        throw new UnsupportedOperationException();
    }
}
