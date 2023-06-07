package com.helliongames.snifferplus.client;

import net.fabricmc.api.ClientModInitializer;

public class SnifferPlusClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientClass.init();
    }
}
