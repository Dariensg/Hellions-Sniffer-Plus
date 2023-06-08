package com.helliongames.snifferplus.platform;

import com.helliongames.snifferplus.client.model.SnifferPlusModelLayers;
import com.helliongames.snifferplus.client.renderer.entity.StonePineBoatRenderer;
import com.helliongames.snifferplus.client.renderer.entity.layers.SnifferSaddleLayer;
import com.helliongames.snifferplus.network.SnifferPlusNetworkHandler;
import com.helliongames.snifferplus.platform.services.IClientHelper;
import com.helliongames.snifferplus.registration.SnifferPlusEntities;
import net.minecraft.client.model.SnifferModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.client.event.EntityRenderersEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ForgeClientHelper implements IClientHelper {
    private static final Map<ModelLayerLocation, Supplier<LayerDefinition>> modelLayers = new HashMap<>();
    private static final Map<EntityType, EntityRendererProvider> entityRenderers = new HashMap<>();

    @Override
    public void registerEntityRenderers() {
    }

    @Override
    public void registerModelLayers() {
    }

    @Override
    public void registerRenderTypes() {
    }

    @Override
    public void registerPackets() {
        SnifferPlusNetworkHandler.register();
    }

    public static void registerModelLayerListener(EntityRenderersEvent.RegisterLayerDefinitions event) {
        modelLayers.put(SnifferPlusModelLayers.SNIFFER_SADDLE, () -> SnifferSaddleLayer.createInflatedSnifferLayer(new CubeDeformation(0.5F)));
        modelLayers.put(SnifferPlusModelLayers.SNIFFER_CHEST, SnifferModel::createBodyLayer);

        for (Map.Entry<ModelLayerLocation, Supplier<LayerDefinition>> entry : modelLayers.entrySet()) {
            event.registerLayerDefinition(entry.getKey(), entry.getValue());
        }
    }

    public static void registerEntityRendererListener(EntityRenderersEvent.RegisterRenderers event) {
        entityRenderers.put(SnifferPlusEntities.STONE_PINE_BOAT.get(), (context) -> new StonePineBoatRenderer(context, false));
        entityRenderers.put(SnifferPlusEntities.STONE_PINE_CHEST_BOAT.get(), (context) -> new StonePineBoatRenderer(context, true));

        for (Map.Entry<EntityType, EntityRendererProvider> entry : entityRenderers.entrySet()) {
            event.registerEntityRenderer(entry.getKey(), entry.getValue());
        }
    }
}
