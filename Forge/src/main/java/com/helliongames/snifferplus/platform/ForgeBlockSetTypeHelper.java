package com.helliongames.snifferplus.platform;

import com.helliongames.snifferplus.platform.services.IBlockSetTypeHelper;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ForgeBlockSetTypeHelper implements IBlockSetTypeHelper {
    @Override
    public BlockSetType registerBlockSetType(String identifier, boolean canOpenByHand, SoundType soundType, SoundEvent doorClose, SoundEvent doorOpen, SoundEvent trapdoorClose, SoundEvent trapdoorOpen, SoundEvent pressurePlateClickOff, SoundEvent pressurePlateClickOn, SoundEvent buttonClickOff, SoundEvent buttonClickOn) {
        return BlockSetType.register(new BlockSetType(identifier, canOpenByHand, soundType, doorClose, doorOpen, trapdoorClose, trapdoorOpen, pressurePlateClickOff, pressurePlateClickOn, buttonClickOff, buttonClickOn));
    }
}
