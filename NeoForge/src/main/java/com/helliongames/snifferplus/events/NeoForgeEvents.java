package com.helliongames.snifferplus.events;

import com.helliongames.hellionsapi.registration.holders.BlockDataHolder;
import com.helliongames.snifferplus.platform.NeoForgeStrippableBlockHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber
public class NeoForgeEvents {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockState targetBlockState = level.getBlockState(event.getPos());
        Block target = targetBlockState.getBlock();
        BlockPos pos = event.getPos();

        if (!event.getEntity().getItemInHand(event.getHand()).is(ItemTags.AXES)) return;

        for (BlockDataHolder<Block> blockHolder : NeoForgeStrippableBlockHelper.strippableBlockMap.keySet()) {
            if (blockHolder.get().equals(target)) {
                Player player = event.getEntity();
                level.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!level.isClientSide) {
                    level.setBlock(pos, NeoForgeStrippableBlockHelper.strippableBlockMap.get(blockHolder).get().withPropertiesOf(targetBlockState), 11);
                    event.getItemStack().hurtAndBreak(1, player, LivingEntity.getSlotForHand(event.getHand()));
                }
                return;
            }
        }
    }
}
