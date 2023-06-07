package com.helliongames.snifferplus.world.gen.features;

import com.helliongames.snifferplus.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class SnifferPlusFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> STONE_PINE_TREE = registerKey("stone_pine_tree");

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String identifier) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Constants.MOD_ID, identifier));
    }
}
