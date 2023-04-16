package com.helliongames.snifferplus.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Saddleable;

public class SnifferSaddleLayer<T extends Entity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private final ResourceLocation textureLocation;
    private final M model;

    public SnifferSaddleLayer(RenderLayerParent<T, M> renderLayerParent, M entityModel, ResourceLocation resourceLocation) {
        super(renderLayerParent);
        this.model = entityModel;
        this.textureLocation = resourceLocation;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, T entity, float f, float g, float h, float j, float k, float l) {
        if (!((Saddleable)entity).isSaddled()) {
            return;
        }
        this.getParentModel().copyPropertiesTo(this.model);
        this.model.prepareMobModel(entity, f, g, h);
        this.model.setupAnim(entity, f, g, j, k, l);
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(this.textureLocation));
        this.model.renderToBuffer(poseStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static LayerDefinition createInflatedSnifferLayer(CubeDeformation deformation) {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition root = meshDefinition.getRoot().addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 0.0F));
        PartDefinition bone = root.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(62, 68).addBox(-12.5F, -14.0F, -20.0F, 25.0F, 29.0F, 40.0F, deformation).texOffs(62, 0).addBox(-12.5F, -14.0F, -20.0F, 25.0F, 24.0F, 40.0F, new CubeDeformation(0.5F)).texOffs(87, 68).addBox(-12.5F, 12.0F, -20.0F, 25.0F, 0.0F, 40.0F, deformation), PartPose.offset(0.0F, 0.0F, 0.0F));
        bone.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(32, 87).addBox(-3.5F, -1.0F, -4.0F, 7.0F, 10.0F, 8.0F, deformation), PartPose.offset(-7.5F, 10.0F, -15.0F));
        bone.addOrReplaceChild("right_mid_leg", CubeListBuilder.create().texOffs(32, 105).addBox(-3.5F, -1.0F, -4.0F, 7.0F, 10.0F, 8.0F, deformation), PartPose.offset(-7.5F, 10.0F, 0.0F));
        bone.addOrReplaceChild("right_hind_leg", CubeListBuilder.create().texOffs(32, 123).addBox(-3.5F, -1.0F, -4.0F, 7.0F, 10.0F, 8.0F, deformation), PartPose.offset(-7.5F, 10.0F, 15.0F));
        bone.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(0, 87).addBox(-3.5F, -1.0F, -4.0F, 7.0F, 10.0F, 8.0F, deformation), PartPose.offset(7.5F, 10.0F, -15.0F));
        bone.addOrReplaceChild("left_mid_leg", CubeListBuilder.create().texOffs(0, 105).addBox(-3.5F, -1.0F, -4.0F, 7.0F, 10.0F, 8.0F, deformation), PartPose.offset(7.5F, 10.0F, 0.0F));
        bone.addOrReplaceChild("left_hind_leg", CubeListBuilder.create().texOffs(0, 123).addBox(-3.5F, -1.0F, -4.0F, 7.0F, 10.0F, 8.0F, deformation), PartPose.offset(7.5F, 10.0F, 15.0F));
        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(8, 15).addBox(-6.5F, -7.5F, -11.5F, 13.0F, 18.0F, 11.0F, deformation).texOffs(8, 4).addBox(-6.5F, 7.5F, -11.5F, 13.0F, 0.0F, 11.0F, deformation), PartPose.offset(0.0F, 6.5F, -19.48F));
        head.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(2, 0).addBox(0.0F, 0.0F, -3.0F, 1.0F, 19.0F, 7.0F, deformation), PartPose.offset(6.51F, -7.5F, -4.51F));
        head.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(48, 0).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 19.0F, 7.0F, deformation), PartPose.offset(-6.51F, -7.5F, -4.51F));
        head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(10, 45).addBox(-6.5F, -2.0F, -9.0F, 13.0F, 2.0F, 9.0F, deformation), PartPose.offset(0.0F, -4.5F, -11.5F));
        head.addOrReplaceChild("lower_beak", CubeListBuilder.create().texOffs(10, 57).addBox(-6.5F, -7.0F, -8.0F, 13.0F, 12.0F, 9.0F, deformation), PartPose.offset(0.0F, 2.5F, -12.5F));
        return LayerDefinition.create(meshDefinition, 192, 192);
    }
}
