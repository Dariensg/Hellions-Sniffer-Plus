package com.helliongames.snifferplus.platform.services;

import com.helliongames.hellionsapi.registration.holders.BlockDataHolder;
import net.minecraft.world.level.block.Block;

public interface IStrippableBlockHelper {
    void registerStrippableBlock(BlockDataHolder<Block> input, BlockDataHolder<Block> stripped);
}
