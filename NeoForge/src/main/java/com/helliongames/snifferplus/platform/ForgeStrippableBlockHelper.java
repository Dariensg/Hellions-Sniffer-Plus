package com.helliongames.snifferplus.platform;

import com.helliongames.hellionsapi.registration.holders.BlockDataHolder;
import com.helliongames.snifferplus.platform.services.IStrippableBlockHelper;
import com.helliongames.snifferplus.registration.util.RegistryObject;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class ForgeStrippableBlockHelper implements IStrippableBlockHelper {
    public static final Map<BlockDataHolder<Block>, BlockDataHolder<Block>> strippableBlockMap = new HashMap<>();

    @Override
    public void registerStrippableBlock(BlockDataHolder<Block> input, BlockDataHolder<Block> stripped) {
        strippableBlockMap.put(input, stripped);
    }
}
