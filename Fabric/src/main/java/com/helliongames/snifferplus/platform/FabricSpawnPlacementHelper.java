package com.helliongames.snifferplus.platform;

import com.helliongames.snifferplus.platform.services.ISpawnPlacementHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public class FabricSpawnPlacementHelper implements ISpawnPlacementHelper {

    @Override
    public <T extends Mob> void register(EntityType<T> type, SpawnPlacements.Type placementType, Heightmap.Types heightmapType, SpawnPlacements.SpawnPredicate<T> predicate) {
        SpawnPlacements.register(type, placementType, heightmapType, predicate);
    }
}
