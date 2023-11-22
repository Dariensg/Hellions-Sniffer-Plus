package com.helliongames.snifferplus.platform;

import com.helliongames.snifferplus.Constants;
import com.helliongames.snifferplus.platform.services.*;

import java.util.ServiceLoader;

public class Services {

    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

    public static final IPacketHelper PACKET_HELPER = load(IPacketHelper.class);

    public static final ISpawnPlacementHelper SPAWN_PLACEMENT_HELPER = load(ISpawnPlacementHelper.class);

    public static final IBlockSetTypeHelper BLOCK_SET_TYPE_HELPER = load(IBlockSetTypeHelper.class);

    public static final IWoodTypeHelper WOOD_TYPE_HELPER = load(IWoodTypeHelper.class);

    public static final IStrippableBlockHelper STRIPPABLE_BLOCK_HELPER = load(IStrippableBlockHelper.class);

    public static final ICompostingChanceHelper COMPOSTING_CHANCE_HELPER = load(ICompostingChanceHelper.class);

    public static <T> T load(Class<T> clazz) {

        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        Constants.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}