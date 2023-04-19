package com.helliongames.snifferplus.mixin;

import com.helliongames.snifferplus.Constants;
import com.helliongames.snifferplus.client.model.SnifferPlusModelLayers;
import com.helliongames.snifferplus.client.renderer.entity.layers.SnifferChestLayer;
import com.helliongames.snifferplus.client.renderer.entity.layers.SnifferSaddleLayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.SnifferModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.SnifferRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SnifferRenderer.class)
public abstract class MixinSnifferRenderer extends MobRenderer<Sniffer, SnifferModel<Sniffer>> {

    public MixinSnifferRenderer(EntityRendererProvider.Context $$0, SnifferModel<Sniffer> $$1, float $$2) {
        super($$0, $$1, $$2);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void snifferplus_addSaddleLayer(EntityRendererProvider.Context context, CallbackInfo ci) {
        this.addLayer(new SnifferSaddleLayer<>((SnifferRenderer) (Object) this, new SnifferModel<>(context.bakeLayer(SnifferPlusModelLayers.SNIFFER_SADDLE)), new ResourceLocation(Constants.MOD_ID, "textures/entity/sniffer/sniffer_saddle.png")));
        this.addLayer(new SnifferChestLayer((SnifferRenderer) (Object) this, Minecraft.getInstance().getBlockRenderer()));
    }
}
