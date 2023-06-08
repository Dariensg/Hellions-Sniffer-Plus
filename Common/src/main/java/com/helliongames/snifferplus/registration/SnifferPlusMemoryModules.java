package com.helliongames.snifferplus.registration;

import com.helliongames.snifferplus.Constants;
import com.helliongames.snifferplus.registration.util.RegistrationProvider;
import com.helliongames.snifferplus.registration.util.RegistryObject;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;

import java.util.Optional;

public class SnifferPlusMemoryModules {

    /**
     * The provider for memory modules
     */
    public static final RegistrationProvider<MemoryModuleType<?>> MEMORY_MODULES = RegistrationProvider.get(Registries.MEMORY_MODULE_TYPE, Constants.MOD_ID);

    public static final RegistryObject<MemoryModuleType<BlockPos>> OUTPOST_LOCATION = MEMORY_MODULES.register("outpost_location", () -> new MemoryModuleType<>(Optional.empty()));

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void loadClass() {}
}
