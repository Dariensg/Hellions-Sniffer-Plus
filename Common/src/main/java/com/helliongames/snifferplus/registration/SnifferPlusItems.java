package com.helliongames.snifferplus.registration;

import com.helliongames.snifferplus.Constants;
import com.helliongames.snifferplus.items.StonePineBoatItem;
import com.helliongames.snifferplus.registration.util.RegistrationProvider;
import com.helliongames.snifferplus.registration.util.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.SignItem;

public class SnifferPlusItems {

    /**
     * The provider for items
     */
    public static final RegistrationProvider<Item> ITEMS = RegistrationProvider.get(Registries.ITEM, Constants.MOD_ID);

    public static final RegistryObject<Item> STONE_PINE_PLANKS = ITEMS.register("stone_pine_planks", () -> new BlockItem(SnifferPlusBlocks.STONE_PINE_PLANKS.get(), new Item.Properties()));
    public static final RegistryObject<Item> STONE_PINE_SAPLING = ITEMS.register("stone_pine_sapling", () -> new BlockItem(SnifferPlusBlocks.STONE_PINE_SAPLING.get(), new Item.Properties()));
    public static final RegistryObject<Item> STONE_PINE_LOG = ITEMS.register("stone_pine_log", () -> new BlockItem(SnifferPlusBlocks.STONE_PINE_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> STRIPPED_STONE_PINE_LOG = ITEMS.register("stripped_stone_pine_log", () -> new BlockItem(SnifferPlusBlocks.STRIPPED_STONE_PINE_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> STONE_PINE_WOOD = ITEMS.register("stone_pine_wood", () -> new BlockItem(SnifferPlusBlocks.STONE_PINE_WOOD.get(), new Item.Properties()));
    public static final RegistryObject<Item> STRIPPED_STONE_PINE_WOOD = ITEMS.register("stripped_stone_pine_wood", () -> new BlockItem(SnifferPlusBlocks.STRIPPED_STONE_PINE_WOOD.get(), new Item.Properties()));
    public static final RegistryObject<Item> STONE_PINE_LEAVES = ITEMS.register("stone_pine_leaves", () -> new BlockItem(SnifferPlusBlocks.STONE_PINE_LEAVES.get(), new Item.Properties()));
    public static final RegistryObject<Item> STONE_PINE_STAIRS = ITEMS.register("stone_pine_stairs", () -> new BlockItem(SnifferPlusBlocks.STONE_PINE_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> STONE_PINE_SLAB = ITEMS.register("stone_pine_slab", () -> new BlockItem(SnifferPlusBlocks.STONE_PINE_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> STONE_PINE_SIGN = ITEMS.register("stone_pine_sign", () -> new SignItem(new Item.Properties().stacksTo(16), SnifferPlusBlocks.STONE_PINE_SIGN.get(), SnifferPlusBlocks.STONE_PINE_WALL_SIGN.get()));
    public static final RegistryObject<Item> STONE_PINE_HANGING_SIGN = ITEMS.register("stone_pine_hanging_sign", () -> new HangingSignItem(SnifferPlusBlocks.STONE_PINE_HANGING_SIGN.get(), SnifferPlusBlocks.STONE_PINE_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> STONE_PINE_DOOR = ITEMS.register("stone_pine_door", () -> new DoubleHighBlockItem(SnifferPlusBlocks.STONE_PINE_DOOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> STONE_PINE_TRAPDOOR = ITEMS.register("stone_pine_trapdoor", () -> new BlockItem(SnifferPlusBlocks.STONE_PINE_TRAPDOOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> STONE_PINE_PRESSURE_PLATE = ITEMS.register("stone_pine_pressure_plate", () -> new BlockItem(SnifferPlusBlocks.STONE_PINE_PRESSURE_PLATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> STONE_PINE_BUTTON = ITEMS.register("stone_pine_button", () -> new BlockItem(SnifferPlusBlocks.STONE_PINE_BUTTON.get(), new Item.Properties()));
    public static final RegistryObject<Item> STONE_PINE_FENCE = ITEMS.register("stone_pine_fence", () -> new BlockItem(SnifferPlusBlocks.STONE_PINE_FENCE.get(), new Item.Properties()));
    public static final RegistryObject<Item> STONE_PINE_FENCE_GATE = ITEMS.register("stone_pine_fence_gate", () -> new BlockItem(SnifferPlusBlocks.STONE_PINE_FENCE_GATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> POTTED_STONE_PINE_SAPLING = ITEMS.register("potted_stone_pine_sapling", () -> new BlockItem(SnifferPlusBlocks.POTTED_STONE_PINE_SAPLING.get(), new Item.Properties()));
    public static final RegistryObject<Item> STONE_PINE_BOAT = ITEMS.register("stone_pine_boat", () -> new StonePineBoatItem(false, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> STONE_PINE_CHEST_BOAT = ITEMS.register("stone_pine_chest_boat", () -> new StonePineBoatItem(true, new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> IVY = ITEMS.register("ivy", () -> new ItemNameBlockItem(SnifferPlusBlocks.IVY_HEAD.get(), new Item.Properties()));
    public static final RegistryObject<Item> FIDDLEFERN = ITEMS.register("fiddlefern", () -> new BlockItem(SnifferPlusBlocks.FIDDLEFERN.get(), new Item.Properties()));
    public static final RegistryObject<Item> TALL_FIDDLEFERN = ITEMS.register("tall_fiddlefern", () -> new BlockItem(SnifferPlusBlocks.TALL_FIDDLEFERN.get(), new Item.Properties()));

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void loadClass() {}
}
