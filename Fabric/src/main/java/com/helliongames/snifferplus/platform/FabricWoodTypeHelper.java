package com.helliongames.snifferplus.platform;

import com.helliongames.snifferplus.Constants;
import com.helliongames.snifferplus.platform.services.IWoodTypeHelper;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class FabricWoodTypeHelper implements IWoodTypeHelper {

    @Override
    public WoodType registerWoodType(String identifier, BlockSetType setType) {
        return WoodTypeRegistry.register(new ResourceLocation(Constants.MOD_ID, identifier), setType);
    }

    @Override
    public WoodType registerWoodType(String identifier, BlockSetType setType, SoundType soundType, SoundType hangingSignSoundType, SoundEvent fenceGateClose, SoundEvent fenceGateOpen) {
        return WoodTypeRegistry.register(new ResourceLocation(Constants.MOD_ID, identifier), setType, soundType, hangingSignSoundType, fenceGateClose, fenceGateOpen);
    }
}
