package com.helliongames.snifferplus.network;

import com.helliongames.snifferplus.Constants;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.PacketListener;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record ClientboundSnifferScreenOpenPacket(int containerId, int size, int entityId) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<ClientboundSnifferScreenOpenPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "sniffer_screen_packet"));
    public static final StreamCodec<FriendlyByteBuf, ClientboundSnifferScreenOpenPacket> PACKET_CODEC = StreamCodec.of(ClientboundSnifferScreenOpenPacket::write, ClientboundSnifferScreenOpenPacket::new);

    public ClientboundSnifferScreenOpenPacket(FriendlyByteBuf buf) {
        this(buf.readUnsignedByte(), buf.readVarInt(), buf.readInt());
    }

    public static void write(FriendlyByteBuf buf, ClientboundSnifferScreenOpenPacket packet) {
        buf.writeByte(packet.containerId);
        buf.writeVarInt(packet.size);
        buf.writeInt(packet.entityId);
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
