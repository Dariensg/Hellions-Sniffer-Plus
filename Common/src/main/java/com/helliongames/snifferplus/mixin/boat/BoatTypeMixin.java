package com.helliongames.snifferplus.mixin.boat;

import com.helliongames.snifferplus.blocks.SnifferPlusBoatTypes;
import com.helliongames.snifferplus.registration.SnifferPlusBlocks;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(Boat.Type.class)
public class BoatTypeMixin {

    //CREDIT TO nyuppo/fabric-boat-example ON GITHUB

    @SuppressWarnings("ShadowTarget")
    @Final
    @Shadow
    @Mutable
    private static Boat.Type[] $VALUES;

    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static Boat.Type snifferPlus$newType(String internalName, int internalId, Block baseBlock, String name) {
        throw new AssertionError("Mixin injection failed - Sniffer+ BoatTypeMixin.");
    }

    @Inject(method = "<clinit>", at = @At(value = "FIELD",
        opcode = 179, // 179 is the opcode for PUTSTATIC
        target = "Lnet/minecraft/world/entity/vehicle/Boat$Type;$VALUES:[Lnet/minecraft/world/entity/vehicle/Boat$Type;",
        shift = At.Shift.AFTER))
    private static void stonePlus$addCustomBoatType(CallbackInfo info) {
        var types = new ArrayList<>(Arrays.asList($VALUES));
        var last = types.get(types.size() - 1);

        var stonePine = snifferPlus$newType("SNIFFERPLUS_STONE_PINE", last.ordinal() + 1, SnifferPlusBlocks.STONE_PINE_PLANKS.get(), "snifferplus_stone_pine");
        SnifferPlusBoatTypes.STONE_PINE = stonePine;
        types.add(stonePine);

        $VALUES = types.toArray(new Boat.Type[0]);
    }

}