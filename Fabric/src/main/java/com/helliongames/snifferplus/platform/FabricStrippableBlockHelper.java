package com.helliongames.snifferplus.platform;

import com.helliongames.snifferplus.platform.services.IStrippableBlockHelper;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.world.level.block.Block;

public class FabricStrippableBlockHelper implements IStrippableBlockHelper {

    @Override
    public void registerStrippableBlock(Block input, Block stripped) {
        StrippableBlockRegistry.register(input, stripped);
    }
}
