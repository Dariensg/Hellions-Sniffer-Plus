package com.helliongames.snifferplus.platform;

import com.helliongames.snifferplus.platform.services.ICompostingChanceHelper;
import com.helliongames.snifferplus.registration.util.RegistryObject;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;

public class ForgeCompostingChanceHelper implements ICompostingChanceHelper {
    @Override
    public void registerCompostingChance(RegistryObject<? extends ItemLike> input, float chance) {
        ComposterBlock.COMPOSTABLES.put(input.get(), chance);
    }
}
