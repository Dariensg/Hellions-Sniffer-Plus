package com.helliongames.snifferplus.network;

import com.helliongames.snifferplus.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;

public class SnifferPlusNetworkHandler {
    private static final SimpleChannel INSTANCE = ChannelBuilder.named(
            new ResourceLocation(Constants.MOD_ID, "main"))
            .serverAcceptedVersions((status, version) -> true)
            .clientAcceptedVersions((status, version) -> true)
            .networkProtocolVersion(1)
            .simpleChannel();

    private static int index;

    public static synchronized void register() {
        INSTANCE.messageBuilder(ClientboundSnifferScreenOpenPacket.class, index++)
                .encoder(ClientboundSnifferScreenOpenPacket::encode)
                .decoder(ClientboundSnifferScreenOpenPacket::decode)
                .consumerMainThread(ClientboundSnifferScreenOpenPacket::handle).add();
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(message, PacketDistributor.PLAYER.with(player));
    }

    public static <MSG> void sendToAll(MSG message) {
        INSTANCE.send(message, PacketDistributor.ALL.noArg());
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.send(message, PacketDistributor.SERVER.noArg());
    }

    public static <MSG> void sendToClient(MSG message, ServerPlayer serverPlayer) {
        INSTANCE.send(message, PacketDistributor.PLAYER.with(serverPlayer));
    }
}
