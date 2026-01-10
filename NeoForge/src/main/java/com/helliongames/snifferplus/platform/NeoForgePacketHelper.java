package com.helliongames.snifferplus.platform;

import com.helliongames.snifferplus.access.ServerPlayerAccess;
import com.helliongames.snifferplus.network.ClientboundSnifferScreenOpenPacket;
import com.helliongames.snifferplus.platform.services.IPacketHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.neoforged.neoforge.network.PacketDistributor;

public class NeoForgePacketHelper implements IPacketHelper {
    @Override
    public void sendOpenSnifferScreenPacket(ServerPlayer player, Sniffer sniffer, Container container) {
        PacketDistributor.sendToPlayer(player, new ClientboundSnifferScreenOpenPacket(((ServerPlayerAccess) player).getContainerCounter(), container.getContainerSize(), sniffer.getId()));
    }
}
