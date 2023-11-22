package com.helliongames.snifferplus.platform;

import com.helliongames.snifferplus.platform.services.ICompostingChanceHelper;
import com.helliongames.snifferplus.registration.util.RegistryObject;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.world.level.ItemLike;

public class FabricCompostingChanceHelper implements ICompostingChanceHelper {
    @Override
    public void registerCompostingChance(RegistryObject<? extends ItemLike> input, float chance) {
        CompostingChanceRegistry.INSTANCE.add(input.get(), chance);
    }
}
