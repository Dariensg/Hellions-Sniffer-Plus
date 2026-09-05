package com.helliongames.snifferplus.events;

import com.helliongames.snifferplus.Constants;
import com.helliongames.snifferplus.registration.SnifferPlusBlocks;
import net.minecraft.world.level.FoliageColor;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NeoForgeClientEvents {
    @SubscribeEvent
    public static void registerBlockColorHandlers(RegisterColorHandlersEvent.Block event) {
        event.register((state, level, pos, tintIndex) -> FoliageColor.getEvergreenColor(), SnifferPlusBlocks.STONE_PINE_LEAVES.get());
    }
}
