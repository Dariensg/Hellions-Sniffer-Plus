package com.helliongames.snifferplus.access;

import net.minecraft.world.Container;
import net.minecraft.world.entity.animal.sniffer.Sniffer;

public interface ServerPlayerAccess {

    void openSnifferInventory(Sniffer sniffer, Container container);

    int getContainerCounter();
}
