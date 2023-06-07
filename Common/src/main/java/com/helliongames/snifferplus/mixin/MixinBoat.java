package com.helliongames.snifferplus.mixin;

import com.helliongames.snifferplus.blocks.SnifferPlusBlocks;
import com.helliongames.snifferplus.entity.StonePineBoat;
import com.helliongames.snifferplus.entity.StonePineChestBoat;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Boat.class)
public class MixinBoat {

    @Redirect(method = "checkFallDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/vehicle/Boat$Type;getPlanks()Lnet/minecraft/world/level/block/Block;"))
    private Block snifferplus_dropCustomPlanks(Boat.Type instance) {
        if (((Object) this) instanceof StonePineBoat || ((Object) this) instanceof StonePineChestBoat) {
            return SnifferPlusBlocks.STONE_PINE_PLANKS;
        }

        return instance.getPlanks();
    }
}
