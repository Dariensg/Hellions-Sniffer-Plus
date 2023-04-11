package com.helliongames.snifferplus.network;

import com.helliongames.snifferplus.Constants;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public record ClientboundSnifferScreenOpenPacket(int containerId, int size, int entityId) implements FabricPacket {
    public static final PacketType<ClientboundSnifferScreenOpenPacket> TYPE = PacketType.create(new ResourceLocation(Constants.MOD_ID, "sniffer_screen_packet"), ClientboundSnifferScreenOpenPacket::new);

    public ClientboundSnifferScreenOpenPacket(FriendlyByteBuf buf) {
        this(buf.readUnsignedByte(), buf.readVarInt(), buf.readInt());
    }

    public void write(FriendlyByteBuf buf) {
        buf.writeByte(this.containerId);
        buf.writeVarInt(this.size);
        buf.writeInt(this.entityId);
    }

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }
}
