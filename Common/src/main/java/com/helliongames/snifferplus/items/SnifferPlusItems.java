package com.helliongames.snifferplus.items;

import com.helliongames.snifferplus.Constants;
import com.helliongames.snifferplus.blocks.SnifferPlusBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;

import java.util.HashMap;
import java.util.Map;

public class SnifferPlusItems {
    private static final Map<ResourceLocation, Item> items = new HashMap<>();

    public static final Item STONE_PINE_PLANKS = registerItem("stone_pine_planks", new BlockItem(SnifferPlusBlocks.STONE_PINE_PLANKS, new Item.Properties()));
    public static final Item STONE_PINE_SAPLING = registerItem("stone_pine_sapling", new BlockItem(SnifferPlusBlocks.STONE_PINE_SAPLING, new Item.Properties()));
    public static final Item STONE_PINE_LOG = registerItem("stone_pine_log", new BlockItem(SnifferPlusBlocks.STONE_PINE_LOG, new Item.Properties()));
    public static final Item STRIPPED_STONE_PINE_LOG = registerItem("stripped_stone_pine_log", new BlockItem(SnifferPlusBlocks.STRIPPED_STONE_PINE_LOG, new Item.Properties()));
    public static final Item STONE_PINE_WOOD = registerItem("stone_pine_wood", new BlockItem(SnifferPlusBlocks.STONE_PINE_WOOD, new Item.Properties()));
    public static final Item STRIPPED_STONE_PINE_WOOD = registerItem("stripped_stone_pine_wood", new BlockItem(SnifferPlusBlocks.STRIPPED_STONE_PINE_WOOD, new Item.Properties()));
    public static final Item STONE_PINE_LEAVES = registerItem("stone_pine_leaves", new BlockItem(SnifferPlusBlocks.STONE_PINE_LEAVES, new Item.Properties()));
    public static final Item STONE_PINE_STAIRS = registerItem("stone_pine_stairs", new BlockItem(SnifferPlusBlocks.STONE_PINE_STAIRS, new Item.Properties()));
    public static final Item STONE_PINE_SLAB = registerItem("stone_pine_slab", new BlockItem(SnifferPlusBlocks.STONE_PINE_SLAB, new Item.Properties()));
    public static final Item STONE_PINE_SIGN = registerItem("stone_pine_sign", new SignItem(new Item.Properties().stacksTo(16), SnifferPlusBlocks.STONE_PINE_SIGN, SnifferPlusBlocks.STONE_PINE_WALL_SIGN));
    public static final Item STONE_PINE_HANGING_SIGN = registerItem("stone_pine_hanging_sign", new HangingSignItem(SnifferPlusBlocks.STONE_PINE_HANGING_SIGN, SnifferPlusBlocks.STONE_PINE_WALL_HANGING_SIGN, new Item.Properties().stacksTo(16)));
    public static final Item STONE_PINE_DOOR = registerItem("stone_pine_door", new DoubleHighBlockItem(SnifferPlusBlocks.STONE_PINE_DOOR, new Item.Properties()));
    public static final Item STONE_PINE_TRAPDOOR = registerItem("stone_pine_trapdoor", new BlockItem(SnifferPlusBlocks.STONE_PINE_TRAPDOOR, new Item.Properties()));
    public static final Item STONE_PINE_PRESSURE_PLATE = registerItem("stone_pine_pressure_plate", new BlockItem(SnifferPlusBlocks.STONE_PINE_PRESSURE_PLATE, new Item.Properties()));
    public static final Item STONE_PINE_BUTTON = registerItem("stone_pine_button", new BlockItem(SnifferPlusBlocks.STONE_PINE_BUTTON, new Item.Properties()));
    public static final Item STONE_PINE_FENCE = registerItem("stone_pine_fence", new BlockItem(SnifferPlusBlocks.STONE_PINE_FENCE, new Item.Properties()));
    public static final Item STONE_PINE_FENCE_GATE = registerItem("stone_pine_fence_gate", new BlockItem(SnifferPlusBlocks.STONE_PINE_FENCE_GATE, new Item.Properties()));
    public static final Item POTTED_STONE_PINE_SAPLING = registerItem("potted_stone_pine_sapling", new BlockItem(SnifferPlusBlocks.POTTED_STONE_PINE_SAPLING, new Item.Properties()));
    public static final Item STONE_PINE_BOAT = registerItem("stone_pine_boat", new StonePineBoatItem(false, new Item.Properties().stacksTo(1)));
    public static final Item STONE_PINE_CHEST_BOAT = registerItem("stone_pine_chest_boat", new StonePineBoatItem(true, new Item.Properties().stacksTo(1)));

    private static Item registerItem(String identifier, Item item) {
        items.put(new ResourceLocation(Constants.MOD_ID, identifier), item);
        return item;
    }

    public static void register() {
        for (Map.Entry<ResourceLocation, Item> entry : items.entrySet()) {
            Registry.register(BuiltInRegistries.ITEM, entry.getKey(), entry.getValue());
        }
    }
}
