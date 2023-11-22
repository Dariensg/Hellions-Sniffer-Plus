package com.helliongames.snifferplus.platform;

import com.helliongames.snifferplus.client.model.SnifferPlusModelLayers;
import com.helliongames.snifferplus.client.renderer.entity.layers.SnifferSaddleLayer;
import com.helliongames.snifferplus.platform.services.IClientHelper;
import net.minecraft.client.model.SnifferModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = "snifferplus", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
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
    }

    @SubscribeEvent
    public static void registerModelLayerListener(EntityRenderersEvent.RegisterLayerDefinitions event) {
        modelLayers.put(SnifferPlusModelLayers.SNIFFER_SADDLE, () -> SnifferSaddleLayer.createInflatedSnifferLayer(new CubeDeformation(0.5F)));
        modelLayers.put(SnifferPlusModelLayers.SNIFFER_CHEST, SnifferModel::createBodyLayer);

        for (Map.Entry<ModelLayerLocation, Supplier<LayerDefinition>> entry : modelLayers.entrySet()) {
            event.registerLayerDefinition(entry.getKey(), entry.getValue());
        }
    }

    @SubscribeEvent
    public static void registerEntityRendererListener(EntityRenderersEvent.RegisterRenderers event) {
        for (Map.Entry<EntityType, EntityRendererProvider> entry : entityRenderers.entrySet()) {
            event.registerEntityRenderer(entry.getKey(), entry.getValue());
        }
    }
}
