package com.helliongames.snifferplus.network;

import com.helliongames.snifferplus.Constants;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record ClientboundSnifferScreenOpenPacket(int containerId, int size, int entityId) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<ClientboundSnifferScreenOpenPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "sniffer_open_screen"));

    public static final StreamCodec<ByteBuf, ClientboundSnifferScreenOpenPacket> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            ClientboundSnifferScreenOpenPacket::containerId,
            ByteBufCodecs.VAR_INT,
            ClientboundSnifferScreenOpenPacket::size,
            ByteBufCodecs.VAR_INT,
            ClientboundSnifferScreenOpenPacket::entityId,
            ClientboundSnifferScreenOpenPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
