package com.helliongames.snifferplus.mixin;

import com.helliongames.snifferplus.access.ServerPlayerAccess;
import com.helliongames.snifferplus.platform.Services;
import com.helliongames.snifferplus.world.inventory.SnifferInventoryMenu;
import com.mojang.authlib.GameProfile;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ServerPlayer.class)
public abstract class MixinServerPlayer extends Player implements ServerPlayerAccess {

    @Shadow private int containerCounter;

    @Shadow protected abstract void initMenu(AbstractContainerMenu abstractContainerMenu);

    @Shadow protected abstract void nextContainerCounter();

    public MixinServerPlayer(Level $$0, BlockPos $$1, float $$2, GameProfile $$3) {
        super($$0, $$1, $$2, $$3);
    }

    @Override
    public void openSnifferInventory(Sniffer sniffer, Container container) {
        if (this.containerMenu != this.inventoryMenu) {
            this.closeContainer();
        }

        this.nextContainerCounter();
        Services.PACKET_HELPER.sendOpenSnifferScreenPacket((ServerPlayer) (Object) this, sniffer, container);
        this.containerMenu = new SnifferInventoryMenu(this.containerCounter, this.getInventory(), container, sniffer);
        this.initMenu(this.containerMenu);
    }

    @Override
    public int getContainerCounter() {
        return this.containerCounter;
    }
}
