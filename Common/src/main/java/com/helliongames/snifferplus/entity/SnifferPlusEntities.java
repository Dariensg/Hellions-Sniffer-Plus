package com.helliongames.snifferplus.entity;

import com.helliongames.snifferplus.Constants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.HashMap;
import java.util.Map;

public class SnifferPlusEntities {
    private static final Map<ResourceLocation, EntityType> entities = new HashMap<>();

    public static final EntityType<StonePineBoat> STONE_PINE_BOAT = registerEntity(
            "stone_pine_boat",
            EntityType.Builder.<StonePineBoat>of(StonePineBoat::new, MobCategory.MISC).sized(1.375f, 0.5625f).clientTrackingRange(10).build("stone_pine_boat")
    );

    public static final EntityType<StonePineChestBoat> STONE_PINE_CHEST_BOAT = registerEntity(
            "stone_pine_chest_boat",
            EntityType.Builder.<StonePineChestBoat>of(StonePineChestBoat::new, MobCategory.MISC).sized(1.375f, 0.5625f).clientTrackingRange(10).build("stone_pine_chest_boat")
    );

    private static EntityType registerEntity(String identifier, EntityType entity) {
        entities.put(new ResourceLocation(Constants.MOD_ID, identifier), entity);
        return entity;
    }

    public static void register() {
        for (Map.Entry<ResourceLocation, EntityType> entry : entities.entrySet()) {
            Registry.register(BuiltInRegistries.ENTITY_TYPE, entry.getKey(), entry.getValue());
        }
    }
}
