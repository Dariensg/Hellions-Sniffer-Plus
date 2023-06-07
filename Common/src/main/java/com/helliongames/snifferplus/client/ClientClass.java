package com.helliongames.snifferplus.client;

import com.helliongames.snifferplus.platform.Services;

public class ClientClass {

    public static void init() {
        Services.CLIENT_HELPER.registerModelLayers();
        Services.CLIENT_HELPER.registerEntityRenderers();
        Services.CLIENT_HELPER.registerRenderTypes();
        Services.CLIENT_HELPER.registerPackets();
    }
}
