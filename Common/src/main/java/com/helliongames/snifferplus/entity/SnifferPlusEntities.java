package com.helliongames.snifferplus.entity;

import com.helliongames.snifferplus.Constants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class SnifferPlusEntities {
    public static EntityType<StonePineBoat> STONE_PINE_BOAT;

    public static EntityType<StonePineChestBoat> STONE_PINE_CHEST_BOAT;

    private static EntityType registerEntity(String identifier, EntityType entity) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, new ResourceLocation(Constants.MOD_ID, identifier), entity);
    }

    public static void registerEntities() {
        STONE_PINE_BOAT = registerEntity(
                "stone_pine_boat",
                EntityType.Builder.<StonePineBoat>of(StonePineBoat::new, MobCategory.MISC).sized(1.375f, 0.5625f).clientTrackingRange(10).build("stone_pine_boat")
        );

        STONE_PINE_CHEST_BOAT = registerEntity(
                "stone_pine_chest_boat",
                EntityType.Builder.<StonePineChestBoat>of(StonePineChestBoat::new, MobCategory.MISC).sized(1.375f, 0.5625f).clientTrackingRange(10).build("stone_pine_chest_boat")
        );
    }
}
