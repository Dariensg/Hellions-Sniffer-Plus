package com.helliongames.snifferplus.entity.schedule;

import com.helliongames.snifferplus.Constants;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;

import java.util.Optional;

public class SnifferPlusMemoryModules {

    public static final MemoryModuleType<BlockPos> OUTPOST_LOCATION = register("outpost_location");

    private static <U> MemoryModuleType<U> register(String name) {
        return Registry.register(BuiltInRegistries.MEMORY_MODULE_TYPE, new ResourceLocation(Constants.MOD_ID, name), new MemoryModuleType<U>(Optional.empty()));
    }
}
