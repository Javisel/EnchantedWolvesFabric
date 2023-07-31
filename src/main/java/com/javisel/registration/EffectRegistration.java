package com.javisel.registration;

import com.javisel.EnchantedWolves;
import com.javisel.effects.*;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EffectRegistration {


    public static final StatusEffect VULNERABLE = new MobEffectBase(StatusEffectCategory.HARMFUL, 16711935);

    public static final StatusEffect SPACIAL_BIND = new MobEffectBase(StatusEffectCategory.HARMFUL, 0x800080);
    public static final StatusEffect PACK_POWER = new PackPower();
    public static final StatusEffect PACK_POWER_COOLDOWN = new MobEffectBase(StatusEffectCategory.HARMFUL, 0);
    public static final StatusEffect BOUNTIFUL = new Bountiful();

    public static final StatusEffect HEARTY = new Hearty();
    public static final StatusEffect SWIFTNESS = new Swiftness();

    public static void registerEffects() {


        Registry.register(Registries.STATUS_EFFECT, new Identifier(EnchantedWolves.MODID, "vulnerable"), VULNERABLE);
      //  Registry.register(Registries.STATUS_EFFECT, new Identifier(EnchantedWolves.MODID, "spacial_bind"), SPACIAL_BIND);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(EnchantedWolves.MODID, "pack_power"), PACK_POWER);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(EnchantedWolves.MODID, "pack_power_cooldown"), PACK_POWER_COOLDOWN);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(EnchantedWolves.MODID, "bountiful"), BOUNTIFUL);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(EnchantedWolves.MODID, "heart"), HEARTY);
        Registry.register(Registries.STATUS_EFFECT, new Identifier(EnchantedWolves.MODID, "swiftness"), SWIFTNESS);


    }


}
