package com.helliongames.snifferplus;

import com.helliongames.snifferplus.platform.Services;
import com.helliongames.snifferplus.registration.SnifferPlusBlocks;
import com.helliongames.snifferplus.registration.SnifferPlusEntities;
import com.helliongames.snifferplus.registration.SnifferPlusItems;
import com.helliongames.snifferplus.registration.SnifferPlusMemoryModules;
import com.helliongames.snifferplus.registration.SnifferPlusTabs;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public class CommonClass {

    public static void init() {
        SnifferPlusBlocks.loadClass();
        SnifferPlusItems.loadClass();
        SnifferPlusTabs.loadClass();
        SnifferPlusEntities.loadClass();
        SnifferPlusMemoryModules.loadClass();

        Services.STRIPPABLE_BLOCK_HELPER.registerStrippableBlock(SnifferPlusBlocks.STONE_PINE_LOG, SnifferPlusBlocks.STRIPPED_STONE_PINE_LOG);
        Services.STRIPPABLE_BLOCK_HELPER.registerStrippableBlock(SnifferPlusBlocks.STONE_PINE_WOOD, SnifferPlusBlocks.STRIPPED_STONE_PINE_WOOD);
        Services.SPAWN_PLACEMENT_HELPER.register(EntityType.SNIFFER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
    }
}