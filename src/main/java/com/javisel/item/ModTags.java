package com.javisel.item;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import static com.javisel.EnchantedWolves.MODID;

public class ModTags {

    public static final TagKey<Item> COLLARS = TagKey.of(RegistryKeys.ITEM, new Identifier(MODID, "collars"));
    public static final TagKey<EntityType<?>> COLLAR_WEARERS = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(MODID, "collar_wearers"));

}
