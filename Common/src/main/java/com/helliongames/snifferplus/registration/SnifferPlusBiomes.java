package com.helliongames.snifferplus.registration;

import com.helliongames.snifferplus.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;

public class SnifferPlusBiomes {
    public static final ResourceKey<Biome> TIMELESS_GROTTO = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "timeless_grotto"));
}
