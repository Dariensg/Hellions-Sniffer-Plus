package com.helliongames.snifferplus.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.fml.DistExecutor;

public class ClientboundSnifferScreenOpenPacket {
    protected final int containerId;
    protected final int size;
    protected final int entityId;


    public ClientboundSnifferScreenOpenPacket(int containerId, int size, int entityId) {
        this.containerId = containerId;
        this.size = size;
        this.entityId = entityId;
    }

    public static void encode(ClientboundSnifferScreenOpenPacket message, FriendlyByteBuf buffer) {
        buffer.writeByte(message.containerId);
        buffer.writeVarInt(message.size);
        buffer.writeInt(message.entityId);
    }

    public static ClientboundSnifferScreenOpenPacket decode(FriendlyByteBuf buffer) {
        return new ClientboundSnifferScreenOpenPacket(buffer.readUnsignedByte(), buffer.readVarInt(), buffer.readInt());
    }

    public static void handle(ClientboundSnifferScreenOpenPacket message, CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientPacketHandler.handleClientboundSnifferScreenOpenPacket(message, context));
        });

        context.setPacketHandled(true);
    }
}
