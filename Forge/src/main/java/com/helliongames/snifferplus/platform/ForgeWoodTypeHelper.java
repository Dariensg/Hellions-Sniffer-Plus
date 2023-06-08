package com.helliongames.snifferplus.platform;

import com.helliongames.snifferplus.platform.services.IWoodTypeHelper;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ForgeWoodTypeHelper implements IWoodTypeHelper {
    @Override
    public WoodType registerWoodType(String identifier, BlockSetType setType, SoundType soundType, SoundType hangingSignSoundType, SoundEvent fenceGateClose, SoundEvent fenceGateOpen) {
        return WoodType.register(new WoodType(identifier, setType, soundType, hangingSignSoundType, fenceGateClose, fenceGateOpen));
    }
}
