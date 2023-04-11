package com.helliongames.snifferplus.mixin;

import com.helliongames.snifferplus.access.SnifferAccess;
import com.helliongames.snifferplus.platform.Services;
import com.helliongames.snifferplus.world.inventory.SnifferInventoryMenu;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.ContainerListener;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Sniffer.class)
public abstract class MixinSniffer extends Entity implements SnifferAccess, ContainerListener, MenuProvider {
    protected SimpleContainer inventory;

    private static final EntityDataAccessor<Boolean> HAS_CHEST = SynchedEntityData.defineId(Sniffer.class, EntityDataSerializers.BOOLEAN);

    public MixinSniffer(EntityType<?> $$0, Level $$1) {
        super($$0, $$1);
    }

    @Inject(method = "mobInteract", at = @At("RETURN"), cancellable = true)
    private void snifferplus_equipChest(Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (player.isSecondaryUseActive()) {
            this.openCustomInventoryScreen(player);
            cir.setReturnValue(InteractionResult.sidedSuccess(this.level.isClientSide));
        } else if (!itemStack.isEmpty()) {
            if (!this.hasChest() && itemStack.is(Items.CHEST)) {
                this.equipChest(player, itemStack);
                cir.setReturnValue(InteractionResult.sidedSuccess(this.level.isClientSide));
            }
        }
    }

    public void openCustomInventoryScreen(Player player) {
        if (!this.level.isClientSide) {
            Services.PACKET_HELPER.sendOpenSnifferScreenPacket((ServerPlayer) player, (Sniffer) (Object) this, this.inventory);
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int var1, Inventory var2, Player var3) {
        return new SnifferInventoryMenu(var1, var2, this.inventory, (Sniffer) (Object) this);
    }

    public boolean hasChest() {
        return this.entityData.get(HAS_CHEST);
    }

    private void setChest(boolean hasChest) {
        this.entityData.set(HAS_CHEST, hasChest);
    }

    private void equipChest(Player player, ItemStack itemStack) {
        this.setChest(true);
        this.playChestEquipsSound();
        if (!player.getAbilities().instabuild) {
            itemStack.shrink(1);
        }
        this.createInventory();
    }

    protected void playChestEquipsSound() {
        this.playSound(SoundEvents.DONKEY_CHEST, 1.0f, (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.0f);
    }

    protected int getInventorySize() {
        return 2;
    }

    @Override
    public int getInventoryColumns() {
        return 5;
    }

    protected void createInventory() {
        SimpleContainer simpleContainer = this.inventory;
        this.inventory = new SimpleContainer(this.getInventorySize());
        if (simpleContainer != null) {
            simpleContainer.removeListener(this);
            int i = Math.min(simpleContainer.getContainerSize(), this.inventory.getContainerSize());
            for (int j = 0; j < i; ++j) {
                ItemStack itemStack = simpleContainer.getItem(j);
                if (itemStack.isEmpty()) continue;
                this.inventory.setItem(j, itemStack.copy());
            }
        }
        this.inventory.addListener(this);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void snifferplus_addChestData(EntityType entityType, Level level, CallbackInfo ci) {
        this.entityData.define(HAS_CHEST, false);
    }
}
