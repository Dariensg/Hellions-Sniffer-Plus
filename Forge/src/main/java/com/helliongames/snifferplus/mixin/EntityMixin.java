package com.helliongames.snifferplus.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.extensions.IForgeEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Entity.class)
public abstract class EntityMixin implements IForgeEntity {

    @Shadow public abstract float maxUpStep();

    @Override
    public float getStepHeight() {
        float vanillaStep = this.maxUpStep();
        if (((Object) this) instanceof LivingEntity living)
        {
            AttributeInstance stepHeightAttribute = living.getAttribute(ForgeMod.STEP_HEIGHT_ADDITION.get());
            if (stepHeightAttribute != null)
            {
                return (float) Math.max(0, vanillaStep + stepHeightAttribute.getValue());
            }
        }
        return vanillaStep;
    }
}
