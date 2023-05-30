package com.helliongames.snifferplus.access;

import net.minecraft.world.Container;

public interface SnifferAccess {

    boolean hasChest();

    boolean hasScentItem();

    boolean hasInventoryChanged(Container snifferContainer);

    int getInventoryColumns();
}
