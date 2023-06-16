package com.helliongames.snifferplus.client.renderer.entity.layers;

import com.helliongames.snifferplus.access.SnifferAccess;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.SnifferModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.level.block.Blocks;

public class SnifferChestLayer extends RenderLayer<Sniffer, SnifferModel<Sniffer>> {
    private final BlockRenderDispatcher blockRenderer;
    private final SnifferModel<Sniffer> model;

    public SnifferChestLayer(RenderLayerParent<Sniffer, SnifferModel<Sniffer>> renderLayerParent, BlockRenderDispatcher blockRenderDispatcher) {
        super(renderLayerParent);
        this.model = renderLayerParent.getModel();
        this.blockRenderer = blockRenderDispatcher;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, Sniffer sniffer, float f, float g, float h, float j, float k, float l) {
        if (!((SnifferAccess) sniffer).hasChest()) {
            return;
        }
        poseStack.pushPose();
        ModelPart body = model.root().getChild("bone").getChild("body");
        poseStack.translate(body.x / 20, body.y / 20, body.z / 20);
        poseStack.translate(0.5f, -0.6f, 0.75f);
        poseStack.scale(-1.0f, -0.5f, 0.5f);
        poseStack.mulPose(Axis.XP.rotationDegrees(-body.xRot * Mth.RAD_TO_DEG));
        poseStack.mulPose(Axis.YP.rotationDegrees(-body.yRot * Mth.RAD_TO_DEG));
        poseStack.mulPose(Axis.ZP.rotationDegrees(body.zRot * Mth.RAD_TO_DEG));
        this.blockRenderer.renderSingleBlock(Blocks.CHEST.defaultBlockState(), poseStack, multiBufferSource, i, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
    }
}
