package com.javisel.effects;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectCategory;

public class PackPower extends MobEffectBase {
    public PackPower() {
        super(StatusEffectCategory.BENEFICIAL, 0xFFFFFF);

        addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, "62f5dceb-7231-460f-861d-02bc3f303a92", 0.1, EntityAttributeModifier.Operation.MULTIPLY_BASE);
        addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, "62f5dceb-7231-460f-861d-02bc3f303a92", 1, EntityAttributeModifier.Operation.ADDITION);


    }


}
