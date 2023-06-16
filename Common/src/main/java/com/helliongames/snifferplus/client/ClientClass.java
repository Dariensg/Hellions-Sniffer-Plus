package com.helliongames.snifferplus.client;

import com.helliongames.snifferplus.platform.ClientServices;

public class ClientClass {

    public static void init() {
        ClientServices.CLIENT_HELPER.registerModelLayers();
        ClientServices.CLIENT_HELPER.registerEntityRenderers();
        ClientServices.CLIENT_HELPER.registerRenderTypes();
        ClientServices.CLIENT_HELPER.registerPackets();
    }
}
