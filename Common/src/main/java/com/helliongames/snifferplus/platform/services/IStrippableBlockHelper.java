package com.helliongames.snifferplus.platform.services;

import com.helliongames.hellionsapi.registration.holders.BlockDataHolder;
import com.helliongames.snifferplus.registration.util.RegistryObject;
import net.minecraft.world.level.block.Block;

public interface IStrippableBlockHelper {
    void registerStrippableBlock(BlockDataHolder<Block> input, BlockDataHolder<Block> stripped);
}
