package com.helliongames.snifferplus.platform;

import com.helliongames.snifferplus.platform.services.IStrippableBlockHelper;
import com.helliongames.snifferplus.registration.util.RegistryObject;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;
import java.util.Map;

public class ForgeStrippableBlockHelper implements IStrippableBlockHelper {
    public static final Map<RegistryObject<Block>, RegistryObject<Block>> strippableBlockMap = new HashMap<>();

    @Override
    public void registerStrippableBlock(RegistryObject<Block> input, RegistryObject<Block> stripped) {
        strippableBlockMap.put(input, stripped);
    }
}
