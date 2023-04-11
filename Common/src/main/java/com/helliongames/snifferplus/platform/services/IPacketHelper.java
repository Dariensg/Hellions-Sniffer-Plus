package com.helliongames.snifferplus.platform.services;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.animal.sniffer.Sniffer;

public interface IPacketHelper {
    /**
     * Sends packet to Client to open a Sniffer screen.
     */
    void sendOpenSnifferScreenPacket(ServerPlayer player, Sniffer sniffer, Container container);
}
