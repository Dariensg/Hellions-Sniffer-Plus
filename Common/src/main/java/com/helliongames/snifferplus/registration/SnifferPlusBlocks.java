package com.helliongames.snifferplus.registration;

import com.helliongames.hellionsapi.registration.holders.BlockDataHolder;
import com.helliongames.hellionsapi.registration.registries.HellionsAPIBlockRegistry;
import com.helliongames.snifferplus.Constants;
import com.helliongames.snifferplus.blocks.IvyBodyBlock;
import com.helliongames.snifferplus.blocks.IvyHeadBlock;
import com.helliongames.snifferplus.blocks.SnifferPlusBlockSetTypes;
import com.helliongames.snifferplus.blocks.SnifferPlusWoodTypes;
import com.helliongames.snifferplus.mixin.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class SnifferPlusBlocks {

    /**
     * The provider for blocks
     */
    public static final HellionsAPIBlockRegistry BLOCKS = new HellionsAPIBlockRegistry(Constants.MOD_ID);

    public static final BlockDataHolder<Block> STONE_PINE_PLANKS = BLOCKS.register("stone_pine_planks", new BlockDataHolder<>(() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0f, 3.0f).sound(SoundType.WOOD).ignitedByLava())));
    public static final BlockDataHolder<Block> STONE_PINE_SAPLING = BLOCKS.register("stone_pine_sapling", new BlockDataHolder<>(() -> SaplingBlockAccessor.createSaplingBlock(SnifferPlusTreeGrowers.STONE_PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING))));
    public static final BlockDataHolder<Block> STONE_PINE_LOG = BLOCKS.register("stone_pine_log", new BlockDataHolder<>(() -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0f).sound(SoundType.WOOD).ignitedByLava())));
    public static final BlockDataHolder<Block> STRIPPED_STONE_PINE_LOG = BLOCKS.register("stripped_stone_pine_log", new BlockDataHolder<>(() -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(STONE_PINE_LOG.get()))));
    public static final BlockDataHolder<Block> STONE_PINE_WOOD = BLOCKS.register("stone_pine_wood", new BlockDataHolder<>(() -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD))));
    public static final BlockDataHolder<Block> STRIPPED_STONE_PINE_WOOD = BLOCKS.register("stripped_stone_pine_wood", new BlockDataHolder<>(() -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD))));
    public static final BlockDataHolder<Block> STONE_PINE_LEAVES = BLOCKS.register("stone_pine_leaves", new BlockDataHolder<>(() -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES))));

    public static final BlockDataHolder<Block> STONE_PINE_STAIRS = BLOCKS.register("stone_pine_stairs", new BlockDataHolder<>(() -> StairBlockAccessor.createStairBlock(STONE_PINE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(STONE_PINE_PLANKS.get()))));
    public static final BlockDataHolder<Block> STONE_PINE_SLAB = BLOCKS.register("stone_pine_slab", new BlockDataHolder<>(() -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB))));
    public static final BlockDataHolder<Block> STONE_PINE_SIGN = BLOCKS.register("stone_pine_sign", new BlockDataHolder<>(() -> new StandingSignBlock(SnifferPlusWoodTypes.STONE_PINE, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0f).ignitedByLava())));
    public static final BlockDataHolder<Block> STONE_PINE_WALL_SIGN = BLOCKS.register("stone_pine_wall_sign", new BlockDataHolder<>(() -> new WallSignBlock(SnifferPlusWoodTypes.STONE_PINE, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0f).dropsLike(STONE_PINE_SIGN.get()).ignitedByLava())));
    public static final BlockDataHolder<Block> STONE_PINE_HANGING_SIGN = BLOCKS.register("stone_pine_hanging_sign", new BlockDataHolder<>(() -> new CeilingHangingSignBlock(SnifferPlusWoodTypes.STONE_PINE, BlockBehaviour.Properties.of().mapColor(STONE_PINE_LOG.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0f).ignitedByLava())));
    public static final BlockDataHolder<Block> STONE_PINE_WALL_HANGING_SIGN = BLOCKS.register("stone_pine_wall_hanging_sign", new BlockDataHolder<>(() -> new WallHangingSignBlock(SnifferPlusWoodTypes.STONE_PINE, BlockBehaviour.Properties.of().mapColor(STONE_PINE_LOG.get().defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0f).ignitedByLava().dropsLike(STONE_PINE_HANGING_SIGN.get()))));
    public static final BlockDataHolder<Block> STONE_PINE_DOOR = BLOCKS.register("stone_pine_door", new BlockDataHolder<>(() -> DoorBlockAccessor.createDoorBlock(SnifferPlusBlockSetTypes.STONE_PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR))));
    public static final BlockDataHolder<Block> STONE_PINE_TRAPDOOR = BLOCKS.register("stone_pine_trapdoor", new BlockDataHolder<>(() -> TrapDoorBlockAccessor.createTrapDoorBlock(SnifferPlusBlockSetTypes.STONE_PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR))));
    public static final BlockDataHolder<Block> STONE_PINE_PRESSURE_PLATE = BLOCKS.register("stone_pine_pressure_plate", new BlockDataHolder<>(() -> PressurePlateBlockAccessor.createPressurePlateBlock(SnifferPlusBlockSetTypes.STONE_PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE))));
    public static final BlockDataHolder<Block> STONE_PINE_BUTTON = BLOCKS.register("stone_pine_button", new BlockDataHolder<>(() -> ButtonBlockAccessor.createButtonBlock(SnifferPlusBlockSetTypes.STONE_PINE, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON))));
    public static final BlockDataHolder<Block> STONE_PINE_FENCE = BLOCKS.register("stone_pine_fence", new BlockDataHolder<>(() -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE))));
    public static final BlockDataHolder<Block> STONE_PINE_FENCE_GATE = BLOCKS.register("stone_pine_fence_gate", new BlockDataHolder<>(() -> new FenceGateBlock(SnifferPlusWoodTypes.STONE_PINE, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE))));

    public static final BlockDataHolder<Block> POTTED_STONE_PINE_SAPLING = BLOCKS.register("potted_stone_pine_sapling", new BlockDataHolder<>(() -> new FlowerPotBlock(STONE_PINE_SAPLING.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_OAK_SAPLING))));

    public static final BlockDataHolder<IvyBodyBlock> IVY_BODY = BLOCKS.register("ivy_body", new BlockDataHolder<>(() -> new IvyBodyBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().noCollission().instabreak().sound(SoundType.CAVE_VINES).pushReaction(PushReaction.DESTROY))));
    public static final BlockDataHolder<IvyHeadBlock> IVY_HEAD = BLOCKS.register("ivy_head", new BlockDataHolder<>(() -> new IvyHeadBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).randomTicks().noCollission().instabreak().sound(SoundType.CAVE_VINES).pushReaction(PushReaction.DESTROY))));
    public static final BlockDataHolder<Block> FIDDLEFERN = BLOCKS.register("fiddlefern", new BlockDataHolder<>(() -> TallGrassBlockAccessor.createTallGrassBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XYZ).ignitedByLava().pushReaction(PushReaction.DESTROY))));
    public static final BlockDataHolder<Block> TALL_FIDDLEFERN = BLOCKS.register("tall_fiddlefern", new BlockDataHolder<>(() -> new DoublePlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().noCollission().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.DESTROY))));

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void loadClass() {}
}
