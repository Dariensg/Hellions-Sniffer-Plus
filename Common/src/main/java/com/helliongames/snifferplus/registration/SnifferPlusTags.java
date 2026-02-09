package com.helliongames.snifferplus.registration;

import com.helliongames.snifferplus.Constants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public class SnifferPlusTags {

    public static final TagKey<Structure> SNIFFER_OUTPOSTS = structure("sniffer_outposts");

    private static TagKey<Structure> structure(String name) {
        return TagKey.create(Registries.STRUCTURE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, name));
    }
}
