package com.helliongames.snifferplus.entity;

import com.helliongames.snifferplus.registration.SnifferPlusEntities;
import com.helliongames.snifferplus.registration.SnifferPlusItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class StonePineBoat extends Boat {

    public StonePineBoat(EntityType<? extends Boat> entityType, Level level) {
        super(entityType, level);
    }

    public StonePineBoat(Level level, double x, double y, double z) {
        this(SnifferPlusEntities.STONE_PINE_BOAT.get(), level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    public Item getDropItem() {
        return SnifferPlusItems.STONE_PINE_BOAT.get();
    }
}
