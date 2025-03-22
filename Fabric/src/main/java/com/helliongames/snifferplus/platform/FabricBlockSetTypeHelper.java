package com.helliongames.snifferplus.platform;

import com.helliongames.snifferplus.Constants;
import com.helliongames.snifferplus.platform.services.IBlockSetTypeHelper;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class FabricBlockSetTypeHelper implements IBlockSetTypeHelper {

    @Override
    public BlockSetType registerBlockSetType(String identifier, boolean canOpenByHand, boolean canOpenByWindCharge, boolean canButtonBeActivatedByArrows, BlockSetType.PressurePlateSensitivity pressurePlateSensitivity, SoundType soundType, SoundEvent doorClose, SoundEvent doorOpen, SoundEvent trapdoorClose, SoundEvent trapdoorOpen, SoundEvent pressurePlateClickOff, SoundEvent pressurePlateClickOn, SoundEvent buttonClickOff, SoundEvent buttonClickOn) {
        return new BlockSetTypeBuilder().openableByHand(canOpenByHand).openableByWindCharge(canOpenByWindCharge).buttonActivatedByArrows(canButtonBeActivatedByArrows).pressurePlateActivationRule(pressurePlateSensitivity).soundGroup(soundType).doorCloseSound(doorClose).doorOpenSound(doorOpen).trapdoorCloseSound(trapdoorClose).trapdoorOpenSound(trapdoorOpen).pressurePlateClickOffSound(pressurePlateClickOff).pressurePlateClickOnSound(pressurePlateClickOn).buttonClickOffSound(buttonClickOff).buttonClickOnSound(buttonClickOn).register(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, identifier));
    }
}
