package com.helliongames.snifferplus.mixin;

import com.helliongames.snifferplus.Constants;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(OverworldBiomeBuilder.class)
public abstract class MixinOverworldBiomeBuilder {

    @Shadow @Final private Climate.Parameter FULL_RANGE;

    @Shadow protected abstract void addUndergroundBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> $$0, Climate.Parameter $$1, Climate.Parameter $$2, Climate.Parameter $$3, Climate.Parameter $$4, Climate.Parameter $$5, float $$6, ResourceKey<Biome> $$7);

    @Inject(method = "addUndergroundBiomes", at = @At("HEAD"))
    private void snifferplus_addTimelessGrotto(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, CallbackInfo ci) {
        this.addUndergroundBiome(consumer, Climate.Parameter.span(0.0F, 1.0F), Climate.Parameter.span(0.6F, 0.9F), this.FULL_RANGE, this.FULL_RANGE, this.FULL_RANGE, 0.0F, ResourceKey.create(Registries.BIOME, new ResourceLocation(Constants.MOD_ID, "timeless_grotto")));
    }
}
