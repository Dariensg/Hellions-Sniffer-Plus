package com.helliongames.snifferplus;

import com.helliongames.snifferplus.client.ClientClass;
import com.helliongames.snifferplus.network.SnifferPlusNetworkHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class SnifferPlus {
    
    public SnifferPlus() {
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::commonSetup);

        CommonClass.init();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ClientClass.init();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(SnifferPlusNetworkHandler::register);
    }
}