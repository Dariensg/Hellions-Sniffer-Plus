package com.helliongames.snifferplus.client;

import com.helliongames.snifferplus.blocks.SnifferPlusBlocks;
import com.helliongames.snifferplus.client.gui.screens.inventory.SnifferInventoryScreen;
import com.helliongames.snifferplus.client.model.SnifferPlusModelLayers;
import com.helliongames.snifferplus.client.renderer.entity.layers.SnifferSaddleLayer;
import com.helliongames.snifferplus.network.ClientboundSnifferScreenOpenPacket;
import com.helliongames.snifferplus.world.inventory.SnifferInventoryMenu;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.SnifferModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.sniffer.Sniffer;

public class SnifferPlusClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(SnifferPlusModelLayers.SNIFFER_SADDLE, () -> SnifferSaddleLayer.createInflatedSnifferLayer(new CubeDeformation(0.5F)));
        EntityModelLayerRegistry.registerModelLayer(SnifferPlusModelLayers.SNIFFER_CHEST, SnifferModel::createBodyLayer);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(), SnifferPlusBlocks.POTTED_STONE_PINE_SAPLING, SnifferPlusBlocks.STONE_PINE_SAPLING, SnifferPlusBlocks.STONE_PINE_DOOR, SnifferPlusBlocks.STONE_PINE_TRAPDOOR);

        ClientPlayNetworking.registerGlobalReceiver(ClientboundSnifferScreenOpenPacket.TYPE.getId(), (client, handler, buf, response) -> {
            int containerId = buf.readUnsignedByte();
            int size = buf.readVarInt();
            int entityId = buf.readInt();

            client.execute(() -> {
                Entity entity = handler.getLevel().getEntity(entityId);
                if (entity instanceof Sniffer sniffer) {
                    SimpleContainer simpleContainer = new SimpleContainer(size);
                    SnifferInventoryMenu snifferMenu = new SnifferInventoryMenu(containerId, client.player.getInventory(), simpleContainer, sniffer);
                    client.player.containerMenu = snifferMenu;
                    Minecraft.getInstance().setScreen(new SnifferInventoryScreen(snifferMenu, client.player.getInventory(), sniffer));
                }
            });
        });
    }
}
