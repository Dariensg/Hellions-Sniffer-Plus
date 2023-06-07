package com.helliongames.snifferplus.client.renderer.entity;

import com.helliongames.snifferplus.Constants;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.WaterPatchModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.vehicle.Boat;
import org.joml.Quaternionf;

public class StonePineBoatRenderer extends EntityRenderer<Boat> {
    private static final ResourceLocation BOAT_TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/entity/boat/stone_pine.png");
    private static final ResourceLocation CHEST_BOAT_TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/entity/chest_boat/stone_pine.png");

    private final boolean hasChest;
    private ListModel<Boat> boatModel;

    public StonePineBoatRenderer(EntityRendererProvider.Context context, boolean hasChest) {
        super(context);
        this.shadowRadius = 0.8f;
        this.hasChest = hasChest;
        this.boatModel = this.createBoatModel(context, Boat.Type.OAK, hasChest);
    }

    private ListModel<Boat> createBoatModel(EntityRendererProvider.Context context, Boat.Type type, boolean bl) {
        ModelLayerLocation modelLayerLocation = bl ? ModelLayers.createChestBoatModelName(type) : ModelLayers.createBoatModelName(type);
        ModelPart modelPart = context.bakeLayer(modelLayerLocation);
        return bl ? new ChestBoatModel(modelPart) : new BoatModel(modelPart);
    }


    @Override
    public void render(Boat boat, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.pushPose();
        poseStack.translate(0.0f, 0.375f, 0.0f);
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0f - f));
        float h = (float)boat.getHurtTime() - g;
        float j = boat.getDamage() - g;
        if (j < 0.0f) {
            j = 0.0f;
        }
        if (h > 0.0f) {
            poseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(h) * h * j / 10.0f * (float)boat.getHurtDir()));
        }
        if (!Mth.equal(boat.getBubbleAngle(g), 0.0f)) {
            poseStack.mulPose(new Quaternionf().setAngleAxis(boat.getBubbleAngle(g) * ((float)Math.PI / 180), 1.0f, 0.0f, 1.0f));
        }
        poseStack.scale(-1.0f, -1.0f, 1.0f);
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0f));
        this.boatModel.setupAnim(boat, g, 0.0f, -0.1f, 0.0f, 0.0f);
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(this.boatModel.renderType(this.hasChest ? CHEST_BOAT_TEXTURE : BOAT_TEXTURE));
        this.boatModel.renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
        if (!boat.isUnderWater()) {
            VertexConsumer vertexConsumer2 = multiBufferSource.getBuffer(RenderType.waterMask());
            if (this.boatModel instanceof WaterPatchModel waterPatchModel) {
                waterPatchModel.waterPatch().render(poseStack, vertexConsumer2, i, OverlayTexture.NO_OVERLAY);
            }
        }
        poseStack.popPose();
        super.render(boat, f, g, poseStack, multiBufferSource, i);
    }

    @Override
    public ResourceLocation getTextureLocation(Boat boat) {
        return this.hasChest ? CHEST_BOAT_TEXTURE : BOAT_TEXTURE;
    }
}
