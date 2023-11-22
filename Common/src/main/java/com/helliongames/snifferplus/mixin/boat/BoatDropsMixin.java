package com.helliongames.snifferplus.mixin.boat;

import com.helliongames.snifferplus.blocks.SnifferPlusBoatTypes;
import com.helliongames.snifferplus.registration.SnifferPlusItems;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Boat.class)
public class BoatDropsMixin {

    //CREDIT TO nyuppo/fabric-boat-example ON GITHUB

    @Inject(method = "getDropItem", at = @At("RETURN"), cancellable = true)
    public void snifferPlus$getModdedBoats(CallbackInfoReturnable<Item> info) {
        var boat = Boat.class.cast(this);
        if (boat.getVariant() == SnifferPlusBoatTypes.STONE_PINE) {
            info.setReturnValue(SnifferPlusItems.STONE_PINE_BOAT.get());
        }
    }

}