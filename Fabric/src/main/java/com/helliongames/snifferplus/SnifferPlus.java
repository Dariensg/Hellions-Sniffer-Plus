package com.helliongames.snifferplus;

import com.helliongames.snifferplus.network.ClientboundSnifferScreenOpenPacket;
import com.helliongames.snifferplus.registration.SnifferPlusItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.impl.content.registry.CompostingChanceRegistryImpl;
import net.minecraft.world.level.block.ComposterBlock;

public class SnifferPlus implements ModInitializer {

    @Override
    public void onInitialize() {
        CommonClass.init();

        PayloadTypeRegistry.playS2C().register(ClientboundSnifferScreenOpenPacket.TYPE, ClientboundSnifferScreenOpenPacket.PACKET_CODEC);

        CompostingChanceRegistryImpl.INSTANCE.add(SnifferPlusItems.STONE_PINE_LEAVES.get(), 0.3F);
        CompostingChanceRegistryImpl.INSTANCE.add(SnifferPlusItems.STONE_PINE_SAPLING.get(), 0.3F);
        CompostingChanceRegistryImpl.INSTANCE.add(SnifferPlusItems.FIDDLEFERN.get(), 0.3F);
        CompostingChanceRegistryImpl.INSTANCE.add(SnifferPlusItems.TALL_FIDDLEFERN.get(), 0.5F);
        CompostingChanceRegistryImpl.INSTANCE.add(SnifferPlusItems.IVY.get(), 0.5F);
    }
}
