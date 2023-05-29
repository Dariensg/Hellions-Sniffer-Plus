package com.helliongames.snifferplus.access;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;

public interface SnifferAccess {

    boolean hasChest();

    boolean hasScentItem();

    BlockPos getScentPos();

    boolean hasInventoryChanged(Container snifferContainer);

    int getInventoryColumns();
}
