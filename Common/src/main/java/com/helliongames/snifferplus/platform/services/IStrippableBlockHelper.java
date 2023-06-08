package com.helliongames.snifferplus.platform.services;

import com.helliongames.snifferplus.registration.util.RegistryObject;
import net.minecraft.world.level.block.Block;

public interface IStrippableBlockHelper {
    void registerStrippableBlock(RegistryObject<Block> input, RegistryObject<Block> stripped);
}
