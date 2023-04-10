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
        this.addSlot(new Slot(snifferContainer, 0, 8, 18) {
            public boolean mayPlace(@NotNull ItemStack $$0) {
                return $$0.is(Items.SADDLE) && !this.hasItem();
            }
        });
        this.addSlot(new Slot(snifferContainer, 1, 8, 36) {
            public int getMaxStackSize() {
                return 1;
            }
        });
        if (this.hasChest(sniffer)) {
            for(int $$6 = 0; $$6 < 3; ++$$6) {
                for(int $$7 = 0; $$7 < ((SnifferAccess) sniffer).getInventoryColumns(); ++$$7) {
                    this.addSlot(new Slot(snifferContainer, 2 + $$7 + $$6 * ((SnifferAccess) sniffer).getInventoryColumns(), 80 + $$7 * 18, 18 + $$6 * 18));
                }
            }
        }

        for(int $$8 = 0; $$8 < 3; ++$$8) {
            for(int $$9 = 0; $$9 < 9; ++$$9) {
                this.addSlot(new Slot(playerInventory, $$9 + $$8 * 9 + 9, 8 + $$9 * 18, 102 + $$8 * 18 + -18));
            }
        }

        for(int $$10 = 0; $$10 < 9; ++$$10) {
            this.addSlot(new Slot(playerInventory, $$10, 8 + $$10 * 18, 142));
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
            ItemStack $$4 = slot.getItem();
            stack = $$4.copy();
            int $$5 = this.snifferContainer.getContainerSize();
            if (slotNumber < $$5) {
                if (!this.moveItemStackTo($$4, $$5, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.getSlot(1).mayPlace($$4) && !this.getSlot(1).hasItem()) {
                if (!this.moveItemStackTo($$4, 1, 2, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.getSlot(0).mayPlace($$4)) {
                if (!this.moveItemStackTo($$4, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if ($$5 <= 2 || !this.moveItemStackTo($$4, 2, $$5, false)) {
                int $$7 = $$5 + 27;
                int $$9 = $$7 + 9;
                if (slotNumber >= $$7 && slotNumber < $$9) {
                    if (!this.moveItemStackTo($$4, $$5, $$7, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (slotNumber < $$7) {
                    if (!this.moveItemStackTo($$4, $$7, $$9, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.moveItemStackTo($$4, $$7, $$7, false)) {
                    return ItemStack.EMPTY;
                }

                return ItemStack.EMPTY;
            }

            if ($$4.isEmpty()) {
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
