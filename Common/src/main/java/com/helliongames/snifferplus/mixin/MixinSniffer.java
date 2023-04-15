package com.helliongames.snifferplus.mixin;

import com.helliongames.snifferplus.access.ServerPlayerAccess;
import com.helliongames.snifferplus.access.SnifferAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerListener;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HasCustomInventoryScreen;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Saddleable;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(Sniffer.class)
public abstract class MixinSniffer extends LivingEntity implements SnifferAccess, ContainerListener, HasCustomInventoryScreen, Saddleable {
    protected SimpleContainer inventory;

    private static final EntityDataAccessor<Boolean> HAS_CHEST = SynchedEntityData.defineId(Sniffer.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_SADDLED = SynchedEntityData.defineId(Sniffer.class, EntityDataSerializers.BOOLEAN);

    protected MixinSniffer(EntityType<? extends LivingEntity> $$0, Level $$1) {
        super($$0, $$1);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void snifferplus_createDataAndInventoryOnCreation(EntityType type, Level level, CallbackInfo ci) {
        this.entityData.define(HAS_CHEST, false);
        this.entityData.define(IS_SADDLED, false);
        this.createInventory();
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

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);

        if (!this.inventory.getItem(0).isEmpty()) {
            tag.put("SaddleItem", this.inventory.getItem(0).save(new CompoundTag()));
        }

        tag.putBoolean("Chested", this.hasChest());
        if (this.hasChest()) {
            ListTag $$1 = new ListTag();

            for(int $$2 = 2; $$2 < this.inventory.getContainerSize(); ++$$2) {
                ItemStack $$3 = this.inventory.getItem($$2);
                if (!$$3.isEmpty()) {
                    CompoundTag $$4 = new CompoundTag();
                    $$4.putByte("Slot", (byte)$$2);
                    $$3.save($$4);
                    $$1.add($$4);
                }
            }

            tag.put("Items", $$1);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);

        if (tag.contains("SaddleItem", 10)) {
            ItemStack $$4 = ItemStack.of(tag.getCompound("SaddleItem"));
            if ($$4.is(Items.SADDLE)) {
                this.inventory.setItem(0, $$4);
            }
        }

        this.setChest(tag.getBoolean("Chested"));
        this.createInventory();
        if (this.hasChest()) {
            ListTag $$1 = tag.getList("Items", 10);

            for(int $$2 = 0; $$2 < $$1.size(); ++$$2) {
                CompoundTag $$3 = $$1.getCompound($$2);
                int $$4 = $$3.getByte("Slot") & 255;
                if ($$4 >= 2 && $$4 < this.inventory.getContainerSize()) {
                    this.inventory.setItem($$4, ItemStack.of($$3));
                }
            }
        }

        this.updateContainerEquipment();
    }

    public void openCustomInventoryScreen(Player player) {
        if (!this.level.isClientSide && (!this.isVehicle() || this.hasPassenger(player))) {
            ((ServerPlayerAccess) player).openSnifferInventory((Sniffer) (Object) this, this.inventory);
        }
    }

    @Override
    public SlotAccess getSlot(int $$0) {

        if ($$0 == 499) {
            return new SlotAccess() {
                public ItemStack get() {
                    return MixinSniffer.this.hasChest() ? new ItemStack(Items.CHEST) : ItemStack.EMPTY;
                }

                public boolean set(ItemStack $$0) {
                    if ($$0.isEmpty()) {
                        if (MixinSniffer.this.hasChest()) {
                            MixinSniffer.this.setChest(false);
                            MixinSniffer.this.createInventory();
                        }

                        return true;
                    } else if ($$0.is(Items.CHEST)) {
                        if (!MixinSniffer.this.hasChest()) {
                            MixinSniffer.this.setChest(true);
                            MixinSniffer.this.createInventory();
                        }

                        return true;
                    } else {
                        return false;
                    }
                }
            };
        } else {

            int $$1 = $$0 - 400;
            if ($$1 >= 0 && $$1 < 2 && $$1 < this.inventory.getContainerSize()) {
                if ($$1 == 0) {
                    return this.createEquipmentSlotAccess($$1, ($$0x) -> $$0x.isEmpty() || $$0x.is(Items.SADDLE));
                }

                if ($$1 == 1) {
                    return SlotAccess.NULL;
                }
            }

            int $$2 = $$0 - 500 + 2;
            return $$2 >= 2 && $$2 < this.inventory.getContainerSize() ? SlotAccess.forContainer(this.inventory, $$2) : super.getSlot($$0);
        }
    }

    private SlotAccess createEquipmentSlotAccess(final int $$0, final Predicate<ItemStack> $$1) {
        return new SlotAccess() {
            public ItemStack get() {
                return MixinSniffer.this.inventory.getItem($$0);
            }

            public boolean set(ItemStack $$0x) {
                if (!$$1.test($$0x)) {
                    return false;
                } else {
                    MixinSniffer.this.inventory.setItem($$0, $$0x);
                    MixinSniffer.this.updateContainerEquipment();
                    return true;
                }
            }
        };
    }

    @Override
    protected void dropEquipment() {
        super.dropEquipment();

        if (this.inventory != null) {
            for(int i = 0; i < this.inventory.getContainerSize(); ++i) {
                ItemStack stack = this.inventory.getItem(i);
                if (!stack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(stack)) {
                    this.spawnAtLocation(stack);
                }
            }
        }

        if (this.hasChest()) {
            if (!this.level.isClientSide) {
                this.spawnAtLocation(Blocks.CHEST);
            }

            this.setChest(false);
        }
    }

    public boolean hasChest() {
        return this.entityData.get(HAS_CHEST);
    }

    private void setChest(boolean hasChest) {
        this.entityData.set(HAS_CHEST, hasChest);
    }

    public boolean isSaddled() {
        return this.entityData.get(IS_SADDLED);
    }

    protected void updateContainerEquipment() {
        if (!this.level.isClientSide) {
            this.entityData.set(IS_SADDLED, !this.inventory.getItem(0).isEmpty());
        }
    }

    @Override
    public void containerChanged(Container var1) {
        boolean saddled = this.isSaddled();
        this.updateContainerEquipment();
        if (this.tickCount > 20 && !saddled && this.isSaddled()) {
            this.playSound(SoundEvents.HORSE_SADDLE, 0.5F, 1.0F);
        }
    }

    public boolean hasInventoryChanged(Container container) {
        return this.inventory != container;
    }

    private void equipChest(Player player, ItemStack itemStack) {
        this.setChest(true);
        this.playChestEquipsSound();
        if (!player.getAbilities().instabuild) {
            itemStack.shrink(1);
        }
        this.createInventory();
    }

    public void equipSaddle(@Nullable SoundSource source) {
        this.inventory.setItem(0, new ItemStack(Items.SADDLE));
    }

    protected void playChestEquipsSound() {
        this.playSound(SoundEvents.DONKEY_CHEST, 1.0f, (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.0f);
    }

    protected int getInventorySize() {
        return this.hasChest() ? 22 : 2;
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
        this.updateContainerEquipment();
    }
}
