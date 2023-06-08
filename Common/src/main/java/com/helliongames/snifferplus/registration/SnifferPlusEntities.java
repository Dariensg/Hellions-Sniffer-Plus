package com.helliongames.snifferplus.registration;

import com.helliongames.snifferplus.Constants;
import com.helliongames.snifferplus.entity.StonePineBoat;
import com.helliongames.snifferplus.entity.StonePineChestBoat;
import com.helliongames.snifferplus.registration.util.RegistrationProvider;
import com.helliongames.snifferplus.registration.util.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class SnifferPlusEntities {
    /**
     * The provider for entities
     */
    public static final RegistrationProvider<EntityType<?>> ENTITIES = RegistrationProvider.get(Registries.ENTITY_TYPE, Constants.MOD_ID);

    public static RegistryObject<EntityType<StonePineBoat>> STONE_PINE_BOAT = ENTITIES.register("stone_pine_boat", () ->
            EntityType.Builder.<StonePineBoat>of(StonePineBoat::new, MobCategory.MISC).sized(1.375f, 0.5625f).clientTrackingRange(10).build("stone_pine_boat"));

    public static RegistryObject<EntityType<StonePineChestBoat>> STONE_PINE_CHEST_BOAT = ENTITIES.register("stone_pine_chest_boat", () ->
            EntityType.Builder.<StonePineChestBoat>of(StonePineChestBoat::new, MobCategory.MISC).sized(1.375f, 0.5625f).clientTrackingRange(10).build("stone_pine_chest_boat"));

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void loadClass() {}
}
