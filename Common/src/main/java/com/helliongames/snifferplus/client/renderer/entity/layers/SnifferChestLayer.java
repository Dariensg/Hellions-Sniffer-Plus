package com.helliongames.snifferplus.client.renderer.entity.layers;

import com.helliongames.snifferplus.access.SnifferAccess;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SnifferModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.level.block.Blocks;

public class SnifferChestLayer extends RenderLayer<Sniffer, SnifferModel<Sniffer>> {
    private final BlockRenderDispatcher blockRenderer;

    public SnifferChestLayer(RenderLayerParent<Sniffer, SnifferModel<Sniffer>> renderLayerParent, BlockRenderDispatcher blockRenderDispatcher) {
        super(renderLayerParent);
        this.blockRenderer = blockRenderDispatcher;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, Sniffer sniffer, float f, float g, float h, float j, float k, float l) {
        if (!((SnifferAccess) sniffer).hasChest()) {
            return;
        }
        poseStack.pushPose();
        poseStack.translate(0.5f, 0.25f, 1.25f);
        poseStack.scale(-1.0f, -0.5f, 0.5f);
        this.blockRenderer.renderSingleBlock(Blocks.CHEST.defaultBlockState(), poseStack, multiBufferSource, i, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
    }
}
