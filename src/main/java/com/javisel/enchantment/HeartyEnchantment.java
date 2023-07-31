package com.javisel.enchantment;

import com.javisel.registration.EffectRegistration;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;

public class HeartyEnchantment extends CollarEnchantmentBase {


    public HeartyEnchantment() {
        super(Enchantment.Rarity.VERY_RARE, 1);

    }

    @Override
    public void tickWearer(int level, ItemStack stack, LivingEntity user) {
        super.tickWearer(level, stack, user);

         if (!user.hasStatusEffect(EffectRegistration.HEARTY)) {
            user.addStatusEffect(new StatusEffectInstance(EffectRegistration.HEARTY, 100, level - 1, false, false, false));
        }
    }
}
