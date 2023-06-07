package com.helliongames.snifferplus.platform.services;

import net.minecraft.world.level.block.Block;

public interface IStrippableBlockHelper {
    void registerStrippableBlock(Block input, Block stripped);
}
