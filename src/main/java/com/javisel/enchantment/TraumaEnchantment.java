package com.javisel.enchantment;

import com.javisel.registration.EnchantmentRegistration;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.LivingEntity;

public class TraumaEnchantment extends CollarEnchantmentBase {
    public TraumaEnchantment() {
        super(Rarity.COMMON, 5);
        super.getIncompatibles().add(EnchantmentRegistration.PEST_PATROL);
        super.getIncompatibles().add(EnchantmentRegistration.SMITING_BITE);
    }


    @Override
    public float getAttackDamageFromCollar(int level, LivingEntity target) {
        return (float) (super.getAttackDamageFromCollar(level, target)  + (0.5 * level -1) + 1.0f);
    }


}
