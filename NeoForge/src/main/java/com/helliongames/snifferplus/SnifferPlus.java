package com.helliongames.snifferplus;

import com.helliongames.snifferplus.client.ClientClass;
import com.helliongames.snifferplus.network.ClientPacketHandler;
import com.helliongames.snifferplus.registration.SnifferPlusItems;
import net.minecraft.world.level.block.ComposterBlock;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(Constants.MOD_ID)
public class SnifferPlus {
    
    public SnifferPlus(IEventBus modBus) {
        modBus.addListener(this::commonSetup);

        if (FMLEnvironment.dist.isClient()) {
            modBus.addListener(this::clientSetup);
            modBus.addListener(ClientPacketHandler::register);
        }

        CommonClass.init();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ClientClass.init();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        try {
            ComposterBlock.COMPOSTABLES.put(SnifferPlusItems.STONE_PINE_LEAVES.get(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(SnifferPlusItems.STONE_PINE_SAPLING.get(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(SnifferPlusItems.FIDDLEFERN.get(), 0.3F);
            ComposterBlock.COMPOSTABLES.put(SnifferPlusItems.TALL_FIDDLEFERN.get(), 0.5F);
            ComposterBlock.COMPOSTABLES.put(SnifferPlusItems.IVY.get(), 0.5F);
        } catch (NullPointerException e) {
            Constants.LOG.error("Blocks not in Registry. Most likely, another mod has failed during startup.");
        }
    }
}