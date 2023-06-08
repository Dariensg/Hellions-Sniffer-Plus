package com.helliongames.snifferplus;

import com.helliongames.snifferplus.blocks.SnifferPlusBlocks;
import com.helliongames.snifferplus.entity.SnifferPlusEntities;
import com.helliongames.snifferplus.items.SnifferPlusItems;
import com.helliongames.snifferplus.items.SnifferPlusTabs;
import com.helliongames.snifferplus.platform.Services;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public class CommonClass {

    public static void init() {
        SnifferPlusBlocks.registerBlocks();
        SnifferPlusItems.registerItems();
        SnifferPlusTabs.register();
        SnifferPlusEntities.registerEntities();

        Services.STRIPPABLE_BLOCK_HELPER.registerStrippableBlock(SnifferPlusBlocks.STONE_PINE_LOG, SnifferPlusBlocks.STRIPPED_STONE_PINE_LOG);
        Services.STRIPPABLE_BLOCK_HELPER.registerStrippableBlock(SnifferPlusBlocks.STONE_PINE_WOOD, SnifferPlusBlocks.STRIPPED_STONE_PINE_WOOD);
        Services.SPAWN_PLACEMENT_HELPER.register(EntityType.SNIFFER, SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Mob::checkMobSpawnRules);
    }
}