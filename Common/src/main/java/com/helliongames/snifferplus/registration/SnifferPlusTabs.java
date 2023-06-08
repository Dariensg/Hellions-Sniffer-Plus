package com.helliongames.snifferplus.registration;

import com.helliongames.snifferplus.Constants;
import com.helliongames.snifferplus.registration.util.RegistrationProvider;
import com.helliongames.snifferplus.registration.util.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class SnifferPlusTabs {
    /**
     * The provider for creative tabs
     */
    public static final RegistrationProvider<CreativeModeTab> TABS = RegistrationProvider.get(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SNIFFER_PLUS_TAB = TABS.register("sniffer_plus", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).title(Component.translatable("itemGroup.sniffer_plus_tab")).icon(() -> new ItemStack(Items.SNIFFER_EGG)).displayItems((itemDisplayParameters, output) -> {
        output.accept(SnifferPlusItems.STONE_PINE_LOG.get());
        output.accept(SnifferPlusItems.STONE_PINE_WOOD.get());
        output.accept(SnifferPlusItems.STRIPPED_STONE_PINE_LOG.get());
        output.accept(SnifferPlusItems.STRIPPED_STONE_PINE_WOOD.get());
        output.accept(SnifferPlusItems.STONE_PINE_PLANKS.get());
        output.accept(SnifferPlusItems.STONE_PINE_STAIRS.get());
        output.accept(SnifferPlusItems.STONE_PINE_SLAB.get());
        output.accept(SnifferPlusItems.STONE_PINE_FENCE.get());
        output.accept(SnifferPlusItems.STONE_PINE_FENCE_GATE.get());
        output.accept(SnifferPlusItems.STONE_PINE_DOOR.get());
        output.accept(SnifferPlusItems.STONE_PINE_TRAPDOOR.get());
        output.accept(SnifferPlusItems.STONE_PINE_PRESSURE_PLATE.get());
        output.accept(SnifferPlusItems.STONE_PINE_BUTTON.get());
        output.accept(SnifferPlusItems.STONE_PINE_SIGN.get());
        output.accept(SnifferPlusItems.STONE_PINE_HANGING_SIGN.get());
        output.accept(SnifferPlusItems.STONE_PINE_BOAT.get());
        output.accept(SnifferPlusItems.STONE_PINE_CHEST_BOAT.get());
        output.accept(SnifferPlusItems.STONE_PINE_LEAVES.get());
        output.accept(SnifferPlusItems.STONE_PINE_SAPLING.get());
        output.accept(SnifferPlusItems.IVY.get());
        output.accept(SnifferPlusItems.FIDDLEFERN.get());
        output.accept(SnifferPlusItems.TALL_FIDDLEFERN.get());
    }).build());

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void loadClass() {}
}
