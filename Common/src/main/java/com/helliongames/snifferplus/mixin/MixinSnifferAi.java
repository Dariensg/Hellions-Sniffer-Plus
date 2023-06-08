package com.helliongames.snifferplus.mixin;

import com.google.common.collect.ImmutableList;
import com.helliongames.snifferplus.access.SnifferAccess;
import com.helliongames.snifferplus.entity.schedule.SnifferOutpostBehavior;
import com.helliongames.snifferplus.registration.SnifferPlusMemoryModules;
import com.mojang.datafixers.util.Pair;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.AnimalPanic;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.behavior.CountDownCooldownTicks;
import net.minecraft.world.entity.ai.behavior.MoveToTargetSink;
import net.minecraft.world.entity.ai.behavior.Swim;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.entity.animal.sniffer.SnifferAi;
import net.minecraft.world.entity.schedule.Activity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Set;

@Mixin(SnifferAi.class)
public abstract class MixinSnifferAi {

    @Inject(method = "initCoreActivity", at = @At("HEAD"), cancellable = true)
    private static void snifferplus_addCoreActivities(Brain<Sniffer> brain, CallbackInfo ci) {
        brain.addActivity(Activity.CORE, 0, ImmutableList.of(new SnifferOutpostBehavior(), new Swim(0.8f){
            @Override
            protected boolean checkExtraStartConditions(ServerLevel level, Mob mob) {
                Sniffer sniffer = (Sniffer) mob;

                return super.checkExtraStartConditions(level, mob) && !(((SnifferAccess) sniffer).hasScentItem() && sniffer.isVehicle());
            }
        }, new AnimalPanic(2.0f){

            @Override
            protected void start(ServerLevel serverLevel, PathfinderMob pathfinderMob, long l) {
                Sniffer sniffer = (Sniffer) pathfinderMob;

                sniffer.getBrain().eraseMemory(MemoryModuleType.SNIFFER_DIGGING);
                sniffer.getBrain().eraseMemory(MemoryModuleType.SNIFFER_SNIFFING_TARGET);
                sniffer.transitionTo(Sniffer.State.IDLING);

                super.start(serverLevel, pathfinderMob, l);
            }
        }, new MoveToTargetSink(10000, 15000), new CountDownCooldownTicks(MemoryModuleType.TEMPTATION_COOLDOWN_TICKS)));
        ci.cancel();
    }

    @Redirect(method = "initSniffingActivity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/Brain;addActivityWithConditions(Lnet/minecraft/world/entity/schedule/Activity;Lcom/google/common/collect/ImmutableList;Ljava/util/Set;)V"))
    private static void snifferplus_initSniffingActivity(Brain brain, Activity activity, ImmutableList<? extends Pair<Integer, ? extends BehaviorControl<?>>> immutableList, Set<Pair<MemoryModuleType<?>, MemoryStatus>> set) {
        brain.addActivityWithConditions(activity, immutableList, Set.of(Pair.of(MemoryModuleType.IS_PANICKING, MemoryStatus.VALUE_ABSENT), Pair.of(MemoryModuleType.SNIFFER_SNIFFING_TARGET, MemoryStatus.VALUE_PRESENT), Pair.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_PRESENT), Pair.of(SnifferPlusMemoryModules.OUTPOST_LOCATION.get(), MemoryStatus.VALUE_ABSENT)));
    }

    @Redirect(method = "initDigActivity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/Brain;addActivityWithConditions(Lnet/minecraft/world/entity/schedule/Activity;Lcom/google/common/collect/ImmutableList;Ljava/util/Set;)V"))
    private static void snifferplus_initDigActivity(Brain brain, Activity activity, ImmutableList<? extends Pair<Integer, ? extends BehaviorControl<?>>> immutableList, Set<Pair<MemoryModuleType<?>, MemoryStatus>> set) {
        brain.addActivityWithConditions(activity, immutableList, Set.of(Pair.of(MemoryModuleType.IS_PANICKING, MemoryStatus.VALUE_ABSENT), Pair.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT), Pair.of(MemoryModuleType.SNIFFER_DIGGING, MemoryStatus.VALUE_PRESENT), Pair.of(SnifferPlusMemoryModules.OUTPOST_LOCATION.get(), MemoryStatus.VALUE_ABSENT)));
    }

    @Redirect(method = "initIdleActivity", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/Brain;addActivityWithConditions(Lnet/minecraft/world/entity/schedule/Activity;Lcom/google/common/collect/ImmutableList;Ljava/util/Set;)V"))
    private static void snifferplus_initIdleActivity(Brain brain, Activity activity, ImmutableList<? extends Pair<Integer, ? extends BehaviorControl<?>>> immutableList, Set<Pair<MemoryModuleType<?>, MemoryStatus>> set) {
        brain.addActivityWithConditions(activity, immutableList, Set.of(Pair.of(MemoryModuleType.SNIFFER_DIGGING, MemoryStatus.VALUE_ABSENT), Pair.of(SnifferPlusMemoryModules.OUTPOST_LOCATION.get(), MemoryStatus.VALUE_ABSENT)));
    }
}
