package com.helliongames.snifferplus.platform;

import com.helliongames.hellionsapi.registration.holders.BlockDataHolder;
import com.helliongames.snifferplus.platform.services.IStrippableBlockHelper;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.world.level.block.Block;

public class FabricStrippableBlockHelper implements IStrippableBlockHelper {

    @Override
    public void registerStrippableBlock(BlockDataHolder<Block> input, BlockDataHolder<Block> stripped) {
        StrippableBlockRegistry.register(input.get(), stripped.get());
    }
}
