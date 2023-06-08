package com.helliongames.snifferplus.network;

import com.helliongames.snifferplus.client.gui.screens.inventory.SnifferInventoryScreen;
import com.helliongames.snifferplus.world.inventory.SnifferInventoryMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ClientPacketHandler {

    public static void handleClientboundSnifferScreenOpenPacket(ClientboundSnifferScreenOpenPacket message, Supplier<NetworkEvent.Context> context) {
        LocalPlayer player = Minecraft.getInstance().player;
        ClientLevel level = player.clientLevel;
        Entity entity = level.getEntity(message.entityId);

        if (entity instanceof Sniffer sniffer) {
            SimpleContainer simpleContainer = new SimpleContainer(message.size);
            SnifferInventoryMenu snifferMenu = new SnifferInventoryMenu(message.containerId, player.getInventory(), simpleContainer, sniffer);
            player.containerMenu = snifferMenu;
            Minecraft.getInstance().setScreen(new SnifferInventoryScreen(snifferMenu, player.getInventory(), sniffer));
        }
    }
}
