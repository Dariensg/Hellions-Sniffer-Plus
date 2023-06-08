package com.helliongames.snifferplus.entity.schedule;

import com.helliongames.snifferplus.access.SnifferAccess;
import com.helliongames.snifferplus.registration.SnifferPlusMemoryModules;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.PositionTracker;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.Map;
import java.util.Optional;

public class SnifferOutpostBehavior extends Behavior<Sniffer> {

    public SnifferOutpostBehavior() {
        super(Map.of(), 600, Integer.MAX_VALUE);
    }

    @Override
    protected boolean checkExtraStartConditions(ServerLevel serverLevel, Sniffer sniffer) {
        return ((SnifferAccess) sniffer).hasScentItem() && sniffer.isVehicle();
    }

    @Override
    protected boolean canStillUse(ServerLevel serverLevel, Sniffer sniffer, long l) {
        if (!((SnifferAccess) sniffer).hasScentItem() || !sniffer.isVehicle()) {
            sniffer.transitionTo(Sniffer.State.IDLING);
            return false;
        }

        sniffer.transitionTo(Sniffer.State.SEARCHING);

        Optional<BlockPos> walkTargetPos = sniffer.getBrain().getMemory(MemoryModuleType.WALK_TARGET).map(WalkTarget::getTarget).map(PositionTracker::currentBlockPosition);
        Optional<BlockPos> outpostPos = sniffer.getBrain().getMemory(SnifferPlusMemoryModules.OUTPOST_LOCATION.get());

        if (walkTargetPos.isEmpty() || outpostPos.isEmpty()) {
            return false;
        }
        return walkTargetPos.get().equals(outpostPos.get());
    }

    @Override
    protected void start(ServerLevel serverLevel, Sniffer sniffer, long l) {
        sniffer.transitionTo(Sniffer.State.SEARCHING);

        Registry<Structure> structureRegistry = serverLevel.registryAccess().registryOrThrow(Registries.STRUCTURE);
        HolderSet<Structure> structure = HolderSet.direct(structureRegistry.getHolderOrThrow(BuiltinStructures.PILLAGER_OUTPOST));
        Pair<BlockPos, Holder<Structure>> posStructurePair = serverLevel.getChunkSource().getGenerator().findNearestMapStructure(serverLevel, structure, sniffer.blockPosition(), 100, false);

        if (posStructurePair != null) {
            sniffer.getBrain().setMemory(SnifferPlusMemoryModules.OUTPOST_LOCATION.get(), posStructurePair.getFirst());
            sniffer.getBrain().setMemory(MemoryModuleType.WALK_TARGET, new WalkTarget(posStructurePair.getFirst(), 1.5F, 20));
        }
    }

    @Override
    protected void stop(ServerLevel serverLevel, Sniffer sniffer, long l) {
        sniffer.getBrain().eraseMemory(SnifferPlusMemoryModules.OUTPOST_LOCATION.get());
        sniffer.getBrain().eraseMemory(MemoryModuleType.WALK_TARGET);
    }
}
