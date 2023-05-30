package com.helliongames.snifferplus.platform.services;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public interface ISpawnPlacementHelper {

    /**
     * Adds Entity SpawnPlacement to the registry.
     */
     <T extends Mob> void register(EntityType<T> type, SpawnPlacements.Type placementType, Heightmap.Types heightmapType, SpawnPlacements.SpawnPredicate<T> predicate);
}
