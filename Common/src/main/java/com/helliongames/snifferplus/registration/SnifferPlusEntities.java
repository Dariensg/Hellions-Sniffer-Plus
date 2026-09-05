package com.helliongames.snifferplus.registration;

import com.helliongames.hellionsapi.registration.holders.EntityTypeDataHolder;
import com.helliongames.hellionsapi.registration.registries.HellionsAPIEntityRegistry;
import com.helliongames.snifferplus.Constants;
import com.helliongames.snifferplus.entity.StonePineBoat;
import com.helliongames.snifferplus.entity.StonePineChestBoat;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class SnifferPlusEntities {
    /**
     * The provider for entities
     */
    public static final HellionsAPIEntityRegistry ENTITIES = new HellionsAPIEntityRegistry(Constants.MOD_ID);

    public static EntityTypeDataHolder<StonePineBoat> STONE_PINE_BOAT = ENTITIES.register("stone_pine_boat", EntityTypeDataHolder.of(() ->
            EntityType.Builder.<StonePineBoat>of(StonePineBoat::new, MobCategory.MISC).sized(1.375f, 0.5625f).clientTrackingRange(10).build("stone_pine_boat")));

    public static EntityTypeDataHolder<StonePineChestBoat> STONE_PINE_CHEST_BOAT = ENTITIES.register("stone_pine_chest_boat", EntityTypeDataHolder.of(() ->
            EntityType.Builder.<StonePineChestBoat>of(StonePineChestBoat::new, MobCategory.MISC).sized(1.375f, 0.5625f).clientTrackingRange(10).build("stone_pine_chest_boat")));

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void loadClass() {}
}
