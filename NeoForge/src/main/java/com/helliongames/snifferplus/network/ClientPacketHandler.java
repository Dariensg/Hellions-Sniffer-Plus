package com.helliongames.snifferplus.network;

import com.helliongames.snifferplus.client.gui.screens.inventory.SnifferInventoryScreen;
import com.helliongames.snifferplus.world.inventory.SnifferInventoryMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.handling.MainThreadPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class ClientPacketHandler {

    public static void handleClientboundSnifferScreenOpenPacket(final ClientboundSnifferScreenOpenPacket packet, final IPayloadContext context) {
        LocalPlayer player = Minecraft.getInstance().player;
        ClientLevel level = player.clientLevel;
        Entity entity = level.getEntity(packet.entityId());

        if (entity instanceof Sniffer sniffer) {
            SimpleContainer simpleContainer = new SimpleContainer(packet.size());
            SnifferInventoryMenu snifferMenu = new SnifferInventoryMenu(packet.containerId(), player.getInventory(), simpleContainer, sniffer);
            player.containerMenu = snifferMenu;
            Minecraft.getInstance().setScreen(new SnifferInventoryScreen(snifferMenu, player.getInventory(), sniffer));
        }
    }

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(
                ClientboundSnifferScreenOpenPacket.TYPE,
                ClientboundSnifferScreenOpenPacket.STREAM_CODEC,
                new MainThreadPayloadHandler<>(ClientPacketHandler::handleClientboundSnifferScreenOpenPacket)
        );
    }
}
