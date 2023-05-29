package com.helliongames.snifferplus.mixin;

import com.helliongames.snifferplus.access.SnifferAccess;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class MixinLivingEntity {
    @Inject(method = "travelRidden", at = @At("HEAD"), cancellable = true)
    private void snifferplus_allowMovingWhenSniffing(Player player, Vec3 vec3, CallbackInfo ci) {
        if (((Object) this) instanceof Sniffer sniffer && ((SnifferAccess) sniffer).hasScentItem()) {
            ci.cancel();
        }
    }
}
