package com.helliongames.snifferplus.mixin;

import com.helliongames.snifferplus.access.ServerPlayerAccess;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ServerPlayer.class)
public class MixinServerPlayer implements ServerPlayerAccess {

    @Shadow private int containerCounter;

    @Override
    public int getContainerCounter() {
        return this.containerCounter;
    }
}
