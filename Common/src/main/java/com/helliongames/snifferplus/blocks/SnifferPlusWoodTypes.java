package com.helliongames.snifferplus.blocks;

import com.helliongames.snifferplus.platform.Services;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class SnifferPlusWoodTypes {
    public static final WoodType STONE_PINE = Services.WOOD_TYPE_HELPER.registerWoodType("stone_pine",
            SnifferPlusBlockSetTypes.STONE_PINE, SoundType.WOOD, SoundType.HANGING_SIGN, SoundEvents.FENCE_GATE_CLOSE, SoundEvents.FENCE_GATE_OPEN);
}
