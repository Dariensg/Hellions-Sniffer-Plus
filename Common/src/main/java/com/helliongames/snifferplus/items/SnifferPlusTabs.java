package com.helliongames.snifferplus.items;

import com.helliongames.snifferplus.Constants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class SnifferPlusTabs {
    public static final ResourceKey<CreativeModeTab> SNIFFER_PLUS = SnifferPlusTabs.createKey("sniffer_plus");

    public static void register() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, SNIFFER_PLUS, CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).title(Component.translatable("itemGroup.sniffer_plus_tab")).icon(() -> new ItemStack(Items.SNIFFER_EGG)).displayItems((itemDisplayParameters, output) -> {
            output.accept(SnifferPlusItems.STONE_PINE_LOG);
            output.accept(SnifferPlusItems.STONE_PINE_WOOD);
            output.accept(SnifferPlusItems.STRIPPED_STONE_PINE_LOG);
            output.accept(SnifferPlusItems.STRIPPED_STONE_PINE_WOOD);
            output.accept(SnifferPlusItems.STONE_PINE_PLANKS);
            output.accept(SnifferPlusItems.STONE_PINE_STAIRS);
            output.accept(SnifferPlusItems.STONE_PINE_SLAB);
            output.accept(SnifferPlusItems.STONE_PINE_FENCE);
            output.accept(SnifferPlusItems.STONE_PINE_FENCE_GATE);
            output.accept(SnifferPlusItems.STONE_PINE_DOOR);
            output.accept(SnifferPlusItems.STONE_PINE_TRAPDOOR);
            output.accept(SnifferPlusItems.STONE_PINE_PRESSURE_PLATE);
            output.accept(SnifferPlusItems.STONE_PINE_BUTTON);
            output.accept(SnifferPlusItems.STONE_PINE_SIGN);
            output.accept(SnifferPlusItems.STONE_PINE_HANGING_SIGN);
            output.accept(SnifferPlusItems.STONE_PINE_BOAT);
            output.accept(SnifferPlusItems.STONE_PINE_CHEST_BOAT);
            output.accept(SnifferPlusItems.STONE_PINE_LEAVES);
            output.accept(SnifferPlusItems.STONE_PINE_SAPLING);
        }).build());
    }

    private static ResourceKey<CreativeModeTab> createKey(String string) {
        return ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(Constants.MOD_ID, string));
    }
}
