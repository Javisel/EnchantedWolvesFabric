package com.javisel.effects;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectCategory;

public class Swiftness extends MobEffectBase {
    public Swiftness() {
        super(StatusEffectCategory.BENEFICIAL, 0);

        addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED,"5f6baa70-970c-4d6b-a612-7e1712ec76fd",0.15, EntityAttributeModifier.Operation.MULTIPLY_BASE);

    }
}
