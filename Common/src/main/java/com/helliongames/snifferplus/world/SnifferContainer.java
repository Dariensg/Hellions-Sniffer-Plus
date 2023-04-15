package com.helliongames.snifferplus.world;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;

public class SnifferContainer extends SimpleContainer {

    public SnifferContainer(int size) {
        super(size);
    }

    public SnifferContainer(ItemStack... stacks) {
        super(stacks);
    }

    @Override
    public ItemStack addItem(ItemStack stack) {
        if (stack.isEmpty()) {
            return ItemStack.EMPTY;
        } else {
            ItemStack stackCopy = stack.copy();
            this.moveItemToOccupiedSlotsWithSameType(stackCopy);
            if (stackCopy.isEmpty()) {
                return ItemStack.EMPTY;
            } else {
                this.moveItemToEmptySlots(stackCopy);
                return stackCopy.isEmpty() ? ItemStack.EMPTY : stackCopy;
            }
        }
    }

    private void moveItemToEmptySlots(ItemStack stack) {
        for(int i = 2; i < this.getContainerSize(); ++i) {
            ItemStack itemInSlot = this.getItem(i);
            if (itemInSlot.isEmpty()) {
                this.setItem(i, stack.copyAndClear());
                return;
            }
        }
    }

    private void moveItemToOccupiedSlotsWithSameType(ItemStack stack) {
        for(int i = 2; i < this.getContainerSize(); ++i) {
            ItemStack stackInSlot = this.getItem(i);
            if (ItemStack.isSameItemSameTags(stackInSlot, stack)) {
                this.moveItemsBetweenStacks(stack, stackInSlot);
                if (stack.isEmpty()) {
                    return;
                }
            }
        }
    }

    private void moveItemsBetweenStacks(ItemStack stackInFirstSlot, ItemStack stackInSecondSlot) {
        int $$2 = Math.min(this.getMaxStackSize(), stackInSecondSlot.getMaxStackSize());
        int $$3 = Math.min(stackInFirstSlot.getCount(), $$2 - stackInSecondSlot.getCount());
        if ($$3 > 0) {
            stackInSecondSlot.grow($$3);
            stackInFirstSlot.shrink($$3);
            this.setChanged();
        }

    }
}
