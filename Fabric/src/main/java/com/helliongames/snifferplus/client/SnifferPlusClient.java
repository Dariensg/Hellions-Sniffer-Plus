package com.helliongames.snifferplus.client;

import com.helliongames.snifferplus.registration.SnifferPlusBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.world.level.FoliageColor;

public class SnifferPlusClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientClass.init();

        ColorProviderRegistry.BLOCK.register((blockState, blockAndTintGetter, blockPos, i) -> FoliageColor.getEvergreenColor(), SnifferPlusBlocks.STONE_PINE_LEAVES.get());
    }
}
