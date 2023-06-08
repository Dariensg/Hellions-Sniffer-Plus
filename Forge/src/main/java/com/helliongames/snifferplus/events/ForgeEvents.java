package com.helliongames.snifferplus.events;

import com.helliongames.snifferplus.platform.ForgeStrippableBlockHelper;
import com.helliongames.snifferplus.registration.util.RegistryObject;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockState targetBlockState = level.getBlockState(event.getPos());
        Block target = targetBlockState.getBlock();
        BlockPos pos = event.getPos();

        for (RegistryObject<Block> blockRegistryObject : ForgeStrippableBlockHelper.strippableBlockMap.keySet()) {
            if (blockRegistryObject.get().equals(target)) {
                Player player = event.getEntity();
                level.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
                if (!level.isClientSide) {
                    level.setBlock(pos, ForgeStrippableBlockHelper.strippableBlockMap.get(blockRegistryObject).get().withPropertiesOf(targetBlockState), 11);
                    if (player != null) {
                        event.getItemStack().hurtAndBreak(1, player, (usingPlayer) -> {
                            usingPlayer.broadcastBreakEvent(event.getHand());
                        });
                    }
                }
                return;
            }
        }
    }
}
