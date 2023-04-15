package com.helliongames.snifferplus.world.inventory;

import com.helliongames.snifferplus.access.SnifferAccess;
import net.minecraft.world.Container;
import net.minecraft.world.entity.animal.sniffer.Sniffer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public class SnifferInventoryMenu extends AbstractContainerMenu {
    private final Container snifferContainer;
    private final Sniffer sniffer;

    public SnifferInventoryMenu(int counter, Inventory playerInventory, Container snifferContainer, final Sniffer sniffer) {
        super(null, counter);
        this.snifferContainer = snifferContainer;
        this.sniffer = sniffer;
        snifferContainer.startOpen(playerInventory.player);
        this.addSlot(new Slot(snifferContainer, 0, 8, 17) {
            public boolean mayPlace(@NotNull ItemStack stack) {
                return stack.is(Items.SADDLE) && !this.hasItem();
            }
        });
        this.addSlot(new Slot(snifferContainer, 1, 8, 35) {
            public int getMaxStackSize() {
                return 1;
            }

            @Override
            public boolean mayPlace(ItemStack stack) {
                return stack.is(Items.WHITE_BANNER);
            }
        });

        if (this.hasChest(sniffer)) {
            for(int i = 0; i < 4; ++i) {
                for(int j = 0; j < ((SnifferAccess) sniffer).getInventoryColumns(); ++j) {
                    this.addSlot(new Slot(snifferContainer, 2 + j + i * ((SnifferAccess) sniffer).getInventoryColumns(), 80 + j * 18, 8 + i * 18));
                }
            }
        }

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 102 + i * 18 + -18));
            }
        }

        for(int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }

    }

    public boolean stillValid(Player player) {
        return !((SnifferAccess) this.sniffer).hasInventoryChanged(this.snifferContainer) && this.snifferContainer.stillValid(player) && this.sniffer.isAlive() && this.sniffer.distanceTo(player) < 8.0F;
    }

    private boolean hasChest(Sniffer sniffer) {
        return ((SnifferAccess) sniffer).hasChest();
    }

    public ItemStack quickMoveStack(Player player, int slotNumber) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotNumber);

        if (slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            stack = stackInSlot.copy();
            int size = this.snifferContainer.getContainerSize();
            if (slotNumber < size) {
                if (!this.moveItemStackTo(stackInSlot, size, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.getSlot(1).mayPlace(stackInSlot) && !this.getSlot(1).hasItem()) {
                if (!this.moveItemStackTo(stackInSlot, 1, 2, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.getSlot(0).mayPlace(stackInSlot) && !this.getSlot(0).hasItem()) {
                if (!this.moveItemStackTo(stackInSlot, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (size <= 2 || !this.moveItemStackTo(stackInSlot, 2, size, false)) {
                int $$7 = size + 27;
                int $$9 = $$7 + 9;
                if (slotNumber >= $$7 && slotNumber < $$9) {
                    if (!this.moveItemStackTo(stackInSlot, size, $$7, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotNumber < $$7) {
                    if (!this.moveItemStackTo(stackInSlot, $$7, $$9, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.moveItemStackTo(stackInSlot, $$7, $$7, false)) {
                    return ItemStack.EMPTY;
                }

                return ItemStack.EMPTY;
            }

            if (stackInSlot.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return stack;
    }

    public void removed(Player player) {
        super.removed(player);
        this.snifferContainer.stopOpen(player);
    }
}
