package com.helliongames.snifferplus.platform.services;

import com.helliongames.snifferplus.registration.util.RegistryObject;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public interface ICompostingChanceHelper {
    void registerCompostingChance(RegistryObject<? extends ItemLike> input, float chance);
}
