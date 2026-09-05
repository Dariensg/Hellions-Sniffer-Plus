package com.helliongames.snifferplus;

import com.helliongames.hellionsapi.HellionsAPICommon;
import com.helliongames.snifferplus.platform.Services;
import com.helliongames.snifferplus.registration.*;

public class CommonClass {

    public static void init() {
        SnifferPlusBlocks.loadClass();
        SnifferPlusItems.loadClass();
        SnifferPlusTabs.loadClass();
        SnifferPlusEntities.loadClass();
        SnifferPlusMemoryModules.loadClass();

        HellionsAPICommon.init(Constants.MOD_ID);

        Services.STRIPPABLE_BLOCK_HELPER.registerStrippableBlock(SnifferPlusBlocks.STONE_PINE_LOG, SnifferPlusBlocks.STRIPPED_STONE_PINE_LOG);
        Services.STRIPPABLE_BLOCK_HELPER.registerStrippableBlock(SnifferPlusBlocks.STONE_PINE_WOOD, SnifferPlusBlocks.STRIPPED_STONE_PINE_WOOD);
    }
}