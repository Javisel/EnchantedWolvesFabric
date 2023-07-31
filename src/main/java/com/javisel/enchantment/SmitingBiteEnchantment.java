package com.javisel.enchantment;

import com.javisel.registration.EnchantmentRegistration;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.LivingEntity;

public class SmitingBiteEnchantment extends CollarEnchantmentBase {
    public SmitingBiteEnchantment() {
        super(Rarity.COMMON, 5);
        super.getIncompatibles().add(EnchantmentRegistration.PEST_PATROL);
        super.getIncompatibles().add(EnchantmentRegistration.TRAUMA);

    }

    @Override
    public float getAttackDamageFromCollar(int level, LivingEntity target) {

        return target.getGroup() == EntityGroup.UNDEAD ? super.getAttackDamageFromCollar(level, target) + level * 2.5f : +super.getAttackDamageFromCollar(level, target);
    }


}
